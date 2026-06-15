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

public class VisualizaçãoCarros {

	private JFrame VisualizaçaoFrame;
	private JTable table;
	private DefaultTableModel modelo; 

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
				
		VisualizaçaoFrame  = new JFrame();
		VisualizaçaoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VisualizaçaoFrame.setUndecorated(true); 

	    
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    VisualizaçaoFrame.setSize(screenSize.width, screenSize.height);
	   
	    VisualizaçaoFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    VisualizaçaoFrame.getContentPane().setLayout(null);
      
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1904, 1041);
		VisualizaçaoFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Modelo");
		modelo.addColumn("Placa");
		modelo.addColumn("CNH do Dono");
		table.setModel(modelo);
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