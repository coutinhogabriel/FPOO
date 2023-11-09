package app;

import javax.xml.crypto.dsig.dom.DOMSignContext;

import Connection.ConnectionDAO;

public class Main {
    public static void main(String[] args) {
        new ConnectionDAO().criarTabela();
        new ConnectionDAO().inserir("gabrieu", "gabrieu@gmail.com");
        new ConnectionDAO().buscarPorId(1);
    }
}