package projeto;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaCartãoDébito extends JDialog {

    private JTextField txtTitular;
    private JTextField txtNumeroCartao;
    private JTextField txtValidade;
    private JPasswordField jftCVV;

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

        // BOTÃO VOLTAR (ISAAC)
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);
        
        JPasswordField jftCVV = new JPasswordField();
        jftCVV.setBounds(1102, 425, 150, 30);

        PlainDocument doc = new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr)
                    throws BadLocationException {
                if (str == null) return;
                if ((getLength() + str.length()) <= 3) {
                    super.insertString(offset, str, attr);
                }
            }
        };

        jftCVV.setDocument(doc);
        jftCVV.setEchoChar('*'); // depois do setDocument
        getContentPane().add(jftCVV);

        // UI ORIGINAL DO ALUNO (HENRIQUE)
        JLabel lblTitulo = new JLabel("COMPRA NO CARTÃO DE DÉBITO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(610, 80, 700, 40);
        getContentPane().add(lblTitulo);

        JLabel lblTitular = new JLabel("Nome do Titular:");
        lblTitular.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitular.setForeground(Color.WHITE);
        lblTitular.setBounds(455, 216, 200, 30);
        getContentPane().add(lblTitular);

        txtTitular = new JTextField();
        txtTitular.setBounds(685, 216, 700, 30);
        getContentPane().add(txtTitular);

        JLabel lblNumero = new JLabel("Número do Cartão:");
        lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNumero.setForeground(Color.WHITE);
        lblNumero.setBounds(435, 316, 220, 30);
        getContentPane().add(lblNumero);

        txtNumeroCartao = new JTextField();
        txtNumeroCartao.setBounds(685, 316, 700, 30);
        getContentPane().add(txtNumeroCartao);

        JLabel lblValidade = new JLabel("Validade:");
        lblValidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblValidade.setForeground(Color.WHITE);
        lblValidade.setBounds(643, 425, 100, 30);
        getContentPane().add(lblValidade);

        txtValidade = new JTextField();
        txtValidade.setBounds(743, 425, 180, 30);
        getContentPane().add(txtValidade);

        JLabel lblCVV = new JLabel("CVV:");
        lblCVV.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCVV.setForeground(Color.WHITE);
        lblCVV.setBounds(1043, 425, 60, 30);
        getContentPane().add(lblCVV);

        JButton btnConfirmar = new JButton("");
        btnConfirmar.setContentAreaFilled(false);
        btnConfirmar.setIcon(new ImageIcon(telaCartãoDébito.class.getResource("/imagens/botoes_isaac/_comicLight small Base (7).png")));
        btnConfirmar.setBounds(626, 796, 220, 50);
        btnConfirmar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Débito Confirmado!"));
        getContentPane().add(btnConfirmar);

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(telaCartãoDébito.class.getResource("/imagens/fundopagamento.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        getContentPane().add(lblFundo);

        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
}
