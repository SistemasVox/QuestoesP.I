package Test;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class JavaOpenFile {

    public static void main(String[] args) throws IOException {
        //text file, should be opening in default text editor
        File file = new File("Questionários/Questionário_Temp.pdf");
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        //https://www.journaldev.com/864/java-open-file
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
        
        //let's try to open PDF file
        file = new File("/Users/pankaj/java.pdf");
        if(file.exists()) desktop.open(file);
    }

}