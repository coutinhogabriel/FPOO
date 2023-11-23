package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.ConnectionFactory;
import Model.Clientes;

public class ClientesDAO {
    private Connection connection;
    private List<Clientes> clientes;

    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes_lojacarros (Nome VARCHAR(255), Contato VARCHAR(255), CPF VARCHAR(255) PRIMARY KEY)";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Clientes> listarTodos() {
        PreparedStatement stmt = null;
        clientes = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM clientes_lojacarros");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes(rs.getString("Nome"), rs.getString("Contato"), rs.getString("CPF"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar clientes: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        return clientes;
    }

    public void cadastrar(String nome, String contato, String cpf) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO clientes_lojacarros (Nome, Contato, CPF) VALUES (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, contato);
            stmt.setString(3, cpf);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void atualizar(String nome, String contato, String cpf) {
        PreparedStatement stmt = null;
        String sql = "UPDATE clientes_lojacarros SET Nome = ?, Contato = ? WHERE CPF = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, contato);
            stmt.setString(3, cpf);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String cpf) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM clientes_lojacarros WHERE CPF = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
