package projeto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Login {

	private JFrame telalogin;
	private JTextField login_txt;
	private JPasswordField senha_txt;
	//private JPasswordField confirmarsenha;
	//private JPasswordField campoAtivo;
	
	private JPanel containerTeclado = new JPanel(new BorderLayout()) {{
        setOpaque(false);
    }};

	private boolean shiftAtivo = false;
	private List<JButton> botoesLetras = new ArrayList<>();

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telalogin = new JFrame();
		// TAMANHO FIXO PARA WINDOW BUILDER (16:9)
		telalogin.setBounds(0, 0, 1920, 1080);
		telalogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    telalogin.getContentPane().setLayout(null);
		
        // REGISTRA PARA CTRL+W
        GerenciadorJanelas.registrarInstancia(this);

        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//coisas novas aqui
        containerTeclado.setBounds(0, 745, screenSize.width, 330);
		//containerTeclado.setBounds(360, 700, 1200, 300);
		telalogin.getContentPane().add(containerTeclado);
		
		// CAMPOS DE LOGIN E SENHA
		login_txt = new JTextField();
		login_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		login_txt.setBounds(663, 340, 640, 60); 
		login_txt.setOpaque(false);      
		login_txt.setBorder(null);
		telalogin.getContentPane().add(login_txt);
		
		login_txt.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { mostrarTeclado(criarTecladoCompleto(login_txt)); }
        });
		
		senha_txt = new JPasswordField();
		senha_txt.setFont(new Font("Tahoma", Font.PLAIN, 30));
		senha_txt.setBounds(663, 481, 640, 60);
		senha_txt.setOpaque(false);      
		senha_txt.setBorder(null);
		telalogin.getContentPane().add(senha_txt);
		
		senha_txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//campoAtivo = senha_txt;
				mostrarTeclado(criarTecladoCompleto(senha_txt));
			}
		});
		
//		confirmarsenha.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {				// essa parte nao precisa disso
//				campoAtivo = confirmarsenha;
//				mostrarTeclado(criarTecladoCompleto(confirmarsenha));
//			}
//		});
		
//		senha_txt.addMouseListener(new MouseAdapter() {
//			@Override public void mouseClicked(MouseEvent e) { mostrarTeclado(criarTecladoCompleto(senha_txt)); }
//		});
		
		JButton btnEntrar = new JButton("");
		btnEntrar.setIcon(new ImageIcon(Login.class.getResource("/imagens/_basicSolid small Base (1).png")));
		btnEntrar.setBounds(695, 570, 530, 71);
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
		
		// FUNDO ADICIONADO POR ÚLTIMO (PARA FICAR ATRÁS NO WB)
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(Login.class.getResource("/imagens/telalogin.png")));
		lblFundo.setBounds(0, 0, 1920, 1080);
		telalogin.getContentPane().add(lblFundo);

        // APLICA GERENCIADOR APÓS A MONTAGEM DOS COMPONENTES
        GerenciadorJanelas.configurarJanela(telalogin);
        telalogin.setVisible(true);
	}

//	private void mostrarTeclado(JPanel painelTeclado) {
//		containerTeclado.removeAll();
//		containerTeclado.add(painelTeclado, BorderLayout.CENTER);
//		containerTeclado.revalidate();
//		containerTeclado.repaint();
//	}
	
	private void mostrarTeclado(JPanel painelTeclado) {
		containerTeclado.removeAll();
		containerTeclado.add(painelTeclado, BorderLayout.CENTER);
		containerTeclado.revalidate();
		containerTeclado.repaint();
	}

//	private void inserirTexto(JTextField campo, String texto) {
//		String atual = campo.getText();
//		if (texto.equals("APAGAR")) {
//			if (atual.length() > 0) campo.setText(atual.substring(0, atual.length() - 1));
//		} else if (texto.equals("ESPAÇO")) {
//			campo.setText(atual + " ");
//		} else {
//			campo.setText(atual + texto);
//		}
//	}
	
	private void inserirTexto(JTextField campo, String texto) {
		if (campo == null) return;

		String atual = campo.getText();

		if (texto.equals("APAGAR")) {
			if (atual.length() > 0) {
				campo.setText(atual.substring(0, atual.length() - 1));
			}
		} else if (texto.equals("ESPAÇO")) {
			campo.setText(atual + " ");
		} else {
			campo.setText(atual + texto);
		}
	}

