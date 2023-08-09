package Exception;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ExemploThrow {
    public static void main(String[] args) {
        // digitação de senhas e login
        	
        boolean testesenha = true;
        while (testesenha) {
            String login = JOptionPane.showInputDialog("Informe um Login");
            String dataNascimento = JOptionPane.showInputDialog("Informe a Data de Nascimento [dd/mm/aa]");
            dataNascimento = dataNascimento.replace("/", "");
            String senhaDigitada = JOptionPane.showInputDialog("Informe uma Senha de 6 Digitos");
            try {
                if(senhaDigitada.length() != 6) {
                    throw new Exception("Senha Inválida");
                }
                if(login.equals(senhaDigitada)){
                    throw new Exception("Senha igual Login");
                }
                testesenha=false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),null,JOptionPane.CLOSED_OPTION);
            }
        }
    }
}
