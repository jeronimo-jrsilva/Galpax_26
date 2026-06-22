package projeto;

import javax.swing.*;
import java.awt.*;

public class TecladoUI extends JDialog {

    private JPanel painelTecladoCompleto;
    private JPanel painelTecladoNumerico;

    private CardLayout cardLayout = new CardLayout();
    private JPanel containerPaineis = new JPanel(cardLayout);

    private boolean modoSimbolos = false;

    public TecladoUI(JFrame owner) {
        super(owner);

        setUndecorated(true);
        setFocusableWindowState(false);
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int tecladoHeight = 330;
        int x = 0;
        int y = screenSize.height - tecladoHeight;

        setBounds(x, y, screenSize.width, tecladoHeight);

        painelTecladoCompleto = criarPainelTeclado(false);
        painelTecladoNumerico = criarPainelTeclado(true);

        containerPaineis.add(painelTecladoCompleto, "COMPLETO");
        containerPaineis.add(painelTecladoNumerico, "NUMERICO");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(containerPaineis, BorderLayout.CENTER);

        mostrarLetras();
    }

    private JPanel criarPainelTeclado(boolean isNumerico) {
        if (isNumerico) {
            return confteclado.criarTecladoNumericoUniversal();
        } else {
            return confteclado.criarTecladoCompletoUniversal();
        }
    }

    public void mostrarPainelCompleto() {
        mostrarLetras();
    }

    public void mostrarPainelNumerico() {
        mostrarSimbolos();
    }

    public void mostrarSimbolos() {
        modoSimbolos = true;
        cardLayout.show(containerPaineis, "NUMERICO");
        containerPaineis.revalidate();
        containerPaineis.repaint();
    }

    public void mostrarLetras() {
        modoSimbolos = false;
        cardLayout.show(containerPaineis, "COMPLETO");
        containerPaineis.revalidate();
        containerPaineis.repaint();
    }

    public boolean isModoSimbolos() {
        return modoSimbolos;
    }
}