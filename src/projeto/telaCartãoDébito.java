package projeto;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;

public class telaCartãoDébito extends JDialog {

    private JTextField txtTitular;
    private JTextField txtNumeroCartao;
    private JFormattedTextField txtValidade;
    private JPasswordField jftCVV;
    private MaskFormatter maskValidade;

    private JFrame parentFrame;

    public telaCartãoDébito(JFrame parent) {
        super(parent, "Cartão de Débito", true);
        this.parentFrame = parent;
        initialize();
    }

    public telaCartãoDébito() {
        this(null);
    }

    public void visivel() {
        setVisible(true);
    }

    private void initialize() {
        setTitle("Compra no Débito");
        setSize(1920, 1080);
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        if (parentFrame != null) {
            GerenciadorTeclado.getInstance().inicializar(parentFrame);
        }

        try {
            maskValidade = new MaskFormatter("##/##");
            maskValidade.setPlaceholderCharacter('_');
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource(
                "/imagens/img_pagamento_debito/img_pagamento_debito_btn_voltar.png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 151, 64);
        btnVoltar.addActionListener(e -> dispose());

        JLabel lblCamuflagem = new JLabel();
        lblCamuflagem.setBounds(673, 711, 200, 50);
        lblCamuflagem.setOpaque(true);
        lblCamuflagem.setBackground(Color.decode("#000721"));
        getContentPane().add(lblCamuflagem);
        getContentPane().add(btnVoltar);

        jftCVV = new JPasswordField();
        jftCVV.setHorizontalAlignment(SwingConstants.CENTER);
        jftCVV.setFont(new Font("Tahoma", Font.PLAIN, 30));
        jftCVV.setForeground(new Color(255, 255, 255));
        jftCVV.setBounds(1233, 501, 309, 64);
        jftCVV.setOpaque(false);
        jftCVV.setBorder(null);
        jftCVV.setEchoChar('*');

        ((AbstractDocument) jftCVV.getDocument()).setDocumentFilter(new FiltroNumerico(3));

        getContentPane().add(jftCVV);

        // CVV: teclado numérico
        GerenciadorTeclado.getInstance().registrarCampoNumerico(jftCVV);

        txtTitular = new JTextField();
        txtTitular.setFont(new Font("Tahoma", Font.PLAIN, 30));
        txtTitular.setForeground(new Color(255, 255, 255));
        txtTitular.setBounds(772, 250, 820, 50);
        txtTitular.setOpaque(false);
        txtTitular.setBorder(null);
        getContentPane().add(txtTitular);

        // Titular: teclado completo
        GerenciadorTeclado.getInstance().registrarCampo(txtTitular);

        txtNumeroCartao = new JTextField();
        txtNumeroCartao.setFont(new Font("Tahoma", Font.PLAIN, 30));
        txtNumeroCartao.setForeground(new Color(255, 255, 255));
        txtNumeroCartao.setBounds(772, 377, 820, 57);
        txtNumeroCartao.setOpaque(false);
        txtNumeroCartao.setBorder(null);

        ((AbstractDocument) txtNumeroCartao.getDocument()).setDocumentFilter(new FiltroNumerico(16));

        getContentPane().add(txtNumeroCartao);

        // Número do cartão: teclado numérico
        GerenciadorTeclado.getInstance().registrarCampoNumerico(txtNumeroCartao);

        txtValidade = new JFormattedTextField(maskValidade);
        txtValidade.setHorizontalAlignment(SwingConstants.CENTER);
        txtValidade.setFont(new Font("Tahoma", Font.PLAIN, 30));
        txtValidade.setForeground(new Color(255, 255, 255));
        txtValidade.setBounds(762, 500, 157, 64);
        txtValidade.setOpaque(false);
        txtValidade.setBorder(null);
        getContentPane().add(txtValidade);

        // Validade: teclado numérico
        GerenciadorTeclado.getInstance().registrarCampoNumerico(txtValidade);

        JButton btnConfirmar = new JButton("");
        btnConfirmar.setContentAreaFilled(false);
        btnConfirmar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource(
                "/imagens/img_pagamento_debito/img_pagamento_debito_btn_confirmar.png")));
        btnConfirmar.setBounds(816, 624, 715, 50);
        btnConfirmar.setOpaque(false);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String titular = txtTitular.getText().trim();
                String numCartao = txtNumeroCartao.getText().trim();
                String validade = txtValidade.getText().trim();
                String CVV = new String(jftCVV.getPassword()).trim();

                if (!titular.isEmpty() && !numCartao.isEmpty() && !validade.isEmpty() && !CVV.isEmpty()) {

                    if (titular.matches("[\\p{L} ]{3,50}")) {

                        if (numCartao.matches("\\d{16}")) {

                            if (validade.matches("^(0[1-9]|1[0-2])/(2[6-9]|3[0-9])$")) {

                                if (CVV.matches("\\d{3}")) {
                                    JOptionPane.showMessageDialog(null, "Débito Confirmado!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "CVV inválido!", "Aviso", -1);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Validade inválida!", "Aviso", -1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Número do cartão inválido!", "Aviso", -1);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Titular inválido!", "Aviso", -1);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Campos em Branco!", "Aviso", -1);
                }
            }
        });
        getContentPane().add(btnConfirmar);

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(telaCartãoDébito.class.getResource(
                "/imagens/img_pagamento_debito/Img_cartao_debito.png")));
        lblFundo.setBounds(0, 0, 1930, 1080);
        getContentPane().add(lblFundo);

        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }

    private static class FiltroNumerico extends javax.swing.text.DocumentFilter {

        private int limite;

        public FiltroNumerico(int limite) {
            this.limite = limite;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String texto, AttributeSet attr)
                throws BadLocationException {

            if (texto == null) {
                return;
            }

            String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset);

            if (novoTexto.matches("\\d*") && novoTexto.length() <= limite) {
                super.insertString(fb, offset, texto, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs)
                throws BadLocationException {

            if (texto == null) {
                return;
            }

            String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset + length);

            if (novoTexto.matches("\\d*") && novoTexto.length() <= limite) {
                super.replace(fb, offset, length, texto, attrs);
            }
        }
    }
}