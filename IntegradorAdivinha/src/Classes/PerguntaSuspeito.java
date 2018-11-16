package Classes;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.PerguntaDAO;
import DAO.PerguntaSuspeitoDAO;
import DAO.SuspeitoDAO;
import Listagem.PerguntaID;

public class PerguntaSuspeito {
	
	private Integer FK_IDPergunta;
	private Integer FK_IDSuspeito;
	private Integer FK_SusSit;


	public Integer getFK_SusSit() {
		return FK_SusSit;
	}

	public void setFK_SusSit(Integer fK_SusSit) {
		FK_SusSit = fK_SusSit;
	}

	public Integer getFK_IDPergunta() {
		return FK_IDPergunta;
	}

	public void setFK_IDPergunta(Integer fK_IDPergunta) {
		FK_IDPergunta = fK_IDPergunta;
	}

	public Integer getFK_IDSuspeito() {
		return FK_IDSuspeito;
	}

	public void setFK_IDSuspeito(Integer fK_IDSuspeito) {
		FK_IDSuspeito = fK_IDSuspeito;
	}

public void Carregar_TabelaPerguntaSuspeito (JTable tabela_perguntasuspeito,int IDSuspeito) throws SQLException{
		
		DefaultTableModel defaultTableModel_perguntasuspeito= new DefaultTableModel() {
			Class[] columnTypes = new Class[] {
					Object.class,Object.class,Object.class
				};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		
		defaultTableModel_perguntasuspeito.addColumn("Nome");
		defaultTableModel_perguntasuspeito.addColumn("Pergunta");
		defaultTableModel_perguntasuspeito.addColumn("Situação");
		
		
		PerguntaSuspeitoDAO perguntaSuspeitoDAO = new PerguntaSuspeitoDAO();
		List<ListarSqlFK> lista_perguntas = perguntaSuspeitoDAO.listartabela(IDSuspeito);
			
			for(ListarSqlFK fk : lista_perguntas){
				defaultTableModel_perguntasuspeito.addRow(new Object[]{fk.getListaNomeSuspeito(),fk.getListaPergunta(),fk.getListaSusSit()});
		}
			tabela_perguntasuspeito.setModel(defaultTableModel_perguntasuspeito);
		}

//////////////////////////////////////////////////////////////

public void Carregar_TabelaPerguntaID(JTable tabela_perguntasuspeito,List<PerguntaID> lista_perguntas) throws SQLException{
	
	DefaultTableModel defaultTableModel_perguntasuspeito= new DefaultTableModel() {
		Class[] columnTypes = new Class[] {
				Object.class,Object.class,Object.class
			};
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
	};
	
	defaultTableModel_perguntasuspeito.addColumn("ID");
	defaultTableModel_perguntasuspeito.addColumn("Pergunta");
	defaultTableModel_perguntasuspeito.addColumn("Situação");

		for(PerguntaID fk : lista_perguntas){
			defaultTableModel_perguntasuspeito.addRow(new Object[]{fk.getIDPergunta(),fk.getPergunta(),fk.getSituacao()});
	}
		tabela_perguntasuspeito.setModel(defaultTableModel_perguntasuspeito);
	}
}

