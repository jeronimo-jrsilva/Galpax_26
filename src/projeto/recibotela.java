package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class recibotela {

	private JFrame frame;

	private int idMensalidade;
	private String empreendimento = "";
	private String status = "";
	private String dataPagamento = "";
	private String formaPagamento = "";
	private String codigoRecibo = "";
	private String valorPago = "";

	public recibotela(int idMensalidade) {
		this.idMensalidade = idMensalidade;
		buscarDadosRecibo();
		initialize();
	}

	public recibotela(String empreendimento) {
		this.empreendimento = empreendimento;
		this.status = "PAGO / Confirmado";
		this.dataPagamento = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.codigoRecibo = "REC-" + System.currentTimeMillis();
		this.valorPago = "R$ 0,00";
		initialize();
	}

	public void visivel() {
		frame.setVisible(true);
	}

	private void buscarDadosRecibo() {
		Bancodedados bd = new Bancodedados();
		bd.conectar();

		if (!bd.verificar()) {
			return;
		}

		String sql = "SELECT m.id_mensalidade, m.mensalidade, m.data_pagamento, "
				+ "m.status, m.forma_pagamento, m.codigo_recibo, l.nome_loja "
				+ "FROM mensalidade m "
				+ "INNER JOIN cad_loja l ON m.id_loja = l.id_loja "
				+ "WHERE m.id_mensalidade = ?";

		try (PreparedStatement stmt = bd.conexão.prepareStatement(sql)) {

			stmt.setInt(1, idMensalidade);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {
					formaPagamento = rs.getString("forma_pagamento");
					codigoRecibo = rs.getString("codigo_recibo");

					if (formaPagamento == null || formaPagamento.isEmpty()) {
						formaPagamento = "Não informado";
					}

					if (codigoRecibo == null || codigoRecibo.isEmpty()) {
						codigoRecibo = "REC-" + rs.getInt("id_mensalidade");
					}
					empreendimento = rs.getString("nome_loja");

					status = rs.getString("status");

					if ("Pago".equalsIgnoreCase(status)) {
						status = "";
					}

					if (rs.getDate("data_pagamento") != null) {
						dataPagamento = rs.getDate("data_pagamento")
								.toLocalDate()
								.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					} else {
						dataPagamento = "";
					}

					NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
					valorPago = formatoMoeda.format(rs.getDouble(""));

					codigoRecibo = "REC-" + rs.getInt("id_mensalidade");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		JLabel lblFundoRecibo = new JLabel("");
		lblFundoRecibo.setIcon(redimensionarImagem(
				"/imagens/img_Recibo/img_recibo.png",
				screenSize.width,
				screenSize.height
		));
		lblFundoRecibo.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.getContentPane().add(lblFundoRecibo);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(redimensionarImagem(
				"/imagens/img_mensalidade/img_mensalidade_btn_voltar.png",
				159,
				74
		));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setOpaque(false);
		btnVoltar.setBounds(50, 50, 159, 74);
		btnVoltar.addActionListener(e -> frame.dispose());
		frame.getContentPane().add(btnVoltar);

		JLabel lblEmpreendimentoValor = criarLabel(empreendimento, 468, 415, 450, 35, 22, Font.BOLD);
		frame.getContentPane().add(lblEmpreendimentoValor);

		JLabel lblStatusValor = criarLabel(status, 1095, 440, 270, 35, 18, Font.BOLD);
		lblStatusValor.setForeground(new Color(80, 255, 150));
		frame.getContentPane().add(lblStatusValor);

		JLabel lblReferenteValor = criarLabel("", 468, 575, 350, 35, 22, Font.BOLD);
		frame.getContentPane().add(lblReferenteValor);

		JLabel lblDataValor = criarLabel(dataPagamento, 440, 720, 220, 35, 20, Font.BOLD);
		frame.getContentPane().add(lblDataValor);

		JLabel lblFormaValor = criarLabel(formaPagamento, 760, 720, 300, 35, 20, Font.BOLD);
		frame.getContentPane().add(lblFormaValor);

		JLabel lblCodigoValor = criarLabel(codigoRecibo, 1075, 720, 260, 35, 20, Font.BOLD);
		frame.getContentPane().add(lblCodigoValor);

		JLabel lblValorPago = criarLabel(valorPago, 1415, 720, 220, 35, 20, Font.BOLD);
		frame.getContentPane().add(lblValorPago);

		JLabel lblTotalPago = criarLabel(valorPago, 1370, 875, 220, 35, 22, Font.BOLD);
		lblTotalPago.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblTotalPago);

		JLabel lblMensagem = criarLabel("", 0, 988, 1920, 35, 24, Font.BOLD);
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setForeground(new Color(0, 255, 120));
		frame.getContentPane().add(lblMensagem);

		frame.getContentPane().setComponentZOrder(lblFundoRecibo, frame.getContentPane().getComponentCount() - 1);

		GerenciadorJanelas.registrarInstancia(this);
		GerenciadorJanelas.configurarJanela(frame);
	}

	private JLabel criarLabel(String texto, int x, int y, int largura, int altura, int tamanhoFonte, int estilo) {
		JLabel label = new JLabel(texto);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", estilo, tamanhoFonte));
		label.setBounds(x, y, largura, altura);
		return label;
	}

	private ImageIcon redimensionarImagem(String caminho, int largura, int altura) {
		ImageIcon icon = new ImageIcon(recibotela.class.getResource(caminho));
		Image img = icon.getImage();
		Image imgRedimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
		return new ImageIcon(imgRedimensionada);
	}
}