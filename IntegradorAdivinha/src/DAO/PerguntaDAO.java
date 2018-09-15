package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
			pergunta.setPergunta(rs.getString(("pergunta")));
			lista_perguntas.add(pergunta);
			System.out.println(pergunta.getPergunta());
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_perguntas;
	}
	
	public void excluirSuspeito (int id_Pergunta) throws SQLException{
		Conecao conecao = new Conecao();
		connection = conecao.getConexao();
		sql="update perguntas set ativo=0 where pergunta =?;";
		stmt =connection.prepareStatement(sql);
		stmt.setInt(1, id_Pergunta);
		stmt.execute();
		stmt.close();
		connection.close();	
	}
	
}
