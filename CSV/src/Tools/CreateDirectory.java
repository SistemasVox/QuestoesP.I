package Tools;

import java.io.File;

public abstract class CreateDirectory {
	public static void CriarDiretorio(String name) {
		File f = new File(name);
		if (!f.exists() && !f.isDirectory()) {
			new File(name).mkdirs();
		}	
	}
	
}
