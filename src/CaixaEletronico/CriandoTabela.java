package CaixaEletronico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriandoTabela {

	public static void main(String[] args) throws SQLException {
    	Connection conexao = FabricaConexao.getConexao();
       
        String tabelaCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                "cpf INT PRIMARY KEY," +
                "nome VARCHAR(100) NOT NULL," +
                "senha VARCHAR(100) NOT NULL" +
                ")";
        
        String TabelaConta = "CREATE TABLE IF NOT EXISTS conta (" +
                "numeroConta INT PRIMARY KEY," +
                "tipoConta VARCHAR(50) NOT NULL," +
                "saldo DECIMAL(10, 2) NOT NULL," +
                "cpf INT," +
                "FOREIGN KEY (cpf) REFERENCES cliente(cpf)" +
                ")";

    	Statement stmt = conexao.createStatement();
		stmt.execute(tabelaCliente);
		stmt.execute(TabelaConta);
		
		System.out.println("Tabela Criada com sucesso");
		conexao.close();
        }
    }

