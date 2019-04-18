package Tools;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.Controladora;
import Model.Alternativa;
import Model.Gabarito;
import Model.Questoes;

public class ExportarPDF {
	private Document document = new Document();
	private static String alternativasFormatacao;
	private static boolean letraMaiuscula;
	private static String nomeConteudo;
	private static ArrayList<Questoes> questoes;
	private static String cor;
	private static ArrayList<Gabarito> gabarito = new ArrayList<Gabarito>();;
		
	
	@SuppressWarnings("static-access")
	public ExportarPDF(String nomeConteudo, String alternativasFormatacao, boolean letraMaiuscula) {
		this.nomeConteudo = nomeConteudo;
		this.alternativasFormatacao = alternativasFormatacao;
		this.letraMaiuscula = letraMaiuscula;
	}
	public void gerarPDF(){
		try {
			CreateDirectory.CriarDiretorio("Questionários");
			cor = Cor.getCor(Aleatorio.getNum(Cor.getCores().size()));
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Questionários/Questionário_" + cor + ".pdf"));
			document.open();
			adcionarLogo(document);
			
			Paragraph preface = new Paragraph("Conteúdo: " + nomeConteudo +".\n\n"); 
			preface.setAlignment(Element.ALIGN_CENTER);
			
			document.add(preface);
			adcionarQuestao(document);
			fim(document);
			imprimirGabarito(document);
			propriedade(document);
			document.close();
			writer.close();
			
			File arquivo = new File("Questionários/Questionário_" + cor + ".pdf");
			if (JOptionPane.showConfirmDialog(null, "Exportado com Sucesso.\n" + arquivo.getAbsolutePath() +"\nDeseja Abrí-lo?") == 0) {
		        Desktop desktop = Desktop.getDesktop();
		        if(arquivo.exists())
					try {
						desktop.open(arquivo);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Opps: " + e.getMessage());
					}
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	private void imprimirGabarito(Document document) {
		try {
			PdfPTable table = new PdfPTable(2);	
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	        
	        //Set Column widths
	        float[] columnWidths = {1f, 1f};
	        table.setWidths(columnWidths);
	        
	        //Cabeçalho
	        PdfPCell cell1 = new PdfPCell(new Paragraph("Questão.", new Font(FontFamily.TIMES_ROMAN, 16, 1)));
	        cell1.setBorderColor(BaseColor.BLACK);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell cell2 = new PdfPCell(new Paragraph("Alternativa Correta.", new Font(FontFamily.TIMES_ROMAN, 16, 1)));
	        cell2.setBorderColor(BaseColor.BLACK);
	        cell2.setPaddingLeft(10);
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table.addCell(cell1);
	        table.addCell(cell2);
	 
	        for (int i = 0; i < gabarito.size(); i++) {
		        PdfPCell cellQ = new PdfPCell(new Paragraph(gabarito.get(i).getQuestao(), new Font(FontFamily.TIMES_ROMAN, 14, 1)));
		        cellQ.setBorderColor(BaseColor.BLACK);
		        cellQ.setPaddingLeft(10);
		        cellQ.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cellQ.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        if (i % 2 != 0) {
		        	cellQ.setBackgroundColor(new CMYKColor(0, 0, 0, 37));
				}
		 
		        PdfPCell cellA = new PdfPCell(new Paragraph(gabarito.get(i).getAlternativa(), new Font(FontFamily.TIMES_ROMAN, 14, 1)));
		        cellA.setBorderColor(BaseColor.BLACK);
		        cellA.setPaddingLeft(10);
		        cellA.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cellA.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        if (i % 2 != 0) {
		        	cellA.setBackgroundColor(new CMYKColor(0, 0, 0, 37));
				}
		        
		        table.addCell(cellQ);
		        table.addCell(cellA);
			}

	        
	        document.add(table);
		} catch (Exception e) {
			
		}
	}
	private static void fim(Document document) {
	    Paragraph chapterTitle = new Paragraph("Gabarito:", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(14, 12, 0, 84)));
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
		questoes = Controladora.consultarQuestoesC(nomeConteudo);		
		
		desordenarQuestoes();
		
		for (int i = 0; i < questoes.size(); i++) {		    
		    orderedList.add(new ListItem(Controladora.consultarQuestao(questoes.get(i).getCod()).getEnunciado() + getAlternativas(questoes.get(i).getCod(), i) + "\n"));
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
		gabarito.clear();
		
		for (int i = 0; i < integers.size(); i++) {
			questoesAUX.add(questoes.get(integers.get(i)));
			gabarito.add(new Gabarito(String.valueOf(i + 1)));
		}
		questoes.clear();
		questoes = questoesAUX;
	}
	
	private static String getAlternativas(String idQ, int p) {			
		ArrayList<Alternativa> alternativas = Controladora.getAlternativas(idQ);
		alternativas = desordenarAlternativas(alternativas);
		String alternativasS = "\n\n";
		
		for (int i = 0; i < alternativas.size(); i++) {
			if (letraMaiuscula) {
				alternativasS += Alphabet.getLetra(i) + alternativasFormatacao + alternativas.get(i).getResposta() + "\n";
			} else {
				alternativasS += Alphabet.getLetra(i).toLowerCase() + alternativasFormatacao + alternativas.get(i).getResposta() + "\n";
			}
			
			if (alternativas.get(i).getClassificacao().equals("0")) {
				gabarito.get(p).setAlternativa(String.valueOf(Alphabet.getLetra(i)));
			}
			
		}
		return alternativasS;
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