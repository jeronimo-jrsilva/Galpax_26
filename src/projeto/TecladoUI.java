package projeto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TecladoUI extends JDialog {

    private JPanel painelTecladoCompleto;
    private JPanel painelTecladoNumerico;
    private CardLayout cardLayout = new CardLayout();
    private JPanel containerPaineis = new JPanel(cardLayout);

    public TecladoUI(JFrame owner) {
        super(owner);
        setUndecorated(true);
        setFocusableWindowState(false);
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0)); // Fundo transparente

        // Determinar a posição e tamanho
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int tecladoHeight = 330;
        setBounds(0, screenSize.height - tecladoHeight, screenSize.width, tecladoHeight);

        // Inicializa os paineis
        this.painelTecladoCompleto = criarPainelTeclado(false);
        this.painelTecladoNumerico = criarPainelTeclado(true);

        containerPaineis.add(painelTecladoCompleto, "COMPLETO");
        containerPaineis.add(painelTecladoNumerico, "NUMERICO");
        
        getContentPane().add(containerPaineis);
        cardLayout.show(containerPaineis, "COMPLETO");
    }

    public void mostrarPainelCompleto() {
        cardLayout.show(containerPaineis, "COMPLETO");
    }

    public void mostrarPainelNumerico() {
        cardLayout.show(containerPaineis, "NUMERICO");
    }

    private JPanel criarPainelTeclado(boolean isNumerico) {
        if (isNumerico) {
            return confteclado.criarTecladoNumericoUniversal();
        } else {
            return confteclado.criarTecladoCompletoUniversal();
        }
    }
}
