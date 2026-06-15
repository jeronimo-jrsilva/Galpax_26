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

public class telaEstacionamento {

	private JFrame estacionamento;
	private JFormattedTextField placa_txt;
	MaskFormatter maskPlaca;
	private JTextField modelo_txt;
	private JTextField CNH_txt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEstacionamento window = new telaEstacionamento();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public telaEstacionamento() throws ParseException {
		initialize();
	}
	
	public void visivel() throws ParseException {
		initialize();
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
        
      //CAMPO DE TEXTO DA CNH DO CARRO
        CNH_txt = new JTextField();
        CNH_txt.setOpaque(false);
        CNH_txt.setFont(new Font("Tahoma", Font.PLAIN, 28));
        CNH_txt.setColumns(10);
        CNH_txt.setBorder(null);
        CNH_txt.setBounds(655, 559, 638, 58);
        estacionamento.getContentPane().add(CNH_txt);
        
      //CAMPO DE TEXTO DO MODELO DO CARRO
        modelo_txt = new JTextField();
        modelo_txt.setOpaque(false);
        modelo_txt.setFont(new Font("Tahoma", Font.PLAIN, 28));
        modelo_txt.setColumns(10);
        modelo_txt.setBorder(null);
        modelo_txt.setBounds(655, 449, 638, 58);
        estacionamento.getContentPane().add(modelo_txt);
		
        //CAMPO DE TEXTO DA PLACA DO CARRO
        MaskFormatter mascara = new MaskFormatter("UUU####");
        placa_txt = new JFormattedTextField(mascara);
        mascara.setPlaceholderCharacter(' ');
        placa_txt.setFont(new Font("Tahoma", Font.PLAIN, 28));
        placa_txt.setBounds(655, 339, 638, 58);
        placa_txt.setOpaque(false);
        placa_txt.setBorder(null);
        estacionamento.getContentPane().add(placa_txt);
        
		//BOTÃO DE ENTRADA DO VEÍCULO(CONFIRMAR)
		JButton btnEntrada = new JButton("ENTRADA DE VEICULO");
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
									DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/CustomM/yyyy HH:mm:ss");
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
		btnEntrada.setBounds(839, 673, 284, 52);
		estacionamento.getContentPane().add(btnEntrada);
		
        // BOTÃO DE SAIDA DO VEÍCULO(VOLTAR)
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/botoes_isaac/_comicLight small Base (4).png")));
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBounds(50, 50, 104, 35);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaComum("loja01@galpax.com").visivel(); // Fallback sessão
				estacionamento.dispose();
			}
		});
		estacionamento.getContentPane().add(btnVoltar);
		
		//FUNDO
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/fundo tela cad carro.png")));
		lblFundo.setBounds(0, 0, 1920, 1080);
		estacionamento.getContentPane().add(lblFundo);

        // APLICA GERENCIADOR (CTRL+W)
        GerenciadorJanelas.configurarJanela(estacionamento);
        estacionamento.setVisible(true);
	}
}
