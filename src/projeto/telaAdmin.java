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
import java.awt.Toolkit;
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
		
		JButton btnNewButton_1_1_1_1 = new JButton("");
		btnNewButton_1_1_1_1.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botao_comun2.png")));
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
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
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/botao_admin1.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrodeCarros c = new CadastrodeCarros();
				c.visivel();
				frameAdmin.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton.setBounds(823, 211, 351, 56);
		frameAdmin.getContentPane().add(btnNewButton);
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton_1_1_1_1.setBounds(823, 679, 351, 56);
		frameAdmin.getContentPane().add(btnNewButton_1_1_1_1);
		
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
		btnVisualizaoDosGalpes.setIcon(new ImageIcon("D:\\Users\\Aluno\\Downloads\\imagem 2+2-1=3\\_basicSolid small Base (15).png"));
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
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon("D:\\Users\\Aluno\\Downloads\\imagem 2+2-1=3\\_basicSolid small Base (14).png"));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizaçãoCarros v = new VisualizaçãoCarros();
				v.visivel();
				frameAdmin.dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton_1_1.setBounds(823, 562, 351, 56);
		frameAdmin.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Encerrar Sessão");
		btnNewButton_1_1_1.setIcon(null);
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton_1_1_1.setBounds(1559, 1000, 351, 56);
		frameAdmin.getContentPane().add(btnNewButton_1_1_1);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Programa encerrando");
				System.exit(0);
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(telaAdmin.class.getResource("/imagens/telamenuprincipal.png")));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frameAdmin.getContentPane().add(lblNewLabel);
	}
}
