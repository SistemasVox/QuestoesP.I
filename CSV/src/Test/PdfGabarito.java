package Test;

import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Tools.Alphabet;

public class PdfGabarito {

	public static void main(String[] args) {
		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Questionários/Questionário_" + "Teste" + ".pdf"));
			document.open();
			
			
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
			
	        for (int i = 0; i < 10; i++) {
		        PdfPCell cellQ = new PdfPCell(new Paragraph(String.valueOf(i + 1), new Font(FontFamily.TIMES_ROMAN, 14, 1)));
		        cellQ.setBorderColor(BaseColor.BLACK);
		        cellQ.setPaddingLeft(10);
		        cellQ.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cellQ.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        if (i % 2 != 0) {
		        	cellQ.setBackgroundColor(new CMYKColor(0, 0, 0, 37));
				}
		 
		        PdfPCell cellA = new PdfPCell(new Paragraph(String.valueOf(Alphabet.getLetra(i)), new Font(FontFamily.TIMES_ROMAN, 14, 1)));
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
	        
			document.close();
			writer.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}
