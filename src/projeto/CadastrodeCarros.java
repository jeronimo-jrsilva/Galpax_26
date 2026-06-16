package projeto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

public class CadastrodeCarros {

    private JFrame CadCarrosFrame;
    private JTextField modelo_txt;
    private JTextField CNH_text;
    private JTextField placa_txt;
    
    private JPanel containerTeclado = new JPanel(new BorderLayout()) {{
        setOpaque(false);
    }};
    
    private boolean shiftAtivo = false;
    private List<JButton> botoesLetras = new ArrayList<>();
    private JTextField campoAtivo;

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
    	CadastrodeCarros window = new CadastrodeCarros();
		window.CadCarrosFrame.setVisible(true);
	}

    private void initialize() {
        CadCarrosFrame = new JFrame();
        CadCarrosFrame.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        CadCarrosFrame.setSize(screenSize.width, screenSize.height);
        CadCarrosFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        CadCarrosFrame.getContentPane().setLayout(null);
        
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(CadastrodeCarros.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 104, 35);
        btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaAdmin("joao.admin@email.com").visivel();
				CadCarrosFrame.dispose();
			}
		});
        CadCarrosFrame.getContentPane().add(btnVoltar);
        
        containerTeclado.setBounds(378, 720, 1200, 350);
        CadCarrosFrame.getContentPane().add(containerTeclado);

        placa_txt = new JTextField();
        placa_txt.setFont(new Font("Tahoma", Font.BOLD, 18));
        placa_txt.setBounds(659, 341, 639, 58);
        placa_txt.setOpaque(false);
        placa_txt.setBorder(null);
        placa_txt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                campoAtivo = placa_txt;
                mostrarTeclado(criarTecladoCompleto());
            }
        });
        CadCarrosFrame.getContentPane().add(placa_txt);

        modelo_txt = new JTextField();
        modelo_txt.setFont(new Font("Tahoma", Font.BOLD, 18));
        modelo_txt.setBounds(656, 449, 639, 59);
        modelo_txt.setOpaque(false);
        modelo_txt.setBorder(null);
        modelo_txt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                campoAtivo = modelo_txt;
                mostrarTeclado(criarTecladoCompleto());
            }
        });
        JButton botãoCadastrar = new JButton(" CADASTRAR ");
        botãoCadastrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
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
        	}
        });
        botãoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 23));
        botãoCadastrar.setBounds(729, 642, 441, 40);
        CadCarrosFrame.getContentPane().add(botãoCadastrar);
        CadCarrosFrame.getContentPane().add(modelo_txt);

        CNH_text = new JTextField();
        CNH_text.setFont(new Font("Tahoma", Font.BOLD, 18));
        CNH_text.setBounds(656, 559, 640, 60);
        CNH_text.setOpaque(false);
        CNH_text.setBorder(null);
        CNH_text.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                campoAtivo = CNH_text;
                mostrarTeclado(criarTecladoCompleto());
            }
        });
        CadCarrosFrame.getContentPane().add(CNH_text);

        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(CadastrodeCarros.class.getResource("/imagens/fundo tela cad carro.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        CadCarrosFrame.getContentPane().add(lblFundo);
    }

    private void mostrarTeclado(JPanel painelTeclado) {
        containerTeclado.removeAll();
        containerTeclado.add(painelTeclado, BorderLayout.CENTER);
        containerTeclado.revalidate();
        containerTeclado.repaint();
    }

    private void inserirTexto(String texto) {
        if (campoAtivo == null) return;
        String atual = campoAtivo.getText();
        if (texto.equals("APAGAR")) {
            if (atual.length() > 0) campoAtivo.setText(atual.substring(0, atual.length() - 1));
        } else if (texto.equals("ESPAÇO")) {
            campoAtivo.setText(atual + " ");
        } else {
            campoAtivo.setText(atual + texto);
        }
    }

    private void alternarShift(JButton btnShift) {
        shiftAtivo = !shiftAtivo;
        btnShift.setBackground(shiftAtivo ? Color.LIGHT_GRAY : Color.WHITE);
        for (JButton btn : botoesLetras) {
            btn.setText(shiftAtivo ? btn.getText().toUpperCase() : btn.getText().toLowerCase());
        }
    }

    private JPanel criarTecladoCompleto() {
        botoesLetras.clear();
        shiftAtivo = false;
        JPanel painelPrincipal = new JPanel(new GridLayout(5, 1, 0, 6));
        painelPrincipal.setOpaque(false);
        
        String[][] linhas = {
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "@"},
            {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "#"},
            {"a", "s", "d", "f", "g", "h", "j", "k", "l", "ç", ";", "\"", "$"},
            {"SHIFT", "z", "x", "c", "v", "b", "n", "m", ",", ".", "/", "!", "%"},
            {"&", "*", "ESPAÇO", "APAGAR"}
        };

        for (String[] linha : linhas) {
            JPanel painelLinha = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
            painelLinha.setOpaque(false);
            for (String caractere : linha) {
                JButton btn = new JButton(caractere);
                btn.setBackground(Color.WHITE);
                if (caractere.equals("SHIFT")) {
                    btn.setText("SHIFT ⇧");
                    btn.setPreferredSize(new Dimension(110, 50));
                    btn.addActionListener(e -> alternarShift(btn));
                } else if (caractere.equals("APAGAR")) {
                    btn.setBackground(new Color(255, 200, 200));
                    btn.setPreferredSize(new Dimension(120, 50));
                    btn.addActionListener(e -> inserirTexto("APAGAR"));
                } else if (caractere.equals("ESPAÇO")) {
                    btn.setPreferredSize(new Dimension(400, 50));
                    btn.addActionListener(e -> inserirTexto("ESPAÇO"));
                } else {
                    btn.setPreferredSize(new Dimension(55, 50));
                    btn.addActionListener(e -> inserirTexto(btn.getText()));
                    if (caractere.matches("[a-zç]")) botoesLetras.add(btn);
                }
                painelLinha.add(btn);
            }
            painelPrincipal.add(painelLinha);
        }
        return painelPrincipal;
    }
}