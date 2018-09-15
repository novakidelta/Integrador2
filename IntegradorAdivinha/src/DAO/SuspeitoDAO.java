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

    public SuspeitoDAO(){ 
      
        this.connection = new Conecao().getConexao();
    } 
    public void inserirsuspeito(Suspeito suspeito){ 
       String sql = "INSERT INTO suspeito (caracteristica) VALUES(?)"; 
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, suspeito.getCaracteristica());
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
	
		String sql="select * from suspeito ";//pega somente os clientes ativos onde ativo é true quando é excluido o ativo vira false e a tabela não busca
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt= connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()){			
			Suspeito suspeito =new Suspeito();
			suspeito.setCaracteristica(rs.getString(("caracteristica")));
			lista_suspeito.add(suspeito);
			System.out.println(suspeito.getCaracteristica());
			}
		connection.close();
		stmt.close();
		rs.close();
		
		return lista_suspeito;
	}
    
}