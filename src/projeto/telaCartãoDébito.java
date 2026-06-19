package projeto;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
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
    private JTextField txtValidade;
    private JPasswordField jftCVV;
    private MaskFormatter maskValidade;

    public telaCartãoDébito(JFrame parent) {
        super(parent, "Cartão de Débito", true);
        initialize();
    }
    
    public telaCartãoDébito() { this(null); }

    public void visivel() { setVisible(true); }

    private void initialize() {
        setTitle("Compra no Débito");
        setSize(1920, 1080);
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        try {
        	maskValidade = new MaskFormatter("##/##");
        } catch(Exception e) {
        	
        }
        
        // GerenciadorTeclado.getInstance().inicializar(this); // JDialog não é JFrame

        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource("/imagens/img_pagamento_debito/img_pagamento_debito_btn_voltar.png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
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
        jftCVV.setBounds(1261, 614, 309, 64);
        jftCVV.setOpaque(false);
        jftCVV.setBorder(null);
        PlainDocument doc = (PlainDocument) jftCVV.getDocument();
        doc.setDocumentFilter(new DocumentFilter(3));
        jftCVV.setEchoChar('*');
        getContentPane().add(jftCVV);
        GerenciadorTeclado.getInstance().registrarCampo(jftCVV);

        txtTitular = new JTextField();
        txtTitular.setFont(new Font("Tahoma", Font.PLAIN, 30));
        txtTitular.setForeground(new Color(255, 255, 255));
        txtTitular.setBounds(762, 307, 856, 72);
        txtTitular.setOpaque(false);
        txtTitular.setBorder(null);
        getContentPane().add(txtTitular);
        GerenciadorTeclado.getInstance().registrarCampo(txtTitular);
        
        txtNumeroCartao = new JTextField();
        txtNumeroCartao.setFont(new Font("Tahoma", Font.PLAIN, 30));
        txtNumeroCartao.setForeground(new Color(255, 255, 255));
        txtNumeroCartao.setBounds(762, 459, 856, 72);
        txtNumeroCartao.setOpaque(false);
        txtNumeroCartao.setBorder(null);
        getContentPane().add(txtNumeroCartao);
        GerenciadorTeclado.getInstance().registrarCampo(txtNumeroCartao);
        
        txtValidade = new JFormattedTextField(maskValidade);
        txtValidade.setHorizontalAlignment(SwingConstants.CENTER);
        txtValidade.setFont(new Font("Tahoma", Font.PLAIN, 30));
        txtValidade.setForeground(new Color(255, 255, 255));
        txtValidade.setBounds(762, 614, 382, 64);
        txtValidade.setOpaque(false);
        txtValidade.setBorder(null);
        getContentPane().add(txtValidade);
        GerenciadorTeclado.getInstance().registrarCampo(txtValidade);

        JButton btnConfirmar = new JButton("");
        btnConfirmar.setContentAreaFilled(false);
        btnConfirmar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource("/imagens/img_pagamento_debito/img_pagamento_debito_btn_confirmar.png")));
        btnConfirmar.setBounds(820, 913, 715, 50);
        btnConfirmar.setOpaque(false);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String titular = txtTitular.getText();
        		String numCartão = txtNumeroCartao.getText();
        		String validade = txtValidade.getText();
        		String CVV = jftCVV.getText();
        		if(titular.isEmpty()==false&&numCartão.isEmpty()==false&&validade.isEmpty()==false&&CVV.isEmpty()==false) {
        			if(titular.matches("[\\p{L} ]{3,50}")) {
        				if(numCartão.matches("(\\d{16}|\\d{4} \\d{4} \\d{4} \\d{4})")) {
        					if(validade.matches("^(0[1-9]|1[0-2])/(2[6-9]|3[0-9])$")) {
        						if(CVV.matches("\\d{3}")) {
        							JOptionPane.showMessageDialog(null, "Débito Confirmado!");
        						} else {
        							JOptionPane.showMessageDialog(null, "CVV inválido!","Aviso",-1);
        						}
        					} else {
        						JOptionPane.showMessageDialog(null, "Validade inválida!","Aviso",-1);
        					}
        				} else {
        					JOptionPane.showMessageDialog(null, "Numero do cartão inválido!","Aviso",-1);
        				}
        			} else {
        				JOptionPane.showMessageDialog(null, "Titular inválido!","Aviso",-1);
        			}
        		} else {
        			JOptionPane.showMessageDialog(null, "Campos em Branco!","Aviso",-1);
        		}
        	}
        });
        getContentPane().add(btnConfirmar);

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(telaCartãoDébito.class.getResource("/imagens/img_pagamento_debito/img_pagamento_debito_fundo.png")));
        lblFundo.setBounds(0, 0, 1930, 1080);
        getContentPane().add(lblFundo);

        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
}
// Adicione a classe DocumentFilter se ela não existir em outro lugar
class DocumentFilter extends javax.swing.text.DocumentFilter {
    private int maxLength;
    public DocumentFilter(int max) {
        this.maxLength = max;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if ((fb.getDocument().getLength() + text.length() - length) <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
