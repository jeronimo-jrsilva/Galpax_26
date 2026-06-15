package projeto;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class telaCartãoCredito extends JDialog {

    private JTextField txtTitular;
    private JTextField txtNumeroCartao;
    private JTextField txtValidade;
    private JTextField txtCVV;

    public telaCartãoCredito(JFrame parent) {
        super(parent, "Cartão de Crédito", true);
        initialize();
    }
    
    public telaCartãoCredito() { this(null); }

    public void visivel() { setVisible(true); }

    private void initialize() {
        setTitle("Compra no Crédito");
        setSize(1920, 1080);
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        // BOTÃO VOLTAR (ISAAC)
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaCartãoCredito.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        // UI ORIGINAL DO ALUNO (HENRIQUE)
        JLabel lblTitulo = new JLabel("COMPRA NO CARTÃO DE CRÉDITO");
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

        txtCVV = new JTextField();
        txtCVV.setBounds(1103, 425, 150, 30);
        getContentPane().add(txtCVV);

        JComboBox<String> cbParcelas = new JComboBox<>(new String[] {"1x sem juros", "2x sem juros", "3x sem juros", "12x sem juros"});
        cbParcelas.setBounds(743, 536, 250, 35);
        getContentPane().add(cbParcelas);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(626, 796, 220, 50);
        btnConfirmar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Compra Concluída!"));
        getContentPane().add(btnConfirmar);

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(telaCartãoCredito.class.getResource("/imagens/fundopagamento.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        getContentPane().add(lblFundo);

        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
}
