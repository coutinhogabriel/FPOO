import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OperacoesAgendas {
    // atributos
    private List<Agenda> agendas;
    private DefaultTableModel tableModel;
    private JTable table;

    // construtor
    public OperacoesAgendas(List<Agenda> agendas, DefaultTableModel tableModel, JTable table) {
        this.agendas = agendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    // métodos do CRUD
    public void cadastrarAgendas(String data, String hora, String usuario, String descricao) {
        Agenda agenda = new Agenda(data, hora, usuario, descricao);
        agendas.add(agenda);
        atualizarTabela();
    }

    public void atualizarAgendas(int linhaSelecionada, String data, String hora, String usuario, String descricao ) {
        if (linhaSelecionada != -1) {
            Agenda agenda = new Agenda(data, hora, usuario, descricao);
            agendas.set(linhaSelecionada, agenda);
            atualizarTabela();
        }
    }

    public void apagarAgendas(int linhaSelecionada) {
        if (linhaSelecionada != -1) {
            agendas.remove(linhaSelecionada);
            atualizarTabela();
        }
    }

    // public void salvarAgendas() {
    //     Serializacao.serializar("dados.txt", usuarios);
    // }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Agenda agenda : agendas) {
            tableModel.addRow(new Object[] { agenda.getData(),
                 agenda.getHora(), agenda.getUsuario(), agenda.getDescricao()  });
        }
    }
}
