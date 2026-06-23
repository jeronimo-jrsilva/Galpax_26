package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.sql.SQLIntegrityConstraintViolationException;

public class Bancodedados {
	
	public Connection conexão = null;
	public Statement consultas = null;
	public ResultSet resultado = null;
	
	Scanner numero = new Scanner(System.in);
	Scanner texto = new Scanner(System.in);
	
	public void conectar() {
		java.util.Properties props = new java.util.Properties();
		
		java.io.File configFile = new java.io.File("secrets.properties");
		
		try (java.io.FileInputStream fis = new java.io.FileInputStream(configFile)) {
			props.load(fis);
			String servidor = props.getProperty("db.url");
			String usuario = props.getProperty("db.user");
			String senha = props.getProperty("db.password");
			String driver = props.getProperty("db.driver");
			
			Class.forName(driver);
			this.conexão = DriverManager.getConnection(servidor, usuario, senha);
			this.consultas = this.conexão.createStatement();
			System.out.println("✅ Conectado com sucesso ao MySQL via secrets.properties");
		} catch (Exception e) {
			System.err.println("⚠️ Aviso: secrets.properties não encontrado ou erro na leitura. Usando fallback localhost.");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.conexão = DriverManager.getConnection("jdbc:mysql://localhost:3306/galpax", "root", "Aluno");
				this.consultas = this.conexão.createStatement();
				System.out.println("✅ Conectado via Fallback (Localhost/Root)");
			} catch (Exception e2) {
				System.err.println("❌ Erro Crítico: Falha total na conexão com o banco de dados.");
			}
		}
	}
	
	public boolean verificar() {
		if(this.conexão!= null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void desconectar() {
		try {
			if (this.conexão != null) this.conexão.close();
		} catch(Exception e) { }
	}
	
	
	public String realizarLogin(String email, String senha) {
		
		String queryLoja = "SELECT nivel_loja FROM cad_loja WHERE email_loja = ? AND senha = ?";
		
		try (PreparedStatement pstmtLoja = this.conexão.prepareStatement(queryLoja)) {
			pstmtLoja.setString(1, email);
			pstmtLoja.setString(2, senha);
			
			try (ResultSet rsLoja = pstmtLoja.executeQuery()) {
				if (rsLoja.next()) {
					
					return rsLoja.getString("nivel_loja"); 
				}
			}
		} catch (Exception e) {
			System.err.println("Erro ao autenticar na tabela cad_loja: " + e.getMessage());
		}
		
		
		String queryAdmin = "SELECT email_admin FROM cad_admin WHERE email_admin = ? AND senha_admin = ?";
		
		try (PreparedStatement pstmtAdmin = this.conexão.prepareStatement(queryAdmin)) {
			pstmtAdmin.setString(1, email);
			pstmtAdmin.setString(2, senha);
			
			try (ResultSet rsAdmin = pstmtAdmin.executeQuery()) {
				if (rsAdmin.next()) {
					
					return "admin"; 
				}
			}
		} catch (Exception e) {
			System.err.println("Erro ao autenticar na tabela cad_admin: " + e.getMessage());
		}
		
		
		return null; 
	}

	public boolean inserirVeiculo(String placa, String modelo, String cnh) {

		String query = "INSERT INTO cad_carro (placa_carro, modelo_carro, cnh_carro) VALUES (?, ?, ?)";

		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {

			pstmt.setString(1, placa);
			pstmt.setString(2, modelo);
			pstmt.setString(3, cnh);

			int linhasAfetadas = pstmt.executeUpdate();

			return linhasAfetadas > 0;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				"Erro ao cadastrar veículo: " + e.getMessage(),
				"Aviso",
				JOptionPane.ERROR_MESSAGE
			);

			return false;
		}
	}
	
	public boolean inserirLojaCompleta(String nome, String cnpj, String responsavel, String telefone,
	        String email, String endereco, String sala, String tipo,
	        String aluguel, String status, String nivel, String senha) {

	    String query = "INSERT INTO cad_loja (nome_loja, cnpj_loja, responsavel_loja, telefone_loja, "
	            + "email_loja, endereco_loja, sala_loja, tipo_loja, aluguel_loja, status_loja, nivel_loja, senha) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        this.conexão.setAutoCommit(false);

	        int idLojaGerado = 0;

	        try (PreparedStatement stmt = this.conexão.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	            stmt.setString(1, nome);
	            stmt.setString(2, cnpj);
	            stmt.setString(3, responsavel);
	            stmt.setString(4, telefone);
	            stmt.setString(5, email);
	            stmt.setString(6, endereco);
	            stmt.setString(7, sala);
	            stmt.setString(8, tipo);
	            stmt.setBigDecimal(9, new java.math.BigDecimal(aluguel));
	            stmt.setString(10, status);
	            stmt.setString(11, nivel);
	            stmt.setString(12, senha);

	            int linhasAfetadas = stmt.executeUpdate();

	            if (linhasAfetadas == 0) {
	                this.conexão.rollback();
	                return false;
	            }

	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    idLojaGerado = rs.getInt(1);
	                } else {
	                    this.conexão.rollback();
	                    JOptionPane.showMessageDialog(
	                            null,
	                            "A loja foi cadastrada, mas não foi possível obter o ID gerado.",
	                            "Aviso",
	                            JOptionPane.ERROR_MESSAGE
	                    );
	                    return false;
	                }
	            }
	        }

	        double valorAluguel = Double.parseDouble(aluguel);

	        gerarMensalidadesAteFinalDoAno(idLojaGerado, valorAluguel);

	        this.conexão.commit();
	        this.conexão.setAutoCommit(true);

	        return true;

	    } catch (Exception e) {
	        try {
	            if (this.conexão != null) {
	                this.conexão.rollback();
	                this.conexão.setAutoCommit(true);
	            }
	        } catch (Exception erroRollback) {
	            erroRollback.printStackTrace();
	        }

	        JOptionPane.showMessageDialog(
	                null,
	                "Erro ao cadastrar loja e gerar mensalidades: " + e.getMessage(),
	                "Aviso",
	                JOptionPane.ERROR_MESSAGE
	        );

	        return false;
	    }
	}
	private String gerarCodigoRecibo() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numeros = "0123456789";
		String caracteres = letras + numeros;

		StringBuilder codigo = new StringBuilder("REC-");

		java.util.Random random = new java.util.Random();

		for (int i = 0; i < 8; i++) {
			int posicao = random.nextInt(caracteres.length());
			codigo.append(caracteres.charAt(posicao));
		}

		return codigo.toString();
	}
	private void gerarMensalidadesAteFinalDoAno(int idLoja, double valorMensalidade) throws Exception {

	    java.time.LocalDate hoje = java.time.LocalDate.now();

	    int anoAtual = hoje.getYear();
	    int mesInicial = hoje.getMonthValue();

	    int diaVencimento = 10;

	    /*
	     * Se hoje já passou do dia de vencimento, começa pelo próximo mês.
	     * Exemplo:
	     * Hoje: 22/06
	     * Primeiro boleto: 10/07
	     *
	     * Se quiser gerar também o mês atual, apague este if.
	     */
	    if (hoje.getDayOfMonth() > diaVencimento) {
	        mesInicial++;
	    }

	    String sql = "INSERT INTO mensalidade "
	            + "(id_loja, mensalidade, vencimento, data_pagamento, status) "
	            + "VALUES (?, ?, ?, NULL, 'Pendente')";

	    try (PreparedStatement stmt = this.conexão.prepareStatement(sql)) {

	        for (int mes = mesInicial; mes <= 12; mes++) {

	            java.time.LocalDate vencimento = java.time.LocalDate.of(anoAtual, mes, diaVencimento);

	            stmt.setInt(1, idLoja);
	            stmt.setDouble(2, valorMensalidade);
	            stmt.setDate(3, java.sql.Date.valueOf(vencimento));

	            stmt.executeUpdate();
	        }
	    }
	}
	
	public ResultSet listarVeiculos() {
		try {
			String query = "select * from cad_carro";
			this.resultado = this.consultas.executeQuery(query);
			return this.resultado;
		} catch(Exception e){
			System.out.println("Erro ao buscar veículos: " + e.getMessage());
			return null;
		}
	}

	public ResultSet buscarVeiculo(String placa) {
	    try {
	        String sql = "SELECT * FROM cad_carro WHERE placa_carro = ?";

	        PreparedStatement stmt =
	                conexão.prepareStatement(sql);

	        stmt.setString(1, placa);

	        return stmt.executeQuery();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public List<Mensalidade> listarMensalidades() {

		List<Mensalidade> lista = new ArrayList<>();

		try {

			String query = "SELECT m.*, l.nome_loja "
			        + "FROM mensalidade m "
			        + "INNER JOIN cad_loja l ON m.id_loja = l.id_loja "
			        + "WHERE l.email_loja = ?";

			this.resultado = this.consultas.executeQuery(query);

			while(this.resultado.next()) {

				Mensalidade m = new Mensalidade();
				
				m.setid_mensalidade(this.resultado.getInt("id_mensalidade"));
				m.setid_loja(this.resultado.getInt("id_loja"));
				
				m.set_nome_loja(this.resultado.getString("nome_loja"));
				m.set_mensalidade(this.resultado.getDouble("mensalidade"));
				m.set_vencimento(this.resultado.getString("vencimento"));
				m.setdata_pagamento(this.resultado.getString("data_pagamento"));
				m.set_status(this.resultado.getString("status"));

				lista.add(m);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	
	public boolean pagarMensalidade(int idMensalidade, String formaPagamento) {

		String codigoRecibo = gerarCodigoRecibo();

		String sql = "UPDATE mensalidade "
				+ "SET status = 'Pago', "
				+ "data_pagamento = CURDATE(), "
				+ "forma_pagamento = ?, "
				+ "codigo_recibo = ? "
				+ "WHERE id_mensalidade = ?";

		try (PreparedStatement pstmt = this.conexão.prepareStatement(sql)) {

			pstmt.setString(1, formaPagamento);
			pstmt.setString(2, codigoRecibo);
			pstmt.setInt(3, idMensalidade);

			int linhasAfetadas = pstmt.executeUpdate();

			return linhasAfetadas > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// HISTÓRICO DE PAGAMENTOS
	public List<Mensalidade> historicoMensalidades() {

		List<Mensalidade> lista = new ArrayList<>();

		try {

			String query = "SELECT * FROM mensalidade " +
					"WHERE status LIKE 'Pago%'";

			this.resultado = this.consultas.executeQuery(query);

			while(this.resultado.next()) {

				Mensalidade m = new Mensalidade();

				m.setid_mensalidade(this.resultado.getInt("id_mensalidade"));
				m.setid_loja(this.resultado.getInt("id_loja"));
				m.set_mensalidade(this.resultado.getDouble("mensalidade"));
				m.set_vencimento(this.resultado.getString("vencimento"));
				m.setdata_pagamento(this.resultado.getString("data_pagamento"));
				m.set_status(this.resultado.getString("status"));

				lista.add(m);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public void verificarCliente(int sala) {
		String query = "SELECT * FROM cad_loja WHERE sala_loja = ?";

		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, String.valueOf(sala));

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String info = String.format(
						"Sala: %s\nNome: %s\nCNPJ: %s\nResponsável: %s\nTipo: %s",
						rs.getString("sala_loja"),
						rs.getString("nome_loja"),
						rs.getString("cnpj_loja"),
						rs.getString("responsavel_loja"),
						rs.getString("tipo_loja")
					);

					JOptionPane.showMessageDialog(
						null,
						info,
						"Informações da Loja",
						JOptionPane.INFORMATION_MESSAGE
					);

				} else {
					JOptionPane.showMessageDialog(
						null,
						"Nenhuma loja encontrada na sala " + sala
					);
				}
			}

		} catch (Exception e) {
			System.err.println("Erro ao buscar loja: " + e.getMessage());
		}
	}

	public boolean isGalpaoOcupado(int sala) {

		String query = "SELECT 1 FROM cad_loja WHERE sala_loja = ?";

		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, String.valueOf(sala));

			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}

		} catch (Exception e) {
			System.err.println("Erro ao verificar ocupação: " + e.getMessage());
			return false;
		}
	}
	
	public void popularComboSalasLivres(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		
		List<String> salasOcupadas = new ArrayList<>();
		String query = "SELECT sala_loja FROM cad_loja";
		
		try (PreparedStatement pstmt = this.conexão.prepareStatement(query);
			 ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				salasOcupadas.add(rs.getString("sala_loja"));
			}
			
			for (int i = 101; i <= 132; i++) {
				String sala = String.valueOf(i);
				if (!salasOcupadas.contains(sala)) {
					comboBox.addItem(sala);
				}
			}
		} catch (Exception e) {
			System.err.println("Erro ao carregar salas disponíveis: " + e.getMessage());
		}
	}
	
	

	public ResultSet listarLojas() {
		try {
			String query = "SELECT * FROM cad_loja";
			this.resultado = this.consultas.executeQuery(query);
			return this.resultado;
		} catch (Exception e) {
			System.out.println("Erro ao buscar lojas: " + e.getMessage());
			return null;
		}
	}

	public ResultSet buscarLojaPorId(int idLoja) {
		try {
			String sql = "SELECT * FROM cad_loja WHERE id_loja = ?";
			PreparedStatement stmt = conexão.prepareStatement(sql);
			stmt.setInt(1, idLoja);
			return stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean atualizarLoja(int idLoja, String nome, String cnpj, String responsavel, String telefone,
			 String email, String endereco, String sala, String tipo,
			 String aluguel, String status, String nivel, String senha) {

if (this.conexão == null) {
JOptionPane.showMessageDialog(
null,
"Não existe conexão ativa com o banco de dados.",
"Erro de Conexão",
JOptionPane.ERROR_MESSAGE
);
return false;
}

String aluguelTratado = aluguel.replace(",", ".").trim();

String query = "UPDATE cad_loja SET "
+ "nome_loja = ?, "
+ "cnpj_loja = ?, "
+ "responsavel_loja = ?, "
+ "telefone_loja = ?, "
+ "email_loja = ?, "
+ "endereco_loja = ?, "
+ "sala_loja = ?, "
+ "tipo_loja = ?, "
+ "aluguel_loja = ?, "
+ "status_loja = ?, "
+ "nivel_loja = ?, "
+ "senha = ? "
+ "WHERE id_loja = ?";

try (PreparedStatement stmt = this.conexão.prepareStatement(query)) {

stmt.setString(1, nome);
stmt.setString(2, cnpj);
stmt.setString(3, responsavel);
stmt.setString(4, telefone);
stmt.setString(5, email);
stmt.setString(6, endereco);
stmt.setString(7, sala);
stmt.setString(8, tipo);
stmt.setBigDecimal(9, new java.math.BigDecimal(aluguelTratado));
stmt.setString(10, status);
stmt.setString(11, nivel);
stmt.setString(12, senha);
stmt.setInt(13, idLoja);

int linhasAfetadas = stmt.executeUpdate();

return linhasAfetadas > 0;

} catch (SQLIntegrityConstraintViolationException e) {

JOptionPane.showMessageDialog(
null,
"Já existe outra loja cadastrada com este CNPJ, e-mail ou sala.",
"Cadastro Duplicado",
JOptionPane.WARNING_MESSAGE
);

return false;

} catch (NumberFormatException e) {

JOptionPane.showMessageDialog(
null,
"Valor do aluguel inválido.",
"Aviso",
JOptionPane.ERROR_MESSAGE
);

return false;

} catch (Exception e) {

JOptionPane.showMessageDialog(
null,
"Erro ao atualizar loja no banco: " + e.getMessage(),
"Aviso",
JOptionPane.ERROR_MESSAGE
);

return false;
}
}

	public boolean excluirLoja(int idLoja) {
		String query = "DELETE FROM cad_loja WHERE id_loja = ?";
		try (PreparedStatement stmt = this.conexão.prepareStatement(query)) {
			stmt.setInt(1, idLoja);
			int linhasAfetadas = stmt.executeUpdate();
			return linhasAfetadas > 0;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir loja: " + e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	public boolean inserirAdmin(String nome, String cpf, String email, String senha) {
		String query = "INSERT INTO cad_admin (nome_admin, cpf_admin, email_admin, senha_admin) VALUES (?, ?, ?, ?)";

		if (this.conexão == null) {
			JOptionPane.showMessageDialog(
				null,
				"Não existe conexão ativa com o banco de dados.",
				"Erro de Conexão",
				JOptionPane.ERROR_MESSAGE
			);
			return false;
		}

		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {

			pstmt.setString(1, nome);
			pstmt.setString(2, cpf);
			pstmt.setString(3, email);
			pstmt.setString(4, senha);

			int linhasAfetadas = pstmt.executeUpdate();

			return linhasAfetadas > 0;

		} catch (SQLIntegrityConstraintViolationException e) {

			JOptionPane.showMessageDialog(
				null,
				"Já existe um administrador cadastrado com este CPF ou e-mail.",
				"Cadastro Duplicado",
				JOptionPane.WARNING_MESSAGE
			);

			return false;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(
				null,
				"Erro ao cadastrar administrador: " + e.getMessage(),
				"Aviso",
				JOptionPane.ERROR_MESSAGE
			);

			return false;
		}
	}
	public void buscarCNPJ(String cnpj) {
		String query = "SELECT * FROM cad_loja WHERE cnpj_loja = ?";

		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, cnpj);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					JTextField txtSala = new JTextField(rs.getString("sala_loja"));
					JTextField txtNome = new JTextField(rs.getString("nome_loja"));
					JTextField txtCnpj = new JTextField(rs.getString("cnpj_loja"));
					txtCnpj.setEditable(false); // não deixar alterar CNPJ
					JTextField txtResponsavel = new JTextField(rs.getString("responsavel_loja"));
					JTextField txtTipo = new JTextField(rs.getString("tipo_loja"));
					JTextField txtTelefone = new JTextField(rs.getString("telefone_loja"));

					JPanel painel = new JPanel(new java.awt.GridLayout(0, 2, 5, 5));

					painel.add(new JLabel("Sala:"));
					painel.add(txtSala);

					painel.add(new JLabel("Nome:"));
					painel.add(txtNome);

					painel.add(new JLabel("CNPJ:"));
					painel.add(txtCnpj);

					painel.add(new JLabel("Responsável:"));
					painel.add(txtResponsavel);

					painel.add(new JLabel("Tipo:"));
					painel.add(txtTipo);
					
					painel.add(new JLabel("Telefone:"));
					painel.add(txtTelefone);

					int opcao = JOptionPane.showConfirmDialog(
						null,
						painel,
						"Editar Loja",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE
					);

					if (opcao == JOptionPane.OK_OPTION) {

						String update = """
							UPDATE cad_loja
							SET sala_loja = ?, nome_loja = ?, responsavel_loja = ?, tipo_loja = ?
							WHERE cnpj_loja = ?
						""";

						try (PreparedStatement psUpdate = this.conexão.prepareStatement(update)) {
							psUpdate.setString(1, txtSala.getText());
							psUpdate.setString(2, txtNome.getText());
							psUpdate.setString(3, txtResponsavel.getText());
							psUpdate.setString(4, txtTipo.getText());
							psUpdate.setString(5, cnpj);

							psUpdate.executeUpdate();

							JOptionPane.showMessageDialog(
								null,
								"Loja atualizada com sucesso!"
							);
						}
					}

				} else {
					JOptionPane.showMessageDialog(
						null,
						"Nenhuma loja encontrada com o CNPJ " + cnpj,
						"Não encontrada",
						JOptionPane.WARNING_MESSAGE
					);
				}
			}

		} catch (Exception e) {
			System.err.println("Erro ao buscar loja: " + e.getMessage());
		}
	}

	public void buscarNumSala(String numSala) {
		String query = "SELECT * FROM cad_loja WHERE sala_loja = ?";

		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, numSala);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					JTextField txtSala = new JTextField(rs.getString("sala_loja"));
					JTextField txtNome = new JTextField(rs.getString("nome_loja"));
					JTextField txtCnpj = new JTextField(rs.getString("cnpj_loja"));
					txtCnpj.setEditable(false);
					JTextField txtResponsavel = new JTextField(rs.getString("responsavel_loja"));
					JTextField txtTipo = new JTextField(rs.getString("tipo_loja"));
					JTextField txtTelefone = new JTextField(rs.getString("telefone_loja"));

					JPanel painel = new JPanel(new java.awt.GridLayout(0, 2, 5, 5));

					painel.add(new JLabel("Sala:"));
					painel.add(txtSala);

					painel.add(new JLabel("Nome:"));
					painel.add(txtNome);

					painel.add(new JLabel("CNPJ:"));
					painel.add(txtCnpj);

					painel.add(new JLabel("Responsável:"));
					painel.add(txtResponsavel);

					painel.add(new JLabel("Tipo:"));
					painel.add(txtTipo);
					
					painel.add(new JLabel("Telefone:"));
					painel.add(txtTelefone);

					int opcao = JOptionPane.showConfirmDialog(
						null,
						painel,
						"Editar Loja",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE
					);

					if (opcao == JOptionPane.OK_OPTION) {

						String update = """
							UPDATE cad_loja
							SET sala_loja = ?, nome_loja = ?, responsavel_loja = ?, tipo_loja = ?
							WHERE cnpj_loja = ?
						""";

						try (PreparedStatement psUpdate = this.conexão.prepareStatement(update)) {
							psUpdate.setString(1, txtSala.getText());
							psUpdate.setString(2, txtNome.getText());
							psUpdate.setString(3, txtResponsavel.getText());
							psUpdate.setString(4, txtTipo.getText());
							psUpdate.setString(5, numSala);

							psUpdate.executeUpdate();

							JOptionPane.showMessageDialog(
								null,
								"Loja atualizada com sucesso!"
							);
						}
					}

				} else {
					JOptionPane.showMessageDialog(
						null,
						"Nenhuma loja encontrada com o numero da sala " + numSala,
						"Não encontrada",
						JOptionPane.WARNING_MESSAGE
					);
				}
			}

		} catch (Exception e) {
			System.err.println("Erro ao buscar loja: " + e.getMessage());
		}
	}

	public void buscarEmail(String emailAdmin) {
		String query = "SELECT * FROM cad_loja WHERE email_loja = ?";

		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, emailAdmin);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					JTextField txtSala = new JTextField(rs.getString("sala_loja"));
					JTextField txtNome = new JTextField(rs.getString("nome_loja"));
					JTextField txtCnpj = new JTextField(rs.getString("cnpj_loja"));
					txtCnpj.setEditable(false);
					JTextField txtResponsavel = new JTextField(rs.getString("responsavel_loja"));
					JTextField txtTipo = new JTextField(rs.getString("tipo_loja"));
					JTextField txtTelefone = new JTextField(rs.getString("telefone_loja"));

					JPanel painel = new JPanel(new java.awt.GridLayout(0, 2, 5, 5));

					painel.add(new JLabel("Sala:"));
					painel.add(txtSala);

					painel.add(new JLabel("Nome:"));
					painel.add(txtNome);

					painel.add(new JLabel("CNPJ:"));
					painel.add(txtCnpj);

					painel.add(new JLabel("Responsável:"));
					painel.add(txtResponsavel);

					painel.add(new JLabel("Tipo:"));
					painel.add(txtTipo);
					
					painel.add(new JLabel("Telefone:"));
					painel.add(txtTelefone);

					int opcao = JOptionPane.showConfirmDialog(
						null,
						painel,
						"Editar Loja",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE
					);

					if (opcao == JOptionPane.OK_OPTION) {

						String update = """
							UPDATE cad_loja
							SET sala_loja = ?, nome_loja = ?, responsavel_loja = ?, tipo_loja = ?
							WHERE cnpj_loja = ?
						""";

						try (PreparedStatement psUpdate = this.conexão.prepareStatement(update)) {
							psUpdate.setString(1, txtSala.getText());
							psUpdate.setString(2, txtNome.getText());
							psUpdate.setString(3, txtResponsavel.getText());
							psUpdate.setString(4, txtTipo.getText());
							psUpdate.setString(5, emailAdmin);

							psUpdate.executeUpdate();

							JOptionPane.showMessageDialog(
								null,
								"Loja atualizada com sucesso!"
							);
						}
					}

				} else {
					JOptionPane.showMessageDialog(
						null,
						"Nenhuma loja encontrada com o email " + emailAdmin,
						"Não encontrada",
						JOptionPane.WARNING_MESSAGE
					);
				}
			}

		} catch (Exception e) {
			System.err.println("Erro ao buscar loja: " + e.getMessage());
		}
	}
	public java.util.Map<String, String> buscarLojaParaEdicao(String tipoBusca, String valor) {

		String coluna;

		switch (tipoBusca) {
			case "cnpj":
				coluna = "cnpj_loja";
				break;

			case "sala":
				coluna = "sala_loja";
				break;

			case "email":
				coluna = "email_loja";
				break;

			default:
				JOptionPane.showMessageDialog(
					null,
					"Tipo de busca inválido.",
					"Aviso",
					JOptionPane.ERROR_MESSAGE
				);
				return null;
		}

		String sql = "SELECT id_loja, nome_loja, cnpj_loja, responsavel_loja, telefone_loja, "
				+ "email_loja, endereco_loja, sala_loja, tipo_loja, aluguel_loja, "
				+ "status_loja, nivel_loja, senha "
				+ "FROM cad_loja WHERE " + coluna + " = ?";

		try (PreparedStatement stmt = this.conexão.prepareStatement(sql)) {

			stmt.setString(1, valor);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {

					java.util.Map<String, String> dados = new java.util.HashMap<>();

					dados.put("id_loja", rs.getString("id_loja"));
					dados.put("nome_loja", rs.getString("nome_loja"));
					dados.put("cnpj_loja", rs.getString("cnpj_loja"));
					dados.put("responsavel_loja", rs.getString("responsavel_loja"));
					dados.put("telefone_loja", rs.getString("telefone_loja"));
					dados.put("email_loja", rs.getString("email_loja"));
					dados.put("endereco_loja", rs.getString("endereco_loja"));
					dados.put("sala_loja", rs.getString("sala_loja"));
					dados.put("tipo_loja", rs.getString("tipo_loja"));
					dados.put("aluguel_loja", rs.getBigDecimal("aluguel_loja").toString());
					dados.put("status_loja", rs.getString("status_loja"));
					dados.put("nivel_loja", rs.getString("nivel_loja"));
					dados.put("senha", rs.getString("senha") == null ? "" : rs.getString("senha"));

					return dados;
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				"Erro ao buscar loja para edição: " + e.getMessage(),
				"Aviso",
				JOptionPane.ERROR_MESSAGE
			);
		}

		return null;
	}
	public boolean excluirLojaPorPesquisa(String pesquisa) {

		if (this.conexão == null) {
			JOptionPane.showMessageDialog(
				null,
				"Não existe conexão ativa com o banco de dados.",
				"Erro de Conexão",
				JOptionPane.ERROR_MESSAGE
			);
			return false;
		}

		if (pesquisa == null || pesquisa.trim().isEmpty()) {
			JOptionPane.showMessageDialog(
				null,
				"Digite o CNPJ, e-mail ou número da sala.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return false;
		}

		pesquisa = pesquisa.trim();
		String pesquisaNumerica = pesquisa.replaceAll("[^0-9]", "");

		String sqlBuscar = 
			"SELECT id_loja, nome_loja, sala_loja, cnpj_loja, email_loja " +
			"FROM cad_loja " +
			"WHERE cnpj_loja = ? " +
			"OR REPLACE(REPLACE(REPLACE(cnpj_loja, '.', ''), '/', ''), '-', '') = ? " +
			"OR email_loja = ? " +
			"OR sala_loja = ? " +
			"LIMIT 1";

		try {
			this.conexão.setAutoCommit(false);

			int idLoja = -1;
			String nomeLoja = "";

			try (PreparedStatement stmtBuscar = this.conexão.prepareStatement(sqlBuscar)) {

				stmtBuscar.setString(1, pesquisa);
				stmtBuscar.setString(2, pesquisaNumerica);
				stmtBuscar.setString(3, pesquisa);
				stmtBuscar.setString(4, pesquisa);

				try (ResultSet rs = stmtBuscar.executeQuery()) {
					if (rs.next()) {
						idLoja = rs.getInt("id_loja");
						nomeLoja = rs.getString("nome_loja");
					} else {
						this.conexão.rollback();
						this.conexão.setAutoCommit(true);

						JOptionPane.showMessageDialog(
							null,
							"Nenhuma loja encontrada com esse CNPJ, e-mail ou sala.",
							"Não encontrada",
							JOptionPane.WARNING_MESSAGE
						);

						return false;
					}
				}
			}

			int confirmar = JOptionPane.showConfirmDialog(
				null,
				"Tem certeza que deseja apagar a loja?\n\n" +
				"Loja: " + nomeLoja + "\n" +
				"ID: " + idLoja + "\n\n" +
				"Essa ação também apagará as mensalidades vinculadas.",
				"Confirmar exclusão",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);

			if (confirmar != JOptionPane.YES_OPTION) {
				this.conexão.rollback();
				this.conexão.setAutoCommit(true);
				return false;
			}

			String sqlExcluirMensalidades = "DELETE FROM mensalidade WHERE id_loja = ?";
			try (PreparedStatement stmtMensalidades = this.conexão.prepareStatement(sqlExcluirMensalidades)) {
				stmtMensalidades.setInt(1, idLoja);
				stmtMensalidades.executeUpdate();
			}

			String sqlExcluirLoja = "DELETE FROM cad_loja WHERE id_loja = ?";
			try (PreparedStatement stmtExcluir = this.conexão.prepareStatement(sqlExcluirLoja)) {
				stmtExcluir.setInt(1, idLoja);

				int linhasAfetadas = stmtExcluir.executeUpdate();

				if (linhasAfetadas > 0) {
					this.conexão.commit();
					this.conexão.setAutoCommit(true);
					return true;
				} else {
					this.conexão.rollback();
					this.conexão.setAutoCommit(true);
					return false;
				}
			}

		} catch (Exception e) {
			try {
				if (this.conexão != null) {
					this.conexão.rollback();
					this.conexão.setAutoCommit(true);
				}
			} catch (Exception erroRollback) {
				erroRollback.printStackTrace();
			}

			JOptionPane.showMessageDialog(
				null,
				"Erro ao apagar loja: " + e.getMessage(),
				"Aviso",
				JOptionPane.ERROR_MESSAGE
			);

			return false;
		}
	}
	
}