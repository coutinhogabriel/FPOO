package Exception;

import javax.swing.JOptionPane;

public class ExemploThrow {
    public static void main(String[] args) {
        // digitação de senhas
        boolean testesenha = true;
        while (testesenha) {
            String senhaDigitada = JOptionPane.showInputDialog("Informe uma Senha de 6 Digitos");
            try {
                if (senhaDigitada.length() != 6) {
                    throw new Exception("Senha Inválida");
                }
                testesenha=false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
