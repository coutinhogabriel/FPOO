package RevisaoFPOO;

import javax.swing.JOptionPane;

import RevisaoFPOO.Agenda.Agenda;
import RevisaoFPOO.Cadastro.Animal;
import RevisaoFPOO.Cadastro.Cachorro;
import RevisaoFPOO.Cadastro.Gato;
import RevisaoFPOO.Cadastro.OutrosAnimais;

/**
 * App
 */
public class App {

    public static void main(String[] args) {
        // cria o cadastro
        Gato gatos[] = new Gato[10];
        Cachorro cachorros[] = new Cachorro[10];
        OutrosAnimais outros[] = new OutrosAnimais[10];
        Agenda agendas[] = new Agenda[100];
        int contGatos = 0, contCachorros = 0, contOutros = 0, contAgenda = 0;

        // criar aplicaçao
        JOptionPane.showMessageDialog(null, "Bem vindo ao consultorio\n do Dr Hans Chucrutes");
        boolean aberto = true;
        while (aberto) {
            int acao1 = Integer.parseInt(JOptionPane.showInputDialog("1-Cadastro\n 2-Consulta\n 3-Sair"));
            if (acao1 == 1) {// cadastro
                // acao1_1 sera a escolha de qual animal a ser cadastrado
                int acao1_1 = Integer.parseInt(JOptionPane.showInputDialog("1-Gato\n 2-Cachorro\n 3-Outro Animal"));
                if (acao1_1 == 1) { // escolher cadastrar gato
                    // criar um objeto
                    gatos[contGatos] = new Gato();
                    // setar os valores do cadastro
                    gatos[contGatos].setNome(JOptionPane.showInputDialog("Informe o Nome do PET"));
                    gatos[contGatos].setraca(JOptionPane.showInputDialog("Informe a Raça do PET"));
                    gatos[contGatos].setProprietario(JOptionPane.showInputDialog("Informe o Nome do Proprietario"));
                    gatos[contGatos].setPeso(Double.parseDouble(JOptionPane.showInputDialog("Informe o Peso do PET")));
                    contGatos += 1;
                } else if (acao1_1 == 2) { // escolher cadastrar cachorro
                    cachorros[contCachorros] = new Cachorro();
                    cachorros[contCachorros].setNome(JOptionPane.showInputDialog("Informe o Nome do PET"));
                    cachorros[contCachorros].setraca(JOptionPane.showInputDialog("Informe a Raça do PET"));
                    cachorros[contCachorros]
                            .setProprietario(JOptionPane.showInputDialog("Informe o Nome do Proprietario"));
                    cachorros[contCachorros]
                            .setPeso(Double.parseDouble(JOptionPane.showInputDialog("Informe o Peso do PET")));
                    contCachorros += 1;
                } else if (acao1_1 == 3) { // escolher cadastrar outro animal
                    outros[contOutros] = new OutrosAnimais();
                    outros[contOutros].setNome(JOptionPane.showInputDialog("Informe o Nome do PET"));
                    outros[contOutros].setraca(JOptionPane.showInputDialog("Informe a Raça do PET"));
                    outros[contOutros].setProprietario(JOptionPane.showInputDialog("Informe o Nome do Proprietario"));
                    outros[contOutros]
                            .setPeso(Double.parseDouble(JOptionPane.showInputDialog("Informe o Peso do PET")));
                    contOutros += 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Opção Inválida");
                }
            }else if (acao1 == 2) {// agendar consulta
                // 1º verificação - cadastro do nome
                boolean cadastroOK = false;
                String nomeAgenda = JOptionPane.showInputDialog("Informe o Nome do PET");
                for (int i = 0; i < contGatos; i++) {// busca no vetor de gatos
                    if (gatos[i].getNome().equals(nomeAgenda)) {
                        // mensagem de confirmação
                        JOptionPane.showMessageDialog(null, "Pet Encontrado");
                        cadastroOK = true;
                        break;
                    }
                }
                for (int i = 0; i < contCachorros; i++) {// busca no vetor de cachorros
                    if (cachorros[i].getNome().equals(nomeAgenda)) {
                        // mensagem de confirmação
                        JOptionPane.showMessageDialog(null, "Pet Encontrado");
                        cadastroOK = true;
                        break;
                    }
                }
                for (int i = 0; i < contOutros; i++) {// busca no vetor de outros animais
                    if (outros[i].getNome().equals(nomeAgenda)) {
                        // mensagem de confirmação
                        JOptionPane.showMessageDialog(null, "Pet Encontrado");
                        cadastroOK = true;
                        break;
                    }
                }
                //2º Terminar o Cadastro 
                if(cadastroOK){
                    boolean agendar = true;
                    while(agendar){
                        String dataAgenda = JOptionPane.showInputDialog("Informe a Data do Agendamento");
                        String horaAgenda = JOptionPane.showInputDialog("Informe a Hora do Agendamento");
                        if(contAgenda==0){
                            agendas[contAgenda] = new Agenda();
                            agendas[contAgenda].setData(dataAgenda);
                            agendas[contAgenda].setHora(horaAgenda);
                            agendar = false;
                        }else{
                        for (int i = 0; i < contAgenda; i++) {
                            if(agendas[i].getDataHora().equals(dataAgenda+horaAgenda)){
                                //não pode agendar
                                break;
                            }else{
                                agendas[contAgenda] = new Agenda();
                                agendas[contAgenda].setData(dataAgenda);
                                agendas[contAgenda].setHora(horaAgenda);
                                agendar = false;
                            }
                        }
                    }
            }
            break;
        }
        
    }
        }}}