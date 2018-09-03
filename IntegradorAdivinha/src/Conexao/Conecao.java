package Conexao;

/*import java.sql.Connection;
import java.sql.DriverManager;

public class MinhaConexao {
	private String driver =  "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
    private String caminho = "jdbc:sqlserver://localhost:1433;databaseName=integrador";
	private String usuario = "LAPTOP-842V4HV4";
	private String senha = "";
	public Connection con;
	public Connection getConexao() throws MyClassException {
		try {
			Connection con;
			System.setProperty("com.microsoft.sqlserver.jdbc.SQLServerDriver", driver);
			con = DriverManager.getConnection(caminho,usuario,senha);
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			return con;
		} catch (Exception e) {
			MyClassException obj = new MyClassException("Erro em ClassNotFoundException");
			obj.setClasse(getClass().getName());
			obj.setMensagem(e.getMessage());
			obj.setMetodo("getConexao");
			throw obj;
		}
	}
}*/




 
/**
 *
 * @author RICARDO
 */
/*faz importação das bibliotecas do SQL para ser usado no projeto*/
import java.sql.*;
public class Conecao {
/*atributo que receberá a conexao, no momento ela inicia nula (vazia)*/
    private static Connection conexao = null;
/*metodo que irá retornar a conexao de forma true ou false*/
    public static Connection getConexao() {
        /*armazena a conexão para o banco de dados
	Connection conexao = null;
	driver de conexão com o banco de dados*/
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        /*url de conexão com o banco de dados*/
        String url = "jdbc:sqlserver://localhost:1433;databaseName=integrador";
        /*usuario de acesso ao banco de dados*/
        String usuario = "novaki";
        /*senha de acesso ao banco de dados*/
        String senha = "novaki";
 
        /*carrega o driver*/
        try {
            Class.forName(driver);
            /*obtem conexão com o banco de dados*/
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao efetuada com sucesso!!!");
        }
        /*caso de erro de carregamento de driver*/ 
        catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar driver: " + ex.getMessage());
        }
        /*caso de erro de conexão com o banco de dados.*/ 
        catch (SQLException ex) {
            System.out.println("Não foi possivel conectar com o Banco de Dados: " 
                    + ex.getMessage());
        }
        return conexao;
    }
    /*metodo para encerrar a conexao, fecha e libera memória*/
    public static void closeConexao() {
        /*verifica se a conexao não esta nula*/
        if (conexao != null) {
            /*bloco que vai ser executado e que sabemos que vai funcionar*/
            try {
                conexao.close();
                System.out.println("conexao encerrada com sucesso.");
            }
            /*se acaso der erro ao tentar fechar a conexao*/
            catch (SQLException ex) {
                System.out.println("erro ao fechar conexao: " + ex.getMessage());
            }
        }
        /*se acaso estiver nula*/
        else {
            System.out.println("conexao não pode ser encerrada pois "
                    + "não foi iniciada.");
            }
    }
}
