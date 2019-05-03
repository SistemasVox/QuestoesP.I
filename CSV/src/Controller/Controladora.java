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
	public static ArrayList<Questoes> consultarQuestoesC(String id) {
		return QuestoesDAO.consultarQuestoesC(id);
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
	public static ArrayList<Disciplina> consultarTodasDisciplinas() {
		return DisciplinaDAO.consultarTodasDisciplinas();
	}
	public static ArrayList<Conteudo> consultarConteudos(String nomeDisc) {
		return ConteudoDAO.consultarConteudos(nomeDisc);
	}
	public static String consultarTotalQ(String nomeConteudo) {
		return  QuestoesDAO.consultarTotalQ(nomeConteudo);
	}
	public static ArrayList<Questoes> getQuestoes(String nomeConteudo) {
		return  QuestoesDAO.getQuestoesC(nomeConteudo);
	}
	public static ArrayList<Conteudo> getConteudos(String nomeConteudo) {
		return ConteudoDAO.getConteudos(nomeConteudo);
	}
	public static ArrayList<Questoes> getQuestoesSemAssociacao() {
		return QuestoesDAO.getQuestoesSemAssociacao();
	}
	public static void insertQuestaoConteudo(String codQ, String codC) {
		QuestoesDAO.insertQuestaoConteudo(codQ, codC);		
	}
	public static int consultarQuestaoConteudo(String codQ, String codC) {
		return QuestoesDAO.consultarQuestaoConteudo(codQ, codC);
	}
	public static int consultarConteudoExiste(String nome) {
		return ConteudoDAO.consultarConteudoExiste(nome);
	}
	public static void insertConteudo(String nome, String desc, String cod, String serie) {
		ConteudoDAO.salvarNovo(nome, desc, cod, serie);	
	}
	public static void excluirConteudo(String cod) {
		ConteudoDAO.excluirConteudo(cod);		
	}
}
