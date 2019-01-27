package Classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.PerguntaSuspeitoDAO;

public class Comparar {
	int IDSuspeito;
	List<Classes.Jogar> lista_recebida_bd = new ArrayList<Classes.Jogar>();
	List<Integer> lista_numeros = new ArrayList<Integer>();
	int idAuxiliar=0;
	int qntAuxiliar=0;
	int idFinal=0;
	int qntFinal=0;
	
	PerguntaSuspeitoDAO perguntaSuspeitoDAO = new  PerguntaSuspeitoDAO();
	
	public int Comparar(List<RecebeRespostas> recebe_respostas) throws SQLException {
		lista_recebida_bd=perguntaSuspeitoDAO.listarttodossuspeitoseperguntas();
		for(int i = 0; i<recebe_respostas.size();i++) {
			for (int j = 0; j<lista_recebida_bd.size();j++) {
				if(lista_recebida_bd.get(j).getIDPergunta().equals(recebe_respostas.get(i).getIDPergunta())) {
					
					
					idAuxiliar=lista_recebida_bd.get(j).getIDSuspeito();
					qntAuxiliar++;
					if(qntAuxiliar>qntFinal) {
						qntFinal=qntAuxiliar;
						idFinal=idAuxiliar;
						IDSuspeito=idAuxiliar;
					}
				}
			}
		}
			return IDSuspeito;		
		
	}
	
	public int CompararExcluido(List<RecebeRespostas> recebe_respostas) throws SQLException {
		lista_recebida_bd=perguntaSuspeitoDAO.listarttodossuspeitoseperguntas();
		for(int i = 0; i<recebe_respostas.size();i++) {
			for (int j = 0; j<lista_recebida_bd.size();j++) {
				if(lista_recebida_bd.get(j).getIDPergunta().equals(recebe_respostas.get(i).getIDPergunta())) {
					
					
					idAuxiliar=lista_recebida_bd.get(j).getIDSuspeito();
					qntAuxiliar++;
					if(qntAuxiliar>qntFinal) {
						qntFinal=qntAuxiliar;
						idFinal=idAuxiliar;
						IDSuspeito=idAuxiliar;
					}
				}
			}
		}
			return IDSuspeito;		
		
	}
	

}
