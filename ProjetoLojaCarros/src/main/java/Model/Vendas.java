package Model;

public class Vendas {

    private String Comprador;
    private String Modelo;
    private String Data; // Alterado para String
    private String Valor; // Alterado para String

    public Vendas(String comprador, String modelo, String data, String valor) {
        Comprador = comprador;
        Modelo = modelo;
        Data = data;
        Valor = valor;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String comprador) {
        Comprador = comprador;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String veiculo) {
        Modelo = veiculo;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) { // Alterado para String
        Data = data;
    }

    public String getValor() { // Alterado para String
        return Valor;
    }

    public void setValor(String valor) { // Alterado para String
        this.Valor = valor;
    }
}
