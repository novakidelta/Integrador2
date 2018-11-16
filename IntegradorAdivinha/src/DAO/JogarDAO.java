package DAO;


import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import Classes.Jogar;
import Classes.Pergunta;

public class JogarDAO {
	Jogar jogar = new Jogar();
	PerguntaDAO perguntaDao = new PerguntaDAO();
	public List <Pergunta> PreencherArray () throws SQLException{
		
		jogar.lista_perguntas = perguntaDao.listarperguntas();
		Collections.shuffle(jogar.lista_perguntas);// pega a arraylist e embaralha as perguntas
		
		return jogar.lista_perguntas;
	}
	
}
