package projeto;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

public class TelaRecibo extends JDialog {

    public TelaRecibo(JFrame parent, String empreendimento) {
        super(parent, "Recibo - " + empreendimento, true);
        setSize(500, 400);
        setUndecorated(true);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);
        
        // PADRÃO: TOP-LEFT (VOLTAR)
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(TelaRecibo.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(10, 10, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        JLabel lblTitulo = new JLabel("RECIBO DE PAGAMENTO");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 60, 464, 30);
        getContentPane().add(lblTitulo);

        JLabel lblDetalhes = new JLabel("Empreendimento: " + empreendimento);
        lblDetalhes.setForeground(Color.WHITE);
        lblDetalhes.setFont(new Font("Arial", Font.PLAIN, 20));
        lblDetalhes.setBounds(50, 130, 400, 30);
        getContentPane().add(lblDetalhes);
        
        JLabel lblStatus = new JLabel("Status: PAGO / Confirmado");
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 15));
        lblStatus.setBounds(50, 170, 400, 30);
        getContentPane().add(lblStatus);
        
        JLabel lblFundoRecibo = new JLabel("");
        lblFundoRecibo.setIcon(new ImageIcon(TelaRecibo.class.getResource("/imagens/fundopagamento.png")));
        lblFundoRecibo.setBounds(0, 0, 500, 400);
        getContentPane().add(lblFundoRecibo);

        setVisible(true);
    }
}
