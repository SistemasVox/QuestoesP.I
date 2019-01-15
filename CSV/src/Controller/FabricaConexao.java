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
					.getConnection("jdbc:sqlite:C:\\Users\\Marcelo\\Documents\\CSV\\CSV\\SQL\\LITE\\questoes.db");
		} catch (Exception e) {
			if (JOptionPane.showConfirmDialog(null, "Servidor SQLITE OFFLINE\n" + e.getMessage()) == 0) {
				System.exit(0);
			}
		}
		return c;
	}
	public static Connection conectarMySQL() {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/megasena", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			if (JOptionPane.showConfirmDialog(null, "Servidor MySQL OFFLINE") == 0) {
				System.exit(0);
			}
		}
		return c;
	}
}
