package Controller;

import Model.Alternativa;
import Model.AlternativaDAO;
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
}
