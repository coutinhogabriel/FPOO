package GUIINICIO;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Container extends JFrame {
    public Container() {
        this.setBounds(300, 300, 800, 350);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        FlowLayout flow = new FlowLayout(FlowLayout.LEFT); // Define o layout
        JLabel label = new JLabel("Exemplo de texto");
        this.add(label);
        JTextField campo = new JTextField(15);
        this.add(campo);
        this.setLayout(flow); // Seta layout do container
        for (int i = 1; i < 6; i++) {
            this.add(new JButton("Aperte " + i)); // Adiciona um botão
            this.setVisible(true); // Exibe a janela
        }
    }

}
