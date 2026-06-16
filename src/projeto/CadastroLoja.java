package projeto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class CadastroLoja {

	private JFrame frame;

	private JTextField txtNome;
	private JTextField txtResponsavel;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtValorAluguel;

	private JTextField txtCnpj;
	private JTextField txtTelefone;

	private JComboBox<String> cbTipoLoja;
	private JComboBox<String> cbStatus;

	MaskFormatter maskCnpj;
	MaskFormatter maskTelefone;

	
	private JPanel containerTeclado = new JPanel(new BorderLayout()) {{
		setOpaque(false);
	}};
	private boolean shiftAtivo = false;
	private List<JButton> botoesLetras = new ArrayList<>();
	
	
	private JTextField campoAtivo;
	private JPasswordField senha;
	private JPasswordField confirmarsenha;
	private JButton btnVoltar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroLoja window = new CadastroLoja();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroLoja() {
		initialize();
	}
	
	public void visivel() {
		CadastroLoja window = new CadastroLoja();
		window.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setUndecorated(true); 

	    
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setSize(screenSize.width, screenSize.height);
	   
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.getContentPane().setLayout(null);
      
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
		
		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(CadastroLoja.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(50, 50, 104, 35);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaAdmin("joao.admin@email.com").visivel();
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnVoltar);
		
		confirmarsenha = new JPasswordField();
		confirmarsenha.setBounds(1264, 644, 409, 33);
		frame.getContentPane().add(confirmarsenha);
		confirmarsenha.setOpaque(false);      
		confirmarsenha.setBorder(null);
		senha = new JPasswordField();
		senha.setBounds(331, 644, 588, 33);
		frame.getContentPane().add(senha);
		senha.setOpaque(false);      
		senha.setBorder(null);
		containerTeclado.setBounds(378, 720, 1200, 350);
		frame.getContentPane().add(containerTeclado);

		
		try {
			maskCnpj = new MaskFormatter("##.###.###/####-##");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro máscara CNPJ");
		}

		try {
			maskTelefone = new MaskFormatter("(##) #####-####");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro máscara telefone");
		}

		txtNome = new JTextField();
		txtNome.setBounds(334, 268, 584, 25);
		txtNome.setOpaque(false);      
		txtNome.setBorder(null);
		frame.getContentPane().add(txtNome);
		txtNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAtivo = txtNome; 
				mostrarTeclado(criarTecladoCompleto(txtNome));
			}
		});

		txtCnpj = new JTextField();
		txtCnpj.setBounds(1087, 266, 578, 25);
		txtCnpj.setOpaque(false);      
		txtCnpj.setBorder(null);
		frame.getContentPane().add(txtCnpj);
		txtCnpj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAtivo = txtCnpj; 
				mostrarTeclado(criarTecladoCompleto(txtCnpj));
			}
		});

		txtResponsavel = new JTextField();
		txtResponsavel.setBounds(334, 333, 578, 23);
		txtResponsavel.setOpaque(false);      
		txtResponsavel.setBorder(null);
		frame.getContentPane().add(txtResponsavel);
		txtResponsavel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAtivo = txtResponsavel; 
				mostrarTeclado(criarTecladoCompleto(txtResponsavel));
			}
		});

		//txtTelefone = new JFormattedTextField(maskTelefone);
		txtTelefone = new JTextField();
		txtTelefone.setBounds(1142, 327, 520, 30);
		txtTelefone.setOpaque(false);      
		txtTelefone.setBorder(null);
		frame.getContentPane().add(txtTelefone);
		txtTelefone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAtivo = txtTelefone; 
				mostrarTeclado(criarTecladoCompleto(txtTelefone));
			}
		});

		txtEmail = new JTextField();
		txtEmail.setBounds(331, 394, 584, 28);
		txtEmail.setOpaque(false);      
		txtEmail.setBorder(null);
		frame.getContentPane().add(txtEmail);
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAtivo = txtEmail;
				mostrarTeclado(criarTecladoCompleto(txtEmail));
			}
		});

		txtEndereco = new JTextField();
		txtEndereco.setBounds(1159, 398, 510, 25);
		txtEndereco.setOpaque(false);      
		txtEndereco.setBorder(null);
		frame.getContentPane().add(txtEndereco);
		txtEndereco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAtivo = txtEndereco; 
				mostrarTeclado(criarTecladoCompleto(txtEndereco));
			}
		});

		cbTipoLoja = new JComboBox<String>();
		cbTipoLoja.setModel(new DefaultComboBoxModel<>(new String[] {"moda", "restaurante e lanchonete", "celulares e acessórios", "jogos", "outros"}));
		cbTipoLoja.addItem("Games");
		cbTipoLoja.addItem("Roupas");
		cbTipoLoja.addItem("Eletrônicos");
		cbTipoLoja.addItem("Alimentação");
		cbTipoLoja.setBounds(1165, 449, 509, 43);
		frame.getContentPane().add(cbTipoLoja);
		

		txtValorAluguel = new JTextField();
		txtValorAluguel.setBounds(330, 521, 584, 32);
		txtValorAluguel.setOpaque(false);      
		txtValorAluguel.setBorder(null);
		frame.getContentPane().add(txtValorAluguel);
		txtValorAluguel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAtivo = txtValorAluguel; 
				mostrarTeclado(criarTecladoCompleto(txtValorAluguel));
			}
		});

		cbStatus = new JComboBox<String>();
		cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {"ativo", "inativo"}));
		cbStatus.addItem("Ativa");
		cbStatus.addItem("Inativa");
		cbStatus.setBounds(1137, 518, 535, 35);
		frame.getContentPane().add(cbStatus);
		
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(330, 452, 590, 36);
		frame.getContentPane().add(comboBox);

		comboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
		    @Override
		    public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
		        Bancodedados bd = new Bancodedados();
		        bd.conectar();
		        bd.popularComboSalasLivres(comboBox);
		        bd.desconectar();
		    }
		    @Override
		    public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
		    @Override
		    public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
		});
		
		
		JComboBox<String> cbStatus_1 = new JComboBox<>();
		cbStatus_1.setModel(new DefaultComboBoxModel(new String[] {"cliente"}));
		cbStatus_1.setBounds(330, 578, 1352, 34);
		frame.getContentPane().add(cbStatus_1);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = txtNome.getText().trim();
				String cnpj = txtCnpj.getText().trim();
				String responsavel = txtResponsavel.getText().trim();
				String telefone = txtTelefone.getText().trim();
				String email = txtEmail.getText().trim();
				String endereco = txtEndereco.getText().trim();
				String valorAluguel = txtValorAluguel.getText().trim();
				
				
				String sala = comboBox.getSelectedItem().toString();
				String tipoLoja = cbTipoLoja.getSelectedItem().toString();
				String status = cbStatus.getSelectedItem().toString();
				String nivelAcesso = cbStatus_1.getSelectedItem().toString();
				String senhalojista = new String(senha.getPassword()).trim();
				String confsenhalojista = new String(confirmarsenha.getPassword()).trim();
			
				if (!nome.isEmpty()) {

				    if (nome.length() >= 3) {

				        if (!nome.matches(".*\\d.*")) {

				            if (!cnpj.isEmpty()) {

				                if (validarCNPJ(cnpj)) {

				                    if (!responsavel.isEmpty()) {

				                        if (responsavel.length() >= 3) {

				                            if (!telefone.isEmpty()) {

				                                if (validarTelefone(telefone)) {

				                                    if (!email.isEmpty()) {

				                                        if (validarEmail(email)) {

				                                            if (!endereco.isEmpty()) {

				                                                if (!valorAluguel.isEmpty()) {

				                                                    try {

				                                                        double aluguel = Double.parseDouble(
				                                                                valorAluguel.replace(",", "."));

				                                                        if (aluguel > 0) {

				                                                            if (!senhalojista.isEmpty()) {

				                                                                if (senhalojista.length() >= 6) {

				                                                                    if (!confsenhalojista.isEmpty()) {

				                                                                        if (senhalojista.equals(confsenhalojista)) {

				                                                                            Bancodedados bd = new Bancodedados();
				                                                                            bd.conectar();

				                                                                            if (bd.verificar()) {

				                                                                                try {

				                                                                                    bd.inserirLojaCompleta(
				                                                                                            nome,
				                                                                                            cnpj,
				                                                                                            responsavel,
				                                                                                            telefone,
				                                                                                            email,
				                                                                                            endereco,
				                                                                                            sala,
				                                                                                            tipoLoja,
				                                                                                            valorAluguel,
				                                                                                            status,
				                                                                                            nivelAcesso,
				                                                                                            senhalojista);

				                                                                                    JOptionPane.showMessageDialog(
				                                                                                            null,
				                                                                                            "Loja cadastrada com sucesso!");

				                                                                                    txtNome.setText("");
				                                                                                    txtCnpj.setText("");
				                                                                                    txtResponsavel.setText("");
				                                                                                    txtTelefone.setText("");
				                                                                                    txtEmail.setText("");
				                                                                                    txtEndereco.setText("");
				                                                                                    txtValorAluguel.setText("");
				                                                                                    senha.setText("");
				                                                                                    confirmarsenha.setText("");

				                                                                                } catch (Exception ex) {

				                                                                                    JOptionPane.showMessageDialog(
				                                                                                            null,
				                                                                                            "Erro ao cadastrar:\n"
				                                                                                                    + ex.getMessage(),
				                                                                                            "Erro",
				                                                                                            JOptionPane.ERROR_MESSAGE);
				                                                                                }

				                                                                                bd.desconectar();

				                                                                            } else {

				                                                                                JOptionPane.showMessageDialog(
				                                                                                        null,
				                                                                                        "Falha ao conectar ao banco.",
				                                                                                        "Erro",
				                                                                                        JOptionPane.ERROR_MESSAGE);
				                                                                            }

				                                                                        } else {

				                                                                            JOptionPane.showMessageDialog(
				                                                                                    null,
				                                                                                    "As senhas não coincidem.");
				                                                                        }

				                                                                    } else {

				                                                                        JOptionPane.showMessageDialog(
				                                                                                null,
				                                                                                "Confirme a senha.");
				                                                                    }

				                                                                } else {

				                                                                    JOptionPane.showMessageDialog(
				                                                                            null,
				                                                                            "A senha deve possuir no mínimo 6 caracteres.");
				                                                                }

				                                                            } else {

				                                                                JOptionPane.showMessageDialog(
				                                                                        null,
				                                                                        "Informe a senha.");
				                                                            }

				                                                        } else {

				                                                            JOptionPane.showMessageDialog(
				                                                                    null,
				                                                                    "O valor do aluguel deve ser maior que zero.");
				                                                        }

				                                                    } catch (NumberFormatException ex) {

				                                                        JOptionPane.showMessageDialog(
				                                                                null,
				                                                                "Valor do aluguel inválido.");
				                                                    }

				                                                } else {

				                                                    JOptionPane.showMessageDialog(
				                                                            null,
				                                                            "Informe o valor do aluguel.");
				                                                }

				                                            } else {

				                                                JOptionPane.showMessageDialog(
				                                                        null,
				                                                        "Informe o endereço.");
				                                            }

				                                        } else {

				                                            JOptionPane.showMessageDialog(
				                                                    null,
				                                                    "E-mail inválido.");
				                                        }

				                                    } else {

				                                        JOptionPane.showMessageDialog(
				                                                null,
				                                                "Informe o e-mail.");
				                                    }

				                                } else {

				                                    JOptionPane.showMessageDialog(
				                                            null,
				                                            "Telefone inválido.");
				                                }

				                            } else {

				                                JOptionPane.showMessageDialog(
				                                        null,
				                                        "Informe o telefone.");
				                            }

				                        } else {

				                            JOptionPane.showMessageDialog(
				                                    null,
				                                    "O responsável deve possuir pelo menos 3 caracteres.");
				                        }

				                    } else {

				                        JOptionPane.showMessageDialog(
				                                null,
				                                "Informe o responsável.");
				                    }

				                } else {

				                    JOptionPane.showMessageDialog(
				                            null,
				                            "CNPJ inválido.");
				                }

				            } else {

				                JOptionPane.showMessageDialog(
				                        null,
				                        "Informe o CNPJ.");
				            }

				        } else {

				            JOptionPane.showMessageDialog(
				                    null,
				                    "O nome da loja não pode conter números.");
				        }

				    } else {

				        JOptionPane.showMessageDialog(
				                null,
				                "O nome da loja deve possuir pelo menos 3 caracteres.");
				    }

				} else {

				    JOptionPane.showMessageDialog(
				            null,
				            "Informe o nome da loja.");
				}
			}
		});
		btnCadastrar.setBounds(1717, 235, 150, 40);
		frame.getContentPane().add(btnCadastrar);

		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtCnpj.setText("");
				txtResponsavel.setText("");
				txtTelefone.setText("");
				txtEmail.setText("");
				txtEndereco.setText("");
				txtValorAluguel.setText("");
			}
		});
		btnLimpar.setBounds(1717, 362, 150, 40);
		frame.getContentPane().add(btnLimpar);

		// BOTÃO SAIR
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSair.setBounds(1717, 463, 150, 40);
		frame.getContentPane().add(btnSair);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CadastroLoja.class.getResource("/imagens/telacadfundoof.png")));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblNewLabel);
	}

	
	private void mostrarTeclado(JPanel painelTeclado) {
		containerTeclado.removeAll();
		containerTeclado.add(painelTeclado, BorderLayout.CENTER);
		containerTeclado.revalidate();
		containerTeclado.repaint();
	}

	private void inserirTexto(JTextField campo, String texto) {
		if (campoAtivo == null) return;
		
		String atual = campoAtivo.getText();
		if (texto.equals("APAGAR")) {
			if (atual.length() > 0) {
				campoAtivo.setText(atual.substring(0, atual.length() - 1));
			}
		} else if (texto.equals("ESPAÇO")) {
			campoAtivo.setText(atual + " ");
		} else {
			campoAtivo.setText(atual + texto);
		}
	}

	private void alternarShift(JButton btnShift) {
		shiftAtivo = !shiftAtivo;
		if (shiftAtivo) {
			btnShift.setBackground(Color.LIGHT_GRAY);
		} else {
			btnShift.setBackground(Color.WHITE);
		}

		for (JButton btn : botoesLetras) {
			String letra = btn.getText();
			if (shiftAtivo) {
				btn.setText(letra.toUpperCase());
			} else {
				btn.setText(letra.toLowerCase());
			}
		}
	}

	private JPanel criarTecladoCompleto(JTextField campo) {
		botoesLetras.clear();
		shiftAtivo = false;

		JPanel painelPrincipal = new JPanel(new GridLayout(5, 1, 0, 6));
		painelPrincipal.setOpaque(false);
		
		String[][] linhas = {
			{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "@"},
			{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "#"},
			{"a", "s", "d", "f", "g", "h", "j", "k", "l", "ç", ";", "\"", "$"},
			{"SHIFT", "z", "x", "c", "v", "b", "n", "m", ",", ".", "/", "!", "%"},
			{"&", "*", "ESPAÇO", "APAGAR"}
		};

		for (int i = 0; i < linhas.length; i++) {
			JPanel painelLinha = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
			painelLinha.setOpaque(false);
			
			for (String caractere : linhas[i]) {
				JButton btn = new JButton(caractere);
				btn.setBackground(Color.WHITE);
				btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				if (caractere.equals("SHIFT")) {
					btn.setText("SHIFT ⇧");
					btn.setPreferredSize(new java.awt.Dimension(110, 50));
					btn.addActionListener(e -> alternarShift(btn));
				} else if (caractere.equals("APAGAR")) {
					btn.setBackground(new Color(255, 200, 200));
					btn.setPreferredSize(new java.awt.Dimension(120, 50));
					btn.addActionListener(e -> inserirTexto(campoAtivo, "APAGAR"));
				} else if (caractere.equals("ESPAÇO")) {
					btn.setPreferredSize(new java.awt.Dimension(400, 50));
					btn.addActionListener(e -> inserirTexto(campoAtivo, "ESPAÇO"));
				} else {
					btn.setPreferredSize(new java.awt.Dimension(55, 50));
					btn.addActionListener(e -> inserirTexto(campoAtivo, btn.getText()));
					
					if (caractere.matches("[a-zç]")) {
						botoesLetras.add(btn);
					}
				}
				painelLinha.add(btn);
			}
			painelPrincipal.add(painelLinha);
		}
		return painelPrincipal;
	}
	private boolean validarEmail(String email) {
	    return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}

	private boolean validarTelefone(String telefone) {
	    return telefone.matches("\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}");
	}

	private boolean validarCNPJ(String cnpj) {

	    cnpj = cnpj.replaceAll("[^0-9]", "");

	    if (cnpj.length() != 14)
	        return false;

	    if (cnpj.matches("(\\d)\\1{13}"))
	        return false;

	    try {

	        int soma = 0;
	        int peso = 2;

	        for (int i = 11; i >= 0; i--) {
	            soma += (cnpj.charAt(i) - '0') * peso;
	            peso++;
	            if (peso == 10)
	                peso = 2;
	        }

	        int resto = soma % 11;
	        int dig1 = (resto < 2) ? 0 : 11 - resto;

	        soma = 0;
	        peso = 2;

	        for (int i = 12; i >= 0; i--) {
	            soma += (cnpj.charAt(i) - '0') * peso;
	            peso++;
	            if (peso == 10)
	                peso = 2;
	        }

	        resto = soma % 11;
	        int dig2 = (resto < 2) ? 0 : 11 - resto;

	        return dig1 == (cnpj.charAt(12) - '0')
	                && dig2 == (cnpj.charAt(13) - '0');

	    } catch (Exception e) {
	        return false;
	    }
	}
	
}