package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JScrollPane;

import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
public class TelaAtualizarLojista {

	private JFrame frame;

	private JTextField txtNome;
	private JTextField txtCNPJ;
	private JTextField txtResponsavel;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtSala;
	private JTextField txtValorAluguel;

	private JComboBox<String> cbTipoLoja;
	private JComboBox<String> cbStatus;

	private JPasswordField txtSenha;

	private int idLoja;
	private String nivelLoja;

	public TelaAtualizarLojista(Map<String, String> loja) {
		this.idLoja = Integer.parseInt(loja.get("id_loja"));
		this.nivelLoja = loja.get("nivel_loja");
		initialize();
		carregarDados(loja);
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

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(TelaAtualizarLojista.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnVoltar.setBounds(40, 34, 178, 65);
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.addActionListener(e -> {
			new TelaEditarLojista().visivel();
			frame.dispose();
		});
		frame.getContentPane().add(btnVoltar);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtNome.setBounds(399, 170, 510, 45);
		frame.getContentPane().add(txtNome);
		txtNome.setOpaque(false);
		txtNome.setBorder(null);
		txtNome.setBackground(new Color(0, 0, 0, 0));
		txtNome.setForeground(Color.WHITE);
		GerenciadorTeclado.getInstance().registrarCampo(txtNome);

		txtCNPJ = new JTextField();
		txtCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtCNPJ.setBounds(1206, 170, 500, 45);
		txtCNPJ.setOpaque(false);
		txtCNPJ.setBorder(null);
		txtCNPJ.setBackground(new Color(0, 0, 0, 0));
		txtCNPJ.setForeground(Color.WHITE);
		frame.getContentPane().add(txtCNPJ);
		GerenciadorTeclado.getInstance().registrarCampo(txtCNPJ);

		txtResponsavel = new JTextField();
		txtResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtResponsavel.setBounds(399, 250, 510, 45);
		txtResponsavel.setOpaque(false);
		txtResponsavel.setBorder(null);
		txtResponsavel.setBackground(new Color(0, 0, 0, 0));
		txtResponsavel.setForeground(Color.WHITE);
		frame.getContentPane().add(txtResponsavel);
		GerenciadorTeclado.getInstance().registrarCampo(txtResponsavel);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtTelefone.setBounds(1206, 250, 500, 45);
		txtTelefone.setOpaque(false);
		txtTelefone.setBorder(null);
		txtTelefone.setBackground(new Color(0, 0, 0, 0));
		txtTelefone.setForeground(Color.WHITE);
		frame.getContentPane().add(txtTelefone);
		GerenciadorTeclado.getInstance().registrarCampo(txtTelefone);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEmail.setBounds(399, 328, 510, 45);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(null);
		txtEmail.setBackground(new Color(0, 0, 0, 0));
		txtEmail.setForeground(Color.WHITE);
		frame.getContentPane().add(txtEmail);
		GerenciadorTeclado.getInstance().registrarCampo(txtEmail);

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEndereco.setBounds(1206, 328, 500, 45);
		txtEndereco.setOpaque(false);
		txtEndereco.setBorder(null);
		txtEndereco.setBackground(new Color(0, 0, 0, 0));
		txtEndereco.setForeground(Color.WHITE);
		frame.getContentPane().add(txtEndereco);
		GerenciadorTeclado.getInstance().registrarCampo(txtEndereco);

		txtSala = new JTextField();
		txtSala.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtSala.setBounds(399, 410, 510, 45);
		txtSala.setOpaque(false);
		txtSala.setBorder(null);
		txtSala.setBackground(new Color(0, 0, 0, 0));
		txtSala.setForeground(Color.WHITE);
		frame.getContentPane().add(txtSala);
		GerenciadorTeclado.getInstance().registrarCampoNumerico(txtSala);

		cbTipoLoja = new JComboBox<>();
		cbTipoLoja.setModel(new DefaultComboBoxModel<>(new String[] {
			"moda",
			"restaurante e lanchonete",
			"celulares e acessórios",
			"jogos",
			"outros"
		}));
		cbTipoLoja.setBounds(1206, 411, 491, 45);
		estilizarComboBox(cbTipoLoja);
		frame.getContentPane().add(cbTipoLoja);

		txtValorAluguel = new JTextField();
		txtValorAluguel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtValorAluguel.setBounds(399, 490, 510, 45);
		txtValorAluguel.setOpaque(false);
		txtValorAluguel.setBorder(null);
		txtValorAluguel.setBackground(new Color(0, 0, 0, 0));
		txtValorAluguel.setForeground(Color.WHITE);
		frame.getContentPane().add(txtValorAluguel);
		GerenciadorTeclado.getInstance().registrarCampoNumerico(txtValorAluguel);

		cbStatus = new JComboBox<>();
		cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {
			"ativo",
			"inativo"
		}));
		cbStatus.setBounds(1206, 491, 491, 45);
		estilizarComboBox(cbStatus);
		frame.getContentPane().add(cbStatus);

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtSenha.setBounds(397, 570, 512, 45);
		txtSenha.setOpaque(false);
		txtSenha.setBorder(null);
		txtSenha.setBackground(new Color(0, 0, 0, 0));
		txtSenha.setForeground(Color.WHITE);
		frame.getContentPane().add(txtSenha);
		GerenciadorTeclado.getInstance().registrarCampo(txtSenha);

