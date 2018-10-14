package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.Cliente;
import Classes.Pergunta;
import Classes.Suspeito;
import Conexao.Conecao;

public class PerguntaDAO {
	
	private Connection connection;
    private Pergunta pergunta;
	private ResultSet rs;
	private PreparedStatement stmt;
	String sql;
	
	
	public PerguntaDAO(){
		this.connection = new Conecao().getConexao();
	}
	public void inserirpergunta(Pergunta pergunta){ 
	    sql = "INSERT INTO perguntas (pergunta) VALUES(?)"; 
	    try { 
	        stmt = connection.prepareStatement(sql);
	        stmt.setString(1, pergunta.getPergunta());
	        stmt.execute();
	        stmt.close();
	    } 
	    catch (SQLException u) { 
	        throw new RuntimeException(u);
	    } 
	   
	}
	public  List <Pergunta>  listarperguntas() throws SQLException {
		List<Pergunta> lista_perguntas = new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select * from perguntas ";//pega somente os clientes ativos onde ativo é true quando é excluido o ativo vira false e a tabela não busca
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt= connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()){			
			Pergunta pergunta = new Pergunta();
			pergunta.setIDPergunta(rs.getInt("IDPerguntas"));
			pergunta.setPergunta(rs.getString("pergunta"));
			lista_perguntas.add(pergunta);
			System.out.println(pergunta.getPergunta());
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_perguntas;
	}
	
	public void ExcluirPergunta (int IDPergunta) throws SQLException{
		Conecao conecao = new Conecao();
		connection = conecao.getConexao();
		/*sql="delete from perguntas where pergunta =?;";
		stmt =connection.prepareStatement(sql);
		stmt.setString(1, pergunta);*/
		sql="delete from Perguntas where IdPerguntas =?;";
		stmt=connection.prepareStatement(sql);
		stmt.setInt(1, IDPergunta);
		stmt.execute();
		stmt.close();
		connection.close();	
		
	}

	/*public void alterarPergunta (int id_Alterar, Cliente c1) throws MyClassException, SQLException{
		MinhaConexao conexao= new MinhaConexao();
		con=conexao.getConexao();
		
		Sql="UPDATE INTO perguntas SET pergunta = ? WHERE pergunta =?;";
		stm= con.prepareStatement(Sql);
		stm.setInt(1, c1.getPessoa().getEndereco().getId_endereco());
		
		stm.setString(2, c1.getPessoa().getEndereco().getRua());
		stm.setString(3, c1.getPessoa().getEndereco().getBairro());
		stm.setString(4, c1.getPessoa().getEndereco().getNumero());
		stm.setString(5, c1.getPessoa().getEndereco().getCidade());
		stm.setString(6, c1.getPessoa().getEndereco().getCep());
		stm.setString(7, c1.getPessoa().getEndereco().getEstado());
		stm.setInt(8, id_Alterar);
		System.out.println(id_Alterar);
		
		stm.execute();
		stm.close();
		con.close();
		}*/
	
}
