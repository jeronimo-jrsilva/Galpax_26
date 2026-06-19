package projeto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

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
		
        // REGISTRA PARA CTRL+W
        GerenciadorJanelas.registrarInstancia(this);

		// PADRÃO: TOP-LEFT (VOLTAR)
		JButton btnVoltaratt = new JButton("");
		btnVoltaratt.setIcon(new ImageIcon(TelaMensalidade.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
		btnVoltaratt.setContentAreaFilled(false);
		btnVoltaratt.setBorderPainted(false);
		btnVoltaratt.setBounds(50, 50, 104, 35);
		btnVoltaratt.addActionListener(e -> {
			new telaComum(emailSessao).visivel();
			frmMensalidade.dispose();
		});
		frmMensalidade.getContentPane().add(btnVoltaratt);
		
		tblMensalidade = new JTable();
		tblMensalidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tblMensalidade.setRowHeight(30);
		tblMensalidade.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		modeloTabela = new DefaultTableModel(
			new Object[][] {}, 
			new String[] { "Empreendimento", "Vencimento", "Pagamento", "Valor", "Status", "Ação" }
		) {
			@Override public boolean isCellEditable(int row, int column) { return false; }
		};
		
		tblMensalidade.setModel(modeloTabela);
		
		tblMensalidade.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, 
					boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value != null) label.setText("<html><u>" + value.toString() + "</u></html>");
				label.setForeground(new Color(0, 102, 204));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				return label;
			}
		});
		
		tblMensalidade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		tblMensalidade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = tblMensalidade.getSelectedRow();
				int coluna = tblMensalidade.getSelectedColumn();
				if (coluna == 5 && linha != -1) {
					String status = (String) modeloTabela.getValueAt(linha, 4);
					String empreendimento = (String) modeloTabela.getValueAt(linha, 0);
					if ("Paga".equalsIgnoreCase(status) || "Pago".equalsIgnoreCase(status)) {
						new TelaRecibo(empreendimento);
					} else {
						// Passa o frmMensalidade como pai para a janela flutuante
						new TelaOpcoesPagamento(frmMensalidade, empreendimento);
					}
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(tblMensalidade);
		scrollPane.setBounds(164, 296, 1607, 393); 
		frmMensalidade.getContentPane().add(scrollPane);
		
		JButton btnHistoricoatt = new JButton("Histórico de Pagamento");
		btnHistoricoatt.setIcon(new ImageIcon(TelaMensalidade.class.getResource("/imagens/img_mensalidade/img_mensalidade_historico.png")));
		btnHistoricoatt.setFont(new Font("Arial", Font.PLAIN, 20));
		btnHistoricoatt.setBounds(825, 703, 563, 74);
		btnHistoricoatt.setOpaque(false);
		btnHistoricoatt.setBorder(null);
		btnHistoricoatt.addActionListener(e -> {
			int linha = tblMensalidade.getSelectedRow();
			if (linha != -1) {
				new TelaHistorico((String) modeloTabela.getValueAt(linha, 0));
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma loja para ver o histórico.");
			}
		});
		frmMensalidade.getContentPane().add(btnHistoricoatt);
		
		JLabel lblFundoatt = new JLabel("");
		lblFundoatt.setIcon(new ImageIcon(TelaMensalidade.class.getResource("/imagens/img_mensalidade/img_mensalidade_fundo.png")));
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
			String sql = "SELECT m.*, l.nome_loja FROM mensalidade m INNER JOIN cad_loja l ON m.id_loja = l.id_loja WHERE l.email_loja = ?";
			try (PreparedStatement pstmt = bd.conexão.prepareStatement(sql)) {
				pstmt.setString(1, emailSessao);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String status = rs.getString("status");
					String acao = ("Paga".equalsIgnoreCase(status) || "Pago".equalsIgnoreCase(status)) ? "Ver Recibo" : "Pagar Agora";
					modeloTabela.addRow(new Object[] { rs.getString("nome_loja"), rs.getDate("vencimento"), rs.getDate("data_pagamento"), rs.getDouble("mensalidade"), status, acao });
				}
			} catch (Exception e) { e.printStackTrace(); } finally { bd.desconectar(); }
		}
	}
}
