package projeto;

import javax.swing.*;
import java.awt.*;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.Insets;

public class confteclado {

    public static JPanel criarTecladoCompleto(JTextField campoDestino) {
        JPanel painel = new JPanel(new GridLayout(4, 10, 2, 2));
        painel.setBackground(Color.GRAY);

        String[] teclas = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "APAGAR",
            "Z", "X", "C", "V", "B", "N", "M", ",", ".", " "
        };

        for (String t : teclas) {
            JButton btn = new JButton(t);
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                if (t.equals("APAGAR")) {
                    String txt = campoDestino.getText();
                    if (txt.length() > 0) campoDestino.setText(txt.substring(0, txt.length() - 1));
                } else {
                    campoDestino.setText(campoDestino.getText() + t);
                }
            });
            painel.add(btn);
        }
        return painel;
    }

    public static JPanel criarTecladoNumerico(JTextField campoDestino) {
        JPanel painel = new JPanel(new GridLayout(4, 3, 2, 2));
        String[] teclas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "APAGAR"};

        for (String t : teclas) {
            JButton btn = new JButton(t);
            btn.setBackground(Color.WHITE);
            btn.addActionListener(e -> {
                if (t.equals("APAGAR")) {
                    String txt = campoDestino.getText();
                    if (txt.length() > 0) campoDestino.setText(txt.substring(0, txt.length() - 1));
                } else {
                    campoDestino.setText(campoDestino.getText() + t);
                }
            });
            painel.add(btn);
        }
        return painel;
    }
}