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
import java.awt.Rectangle;
import java.awt.Point;

public class telaEstacionamento {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEstacionamento window = new telaEstacionamento();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public telaEstacionamento() {
		initialize();
	}
	
	public void visivel() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// PADRÃO: TOP-LEFT (VOLTAR)
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(10, 11, 104, 35);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaComum("loja01@galpax.com").visivel(); // Fallback sessão
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnVoltar);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/fundo tela cad carro.png")));
		lblFundo.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblFundo);

        // APLICA GERENCIADOR (CTRL+W)
        GerenciadorJanelas.configurarJanela(frame);
        frame.setVisible(true);
	}
}
