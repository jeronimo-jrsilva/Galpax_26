package projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class VisualizaçãoCarros {

	private JFrame VisualizaçaoFrame;
	private JTable table;
	private DefaultTableModel modelo; 
	private JButton btnVoltar;

	public VisualizaçãoCarros() {
		initialize();
		preencherTabela(); 
	}

	public void visivel() {
		VisualizaçaoFrame.setVisible(true);
	}

	private void initialize() {
		VisualizaçaoFrame = new JFrame();
		VisualizaçaoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VisualizaçaoFrame.setUndecorated(true); 

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		VisualizaçaoFrame.setSize(screenSize.width, screenSize.height);
		VisualizaçaoFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		VisualizaçaoFrame.getContentPane().setLayout(null);

		// JTable e JScrollPane estilizados e transparentes
		table = new JTable() {
			@Override
			protected void paintComponent(java.awt.Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		table.setOpaque(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(20, 15, 60, 200));
		table.setRowHeight(40);
		table.setGridColor(new Color(100, 80, 240, 150));
		table.setShowGrid(true);
		
		modelo = new DefaultTableModel(
			new Object[][] {},
			new String[] { "Modelo", "Placa", "CNH do Dono" }
		) {
			@Override public boolean isCellEditable(int row, int column) { return false; }
		};
		table.setModel(modelo);

		// Estilização do cabeçalho da tabela
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setBackground(new Color(15, 10, 45));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		// Renderer customizado para centralizar texto e manter transparência
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setOpaque(isSelected);
				if (isSelected) {
					label.setBackground(new Color(80, 40, 180));
				}
				return label;
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(200, 250, 1520, 600);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));
		VisualizaçaoFrame.getContentPane().add(scrollPane);
		
		// Botão Voltar no padrão de posicionamento superior
		btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(VisualizaçãoCarros.class.getResource("/imagens/img_cad_veiculo/img_cad_veiculo_btn_voltar.png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setOpaque(false);
		btnVoltar.setBounds(50, 50, 155, 83);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaAdmin("joao.admin@email.com").visivel();
				VisualizaçaoFrame.dispose();
			}
		});
		VisualizaçaoFrame.getContentPane().add(btnVoltar);

		// Título da Tela
		JLabel lblTitulo = new JLabel("VEÍCULOS CADASTRADOS");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
		lblTitulo.setBounds(200, 170, 800, 50);
		VisualizaçaoFrame.getContentPane().add(lblTitulo);

		// Fundo Temático de Veículos
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(VisualizaçãoCarros.class.getResource("/imagens/telamenuprincipal.png")));
		lblFundo.setBounds(0, 0, 1920, 1080);
		VisualizaçaoFrame.getContentPane().add(lblFundo);

		GerenciadorJanelas.configurarJanela(VisualizaçaoFrame);
	}

	public void preencherTabela() {
		Bancodedados bd = new Bancodedados();
		bd.conectar();
		
		if (bd.verificar()) {
			try {
				ResultSet rs = bd.listarVeiculos();
				modelo.setRowCount(0); 
				while (rs != null && rs.next()) {
					modelo.addRow(new Object[] {
						rs.getString("modelo_carro"),
						rs.getString("placa_carro"),
						rs.getString("cnh_carro")
					});
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela: " + e.getMessage());
			} finally {
				bd.desconectar(); 
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.");
		}
	}
}