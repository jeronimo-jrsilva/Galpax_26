package projeto;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.KeyboardFocusManager;
import java.awt.Component;

public class GerenciadorTeclado {

    private static GerenciadorTeclado instancia;
    private TecladoUI tecladoDialog;
    private JTextField campoAtivo;
    private List<JTextField> camposRegistrados = new ArrayList<>();

    private GerenciadorTeclado() {
        // Construtor privado para o Singleton
    }

    public static GerenciadorTeclado getInstance() {
        if (instancia == null) {
            instancia = new GerenciadorTeclado();
        }
        return instancia;
    }

    public void inicializar(JFrame framePrincipal) {
        if (tecladoDialog == null) {
            tecladoDialog = new TecladoUI(framePrincipal);
        }
    }

    public void registrarCampo(JTextField campo) {
        if (!camposRegistrados.contains(campo)) {
            camposRegistrados.add(campo);
        }

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setCampoAtivo((JTextField) e.getComponent());
                mostrarTeclado();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Adia a verificação para garantir que o novo foco seja processado
                SwingUtilities.invokeLater(() -> {
                    Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                    if (!isCampoRegistrado(focusOwner)) {
                        esconderTeclado();
                    }
                });
            }
        });
    }

    private boolean isCampoRegistrado(Component comp) {
        if (comp instanceof JTextField) {
            return camposRegistrados.contains((JTextField) comp);
        }
        // Verifica se o componente está dentro do próprio teclado
        if (comp != null && tecladoDialog != null && SwingUtilities.isDescendingFrom(comp, tecladoDialog)) {
            return true;
        }
        return false;
    }

    public void setCampoAtivo(JTextField campo) {
        this.campoAtivo = campo;
    }

    public JTextField getCampoAtivo() {
        return this.campoAtivo;
    }

    public void mostrarTeclado() {
        if (tecladoDialog != null) {
            tecladoDialog.setVisible(true);
        }
    }

    public void esconderTeclado() {
        if (tecladoDialog != null) {
            tecladoDialog.setVisible(false);
        }
    }
    
    public void alternarTecladoNumerico() {
        if (tecladoDialog != null) {
            tecladoDialog.mostrarPainelNumerico();
        }
    }

    public void alternarTecladoCompleto() {
        if (tecladoDialog != null) {
            tecladoDialog.mostrarPainelCompleto();
        }
    }
}
