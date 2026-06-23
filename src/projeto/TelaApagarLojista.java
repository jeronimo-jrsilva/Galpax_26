package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaApagarLojista {

	private JFrame frame;
	private JTextField txtPesquisa;

	private static final Color COR_TEXTO = Color.WHITE;
	private static final Color COR_PLACEHOLDER = new Color(180, 180, 180);
	private static final String PLACEHOLDER_PESQUISA = "Digite o CNPJ, e-mail ou número da sala";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaApagarLojista window = new TelaApagarLojista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void visivel() {
		this.frame.setVisible(true);
	}

	public TelaApagarLojista() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		// TECLADO VIRTUAL
		GerenciadorTeclado.getInstance().inicializar(frame);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(e -> {
			telaAdmin tel = new telaAdmin();
			tel.visivel();
			frame.dispose();
		});
		btnVoltar.setIcon(new ImageIcon(TelaApagarLojista.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setOpaque(false);
		btnVoltar.setBounds(129, 87, 196, 72);
		frame.getContentPane().add(btnVoltar);

		JButton btnApagar = new JButton("");
		btnApagar.setIcon(new ImageIcon(TelaApagarLojista.class.getResource("/imagens/img_apagar_dados/btn_apagar_Dados.png")));
		btnApagar.setBounds(798, 443, 434, 72);
		btnApagar.setContentAreaFilled(false);
		btnApagar.setBorderPainted(false);
		btnApagar.setFocusPainted(false);
		btnApagar.setOpaque(false);
		frame.getContentPane().add(btnApagar);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(297, 299, 1313, 61);
		txtPesquisa.setColumns(10);
		txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtPesquisa.setForeground(Color.WHITE);
		txtPesquisa.setOpaque(false);
		txtPesquisa.setBorder(null);
		frame.getContentPane().add(txtPesquisa);

		// FRASE DENTRO DO CAMPO
		aplicarPlaceholder(txtPesquisa, PLACEHOLDER_PESQUISA);

		// CAMPO REGISTRADO NO TECLADO VIRTUAL
		GerenciadorTeclado.getInstance().registrarCampo(txtPesquisa);

		btnApagar.addActionListener(e -> apagarLojista());

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaApagarLojista.class.getResource("/imagens/img_apagar_dados/img_fundo_Apagar.png")));
		lblNewLabel_1.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblNewLabel_1);

		GerenciadorJanelas.configurarJanela(frame);
	}

	private void aplicarPlaceholder(JTextField campo, String placeholder) {
		mostrarPlaceholder(campo, placeholder);

		campo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (campo.getText().equals(placeholder)) {
					campo.setText("");
					campo.setForeground(COR_TEXTO);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (campo.getText().trim().isEmpty()) {
					mostrarPlaceholder(campo, placeholder);
				}
			}
		});
	}

	private void mostrarPlaceholder(JTextField campo, String placeholder) {
		campo.setText(placeholder);
		campo.setForeground(COR_PLACEHOLDER);
	}

	private String pegarTexto(JTextField campo, String placeholder) {
		String texto = campo.getText().trim();

		if (texto.equals(placeholder)) {
			return "";
		}

		return texto;
	}

	private void apagarLojista() {

		String pesquisa = pegarTexto(txtPesquisa, PLACEHOLDER_PESQUISA);

		if (pesquisa.isEmpty()) {
			JOptionPane.showMessageDialog(
				frame,
				"Digite o CNPJ, e-mail ou número da sala para apagar.",
				"Aviso",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		Bancodedados banco = new Bancodedados();
		banco.conectar();

		if (!banco.verificar()) {
			JOptionPane.showMessageDialog(
				frame,
				"Não foi possível conectar ao banco de dados.",
				"Erro",
				JOptionPane.ERROR_MESSAGE
			);
			return;
		}

		boolean apagado = banco.excluirLojaPorPesquisa(pesquisa);

		banco.desconectar();

		if (apagado) {
			JOptionPane.showMessageDialog(
				frame,
				"Loja apagada com sucesso!",
				"Sucesso",
				JOptionPane.INFORMATION_MESSAGE
			);

			mostrarPlaceholder(txtPesquisa, PLACEHOLDER_PESQUISA);
		}
	}
}