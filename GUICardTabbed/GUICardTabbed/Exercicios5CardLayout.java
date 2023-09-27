package GUICardTabbed.GUICardTabbed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Exercicios5CardLayout extends JFrame {
    private JPanel cards; // Painel para usar CardLayout
    private int score = 0; // Variável para armazenar a pontuação

    public Exercicios5CardLayout() {
        super("Quiz CardLayout");

        // Crie um novo painel para a tela de boas-vindas
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Bem-vindo ao Quiz!");
        JButton startButton = new JButton("Começar Jogo");

        // Adicione o rótulo e o botão ao painel de boas-vindas
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(startButton);

        // Crie o painel do jogo (onde as perguntas serão exibidas)
        JPanel gamePanel = new JPanel();
        JLabel questionLabel = new JLabel("Pergunta 1: Qual é a capital do Brasil?");
        JButton answerButton1 = new JButton("Rio de Janeiro");
        JButton answerButton2 = new JButton("São Paulo");
        JButton answerButton3 = new JButton("Brasília");

        // Adicione os componentes ao painel do jogo
        gamePanel.add(questionLabel);
        gamePanel.add(answerButton1);
        gamePanel.add(answerButton2);
        gamePanel.add(answerButton3);

        // Crie o painel de pontuação
        JPanel scorePanel = new JPanel();
        JLabel scoreLabel = new JLabel("Pontuação final: ");
        JLabel finalScoreLabel = new JLabel("0");

        // Adicione os componentes ao painel de pontuação
        scorePanel.add(scoreLabel);
        scorePanel.add(finalScoreLabel);

        // Crie o ActionListener para o botão "Começar Jogo"
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "gamePanel"); // Mude para o painel do jogo
            }
        });

        // ActionListener para os botões de resposta
        ActionListener answerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String answer = clickedButton.getText();

                // Verifique a resposta e atualize a pontuação
                if (answer.equals("Brasília")) {
                    score++; // Resposta correta
                }

                // Avance para a próxima pergunta
                showNextQuestion();
            }
        };

        // Adicione o ActionListener aos botões de resposta
        answerButton1.addActionListener(answerListener);
        answerButton2.addActionListener(answerListener);
        answerButton3.addActionListener(answerListener);

        // Configure o layout do frame e adicione os painéis ao CardLayout
        cards = new JPanel(new CardLayout());
        cards.add(welcomePanel, "welcomePanel");
        cards.add(gamePanel, "gamePanel");
        cards.add(scorePanel, "scorePanel"); // Adicione o painel de pontuação

        // Adicione o painel de cartões ao JFrame
        getContentPane().add(cards);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centralize o JFrame
        setVisible(true);
    }

    private void showNextQuestion() {
        // Verifique a pontuação para determinar se o jogo acabou ou continue com a próxima pergunta
        if (score == 1) {
            // O jogador respondeu corretamente à primeira pergunta
            // Crie o painel para a segunda pergunta
            JPanel question2Panel = new JPanel();
            JLabel question2Label = new JLabel("Pergunta 2: Qual é a maior cidade do mundo?");
            JButton answer2Button1 = new JButton("Nova York");
            JButton answer2Button2 = new JButton("Tóquio");
            JButton answer2Button3 = new JButton("Xangai");

            // Adicione os componentes ao painel da segunda pergunta
            question2Panel.add(question2Label);
            question2Panel.add(answer2Button1);
            question2Panel.add(answer2Button2);
            question2Panel.add(answer2Button3);

            // ActionListener para os botões da segunda pergunta
            ActionListener answer2Listener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    String answer = clickedButton.getText();

                    // Verifique a resposta e atualize a pontuação
                    if (answer.equals("Xangai")) {
                        score++; // Resposta correta
                    }

                    // Avance para a próxima pergunta ou encerre o jogo
                    if (score == 2) {
                        // O jogador respondeu corretamente à segunda pergunta
                        CardLayout cardLayout = (CardLayout) cards.getLayout();
                        cardLayout.show(cards, "scorePanel"); // Mostre o painel de pontuação
                    } else {
                        // Mostre a próxima pergunta
                        CardLayout cardLayout = (CardLayout) cards.getLayout();
                        cardLayout.show(cards, "question2Panel");
                    }
                }
            };

            // Adicione o ActionListener aos botões da segunda pergunta
            answer2Button1.addActionListener(answer2Listener);
            answer2Button2.addActionListener(answer2Listener);
            answer2Button3.addActionListener(answer2Listener);

            // Adicione o painel da segunda pergunta ao CardLayout
            cards.add(question2Panel, "question2Panel");

            // Mostre a segunda pergunta
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "question2Panel");
        } else {
            // O jogador errou ou já respondeu às duas perguntas
            JLabel finalScoreLabel = (JLabel) ((JPanel) cards.getComponent(2)).getComponent(1);
            finalScoreLabel.setText(Integer.toString(score)); // Atualize a pontuação final
            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "scorePanel"); // Mostre o painel de pontuação
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Exercicios5CardLayout();
            }
        });
    }
}


