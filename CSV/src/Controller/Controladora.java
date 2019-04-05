package Controller;

import java.util.ArrayList;

import Model.Alternativa;
import Model.AlternativaDAO;
import Model.Area_Conhecimento;
import Model.Area_ConhecimentoDAO;
import Model.Conteudo;
import Model.ConteudoDAO;
import Model.Disciplina;
import Model.DisciplinaDAO;
import Model.Questoes;
import Model.QuestoesDAO;

public class Controladora {
	public static String consultarTotalQ() {
		return QuestoesDAO.consultarTotalQ();
	}
	public static String consultarTotalA() {
		return AlternativaDAO.consultarTotalA();
	}
	public static void savarQ(Questoes questao) {
		QuestoesDAO.salvarQ(questao);		
	}
	public static void savarA(Alternativa alternativa) {
		AlternativaDAO.salvarA(alternativa);
		
	}
	public static Questoes consultarQuestao(String id) {
		return QuestoesDAO.consultarQuestao(id);
	}
	public static Alternativa consultarAlternativa(String id) {
		return AlternativaDAO.consultarAlternativa(id);
	}
	public static ArrayList<Alternativa> getAlternativas(String id) {
		return AlternativaDAO.getAlternativas(id);
	}
	public static ArrayList<Area_Conhecimento> consultarAreas() {
		return Area_ConhecimentoDAO.consultarAreas();
	}
	public static ArrayList<Disciplina> consultarDisciplinas(String nomeArea) {
		return DisciplinaDAO.consultarDisciplinas(nomeArea);
	}
	public static ArrayList<String> consultarConteudos(String nomeDisc) {
		return ConteudoDAO.consultarConteudos(nomeDisc);
	}
}
