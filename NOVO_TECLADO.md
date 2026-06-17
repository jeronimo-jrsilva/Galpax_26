Coloque os imports

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Insets;


troque o 

containerTeclado.setBounds(159, 806, 1754, 264);

por

containerTeclado.setBounds(0, 745, screenSize.width, 330);


coloque logo depois do Textfield de senha

senha.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		campoAtivo = senha;
		mostrarTeclado(criarTecladoCompleto(senha));
	}
});

confirmarsenha.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		campoAtivo = confirmarsenha;
		mostrarTeclado(criarTecladoCompleto(confirmarsenha));
	}
});


troque o os métodos 

mostrarTeclado
inserirTexto
alternarShift
criarTecladoCompleto


private void mostrarTeclado(JPanel painelTeclado) {
	containerTeclado.removeAll();
	containerTeclado.add(painelTeclado, BorderLayout.CENTER);
	containerTeclado.revalidate();
	containerTeclado.repaint();
}

private void inserirTexto(JTextField campo, String texto) {
	if (campo == null) return;

	String atual = campo.getText();

	if (texto.equals("APAGAR")) {
		if (atual.length() > 0) {
			campo.setText(atual.substring(0, atual.length() - 1));
		}
	} else if (texto.equals("ESPAÇO")) {
		campo.setText(atual + " ");
	} else {
		campo.setText(atual + texto);
	}
}

private JPanel criarTecladoCompleto(JTextField campo) {
	botoesLetras.clear();
	shiftAtivo = false;

	JPanel painelPrincipal = new JPanel(new GridLayout(4, 1, 12, 12));
	painelPrincipal.setOpaque(true);
	painelPrincipal.setBackground(new Color(8, 18, 42));
	painelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 22, 22));

	String[][] linhas = {
		{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "⌫"},
		{"a", "s", "d", "f", "g", "h", "j", "k", "l", "@"},
		{"⇧", "z", "x", "c", "v", "b", "n", "m", ",", ".", "-"},
		{"?123", "ESPAÇO", "↵"}
	};

	for (String[] linha : linhas) {
		JPanel painelLinha = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
		painelLinha.setOpaque(false);

		for (String tecla : linha) {
			JButton btn = new BotaoTeclado(tecla);

			if (tecla.equals("ESPAÇO")) {
				btn.setPreferredSize(new Dimension(1020, 72));
			} else if (tecla.equals("↵")) {
				btn.setPreferredSize(new Dimension(210, 72));
				btn.setBackground(new Color(15, 83, 230));
			} else if (tecla.equals("?123")) {
				btn.setPreferredSize(new Dimension(170, 72));
			} else {
				btn.setPreferredSize(new Dimension(130, 72));
			}

			if (tecla.equals("⌫")) {
				btn.addActionListener(e -> inserirTexto(campo, "APAGAR"));
			} else if (tecla.equals("ESPAÇO")) {
				btn.addActionListener(e -> inserirTexto(campo, "ESPAÇO"));
			} else if (tecla.equals("⇧")) {
				btn.addActionListener(e -> alternarShift(btn));
			} else if (tecla.equals("↵")) {
				btn.addActionListener(e -> {
					containerTeclado.removeAll();
					containerTeclado.revalidate();
					containerTeclado.repaint();
				});
			} else if (tecla.equals("?123")) {
				btn.addActionListener(e -> mostrarTeclado(criarTecladoNumerico(campo)));
			} else {
				btn.addActionListener(e -> inserirTexto(campo, btn.getText()));

				if (tecla.matches("[a-z]")) {
					botoesLetras.add(btn);
				}
			}

			painelLinha.add(btn);
		}

		painelPrincipal.add(painelLinha);
	}

	return painelPrincipal;
}

private void alternarShift(JButton btnShift) {
	shiftAtivo = !shiftAtivo;
	btnShift.setBackground(shiftAtivo ? new Color(45, 65, 105) : new Color(31, 42, 67));

	for (JButton btn : botoesLetras) {
		String letra = btn.getText();
		btn.setText(shiftAtivo ? letra.toUpperCase() : letra.toLowerCase());
	}
}

private JPanel criarTecladoNumerico(JTextField campo) {
	JPanel painelPrincipal = new JPanel(new GridLayout(4, 1, 12, 12));
	painelPrincipal.setOpaque(true);
	painelPrincipal.setBackground(new Color(8, 18, 42));
	painelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 22, 22));

	String[][] linhas = {
		{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "⌫"},
		{"@", "#", "$", "_", "&", "-", "+", "(", ")", "/"},
		{"*", "\"", "'", ":", ";", "!", "?", ".", ","},
		{"ABC", "ESPAÇO", "↵"}
	};

	for (String[] linha : linhas) {
		JPanel painelLinha = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
		painelLinha.setOpaque(false);

		for (String tecla : linha) {
			JButton btn = new BotaoTeclado(tecla);

			if (tecla.equals("ESPAÇO")) {
				btn.setPreferredSize(new Dimension(1020, 72));
			} else if (tecla.equals("↵")) {
				btn.setPreferredSize(new Dimension(210, 72));
				btn.setBackground(new Color(15, 83, 230));
			} else if (tecla.equals("ABC")) {
				btn.setPreferredSize(new Dimension(170, 72));
			} else {
				btn.setPreferredSize(new Dimension(130, 72));
			}

			if (tecla.equals("⌫")) {
				btn.addActionListener(e -> inserirTexto(campo, "APAGAR"));
			} else if (tecla.equals("ESPAÇO")) {
				btn.addActionListener(e -> inserirTexto(campo, "ESPAÇO"));
			} else if (tecla.equals("↵")) {
				btn.addActionListener(e -> {
					containerTeclado.removeAll();
					containerTeclado.revalidate();
					containerTeclado.repaint();
				});
			} else if (tecla.equals("ABC")) {
				btn.addActionListener(e -> mostrarTeclado(criarTecladoCompleto(campo)));
			} else {
				btn.addActionListener(e -> inserirTexto(campo, btn.getText()));
			}

			painelLinha.add(btn);
		}

		painelPrincipal.add(painelLinha);
	}

	return painelPrincipal;
}

private class BotaoTeclado extends JButton {

	public BotaoTeclado(String texto) {
		super(texto);
		setFont(new Font("Arial Unicode MS", Font.PLAIN, 34));
		setForeground(Color.WHITE);
		setBackground(new Color(31, 42, 67));
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
		setMargin(new Insets(0, 0, 0, 0));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
		super.paintComponent(g);
		g2.dispose();
	}
}