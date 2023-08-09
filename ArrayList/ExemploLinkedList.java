package ArrayList;

import java.util.LinkedList;

public class ExemploLinkedList {
    //criar uma Lista usando elementos do LinkedList
    public static void main(String[] args) {
        LinkedList<String> flores = new LinkedList<>();
        flores.add("Rosas");
        flores.add("Margaridas");
        flores.addFirst("Begônias");
        flores.addLast("Cravos");
        System.out.println(flores.getFirst());
        System.out.println(flores.getLast());
    }
}