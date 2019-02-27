package View;

import java.io.File;

import javax.swing.JOptionPane;

import Test.SelecionarAqr;

public class vwBoot {
	public static String caminhoBD = "SQL/LITE/questoes.db";

	public static void main(String[] args) {
		File file = new File(caminhoBD);
		if(file.exists()) {
			vwHome home = new vwHome();
			home.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "Por favor, encontre o Banco de Questões e o Selecione.");
			SelecionarAqr aqr = new SelecionarAqr();
			aqr.main(null);
			caminhoBD = aqr.s;
			System.out.println(caminhoBD);
			vwHome home = new vwHome();
			home.setVisible(true);
		}
		
	}

}
