package Model;

public class Clientes {

    private String Nome;
    private String Contato;
    private String CPF;

    public Clientes(String nome, String contato, String cpf) {
        Nome = nome;
        Contato = contato;
        CPF = cpf;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getContato() {
        return Contato;
    }

    public void setContato(String contato) {
        Contato = contato;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cpf) {
        CPF = cpf;
    }
}
