package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TelaRecibo extends JDialog {

	private String empreendimento;
	private JFrame parentFrame;

	public TelaRecibo(JFrame parent, String empreendimento) {
		super(parent, "Recibo - " + empreendimento, true);
		this.parentFrame = parent;
		this.empreendimento = empreendimento;
		initialize();
	}

	public void visivel() {
		setVisible(true);
	}

	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parentFrame);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(TelaRecibo.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBounds(10, 10, 104, 35);
		btnVoltar.addActionListener(e -> dispose());
		getContentPane().add(btnVoltar);

		JLabel lblTitulo = new JLabel("RECIBO DE PAGAMENTO");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 60, 464, 30);
		getContentPane().add(lblTitulo);

		JLabel lblDetalhes = new JLabel("Empreendimento: " + empreendimento);
		lblDetalhes.setForeground(Color.WHITE);
		lblDetalhes.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDetalhes.setBounds(50, 130, 600, 30);
		getContentPane().add(lblDetalhes);

		JLabel lblStatus = new JLabel("Status: PAGO / Confirmado");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 15));
		lblStatus.setBounds(50, 170, 400, 30);
		getContentPane().add(lblStatus);

		JLabel lblFundoRecibo = new JLabel("");
		lblFundoRecibo.setIcon(new ImageIcon(TelaRecibo.class.getResource("/imagens/fundopagamento.png")));
		lblFundoRecibo.setBounds(0, 0, 1920, 1080);
		getContentPane().add(lblFundoRecibo);

		GerenciadorJanelas.registrarInstancia(this);
		GerenciadorJanelas.configurarJanela(this);
	}
}
