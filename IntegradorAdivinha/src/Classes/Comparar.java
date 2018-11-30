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
					
					/*lista_numeros.add(lista_recebida_bd.get(j).getIDSuspeito());
					System.out.println(lista_recebida_bd.get(j).getIDSuspeito());*/
				}
			}
		}
		
		
		
		/*for(int i =0; i < lista_numeros.size();i++) {
			for (int j = 1; j< lista_numeros.size();j++) {
				if(lista_numeros.get(i) == lista_numeros.get(j)) {
					idAuxiliar=lista_numeros.get(i);
					qntAuxiliar++;
					if(qntAuxiliar>qntFinal) {
						qntFinal=qntAuxiliar;
						idFinal=idAuxiliar;
						IDSuspeito=idAuxiliar;
						
					}
				}
			}
		}*/
		// aqui ele deve fazer a contagem de quem se repete mais vezes no array e manar para idsuspeito o id dele para dizer que é o suspeito
		return IDSuspeito;		
		
	}
	

}
