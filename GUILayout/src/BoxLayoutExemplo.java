package GUILayout.src;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoxLayoutExemplo extends JFrame {
        //configurando o layout do frame
        public BoxLayoutExemplo() {
            super("Exemplo Box Layout");
            BorderLayout border = new BorderLayout();
            this.setLayout(border);
            // criar dois paineis
            JPanel painel1 = new JPanel();
            JPanel painel2 = new JPanel();
            // adicionar os pianeis ao frame
            this.add(painel1, BorderLayout.WEST);
            this.add(painel2, BorderLayout.SOUTH);
            // layout dos paineis
            painel1.setLayout(new BoxLayout(painel1, BoxLayout.Y_AXIS));
            painel2.setLayout(new BoxLayout(painel2, BoxLayout.X_AXIS));
            //adicionar os elementos aos paineis
            for (int i = 0; i < 5; i++) {
                painel1.add(new JButton(""+i+1));
                painel2.add(new JButton(""+i+1));
            }
            //set do frame
            this.setDefaultCloseOperation(2);
            this.pack();
            this.setVisible(true);
        }
    
    
}
