package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

	private final Color COR_PLACEHOLDER = new Color(180, 180, 180);
	private final Color COR_TEXTO = Color.WHITE;

	private final String PLACEHOLDER_NOME = "Digite seu nome aqui";
	private final String PLACEHOLDER_CPF = "Digite seu CPF aqui";
	private final String PLACEHOLDER_EMAIL = "Digite seu e-mail aqui";
	private final String PLACEHOLDER_SENHA = "Digite sua senha aqui";
	private final String PLACEHOLDER_CONFIRMAR = "Confirme sua senha aqui";

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

	public telaCadAdmin() {
		initialize();
	}

	public void visivel() {
		this.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		GerenciadorTeclado.getInstance().inicializar(frame);

		txt_nome = new JTextField();
		txt_nome.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_nome.setBounds(594, 223, 441, 60);
		txt_nome.setColumns(10);
		txt_nome.setOpaque(false);
		txt_nome.setBorder(null);
		frame.getContentPane().add(txt_nome);
		aplicarPlaceholder(txt_nome, PLACEHOLDER_NOME);
		GerenciadorTeclado.getInstance().registrarCampo(txt_nome);

		txt_senha = new JPasswordField();
		txt_senha.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_senha.setBounds(1125, 227, 362, 52);
		txt_senha.setOpaque(false);
		txt_senha.setBorder(null);
		frame.getContentPane().add(txt_senha);
		aplicarPlaceholderSenha(txt_senha, PLACEHOLDER_SENHA);
		GerenciadorTeclado.getInstance().registrarCampo(txt_senha);

		txt_cpf = new JTextField();
		txt_cpf.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_cpf.setColumns(10);
		txt_cpf.setBounds(595, 360, 440, 66);
		txt_cpf.setOpaque(false);
		txt_cpf.setBorder(null);
		frame.getContentPane().add(txt_cpf);
		aplicarPlaceholder(txt_cpf, PLACEHOLDER_CPF);
		GerenciadorTeclado.getInstance().registrarCampo(txt_cpf);

		txt_confirmar = new JPasswordField();
		txt_confirmar.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_confirmar.setBounds(1125, 396, 489, 52);
		txt_confirmar.setOpaque(false);
		txt_confirmar.setBorder(null);
		frame.getContentPane().add(txt_confirmar);
		aplicarPlaceholderSenha(txt_confirmar, PLACEHOLDER_CONFIRMAR);
		GerenciadorTeclado.getInstance().registrarCampo(txt_confirmar);

		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_email.setColumns(10);
		txt_email.setBounds(595, 495, 440, 52);
		txt_email.setOpaque(false);
		txt_email.setBorder(null);
		frame.getContentPane().add(txt_email);
		aplicarPlaceholder(txt_email, PLACEHOLDER_EMAIL);
		GerenciadorTeclado.getInstance().registrarCampo(txt_email);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(telaCadAdmin.class.getResource("/imagens/img_cad_adm/btn_cad_admin.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 31));
		btnNewButton.setBounds(538, 570, 1076, 91);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = pegarTexto(txt_nome, PLACEHOLDER_NOME);
				String cpf = pegarTexto(txt_cpf, PLACEHOLDER_CPF);
				String email = pegarTexto(txt_email, PLACEHOLDER_EMAIL);
				String senha = pegarSenha(txt_senha, PLACEHOLDER_SENHA);
				String confirmarSenha = pegarSenha(txt_confirmar, PLACEHOLDER_CONFIRMAR);

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

				String cpfLimpo = cpf.replaceAll("[^0-9]", "");

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

				try {
					bd.conectar();

					if (bd.verificar()) {

						boolean cadastrado = bd.inserirAdmin(nome, cpfLimpo, email, senha);

						if (cadastrado) {
							JOptionPane.showMessageDialog(
								frame,
								"Administrador cadastrado com sucesso!",
								"Cadastro Realizado",
								JOptionPane.INFORMATION_MESSAGE
							);

							limparCampos();
						}

					} else {
						JOptionPane.showMessageDialog(
							frame,
							"Não foi possível conectar ao Banco de Dados.",
							"Erro de Conexão",
							JOptionPane.ERROR_MESSAGE
						);
					}

				} finally {
					bd.desconectar();
				}
			}
		});

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(telaCadAdmin.class.getResource("/imagens/img_cad_adm/img_cad_adm_btn_voltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(50, 50, 104, 35);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaAdmin tel = new telaAdmin();
				tel.visivel();
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnVoltar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(telaCadAdmin.class.getResource("/imagens/img_cad_adm/img_cad_adm_ficha.png")));
		lblNewLabel_2.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(lblNewLabel_2);

		GerenciadorJanelas.configurarJanela(frame);
	}

	private void aplicarPlaceholder(JTextField campo, String placeholder) {
		campo.setText(placeholder);
		campo.setForeground(COR_PLACEHOLDER);

		campo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (campo.getText().equals(placeholder)) {
					campo.setText("");
					campo.setForeground(COR_TEXTO);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (campo.getText().trim().isEmpty()) {
					campo.setText(placeholder);
					campo.setForeground(COR_PLACEHOLDER);
				}
			}
		});
	}

	private void aplicarPlaceholderSenha(JPasswordField campo, String placeholder) {
		char echoPadrao = campo.getEchoChar();

		campo.setEchoChar((char) 0);
		campo.setText(placeholder);
		campo.setForeground(COR_PLACEHOLDER);

		campo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String valor = new String(campo.getPassword());

				if (valor.equals(placeholder)) {
					campo.setText("");
					campo.setEchoChar(echoPadrao);
					campo.setForeground(COR_TEXTO);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String valor = new String(campo.getPassword()).trim();

				if (valor.isEmpty()) {
					campo.setEchoChar((char) 0);
					campo.setText(placeholder);
					campo.setForeground(COR_PLACEHOLDER);
				}
			}
		});
	}

	private String pegarTexto(JTextField campo, String placeholder) {
		String valor = campo.getText().trim();

		if (valor.equals(placeholder)) {
			return "";
		}

		return valor;
	}

	private String pegarSenha(JPasswordField campo, String placeholder) {
		String valor = new String(campo.getPassword()).trim();

		if (valor.equals(placeholder)) {
			return "";
		}

		return valor;
	}

	private void limparCampos() {
		txt_nome.setText(PLACEHOLDER_NOME);
		txt_nome.setForeground(COR_PLACEHOLDER);

		txt_cpf.setText(PLACEHOLDER_CPF);
		txt_cpf.setForeground(COR_PLACEHOLDER);

		txt_email.setText(PLACEHOLDER_EMAIL);
		txt_email.setForeground(COR_PLACEHOLDER);

		txt_senha.setEchoChar((char) 0);
		txt_senha.setText(PLACEHOLDER_SENHA);
		txt_senha.setForeground(COR_PLACEHOLDER);

		txt_confirmar.setEchoChar((char) 0);
		txt_confirmar.setText(PLACEHOLDER_CONFIRMAR);
		txt_confirmar.setForeground(COR_PLACEHOLDER);
	}
}