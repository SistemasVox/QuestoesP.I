package Test;

import Tools.Cor;

public class SelecionarCor {

	public static void main(String[] args) {
		for (int i = 0; i < Cor.getCores().size(); i++) {
			System.out.println(Cor.getCor(i));
		}
	}

}
