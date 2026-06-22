package projeto;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
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

        // TELA
        estacionamento = new JFrame();
        estacionamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        estacionamento.getContentPane().setLayout(null);
        estacionamento.setUndecorated(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        estacionamento.setSize(screenSize.width, screenSize.height);
        estacionamento.setExtendedState(JFrame.MAXIMIZED_BOTH);
        estacionamento.getContentPane().setLayout(null);

        // INICIALIZA O GERENCIADOR DO TECLADO
        GerenciadorTeclado.getInstance().inicializar(estacionamento);

        placa_txt = new JTextField();
        placa_txt.setFont(new Font("Tahoma", Font.PLAIN, 28));
        placa_txt.setBounds(672, 279, 562, 83);
        estacionamento.getContentPane().add(placa_txt);

        // FILTRO DA PLACA: aceita ABC1234 ou ABC1D23
        ((AbstractDocument) placa_txt.getDocument()).setDocumentFilter(new FiltroPlaca());

        // PLACA USA TECLADO COMPLETO, POIS TEM LETRAS E NÚMEROS
        GerenciadorTeclado.getInstance().registrarCampo(placa_txt);

        // BOTÃO PARA VERIFICAR O VEÍCULO
        JButton btnEntrada = new JButton("");
        btnEntrada.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/img_estacionamento/btn_entrada.png")));
        btnEntrada.setContentAreaFilled(false);
        btnEntrada.setBorderPainted(false);
        btnEntrada.setFocusPainted(false);
        btnEntrada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placa_txt.getText().trim().toUpperCase();

                if (!placa.isEmpty()) {

                    if (placa.matches("^[A-Z]{3}[0-9]{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$")) {

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

                                    JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                } else {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Veículo com a placa " + placa + " não encontrado!",
                                            "Aviso",
                                            JOptionPane.WARNING_MESSAGE
                                    );
                                }

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Erro ao processar dados: " + ex.getMessage(),
                                        "Erro",
                                        JOptionPane.ERROR_MESSAGE
                                );

                            } finally {
                                bd.desconectar();
                            }

                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Não foi possível conectar ao banco de dados.",
                                    "Erro",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Placa inválida!", "Aviso", -1);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Campo em Branco!", "Aviso", -1);
                }
            }

            // MÉTODO PARA IMPRIMIR TICKET
            private void imprimirTicket(String placa, String modelo, String cnh, String dataHora) {
                try {
                    PrintService impressoraPOS80 = buscarImpressoraPOS80();

                    if (impressoraPOS80 == null) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Impressora POS-80 não encontrada.\nVerifique o nome da impressora no Windows.",
                                "Erro de Impressão",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    String ESC = "\u001B";

                    StringBuilder ticket = new StringBuilder();

                    // Inicializa a impressora
                    ticket.append(ESC).append("@");

                    // Espaçamento menor entre linhas
                    ticket.append(ESC).append("3").append((char) 24);

                    // Centralizado
                    ticket.append(ESC).append("a").append((char) 1);

                    // Negrito ligado
                    ticket.append(ESC).append("E").append((char) 1);
                    ticket.append("GALPAX ESTACIONAMENTO\n");

                    // Negrito desligado
                    ticket.append(ESC).append("E").append((char) 0);

                    ticket.append("-------------------------------\n");
                    ticket.append("COMPROVANTE DE ENTRADA\n");
                    ticket.append("-------------------------------\n");

                    // Alinhado à esquerda
                    ticket.append(ESC).append("a").append((char) 0);

                    ticket.append("DATA/HORA: ").append(dataHora).append("\n");
                    ticket.append("PLACA:     ").append(limitarTexto(placa, 20)).append("\n");
                    ticket.append("MODELO:    ").append(limitarTexto(modelo, 22)).append("\n");
                    ticket.append("CNH:       ").append(limitarTexto(cnh, 20)).append("\n");

                    ticket.append("-------------------------------\n");

                    // Centralizado
                    ticket.append(ESC).append("a").append((char) 1);
                    ticket.append("Guarde seu comprovante.\n");
                    ticket.append("Obrigado pela visita!\n\n\n");

                    byte[] dados = ticket.toString().getBytes(StandardCharsets.ISO_8859_1);

                    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                    Doc doc = new SimpleDoc(dados, flavor, null);

                    DocPrintJob job = impressoraPOS80.createPrintJob();
                    job.print(doc, null);

                } catch (PrintException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Erro ao tentar imprimir: " + ex.getMessage(),
                            "Erro de Impressão",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

            private PrintService buscarImpressoraPOS80() {
                PrintService[] impressoras = PrintServiceLookup.lookupPrintServices(null, null);

                for (PrintService impressora : impressoras) {
                    System.out.println("Impressora encontrada: " + impressora.getName());

                    if (impressora.getName().toLowerCase().contains("pos-80")) {
                        return impressora;
                    }
                }

                return null;
            }

            private String limitarTexto(String texto, int limite) {
                if (texto == null) {
                    return "";
                }

                texto = removerAcentos(texto);

                if (texto.length() > limite) {
                    return texto.substring(0, limite);
                }

                return texto;
            }

            private String removerAcentos(String texto) {
                String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
                return normalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            }
        });

        btnEntrada.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnEntrada.setBounds(796, 396, 383, 83);
        estacionamento.getContentPane().add(btnEntrada);

        // BOTÃO DE SAÍDA / VOLTAR
        JButton btnVoltar = new JButton("");
        btnVoltar.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/img_mensalidade/img_mensalidade_btn_voltar.png")));
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setBounds(50, 50, 155, 83);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (telaOrigem != null) {
                    telaOrigem.setVisible(true);
                }

                estacionamento.dispose();
            }
        });
        estacionamento.getContentPane().add(btnVoltar);

        // FUNDO
        JLabel lblFundo = new JLabel("");
        lblFundo.setIcon(new ImageIcon(telaEstacionamento.class.getResource("/imagens/img_estacionamento/tela_entrada_Estacionamento.png")));
        lblFundo.setBounds(0, 0, 1920, 1080);
        estacionamento.getContentPane().add(lblFundo);

        // APLICA GERENCIADOR
        GerenciadorJanelas.configurarJanela(estacionamento);

        estacionamento.setVisible(true);
    }

    private static class FiltroPlaca extends DocumentFilter {

        private final int LIMITE = 7;

        @Override
        public void insertString(FilterBypass fb, int offset, String texto, AttributeSet attr)
                throws BadLocationException {

            if (texto == null) {
                return;
            }

            replace(fb, offset, 0, texto, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs)
                throws BadLocationException {

            if (texto == null) {
                return;
            }

            texto = texto.toUpperCase();

            String textoAtual = fb.getDocument().getText(0, fb.getDocument().getLength());
            String novoTexto = textoAtual.substring(0, offset) + texto + textoAtual.substring(offset + length);

            if (novoTexto.length() > LIMITE) {
                return;
            }

            if (placaValidaDuranteDigitacao(novoTexto)) {
                super.replace(fb, offset, length, texto, attrs);
            }
        }

        private boolean placaValidaDuranteDigitacao(String texto) {

            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);

                if (i < 3) {
                    if (!Character.isLetter(c)) {
                        return false;
                    }
                } else if (i == 3) {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                } else if (i == 4) {
                    if (!Character.isLetter(c) && !Character.isDigit(c)) {
                        return false;
                    }
                } else {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}