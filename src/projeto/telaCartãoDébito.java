package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;

public class telaCartãoDébito {

	private JFrame frame;

	private JTextField txtTitular;
	private JTextField txtNumeroCartao;
	private JFormattedTextField txtValidade;
	private JPasswordField jftCVV;
	private MaskFormatter maskValidade;

	private int idMensalidade;
	private String emailSessao;

	public telaCartãoDébito(JFrame parent, int idMensalidade, String emailSessao) {
		this.idMensalidade = idMensalidade;
		this.emailSessao = emailSessao;
		initialize();
	}

	public telaCartãoDébito() {
		this(null, 0, null);
	}

	public void visivel() {
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Compra no Débito");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		GerenciadorTeclado.getInstance().inicializar(frame);

		try {
			maskValidade = new MaskFormatter("##/##");
			maskValidade.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lblCamuflagem = new JLabel();
		lblCamuflagem.setBounds(673, 711, 200, 50);
		lblCamuflagem.setOpaque(true);
		lblCamuflagem.setBackground(Color.decode("#000721"));
		frame.getContentPane().add(lblCamuflagem);

		jftCVV = new JPasswordField();
		jftCVV.setHorizontalAlignment(SwingConstants.CENTER);
		jftCVV.setFont(new Font("Tahoma", Font.PLAIN, 30));
		jftCVV.setForeground(Color.WHITE);
		jftCVV.setBounds(1233, 501, 309, 64);
		jftCVV.setOpaque(false);
		jftCVV.setBorder(null);
		jftCVV.setEchoChar('*');

		((AbstractDocument) jftCVV.getDocument()).setDocumentFilter(new FiltroNumerico(3));

		frame.getContentPane().add(jftCVV);
		GerenciadorTeclado.getInstance().registrarCampoNumerico(jftCVV);

		txtTitular = new JTextField();
		txtTitular.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtTitular.setForeground(Color.WHITE);
		txtTitular.setBounds(772, 250, 820, 50);
		txtTitular.setOpaque(false);
		txtTitular.setBorder(null);
		frame.getContentPane().add(txtTitular);
		GerenciadorTeclado.getInstance().registrarCampo(txtTitular);

		txtNumeroCartao = new JTextField();
		txtNumeroCartao.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtNumeroCartao.setForeground(Color.WHITE);
		txtNumeroCartao.setBounds(772, 377, 820, 57);
		txtNumeroCartao.setOpaque(false);
		txtNumeroCartao.setBorder(null);

		((AbstractDocument) txtNumeroCartao.getDocument()).setDocumentFilter(new FiltroNumerico(16));

		frame.getContentPane().add(txtNumeroCartao);
		GerenciadorTeclado.getInstance().registrarCampoNumerico(txtNumeroCartao);

		txtValidade = new JFormattedTextField(maskValidade);
		txtValidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtValidade.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtValidade.setForeground(Color.WHITE);
		txtValidade.setBounds(762, 500, 157, 64);
		txtValidade.setOpaque(false);
		txtValidade.setBorder(null);
		frame.getContentPane().add(txtValidade);
		GerenciadorTeclado.getInstance().registrarCampoNumerico(txtValidade);

		JButton btnConfirmar = new JButton("");
		btnConfirmar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource(
				"/imagens/img_pagamento_debito/img_pagamento_debito_btn_confirmar.png"
		)));
		btnConfirmar.setBounds(816, 624, 715, 50);
		btnConfirmar.setContentAreaFilled(false);
		btnConfirmar.setOpaque(false);
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setFocusPainted(false);

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarEConfirmarPagamento();
			}
		});

		frame.getContentPane().add(btnConfirmar);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource(
				"/imagens/img_pagamento_debito/img_pagamento_debito_btn_voltar.png"
		)));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setOpaque(false);
		btnVoltar.setBounds(50, 50, 151, 64);

		btnVoltar.addActionListener(e -> {
			frame.dispose();

			if (emailSessao != null) {
				new oppagamento(idMensalidade, emailSessao).visivel();
			}
		});

		frame.getContentPane().add(btnVoltar);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(telaCartãoDébito.class.getResource(
				"/imagens/img_pagamento_debito/Img_cartao_debito.png"
		)));
		lblFundo.setBounds(0, 0, 1930, 1080);
		frame.getContentPane().add(lblFundo);

		frame.getContentPane().setComponentZOrder(lblFundo, frame.getContentPane().getComponentCount() - 1);

		GerenciadorJanelas.registrarInstancia(this);
		GerenciadorJanelas.configurarJanela(frame);
	}

	private void validarEConfirmarPagamento() {
		String titular = txtTitular.getText().trim();
		String numCartao = txtNumeroCartao.getText().trim();
		String validade = txtValidade.getText().trim();
		String cvv = new String(jftCVV.getPassword()).trim();

		if (titular.isEmpty() || numCartao.isEmpty() || validade.isEmpty() || cvv.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Campos em branco!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!titular.matches("[\\p{L} ]{3,50}")) {
			JOptionPane.showMessageDialog(frame, "Titular inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!numCartao.matches("\\d{16}")) {
			JOptionPane.showMessageDialog(frame, "Número do cartão inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!validade.matches("^(0[1-9]|1[0-2])/(2[6-9]|3[0-9])$")) {
			JOptionPane.showMessageDialog(frame, "Validade inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!cvv.matches("\\d{3}")) {
			JOptionPane.showMessageDialog(frame, "CVV inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
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
					"Pagamento no débito confirmado com sucesso!",
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

	private static class FiltroNumerico extends javax.swing.text.DocumentFilter {

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