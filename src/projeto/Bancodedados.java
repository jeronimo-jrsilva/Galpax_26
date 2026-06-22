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
import javax.swing.JOptionPane;
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
								 String aluguel, String status, String senha) {
		String query = "UPDATE cad_loja SET nome_loja = ?, cnpj_loja = ?, responsavel_loja = ?, telefone_loja = ?, "
				+ "email_loja = ?, endereco_loja = ?, sala_loja = ?, tipo_loja = ?, aluguel_loja = ?, status_loja = ?, senha = ? "
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
			stmt.setString(9, aluguel);
			stmt.setString(10, status);
			stmt.setString(11, senha);
			stmt.setInt(12, idLoja);
			
			int linhasAfetadas = stmt.executeUpdate();
			return linhasAfetadas > 0;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar loja no banco: " + e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
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
	
}