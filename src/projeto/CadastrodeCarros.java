package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CadastrodeCarros {

    private JFrame CadCarrosFrame;
    private JTextField modelo_txt;
    private JTextField CNH_text;
    private JFormattedTextField placa_txt;
    private MaskFormatter maskPlaca;

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

        // INICIALIZA O TECLADO UNIVERSAL
        GerenciadorTeclado.getInstance().inicializar(CadCarrosFrame);

        try {
            maskPlaca = new MaskFormatter("UUU####");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

        placa_txt = new JFormattedTextField(maskPlaca);
        placa_txt.setForeground(new Color(255, 255, 255));
        placa_txt.setFont(new Font("Tahoma", Font.BOLD, 18));
        placa_txt.setBounds(785, 255, 564, 45);
        placa_txt.setOpaque(false);
        placa_txt.setBorder(null);
        CadCarrosFrame.getContentPane().add(placa_txt);
        GerenciadorTeclado.getInstance().registrarCampo(placa_txt);

        modelo_txt = new JTextField();
        modelo_txt.setForeground(new Color(255, 255, 255));
        modelo_txt.setFont(new Font("Tahoma", Font.BOLD, 18));
        modelo_txt.setBounds(797, 346, 552, 45);
        modelo_txt.setOpaque(false);
        modelo_txt.setBorder(null);
        CadCarrosFrame.getContentPane().add(modelo_txt);
        GerenciadorTeclado.getInstance().registrarCampo(modelo_txt);

        CNH_text = new JTextField();
        CNH_text.setForeground(new Color(255, 255, 255));
        CNH_text.setFont(new Font("Tahoma", Font.BOLD, 18));
        CNH_text.setBounds(861, 439, 488, 45);
        CNH_text.setOpaque(false);
        CNH_text.setBorder(null);
        CadCarrosFrame.getContentPane().add(CNH_text);
        GerenciadorTeclado.getInstance().registrarCampo(CNH_text);

        JButton botãoCadastrar = new JButton("");
        botãoCadastrar.addActionListener(e -> {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()) {
                String placa = placa_txt.getText();
                String modelo = modelo_txt.getText();
                String cnh = CNH_text.getText();
                if (placa.isEmpty() || modelo.isEmpty() || cnh.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else if (cnh.length() != 11) {
                    JOptionPane.showMessageDialog(null, "A CNH deve conter exatamente 11 dígitos.");
                } else {
                    bd.inseriVeiculo(placa, modelo, cnh);
                    placa_txt.setText("");
                    modelo_txt.setText("");
                    CNH_text.setText("");
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
        lblFundo.setBounds(0, -13, 1930, 1104);
        CadCarrosFrame.getContentPane().add(lblFundo);
    }
}