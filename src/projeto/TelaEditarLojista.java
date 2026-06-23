package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
public class TelaEditarLojista {

	private JFrame frame;
	private JTextField txtCNPJ;
	private JTextField txtNumeroSala;
	private JTextField txtEmailAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarLojista window = new TelaEditarLojista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaEditarLojista() {
		initialize();
	}
	public void visivel() {
		TelaEditarLojista window = new TelaEditarLojista();
		window.frame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
	   
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		GerenciadorTeclado.getInstance().inicializar(frame);
		
		txtCNPJ = new JTextField();
		txtCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtCNPJ.setBounds(732, 296, 720, 59);
		frame.getContentPane().add(txtCNPJ);
		txtCNPJ.setColumns(10);
		txtCNPJ.setOpaque(false);
		txtCNPJ.setBorder(null);
		txtCNPJ.setBackground(new Color(0, 0, 0, 0));
		txtCNPJ.setForeground(Color.WHITE);
		GerenciadorTeclado.getInstance().registrarCampo(txtCNPJ);
		txtNumeroSala = new JTextField();
		txtNumeroSala.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtNumeroSala.setColumns(10);
		txtNumeroSala.setBounds(722, 458, 730, 59);
		txtNumeroSala.setOpaque(false);
		txtNumeroSala.setBorder(null);
		txtNumeroSala.setBackground(new Color(0, 0, 0, 0));
		txtNumeroSala.setForeground(Color.WHITE);
		frame.getContentPane().add(txtNumeroSala);
		GerenciadorTeclado.getInstance().registrarCampoNumerico(txtNumeroSala);
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(TelaEditarLojista.class.getResource("/imagens/img_alterar_cadastro/btn_editar2.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarEEditarLoja();
			}
	
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnEditar.setBounds(838, 685, 282, 59);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setFocusPainted(false);
		frame.getContentPane().add(btnEditar);
		
		txtEmailAdmin = new JTextField();
		txtEmailAdmin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtEmailAdmin.setColumns(10);
		txtEmailAdmin.setBounds(722, 616, 730, 59);
		txtEmailAdmin.setOpaque(false);
		txtEmailAdmin.setBorder(null);
		txtEmailAdmin.setBackground(new Color(0, 0, 0, 0));
		txtEmailAdmin.setForeground(Color.WHITE);
		frame.getContentPane().add(txtEmailAdmin);
		GerenciadorTeclado.getInstance().registrarCampo(txtEmailAdmin);
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(TelaEditarLojista.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            new telaAdmin("joao.admin@email.com").visivel();
	            frame.dispose();
	        }
	    });
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnVoltar.setBounds(15, 15, 285, 90);
		frame.getContentPane().add(btnVoltar);
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaEditarLojista.class.getResource("/imagens/img_alterar_cadastro/tela_Atualizar_Cad.png")));
		lblNewLabel_2.setBounds(0, -23, 1920, 1126);
		frame.getContentPane().add(lblNewLabel_2);
      
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
	}
	private void pesquisarEEditarLoja() {

		String cnpj = txtCNPJ.getText().trim();
		String numSala = txtNumeroSala.getText().trim();
		String email = txtEmailAdmin.getText().trim();

		String regexCNPJ = "^(\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}-\\d{2}|\\d{14})$";
		String regexNumSala = "^(10[1-9]|11\\d|12\\d|13[0-2])$";
		String regexEmail = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

		int camposPreenchidos = 0;

		if (!cnpj.isEmpty()) camposPreenchidos++;
		if (!numSala.isEmpty()) camposPreenchidos++;
		if (!email.isEmpty()) camposPreenchidos++;

		if (camposPreenchidos == 0) {
			JOptionPane.showMessageDialog(
				frame,
				"Preencha CNPJ, número da sala ou e-mail.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		if (camposPreenchidos > 1) {
			JOptionPane.showMessageDialog(
				frame,
				"Preencha apenas um campo para fazer a busca.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		String tipoBusca;
		String valorBusca;

		if (!cnpj.isEmpty()) {

			if (!cnpj.matches(regexCNPJ)) {
				JOptionPane.showMessageDialog(
					frame,
					"CNPJ inválido.",
					"Erro",
					JOptionPane.ERROR_MESSAGE
				);
				return;
			}

			tipoBusca = "cnpj";
			valorBusca = cnpj;

		} else if (!numSala.isEmpty()) {

			if (!numSala.matches(regexNumSala)) {
				JOptionPane.showMessageDialog(
					frame,
					"Número da sala inválido. Use salas de 101 até 132.",
					"Erro",
					JOptionPane.ERROR_MESSAGE
				);
				return;
			}

			tipoBusca = "sala";
			valorBusca = numSala;

		} else {

			if (!email.matches(regexEmail)) {
				JOptionPane.showMessageDialog(
					frame,
					"E-mail inválido.",
					"Erro",
					JOptionPane.ERROR_MESSAGE
				);
				return;
			}

			tipoBusca = "email";
			valorBusca = email;
		}

		Bancodedados bd = new Bancodedados();
		bd.conectar();

		if (!bd.verificar()) {
			JOptionPane.showMessageDialog(
				frame,
				"Não foi possível conectar ao banco de dados.",
				"Aviso",
				JOptionPane.ERROR_MESSAGE
			);
			return;
		}

		try {

			Map<String, String> loja = bd.buscarLojaParaEdicao(tipoBusca, valorBusca);

			if (loja == null) {
				JOptionPane.showMessageDialog(
					frame,
					"Nenhuma loja encontrada.",
					"Não encontrada",
					JOptionPane.WARNING_MESSAGE
				);
				return;
			}

			new TelaAtualizarLojista(loja).visivel();
			frame.dispose();

		} finally {
			bd.desconectar();
		}
	}

	private void abrirFormularioEdicao(Bancodedados bd, Map<String, String> loja) {

		int idLoja = Integer.parseInt(loja.get("id_loja"));

		JTextField txtNome = new JTextField(loja.get("nome_loja"));
		JTextField txtCnpj = new JTextField(loja.get("cnpj_loja"));
		JTextField txtResponsavel = new JTextField(loja.get("responsavel_loja"));
		JTextField txtTelefone = new JTextField(loja.get("telefone_loja"));
		JTextField txtEmail = new JTextField(loja.get("email_loja"));
		JTextField txtEndereco = new JTextField(loja.get("endereco_loja"));
		JTextField txtSala = new JTextField(loja.get("sala_loja"));
		JTextField txtAluguel = new JTextField(loja.get("aluguel_loja"));

		JComboBox<String> cbTipo = new JComboBox<>();
		cbTipo.setModel(new DefaultComboBoxModel<>(new String[] {
			"moda",
			"restaurante e lanchonete",
			"celulares e acessórios",
			"jogos",
			"outros"
		}));
		cbTipo.setSelectedItem(loja.get("tipo_loja"));

		JComboBox<String> cbStatus = new JComboBox<>();
		cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {
			"ativo",
			"inativo"
		}));
		cbStatus.setSelectedItem(loja.get("status_loja"));

		JPasswordField txtSenha = new JPasswordField(loja.get("senha"));

		JPanel painel = new JPanel(new GridLayout(0, 2, 8, 8));

		painel.add(new JLabel("Nome:"));
		painel.add(txtNome);

		painel.add(new JLabel("CNPJ:"));
		painel.add(txtCnpj);

		painel.add(new JLabel("Responsável:"));
		painel.add(txtResponsavel);

		painel.add(new JLabel("Telefone:"));
		painel.add(txtTelefone);

		painel.add(new JLabel("E-mail:"));
		painel.add(txtEmail);

		painel.add(new JLabel("Endereço:"));
		painel.add(txtEndereco);

		painel.add(new JLabel("Sala:"));
		painel.add(txtSala);

		painel.add(new JLabel("Tipo de Loja:"));
		painel.add(cbTipo);

		painel.add(new JLabel("Aluguel:"));
		painel.add(txtAluguel);

		painel.add(new JLabel("Status:"));
		painel.add(cbStatus);

		painel.add(new JLabel("Senha:"));
		painel.add(txtSenha);

		int opcao = JOptionPane.showConfirmDialog(
			frame,
			painel,
			"Atualizar Loja",
			JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.PLAIN_MESSAGE
		);

		if (opcao != JOptionPane.OK_OPTION) {
			return;
		}

		String nome = txtNome.getText().trim();
		String cnpj = txtCnpj.getText().trim();
		String responsavel = txtResponsavel.getText().trim();
		String telefone = txtTelefone.getText().trim();
		String email = txtEmail.getText().trim();
		String endereco = txtEndereco.getText().trim();
		String sala = txtSala.getText().trim();
		String tipo = cbTipo.getSelectedItem().toString();
		String aluguel = txtAluguel.getText().trim().replace(",", ".");
		String status = cbStatus.getSelectedItem().toString();
		String nivel = loja.get("nivel_loja");
		String senha = new String(txtSenha.getPassword()).trim();

		if (nome.isEmpty() || cnpj.isEmpty() || responsavel.isEmpty() ||
			telefone.isEmpty() || email.isEmpty() || endereco.isEmpty() ||
			sala.isEmpty() || aluguel.isEmpty() || senha.isEmpty()) {

			JOptionPane.showMessageDialog(
				frame,
				"Preencha todos os campos.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		if (!validarCNPJ(cnpj)) {
			JOptionPane.showMessageDialog(
				frame,
				"CNPJ inválido.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		if (!validarTelefone(telefone)) {
			JOptionPane.showMessageDialog(
				frame,
				"Telefone inválido. Use o formato: (85) 99999-9999",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		if (!validarEmail(email)) {
			JOptionPane.showMessageDialog(
				frame,
				"E-mail inválido.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		if (!sala.matches("^(10[1-9]|11\\d|12\\d|13[0-2])$")) {
			JOptionPane.showMessageDialog(
				frame,
				"Sala inválida. Use salas de 101 até 132.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		if (!aluguel.matches("\\d+(\\.\\d{1,2})?")) {
			JOptionPane.showMessageDialog(
				frame,
				"Valor do aluguel inválido. Exemplo: 1200.00",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		boolean atualizado = bd.atualizarLoja(
			idLoja,
			nome,
			cnpj,
			responsavel,
			telefone,
			email,
			endereco,
			sala,
			tipo,
			aluguel,
			status,
			nivel,
			senha
		);

		if (atualizado) {
			JOptionPane.showMessageDialog(
				frame,
				"Loja atualizada com sucesso!",
				"Sucesso",
				JOptionPane.INFORMATION_MESSAGE
			);

			limparCamposBusca();
		}
	}

	private void limparCamposBusca() {
		txtCNPJ.setText("");
		txtNumeroSala.setText("");
		txtEmailAdmin.setText("");
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

			return (dig1 == (cnpj.charAt(12) - '0')) &&
				   (dig2 == (cnpj.charAt(13) - '0'));

		} catch (Exception e) {
			return false;
		}
	}
}
