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
			lista_FK.add(perguntaSuspeito);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_FK;
	}
	
	

}
