package projeto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

public class telaCartãoCredito {

	private JFrame frame;

	private JTextField txtTitular;
	private JFormattedTextField txtNumeroCartao;
	private JFormattedTextField txtValidade;
	private JPasswordField jftCVV;
	private JComboBox<String> cboxParcelas;

	private int idMensalidade;
	private String emailSessao;

	public telaCartãoCredito(JFrame parent, int idMensalidade, String emailSessao) {
		this.idMensalidade = idMensalidade;
		this.emailSessao = emailSessao;
		initialize();
	}

	public telaCartãoCredito() {
		this(null, 0, null);
	}

	public void visivel() {
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Compra no Crédito");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		GerenciadorTeclado.getInstance().inicializar(frame);

		txtTitular = new JTextField();
		txtTitular.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTitular.setForeground(Color.WHITE);
		txtTitular.setBounds(757, 247, 831, 56);
		txtTitular.setOpaque(false);
		txtTitular.setBorder(null);
		txtTitular.setBackground(new Color(0, 0, 0, 0));
		frame.getContentPane().add(txtTitular);
		GerenciadorTeclado.getInstance().registrarCampo(txtTitular);

		try {
			MaskFormatter mascaraCartao = new MaskFormatter("#### #### #### ####");
			mascaraCartao.setPlaceholderCharacter('_');

			txtNumeroCartao = new JFormattedTextField(mascaraCartao);
			txtNumeroCartao.setForeground(Color.WHITE);
			txtNumeroCartao.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtNumeroCartao.setBounds(757, 383, 831, 46);
			txtNumeroCartao.setOpaque(false);
			txtNumeroCartao.setBorder(null);
			txtNumeroCartao.setBackground(new Color(0, 0, 0, 0));
			frame.getContentPane().add(txtNumeroCartao);
			GerenciadorTeclado.getInstance().registrarCampoNumerico(txtNumeroCartao);

			MaskFormatter mascaraValidade = new MaskFormatter("##/##");
			mascaraValidade.setPlaceholderCharacter('_');

			txtValidade = new JFormattedTextField(mascaraValidade);
			txtValidade.setForeground(Color.WHITE);
			txtValidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtValidade.setBounds(767, 515, 180, 30);
			txtValidade.setOpaque(false);
			txtValidade.setBorder(null);
			txtValidade.setBackground(new Color(0, 0, 0, 0));
			frame.getContentPane().add(txtValidade);
			GerenciadorTeclado.getInstance().registrarCampoNumerico(txtValidade);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		jftCVV = new JPasswordField();
		jftCVV.setForeground(Color.WHITE);
		jftCVV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jftCVV.setBounds(1230, 499, 315, 56);
		jftCVV.setOpaque(false);
		jftCVV.setBorder(null);
		jftCVV.setBackground(new Color(0, 0, 0, 0));
		jftCVV.setEchoChar('*');

		((AbstractDocument) jftCVV.getDocument()).setDocumentFilter(new FiltroNumerico(3));

		frame.getContentPane().add(jftCVV);
		GerenciadorTeclado.getInstance().registrarCampoNumerico(jftCVV);

		String[] parcelas = {
				"1x (À vista)",
				"2x (Sem juros)",
				"3x (Sem juros)"
		};

		cboxParcelas = new JComboBox<>();
		cboxParcelas.setModel(new DefaultComboBoxModel<>(parcelas));
		cboxParcelas.setBounds(853, 582, 250, 46);
		estilizarComboBox(cboxParcelas);
		frame.getContentPane().add(cboxParcelas);

		JButton btnConfirmar = new JButton("");
		btnConfirmar.setIcon(new ImageIcon(telaCartãoCredito.class.getResource(
				"/imagens/img_pagamento_credito/img_pagamento_credito_btn_confirmar.png"
		)));
		btnConfirmar.setBounds(723, 633, 840, 95);
		btnConfirmar.setContentAreaFilled(false);
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setFocusPainted(false);
		btnConfirmar.setOpaque(false);

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarEConfirmarPagamento();
			}
		});

		frame.getContentPane().add(btnConfirmar);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(telaCartãoCredito.class.getResource(
				"/imagens/img_pagamento_credito/img_pagamento_credito_btn_voltar.png"
		)));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setOpaque(false);
		btnVoltar.setBounds(50, 50, 157, 76);

		btnVoltar.addActionListener(e -> {
			frame.dispose();

			if (emailSessao != null) {
				new oppagamento(idMensalidade, emailSessao).visivel();
			}
		});

		frame.getContentPane().add(btnVoltar);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(telaCartãoCredito.class.getResource(
				"/imagens/img_pagamento_credito/Img_cartao_credito.png"
		)));
		lblFundo.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblFundo);

		frame.getContentPane().setComponentZOrder(lblFundo, frame.getContentPane().getComponentCount() - 1);

		GerenciadorJanelas.registrarInstancia(this);
		GerenciadorJanelas.configurarJanela(frame);
	}

	private void validarEConfirmarPagamento() {
		String titular = txtTitular.getText().trim();
		String numero = txtNumeroCartao.getText().replaceAll("[^0-9]", "");
		String validade = txtValidade.getText().trim();
		String cvv = new String(jftCVV.getPassword()).trim();

		if (titular.isEmpty() || numero.isEmpty() || validade.isEmpty() || cvv.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
			return;
		}

		if (!titular.matches("[\\p{L} ]{3,50}")) {
			JOptionPane.showMessageDialog(frame, "Titular inválido.");
			return;
		}

		if (!numero.matches("\\d{16}")) {
			JOptionPane.showMessageDialog(frame, "Número do cartão inválido.");
			return;
		}

		if (!validade.matches("^(0[1-9]|1[0-2])/(2[6-9]|3[0-9])$")) {
			JOptionPane.showMessageDialog(frame, "Validade inválida.");
			return;
		}

		if (!cvv.matches("\\d{3}")) {
			JOptionPane.showMessageDialog(frame, "CVV inválido.");
			return;
		}

		confirmarPagamento();
	}

	private void confirmarPagamento() {
		Bancodedados bd = new Bancodedados();
		bd.conectar();

		if (!bd.verificar()) {
			JOptionPane.showMessageDialog(
					frame,
					"Erro ao conectar com o banco de dados.",
					"Erro",
					JOptionPane.ERROR_MESSAGE
			);
			return;
		}

		boolean pago = bd.pagarMensalidade(idMensalidade, "Crédito");
		bd.desconectar();

		if (pago) {
			JOptionPane.showMessageDialog(
					frame,
					"Pagamento no crédito confirmado com sucesso!",
					"Sucesso",
					JOptionPane.INFORMATION_MESSAGE
			);

			frame.dispose();

			if (emailSessao != null) {
				new TelaMensalidade(emailSessao).visivel();
			}

		} else {
			JOptionPane.showMessageDialog(
					frame,
					"Não foi possível confirmar o pagamento.",
					"Erro",
					JOptionPane.ERROR_MESSAGE
			);
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

	private static class FiltroNumerico extends DocumentFilter {

		private int limite;

		public FiltroNumerico(int limite) {
			this.limite = limite;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String texto, AttributeSet attr)
				throws BadLocationException {

			if (texto == null) {
				return;
			}

			String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
			String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset);

			if (novoTexto.matches("\\d*") && novoTexto.length() <= limite) {
				super.insertString(fb, offset, texto, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs)
				throws BadLocationException {

			if (texto == null) {
				return;
			}

			String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
			String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset + length);

			if (novoTexto.matches("\\d*") && novoTexto.length() <= limite) {
				super.replace(fb, offset, length, texto, attrs);
			}
		}
	}
}