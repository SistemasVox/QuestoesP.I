package Test;

import java.util.regex.Pattern;

public class ApString {

	public static void main(String[] args) {
		String s = "Oi como vai vc.";
		System.out.println(".".matches(Pattern.quote(".")));
	}

}
