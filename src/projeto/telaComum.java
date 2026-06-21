package projeto;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.ParseException;
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
		btnSair.setIcon(new ImageIcon(telaComum.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
		btnSair.setContentAreaFilled(false);
		btnSair.setBorderPainted(false);
		btnSair.setBounds(51, 91, 160, 64);

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(null, "Sessão encerrada. Voltando para a tela de login.");
		        
		        
		        Login telaLogin = new Login(); // RETORNO PARA TELA LOGIN
		        telaLogin.visivel(); 
		        
		        frame.dispose(); // FECHA TELA ATUAL
		    }
		});

		frame.getContentPane().add(btnSair);

		JButton btnPagarMensalidade = new JButton("");
		btnPagarMensalidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaMensalidade(emailSessao).visivel();
				frame.dispose();
			}
		});

		btnPagarMensalidade.setIcon(
				new ImageIcon(telaComum.class.getResource("/imagens/img_menu_user/img_menu_user_btn_mensalidade.png")));
		btnPagarMensalidade.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnPagarMensalidade.setBounds(825, 464, 397, 70);
		btnPagarMensalidade.setOpaque(false);
		btnPagarMensalidade.setContentAreaFilled(false);
		btnPagarMensalidade.setBorderPainted(false);
		frame.getContentPane().add(btnPagarMensalidade);

		JButton btnEstacionamento = new JButton("");
		btnEstacionamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new telaEstacionamento(frame);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		});

		btnEstacionamento.setIcon(
				new ImageIcon(telaComum.class.getResource("/imagens/img_menu_user/img_menu_user_btn_estacionamento.png")));
		btnEstacionamento.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnEstacionamento.setBounds(845, 555, 351, 70);
		btnEstacionamento.setOpaque(false);
		btnEstacionamento.setContentAreaFilled(false);
		btnEstacionamento.setBorderPainted(false);
		frame.getContentPane().add(btnEstacionamento);

		JLabel lblSessao = new JLabel("Lojista: " + emailSessao);
		lblSessao.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSessao.setBounds(1600, 50, 500, 30);
		frame.getContentPane().add(lblSessao);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(telaComum.class.getResource("/imagens/img_menu_user/img_menu_user_fundo.png")));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblNewLabel);

		GerenciadorJanelas.configurarJanela(frame);
		frame.setVisible(true);
	}
}
