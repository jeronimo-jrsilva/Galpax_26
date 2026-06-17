package projeto;

import java.awt.Font;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
import java.awt.Color;
import java.text.ParseException;
import java.time.LocalDate;
>>>>>>> refs/remotes/origin/dev-arthur

import javax.swing.JFrame;
import javax.swing.JDialog;
<<<<<<< HEAD
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
=======
>>>>>>> refs/remotes/origin/dev-arthur
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
<<<<<<< HEAD
import javax.swing.text.AttributeSet;
import javax.swing.DefaultComboBoxModel;
=======
>>>>>>> refs/remotes/origin/dev-arthur
import javax.swing.ImageIcon;
<<<<<<< HEAD
import java.awt.Color;
import javax.swing.JFormattedTextField;
=======
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
>>>>>>> refs/remotes/origin/dev-arthur

public class telaCartãoCredito extends JDialog {

    private JTextField txtTitular;
<<<<<<< HEAD
    private JTextField txtNumeroCartao;
    private JTextField txtValidade;
    private MaskFormatter maskCVV;
=======
    private JFormattedTextField txtNumeroCartao;
    private JFormattedTextField txtValidade;
    private JFormattedTextField txtCVV;
>>>>>>> refs/remotes/origin/dev-arthur

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
        
        
        try {
			maskCVV = new MaskFormatter("***");
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro","Aviso",-1);
		}
		

        // BOTÃO VOLTAR
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(
                telaCartãoCredito.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        JLabel lblTitulo = new JLabel("COMPRA NO CARTÃO DE CRÉDITO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(610, 80, 700, 40);
        getContentPane().add(lblTitulo);

        JLabel lblTitular = new JLabel("Nome do Titular:");
        lblTitular.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitular.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitular.setForeground(Color.WHITE);
        lblTitular.setBounds(455, 216, 200, 30);
        getContentPane().add(lblTitular);

        txtTitular = new JTextField();
        txtTitular.setBounds(685, 216, 700, 30);
        getContentPane().add(txtTitular);

        JLabel lblNumero = new JLabel("Número do Cartão:");
        lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNumero.setForeground(Color.WHITE);
        lblNumero.setBounds(435, 316, 220, 30);
        getContentPane().add(lblNumero);

        try {

            // Máscara do cartão
            MaskFormatter mascaraCartao = new MaskFormatter("#### #### #### ####");
            mascaraCartao.setPlaceholderCharacter('_');

            txtNumeroCartao = new JFormattedTextField(mascaraCartao);
            txtNumeroCartao.setBounds(685, 316, 700, 30);
            getContentPane().add(txtNumeroCartao);

            // Máscara validade MM/AA
            MaskFormatter mascaraValidade = new MaskFormatter("##/##");
            mascaraValidade.setPlaceholderCharacter('_');

            txtValidade = new JFormattedTextField(mascaraValidade);
            txtValidade.setBounds(743, 425, 180, 30);
            getContentPane().add(txtValidade);

            // Máscara CVV
            MaskFormatter mascaraCVV = new MaskFormatter("###");
            mascaraCVV.setPlaceholderCharacter('_');

            txtCVV = new JFormattedTextField(mascaraCVV);
            txtCVV.setBounds(1103, 425, 150, 30);
            getContentPane().add(txtCVV);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        JLabel lblValidade = new JLabel("Validade:");
        lblValidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblValidade.setForeground(Color.WHITE);
        lblValidade.setBounds(643, 425, 100, 30);
        getContentPane().add(lblValidade);

        JLabel lblCVV = new JLabel("CVV:");
        lblCVV.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCVV.setForeground(Color.WHITE);
        lblCVV.setBounds(1043, 425, 60, 30);
        getContentPane().add(lblCVV);

<<<<<<< HEAD
        JComboBox<String> cbParcelas = new JComboBox<>(new String[] {"1x sem juros", "2x sem juros", "3x sem juros", "12x sem juros"});
=======
        JComboBox<String> cbParcelas = new JComboBox<>(
                new String[] {
                        "1x sem juros",
                        "2x sem juros",
                        "3x sem juros",
                        "12x sem juros"
                });
>>>>>>> refs/remotes/origin/dev-arthur
        cbParcelas.setBounds(743, 536, 250, 35);
        getContentPane().add(cbParcelas);

        JButton btnConfirmar = new JButton("Confirmar");
<<<<<<< HEAD
        btnConfirmar.setContentAreaFilled(false);
        btnConfirmar.setIcon(new ImageIcon(telaCartãoCredito.class.getResource("/imagens/botoes_isaac/_comicLight small Base (7).png")));
        btnConfirmar.setBounds(610, 796, 230, 50);
        btnConfirmar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Compra Concluída!"));
=======
        btnConfirmar.setBounds(626, 796, 220, 50);

        btnConfirmar.addActionListener(e -> {

            String titular = txtTitular.getText().trim();
            String cartao = txtNumeroCartao.getText();
            String validade = txtValidade.getText();
            String cvv = txtCVV.getText();

            // Nome
            if (titular.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Informe o nome do titular.");
                return;
            }

            // Cartão
            if (cartao.contains("_")) {
                JOptionPane.showMessageDialog(this,
                        "Informe o número completo do cartão.");
                return;
            }

            // Validade preenchida
            if (validade.contains("_")) {
                JOptionPane.showMessageDialog(this,
                        "Informe a validade corretamente.");
                return;
            }

            try {

                String[] partes = validade.split("/");

                int mes = Integer.parseInt(partes[0]);
                int ano = Integer.parseInt(partes[1]);

                // Mês entre 1 e 12
                if (mes < 1 || mes > 12) {
                    JOptionPane.showMessageDialog(this,
                            "O mês deve estar entre 01 e 12.");
                    return;
                }

                LocalDate hoje = LocalDate.now();

                int anoAtual = hoje.getYear() % 100;
                int mesAtual = hoje.getMonthValue();

                // Ano menor que o atual
                if (ano < anoAtual) {
                    JOptionPane.showMessageDialog(this,
                            "O cartão está vencido.");
                    return;
                }

                // Mesmo ano e mês vencido
                if (ano == anoAtual && mes < mesAtual) {
                    JOptionPane.showMessageDialog(this,
                            "O cartão está vencido.");
                    return;
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Data de validade inválida.");
                return;
            }

            // CVV
            if (cvv.contains("_")) {
                JOptionPane.showMessageDialog(this,
                        "Informe o CVV corretamente.");
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Compra Concluída!");
        });

>>>>>>> refs/remotes/origin/dev-arthur
        getContentPane().add(btnConfirmar);
        
        
        
        JPasswordField jftCVV = new JPasswordField();
        jftCVV.setBounds(1102, 425, 150, 30);

<<<<<<< HEAD
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
        
        
        		JLabel lblFundo = new JLabel("");
                lblFundo.setIcon(new ImageIcon(telaCartãoCredito.class.getResource("/imagens/fundopagamento.png")));
                lblFundo.setBounds(0, 0, 1920, 1080);
                getContentPane().add(lblFundo);
=======
        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(
                new ImageIcon(telaCartãoCredito.class.getResource("/imagens/fundopagamento.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        getContentPane().add(lblFundo);
>>>>>>> refs/remotes/origin/dev-arthur

        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
}