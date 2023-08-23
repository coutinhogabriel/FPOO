package GUIINICIO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ContainerComponente extends JFrame {
    // Atributo
    int clickCount = 0;

    // Construtor
    public ContainerComponente() {
        super("JFrame");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JButton but = new JButton("I am a Swing button");
        JLabel texto = new JLabel("Number of button clicks: ");
        JPanel painel = new JPanel();
        painel.add(but);
        painel.add(texto);
        this.setContentPane(painel);
        this.pack();
        this.setVisible(true);
        but.addActionListener(e -> {
            clickCount++;
            texto.setText("Number of button clicks: " + clickCount);
            this.add(new JButton("" + clickCount));

        });
    }

}
