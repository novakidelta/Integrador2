package Classes;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.SuspeitoDAO;

public class Suspeito {
	
	private static String caracteristica;
	

	public static String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	public void Carregar_TabelaSuspeito(JTable tabela_suspeito) throws SQLException{
		DefaultTableModel defaultTableModel_supeito= new DefaultTableModel();
		
		defaultTableModel_supeito.addColumn("Caracteristicas");
		
		SuspeitoDAO suspeitoDao= new SuspeitoDAO();
			List<Suspeito> lista_suspeito= suspeitoDao.listar();
			
			for(Suspeito suspeito : lista_suspeito){///Mudei aqui para não dar problemas com o idduplicado
				
				defaultTableModel_supeito.addRow(new Object[]{
						suspeito.getCaracteristica(),
						
				});
			
		}
			tabela_suspeito.setModel(defaultTableModel_supeito);
		}
	
}
