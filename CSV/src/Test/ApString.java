package Test;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ApString {

	public static void main(String[] args) {
		System.out.println(JOptionPane.showInputDialog("Oi").subSequence(0, 2).equals("C)"));
	}

}