		JButton btnSalvar = new JButton("Salvar Alterações");
		btnSalvar.setIcon(new ImageIcon(TelaAtualizarLojista.class.getResource("/imagens/img_Atualizar_dados/btn_editar_dados.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSalvar.setBounds(760, 825, 390, 65);
		btnSalvar.addActionListener(e -> salvarAlteracoes());
		frame.getContentPane().add(btnSalvar);
		btnSalvar.setContentAreaFilled(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setFocusPainted(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaAtualizarLojista.class.getResource("/imagens/img_Atualizar_dados/img_fundo_editar_dados.png")));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblNewLabel);

		GerenciadorJanelas.configurarJanela(frame);
	}

	private void carregarDados(Map<String, String> loja) {
		txtNome.setText(loja.get("nome_loja"));
		txtCNPJ.setText(loja.get("cnpj_loja"));
		txtResponsavel.setText(loja.get("responsavel_loja"));
		txtTelefone.setText(loja.get("telefone_loja"));
		txtEmail.setText(loja.get("email_loja"));
		txtEndereco.setText(loja.get("endereco_loja"));
		txtSala.setText(loja.get("sala_loja"));
		txtValorAluguel.setText(loja.get("aluguel_loja"));

		cbTipoLoja.setSelectedItem(loja.get("tipo_loja"));
		cbStatus.setSelectedItem(loja.get("status_loja"));

		txtSenha.setText(loja.get("senha"));
	}

	private void salvarAlteracoes() {
		String nome = txtNome.getText().trim();
		String cnpj = txtCNPJ.getText().trim();
		String responsavel = txtResponsavel.getText().trim();
		String telefone = txtTelefone.getText().trim();
		String email = txtEmail.getText().trim();
		String endereco = txtEndereco.getText().trim();
		String sala = txtSala.getText().trim();
		String tipo = cbTipoLoja.getSelectedItem().toString();
		String aluguel = txtValorAluguel.getText().trim().replace(",", ".");
		String status = cbStatus.getSelectedItem().toString();
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
			JOptionPane.showMessageDialog(frame, "CNPJ inválido.");
			return;
		}

		if (!validarTelefone(telefone)) {
			JOptionPane.showMessageDialog(frame, "Telefone inválido. Use o formato: (85) 99999-9999");
			return;
		}

		if (!validarEmail(email)) {
			JOptionPane.showMessageDialog(frame, "E-mail inválido.");
			return;
		}

		if (!sala.matches("^(10[1-9]|11\\d|12\\d|13[0-2])$")) {
			JOptionPane.showMessageDialog(frame, "Sala inválida. Use salas de 101 até 132.");
			return;
		}

		if (!aluguel.matches("\\d+(\\.\\d{1,2})?")) {
			JOptionPane.showMessageDialog(frame, "Valor do aluguel inválido. Exemplo: 1200.00");
			return;
		}

		Bancodedados bd = new Bancodedados();
		bd.conectar();

		if (!bd.verificar()) {
			JOptionPane.showMessageDialog(frame, "Não foi possível conectar ao banco de dados.");
			return;
		}

		try {
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
				nivelLoja,
				senha
			);

			if (atualizado) {
				JOptionPane.showMessageDialog(
					frame,
					"Loja atualizada com sucesso!",
					"Sucesso",
					JOptionPane.INFORMATION_MESSAGE
				);

				new TelaEditarLojista().visivel();
				frame.dispose();
			}

		} finally {
			bd.desconectar();
		}
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
			int soma = 0;
			int peso = 2;

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

			return dig1 == (cnpj.charAt(12) - '0') &&
				   dig2 == (cnpj.charAt(13) - '0');

		} catch (Exception e) {
			return false;
		}
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
}