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


public class Bancodedados {
	
	public Connection conexão = null;
	public Statement consultas = null;
	public ResultSet resultado = null;
	
	Scanner numero = new Scanner(System.in);
	Scanner texto = new Scanner(System.in);
	
	public void conectar() {
		java.util.Properties props = new java.util.Properties();
		// Tenta carregar da raiz do projeto (Diretório de execução do Eclipse)
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
				this.conexão = DriverManager.getConnection("jdbc:mysql://localhost:3306/galpax", "root", "");
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
			this.conexão.close();
		} catch(Exception e) {
			System.out.println("Erro");
		}
	}
	
	
	public String realizarLogin(String email, String senha) {
		// 1º PASSO: Tenta buscar na tabela de LOJAS (Clientes)
		String queryLoja = "SELECT nivel_loja FROM cad_loja WHERE email_loja = ? AND senha = ?";
		
		try (PreparedStatement pstmtLoja = this.conexão.prepareStatement(queryLoja)) {
			pstmtLoja.setString(1, email);
			pstmtLoja.setString(2, senha);
			
			try (ResultSet rsLoja = pstmtLoja.executeQuery()) {
				if (rsLoja.next()) {
					// Se achou na tabela de lojas, retorna o nível cadastrado na loja (ex: 'cliente')
					return rsLoja.getString("nivel_loja"); 
				}
			}
		} catch (Exception e) {
			System.err.println("Erro ao autenticar na tabela cad_loja: " + e.getMessage());
		}
		
		// 2º PASSO: Se não achou na cad_loja, tenta buscar na tabela de ADMINS
		String queryAdmin = "SELECT email_admin FROM cad_admin WHERE email_admin = ? AND senha_admin = ?";
		
		try (PreparedStatement pstmtAdmin = this.conexão.prepareStatement(queryAdmin)) {
			pstmtAdmin.setString(1, email);
			pstmtAdmin.setString(2, senha);
			
			try (ResultSet rsAdmin = pstmtAdmin.executeQuery()) {
				if (rsAdmin.next()) {
					// Se achou na tabela de admins, retorna a String fixa "admin"
					return "admin"; 
				}
			}
		} catch (Exception e) {
			System.err.println("Erro ao autenticar na tabela cad_admin: " + e.getMessage());
		}
		
		// Se passou pelas duas tabelas e não encontrou nada, retorna null (usuário/senha inválidos)
		return null; 
	}

	public void inseriVeiculo(String placa, String modelo, String CNH) {
	   
		String query = "INSERT INTO cad_carro (placa_carro, modelo_carro, cnh_carro) VALUES (?, ?, ?)";
		
		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, placa);
			pstmt.setString(2, modelo);
			pstmt.setString(3, CNH);
			
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void inserirLojaCompleta(String nome, String cnpj, String responsavel, String telefone, 
            String email, String endereco, String sala, String tipo, 
            String aluguel, String status, String nivel,String senha) {

		String query = "INSERT INTO cad_loja (nome_loja, cnpj_loja, responsavel_loja, telefone_loja, "
		+ "email_loja, endereco_loja, sala_loja, tipo_loja, aluguel_loja, status_loja, nivel_loja,senha) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		
		try {
		java.sql.PreparedStatement stmt = this.conexão.prepareStatement(query);
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
		stmt.setString(11, nivel);
		stmt.setString(12, senha);
		
		stmt.executeUpdate();
		stmt.close();
		} catch(Exception e) {
		JOptionPane.showMessageDialog(null, "Erro ao cadastrar loja no banco: " + e.getMessage(), "Aviso", -1);
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
			String query = "SELECT * FROM cad_carro WHERE placa_carro = '" + placa + "'";
			this.resultado = this.consultas.executeQuery(query);
			return this.resultado;
		} catch (Exception e) {
			System.out.println("Erro ao buscar veículo: " + e.getMessage());
			return null;
		}
	}
	
	public List<Mensalidade> listarMensalidades() {

		List<Mensalidade> lista = new ArrayList<>();

		try {

			String query = "SELECT\r\n"
					+ "    m.*,\r\n"
					+ "    l.nome_loja\r\n"
					+ "FROM mensalidade m\r\n"
					+ "INNER JOIN cad_loja l\r\n"
					+ "    ON m.id_loja = l.id_loja;";

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

	// PAGAMENTO FICTÍCIO
	public void pagarMensalidade(int idMensalidade) {

		try {

			String query = "UPDATE mensalidade " +
					"SET status='Pago', " +
					"data_pagamento=CURDATE() " +
					"WHERE id_mensalidade=" + idMensalidade;

			this.consultas.executeUpdate(query);
			System.out.println("Pagamento realizado com sucesso!");

		} catch(Exception e) {
			e.printStackTrace();
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
	
	public void inserirAdmin(String nome, String cpf, String email, String senha) {
	    String query = "INSERT INTO cad_admin (nome_admin, cpf_admin, email_admin, senha_admin) VALUES (?, ?, ?, ?)";
	    
	    try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
	        pstmt.setString(1, nome);
	        pstmt.setString(2, cpf);
	        pstmt.setString(3, email);
	        pstmt.setString(4, senha);
	        
	        pstmt.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Administrador cadastrado com sucesso!");
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Erro ao cadastrar administrador: " + e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
	    }
	}
}