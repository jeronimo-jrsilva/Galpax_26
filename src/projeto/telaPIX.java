package projeto;

import java.awt.EventQueue;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class telaPIX extends JDialog {

    private JFrame parent;

    public telaPIX(JFrame parent) {
        super(parent, "Pagamento PIX", true);
        this.parent = parent;
        initialize();
    }
    
    // Fallback para main
    public telaPIX() { this(null); }

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
        btnVoltar.setIcon(new ImageIcon(telaPIX.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        // LÓGICA ORIGINAL DO ALUNO (HENRIQUE)
        String codigoPix = "00020126360014BR.GOV.BCB.PIX0114seupix@email.com520400005303986540510.005802BR5920Empresa Exemplo6009Fortaleza62070503***6304ABCD";

        JLabel lblCodigoPix = new JLabel(codigoPix);
        lblCodigoPix.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblCodigoPix.setForeground(Color.WHITE);
        lblCodigoPix.setHorizontalAlignment(SwingConstants.CENTER);
        lblCodigoPix.setBounds(157, 120, 1574, 60);
        getContentPane().add(lblCodigoPix);

        try {
            String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?size=400x400&data=" + URLEncoder.encode(codigoPix, "UTF-8");
            JLabel lblQRCode = new JLabel();
            lblQRCode.setIcon(new ImageIcon(new URL(qrUrl)));
            lblQRCode.setBounds(760, 250, 400, 400);
            getContentPane().add(lblQRCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(telaPIX.class.getResource("/imagens/fundopagamento.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        getContentPane().add(lblFundo);

        // REGISTRA PARA ESCALA E CTRL+W
        GerenciadorJanelas.registrarInstancia(this);
        GerenciadorJanelas.configurarJanela(this);
    }
}
