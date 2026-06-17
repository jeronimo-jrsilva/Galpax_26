package projeto;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class telaCadAdmin {

	private JFrame frame;
	private JTextField txt_nome;
	private JTextField txt_cpf;
	private JTextField txt_email;
	private JPasswordField txt_senha;
	private JPasswordField txt_confirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaCadAdmin window = new telaCadAdmin();
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
	public telaCadAdmin() {
		initialize();
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
		
//		JButton btnVoltar = new JButton("");
//		btnVoltar.setIcon(new ImageIcon(telaCadAdmin.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
//		btnVoltar.setContentAreaFilled(false);
//		btnVoltar.setBorderPainted(false);
//		btnVoltar.setBounds(1789, 1007, 117, 58);
//		frame.getContentPane().add(btnVoltar);
		
		txt_nome = new JTextField();
		txt_nome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_nome.setBounds(227, 219, 1289, 58);
		frame.getContentPane().add(txt_nome);
		txt_nome.setColumns(10);
		txt_nome.setOpaque(false);      
		txt_nome.setBorder(null);
		
		txt_cpf = new JTextField();
		txt_cpf.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_cpf.setColumns(10);
		txt_cpf.setBounds(227, 334, 1289, 52);
		frame.getContentPane().add(txt_cpf);
		txt_cpf.setOpaque(false);      
		txt_cpf.setBorder(null);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_email.setColumns(10);
		txt_email.setBounds(226, 442, 1289, 58);
		frame.getContentPane().add(txt_email);
		txt_email.setOpaque(false);      
		txt_email.setBorder(null);
		
		txt_senha = new JPasswordField();
		txt_senha.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_senha.setBounds(227, 565, 1289, 60);
		frame.getContentPane().add(txt_senha);
		txt_senha.setOpaque(false);      
		txt_senha.setBorder(null);
		
		txt_confirmar = new JPasswordField();
		txt_confirmar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_confirmar.setBounds(460, 681, 1056, 66);
		frame.getContentPane().add(txt_confirmar);
		txt_confirmar.setOpaque(false);      
		txt_confirmar.setBorder(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setIcon(new ImageIcon(telaCadAdmin.class.getResource("/imagens/botao_admin_6.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 31));
		btnNewButton.setBounds(791, 803, 436, 58);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String nome = txt_nome.getText().trim();
				String cpf = txt_cpf.getText().trim();
				String email = txt_email.getText().trim();
				String senha = new String(txt_senha.getPassword()).trim();
				String confirmarSenha = new String(txt_confirmar.getPassword()).trim();
				
				
				if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			
				if (!nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
					JOptionPane.showMessageDialog(frame, "O nome digitado é inválido! Não use números ou símbolos.", "Nome Inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (!nome.contains(" ")) {
					JOptionPane.showMessageDialog(frame, "Por favor, insira o nome completo (Nome e Sobrenome).", "Nome Incompleto", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				String cpfLimpo = cpf.replaceAll("[^0-9]", ""); // Mantém só números
				if (cpfLimpo.length() != 11) {
					JOptionPane.showMessageDialog(frame, "O CPF deve conter exatamente 11 dígitos numéricos!", "CPF Inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
				Pattern pattern = Pattern.compile(emailRegex);
				if (!pattern.matcher(email).matches()) {
					JOptionPane.showMessageDialog(frame, "O formato do e-mail digitado é inválido!\nExemplo correto: nome@dominio.com", "E-mail Inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				if (senha.length() < 6) {
					JOptionPane.showMessageDialog(frame, "A senha deve conter no mínimo 6 caracteres por segurança!", "Senha Fraca", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			
				if (!senha.equals(confirmarSenha)) {
					JOptionPane.showMessageDialog(frame, "As senhas digitadas não coincidem!", "Erro de Confirmação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				Bancodedados bd = new Bancodedados();
				bd.conectar();
				
				if (bd.verificar()) {
					
					bd.inserirAdmin(nome, cpfLimpo, email, senha);
					
					
					txt_nome.setText("");
					txt_cpf.setText("");
					txt_email.setText("");
					txt_senha.setText("");
					txt_confirmar.setText("");
					
					bd.desconectar();
				} else {
					JOptionPane.showMessageDialog(frame, "Não foi possível conectar ao Banco de Dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		

				JButton btnVoltar = new JButton("");
				btnVoltar.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
				btnVoltar.setContentAreaFilled(false);
				btnVoltar.setBorderPainted(false);
				btnVoltar.setBounds(50, 50, 104, 35);
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				frame.getContentPane().add(btnVoltar);
				
				
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(telaCadAdmin.class.getResource("/imagens/telafundocadadmin.png")));
		lblNewLabel_2.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblNewLabel_2);
	}
}