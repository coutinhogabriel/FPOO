package GUILayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutCalculadoraGUI {
    private JTextField caixa1; // Campo de texto onde os números e o resultado são exibidos
    private JLabel operacaoLabel; // Rótulo para mostrar a operação atual
    private double numeroAnterior = 0; // Armazena o número anterior digitado
    private String operador = ""; // Armazena o operador (+, -, *, /)
    private boolean novoNumero = true; // Verifica se é um novo número

    public LayoutCalculadoraGUI() {
        // Configuração da janela principal
        JFrame janelaP = new JFrame("LayoutCalculadora");
        BorderLayout border = new BorderLayout();
        janelaP.setLayout(border);
        JPanel painelV = new JPanel(); // Painel para o visor e rótulo
        JPanel painelB = new JPanel(); // Painel para os botões
        janelaP.getContentPane().add(painelB, BorderLayout.CENTER);
        janelaP.getContentPane().add(painelV, BorderLayout.NORTH);

        // Campo de texto onde os números e o resultado são exibidos
        caixa1 = new JTextField(20);
        caixa1.setPreferredSize(new Dimension(caixa1.getPreferredSize().width, 25)); // Aumenta a altura para 25 pixels

        operacaoLabel = new JLabel(""); // Rótulo inicialmente vazio para a operação
        painelV.add(caixa1);
        painelV.add(operacaoLabel); // Adiciona o rótulo ao painel

        // Layout da grade para os botões
        GridLayout grid = new GridLayout(4, 4, 2, 2);
        painelB.setLayout(grid);

        // Textos dos botões
        String textBotoes[] = { "C", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "+", "0", "=" };

        // Adiciona botões à grade
        for (int i = 0; i < textBotoes.length; i++) {
            JButton button = new JButton(textBotoes[i]);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    botaoClicado(e.getActionCommand()); // Trata o clique no botão
                }
            });
            painelB.add(button);
        }

        // Configurações da janela principal
        janelaP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaP.setBounds(500, 200, 400, 450); // Define o tamanho da janela
        janelaP.setVisible(true);
    }

    // Método para lidar com o clique nos botões
    private void botaoClicado(String textoBotao) {
        if (textoBotao.equals("C")) {
            caixa1.setText(""); // Limpa a caixa de texto
            operacaoLabel.setText(""); // Limpa o rótulo da operação
            numeroAnterior = 0;
            operador = "";
            novoNumero = true;
        } else if (textoBotao.matches("[0-9]")) {
            if (novoNumero) {
                caixa1.setText(textoBotao);
                novoNumero = false;
            } else {
                caixa1.setText(caixa1.getText() + textoBotao);
            }
        } else if (textoBotao.equals("+") || textoBotao.equals("-") || textoBotao.equals("*") || textoBotao.equals("/")) {
            if (!operador.isEmpty()) {
                calcularResultado(); // Realiza o cálculo quando já existe um operador
            }
            operador = textoBotao;
            numeroAnterior = Double.parseDouble(caixa1.getText());
            operacaoLabel.setText(caixa1.getText() + " " + operador); // Atualiza o rótulo da operação
            novoNumero = true;
        } else if (textoBotao.equals("=")) {
            calcularResultado();
            operador = "";
        }
    }

    // Método para calcular o resultado
    private void calcularResultado() {
        try {
            double numeroAtual = Double.parseDouble(caixa1.getText());
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = numeroAnterior + numeroAtual;
                    break;
                case "-":
                    resultado = numeroAnterior - numeroAtual;
                    break;
                case "*":
                    resultado = numeroAnterior * numeroAtual;
                    break;
                case "/":
                    if (numeroAtual == 0) {
                        caixa1.setText("Não é possível dividir por 0");
                        operacaoLabel.setText(""); // Limpa o rótulo da operação
                        return;
                    } else {
                        resultado = numeroAnterior / numeroAtual;
                    }
                    break;
            }

            caixa1.setText(Double.toString(resultado)); // Exibe o resultado na caixa de texto
            operacaoLabel.setText(""); // Limpa o rótulo da operação
            novoNumero = true;
        } catch (NumberFormatException e) {
            caixa1.setText("Erro");
            operacaoLabel.setText(""); // Limpa o rótulo da operação
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LayoutCalculadoraGUI());
    }
}
