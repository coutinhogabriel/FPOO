package Controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.Clientes;

public class ClientesControl {
    private List<Clientes> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        clientes = new ClientesDAO().listarTodos();
        for (Clientes cliente : clientes) {
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getContato(), cliente.getCPF() });
        }
    }

    public void cadastrar(String nome, String contato, String cpf) {
        try {
            if (validarNome(nome) && validarCPF(cpf) && validarContato(contato)) {
                new ClientesDAO().cadastrar(nome, contato, cpf);
                atualizarTabela();
            } else {
                throw new IllegalArgumentException("Dados inválidos. Verifique os campos e tente novamente.");
            }
        } catch (Exception e) {
            exibirMensagemErro("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void atualizar(String nome, String contato, String cpf) {
        try {
            if (validarNome(nome) && validarCPF(cpf) && validarContato(contato)) {
                new ClientesDAO().atualizar(nome, contato, cpf);
                atualizarTabela();
            } else {
                throw new IllegalArgumentException("Dados inválidos. Verifique os campos e tente novamente.");
            }
        } catch (Exception e) {
            exibirMensagemErro("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void apagar(String cpf) {
        try {
            if (validarCPF(cpf)) {
                new ClientesDAO().apagar(cpf);
                atualizarTabela();
            } else {
                throw new IllegalArgumentException("CPF inválido. Verifique o campo e tente novamente.");
            }
        } catch (Exception e) {
            exibirMensagemErro("Erro ao apagar: " + e.getMessage());
        }
    }

    // Métodos para validar o formato do nome, CPF e contato
    private boolean validarNome(String nome) {
        if (nome.matches("^[a-zA-Z]+$")) {
            return true;
        } else {
            throw new IllegalArgumentException("Nome inválido. Verifique e tente novamente.");
        }
    }

    private boolean validarCPF(String cpf) {
        if (cpf.matches("\\d{11}")) {
            return true;
        } else {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 dígitos numéricos. Verifique e tente novamente.");
        }
    }

    private boolean validarContato(String contato) {
        if (contato.matches("\\d{10,11}")) {
            return true;
        } else {
            throw new IllegalArgumentException("O Contato deve conter DDD (mínimo 10 e máximo 11 dígitos). Verifique e tente novamente.");
        }
    }

    // Método para exibir mensagens de erro usando JOptionPane
    private void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
