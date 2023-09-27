package GUICardTabbed.GUICardTabbed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercicios3CardLayout extends JFrame {
    private JPanel cards;
    private CardLayout cardLayout;
    private JButton btnTelaLogin;
    private JButton btnTelaRegistro;

    private boolean telaLoginAtiva = false;
    private boolean telaRegistroAtiva = false;

    public Exercicios3CardLayout() {
        super("Navegação com CardLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Criar um painel para conter os "cards" (telas) com CardLayout
        cards = new JPanel();
        cardLayout = new CardLayout();
        cards.setLayout(cardLayout);

        // Criar três painéis para representar as telas
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        // Adiciona rótulos aos painéis para identificar as telas
        panel1.add(new JLabel("Tela Inicial"));
        panel2.add(new JLabel("Tela de Login"));
        panel3.add(new JLabel("Tela de Registro"));

        // Adiciona os painéis ao painel de "cards" com nomes identificativos
        cards.add(panel1, "telaInicial");
        cards.add(panel2, "telaLogin");
        cards.add(panel3, "telaRegistro");

        // Adiciona o painel de "cards" ao JFrame
        add(cards, BorderLayout.CENTER);

        // Cria botões para navegar entre as telas
        btnTelaLogin = new JButton("Tela de Login");
        btnTelaRegistro = new JButton("Tela de Registro");

        // Cria um painel para os botões
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnTelaLogin);
        btnPanel.add(btnTelaRegistro);

        // Adiciona o painel de botões ao JFrame
        add(btnPanel, BorderLayout.SOUTH);

        // Define a ação dos botões para alternar entre as telas e atualizar os textos
        btnTelaLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ser executado se a tela de login NÃO estiver ativa
                if (!telaLoginAtiva) {
                    cardLayout.show(cards, "telaLogin");
                    btnTelaLogin.setText("Tela Inicial");
                    telaLoginAtiva = true;
                    telaRegistroAtiva = false;
                    btnTelaRegistro.setText("Tela de Registro");
                    // Código a ser executado se a tela de login estiver ativa
                } else {
                    cardLayout.show(cards, "telaInicial");
                    btnTelaLogin.setText("Tela de Login");
                    telaLoginAtiva = false;
                }
            }
        });

        btnTelaRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ser executado se a tela de registro NÃO estiver ativa
                if (!telaRegistroAtiva) {
                    cardLayout.show(cards, "telaRegistro");
                    btnTelaRegistro.setText("Tela Inicial");
                    telaRegistroAtiva = true;
                    telaLoginAtiva = false;
                    btnTelaLogin.setText("Tela de Login");
                    // Código a ser executado se a tela de registro estiver ativa
                } else {
                    cardLayout.show(cards, "telaInicial");
                    btnTelaRegistro.setText("Tela de Registro");
                    telaRegistroAtiva = false;
                }
            }
        });

        // Configurações finais do JFrame
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

