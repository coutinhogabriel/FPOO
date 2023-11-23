package Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import Model.Carros;

public class CarrosControl {
    // Atributos
    private List<Carros> carros;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor
    public CarrosControl(List<Carros> carros, DefaultTableModel tableModel, JTable table) {
        this.carros = carros;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        carros = new CarrosDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
        for (Carros carro : carros) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[]{carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPlaca(), carro.getValor()});
        }
    }

    // Método para cadastrar um novo carro no banco de dados
    public void cadastrar(String marca, String modelo, String ano, String placa, String valor) {
        try {
            validarCampos(marca, modelo, ano, placa, valor);
            new CarrosDAO().cadastrar(marca, modelo, ano, placa, valor);
            // Chama o método de cadastro no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para atualizar os dados de um carro no banco de dados
    public void atualizar(String marca, String modelo, String ano, String placa, String valor) {
        try {
            validarCampos(marca, modelo, ano, placa, valor);
            new CarrosDAO().atualizar(marca, modelo, ano, placa, valor);
            // Chama o método de atualização no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após a atualização
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para apagar um carro do banco de dados
    public void apagar(String placa) {
        try {
            validarPlaca(placa);
            new CarrosDAO().apagar(placa);
            // Chama o método de exclusão no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para validar os campos
    private void validarCampos(String marca, String modelo, String ano, String placa, String valor) {
        if (marca.isEmpty() || modelo.isEmpty() || ano.isEmpty() || placa.isEmpty() || valor.isEmpty()) {
            throw new IllegalArgumentException("Preencha todos os campos.");
        }

        validarPlaca(placa);

        int anoInt;
        try {
            anoInt = Integer.parseInt(ano);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ano inválido. Insira um valor numérico.");
        }

        if (anoInt < 1930 || anoInt > 2024) {
            throw new IllegalArgumentException("Ano inválido. Insira um ano entre 1930 e 2024.");
        }

        if (!valor.matches("\\d+")) {
            throw new IllegalArgumentException("Valor inválido. Insira um valor numérico.");
        }

        if (!marca.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Marca inválida. Insira apenas letras.");
        }
    }

    // Método para validar a placa
    private void validarPlaca(String placa) {
        if (!placa.matches("[A-Z]{3}\\d{4}")) {
            throw new IllegalArgumentException("Placa inválida. Insira uma placa válida no formato AAA1234.");
        }
    }
}
