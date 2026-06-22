package projeto;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
public class VisualizaçãoCarros {

	private JFrame VisualizaçaoFrame;
	private JTable table;
	private DefaultTableModel modelo; 
	private JButton btnVoltar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizaçãoCarros window = new VisualizaçãoCarros();
					window.VisualizaçaoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizaçãoCarros() {
		initialize();
		preencherTabela(); 
	}
	public void visivel() {
		VisualizaçãoCarros window = new VisualizaçãoCarros();
		window.VisualizaçaoFrame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		VisualizaçaoFrame = new JFrame();
		VisualizaçaoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VisualizaçaoFrame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		VisualizaçaoFrame.setSize(screenSize.width, screenSize.height);
		VisualizaçaoFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		VisualizaçaoFrame.getContentPane().setLayout(null);

		// FUNDO DA TELA
		JLabel lblFundoatt = new JLabel("");
		lblFundoatt.setIcon(new ImageIcon(VisualizaçãoCarros.class.getResource(
				"/imagens/img_vis_veiculos/img_vis_veiculos.png"
		)));
		lblFundoatt.setBounds(0, 0, 1920, 1080);
		lblFundoatt.setLayout(null);
		VisualizaçaoFrame.getContentPane().add(lblFundoatt);

		// SCROLLPANE DENTRO DO RETÂNGULO DA IMAGEM
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 315, 1670, 560);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(110, 70, 255), 2));
		lblFundoatt.add(scrollPane);

		table = new JTable();

		modelo = new DefaultTableModel();
		modelo.addColumn("Modelo");
		modelo.addColumn("Placa");
		modelo.addColumn("CNH do Dono");
		table.setModel(modelo);

		estilizarTabela();

		scrollPane.setViewportView(table);

		btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaAdmin("joao.admin@email.com").visivel();
				VisualizaçaoFrame.dispose();
			}
		});

		btnVoltar.setIcon(new ImageIcon(VisualizaçãoCarros.class.getResource(
				"/imagens/img_mensalidade/img_mensalidade_btn_voltar.png"
		)));

		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setFocusPainted(false);
		btnVoltar.setOpaque(false);
		btnVoltar.setBounds(895, 980, 149, 51);
		lblFundoatt.add(btnVoltar);

		GerenciadorJanelas.configurarJanela(VisualizaçaoFrame);
	}
	private void estilizarTabela() {

		table.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(8, 12, 45, 210));
		table.setRowHeight(42);

		table.setGridColor(new Color(95, 65, 220));
		table.setShowGrid(true);

		table.setSelectionBackground(new Color(90, 60, 200));
		table.setSelectionForeground(Color.WHITE);

		table.setOpaque(false);

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 24));
		header.setForeground(Color.WHITE);
		header.setBackground(new Color(12, 18, 65));
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		header.setPreferredSize(new Dimension(header.getWidth(), 50));

		DefaultTableCellRenderer centralizar = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(
					JTable table,
					Object value,
					boolean isSelected,
					boolean hasFocus,
					int row,
					int column) {

				Component c = super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);

				setHorizontalAlignment(CENTER);
				setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));

				if (isSelected) {
					c.setBackground(new Color(90, 60, 200));
					c.setForeground(Color.WHITE);
				} else {
					if (row % 2 == 0) {
						c.setBackground(new Color(9, 15, 50, 220));
					} else {
						c.setBackground(new Color(15, 22, 70, 220));
					}
					c.setForeground(Color.WHITE);
				}

				return c;
			}
		};

		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centralizar);
		}
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