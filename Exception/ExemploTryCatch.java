package Exception;

import javax.swing.JOptionPane;

public class ExemploTryCatch {
    public static void main(String[] args) {
        boolean rodando = true;
        String calc[] = { "Soma", "Subtração", "Divisão", "Multiplicação", "Sair" };
        while (rodando) {
            try {
                int numero1 = Integer.parseInt(JOptionPane.showInputDialog("Digite um Nº Inteiro"));
                int numero2 = Integer.parseInt(JOptionPane.showInputDialog("Digite outro Nº Inteiro"));
                // pedir para usuario escolher a operação
                int operacao = JOptionPane.showOptionDialog(null, "Escolha a Operacão", "Calculadora",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.CANCEL_OPTION,
                        null, calc, calc[0]);
                if (operacao == 0) {
                    JOptionPane.showMessageDialog(null, "O resultado é "
                            + (numero1 + numero2));
                } else if (operacao == 1) {
                    JOptionPane.showMessageDialog(null, "O resultado é "
                            + (numero1 - numero2));
                } else if (operacao == 2) {
                    JOptionPane.showMessageDialog(null, "O resultado é "
                            + (numero1 / numero2));
                } else if (operacao == 3) {
                    JOptionPane.showMessageDialog(null, "O resultado é "
                            + (numero1 * numero2));
                } else {
                    rodando = false;
                }

            } catch (NumberFormatException erro1) {
                JOptionPane.showMessageDialog(null, "Erro - Valor digitado não é um inteiro"
                        + "\nDigite novamente");
            } catch (ArithmeticException erro2) {
                JOptionPane.showMessageDialog(null, "Erro - não Dividirás por Zero!!!"
                        + "\nDigite novamente");
            } catch (Exception erro3) {
                JOptionPane.showMessageDialog(null, "Aconteceu um erro"
                        + "\nDigite novamente");
                    }
        }
    }
}
