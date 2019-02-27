package Test;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelecionarAqr {
	
	public static String s = "";

	public static void main(String[] args) {
		FileNameExtensionFilter filtroDB = new FileNameExtensionFilter("Arquivos Lite", "db");  
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filtroDB);
		fc.setDialogTitle("Selcionando o Banco Questões");
		int resposta = fc.showOpenDialog(null);
		System.out.println(resposta);
		
		if (resposta == JFileChooser.APPROVE_OPTION) {
			File file = new File(fc.getSelectedFile().getAbsolutePath());
			//System.out.println(file.getPath().toString());
			s = file.getPath().toString();
		}else {
			System.exit(0);
		}
	}

}
