package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Controller.FabricaConexao;

public class AlternativaDAO {
	public static String consultarTotalA() {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		String temp = "";
		
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT COUNT(*) FROM Alternativa;");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				temp = rs.getString(1);
			}
			c.close();
			return temp;
		} catch (Exception e) {
			//vwHomeLoto.mensagem(e.getClass().getName() + ": " + e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO: Na Classe MegaDAO, no método consultarTotalA();\n\n" + e.getMessage());
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void salvarA(Alternativa alternativa) {
		Statement stmt = null;
		StringBuilder sql = null;
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			sql = new StringBuilder();
			sql.append("INSERT INTO Alternativa");
			sql.append(" (cod, cod_q, classificacao, resposta) ");
			sql.append("VALUES ('" + alternativa.getCod() + "', ");
			sql.append("'" + alternativa.getCod_q() + "',");
			sql.append("'" + alternativa.getClassificacao() + "',");
			sql.append("'" + alternativa.getResposta() + "');");
			stmt.executeUpdate(sql.toString());
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Falha em processar mensagem " + e.getClass().getName() + ": " + e.getMessage() + sql.toString());
			System.out.println(e.getMessage());
			System.out.println(sql.toString());
		}
		
	}
}
