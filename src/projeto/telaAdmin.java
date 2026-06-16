package projeto;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class telaAdmin {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new telaAdmin().visivel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor vazio
	public telaAdmin() {
	}

	public void visivel() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// BOTÃO SAIR/VOLTAR
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(
				telaAdmin.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(50, 50, 104, 35);

		btnVoltar.addActionListener(e -> {

			int opcao = JOptionPane.showConfirmDialog(
					frame,
					"Deseja sair e voltar para a tela de login?",
					"Confirmação",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (opcao == JOptionPane.YES_OPTION) {
				new Login().visivel();
				frame.dispose();
			}
		});

		frame.getContentPane().add(btnVoltar);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(
				new ImageIcon(telaAdmin.class.getResource("/imagens/Slide 16_9 - 1 (2).png")));
		lblFundo.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblFundo);

		// REGISTRA PARA CTRL+W (caso utilize o mesmo padrão das outras telas)
		GerenciadorJanelas.registrarInstancia(this);

		// CONFIGURA A JANELA
		GerenciadorJanelas.configurarJanela(frame);
		frame.setVisible(true);
	}
}