//	private void alternarShift(JButton btnShift) {
//		shiftAtivo = !shiftAtivo;
//		for (JButton btn : botoesLetras) {
//			String letra = btn.getText();
//			btn.setText(shiftAtivo ? letra.toUpperCase() : letra.toLowerCase());
//		}
//	}

	// alter shift ficava aqui :D	
	
	
//	private JPanel criarTecladoCompleto(JTextField campo) {
//		botoesLetras.clear();
//		shiftAtivo = false;
//        JPanel painelPrincipal = new JPanel(new GridLayout(5, 1, 0, 6));
//        painelPrincipal.setOpaque(false);
//        String[][] linhas = {
//            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "@"},
//            {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "#"},
//            {"a", "s", "d", "f", "g", "h", "j", "k", "l", "ç", ";", "\"", "$"},
//            {"SHIFT", "z", "x", "c", "v", "b", "n", "m", ",", ".", "/", "!", "%"},
//            {"&", "*", "ESPAÇO", "APAGAR"}
//        };
//        for (int i = 0; i < linhas.length; i++) {
//            JPanel painelLinha = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
//            painelLinha.setOpaque(false);
//            for (String caractere : linhas[i]) {
//                JButton btn = new JButton(caractere);
//                if (caractere.equals("SHIFT")) {
//                    btn.setPreferredSize(new java.awt.Dimension(110, 50));
//                    btn.addActionListener(e -> alternarShift(btn));
//                } else if (caractere.equals("APAGAR")) {
//                    btn.setPreferredSize(new java.awt.Dimension(120, 50));
//                    btn.addActionListener(e -> inserirTexto(campo, "APAGAR"));
//                } else if (caractere.equals("ESPAÇO")) {
//                    btn.setPreferredSize(new java.awt.Dimension(400, 50));
//                    btn.addActionListener(e -> inserirTexto(campo, "ESPAÇO"));
//                } else {
//                    btn.setPreferredSize(new java.awt.Dimension(55, 50));
//                    btn.addActionListener(e -> inserirTexto(campo, btn.getText()));
//                    if (caractere.matches("[a-zç]")) botoesLetras.add(btn);
//                }
//                painelLinha.add(btn);
//            }
//            painelPrincipal.add(painelLinha);
//        }
//        return painelPrincipal;
//    }
	
	private JPanel criarTecladoCompleto(JTextField campo) {
		botoesLetras.clear();
		shiftAtivo = false;

		JPanel painelPrincipal = new JPanel(new GridLayout(4, 1, 12, 12));
		painelPrincipal.setOpaque(true);
		painelPrincipal.setBackground(new Color(8, 18, 42));
		painelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 22, 22));

		String[][] linhas = {
			{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "⌫"},
			{"a", "s", "d", "f", "g", "h", "j", "k", "l", "@"},
			{"⇧", "z", "x", "c", "v", "b", "n", "m", ",", ".", "-"},
			{"?123", "ESPAÇO", "↵"}
		};

		for (String[] linha : linhas) {
			JPanel painelLinha = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
			painelLinha.setOpaque(false);

			for (String tecla : linha) {
				JButton btn = new BotaoTeclado(tecla);

				if (tecla.equals("ESPAÇO")) {
					btn.setPreferredSize(new Dimension(1020, 72));
				} else if (tecla.equals("↵")) {
					btn.setPreferredSize(new Dimension(210, 72));
					btn.setBackground(new Color(15, 83, 230));
				} else if (tecla.equals("?123")) {
					btn.setPreferredSize(new Dimension(170, 72));
				} else {
					btn.setPreferredSize(new Dimension(130, 72));
				}

				if (tecla.equals("⌫")) {
					btn.addActionListener(e -> inserirTexto(campo, "APAGAR"));
				} else if (tecla.equals("ESPAÇO")) {
					btn.addActionListener(e -> inserirTexto(campo, "ESPAÇO"));
				} else if (tecla.equals("⇧")) {
					btn.addActionListener(e -> alternarShift(btn));
				} else if (tecla.equals("↵")) {
					btn.addActionListener(e -> {
						containerTeclado.removeAll();
						containerTeclado.revalidate();
						containerTeclado.repaint();
					});
				} else if (tecla.equals("?123")) {
					btn.addActionListener(e -> mostrarTeclado(criarTecladoNumerico(campo)));
				} else {
					btn.addActionListener(e -> inserirTexto(campo, btn.getText()));

					if (tecla.matches("[a-z]")) {
						botoesLetras.add(btn);
					}
				}

				painelLinha.add(btn);
			}

			painelPrincipal.add(painelLinha);
		}

		return painelPrincipal;
	}
	
	private void alternarShift(JButton btnShift) {
		shiftAtivo = !shiftAtivo;
		btnShift.setBackground(shiftAtivo ? new Color(45, 65, 105) : new Color(31, 42, 67));

		for (JButton btn : botoesLetras) {
			String letra = btn.getText();
			btn.setText(shiftAtivo ? letra.toUpperCase() : letra.toLowerCase());
		}
	}

	
	private JPanel criarTecladoNumerico(JTextField campo) {
		JPanel painelPrincipal = new JPanel(new GridLayout(4, 1, 12, 12));
		painelPrincipal.setOpaque(true);
		painelPrincipal.setBackground(new Color(8, 18, 42));
		painelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 22, 22));

		String[][] linhas = {
			{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "⌫"},
			{"@", "#", "$", "_", "&", "-", "+", "(", ")", "/"},
			{"*", "\"", "'", ":", ";", "!", "?", ".", ","},
			{"ABC", "ESPAÇO", "↵"}
		};

		for (String[] linha : linhas) {
			JPanel painelLinha = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
			painelLinha.setOpaque(false);

			for (String tecla : linha) {
				JButton btn = new BotaoTeclado(tecla);

				if (tecla.equals("ESPAÇO")) {
					btn.setPreferredSize(new Dimension(1020, 72));
				} else if (tecla.equals("↵")) {
					btn.setPreferredSize(new Dimension(210, 72));
					btn.setBackground(new Color(15, 83, 230));
				} else if (tecla.equals("ABC")) {
					btn.setPreferredSize(new Dimension(170, 72));
				} else {
					btn.setPreferredSize(new Dimension(130, 72));
				}

				if (tecla.equals("⌫")) {
					btn.addActionListener(e -> inserirTexto(campo, "APAGAR"));
				} else if (tecla.equals("ESPAÇO")) {
					btn.addActionListener(e -> inserirTexto(campo, "ESPAÇO"));
				} else if (tecla.equals("↵")) {
					btn.addActionListener(e -> {
						containerTeclado.removeAll();
						containerTeclado.revalidate();
						containerTeclado.repaint();
					});
				} else if (tecla.equals("ABC")) {
					btn.addActionListener(e -> mostrarTeclado(criarTecladoCompleto(campo)));
				} else {
					btn.addActionListener(e -> inserirTexto(campo, btn.getText()));
				}

				painelLinha.add(btn);
			}

			painelPrincipal.add(painelLinha);
		}

		return painelPrincipal;
	}
	
	private class BotaoTeclado extends JButton {

		public BotaoTeclado(String texto) {
			super(texto);
			setFont(new Font("Arial Unicode MS", Font.PLAIN, 34));
			setForeground(Color.WHITE);
			setBackground(new Color(31, 42, 67));
			setFocusPainted(false);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setOpaque(false);
			setMargin(new Insets(0, 0, 0, 0));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
			super.paintComponent(g);
			g2.dispose();
		}
	}
	
}