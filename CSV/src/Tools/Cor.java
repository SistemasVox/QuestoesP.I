package Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Cor {

	private static ArrayList<String> cores = new ArrayList<String>();
	private static BufferedReader lerArq;

	public static ArrayList<String> getCores() {		
		if (cores.isEmpty()) {
			lerCores();
		}
		return cores;
	}
	
	private static void lerCores() {
		try {
			FileReader arq = new FileReader("cor/cor.txt");
			lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();

			while (linha != null) {
				cores.add(linha);
				linha = lerArq.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String getCor(int i) {
		if (cores.isEmpty()) {
			lerCores();
			getCor(i);
		}
		return cores.get(i);
	}

}
