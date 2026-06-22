package projeto;

import java.awt.EventQueue;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class telaPIX extends JDialog {

    private JFrame parent;
    private int idMensalidade;
    private String emailSessao;
    public telaPIX(JFrame parent, int idMensalidade, String emailSessao) {
    	super(parent, "Pagamento PIX", true);
    	this.parent = parent;
    	this.idMensalidade = idMensalidade;
    	this.emailSessao = emailSessao;
    	initialize();
    }
    
    // Fallback para main
    public telaPIX() {
    	this(null, 0, null);
    }

    public void visivel() {
        setVisible(true);
    }

    private void initialize() {
        setTitle("Pagamento PIX");
        setSize(1920, 1080);
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        // BOTÃO VOLTAR (ISAAC)
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaPIX.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(100, 77, 152, 76);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        // LÓGICA ORIGINAL DO ALUNO (HENRIQUE)
        String codigoPix = "00020126360014BR.GOV.BCB.PIX0114seupix@email.com520400005303986540510.005802BR5920Empresa Exemplo6009Fortaleza62070503***6304ABCD";

        try {
            String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?size=400x400&data=" + URLEncoder.encode(codigoPix, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton btnConfirmarPix = new JButton("");
        btnConfirmarPix.setIcon(new ImageIcon(telaPIX.class.getResource("/imagens/img_pagamento_pix/img_confirmar_pagamento.png")));
        btnConfirmarPix.setFont(new Font("Arial", Font.BOLD, 24));
        btnConfirmarPix.setBounds(757, 895, 386, 60);    
        btnConfirmarPix.setContentAreaFilled(false);
        btnConfirmarPix.setBorderPainted(false);
        btnConfirmarPix.setFocusPainted(false);
        btnConfirmarPix.addActionListener(e -> confirmarPagamento());
        getContentPane().add(btnConfirmarPix);
        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(telaPIX.class.getResource("/imagens/img_pagamento_pix/img_pagamento_pix_fundo.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        getContentPane().add(lblFundo);

        // REGISTRA PARA ESCALA E CTRL+W
        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
    private void confirmarPagamento() {

    	Bancodedados bd = new Bancodedados();
    	bd.conectar();

    	if (!bd.verificar()) {
    		JOptionPane.showMessageDialog(
    				this,
    				"Erro ao conectar com o banco de dados.",
    				"Erro",
    				JOptionPane.ERROR_MESSAGE
    		);
    		return;
    	}

    	boolean pago = bd.pagarMensalidade(idMensalidade, "PIX");
    	bd.desconectar();

    	if (pago) {
    		JOptionPane.showMessageDialog(
    				this,
    				"Pagamento PIX confirmado com sucesso!",
    				"Sucesso",
    				JOptionPane.INFORMATION_MESSAGE
    		);

    		dispose();

    		if (emailSessao != null) {
    			new TelaMensalidade(emailSessao).visivel();
    		}

    	} else {
    		JOptionPane.showMessageDialog(
    				this,
    				"Não foi possível confirmar o pagamento.",
    				"Erro",
    				JOptionPane.ERROR_MESSAGE
    		);
    	}
    }
}
