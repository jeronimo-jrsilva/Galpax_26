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

		// INICIALIZA O TECLADO UNIVERSAL
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
		senha.setBounds(304, 745, 517, 25);
		senha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		senha.setForeground(new Color(255, 255, 255));
		senha.setOpaque(false);
		senha.setBorder(null);
		frame.getContentPane().add(senha);
		GerenciadorTeclado.getInstance().registrarCampo(senha);

		confirmarsenha = new JPasswordField();
		confirmarsenha.setBounds(921, 745, 517, 25);
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
		txtNome.setBounds(304, 160, 517, 25);
		txtNome.setOpaque(false);
		txtNome.setBorder(null);
		frame.getContentPane().add(txtNome);
		GerenciadorTeclado.getInstance().registrarCampo(txtNome);

		txtCnpj = new JTextField();
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCnpj.setForeground(new Color(255, 255, 255));
		txtCnpj.setBounds(921, 160, 517, 25);
		txtCnpj.setOpaque(false);
		txtCnpj.setBorder(null);
		frame.getContentPane().add(txtCnpj);
		GerenciadorTeclado.getInstance().registrarCampo(txtCnpj);

		txtResponsavel = new JTextField();
		txtResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtResponsavel.setForeground(new Color(255, 255, 255));
		txtResponsavel.setBounds(304, 261, 517, 25);
		txtResponsavel.setOpaque(false);
		txtResponsavel.setBorder(null);
		frame.getContentPane().add(txtResponsavel);
		GerenciadorTeclado.getInstance().registrarCampo(txtResponsavel);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTelefone.setForeground(new Color(255, 255, 255));
		txtTelefone.setBounds(921, 258, 517, 25);
		txtTelefone.setOpaque(false);
		txtTelefone.setBorder(null);
		frame.getContentPane().add(txtTelefone);
		GerenciadorTeclado.getInstance().registrarCampo(txtTelefone);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setForeground(new Color(255, 255, 255));
		txtEmail.setBounds(303, 360, 517, 25);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(null);
		frame.getContentPane().add(txtEmail);
		GerenciadorTeclado.getInstance().registrarCampo(txtEmail);

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEndereco.setForeground(new Color(255, 255, 255));
		txtEndereco.setBounds(921, 360, 517, 25);
		txtEndereco.setOpaque(false);
		txtEndereco.setBorder(null);
		frame.getContentPane().add(txtEndereco);
		GerenciadorTeclado.getInstance().registrarCampo(txtEndereco);

		cbTipoLoja = new JComboBox<>();
		cbTipoLoja.setModel(new DefaultComboBoxModel<>(new String[] { "moda", "restaurante e lanchonete", "celulares e acessórios", "jogos", "outros" }));
		cbTipoLoja.setBounds(921, 452, 500, 25);
		frame.getContentPane().add(cbTipoLoja);

		txtValorAluguel = new JTextField();
		txtValorAluguel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorAluguel.setForeground(new Color(255, 255, 255));
		txtValorAluguel.setBounds(321, 551, 506, 34);
		txtValorAluguel.setOpaque(false);
		txtValorAluguel.setBorder(null);
		frame.getContentPane().add(txtValorAluguel);
		GerenciadorTeclado.getInstance().registrarCampo(txtValorAluguel);

		cbStatus = new JComboBox<>();
		cbStatus.setModel(new DefaultComboBoxModel<>(new String[] { "ativo", "inativo" }));
		cbStatus.setBounds(921, 556, 500, 25);
		frame.getContentPane().add(cbStatus);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "131 ", "132" }));
		comboBox.setBounds(304, 452, 517, 25);
		frame.getContentPane().add(comboBox);

		JButton btnCadastrar = new JButton("");
		btnCadastrar.addActionListener(e -> {
			// Lógica de cadastro mantida
		});
		btnCadastrar.setBounds(1717, 235, 150, 40);
		frame.getContentPane().add(btnCadastrar);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(e -> {
			txtNome.setText("");
			txtCnpj.setText("");
			txtResponsavel.setText("");
			txtTelefone.setText("");
			txtEmail.setText("");
			txtEndereco.setText("");
			txtValorAluguel.setText("");
		});
		btnLimpar.setBounds(1717, 362, 150, 40);
		frame.getContentPane().add(btnLimpar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CadastroLoja.class.getResource("/imagens/img_cad_cliente/img_cad_cliente_ficha.png")));
		lblNewLabel.setBounds(-26, -19, 1920, 1080);
		frame.getContentPane().add(lblNewLabel);
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