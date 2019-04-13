package Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.Controladora;
import Model.Alternativa;
import Model.Questoes;

public class ExportarPDF {
	private Document document = new Document();
	private static ArrayList<Questoes> questoes;
	private static String cor;
		
	
	@SuppressWarnings("static-access")
	public ExportarPDF(ArrayList<Questoes> questoes) {
		super();
		this.questoes = questoes;
	}
	public void gerarPDF(){		
		try {
			CreateDirectory.CriarDiretorio("Questionários");
			cor = Cor.getCor(Aleatorio.getNum(Cor.getCores().size()));
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Questionários/Questionário_" + cor + ".pdf"));
			document.open();
			adcionarLogo(document);
			//document.add(new Paragraph("A Hello World PDF document."));
			adcionarQuestao(document);
			fim(document);
			propriedade(document);
			document.close();
			writer.close();
			
			File arquivo = new File("Questionários/Questionário_" + cor + ".pdf");
			JOptionPane.showMessageDialog(null, "Exportado com Sucesso.\n" + arquivo.getAbsolutePath());
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void fim(Document document) {
	    Paragraph chapterTitle = new Paragraph("FIM.", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(14, 12, 0, 84)));
	    Chapter chapter1 = new Chapter(chapterTitle, 1);
	    chapter1.setNumberDepth(0);	
	    try {
			document.add(chapter1);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void adcionarQuestao(Document document) {
	    //Add ordered list
		List orderedList = new List(List.ORDERED);
		
		
		desordenarQuestoes();
		
		for (int i = 0; i < questoes.size(); i++) {		    
		    orderedList.add(new ListItem(Controladora.consultarQuestao(questoes.get(i).getCod()).getEnunciado()));
		    adcionarAlternativas(document, orderedList, questoes.get(i).getCod());
		}
		
		try {
			document.add(orderedList);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	private static void desordenarQuestoes() {
		ArrayList<Integer> integers = Aleatorio.gerarCombinacaoAleatorio(questoes.size(), questoes.size());
		ArrayList<Questoes> questoesAUX = new ArrayList<Questoes>();
		
		for (int i = 0; i < integers.size(); i++) {
			questoesAUX.add(questoes.get(integers.get(i)));
		}
		questoes.clear();
		questoes = questoesAUX;
	}
	private static void adcionarAlternativas(Document document, List orderedList, String idQ) {
		List nestedList = new List(List.UNORDERED);
		List sublist = new List(true, false, 30);
		sublist.setListSymbol(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6)));
		
		ArrayList<Alternativa> alternativas = Controladora.getAlternativas(idQ);		
		alternativas = desordenarAlternativas(alternativas);
		
		for (int i = 0; i < alternativas.size(); i++) {
			nestedList.add(Alphabet.getLetra(i).toLowerCase() + ") " + alternativas.get(i).getResposta());
		}
		orderedList.add(nestedList);	
	}

	private static ArrayList<Alternativa> desordenarAlternativas(ArrayList<Alternativa> alternativas) {
		ArrayList<Integer> integers = Aleatorio.gerarCombinacaoAleatorio(alternativas.size(), alternativas.size());
		ArrayList<Alternativa> alternativasAUX = new ArrayList<Alternativa>();
		
		for (int i = 0; i < integers.size(); i++) {
			alternativasAUX.add(alternativas.get(integers.get(i)));				
		}
		 return alternativasAUX;
				
	}
	private static void adcionarLogo(Document document) {
		try {
		    //Add Image
		    Image image1 = Image.getInstance("pic/logo.png");
		    image1.setAlignment(Element.ALIGN_CENTER);
		    document.add(image1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void propriedade(Document document) {
		try {
			// Set attributes here
			document.addAuthor("X-Quest");
			document.addCreationDate();
			document.addCreator("SistemasVOX");
			document.addTitle("Questionário " + cor);
			document.addSubject("Questionário criado, por X-Quest.");
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}