package projeto;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRecibo {
    private JFrame frame;

    public TelaRecibo(String empreendimento) {
        frame = new JFrame();
        frame.setTitle("Recibo - " + empreendimento);
        frame.setBounds(710, 340, 500, 400); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.getContentPane().setLayout(null);
        
        // PADRÃO: TOP-LEFT (VOLTAR)
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(TelaRecibo.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(10, 10, 104, 35);
        btnVoltar.addActionListener(e -> frame.dispose());
        frame.getContentPane().add(btnVoltar);

        JLabel lblTitulo = new JLabel("RECIBO DE PAGAMENTO");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 60, 464, 30);
        frame.getContentPane().add(lblTitulo);

        JLabel lblDetalhes = new JLabel("Empreendimento: " + empreendimento);
        lblDetalhes.setForeground(new Color(255, 255, 255));
        lblDetalhes.setFont(new Font("Arial", Font.PLAIN, 20));
        lblDetalhes.setBounds(50, 130, 400, 30);
        frame.getContentPane().add(lblDetalhes);
        
        JLabel lblStatus = new JLabel("Status: PAGO / Confirmado");
        lblStatus.setForeground(new Color(255, 255, 255));
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 15));
        lblStatus.setBounds(50, 170, 400, 30);
        frame.getContentPane().add(lblStatus);
        
        JLabel lblFundoRecibo = new JLabel("");
        lblFundoRecibo.setIcon(new ImageIcon(TelaRecibo.class.getResource("/imagens/fundopagamento.png")));
        lblFundoRecibo.setBounds(0, 0, 500, 400);
        frame.getContentPane().add(lblFundoRecibo);

     // APLICA GERENCIADOR (CTRL+W)
        
        /*
        // Comentei essa linha pq está fazendo a tela iniciar em tela cheia gerando bug visual 
        
        GerenciadorJanelas.configurarJanela(frame); 
        
        */
        frame.setVisible(true);
    }
}
