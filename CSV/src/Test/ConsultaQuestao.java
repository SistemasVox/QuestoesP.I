package Test;

import java.util.ArrayList;

import Controller.Controladora;
import Model.Alternativa;
import Model.Questoes;

public class ConsultaQuestao {

	public static void main(String[] args) {
		//Questoes questao = Controladora.consultarQuestao("1");
		//System.out.println(questao.toString());
		
		//Alternativa alternativa = Controladora.consultarAlternativa("1");
		//System.out.println(alternativa.toString());
		/*
		int id = 3;
		final int TAM = 5;
		
		for (int i = (((id - 1) * TAM) + 1); i <= (((id -1) * TAM) + TAM); i++) {			
			System.out.println(Controladora.consultarAlternativa(String.valueOf(i)).toString());
		}*/
		
		ArrayList<Alternativa> alternativas = Controladora.getAlternativas("1");
		
		for (int i = 0; i < alternativas.size(); i++) {
			System.out.println(alternativas.get(i).toString());
		}
	}

}
