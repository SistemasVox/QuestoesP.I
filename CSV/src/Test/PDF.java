package Test;

import Tools.ExportarPDF;

public class PDF {

	public static void main(String[] args) {
		ExportarPDF exporTEMP = new ExportarPDF("Reino Fungi - II", ") ", false);
		exporTEMP.gerarPDF();
	}

}
