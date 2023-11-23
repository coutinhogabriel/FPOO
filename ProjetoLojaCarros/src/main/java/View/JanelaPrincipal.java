package View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {
    // criação do tabbedPane para incluir as tabs
    private JTabbedPane jTPane;

    public JanelaPrincipal() {
        jTPane = new JTabbedPane();
        add(jTPane);
        // criandos as tabs
        // tab1 carros
        JanelaCarros tab1 = new JanelaCarros();
        JanelaClientes tab2 = new JanelaClientes();
        JanelaVendas tab3 = new JanelaVendas();
        jTPane.add("Carros", tab1);
        jTPane.add("Clientes", tab2);
        jTPane.add("Vendas", tab3);
        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // métodos para tornar a janela visível
    public void run() {
        this.setVisible(true);
    }

}