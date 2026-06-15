package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class Bancodedados {
	
	public Connection conexão = null;
	public Statement consultas = null;
	public ResultSet resultado = null;
    
    // MODO MOCK (OFFLINE)
    public boolean isMock = false;
    private int mockIndex = -1;
	
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
            this.isMock = false;
			System.out.println("✅ Conectado com sucesso ao MySQL via secrets.properties");
		} catch (Exception e) {
			System.err.println("⚠️ Aviso: secrets.properties não encontrado. Usando fallback localhost.");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.conexão = DriverManager.getConnection("jdbc:mysql://localhost:3306/galpax", "root", "");
				this.consultas = this.conexão.createStatement();
                this.isMock = false;
				System.out.println("✅ Conectado via Fallback (Localhost/Root)");
			} catch (Exception e2) {
				System.err.println("🚀 MODO MOCK ATIVADO: Banco de dados offline.");
                this.isMock = true;
			}
		}
	}
	
	public boolean verificar() {
		return (this.isMock || this.conexão != null);
	}
	
	public void desconectar() {
		try {
			if (this.conexão != null) this.conexão.close();
		} catch(Exception e) { }
	}
	
	public String realizarLogin(String email, String senha) {
        if (isMock) {
            if (email.equals("admin")) return "admin";
            return "cliente";
        }

		// 1º PASSO: Tenta buscar na tabela de LOJAS (Clientes)
		String queryLoja = "SELECT nivel_loja FROM cad_loja WHERE email_loja = ? AND senha = ?";
		
		try (PreparedStatement pstmtLoja = this.conexão.prepareStatement(queryLoja)) {
			pstmtLoja.setString(1, email);
			pstmtLoja.setString(2, senha);
			
			try (ResultSet rsLoja = pstmtLoja.executeQuery()) {
				if (rsLoja.next()) {
					return rsLoja.getString("nivel_loja"); 
				}
			}
		} catch (Exception e) { }
		
		// 2º PASSO: Tenta buscar na tabela de ADMINS
		String queryAdmin = "SELECT email_admin FROM cad_admin WHERE email_admin = ? AND senha_admin = ?";
		
		try (PreparedStatement pstmtAdmin = this.conexão.prepareStatement(queryAdmin)) {
			pstmtAdmin.setString(1, email);
			pstmtAdmin.setString(2, senha);
			
			try (ResultSet rsAdmin = pstmtAdmin.executeQuery()) {
				if (rsAdmin.next()) {
					return "admin"; 
				}
			}
		} catch (Exception e) { }
		
		return null; 
	}

    // --- SISTEMA DE MOCK (RESULTSET DINÂMICO) ---
    private ResultSet createMockResultSet(List<Map<String, Object>> data) {
        mockIndex = -1;
        return (ResultSet) Proxy.newProxyInstance(
            ResultSet.class.getClassLoader(),
            new Class[] { ResultSet.class },
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    String methodName = method.getName();
                    if (methodName.equals("next")) {
                        mockIndex++;
                        return mockIndex < data.size();
                    }
                    if (methodName.equals("getString")) {
                        String col = (String) args[0];
                        Object val = data.get(mockIndex).get(col);
                        return (val == null) ? "" : String.valueOf(val);
                    }
                    if (methodName.equals("getInt")) {
                        String col = (String) args[0];
                        Object val = data.get(mockIndex).get(col);
                        return (val instanceof Integer) ? (Integer) val : 0;
                    }
                    if (methodName.equals("getDouble")) {
                        String col = (String) args[0];
                        Object val = data.get(mockIndex).get(col);
                        return (val instanceof Double) ? (Double) val : 0.0;
                    }
                    if (methodName.equals("close")) return null;
                    return null;
                }
            }
        );
    }

	public void inseriVeiculo(String placa, String modelo, String CNH) {
        if (isMock) {
            JOptionPane.showMessageDialog(null, "[MOCK] Veículo " + placa + " cadastrado com sucesso!");
            return;
        }
		String query = "INSERT INTO cad_carro (placa_carro, modelo_carro, cnh_carro) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, placa);
			pstmt.setString(2, modelo);
			pstmt.setString(3, CNH);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
		} catch (Exception e) { }
	}
	
	public void inserirLojaCompleta(String nome, String cnpj, String responsavel, String telefone, 
            String email, String endereco, String sala, String tipo, 
            String aluguel, String status, String nivel,String senha) {
        if (isMock) {
            JOptionPane.showMessageDialog(null, "[MOCK] Loja " + nome + " cadastrada com sucesso!");
            return;
        }
		String query = "INSERT INTO cad_loja (nome_loja, cnpj_loja, responsavel_loja, telefone_loja, "
		+ "email_loja, endereco_loja, sala_loja, tipo_loja, aluguel_loja, status_loja, nivel_loja,senha) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		try (java.sql.PreparedStatement stmt = this.conexão.prepareStatement(query)) {
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
		} catch(Exception e) { }
	}
	
	public ResultSet listarVeiculos() {
        if (isMock) {
            List<Map<String, Object>> data = new ArrayList<>();
            Map<String, Object> v1 = new HashMap<>();
            v1.put("placa_carro", "ABC-1234"); v1.put("modelo_carro", "Fiat Uno"); v1.put("cnh_carro", "123456");
            data.add(v1);
            return createMockResultSet(data);
        }
		try {
			String query = "select * from cad_carro";
			this.resultado = this.consultas.executeQuery(query);
			return this.resultado;
		} catch(Exception e){ return null; }
	}

	public ResultSet buscarVeiculo(String placa) {
        if (isMock) {
            List<Map<String, Object>> data = new ArrayList<>();
            Map<String, Object> v1 = new HashMap<>();
            v1.put("placa_carro", placa); v1.put("modelo_carro", "Fusca Mock"); v1.put("cnh_carro", "99999");
            data.add(v1);
            return createMockResultSet(data);
        }
		try {
			String query = "SELECT * FROM cad_carro WHERE placa_carro = '" + placa + "'";
			this.resultado = this.consultas.executeQuery(query);
			return this.resultado;
		} catch (Exception e) { return null; }
	}
	
	public List<Mensalidade> listarMensalidades() {
		List<Mensalidade> lista = new ArrayList<>();
        if (isMock) {
            Mensalidade m = new Mensalidade();
            m.set_nome_loja("Loja Exemplo (Mock)"); m.set_mensalidade(1500.0); m.set_status("Pendente");
            lista.add(m);
            return lista;
        }
		try {
			String query = "SELECT m.*, l.nome_loja FROM mensalidade m INNER JOIN cad_loja l ON m.id_loja = l.id_loja;";
			this.resultado = this.consultas.executeQuery(query);
			while(this.resultado.next()) {
				Mensalidade m = new Mensalidade();
				m.set_nome_loja(this.resultado.getString("nome_loja"));
				m.set_mensalidade(this.resultado.getDouble("mensalidade"));
				m.set_status(this.resultado.getString("status"));
				lista.add(m);
			}
		} catch(Exception e) { }
		return lista;
	}

	public void pagarMensalidade(int idMensalidade) {
        if (isMock) { JOptionPane.showMessageDialog(null, "[MOCK] Mensalidade paga!"); return; }
		try {
			String query = "UPDATE mensalidade SET status='Pago', data_pagamento=CURDATE() WHERE id_mensalidade=" + idMensalidade;
			this.consultas.executeUpdate(query);
		} catch(Exception e) { }
	}

	public List<Mensalidade> historicoMensalidades() {
		List<Mensalidade> lista = new ArrayList<>();
        if (isMock) return lista;
		try {
			String query = "SELECT * FROM mensalidade WHERE status LIKE 'Pago%'";
			this.resultado = this.consultas.executeQuery(query);
			while(this.resultado.next()) {
				Mensalidade m = new Mensalidade();
				m.set_mensalidade(this.resultado.getDouble("mensalidade"));
				m.set_status(this.resultado.getString("status"));
				lista.add(m);
			}
		} catch(Exception e) { }
		return lista;
	}
	
	public void verificarCliente(int sala) {
        if (isMock) { JOptionPane.showMessageDialog(null, "[MOCK] Loja da Sala " + sala); return; }
		String query = "SELECT * FROM cad_loja WHERE sala_loja = ?";
		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, String.valueOf(sala));
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Loja: " + rs.getString("nome_loja"));
				}
			}
		} catch (Exception e) { }
	}

	public boolean isGalpaoOcupado(int sala) {
        if (isMock) return (sala == 101); // Simula que a 101 está ocupada
		String query = "SELECT 1 FROM cad_loja WHERE sala_loja = ?";
		try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
			pstmt.setString(1, String.valueOf(sala));
			try (ResultSet rs = pstmt.executeQuery()) { return rs.next(); }
		} catch (Exception e) { return false; }
	}
	
	public void popularComboSalasLivres(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
        if (isMock) { comboBox.addItem("102"); comboBox.addItem("103"); return; }
		try {
			for (int i = 101; i <= 105; i++) comboBox.addItem(String.valueOf(i));
		} catch (Exception e) { }
	}
	
	public void inserirAdmin(String nome, String cpf, String email, String senha) {
        if (isMock) { JOptionPane.showMessageDialog(null, "[MOCK] Admin " + nome + " criado!"); return; }
	    String query = "INSERT INTO cad_admin (nome_admin, cpf_admin, email_admin, senha_admin) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement pstmt = this.conexão.prepareStatement(query)) {
	        pstmt.setString(1, nome); pstmt.setString(2, cpf); pstmt.setString(3, email); pstmt.setString(4, senha);
	        pstmt.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Administrador cadastrado com sucesso!");
	    } catch (Exception e) { }
	}
}