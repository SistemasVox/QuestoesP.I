package Tools;

public abstract class Alphabet {
	public static String getLetra(int i) {
		if (i >= 26) {
			i = i % 26;
		}
		char s = 'a';		
		for (int j = 0; j < i; j++) {
			s++;
		}
		return "" +  Character.toUpperCase(s);
	}

}