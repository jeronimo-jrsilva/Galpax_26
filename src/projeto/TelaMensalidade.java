package projeto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaMensalidade {

	private JFrame frmMensalidade;
	private JTable tblMensalidade;
	private DefaultTableModel modeloTabela;
	private String emailSessao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaMensalidade("loja01@galpax.com").visivel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaMensalidade(String email) {
		this.emailSessao = email;
	}

	public void visivel() {
		initialize();
		preencherTabelaPorEmail();
	}

	private void initialize() {

		frmMensalidade = new JFrame();
		frmMensalidade.setTitle("Mensalidades - " + emailSessao);
		frmMensalidade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMensalidade.getContentPane().setLayout(null);

		
		GerenciadorJanelas.registrarInstancia(this);

		
		JButton btnVoltaratt = new JButton("");
		btnVoltaratt.setIcon(new ImageIcon(TelaMensalidade.class.getResource(
				"/imagens/img_mensalidade/img_mensalidade_btn_voltar.png"
		)));
		btnVoltaratt.setContentAreaFilled(false);
		btnVoltaratt.setBorderPainted(false);
		btnVoltaratt.setFocusPainted(false);
		btnVoltaratt.setBounds(50, 50, 159, 74);

		btnVoltaratt.addActionListener(e -> {
			new telaComum(emailSessao).visivel();
			frmMensalidade.dispose();
		});

		frmMensalidade.getContentPane().add(btnVoltaratt);

		tblMensalidade = new JTable();
		tblMensalidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tblMensalidade.setRowHeight(40);
		tblMensalidade.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		
		tblMensalidade.setOpaque(false);
		tblMensalidade.setBackground(new Color(0, 0, 0, 0));
		tblMensalidade.setForeground(Color.WHITE);
		tblMensalidade.setGridColor(new Color(120, 70, 255, 120));
		tblMensalidade.setShowGrid(true);
		tblMensalidade.setIntercellSpacing(new Dimension(1, 1));

		
		tblMensalidade.setSelectionBackground(new Color(80, 40, 180));
		tblMensalidade.setSelectionForeground(Color.WHITE);

		modeloTabela = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Empreendimento",
						"Vencimento",
						"Pagamento",
						"Valor",
						"Status",
						"Ação"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblMensalidade.setModel(modeloTabela);

		
		tblMensalidade.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		tblMensalidade.getTableHeader().setForeground(Color.WHITE);
		tblMensalidade.getTableHeader().setBackground(new Color(20, 15, 70));
		tblMensalidade.getTableHeader().setOpaque(false);
		tblMensalidade.getTableHeader().setReorderingAllowed(false);
		tblMensalidade.getTableHeader().setResizingAllowed(false);

		
		DefaultTableCellRenderer rendererPadrao = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(
					JTable table,
					Object value,
					boolean isSelected,
					boolean hasFocus,
					int row,
					int column
			) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(
						table,
						value,
						isSelected,
						hasFocus,
						row,
						column
				);

				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setForeground(Color.WHITE);
				label.setFont(new Font("Tahoma", Font.PLAIN, 18));
				label.setBorder(null);

				if (isSelected) {
					label.setOpaque(true);
					label.setBackground(new Color(80, 40, 180));
				} else {
					label.setOpaque(false);
					label.setBackground(new Color(0, 0, 0, 0));
				}

				return label;
			}
		};

		tblMensalidade.setDefaultRenderer(Object.class, rendererPadrao);

		
		tblMensalidade.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(
					JTable table,
					Object value,
					boolean isSelected,
					boolean hasFocus,
					int row,
					int column
			) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(
						table,
						value,
						isSelected,
						hasFocus,
						row,
						column
				);

				if (value != null) {
					label.setText("<html><u>" + value.toString() + "</u></html>");
				}

				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Tahoma", Font.BOLD, 18));
				label.setForeground(new Color(80, 220, 255));
				label.setBorder(null);

				if (isSelected) {
					label.setOpaque(true);
					label.setBackground(new Color(80, 40, 180));
				} else {
					label.setOpaque(false);
					label.setBackground(new Color(0, 0, 0, 0));
				}

				return label;
			}
		});

		tblMensalidade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tblMensalidade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int linha = tblMensalidade.getSelectedRow();
				int coluna = tblMensalidade.getSelectedColumn();

				if (linha != -1 && coluna == 5) {

					String empreendimento = modeloTabela.getValueAt(linha, 0).toString();
					String status = modeloTabela.getValueAt(linha, 4).toString();

					if ("Paga".equalsIgnoreCase(status) || "Pago".equalsIgnoreCase(status)) {
						new TelaRecibo(frmMensalidade, empreendimento);
					} else {
						new TelaOpcoesPagamento(frmMensalidade, empreendimento);
					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tblMensalidade);
		scrollPane.setBounds(164, 296, 1607, 393);

		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);

		
		scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));

		frmMensalidade.getContentPane().add(scrollPane);

		JButton btnHistoricoatt = new JButton("");
		btnHistoricoatt.setIcon(new ImageIcon(TelaMensalidade.class.getResource(
				"/imagens/img_mensalidade/img_mensalidade_historico.png"
		)));
		btnHistoricoatt.setFont(new Font("Arial", Font.PLAIN, 20));
		btnHistoricoatt.setBounds(644, 710, 679, 74);
		btnHistoricoatt.setOpaque(false);
		btnHistoricoatt.setBorder(null);
		btnHistoricoatt.setFocusPainted(false);
		btnHistoricoatt.setContentAreaFilled(false);
		btnHistoricoatt.setBorderPainted(false);
		btnHistoricoatt.addActionListener(e -> {

			int linha = tblMensalidade.getSelectedRow();

			if (linha != -1) {
				String empreendimento = modeloTabela.getValueAt(linha, 0).toString();
				new TelaHistorico(empreendimento);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma loja para ver o histórico.");
			}
		});

		frmMensalidade.getContentPane().add(btnHistoricoatt);

		JLabel lblFundoatt = new JLabel("");
		lblFundoatt.setIcon(new ImageIcon(TelaMensalidade.class.getResource(
				"/imagens/img_mensalidade/img_mensalidade_fundo.png"
		)));
		lblFundoatt.setBounds(0, 0, 1920, 1080);
		frmMensalidade.getContentPane().add(lblFundoatt);

		GerenciadorJanelas.configurarJanela(frmMensalidade);
		frmMensalidade.setVisible(true);
	}

	private void preencherTabelaPorEmail() {

		Bancodedados bd = new Bancodedados();
		bd.conectar();

		if (bd.verificar()) {

			modeloTabela.setRowCount(0);

			String sql = "SELECT m.*, l.nome_loja "
					+ "FROM mensalidade m "
					+ "INNER JOIN cad_loja l ON m.id_loja = l.sala_loja "
					+ "WHERE l.email_loja = ?";

			try (PreparedStatement pstmt = bd.conexão.prepareStatement(sql)) {

				pstmt.setString(1, emailSessao);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {

					String status = rs.getString("status");

					String acao;

					if ("Paga".equalsIgnoreCase(status) || "Pago".equalsIgnoreCase(status)) {
						acao = "Ver Recibo";
					} else {
						acao = "Pagar Agora";
					}

					modeloTabela.addRow(new Object[] {
							rs.getString("nome_loja"),
							rs.getDate("vencimento"),
							rs.getDate("data_pagamento"),
							rs.getDouble("mensalidade"),
							status,
							acao
					});
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao carregar mensalidades.");
			} finally {
				bd.desconectar();
			}

		} else {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.");
		}
	}
}