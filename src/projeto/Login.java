package projeto;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        GerenciadorTeclado.getInstance().inicializar(telalogin);
		
		login_txt = new JTextField();
		login_txt.setForeground(new Color(255, 255, 255));
		login_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		login_txt.setBounds(915, 275, 772, 77); 
		login_txt.setOpaque(false);      
		login_txt.setBorder(null);
		telalogin.getContentPane().add(login_txt);
		
		GerenciadorTeclado.getInstance().registrarCampo(login_txt);
		
		senha_txt = new JPasswordField();
		senha_txt.setForeground(new Color(255, 255, 255));
		senha_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		senha_txt.setBounds(915, 446, 700, 77);
		senha_txt.setOpaque(false);      
		senha_txt.setBorder(null);
		telalogin.getContentPane().add(senha_txt);

		GerenciadorTeclado.getInstance().registrarCampo(senha_txt);

		
		char caracterePadraoSenha = senha_txt.getEchoChar();

		

		
		ImageIcon iconOlhoAbertoOriginal = new ImageIcon(Login.class.getResource("/imagens/img_login/olhoaberto.png"));
		Image imgOlhoAberto = iconOlhoAbertoOriginal.getImage();
		Image imgOlhoAbertoRedimensionado = imgOlhoAberto.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconOlhoAberto = new ImageIcon(imgOlhoAbertoRedimensionado);

		
		ImageIcon iconOlhoFechadoOriginal = new ImageIcon(Login.class.getResource("/imagens/img_login/olhofechado.png"));
		Image imgOlhoFechado = iconOlhoFechadoOriginal.getImage();
		Image imgOlhoFechadoRedimensionado = imgOlhoFechado.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconOlhoFechado = new ImageIcon(imgOlhoFechadoRedimensionado);

		
		JLabel lblOlhoSenha = new JLabel(iconOlhoAberto);
		lblOlhoSenha.setBounds(1636, 463, 40, 40);
		lblOlhoSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblOlhoSenha.setOpaque(false);
		telalogin.getContentPane().add(lblOlhoSenha);

		final boolean[] senhaVisivel = { false };

		lblOlhoSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (senhaVisivel[0]) {
					
					senha_txt.setEchoChar(caracterePadraoSenha);
					lblOlhoSenha.setIcon(iconOlhoAberto);
					senhaVisivel[0] = false;
				} else {
					
					senha_txt.setEchoChar((char) 0);
					lblOlhoSenha.setIcon(iconOlhoFechado);
					senhaVisivel[0] = true;
				}

				senha_txt.requestFocusInWindow();
			}
		});

		
		
		JButton btnEntrar = new JButton("");
		btnEntrar.setIcon(new ImageIcon(Login.class.getResource("/imagens/img_login/img_login_btn_entrar.png")));
		btnEntrar.setBounds(822, 551, 905, 71);
		btnEntrar.setOpaque(false);
		btnEntrar.setBorder(null);
		btnEntrar.setContentAreaFilled(false);
		btnEntrar.setFocusPainted(false);
		telalogin.getContentPane().add(btnEntrar);

		btnEntrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String login = login_txt.getText().trim();
		        String senha = new String(senha_txt.getPassword()).trim();

		        if (!login.isEmpty() && !senha.isEmpty()) {
		            Bancodedados bd = new Bancodedados();
		            bd.conectar();

		            if (bd.verificar()) {
		                String permissao = bd.realizarLogin(login, senha);

		                if (permissao != null) {
		                    if (permissao.equalsIgnoreCase("admin")) {
		                    	bd.desconectar();
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

		telalogin.getContentPane().setComponentZOrder(lblOlhoSenha, 0);
		telalogin.repaint();
        GerenciadorJanelas.configurarJanela(telalogin);
        telalogin.setVisible(true);
	}
}