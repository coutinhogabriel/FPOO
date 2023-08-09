import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // Cadastrar novo carro
        // Consultar carro
        // Excluir carro da minha lista
        List<Carros> listaCarros = new ArrayList<>();
        boolean aberto = true;
        while (aberto) {
            int acao = Integer.parseInt(JOptionPane.showInputDialog(
                           "" + "\nEscolha a ação desejada: "
                            +"\n"
                            + "\n 1 - Cadastrar um novo carro: "
                            + "\n 2 - Consultar carros na lista: "
                            + "\n 3 - Excluir carros da lista: "
                            + "\n 4 - Sair: "));
            if (acao == 1) {
                Carros novoCarro = new Carros(null, null, null, null);
                novoCarro.setMarca(JOptionPane.showInputDialog("Digite a marca do carro"));
                novoCarro.setModelo(JOptionPane.showInputDialog("Digite o modelo do carro"));
                novoCarro.setAno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro")));
                novoCarro.setCor(JOptionPane.showInputDialog("Digite a cor do carro"));
                listaCarros.add(novoCarro);
                JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso");
            } else if (acao == 2) {
                Carros carro = new Carros(null, null, null, null);
                carro.setMarca(JOptionPane.showInputDialog("Digite a marca do carro"));
                carro.setModelo(JOptionPane.showInputDialog("Digite o modelo do carro"));
                carro.setAno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro")));
                carro.setCor(JOptionPane.showInputDialog("Digite a cor do carro"));
            } else if (acao == 3) {
                Carros carro = new Carros(null, null, null, null);
                carro.setMarca(JOptionPane.showInputDialog("Digite a marca do carro"));
                carro.setModelo(JOptionPane.showInputDialog("Digite o modelo do carro"));
                carro.setAno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro")));
                carro.setCor(JOptionPane.showInputDialog("Digite a cor do carro"));
                listaCarros.remove(carro);
                JOptionPane.showMessageDialog(null, "Carro excluido com sucesso");
            } else if (acao == 4) {
                aberto = false;
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida");
            }

        }
    }
}
