package projeto;

import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);

		// PADRÃO: TOP-LEFT (VOLTAR)
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(TelaHistorico.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(10, 10, 104, 35);
		btnVoltar.addActionListener(e -> frame.dispose());
		frame.getContentPane().add(btnVoltar);

		JLabel lblTitulo = new JLabel("Histórico de Pagamentos");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 24, 764, 30);
		frame.getContentPane().add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Empreendimento: " + empreendimentoFiltro);
		lblSubtitulo.setForeground(new Color(255, 255, 255));
		lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSubtitulo.setBounds(37, 65, 500, 25);
		frame.getContentPane().add(lblSubtitulo);

		tblHistorico = new JTable();
		modeloTabela = new DefaultTableModel(
			new Object[][] {},
			new String[] { "Vencimento", "Data de Pagamento", "Valor", "Status" }
		) {
			@Override public boolean isCellEditable(int row, int column) { return false; }
		};
		tblHistorico.setModel(modeloTabela);

		JScrollPane scrollPane = new JScrollPane(tblHistorico);
		scrollPane.setBounds(37, 110, 710, 300);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblFundoHistorico = new JLabel("");
		lblFundoHistorico.setIcon(new ImageIcon(TelaHistorico.class.getResource("/imagens/fundopagamento.png")));
		lblFundoHistorico.setBounds(0, 0, 800, 500);
		frame.getContentPane().add(lblFundoHistorico);

		frame.setVisible(true);
	}

	private void preencherTabelaHistorico() {
		Bancodedados bd = new Bancodedados();
		bd.conectar();
		if (bd.verificar()) {
			List<Mensalidade> lista = bd.listarMensalidades();
			modeloTabela.setRowCount(0); 
			for (Mensalidade m : lista) {
				if (m.get_nome_loja().equalsIgnoreCase(empreendimentoFiltro)) {
					modeloTabela.addRow(new Object[] { m.get_vencimento(), m.getdata_pagamento(), m.get_mensalidade(), m.get_status() });
				}
			}
			bd.desconectar();
		}
	}
}
