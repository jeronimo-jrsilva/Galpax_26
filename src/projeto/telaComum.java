package projeto;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaComum {

	private JFrame frame;
	private String emailSessao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new telaComum("loja01@galpax.com").visivel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public telaComum(String email) {
		this.emailSessao = email;
	}

	public void visivel() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// REGISTRA PARA CTRL+W
		GerenciadorJanelas.registrarInstancia(this);

		// BOTÃO SAIR
		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(
				telaComum.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnSair.setContentAreaFilled(false);
		btnSair.setBorderPainted(false);
		btnSair.setBounds(1766, 50, 104, 35);

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int opcao = JOptionPane.showConfirmDialog(
						frame,
						"Deseja voltar para a tela de login?",
						"Confirmação",
						JOptionPane.YES_NO_OPTION);

				if (opcao == JOptionPane.YES_OPTION) {
					new Login().visivel();
					frame.dispose();
				}
			}
		});

		frame.getContentPane().add(btnSair);

		JButton btnPagarMensalidade = new JButton("Pagar mensalidade");
		btnPagarMensalidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMensalidade(emailSessao).visivel();
				frame.dispose();
			}
		});

		btnPagarMensalidade.setIcon(
				new ImageIcon(telaComum.class.getResource("/imagens/botao_comun1.png")));
		btnPagarMensalidade.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnPagarMensalidade.setBounds(838, 306, 351, 55);
		frame.getContentPane().add(btnPagarMensalidade);

		JButton btnEstacionamento = new JButton("Estacionamento");
		btnEstacionamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaEstacionamento().visivel();
				frame.dispose();
			}
		});

		btnEstacionamento.setIcon(
				new ImageIcon(telaComum.class.getResource("/imagens/botao_comun2.png")));
		btnEstacionamento.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnEstacionamento.setBounds(838, 419, 351, 55);
		frame.getContentPane().add(btnEstacionamento);

		JLabel lblSessao = new JLabel("Lojista: " + emailSessao);
		lblSessao.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSessao.setBounds(50, 50, 500, 30);
		frame.getContentPane().add(lblSessao);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(telaComum.class.getResource("/imagens/telamenuprincipal.png")));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblNewLabel);

		GerenciadorJanelas.configurarJanela(frame);
		frame.setVisible(true);
	}
}
