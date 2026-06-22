package projeto;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class oppagamento {

	private JFrame frame;
	private int idMensalidade;
	private String emailSessao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					oppagamento window = new oppagamento();
					window.visivel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor usado apenas para teste
	public oppagamento() {
		this.idMensalidade = 0;
		this.emailSessao = null;
		initialize();
	}

	// Construtor correto para usar vindo da TelaMensalidade
	public oppagamento(int idMensalidade, String emailSessao) {
		this.idMensalidade = idMensalidade;
		this.emailSessao = emailSessao;
		initialize();
	}

	public void visivel() {
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		JButton btnVoltar = new JButton("");
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setIcon(new ImageIcon(oppagamento.class.getResource(
				"/imagens/img_mensalidade/img_mensalidade_btn_voltar.png"
		)));
		btnVoltar.setBounds(26, 33, 156, 64);

		btnVoltar.addActionListener(e -> {
			frame.dispose();

			if (emailSessao != null) {
				new TelaMensalidade(emailSessao).visivel();
			}
		});

		frame.getContentPane().add(btnVoltar);

		JButton btnPix = new JButton("");
		btnPix.setContentAreaFilled(false);
		btnPix.setBorderPainted(false);
		btnPix.setFocusPainted(false);
		btnPix.setIcon(new ImageIcon(oppagamento.class.getResource(
				"/imagens/img_opcoes_pagamento/btn_opcao_pix.png"
		)));
		btnPix.setBounds(875, 523, 340, 64);

		btnPix.addActionListener(e -> {
			telaPIX tel = new telaPIX(frame, idMensalidade, emailSessao);
			tel.visivel();
			frame.dispose();
		});

		frame.getContentPane().add(btnPix);

		JButton btnCredito = new JButton("");
		btnCredito.setContentAreaFilled(false);
		btnCredito.setBorderPainted(false);
		btnCredito.setFocusPainted(false);
		btnCredito.setIcon(new ImageIcon(oppagamento.class.getResource(
				"/imagens/img_opcoes_pagamento/btn_opcao_credito.png"
		)));
		btnCredito.setBounds(864, 598, 363, 64);

		btnCredito.addActionListener(e -> {
		    telaCartãoCredito tel = new telaCartãoCredito(null, idMensalidade, emailSessao);
		    tel.visivel();
		    frame.dispose();
		});

		frame.getContentPane().add(btnCredito);

		JButton btnDebito = new JButton("");
		btnDebito.setContentAreaFilled(false);
		btnDebito.setBorderPainted(false);
		btnDebito.setFocusPainted(false);
		btnDebito.setIcon(new ImageIcon(oppagamento.class.getResource(
				"/imagens/img_opcoes_pagamento/btn_opcao_debito.png"
		)));
		btnDebito.setBounds(864, 655, 363, 91);

		btnDebito.addActionListener(e -> {
			telaCartãoDébito tel = new telaCartãoDébito(frame, idMensalidade, emailSessao);
			tel.visivel();
			frame.dispose();
		});

		frame.getContentPane().add(btnDebito);

		JLabel lblFundoPagamento = new JLabel("");
		lblFundoPagamento.setIcon(new ImageIcon(oppagamento.class.getResource(
				"/imagens/img_opcoes_pagamento/img_fundo_pagamento.png"
		)));
		lblFundoPagamento.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblFundoPagamento);

		GerenciadorJanelas.registrarInstancia(this);
		GerenciadorJanelas.configurarJanela(frame);
	}
}