package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Classes.Pergunta;
import Conexao.Conecao;
import Listagem.PerguntaID;

public class PerguntaDAO {
	
	private Connection connection;
    private Pergunta pergunta;
	private ResultSet rs;
	private PreparedStatement stmt;
	boolean situacao;
	String sql;
	
	
	public PerguntaDAO(){
		this.connection = new Conecao().getConexao();
	}
	public void inserirpergunta(Pergunta pergunta){ 
	    sql = "INSERT INTO pergunta (pergunta,situacao) VALUES(?,0)"; 
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
	
		String sql="select * from pergunta ";
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
	//////////////////////////
	public  List <PerguntaID>  listarIDperguntas() throws SQLException {
		List<PerguntaID> lista_IDperguntas = new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select IDPergunta,pergunta,situacao from pergunta ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt= connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()){			
			PerguntaID pergunta = new PerguntaID();
			pergunta.setIDPergunta(rs.getInt("IDPergunta"));
			pergunta.setPergunta(rs.getString("pergunta"));
			situacao=(rs.getBoolean("situacao"));
			if(situacao == false) {
				pergunta.setSituacao("ADICIONAR");
			}
			else if(situacao == true) {
				pergunta.setSituacao("REMOVER");
			}
			lista_IDperguntas.add(pergunta);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_IDperguntas;
	}
	///////////////////////////////////
	public void ExcluirPergunta (int IDPergunta) throws SQLException{
		Conecao conecao = new Conecao();
		connection = conecao.getConexao();
		sql="delete from pergunta where IdPergunta =?;";
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
