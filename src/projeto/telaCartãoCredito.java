package projeto;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;

public class telaCartãoCredito extends JDialog {

    private JTextField txtTitular;
    private JFormattedTextField txtNumeroCartao;
    private JFormattedTextField txtValidade;
    private JPasswordField jftCVV;

    public telaCartãoCredito(JFrame parent) {
        super(parent, "Cartão de Crédito", true);
        initialize();
    }

    public telaCartãoCredito() {
        this(null);
    }

    public void visivel() {
        setVisible(true);
    }

    private void initialize() {
        setTitle("Compra no Crédito");
        setSize(1920, 1080);
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        JButton btnConfirmar = new JButton("");
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Pagamento Confirmado com Sucesso!");
        	}
        });
        		
        
        		String[] parcelas = {"1x (À vista)", "2x (Sem juros)", "3x (Sem juros)"};
                JComboBox<String> cboxParcelas = new JComboBox<>(parcelas);
                cboxParcelas.setBounds(858, 723, 88, 22);
                getContentPane().add(cboxParcelas);
                                            
        
                txtTitular = new JTextField();
                txtTitular.setFont(new Font("Tahoma", Font.PLAIN, 20));
                txtTitular.setForeground(new Color(255, 255, 255));
                txtTitular.setBounds(757, 327, 700, 30);
                
                txtTitular.setOpaque(false);
                txtTitular.setBorder(null); //
                txtTitular.setBackground(new Color(0, 0, 0, 0));
                
                getContentPane().add(txtTitular);
                GerenciadorTeclado.getInstance().registrarCampo(txtTitular);
        btnConfirmar.setIcon(new ImageIcon(telaCartãoCredito.class.getResource("/imagens/img_pagamento_credito/img_pagamento_credito_btn_confirmar.png")));
        btnConfirmar.setBounds(757, 899, 780, 70);
        getContentPane().add(btnConfirmar);

        try {
            MaskFormatter mascaraCartao = new MaskFormatter("#### #### #### ####");
            mascaraCartao.setPlaceholderCharacter('_');
            txtNumeroCartao = new JFormattedTextField(mascaraCartao);
            txtNumeroCartao.setForeground(new Color(255, 255, 255));
            txtNumeroCartao.setFont(new Font("Tahoma", Font.PLAIN, 20));
            txtNumeroCartao.setBounds(757, 482, 700, 30);
            
            txtNumeroCartao.setOpaque(false);
            txtNumeroCartao.setBorder(null); //
            txtNumeroCartao.setBackground(new Color(0, 0, 0, 0));
            
            getContentPane().add(txtNumeroCartao);
            GerenciadorTeclado.getInstance().registrarCampo(txtNumeroCartao);

            MaskFormatter mascaraValidade = new MaskFormatter("##/##");
            mascaraValidade.setPlaceholderCharacter('_');
            txtValidade = new JFormattedTextField(mascaraValidade);
            txtValidade.setForeground(new Color(255, 255, 255));
            txtValidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
            txtValidade.setBounds(757, 633, 180, 30);
            
            txtValidade.setOpaque(false);
            txtValidade.setBorder(null); //
            txtValidade.setBackground(new Color(0, 0, 0, 0));
            
            getContentPane().add(txtValidade);
            GerenciadorTeclado.getInstance().registrarCampo(txtValidade);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        jftCVV = new JPasswordField();
        jftCVV.setForeground(new Color(255, 255, 255));
        jftCVV.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jftCVV.setBounds(1256, 633, 150, 30);
        
        jftCVV.setOpaque(false);
        jftCVV.setBorder(null); //
        jftCVV.setBackground(new Color(0, 0, 0, 0));
        
        PlainDocument doc = (PlainDocument) jftCVV.getDocument();
        doc.setDocumentFilter(new DocumentFilter(3)); // Limita para 3 caracteres
        jftCVV.setEchoChar('*');
        getContentPane().add(jftCVV);
        GerenciadorTeclado.getInstance().registrarCampo(jftCVV);

        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaCartãoCredito.class.getResource("/imagens/img_pagamento_credito/img_pagamento_credito_btn_voltar.png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        // RESTANTE DA UI...
        // ... (labels, combobox, botão de confirmar, fundo, etc. permanecem os mesmos)

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(
                new ImageIcon(telaCartãoCredito.class.getResource("/imagens/img_pagamento_credito/img_pagamento_credito_fundo.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        getContentPane().add(lblFundo);

        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
}
