package DAO;

import Conexao.Conecao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Classes.Suspeito;


public class SuspeitoDAO { 
    private Connection connection;
    private Suspeito suspeito;
	private ResultSet rs;
	String sql;
	PreparedStatement stmt;
	
    public SuspeitoDAO(){ 
      
        this.connection = new Conecao().getConexao();
    } 
    public void inserirsuspeito(Suspeito suspeito){ 
       sql = "INSERT INTO suspeito (nomeSuspeito,situacao) VALUES(?,1)"; 
        try { 
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, suspeito.getNome());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
       
    }
	public  List <Suspeito>  listarSuspeitos() throws SQLException {
		List<Suspeito> lista_suspeito= new ArrayList<>();
		Conecao conexao= new Conecao();
		connection = conexao.getConexao();
	
		String sql="select * from suspeito ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt= connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()){			
			Suspeito suspeito =new Suspeito();
			suspeito.setIDSuspeito(rs.getInt("IDSuspeito"));
			suspeito.setCaracteristica(rs.getString("nomeSuspeito"));
			lista_suspeito.add(suspeito);
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_suspeito;
	}
	
	public void excluirSuspeito (int IDSuspeito) throws SQLException{
		Conecao conecao = new Conecao();
		connection = conecao.getConexao();
		sql="delete from suspeito where IdSuspeito =?;";
		stmt=connection.prepareStatement(sql);
		stmt.setInt(1, IDSuspeito);
		stmt.execute();
		stmt.close();
		connection.close();	
	}
	
    
}