package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Vendas;

public class VendasDAO {
    private Connection connection;
    private List<Vendas> vendas;

    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS vendas_lojacarros (Comprador VARCHAR(255) PRIMARY KEY, Modelo VARCHAR(255), Data VARCHAR(10), Valor VARCHAR(10))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Vendas> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        vendas = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM vendas_lojacarros");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas venda = new Vendas(rs.getString("Comprador"), rs.getString("Modelo"), rs.getString("Data"),
                        rs.getString("Valor"));
                vendas.add(venda);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar vendas: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return vendas;
    }

    public void cadastrar(String comprador, String modelo, String data, String valor) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO vendas_lojacarros (Comprador, Modelo, Data, Valor) VALUES (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, comprador);
            stmt.setString(2, modelo);
            stmt.setString(3, data);
            stmt.setString(4, valor);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void atualizar(String comprador, String modelo, String data, String valor) {
        PreparedStatement stmt = null;
        String sql = "UPDATE vendas_lojacarros SET Modelo = ?, Data = ?, Valor = ? WHERE Comprador = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelo);
            stmt.setString(2, data);
            stmt.setString(3, valor);
            stmt.setString(4, comprador);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String comprador) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM vendas_lojacarros WHERE Comprador = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, comprador);
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
