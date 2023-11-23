package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Vendas;

public class VendasControl {
    private List<Vendas> vendas;
    private DefaultTableModel tableModel;
    private JTable table;

    public VendasControl(List<Vendas> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        vendas = new VendasDAO().listarTodos();
        for (Vendas venda : vendas) {
            tableModel.addRow(new Object[] { venda.getComprador(), venda.getModelo(), venda.getData(), venda.getValor() });
        }
    }

    public void cadastrar(String comprador, String modelo, String data, String valor) {
        try {
            if (validarCamposVazios(comprador, modelo, data, valor) && validarCompradorModelo(comprador, modelo)
                    && validarData(data) && validarValor(valor)) {
                new VendasDAO().cadastrar(comprador, modelo, data, valor);
                atualizarTabela();
            } else {
                throw new IllegalArgumentException("Dados inválidos. Verifique os campos e tente novamente.");
            }
        } catch (Exception e) {
            exibirMensagemErro("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void atualizar(String comprador, String modelo, String data, String valor) {
        try {
            if (validarCamposVazios(comprador, modelo, data, valor) && validarData(data) && validarValor(valor)) {
                new VendasDAO().atualizar(comprador, modelo, data, valor);
                atualizarTabela();
            } else {
                throw new IllegalArgumentException("Dados inválidos. Verifique os campos e tente novamente.");
            }
        } catch (Exception e) {
            exibirMensagemErro("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void apagar(String comprador) {
        try {
            if (validarComprador(comprador)) {
                new VendasDAO().apagar(comprador);
                atualizarTabela();
            } else {
                throw new IllegalArgumentException("Comprador inválido. Verifique o campo e tente novamente.");
            }
        } catch (Exception e) {
            exibirMensagemErro("Erro ao apagar: " + e.getMessage());
        }
    }

    private boolean validarCamposVazios(String comprador, String modelo, String data, String valor) {
        if (comprador.isEmpty() || modelo.isEmpty() || data.isEmpty() || valor.isEmpty()) {
            throw new IllegalArgumentException("Nenhum campo pode estar vazio. Preencha todos os campos e tente novamente.");
        }
        return true;
    }

    private boolean validarCompradorModelo(String comprador, String modelo) {
        if (comprador.matches("^[a-zA-Z]+$") && modelo.matches("^[a-zA-Z]+$")) {
            return true;
        } else {
            throw new IllegalArgumentException("Os campos Comprador e Modelo só podem conter letras. Verifique e tente novamente.");
        }
    }

    private boolean validarData(String data) {
        if (data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return true;
        } else {
            throw new IllegalArgumentException("O campo Data deve estar no formato dd/MM/yyyy. Verifique e tente novamente.");
        }
    }

    private boolean validarValor(String valor) {
        if (valor.matches("\\d+")) {
            return true;
        } else {
            throw new IllegalArgumentException("O campo Valor só aceita números. Verifique e tente novamente.");
        }
    }

    private boolean validarComprador(String comprador) {
        return comprador.matches("^[a-zA-Z]+$");
    }

    private void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
