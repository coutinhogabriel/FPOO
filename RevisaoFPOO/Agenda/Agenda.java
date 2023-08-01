package RevisaoFPOO.Agenda;

public class Agenda {
    //atributos
    String data;
    String hora;
    String dataHora; 
    String servico;
    boolean cadastroOkay=false;
    boolean horarioDisp=false;
    //metodos
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getDataHora() {
        return dataHora;
    }
    public void setDataHora(String dataHora) {
        dataHora=data+hora;
        this.dataHora = dataHora;
    }
    public String getServico() {
        return servico;
    }
    public void setServico(String servico) {
        this.servico = servico;
    }
    public boolean isCadastroOkay() {
        return cadastroOkay;
    }
    public void setCadastroOkay(boolean cadastroOkay) {
        this.cadastroOkay = cadastroOkay;
    }
    public boolean isHorarioDisp() {
        return horarioDisp;
    }
    public void setHorarioDisp(boolean horarioDisp) {
        this.horarioDisp = horarioDisp;
    }
    
}
