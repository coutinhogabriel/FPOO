package ArrayList;

//import java.util.ArrayList;
//import java.util.List;
import java.util.*;

import javax.swing.JOptionPane;

public class ExemploArrayList {
    public static void main(String[] args) {
        // criando a ArrayList de String com obj de nome de carros
        ArrayList<String> carros = new ArrayList<String>();// ArrayList
        List carros1 = new ArrayList<>();// coleção
        // adicionar elementos na coleção
        carros.add(JOptionPane.showInputDialog("Insira um nome de um carro"));
        carros.add("Fusca");
        carros.add("Brasília");
        carros.add("Chevette");
        carros.add("Monza");
        carros.add("Camaro");
        for (int i = 0; i < carros.size(); i++) {
            System.out.println(carros.get(i));
        }
        // ordenar uma lista(sort - Collections)
        Collections.sort(carros);

        // Iterando com For-each
        for (String i : carros) {
            System.out.println(i);
        }
        carros.clear();// Apagar ArrayList
        System.out.println(carros);// Imprimindo coleção
    }

}