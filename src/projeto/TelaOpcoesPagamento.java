package projeto;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaOpcoesPagamento extends JDialog {

    public TelaOpcoesPagamento(JFrame parent, String empreendimento) {
        super(parent, "Formas de Pagamento", true);
        
        // TAMANHO FIXO E PEQUENO (Conforme o fundo do Isaac)
        setSize(500, 400);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(parent); // Centraliza sobre a mensalidade

        // BOTÃO VOLTAR (ISAAC)
        JButton btnVoltar = new JButton("");
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setBounds(10, 10, 104, 35);
        btnVoltar.addActionListener(e -> dispose());
        getContentPane().add(btnVoltar);

        JLabel lblTitulo = new JLabel("Escolha o Pagamento");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setBounds(10, 60, 464, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTitulo);

        // BOTÃO PIX
        JButton btnPix = new JButton("");
        btnPix.setContentAreaFilled(false);
        btnPix.setBorderPainted(false);
        btnPix.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/botoes_isaac/Pix.png")));
        btnPix.setBounds(100, 120, 297, 35);
        btnPix.addActionListener(e -> {
            dispose(); // Destrói o seletor pequeno
            new telaPIX(parent).visivel(); // Abre a tela grande do PIX
        });
        getContentPane().add(btnPix);

        // BOTÃO CRÉDITO
        JButton btnCredito = new JButton("");
        btnCredito.setContentAreaFilled(false);
        btnCredito.setBorderPainted(false);
        btnCredito.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/botoes_isaac/_comicLight small Base (1).png")));
        btnCredito.setBounds(100, 180, 297, 35);
        btnCredito.addActionListener(e -> {
            dispose();
            new telaCartãoCredito(parent).visivel();
        });
        getContentPane().add(btnCredito);

        // BOTÃO DÉBITO
        JButton btnDebito = new JButton("");
        btnDebito.setContentAreaFilled(false);
        btnDebito.setBorderPainted(false);
        btnDebito.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/botoes_isaac/_comicLight small Base (2).png")));
        btnDebito.setBounds(100, 240, 297, 35);
        btnDebito.addActionListener(e -> {
            dispose();
            new telaCartãoDébito(parent).visivel();
        });
        getContentPane().add(btnDebito);
        
        JLabel lblFundoPagamento = new JLabel("");
        lblFundoPagamento.setIcon(new ImageIcon(TelaOpcoesPagamento.class.getResource("/imagens/fundopagamento.png")));
        lblFundoPagamento.setBounds(0, 0, 500, 400);
        getContentPane().add(lblFundoPagamento);

        // NOTA: Não chamamos GerenciadorJanelas aqui para não forçar o 1920x1080 nesta janelinha
    }

    public void visivel() {
        setVisible(true);
    }
}
