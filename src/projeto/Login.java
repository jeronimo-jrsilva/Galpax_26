package projeto;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

	private static final String PLACEHOLDER_EMAIL = "Digite seu email";
	private static final String PLACEHOLDER_SENHA = "Digite sua senha";

	private static final Color COR_PLACEHOLDER = new Color(180, 180, 180);
	private static final Color COR_TEXTO = new Color(255, 255, 255);

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
		login_txt.setForeground(COR_TEXTO);
		login_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		login_txt.setBounds(915, 275, 772, 77); 
		login_txt.setOpaque(false);      
		login_txt.setBorder(null);
		telalogin.getContentPane().add(login_txt);

		aplicarPlaceholderEmail();
		
		GerenciadorTeclado.getInstance().registrarCampo(login_txt);
		
		senha_txt = new JPasswordField();
		senha_txt.setForeground(COR_TEXTO);
		senha_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		senha_txt.setBounds(915, 446, 700, 77);
		senha_txt.setOpaque(false);      
		senha_txt.setBorder(null);
		telalogin.getContentPane().add(senha_txt);

		GerenciadorTeclado.getInstance().registrarCampo(senha_txt);

		char caracterePadraoSenha = senha_txt.getEchoChar();

		aplicarPlaceholderSenha(caracterePadraoSenha);

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

				if (senhaEstaComPlaceholder()) {
					senha_txt.requestFocusInWindow();
					return;
				}

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
		        String senha = new String(senha_txt.getPassword());

		        if (login.equals(PLACEHOLDER_EMAIL)) {
		        	login = "";
		        }

		        if (senha.equals(PLACEHOLDER_SENHA)) {
		        	senha = "";
		        }

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
		        } else {
		        	JOptionPane.showMessageDialog(null, "Preencha o email e a senha!");
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

	private void aplicarPlaceholderEmail() {
		login_txt.setText(PLACEHOLDER_EMAIL);
		login_txt.setForeground(COR_PLACEHOLDER);

		login_txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (login_txt.getText().equals(PLACEHOLDER_EMAIL)) {
					login_txt.setText("");
					login_txt.setForeground(COR_TEXTO);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (login_txt.getText().trim().isEmpty()) {
					login_txt.setText(PLACEHOLDER_EMAIL);
					login_txt.setForeground(COR_PLACEHOLDER);
				}
			}
		});
	}

	private void aplicarPlaceholderSenha(char caracterePadraoSenha) {
		senha_txt.setEchoChar((char) 0);
		senha_txt.setText(PLACEHOLDER_SENHA);
		senha_txt.setForeground(COR_PLACEHOLDER);

		senha_txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (senhaEstaComPlaceholder()) {
					senha_txt.setText("");
					senha_txt.setForeground(COR_TEXTO);
					senha_txt.setEchoChar(caracterePadraoSenha);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String senha = new String(senha_txt.getPassword());

				if (senha.isEmpty()) {
					senha_txt.setEchoChar((char) 0);
					senha_txt.setText(PLACEHOLDER_SENHA);
					senha_txt.setForeground(COR_PLACEHOLDER);
				}
			}
		});
	}

	private boolean senhaEstaComPlaceholder() {
		return new String(senha_txt.getPassword()).equals(PLACEHOLDER_SENHA);
	}
}