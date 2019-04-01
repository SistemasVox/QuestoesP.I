package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Controller.FabricaConexao;

public class QuestoesDAO {

	public static String consultarTotalQ() {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		String temp = "";
		
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT COUNT(*) FROM Questoes;");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				temp = rs.getString(1);
			}
			c.close();
			return temp;
		} catch (Exception e) {
			//vwHomeLoto.mensagem(e.getClass().getName() + ": " + e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO: Na Classe MegaDAO, no método consultarTotalQ();\n\n" + e.getMessage());
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void salvarQ(Questoes questao) {
		Statement stmt = null;
		StringBuilder sql = null;
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			sql = new StringBuilder();
			sql.append("INSERT INTO Questoes");
			sql.append(" (cod, enunciado, dificuldade, referencia) ");
			sql.append("VALUES ('" + questao.getCod() + "', ");
			sql.append("'" + questao.getEnunciado() + "',");
			sql.append("'" + questao.getDificuldade() + "',");
			sql.append("'" + questao.getReferencia() + "');");
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

	public static Questoes consultarQuestao(String id) {
		Statement stmt = null;
		ResultSet rs = null;
		Questoes questao = null;
		StringBuilder sql = new StringBuilder();
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT * FROM Questoes WHERE cod = '" + id + "' ; ");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				questao = new Questoes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			c.close();

			return questao;
		} catch (Exception e) {
			System.out.println(sql.toString());
			JOptionPane.showMessageDialog(null, "ERRO: Na Classe QuestoesDAO, no método consultarQuestao(String id):\n" + e.getMessage());
		}
		return null;
	}
}
