package Test;
import java.io.FileOutputStream;
import java.io.IOException;
  
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
  
  
public class ObjetoList {
  
  public static final String RESULT = "arqPDFexemplo7.pdf";
        
  public static final Font BOLD_UNDERLINED = new 
   Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.UNDERLINE);
  public static final Font NORMAL = new Font(FontFamily.TIMES_ROMAN, 12);
  
        
  public static void main(String[] args) throws DocumentException, 
   IOException {
       Document document = new Document();
       PdfWriter.getInstance(document, new FileOutputStream(RESULT));
       document.open();
       List list = new List();
              
       ListItem item = new ListItem("Teste List", BOLD_UNDERLINED);
        
       ListItem listItem = new ListItem("Teste ListItem");
       item.add(listItem);
       list.add(item);
              
       document.add(list);
  
       document.close();
  }
}