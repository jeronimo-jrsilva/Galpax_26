package projeto;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class TelaHistorico {

	private JFrame frame;
	private JTable tblHistorico;
	private DefaultTableModel modeloTabela;
	private String empreendimentoFiltro;

	public TelaHistorico(String empreendimento) {
		this.empreendimentoFiltro = empreendimento;
		initialize();
		preencherTabelaHistorico();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Histórico - " + empreendimentoFiltro);
		frame.setBounds(150, 150, 800, 500);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(TelaHistorico.class.getResource(
				"/imagens/botoes_isaac/_comicLight small Base (4).png"
		)));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBounds(10, 10, 104, 35);
		btnVoltar.addActionListener(e -> frame.dispose());
		frame.getContentPane().add(btnVoltar);

		JLabel lblTitulo = new JLabel("Histórico de Pagamentos");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 24, 764, 30);
		frame.getContentPane().add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Empreendimento: " + empreendimentoFiltro);
		lblSubtitulo.setForeground(Color.WHITE);
		lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSubtitulo.setBounds(37, 65, 700, 25);
		frame.getContentPane().add(lblSubtitulo);

		tblHistorico = new JTable();
		tblHistorico.setFont(new Font("Arial", Font.PLAIN, 16));
		tblHistorico.setRowHeight(30);

		modeloTabela = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Vencimento",
						"Data de Pagamento",
						"Valor",
						"Status"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblHistorico.setModel(modeloTabela);

		JScrollPane scrollPane = new JScrollPane(tblHistorico);
		scrollPane.setBounds(37, 110, 710, 300);
		frame.getContentPane().add(scrollPane);

		JLabel lblFundoHistorico = new JLabel("");
		lblFundoHistorico.setIcon(new ImageIcon(TelaHistorico.class.getResource(
				"/imagens/fundopagamento.png"
		)));
		lblFundoHistorico.setBounds(0, 0, 800, 500);
		frame.getContentPane().add(lblFundoHistorico);

		frame.setVisible(true);
	}

	private void preencherTabelaHistorico() {
		Bancodedados bd = new Bancodedados();
		bd.conectar();

		if (!bd.verificar()) {
			JOptionPane.showMessageDialog(
					null,
					"Erro ao conectar com o banco de dados.",
					"Erro",
					JOptionPane.ERROR_MESSAGE
			);
			return;
		}

		String sql = "SELECT m.vencimento, m.data_pagamento, m.mensalidade, m.status "
				+ "FROM mensalidade m "
				+ "INNER JOIN cad_loja l ON m.id_loja = l.id_loja "
				+ "WHERE l.nome_loja = ? "
				+ "AND m.status = 'Pago' "
				+ "ORDER BY m.data_pagamento DESC";

		try (PreparedStatement pstmt = bd.conexão.prepareStatement(sql)) {

			pstmt.setString(1, empreendimentoFiltro);

			try (ResultSet rs = pstmt.executeQuery()) {

				modeloTabela.setRowCount(0);

				boolean encontrou = false;

				while (rs.next()) {
					encontrou = true;

					String vencimento = formatarData(rs.getDate("vencimento"));
					String dataPagamento = formatarData(rs.getDate("data_pagamento"));
					String valor = formatarValor(rs.getDouble("mensalidade"));
					String status = rs.getString("status");

					modeloTabela.addRow(new Object[] {
							vencimento,
							dataPagamento,
							valor,
							status
					});
				}

				if (!encontrou) {
					JOptionPane.showMessageDialog(
							null,
							"Nenhum pagamento encontrado para este empreendimento.",
							"Histórico",
							JOptionPane.INFORMATION_MESSAGE
					);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(
					null,
					"Erro ao carregar histórico de pagamentos: " + e.getMessage(),
					"Erro",
					JOptionPane.ERROR_MESSAGE
			);

		} finally {
			bd.desconectar();
		}
	}

	private String formatarData(java.sql.Date data) {
		if (data == null) {
			return "";
		}

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return data.toLocalDate().format(formato);
	}

	private String formatarValor(double valor) {
		NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return formatoMoeda.format(valor);
	}
}