package projeto;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorTeclado {

    private static GerenciadorTeclado instancia;

    private JFrame framePrincipal;

    private TecladoUI tecladoCompletoDialog;
    private TecladoNumericoUI tecladoNumericoDialog;

    private JTextField campoAtivo;

    private Set<JTextField> camposRegistrados = new HashSet<>();
    private Set<JTextField> camposNumericos = new HashSet<>();
    private Set<JTextField> camposComListener = new HashSet<>();

    private GerenciadorTeclado() {

    }

    public static GerenciadorTeclado getInstance() {
        if (instancia == null) {
            instancia = new GerenciadorTeclado();
        }
        return instancia;
    }

    public void inicializar(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;

        if (tecladoCompletoDialog == null) {
            tecladoCompletoDialog = new TecladoUI(framePrincipal);
        }

        if (tecladoNumericoDialog == null) {
            tecladoNumericoDialog = new TecladoNumericoUI(framePrincipal);
        }
    }

    public void registrarCampo(JTextField campo) {
        camposRegistrados.add(campo);
        camposNumericos.remove(campo);
        adicionarListenerSeNecessario(campo);
    }

    public void registrarCampoNumerico(JTextField campo) {
        camposRegistrados.add(campo);
        camposNumericos.add(campo);
        adicionarListenerSeNecessario(campo);
    }

    private void adicionarListenerSeNecessario(JTextField campo) {
        if (camposComListener.contains(campo)) {
            return;
        }

        camposComListener.add(campo);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField campoAtual = (JTextField) e.getComponent();
                setCampoAtivo(campoAtual);

                SwingUtilities.invokeLater(() -> {
                    if (camposNumericos.contains(campoAtual)) {
                        mostrarTecladoNumerico();
                    } else {
                        mostrarTecladoCompleto();
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Component focusOwner = KeyboardFocusManager
                            .getCurrentKeyboardFocusManager()
                            .getFocusOwner();

                    if (!isCampoRegistradoOuTeclado(focusOwner)) {
                        esconderTeclados();
                    }
                });
            }
        });
    }

    private boolean isCampoRegistradoOuTeclado(Component comp) {
        if (comp instanceof JTextField) {
            return camposRegistrados.contains((JTextField) comp);
        }

        if (comp != null && tecladoCompletoDialog != null &&
                SwingUtilities.isDescendingFrom(comp, tecladoCompletoDialog)) {
            return true;
        }

        if (comp != null && tecladoNumericoDialog != null &&
                SwingUtilities.isDescendingFrom(comp, tecladoNumericoDialog)) {
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

    public boolean campoAtivoEhNumerico() {
        return campoAtivo != null && camposNumericos.contains(campoAtivo);
    }

    public void mostrarTecladoCompleto() {
        if (tecladoNumericoDialog != null) {
            tecladoNumericoDialog.setVisible(false);
        }

        if (tecladoCompletoDialog == null && framePrincipal != null) {
            tecladoCompletoDialog = new TecladoUI(framePrincipal);
        }

        if (tecladoCompletoDialog != null) {
            tecladoCompletoDialog.mostrarLetras(); // volta para o QWERTY
            tecladoCompletoDialog.setVisible(true);
            tecladoCompletoDialog.toFront();
        }
    }

    public void mostrarTecladoNumerico() {
        if (tecladoCompletoDialog != null) {
            tecladoCompletoDialog.setVisible(false);
        }

        if (tecladoNumericoDialog == null && framePrincipal != null) {
            tecladoNumericoDialog = new TecladoNumericoUI(framePrincipal);
        }

        if (tecladoNumericoDialog != null) {
            tecladoNumericoDialog.setVisible(true);
            tecladoNumericoDialog.toFront();
        }
    }

    public void esconderTeclados() {
        if (tecladoCompletoDialog != null) {
            tecladoCompletoDialog.setVisible(false);
        }

        if (tecladoNumericoDialog != null) {
            tecladoNumericoDialog.setVisible(false);
        }
    }

    public void esconderTeclado() {
        esconderTeclados();
    }

    public void mostrarTeclado() {
        if (campoAtivoEhNumerico()) {
            mostrarTecladoNumerico();
        } else {
            mostrarTecladoCompleto();
        }
    }

    public void alternarTecladoNumerico() {
        if (campoAtivoEhNumerico()) {
            mostrarTecladoNumerico();
        } else {
            if (tecladoCompletoDialog != null) {
                tecladoCompletoDialog.mostrarSimbolos();
            }
        }
    }

    public void alternarTecladoCompleto() {
        if (tecladoCompletoDialog != null) {
            tecladoCompletoDialog.mostrarLetras();
        }

        mostrarTecladoCompleto();
    }
}