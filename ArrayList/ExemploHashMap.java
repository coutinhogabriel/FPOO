package ArrayList;

import java.util.HashMap;

public class ExemploHashMap {
    //criar um HashMap Cidades
    public static void main(String[] args) {
        HashMap <String,String> capitaisCidades = new HashMap<>();
        capitaisCidades.put("SP", "São Paulo");
        capitaisCidades.put("RJ", "Rio de Janeiro");
        capitaisCidades.put("MG","BeloHorizonte");
        capitaisCidades.put("ES","Vitória");
        //Imprime valor correspondente a chave
        System.out.println(capitaisCidades.get("ES"));
        //Imprime chave e valor
        System.out.println(capitaisCidades);
        //Imprime somente chave
        System.out.println(capitaisCidades.keySet());
        //imprimir somente valores
        for (String i : capitaisCidades.keySet()) {
            System.out.println(capitaisCidades.get(i));
        }
        System.out.println(capitaisCidades);

    }
}