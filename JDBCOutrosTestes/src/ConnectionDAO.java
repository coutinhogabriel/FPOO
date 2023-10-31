import java.sql.*;

public class ConnectionDAO {
    public void cleanup() {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        {
            try {
                stmt.executeUpdate("drop table tbl_basica");
                con.close();
            } catch (Exception ex) {
                // Ignorar todos os erros
            }
        }
        
    }
    public void doexemple() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        System.out.println("\nExecutando os testes");
        // criar a tabela que armazena os daods
        stmt.execu
}
