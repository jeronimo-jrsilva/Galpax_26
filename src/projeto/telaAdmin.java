package projeto;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaAdmin {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaAdmin window = new telaAdmin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public telaAdmin() {
		initialize();
	}
	
	public void visivel() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// PADRÃO: TOP-LEFT (SAIR/VOLTAR)
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(50, 50, 104, 35);
		btnVoltar.addActionListener(e -> {
			new Login().visivel();
			frame.dispose();
		});
		frame.getContentPane().add(btnVoltar);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/Slide 16_9 - 1 (2).png")));
		lblFundo.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblFundo);

        // APLICA GERENCIADOR (CTRL+W)
        GerenciadorJanelas.configurarJanela(frame);
        frame.setVisible(true);
	}
}
