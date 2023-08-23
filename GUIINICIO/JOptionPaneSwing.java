package GUIINICIO;

import javax.swing.JOptionPane;

public class JOptionPaneSwing extends JOptionPane {
    public JOptionPaneSwing() {
        String informacao = "Minha Janela JOptionPane";
        JOptionPane.showMessageDialog(getComponentPopupMenu(), informacao, informacao, JOptionPane.INFORMATION_MESSAGE);

    }

}
