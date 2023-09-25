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
        JFrame janelaP = new JFrame("Calculadora"); // Alteração do título
        BorderLayout border = new BorderLayout();
        janelaP.setLayout(border);
        JPanel painelV = new JPanel(); // Painel para o visor e rótulo
        JPanel painelB = new JPanel(); // Painel para os botões
        janelaP.getContentPane().add(painelB, BorderLayout.CENTER);
        janelaP.getContentPane().add(painelV, BorderLayout.NORTH);
        painelB.setBackground(Color.gray); // Altera cor do fundo dos botões
        painelV.setBackground(Color.white); // Altera cor do fundo do display

        // Campo de texto onde os números e o resultado são exibidos
        caixa1 = new JTextField(15);
        caixa1.setPreferredSize(new Dimension(caixa1.getPreferredSize().width, 50));
        caixa1.setFont(new Font("Arial", Font.PLAIN, 24)); // Altera Fonte e tamanho do texto no display
        caixa1.setHorizontalAlignment(JTextField.RIGHT); // Alinha o texto à direita
        caixa1.setEditable(false); //Não permite digitar pelo teclado do note
        caixa1.setBackground(Color.white); // Altera cor do visor

        operacaoLabel = new JLabel(""); // Rótulo inicialmente vazio para a operação
        operacaoLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Altera Fonte e tamanho do texto na operação do lado do display
        painelV.add(caixa1);
        painelV.add(operacaoLabel); // Adiciona o rótulo ao painel

        // Layout da grade para os botões
        GridLayout grid = new GridLayout(4, 4, 10, 10); // Aumento do espaçamento
        painelB.setLayout(grid);

        // Textos dos botões
        String textBotoes[] = { "C", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "+", "0", "=" };

        // Adiciona botões à grade
        for (int i = 0; i < textBotoes.length; i++) {
            JButton button = new JButton(textBotoes[i]);
            button.setFont(new Font("Arial", Font.PLAIN, 32)); // Fonte e no tamanho dos numeros dos botões
            button.setBackground(Color.white); // Altera cor dos botões
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    botaoClicado(e.getActionCommand()); // Trata o clique no botão
                }
            });
            painelB.add(button);
        }

        // Configurações da janela principal
        janelaP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaP.setBounds(500, 200, 500, 600); // Ajuste do tamanho da janela
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
        } else if (textoBotao.matches("[0-9]")) {  //regex permite ler intervalos
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
    // Rodar o programa sem precisar utilizar o App.java
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LayoutCalculadoraGUI());
    }
}
