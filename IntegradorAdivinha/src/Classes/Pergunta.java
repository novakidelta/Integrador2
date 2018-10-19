package Classes;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.PerguntaDAO;
import DAO.SuspeitoDAO;

public class Pergunta {

	private static final Object Boolean = null;
	private String pergunta;
	private int IDPergunta;
	private Boolean VouF;
	
	public Boolean getVouF() {
		return VouF;
	}

	public void setVouF(Boolean vouF) {
		VouF = vouF;
	}

	public int getIDPergunta() {
		return IDPergunta;
	}

	public void setIDPergunta(int iDPergunta) {
		IDPergunta = iDPergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	
	public String getPergunta() {
		return pergunta;
	}
	
	public void Carregar_TabelaPergunta (JTable tabela_pergunta) throws SQLException{
		DefaultTableModel defaultTableModel_pergunta= new DefaultTableModel();

		defaultTableModel_pergunta.addColumn("ID");
		defaultTableModel_pergunta.addColumn("Pergunta");

		
		
		PerguntaDAO perguntaDao = new PerguntaDAO();
			List<Pergunta> lista_perguntas = perguntaDao.listarperguntas();
			
			for(Pergunta pergunta : lista_perguntas){
				
				defaultTableModel_pergunta.addRow(new Object[]{pergunta.getIDPergunta(),pergunta.getPergunta()
						
				});
			
		}
			tabela_pergunta.setModel(defaultTableModel_pergunta);
		}
	
	
}
