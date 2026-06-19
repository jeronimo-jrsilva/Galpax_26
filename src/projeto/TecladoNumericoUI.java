package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TecladoNumericoUI extends JDialog {

    public TecladoNumericoUI(JFrame framePrincipal) {
        super(framePrincipal, false);

        setUndecorated(true);
        setSize(420, 300);

        // Impede o teclado de roubar o foco do JTextField
        setFocusableWindowState(false);

        setAlwaysOnTop(true);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(35, 35, 35));

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (tela.width - 420) / 2;
        int y = tela.height - 360;

        setLocation(x, y);

        JPanel painel = new JPanel();
        painel.setBounds(10, 10, 400, 280);
        painel.setOpaque(false);
        painel.setLayout(new GridLayout(4, 3, 8, 8));
        getContentPane().add(painel);

        adicionarBotaoNumero(painel, "1");
        adicionarBotaoNumero(painel, "2");
        adicionarBotaoNumero(painel, "3");

        adicionarBotaoNumero(painel, "4");
        adicionarBotaoNumero(painel, "5");
        adicionarBotaoNumero(painel, "6");

        adicionarBotaoNumero(painel, "7");
        adicionarBotaoNumero(painel, "8");
        adicionarBotaoNumero(painel, "9");

        adicionarBotaoApagar(painel);
        adicionarBotaoNumero(painel, "0");
        adicionarBotaoOk(painel);
    }

    private void adicionarBotaoNumero(JPanel painel, String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Tahoma", Font.BOLD, 24));
        botao.setBackground(new Color(60, 60, 60));
        botao.setForeground(Color.WHITE);
        botao.setFocusable(false);

        botao.addActionListener(e -> inserirTexto(texto));

        painel.add(botao);
    }

    private void adicionarBotaoApagar(JPanel painel) {
        JButton botao = new JButton("⌫");
        botao.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
        botao.setBackground(new Color(90, 90, 90));
        botao.setForeground(Color.WHITE);
        botao.setFocusable(false);

        botao.addActionListener(e -> apagarTexto());

        painel.add(botao);
    }

    private void adicionarBotaoOk(JPanel painel) {
        JButton botao = new JButton("OK");
        botao.setFont(new Font("Tahoma", Font.BOLD, 22));
        botao.setBackground(new Color(90, 90, 90));
        botao.setForeground(Color.WHITE);
        botao.setFocusable(false);

        botao.addActionListener(e -> setVisible(false));

        painel.add(botao);
    }

    private void inserirTexto(String texto) {
        JTextField campo = GerenciadorTeclado.getInstance().getCampoAtivo();

        if (campo != null) {
            campo.replaceSelection(texto);
            campo.requestFocusInWindow();
        }
    }

    private void apagarTexto() {
        JTextField campo = GerenciadorTeclado.getInstance().getCampoAtivo();

        if (campo != null) {
            try {
                int inicio = campo.getSelectionStart();
                int fim = campo.getSelectionEnd();

                if (inicio != fim) {
                    campo.getDocument().remove(inicio, fim - inicio);
                } else if (inicio > 0) {
                    campo.getDocument().remove(inicio - 1, 1);
                }

                campo.requestFocusInWindow();

            } catch (Exception erro) {
                erro.printStackTrace();
            }
        }
    }
}