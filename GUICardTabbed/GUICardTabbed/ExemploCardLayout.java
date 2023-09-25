package GUICardTabbed.GUICardTabbed;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class ExemploCardLayout extends JFrame{
    //construtor
    public ExemploCardLayout() {
        super("Exemplo CardLayout");
        JPanel painel1 = new JPanel();//principal
        //elemento de seleção dos Cards JComboBox
        String itensCB[] = {"Card 1","Card 2"}; 
        JComboBox cb = new JComboBox<>(itensCB);
        painel1.add(cb);
        //criar os cards
        JPanel cards = new JPanel();//card principal - vai permitir a navegação
        CardLayout cl = new CardLayout();
        cards.setLayout(cl);
        //criar os cards da pilha
        JPanel card1 = new JPanel();
        card1.add(new JButton("Botão 1"));
        card1.add(new JButton("Botão 2"));
        card1.add(new JButton("Botão 3"));
        JPanel card2 = new JPanel();
        card2.add(new JTextField("Card com Jtext", 20));
        //add cards a pilha
        cards.add(card1,"Card 1");
        cards.add(card2,"Card 2");
        painel1.add(cards);
        this.add(painel1);
        //set do Frame
        this.setDefaultCloseOperation(2);
        this.setBounds(100, 100, 300, 300);
        this.setVisible(true);
        //criar a ação para o ComboBox
        cb.addItemListener(e->{
            cl.show(cards, (String)e.getItem());
        });

    }
}

