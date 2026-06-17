package projeto;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class telaAdmin {

	private JFrame frameAdmin;
	private String emailSessão;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaAdmin window = new telaAdmin();
					window.frameAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaAdmin() {
		initialize();
	}
public telaAdmin(String email) {
		this.emailSessão = email;
	}

public void visivel() {
	telaAdmin window = new telaAdmin();
	window.frameAdmin.setVisible(true);
}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameAdmin = new JFrame();
	    frameAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frameAdmin.setUndecorated(true); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameAdmin.setSize(screenSize.width, screenSize.height);
	   
		frameAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frameAdmin.getContentPane().setLayout(null);
      
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
		
		JButton btn_Estacionamento = new JButton("");
		btn_Estacionamento.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botao_comun2.png")));
		btn_Estacionamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaEstacionamento t = null;
				try {
					t = new telaEstacionamento(frameAdmin);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameAdmin.setVisible(false);
			}
		});
		JButton btn_Cadastrar_Veiculo = new JButton("");
		btn_Cadastrar_Veiculo.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botao_admin1.png")));
		btn_Cadastrar_Veiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrodeCarros c = new CadastrodeCarros();
				c.visivel();
				frameAdmin.dispose();
			}
		});
		btn_Cadastrar_Veiculo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btn_Cadastrar_Veiculo.setBounds(823, 211, 351, 56);
		frameAdmin.getContentPane().add(btn_Cadastrar_Veiculo);
		btn_Estacionamento.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btn_Estacionamento.setBounds(823, 679, 351, 56);
		frameAdmin.getContentPane().add(btn_Estacionamento);
		
		JButton btnCadastrarLoja = new JButton("");
		btnCadastrarLoja.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botao_admin_2.png")));
		btnCadastrarLoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroLoja c = new CadastroLoja();
				c.visivel();
				frameAdmin.dispose();
			}
		});
		btnCadastrarLoja.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnCadastrarLoja.setBounds(823, 330, 351, 56);
		frameAdmin.getContentPane().add(btnCadastrarLoja);
		
		JButton btnVisualizaoDosGalpes = new JButton("");
		btnVisualizaoDosGalpes.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/_basicSolid small Base (15).png")));
		btnVisualizaoDosGalpes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telavisgalpao t = new telavisgalpao();
				t.visivel();
				frameAdmin.dispose();
			}
		});
		btnVisualizaoDosGalpes.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnVisualizaoDosGalpes.setBounds(823, 443, 351, 56);
		frameAdmin.getContentPane().add(btnVisualizaoDosGalpes);
		
//		JButton btn_Visualizar_Veiculos = new JButton("");
//		btn_Visualizar_Veiculos.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botao_vis (1).png")));
//		btn_Visualizar_Veiculos.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				VisualizaçãoCarros v = new VisualizaçãoCarros();
//				v.visivel();
//				frameAdmin.dispose();
//			}
//		});
//		btn_Visualizar_Veiculos.setFont(new Font("Tahoma", Font.PLAIN, 26));
//		btn_Visualizar_Veiculos.setBounds(823, 562, 351, 56);
//		frameAdmin.getContentPane().add(btn_Visualizar_Veiculos);
		
		JButton btn_Visualizar_Veiculos = new JButton("");

		// --- Ajuste de Redimensionamento Vertical Total (56px) ---
		ImageIcon icon_vis = new
		ImageIcon(telaAdmin.class.getResource("/imagens/botao_vis (1).png"));
		Image img_vis = icon_vis.getImage();

		// Altura baseada no seu setBounds (56)
		int alturaDesejada = 56;
		// Cálculo da largura proporcional para não distorcer
		int larguraProporcional = (img_vis.getWidth(null) *
		alturaDesejada) / img_vis.getHeight(null);

		// Aplica o redimensionamento suave
		Image img_vis_final = img_vis.getScaledInstance(larguraProporcional, alturaDesejada, Image.SCALE_SMOOTH);
		btn_Visualizar_Veiculos.setIcon(new ImageIcon(img_vis_final));
		// -------------------------------------------------------

		btn_Visualizar_Veiculos.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) {
		VisualizaçãoCarros v = new VisualizaçãoCarros();
		v.visivel();
		frameAdmin.dispose();
		}
		});

		btn_Visualizar_Veiculos.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btn_Visualizar_Veiculos.setBounds(823, 562, 351, 56);

		// Ajustes estéticos para garantir que o ícone preencha o espaço sem bordas cinzas
		btn_Visualizar_Veiculos.setBorderPainted(false);
		btn_Visualizar_Veiculos.setContentAreaFilled(false);
		btn_Visualizar_Veiculos.setFocusPainted(false);
		btn_Visualizar_Veiculos.setMargin(new java.awt.Insets(0, 0, 0, 0));

		frameAdmin.getContentPane().add(btn_Visualizar_Veiculos);
		// -------------------------------------------------------

		JButton btnVoltar = new JButton("");
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnVoltar.setBounds(50, 50, 104, 35);
		frameAdmin.getContentPane().add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(null, "Sessão encerrada. Voltando para a tela de login.");
		        
		        Login telaLogin = new Login(); // RETORNO PARA TELA LOGIN
		        telaLogin.visivel(); 
		        		        
		        frameAdmin.dispose(); // FECHA TELA ATUAL
		    }
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/telamenuprincipal.png")));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frameAdmin.getContentPane().add(lblNewLabel);
	}
}
