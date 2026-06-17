package projeto;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;

public class telaEstacionamento {

	private JFrame estacionamento;
	private JTextField placa_txt;
	private JFrame telaOrigem;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEstacionamento window = new telaEstacionamento(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public telaEstacionamento(JFrame telaOrigem) throws ParseException {
		this.telaOrigem = telaOrigem;
		initialize();
	}
	
	public void visivel() throws ParseException {
		estacionamento.setVisible(true);
	}

	private void initialize() throws ParseException {
		
		//TELA
		estacionamento = new JFrame();
		estacionamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		estacionamento.getContentPane().setLayout(null);
		estacionamento.setUndecorated(true); 
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    estacionamento.setSize(screenSize.width, screenSize.height);
	    estacionamento.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    estacionamento.getContentPane().setLayout(null);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
		
        //CAMPO DE TEXTO DA PLACA DO CARRO
        placa_txt = new JFormattedTextField();
        placa_txt.setFont(new Font("Tahoma", Font.PLAIN, 28));
        placa_txt.setBounds(665, 408, 562, 83);
        placa_txt.setOpaque(false);
        placa_txt.setBorder(null);
        estacionamento.getContentPane().add(placa_txt);
        
		//BOTÃO PARA VERIFICAR O VEICULO
		JButton btnEntrada = new JButton("VERIFICAR CARRO");
		btnEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String placa = placa_txt.getText().trim().toUpperCase();
				if (!placa.isEmpty()){
					if (placa.matches("^[A-Z]{3}[0-9]{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$")){
						Bancodedados bd = new Bancodedados();
						bd.conectar();
						if (bd.verificar()) {
							try {
								ResultSet rs = bd.buscarVeiculo(placa);
								if (rs != null && rs.next()) {
									String modelo = rs.getString("modelo_carro");
									String cnh = rs.getString("cnh_carro");
									LocalDateTime agora = LocalDateTime.now();
									DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
									String dataHoraStr = agora.format(formatador);
									String mensagem = "VEÍCULO ENCONTRADO!\n\n" +
													  "Placa: " + placa + "\n" +
													  "Modelo: " + modelo + "\n" +
													  "CNH do Condutor: " + cnh + "\n" +
													  "Data/Hora: " + dataHoraStr;
									
									JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
									imprimirTicket(placa, modelo, cnh, dataHoraStr);
								} else {
									JOptionPane.showMessageDialog(null, "Veículo com a placa " + placa + " não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
								}
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Erro ao processar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} finally {
								bd.desconectar();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
						}	
					} else {
						JOptionPane.showMessageDialog(null, "Placa inválida!", "Aviso", -1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Campo em Branco!", "Aviso", -1);
				}
			}

			//MÉTODO PARA IMPRIMIR TICKET
			private void imprimirTicket(String placa, String modelo, String cnh, String dataHora) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new Printable() {
					@Override
					public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
						
						if (pageIndex > 0) {
							return NO_SUCH_PAGE;
						}
						
						Graphics2D g2d = (Graphics2D) graphics;
						
						g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
						
					
						Font fonteTitulo = new Font("Courier New", Font.BOLD, 12);
						Font fonteCorpo = new Font("Courier New", Font.PLAIN, 10);
						Font fonteRodape = new Font("Courier New", Font.ITALIC, 8);
						
						int y = 15; 
						
						
						g2d.setFont(fonteTitulo);
						g2d.drawString("    GALPAX ESTACIONAMENTO    ", 10, y); y += 15;
						g2d.drawString("-----------------------------", 10, y); y += 15;
						
						
						g2d.setFont(fonteCorpo);
						g2d.drawString("COMPROVANTE DE ENTRADA", 10, y); y += 15;
						g2d.drawString("DATA/HORA: " + dataHora, 10, y); y += 15;
						g2d.drawString("PLACA:     " + placa, 10, y); y += 15;
						g2d.drawString("MODELO:    " + modelo, 10, y); y += 15;
						g2d.drawString("CNH:       " + cnh, 10, y); y += 15;
					
						g2d.setFont(fonteTitulo);
						g2d.drawString("-----------------------------", 10, y); y += 15;
						g2d.setFont(fonteRodape);
						g2d.drawString("   Guarde seu comprovante.   ", 10, y); y += 12;
						g2d.drawString("      Obrigado pela visita!  ", 10, y);
						
						return PAGE_EXISTS;
					}
				});
				
				try {
					
					job.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar imprimir: " + ex.getMessage(), "Erro de Impressão", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEntrada.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnEntrada.setBounds(816, 673, 284, 52);
		estacionamento.getContentPane().add(btnEntrada);
		
        // BOTÃO DE SAIDA(VOLTAR)
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(50, 50, 104, 35);
		btnVoltar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        if (telaOrigem != null) {
		            telaOrigem.setVisible(true);
		        }

		        estacionamento.dispose();
		    }
		});
		estacionamento.getContentPane().add(btnVoltar);
		
		//FUNDO
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon("D:\\Users\\Aluno\\Downloads\\New Project.png"));
		lblFundo.setBounds(0, 0, 1920, 1080);
		estacionamento.getContentPane().add(lblFundo);

        // APLICA GERENCIADOR (CTRL+W)
        GerenciadorJanelas.configurarJanela(estacionamento);
        estacionamento.setVisible(true);
	}
}
