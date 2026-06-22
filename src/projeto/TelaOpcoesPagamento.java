package projeto;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Toolkit;

public class TelaOpcoesPagamento extends JDialog {

	private String empreendimento;
	private JFrame parentFrame;

	public TelaOpcoesPagamento(JFrame parent, String empreendimento) {
		super(parent, "Formas de Pagamento", true);
		this.parentFrame = parent;
		this.empreendimento = empreendimento;
		initialize();
	}

	public void visivel() {
		setVisible(true);
	}

	private void initialize() {
		setUndecorated(true); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parentFrame);

		JButton btnVoltar = new JButton("");
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
		btnVoltar.setBounds(26, 33, 156, 64);
		btnVoltar.addActionListener(e -> dispose());
		getContentPane().add(btnVoltar);

		JButton btnPix = new JButton("");
		btnPix.setContentAreaFilled(false);
		btnPix.setBorderPainted(false);
		btnPix.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/img_opcoes_pagamento/btn_opcao_pix.png")));
		btnPix.setBounds(875, 523, 340, 64);
		btnPix.addActionListener(e -> {
			dispose();
			new telaPIX(parentFrame).visivel();
		});
		getContentPane().add(btnPix);

		// BOTÃO CRÉDITO
		JButton btnCredito = new JButton("");
		btnCredito.setContentAreaFilled(false);
		btnCredito.setBorderPainted(false);
		btnCredito.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/img_opcoes_pagamento/btn_opcao_credito.png")));
		btnCredito.setBounds(864, 598, 363, 64);
		btnCredito.addActionListener(e -> {
			dispose();
			new telaCartãoCredito(parentFrame).visivel();
		});
		getContentPane().add(btnCredito);

		// BOTÃO DÉBITO
		JButton btnDebito = new JButton("");
		btnDebito.setContentAreaFilled(false);
		btnDebito.setBorderPainted(false);
		btnDebito.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/img_opcoes_pagamento/btn_opcao_debito.png")));
		btnDebito.setBounds(864, 655, 363, 91);
		btnDebito.addActionListener(e -> {
			dispose();
			new telaCartãoDébito(parentFrame).visivel();
		});
		getContentPane().add(btnDebito);
		
		JLabel lblFundoPagamento = new JLabel("");
		lblFundoPagamento.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/img_opcoes_pagamento/img_fundo_pagamento.png")));
		lblFundoPagamento.setBounds(0, 0, 1920, 1080);
		getContentPane().add(lblFundoPagamento);

		GerenciadorJanelas.registrarInstancia(this);
		GerenciadorJanelas.configurarJanela(this);
	}
}
