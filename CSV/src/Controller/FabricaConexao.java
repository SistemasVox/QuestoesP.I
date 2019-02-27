package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class FabricaConexao {

	public static Connection conectarSQLITE() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:SQL/LITE/questoes.db");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Arquivo questoes.db não encontrado, por favor crie uma pasta: SQL/LITE/questoes.db");
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(0);
		}
		return c;
	}
	public static Connection conectarMySQL() {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/questoes", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "MySQL Off-line, verifique se o banco (questoes) existe.");
			System.exit(0);
		}
		return c;
	}
}
