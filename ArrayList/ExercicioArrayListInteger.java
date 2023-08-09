package ArrayList;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class ExercicioArrayListInteger {
    public static void main(String[] args) {
        ArrayList<Integer> num = new ArrayList<Integer>(5);
        for (int i = 0; i < 5; i++) {
            num.add(Integer.parseInt(JOptionPane.showInputDialog("Digite um Nº")));
        }
        Collections.sort(num);
        for (Integer integer : num) {
            System.out.println(integer);
        }
        Collections.reverse(num);
        for (Integer integer : num) {
            System.out.println(integer);
        }
    }
}