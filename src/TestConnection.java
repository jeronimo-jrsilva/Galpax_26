import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

public class TestConnection {
    public static void main(String[] args) {
        Properties props = new Properties();
        String path = "secrets.properties";
        
        System.out.println("--- INICIANDO TESTE DE CONEXÃO ---");
        
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            System.out.println("Tentando conectar a: " + url);
            System.out.println("Usuário: " + user);
            // Senha não printada por segurança
            
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            
            if (conn != null) {
                System.out.println("\n✅ SUCESSO! Conexão estabelecida com o MySQL local.");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("\n❌ FALHA NA CONEXÃO!");
            System.out.println("Motivo: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("----------------------------------");
    }
}
