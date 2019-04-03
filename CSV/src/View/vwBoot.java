package View;

import java.io.File;

import javax.swing.JOptionPane;

import Test.SelecionarAqr;

public abstract class vwBoot {
	public static String caminhoBD = "SQL/LITE/questoes.db";

	public static void main(String[] args) {
		boolean continuar = true;
		do {
			File file = new File(caminhoBD);
			if(file.exists()) {
				continuar = false;
				vwHome home = new vwHome();
				home.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "["+file.getAbsolutePath()+"] não encontrado.\nPor favor, encontre o Banco de Questões e o Selecione.");
				SelecionarAqr aqr = new SelecionarAqr();
				aqr.main(null);
				caminhoBD = aqr.s;				
			}
		} while (continuar);
		
	}

}
