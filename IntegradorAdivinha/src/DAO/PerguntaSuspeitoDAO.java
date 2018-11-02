package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.Pergunta;
import Classes.PerguntaSuspeito;
import Conexao.Conecao;

public class PerguntaSuspeitoDAO {
	
	private Connection connection;
    private PerguntaSuspeito perguntasuspeito;
	private ResultSet rs;
	private PreparedStatement stmt;
	String sql;
	
	public PerguntaSuspeitoDAO() {
		this.connection = new Conecao().getConexao();
	}
	
	public void inserirPerguntaeSuspeito(PerguntaSuspeito perguntaSuspeito){ 
	    sql = "INSERT INTO pergunta_suspeito (FK_IDPergunta,FK_IDSuspeito,FK_SusSit) VALUES(?,?,?)"; 
	    try { 
	        stmt = connection.prepareStatement(sql);
	        stmt.setInt(1, perguntaSuspeito.getFK_IDPergunta());
	        stmt.setInt(2, perguntaSuspeito.getFK_IDSuspeito());
	        stmt.setInt(3, perguntaSuspeito.getFK_SusSit());
	        stmt.execute();
	        stmt.close();
	    } 
	    catch (SQLException u) { 
	        throw new RuntimeException(u);
	    } 
	   
	}
	
	public  List <PerguntaSuspeito>  listarFK() throws SQLException {
		List<PerguntaSuspeito> lista_FK = new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select * from pergunta_suspeito ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt= connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()){			
			PerguntaSuspeito perguntaSuspeito = new PerguntaSuspeito();
			perguntaSuspeito.setFK_IDPergunta(rs.getInt("FK_IDPergunta"));
			perguntaSuspeito.setFK_IDSuspeito(rs.getInt("FK_IDSuspeito"));
			perguntaSuspeito.setFK_SusSit(rs.getInt("FK_SusSit"));
			lista_FK.add(perguntaSuspeito);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_FK;
	}
	
	public  List <Pergunta>  listarperguntas() throws SQLException {
		List<Pergunta> lista_perguntas = new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select * from pergunta "
				+ "inner join "
				+ "on ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt= connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()){			
			Pergunta pergunta = new Pergunta();
			pergunta.setIDPergunta(rs.getInt("IDPergunta"));
			pergunta.setPergunta(rs.getString("pergunta"));
			pergunta.setVouF(rs.getBoolean("situacao"));
			lista_perguntas.add(pergunta);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_perguntas;
	}

}
