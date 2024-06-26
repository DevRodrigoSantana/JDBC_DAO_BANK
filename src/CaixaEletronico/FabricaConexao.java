package CaixaEletronico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FabricaConexao {
	public static Connection getConexao() {
		try {
			
		final String url = "";
		final String usuario =  ""; 
		final String senha = "";

		return DriverManager.getConnection(url,usuario,senha);
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

