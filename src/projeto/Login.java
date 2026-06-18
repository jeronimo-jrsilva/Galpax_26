package projeto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	private JFrame telalogin;
	private JTextField login_txt;
	private JPasswordField senha_txt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login().visivel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() { 
	}
	
	public void visivel() {
		initialize();
	}

	private void initialize() {
		telalogin = new JFrame();
		telalogin.setBounds(0, 0, 1920, 1080);
		telalogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    telalogin.getContentPane().setLayout(null);
		
        GerenciadorJanelas.registrarInstancia(this);

		// INICIALIZA O GERENCIADOR DE TECLADO UNIVERSAL
        GerenciadorTeclado.getInstance().inicializar(telalogin);

		login_txt = new JTextField();
		login_txt.setForeground(new Color(255, 255, 255));
		login_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		login_txt.setBounds(915, 275, 772, 77); 
		login_txt.setOpaque(false);      
		login_txt.setBorder(null);
		telalogin.getContentPane().add(login_txt);
		
		// REGISTRA O CAMPO NO GERENCIADOR
		GerenciadorTeclado.getInstance().registrarCampo(login_txt);
		
		senha_txt = new JPasswordField();
		senha_txt.setForeground(new Color(255, 255, 255));
		senha_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		senha_txt.setBounds(915, 446, 772, 77);
		senha_txt.setOpaque(false);      
		senha_txt.setBorder(null);
		telalogin.getContentPane().add(senha_txt);

		// REGISTRA O CAMPO NO GERENCIADOR
		GerenciadorTeclado.getInstance().registrarCampo(senha_txt);
		
		JButton btnEntrar = new JButton("");
		btnEntrar.setIcon(new ImageIcon(Login.class.getResource("/imagens/img_login/img_login_btn_entrar.png")));
		btnEntrar.setBounds(822, 551, 905, 71);
		btnEntrar.setOpaque(false);
		btnEntrar.setBorder(null);
		btnEntrar.setContentAreaFilled(false);
		telalogin.getContentPane().add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String login = login_txt.getText();
		        String senha = new String(senha_txt.getPassword());
		        if (!login.isEmpty() && !senha.isEmpty()) {
		            Bancodedados bd = new Bancodedados();
		            bd.conectar();
		            if (bd.verificar()) {
		                String permissao = bd.realizarLogin(login, senha);
		                if (permissao != null) {
		                    if (permissao.equalsIgnoreCase("admin")) {
		                        new telaAdmin().visivel();
		                        telalogin.dispose();
		                    } else {
		                        bd.desconectar();
		                        new telaComum(login).visivel();
		                        telalogin.dispose();
		                    }
		                } else {
		                    bd.desconectar();
		                    JOptionPane.showMessageDialog(null, "Usuário ou Senha incorretos!");
		                }
		            }
		        }
		    }
		});
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(Login.class.getResource("/imagens/img_login/img_login_fundo.png")));
		lblFundo.setBounds(0, 0, 1920, 1080);
		telalogin.getContentPane().add(lblFundo);

        GerenciadorJanelas.configurarJanela(telalogin);
        telalogin.setVisible(true);
	}
}