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

        // INICIALIZA O TECLADO UNIVERSAL
        // GerenciadorTeclado.getInstance().inicializar(this); // JDialog não é JFrame

        JLabel lblTitulo = new JLabel("COMPRA NO CARTÃO DE CRÉDITO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(610, 80, 700, 40);
        getContentPane().add(lblTitulo);

        txtTitular = new JTextField();
        txtTitular.setBounds(685, 216, 700, 30);
        getContentPane().add(txtTitular);
        GerenciadorTeclado.getInstance().registrarCampo(txtTitular);

        try {
            MaskFormatter mascaraCartao = new MaskFormatter("#### #### #### ####");
            mascaraCartao.setPlaceholderCharacter('_');
            txtNumeroCartao = new JFormattedTextField(mascaraCartao);
            txtNumeroCartao.setBounds(685, 316, 700, 30);
            getContentPane().add(txtNumeroCartao);
            GerenciadorTeclado.getInstance().registrarCampo(txtNumeroCartao);

            MaskFormatter mascaraValidade = new MaskFormatter("##/##");
            mascaraValidade.setPlaceholderCharacter('_');
            txtValidade = new JFormattedTextField(mascaraValidade);
            txtValidade.setBounds(743, 425, 180, 30);
            getContentPane().add(txtValidade);
            GerenciadorTeclado.getInstance().registrarCampo(txtValidade);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        jftCVV = new JPasswordField();
        jftCVV.setBounds(1103, 425, 150, 30);
        PlainDocument doc = (PlainDocument) jftCVV.getDocument();
        doc.setDocumentFilter(new DocumentFilter(3)); // Limita para 3 caracteres
        jftCVV.setEchoChar('*');
        getContentPane().add(jftCVV);
        GerenciadorTeclado.getInstance().registrarCampo(jftCVV);

        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaCartãoCredito.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        // RESTANTE DA UI...
        // ... (labels, combobox, botão de confirmar, fundo, etc. permanecem os mesmos)

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(
                new ImageIcon(telaCartãoCredito.class.getResource("/imagens/fundopagamento.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        getContentPane().add(lblFundo);

        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
}
