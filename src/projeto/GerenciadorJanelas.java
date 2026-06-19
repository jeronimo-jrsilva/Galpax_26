package projeto;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Window;

public class GerenciadorJanelas {
    
    public static boolean isModoJanela = false;
    private static final int LARGURA_NATIVA = 1920;
    private static final int ALTURA_NATIVA = 1080;
    
    private static Window windowAtiva;
    private static Object telaAtivaInstancia;

    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
                    if (windowAtiva != null) {
                        isModoJanela = !isModoJanela;
                        windowAtiva.dispose();
                        reabrirTelaAtiva();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public static void registrarInstancia(Object tela) {
        telaAtivaInstancia = tela;
    }

    private static void reabrirTelaAtiva() {
        try {
            if (telaAtivaInstancia != null) {
                telaAtivaInstancia.getClass().getMethod("visivel").invoke(telaAtivaInstancia);
            }
        } catch (Exception e) { }
    }

    /**
     * Suporta tanto JFrame quanto JDialog
     */
    public static void configurarJanela(Window window) {
        windowAtiva = window;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double escalaX, escalaY;

        if (isModoJanela) {
           
            int targetWidth = (int) (screenSize.width * 0.80);
            int targetHeight = (int) (targetWidth * 1080 / 1920);
            window.setSize(targetWidth, targetHeight);
            
            if (window instanceof JFrame) {
                ((JFrame)window).setUndecorated(false);
                ((JFrame)window).setExtendedState(JFrame.NORMAL);
            }
            window.setLocationRelativeTo(null);
            
            escalaX = (double) targetWidth / LARGURA_NATIVA;
            escalaY = (double) targetHeight / ALTURA_NATIVA;
        } else {
            if (window instanceof JFrame) {
                ((JFrame)window).setUndecorated(true);
                ((JFrame)window).setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            window.setSize(screenSize.width, screenSize.height);
            
            escalaX = (double) screenSize.width / LARGURA_NATIVA;
            escalaY = (double) screenSize.height / ALTURA_NATIVA;
        }

        aplicarEscalaRecursiva(window, escalaX, escalaY);
        window.revalidate();
        window.repaint();
    }

    private static void aplicarEscalaRecursiva(Container container, double escalaX, double escalaY) {
        for (Component comp : container.getComponents()) {
            int x = (int) (comp.getX() * escalaX);
            int y = (int) (comp.getY() * escalaY);
            int w = (int) (comp.getWidth() * escalaX);
            int h = (int) (comp.getHeight() * escalaY);
            
            comp.setBounds(x, y, w, h);

            if (comp instanceof javax.swing.JLabel) {
                javax.swing.JLabel label = (javax.swing.JLabel) comp;
                if (label.getIcon() instanceof ImageIcon) {
                    if (label.getWidth() > 800 || label.getHeight() > 400) {
                        label.setIcon(redimensionarAltaQualidade((ImageIcon) label.getIcon(), w, h));
                    }
                }
            } else if (comp instanceof javax.swing.JButton) {
                javax.swing.JButton btn = (javax.swing.JButton) comp;
                if (btn.getIcon() instanceof ImageIcon) {
                    String path = btn.getIcon().toString();
                    if (path.contains("botoes_isaac")) {
                        btn.setIcon(redimensionarAltaQualidade((ImageIcon) btn.getIcon(), w, h));
                    }
                }
            }

            if (comp instanceof Container) {
                aplicarEscalaRecursiva((Container) comp, escalaX, escalaY);
            }
        }
    }

    private static ImageIcon redimensionarAltaQualidade(ImageIcon icon, int targetW, int targetH) {
        if (targetW <= 0 || targetH <= 0) return icon;
        Image original = icon.getImage();
        BufferedImage output = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(original, 0, 0, targetW, targetH, null);
        g2.dispose();
        return new ImageIcon(output);
    }
}
