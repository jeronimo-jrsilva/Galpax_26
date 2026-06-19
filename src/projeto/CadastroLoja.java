package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.DefaultListCellRenderer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Polygon;
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
	private JPasswordField senha;
	private JPasswordField confirmarsenha;
	private JButton btnVoltar;
	
	MaskFormatter maskCnpj;
	MaskFormatter maskTelefone;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CadastroLoja().frame.setVisible(true);
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
		this.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		
		GerenciadorTeclado.getInstance().inicializar(frame);

		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(CadastroLoja.class.getResource("/imagens/img_pagamento_credito/img_pagamento_credito_btn_voltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(50, 50, 104, 35);
		btnVoltar.addActionListener(e -> {
			new telaAdmin("joao.admin@email.com").visivel();
			frame.dispose();
		});
		frame.getContentPane().add(btnVoltar);

		senha = new JPasswordField();
		senha.setBounds(333, 678, 517, 44);
		senha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		senha.setForeground(new Color(255, 255, 255));
		senha.setOpaque(false);
		senha.setBorder(null);
		frame.getContentPane().add(senha);
		GerenciadorTeclado.getInstance().registrarCampo(senha);

		confirmarsenha = new JPasswordField();
		confirmarsenha.setBounds(940, 674, 517, 46);
		confirmarsenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		confirmarsenha.setForeground(new Color(255, 255, 255));
		confirmarsenha.setOpaque(false);
		confirmarsenha.setBorder(null);
		frame.getContentPane().add(confirmarsenha);
		GerenciadorTeclado.getInstance().registrarCampo(confirmarsenha);

		try {
			maskCnpj = new MaskFormatter("##.###.###/####-##");
			maskTelefone = new MaskFormatter("(##) #####-####");
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNome.setForeground(new Color(255, 255, 255));
		txtNome.setBounds(331, 175, 517, 42);
		txtNome.setOpaque(false);
		txtNome.setBorder(null);
		frame.getContentPane().add(txtNome);
		GerenciadorTeclado.getInstance().registrarCampo(txtNome);

		txtCnpj = new JTextField();
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCnpj.setForeground(new Color(255, 255, 255));
		txtCnpj.setBounds(944, 170, 517, 45);
		txtCnpj.setOpaque(false);
		txtCnpj.setBorder(null);
		frame.getContentPane().add(txtCnpj);
		GerenciadorTeclado.getInstance().registrarCampo(txtCnpj);

		txtResponsavel = new JTextField();
		txtResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtResponsavel.setForeground(new Color(255, 255, 255));
		txtResponsavel.setBounds(330, 271, 517, 47);
		txtResponsavel.setOpaque(false);
		txtResponsavel.setBorder(null);
		frame.getContentPane().add(txtResponsavel);
		GerenciadorTeclado.getInstance().registrarCampo(txtResponsavel);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTelefone.setForeground(new Color(255, 255, 255));
		txtTelefone.setBounds(939, 268, 517, 47);
		txtTelefone.setOpaque(false);
		txtTelefone.setBorder(null);
		frame.getContentPane().add(txtTelefone);
		GerenciadorTeclado.getInstance().registrarCampo(txtTelefone);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setForeground(new Color(255, 255, 255));
		txtEmail.setBounds(335, 372, 517, 44);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(null);
		frame.getContentPane().add(txtEmail);
		GerenciadorTeclado.getInstance().registrarCampo(txtEmail);

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEndereco.setForeground(new Color(255, 255, 255));
		txtEndereco.setBounds(940, 368, 517, 47);
		txtEndereco.setOpaque(false);
		txtEndereco.setBorder(null);
		frame.getContentPane().add(txtEndereco);
		GerenciadorTeclado.getInstance().registrarCampo(txtEndereco);

		cbTipoLoja = new JComboBox<>();
		cbTipoLoja.setModel(new DefaultComboBoxModel<>(new String[] { 
			"moda", 
			"restaurante e lanchonete", 
			"celulares e acessórios", 
			"jogos", 
			"outros" 
		}));
		cbTipoLoja.setBounds(912, 474, 537, 35);
		estilizarComboBox(cbTipoLoja);
		frame.getContentPane().add(cbTipoLoja);

		txtValorAluguel = new JTextField();
		txtValorAluguel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorAluguel.setForeground(new Color(255, 255, 255));
		txtValorAluguel.setBounds(338, 563, 506, 47);
		txtValorAluguel.setOpaque(false);
		txtValorAluguel.setBorder(null);
		frame.getContentPane().add(txtValorAluguel);
		GerenciadorTeclado.getInstance().registrarCampo(txtValorAluguel);

		cbStatus = new JComboBox<>();
		cbStatus.setModel(new DefaultComboBoxModel<>(new String[] { "ativo", "inativo" }));
		cbStatus.setBounds(913, 575, 537, 46);
		estilizarComboBox(cbStatus);
		frame.getContentPane().add(cbStatus);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "131", "132" }));
		comboBox.setBounds(306, 474, 545, 35);
		estilizarComboBox(comboBox);
		frame.getContentPane().add(comboBox);

		JButton btnCadastrar = new JButton("");
		btnCadastrar.setIcon(new ImageIcon(CadastroLoja.class.getResource("/imagens/img_cad_cliente/img_cad_cliente_btn_cadastrar.png")));
		btnCadastrar.addActionListener(e -> {
			
		});
		btnCadastrar.setBounds(1537, 132, 182, 40);
		frame.getContentPane().add(btnCadastrar);
		btnCadastrar.setContentAreaFilled(false);
		btnCadastrar.setBorderPainted(false);     
		btnCadastrar.setFocusPainted(false);      
		btnCadastrar.setOpaque(false);            
		JButton btnLimpar = new JButton("");
		btnLimpar.setIcon(new ImageIcon(CadastroLoja.class.getResource("/imagens/img_cad_cliente/img_cad_cliente_btn_limpar.png")));
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			txtCnpj.setText("");
			txtResponsavel.setText("");
			txtTelefone.setText("");
			txtEmail.setText("");
			txtEndereco.setText("");
			txtValorAluguel.setText("");
		});
		btnLimpar.setBounds(1546, 190, 169, 46);
		frame.getContentPane().add(btnLimpar);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);     
		btnLimpar.setFocusPainted(false);      
		btnLimpar.setOpaque(false);     
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CadastroLoja.class.getResource("/imagens/img_cad_cliente/img_cad_cliente_ficha.png")));
		lblNewLabel.setBounds(0, -9, 1920, 1099);
		frame.getContentPane().add(lblNewLabel);

		// APLICA GERENCIADOR ADAPTATIVO
		GerenciadorJanelas.configurarJanela(frame);
	}
	private void estilizarComboBox(JComboBox<String> combo) {
		Color fundo = new Color(0x0e1731);
		Color fundoSelecionado = new Color(0x16244a);
		Color texto = Color.WHITE;

		combo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		combo.setForeground(texto);
		combo.setBackground(fundo);
		combo.setOpaque(false);
		combo.setBorder(null);
		combo.setFocusable(false);
		combo.setMaximumRowCount(6);

		combo.setUI(new BasicComboBoxUI() {

			@Override
			protected JButton createArrowButton() {
				JButton botao = new JButton() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);

						g.setColor(Color.WHITE);

						int largura = getWidth();
						int altura = getHeight();

						Polygon seta = new Polygon();
						seta.addPoint(largura / 2 - 7, altura / 2 - 3);
						seta.addPoint(largura / 2 + 7, altura / 2 - 3);
						seta.addPoint(largura / 2, altura / 2 + 5);

						g.fillPolygon(seta);
					}
				};

				botao.setOpaque(false);
				botao.setContentAreaFilled(false);
				botao.setBorderPainted(false);
				botao.setFocusPainted(false);

				return botao;
			}

			@Override
			public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
				
			}

			@Override
			protected ComboPopup createPopup() {
				BasicComboPopup popup = new BasicComboPopup(comboBox) {
					@Override
					protected JScrollPane createScroller() {
						JScrollPane scroll = super.createScroller();
						scroll.setBorder(null);
						scroll.getViewport().setBackground(fundo);
						return scroll;
					}
				};

				popup.setBackground(fundo);
				popup.setBorder(BorderFactory.createLineBorder(new Color(0x26385f), 1));

				return popup;
			}
		});

		combo.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(
					JList<?> list,
					Object value,
					int index,
					boolean isSelected,
					boolean cellHasFocus) {

				JLabel label = (JLabel) super.getListCellRendererComponent(
						list, value, index, isSelected, cellHasFocus);

				label.setFont(new Font("Tahoma", Font.PLAIN, 16));
				label.setForeground(Color.WHITE);
				label.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 8));

				if (index == -1) {
					label.setOpaque(false);
				} else {
					label.setOpaque(true);

					if (isSelected) {
						label.setBackground(fundoSelecionado);
					} else {
						label.setBackground(fundo);
					}
				}

				list.setBackground(fundo);
				list.setForeground(Color.WHITE);
				list.setSelectionBackground(fundoSelecionado);
				list.setSelectionForeground(Color.WHITE);

				return label;
			}
		});

		combo.repaint();
	}
	private boolean validarEmail(String email) {
		return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}

	private boolean validarTelefone(String telefone) {
		return telefone.matches("\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}");
	}

	private boolean validarCNPJ(String cnpj) {
		cnpj = cnpj.replaceAll("[^0-9]", "");
		if (cnpj.length() != 14) return false;
		if (cnpj.matches("(\\d)\\1{13}")) return false;
		try {
			int soma = 0, peso = 2;
			for (int i = 11; i >= 0; i--) {
				soma += (cnpj.charAt(i) - '0') * peso++;
				if (peso == 10) peso = 2;
			}
			int dig1 = 11 - (soma % 11);
			if (dig1 > 9) dig1 = 0;
			soma = 0;
			peso = 2;
			for (int i = 12; i >= 0; i--) {
				soma += (cnpj.charAt(i) - '0') * peso++;
				if (peso == 10) peso = 2;
			}
			int dig2 = 11 - (soma % 11);
			if (dig2 > 9) dig2 = 0;
			return (dig1 == (cnpj.charAt(12) - '0')) && (dig2 == (cnpj.charAt(13) - '0'));
		} catch (Exception e) {
			return false;
		}
	}
}