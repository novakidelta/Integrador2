package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.Jogar;
import Classes.ListarSqlFK;
import Classes.PerguntaSuspeito;
import Conexao.Conecao;
import Listagem.PerguntaID;

public class PerguntaSuspeitoDAO {
	
	private Connection connection;
    private PerguntaSuspeito perguntasuspeito;
	private ResultSet rs;
	private PreparedStatement stmt;
	String sql;
	Boolean situacao;
	public PerguntaSuspeitoDAO() {
		this.connection = new Conecao().getConexao();
	}
	
	public void ExcluirPerguntaeSuspeito (int IDPergunta , int IDSuspeito) throws SQLException{
		Conecao conecao = new Conecao();
		connection = conecao.getConexao();
		sql="delete from pergunta_suspeito  where FK_IDPergunta = ? and FK_IDSuspeito = ?";
		stmt=connection.prepareStatement(sql);
		stmt.setInt(1, IDPergunta);
		stmt.setInt(2, IDSuspeito);
		stmt.execute();
		stmt.close();
		connection.close();	
	}
	
	
	public void inserirPerguntaeSuspeito(PerguntaSuspeito perguntaSuspeito){ 
	    sql = "INSERT INTO pergunta_suspeito (FK_IDPergunta,FK_IDSuspeito,SusSit) VALUES(?,?,?)"; 
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
	//////////////////////////////////
	
	public  List <Jogar>  listarttodossuspeitoseperguntas() throws SQLException {
		List<Jogar> lista_perguntas = new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select FK_IDSuspeito,FK_IDPergunta "
				+ " from pergunta_suspeito ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt= connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()){			
			Jogar fk = new Jogar();
			fk.setIDSuspeito(rs.getInt("FK_IDSuspeito"));
			fk.setIDPergunta(rs.getInt("FK_IDPergunta"));
			lista_perguntas.add(fk);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_perguntas;
	}
	
	//////////////////////////////////
	
	public  List <ListarSqlFK>  listartabela(int IDSuspeito) throws SQLException {
		List<ListarSqlFK> lista_perguntas = new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select suspeito.nomeSuspeito , pergunta.pergunta , pergunta_suspeito.SusSit ,pergunta_suspeito.FK_IDPergunta "
				+ " from pergunta_suspeito "
				+ " inner join pergunta on pergunta.IDPergunta = pergunta_suspeito.FK_IDPergunta"
				+ " inner join suspeito on suspeito.IDSuspeito = pergunta_suspeito.FK_IDSuspeito"
				+ " where FK_IDSuspeito = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt= connection.prepareStatement(sql);
		stmt.setInt(1, IDSuspeito);
		rs=stmt.executeQuery();
		while(rs.next()){			
			ListarSqlFK fk = new ListarSqlFK();
			fk.setListaNomeSuspeito(rs.getString("nomeSuspeito"));
			fk.setListaPergunta(rs.getString("pergunta"));
			fk.setListaSusSit(rs.getInt("SusSit"));
			fk.setIDPergunta(rs.getInt("FK_IDPergunta"));
			lista_perguntas.add(fk);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_perguntas;
	}
	
	/////////////////////////////////
	
	public  List<PerguntaID>  listartFKIDPerguntas(int IDSuspeito) throws SQLException {
		List<PerguntaID> lista_perguntasFKID = new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select pergunta_suspeito.FK_IDPergunta,pergunta.pergunta,pergunta_suspeito.SusSit "
				+ " from pergunta_suspeito "
				+ " inner join pergunta on pergunta_suspeito.FK_IDPergunta = pergunta.IDPergunta "
				+ " where FK_IDSuspeito = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt= connection.prepareStatement(sql);
		stmt.setInt(1, IDSuspeito);
		rs=stmt.executeQuery();
		while(rs.next()){			
			PerguntaID fk = new PerguntaID();
			fk.setIDPergunta(rs.getInt("FK_IDPergunta"));
			fk.setPergunta(rs.getString("pergunta"));
			situacao=rs.getBoolean("SusSit");
			if(situacao == false) {
				fk.setSituacao("ADICIONAR");
			}
			else if(situacao == true) {
				fk.setSituacao("REMOVER");
			}
			lista_perguntasFKID.add(fk);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_perguntasFKID;
	}
	
	

}
