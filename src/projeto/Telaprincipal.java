package projeto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Telaprincipal extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public Telaprincipal() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 400, 300);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Vaga");
        modelo.addColumn("Status");

        modelo.addRow(new Object[] {"1", "Livre"});
        modelo.addRow(new Object[] {"2", "Ocupada"});
        modelo.addRow(new Object[] {"3", "Livre"});

        table.setModel(modelo);
    }
}
