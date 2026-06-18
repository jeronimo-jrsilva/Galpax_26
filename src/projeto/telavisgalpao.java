package projeto;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class telavisgalpao {

	private JFrame frame;
	JLabel lblNewLabel;
	
	private JLabel lblNewLabel_1_13;
	private JLabel lblNewLabel_1_12;
	private JLabel lblNewLabel_1_11;
	private JLabel lblNewLabel_1_10;
	private JLabel lblNewLabel_1_8;
	private JLabel lblNewLabel_1_6;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_9;
	private JLabel lblNewLabel_2_3_8_4;
	private JLabel lblNewLabel_2_3_8_3;
	private JLabel lblNewLabel_2_3_8_2;
	private JLabel lblNewLabel_2_3_8_1;
	private JLabel lblNewLabel_2_3_8;
	private JLabel lblNewLabel_2_3_7;
	private JLabel lblNewLabel_2_3_6;
	private JLabel lblNewLabel_2_3_5;
	private JLabel lblNewLabel_2_3_4;
	private JLabel lblNewLabel_2_3_3;
	private JLabel lblNewLabel_2_3_2;
	private JLabel lblNewLabel_2_3_1;
	private JLabel lblNewLabel_2_3;
	private JLabel lblNewLabel_2_2;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1_5_2;
	private JLabel lblNewLabel_1_5_1;
	private JLabel lblNewLabel_1_5;
	private JLabel lblNewLabel_1_4;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telavisgalpao window = new telavisgalpao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telavisgalpao() {
		initialize();
		atualizarTodosOsGalpoes(); 
	}
public void visivel() {
	telavisgalpao window = new telavisgalpao();
	window.frame.setVisible(true);
}
	/**
	 * Initialize the contents of the frame.
	 */
private void initialize() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setUndecorated(true); 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize.width, screenSize.height);
    
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.getContentPane().setLayout(null);
      
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = env.getDefaultScreenDevice();
    
    // --- BOTÃO VOLTAR ---
    btnVoltar = new JButton("");
    btnVoltar.setIcon(new ImageIcon(telavisgalpao.class.getResource("/imagens/img_visualizar_galpao/img_visualizar_galpao_btn_voltar.png")));
    btnVoltar.setContentAreaFilled(false);
    btnVoltar.setBorderPainted(false);
    btnVoltar.setBounds(30, 11, 150, 106);
    btnVoltar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new telaAdmin("joao.admin@email.com").visivel();
            frame.dispose();
        }
    });
    frame.getContentPane().add(btnVoltar);

    // --- LINHA DOS GALPÕES 125 A 132 ---
    lblNewLabel_1_13 = new JLabel("132");
    lblNewLabel_1_13.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_13.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_13.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_13.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(132);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_13.setIcon(null);
    lblNewLabel_1_13.setBounds(1716, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_13);
    
    lblNewLabel_1_12 = new JLabel("131");
    lblNewLabel_1_12.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_12.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_12.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_12.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(131);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_12.setIcon(null);
    lblNewLabel_1_12.setBounds(1500, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_12);
    
    lblNewLabel_1_11 = new JLabel("130");
    lblNewLabel_1_11.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_11.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_11.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_11.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(130);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_11.setIcon(null);
    lblNewLabel_1_11.setBounds(1225, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_11);
    
    lblNewLabel_1_10 = new JLabel("129");
    lblNewLabel_1_10.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_10.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_10.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_10.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(129);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_10.setIcon(null);
    lblNewLabel_1_10.setBounds(1005, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_10);
    
    lblNewLabel_1_8 = new JLabel("127");
    lblNewLabel_1_8.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_8.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_8.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(127);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_8.setIcon(null);
    lblNewLabel_1_8.setBounds(517, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_8);
    
    lblNewLabel_1_6 = new JLabel("125");
    lblNewLabel_1_6.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_6.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_6.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(125);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_6.setIcon(null);
    lblNewLabel_1_6.setBounds(90, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_6);
    
    lblNewLabel_1_7 = new JLabel("126");
    lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_7.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_7.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_7.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(126);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_7.setIcon(null);
    lblNewLabel_1_7.setBounds(298, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_7);
    
    lblNewLabel_1_9 = new JLabel("128");
    lblNewLabel_1_9.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_9.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_9.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_9.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(128);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_9.setIcon(null);
    lblNewLabel_1_9.setBounds(729, 929, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_9);
    
    // --- LINHA DOS GALPÕES 117 A 124 ---
    lblNewLabel_2_3_8_4 = new JLabel("124");
    lblNewLabel_2_3_8_4.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_8_4.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_8_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_8_4.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(124);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_8_4.setIcon(null);
    lblNewLabel_2_3_8_4.setBounds(1716, 776, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_8_4);
    
    lblNewLabel_2_3_8_3 = new JLabel("123");
    lblNewLabel_2_3_8_3.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_8_3.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_8_3.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_8_3.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(123);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_8_3.setIcon(null);
    lblNewLabel_2_3_8_3.setBounds(1500, 776, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_8_3);
    
    lblNewLabel_2_3_8_2 = new JLabel("122");
    lblNewLabel_2_3_8_2.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_8_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_8_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_8_2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(122);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_8_2.setIcon(null);
    lblNewLabel_2_3_8_2.setBounds(1225, 778, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_8_2);
    
    lblNewLabel_2_3_8_1 = new JLabel("121");
    lblNewLabel_2_3_8_1.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_8_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_8_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_8_1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(121);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_8_1.setIcon(null);
    lblNewLabel_2_3_8_1.setBounds(1005, 778, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_8_1);
    
    lblNewLabel_2_3_8 = new JLabel("120");
    lblNewLabel_2_3_8.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_8.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_8.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_8.setIcon(null);
    lblNewLabel_2_3_8.setBounds(729, 776, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_8);
    
    lblNewLabel_2_3_7 = new JLabel("119");
    lblNewLabel_2_3_7.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_7.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_7.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_7.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(119);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_7.setIcon(null);
    lblNewLabel_2_3_7.setBounds(517, 778, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_7);
    
    lblNewLabel_2_3_6 = new JLabel("118");
    lblNewLabel_2_3_6.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_6.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_6.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_6.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(118);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_6.setIcon(null);
    lblNewLabel_2_3_6.setBounds(298, 776, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_6);
    
    lblNewLabel_2_3_5 = new JLabel("117");
    lblNewLabel_2_3_5.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_5.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_5.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_5.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(117);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_5.setIcon(null);
    lblNewLabel_2_3_5.setBounds(90, 778, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_5);
    
    // --- LINHA DOS GALPÕES 109 A 116 ---
    lblNewLabel_2_3_4 = new JLabel("116");
    lblNewLabel_2_3_4.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_4.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_4.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(116);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_4.setIcon(null);
    lblNewLabel_2_3_4.setBounds(1716, 619, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_4);
    
    lblNewLabel_2_3_3 = new JLabel("115");
    lblNewLabel_2_3_3.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_3.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_3.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_3.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(115);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_3.setIcon(null);
    lblNewLabel_2_3_3.setBounds(1500, 621, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_3);
    
    lblNewLabel_2_3_2 = new JLabel("114");
    lblNewLabel_2_3_2.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(114);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_2.setIcon(null);
    lblNewLabel_2_3_2.setBounds(1225, 625, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_2);
    
    lblNewLabel_2_3_1 = new JLabel("113");
    lblNewLabel_2_3_1.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3_1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(113);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3_1.setIcon(null);
    lblNewLabel_2_3_1.setBounds(1005, 627, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3_1);
    
    lblNewLabel_2_3 = new JLabel("112");
    lblNewLabel_2_3.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_3.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_3.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(112);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_3.setIcon(null);
    lblNewLabel_2_3.setBounds(729, 620, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_3);
    
    lblNewLabel_2_2 = new JLabel("111");
    lblNewLabel_2_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_2.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(111);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_2.setIcon(null);
    lblNewLabel_2_2.setBounds(517, 619, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_2);
    
    lblNewLabel_2_1 = new JLabel("110");
    lblNewLabel_2_1.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(110);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2_1.setIcon(null);
    lblNewLabel_2_1.setBounds(298, 619, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2_1);
    
    lblNewLabel_2 = new JLabel("109");
    lblNewLabel_2.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(109);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_2.setIcon(null);
    lblNewLabel_2.setBounds(90, 619, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_2);
    
    // --- LINHA DOS GALPÕES 101 A 108 ---
    lblNewLabel_1_5_2 = new JLabel("108");
    lblNewLabel_1_5_2.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_5_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_5_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_5_2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(108);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_5_2.setIcon(null);
    lblNewLabel_1_5_2.setBounds(1716, 453, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_5_2);
    
    lblNewLabel_1_5_1 = new JLabel("106");
    lblNewLabel_1_5_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_5_1.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_5_1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(106);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_5_1.setIcon(null);
    lblNewLabel_1_5_1.setBounds(1225, 453, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_5_1);
    
    lblNewLabel_1_5 = new JLabel("107");
    lblNewLabel_1_5.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_5.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_5.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(107);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_5.setIcon(null);
    lblNewLabel_1_5.setBounds(1500, 453, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_5);
    
    lblNewLabel_1_4 = new JLabel("105");
    lblNewLabel_1_4.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_4.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_4.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(105);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_4.setIcon(null);
    lblNewLabel_1_4.setBounds(1005, 453, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_4);
    
    lblNewLabel_1_3 = new JLabel("104");
    lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_3.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_3.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(104);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_3.setIcon(null);
    lblNewLabel_1_3.setBounds(729, 453, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1_3);
    
    lblNewLabel_1_2 = new JLabel("103");
    lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_2.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(103);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_2.setIcon(null);
    lblNewLabel_1_2.setBounds(517, 453, 150, 140); // Já era 150x140, mantido
    frame.getContentPane().add(lblNewLabel_1_2);
    
    lblNewLabel_1_1 = new JLabel("102");
    lblNewLabel_1_1.setBackground(new Color(255, 255, 255));
    lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_1_1.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(102);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1_1.setIcon(null);
    lblNewLabel_1_1.setBounds(298, 453, 150, 140); // Já era 150x140, mantido
    frame.getContentPane().add(lblNewLabel_1_1);
    
    lblNewLabel_1 = new JLabel("101");
    lblNewLabel_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_1.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Bancodedados bd = new Bancodedados();
            bd.conectar();
            if (bd.verificar()){
                bd.verificarCliente(101);
            }else {
                JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados","Aviso",-1);
            }
        }
    });
    lblNewLabel_1.setIcon(null);
    lblNewLabel_1.setBounds(90, 453, 150, 140); // Ajustado para 150x140
    frame.getContentPane().add(lblNewLabel_1);
    
    // --- LBLNEWLABEL (FUNDO - PERMANECE IGUAL) ---
    lblNewLabel = new JLabel("");
    lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 52));
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setIcon(new ImageIcon(telavisgalpao.class.getResource("/imagens/img_visualizar_galpao/img_visualizar_galpao_fundo.png")));
    lblNewLabel.setBounds(0, -98, 1920, 1277);
    frame.getContentPane().add(lblNewLabel);
}

	
	private void atualizarStatus(Bancodedados bd, JLabel label, int idGalpao) {
		label.setOpaque(true); 
		if (bd.isGalpaoOcupado(idGalpao)) {
			label.setBackground(Color.RED);
		} else {
			label.setBackground(Color.GREEN);
		}
	}

	
	public void atualizarTodosOsGalpoes() {
	    Bancodedados bd = new Bancodedados();
	    bd.conectar();
	    
	    if (bd.verificar()) {
	       
	        atualizarStatus(bd, lblNewLabel_1_6, 125);
	        atualizarStatus(bd, lblNewLabel_1_7, 126);
	        atualizarStatus(bd, lblNewLabel_1_8, 127);
	        atualizarStatus(bd, lblNewLabel_1_9, 128);
	        atualizarStatus(bd, lblNewLabel_1_10, 129);
	        atualizarStatus(bd, lblNewLabel_1_11, 130);
	        atualizarStatus(bd, lblNewLabel_1_12, 131);
	        atualizarStatus(bd, lblNewLabel_1_13, 132);
	        
	      
	        atualizarStatus(bd, lblNewLabel_2_3_5, 117);
	        atualizarStatus(bd, lblNewLabel_2_3_6, 118);
	        atualizarStatus(bd, lblNewLabel_2_3_7, 119);
	        atualizarStatus(bd, lblNewLabel_2_3_8, 120); 
	        atualizarStatus(bd, lblNewLabel_2_3_8_1, 121);
	        atualizarStatus(bd, lblNewLabel_2_3_8_2, 122);
	        atualizarStatus(bd, lblNewLabel_2_3_8_3, 123);
	        atualizarStatus(bd, lblNewLabel_2_3_8_4, 124);
	        
	      
	        atualizarStatus(bd, lblNewLabel_2, 109);
	        atualizarStatus(bd, lblNewLabel_2_1, 110);
	        atualizarStatus(bd, lblNewLabel_2_2, 111);
	        atualizarStatus(bd, lblNewLabel_2_3, 112);
	        atualizarStatus(bd, lblNewLabel_2_3_1, 113);
	        atualizarStatus(bd, lblNewLabel_2_3_2, 114);
	        atualizarStatus(bd, lblNewLabel_2_3_3, 115);
	        atualizarStatus(bd, lblNewLabel_2_3_4, 116);
	        
	     
	        atualizarStatus(bd, lblNewLabel_1, 101);
	        atualizarStatus(bd, lblNewLabel_1_1, 102);
	        atualizarStatus(bd, lblNewLabel_1_2, 103);
	        atualizarStatus(bd, lblNewLabel_1_3, 104);
	        atualizarStatus(bd, lblNewLabel_1_4, 105);
	        atualizarStatus(bd, lblNewLabel_1_5_1, 106);
	        atualizarStatus(bd, lblNewLabel_1_5, 107);
	        atualizarStatus(bd, lblNewLabel_1_5_2, 108);
	        
	        bd.desconectar();
	   
	        frame.getContentPane().setComponentZOrder(lblNewLabel, frame.getContentPane().getComponentCount() - 1);
	        
	       
	        frame.getContentPane().revalidate();
	        frame.getContentPane().repaint();
	    }
	}
}