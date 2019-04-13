package Test;

import java.util.ArrayList;

import Tools.Aleatorio;

public class TestAleatorio {

	public static void main(String[] args) {
		/*
		for (int i = 0; i < 10; i++) {
			System.out.println(Aleatorio.getNum(10));
		}*/
		ArrayList<Integer> combiA = Aleatorio.gerarCombinacaoAleatorio(4, 4);
		
		for (int i = 0; i < combiA.size(); i++) {
			System.out.println(combiA.get(i));			
		}
	}

}
