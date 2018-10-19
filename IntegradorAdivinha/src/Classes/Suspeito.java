package Classes;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.SuspeitoDAO;

public class Suspeito {
	
	private  String caracteristica;
	private String nome;
	private int IDSuspeito;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIDSuspeito() {
		return IDSuspeito;
	}

	public void setIDSuspeito(int iDSuspeito) {
		IDSuspeito = iDSuspeito;
	}

	public  String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	public void Carregar_TabelaSuspeito(JTable tabela_suspeito) throws SQLException{
		DefaultTableModel defaultTableModel_supeito= new DefaultTableModel();
		defaultTableModel_supeito.addColumn("ID");
		defaultTableModel_supeito.addColumn("Caracteristicas");
		
		SuspeitoDAO suspeitoDao= new SuspeitoDAO();
			List<Suspeito> lista_suspeito= suspeitoDao.listarSuspeitos();
			
			for(Suspeito suspeito : lista_suspeito){
				
				defaultTableModel_supeito.addRow(new Object[]{suspeito.getIDSuspeito(),suspeito.getCaracteristica()
						
				});
			
		}
			tabela_suspeito.setModel(defaultTableModel_supeito);
		}
}
