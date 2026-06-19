package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CadastrodeCarros {

    private JFrame CadCarrosFrame;
    private JTextField modelo_txt;
    private JTextField CNH_text;
    private JTextField placa_txt;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastrodeCarros window = new CadastrodeCarros();
                window.CadCarrosFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastrodeCarros() {
        initialize();
    }

    public void visivel() {
        this.CadCarrosFrame.setVisible(true);
    }

    private void initialize() {
        CadCarrosFrame = new JFrame();
        CadCarrosFrame.setUndecorated(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        CadCarrosFrame.setSize(screenSize.width, screenSize.height);
        CadCarrosFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        CadCarrosFrame.getContentPane().setLayout(null);

        
        GerenciadorTeclado.getInstance().inicializar(CadCarrosFrame);

        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(CadastrodeCarros.class.getResource("/imagens/img_cad_veiculo/img_cad_veiculo_btn_voltar.png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(e -> {
            new telaAdmin("joao.admin@email.com").visivel();
            CadCarrosFrame.dispose();
        });
        CadCarrosFrame.getContentPane().add(btnVoltar);

        placa_txt = new PlaceholderTextField("Digite a placa do veículo");
        placa_txt.setForeground(Color.WHITE);
        placa_txt.setFont(new Font("Tahoma", Font.BOLD, 18));
        placa_txt.setBounds(606, 255, 743, 45);
        placa_txt.setOpaque(false);
        placa_txt.setBorder(null);
        CadCarrosFrame.getContentPane().add(placa_txt);

        ((AbstractDocument) placa_txt.getDocument()).setDocumentFilter(new FiltroPlaca());
        GerenciadorTeclado.getInstance().registrarCampo(placa_txt);

        modelo_txt = new PlaceholderTextField("Digite o modelo do veículo");
        modelo_txt.setForeground(Color.WHITE);
        modelo_txt.setFont(new Font("Tahoma", Font.BOLD, 18));
        modelo_txt.setBounds(606, 346, 743, 45);
        modelo_txt.setOpaque(false);
        modelo_txt.setBorder(null);
        CadCarrosFrame.getContentPane().add(modelo_txt);

        GerenciadorTeclado.getInstance().registrarCampo(modelo_txt);

        CNH_text = new PlaceholderTextField("Digite o número da CNH do condutor");
        CNH_text.setForeground(Color.WHITE);
        CNH_text.setFont(new Font("Tahoma", Font.BOLD, 18));
        CNH_text.setBounds(606, 439, 743, 45);
        CNH_text.setOpaque(false);
        CNH_text.setBorder(null);
        CadCarrosFrame.getContentPane().add(CNH_text);

        ((AbstractDocument) CNH_text.getDocument()).setDocumentFilter(new FiltroNumerico(11));
        GerenciadorTeclado.getInstance().registrarCampoNumerico(CNH_text);

        JButton botãoCadastrar = new JButton("");
        botãoCadastrar.setIcon(new ImageIcon(CadastrodeCarros.class.getResource("/imagens/img_cad_veiculo/img_cad_veiculo_ficha2-Photoroom (1).png")));
        botãoCadastrar.addActionListener(e -> {
            Bancodedados bd = new Bancodedados();
            bd.conectar();

            if (bd.verificar()) {
                String placa = placa_txt.getText().trim();
                String modelo = modelo_txt.getText().trim();
                String cnh = CNH_text.getText().trim();

                if (placa.isEmpty() || modelo.isEmpty() || cnh.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else if (placa.length() != 7) {
                    JOptionPane.showMessageDialog(null, "A placa deve conter 7 caracteres. Exemplo: ABC1234");
                } else if (cnh.length() != 11) {
                    JOptionPane.showMessageDialog(null, "A CNH deve conter exatamente 11 dígitos.");
                } else {
                    bd.inseriVeiculo(placa, modelo, cnh);

                    placa_txt.setText("");
                    modelo_txt.setText("");
                    CNH_text.setText("");

                    JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados.");
            }

            bd.desconectar();
        });

        botãoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 23));
        botãoCadastrar.setBounds(555, 564, 796, 60);
        botãoCadastrar.setOpaque(false);
        botãoCadastrar.setContentAreaFilled(false);
        botãoCadastrar.setBorderPainted(false);
        CadCarrosFrame.getContentPane().add(botãoCadastrar);

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(CadastrodeCarros.class.getResource("/imagens/img_cad_veiculo/img_cad_veiculo_ficha.png")));
        lblFundo.setBounds(0, -12, 1930, 1104);
        CadCarrosFrame.getContentPane().add(lblFundo);
    }

    private static class PlaceholderTextField extends JTextField {

        private String placeholder;
        private Color placeholderColor = new Color(210, 210, 210);

        public PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (getText().isEmpty() && !isFocusOwner()) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setColor(placeholderColor);
                g2.setFont(getFont());

                Insets insets = getInsets();
                int x = insets.left;
                int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2 + g2.getFontMetrics().getAscent();

                g2.drawString(placeholder, x, y);
                g2.dispose();
            }
        }
    }

    private static class FiltroNumerico extends DocumentFilter {

        private int limite;

        public FiltroNumerico(int limite) {
            this.limite = limite;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String texto, AttributeSet attr) throws BadLocationException {
            if (texto == null) {
                return;
            }

            String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset);

            if (novoTexto.matches("\\d*") && novoTexto.length() <= limite) {
                super.insertString(fb, offset, texto, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs) throws BadLocationException {
            if (texto == null) {
                return;
            }

            String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset + length);

            if (novoTexto.matches("\\d*") && novoTexto.length() <= limite) {
                super.replace(fb, offset, length, texto, attrs);
            }
        }
    }

    private static class FiltroPlaca extends DocumentFilter {

        private final int LIMITE = 7;

        @Override
        public void insertString(FilterBypass fb, int offset, String texto, AttributeSet attr) throws BadLocationException {
            if (texto == null) {
                return;
            }

            replace(fb, offset, 0, texto, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs) throws BadLocationException {
            if (texto == null) {
                return;
            }

            texto = texto.toUpperCase();

            String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset + length);

            if (novoTexto.length() > LIMITE) {
                return;
            }

            if (placaValidaDuranteDigitacao(novoTexto)) {
                super.replace(fb, offset, length, texto, attrs);
            }
        }

        private boolean placaValidaDuranteDigitacao(String texto) {
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);

                if (i < 3) {
                    if (!Character.isLetter(c)) {
                        return false;
                    }
                } else {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}