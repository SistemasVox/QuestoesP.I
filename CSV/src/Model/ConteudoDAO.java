package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.Controladora;
import Controller.FabricaConexao;

public class ConteudoDAO {

	public static ArrayList<Conteudo> consultarConteudos(String nomeDisc) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Conteudo> conteudos = new ArrayList<Conteudo>();
		StringBuilder sql = new StringBuilder();
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append(
					"SELECT * FROM Conteudo c, Disciplina_Conteudo dc where dc.cod_disciplina = (SELECT cod_disciplina FROM Disciplina WHERE nome_disciplina = '"
							+ nomeDisc + "') and c.cod_conteudo = dc.cod_conteudo;");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				conteudos.add(new Conteudo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(3)));
			}
			c.close();

			return conteudos;
		} catch (Exception e) {
			System.out.println(sql.toString());
			JOptionPane.showMessageDialog(null,
					"ERRO: Na Classe QuestoesDAO, no método consultarConteudos(String nomeDisc):\n" + e.getMessage());
		}
		return null;
	}

	public static ArrayList<Conteudo> getConteudos(String nomeConte) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Conteudo> conteudos = new ArrayList<Conteudo>();
		StringBuilder sql = new StringBuilder();
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT * FROM Conteudo WHERE nome_conteudo like '%" + nomeConte + "%';");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				conteudos.add(new Conteudo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(3)));
			}
			c.close();

			return conteudos;
		} catch (Exception e) {
			System.out.println(sql.toString());
			JOptionPane.showMessageDialog(null,
					"ERRO: Na Classe QuestoesDAO, no método consultarConteudos(String nomeDisc):\n" + e.getMessage());
		}
		return null;
	}

	public static int consultarConteudoExiste(String nome) {
		Statement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		String temp = "";

		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT COUNT(*) FROM Conteudo WHERE nome_conteudo = '" + nome + "';");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				temp = rs.getString(1);
			}
			c.close();
			return Integer.parseInt(temp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"ERRO: Na Classe QuestoesDAO, no método consultarConteudoExiste();\n\n" + e.getMessage());
			System.out.println(e.getMessage());
		}
		return 1;
	}

	public static void salvarNovo(String nome, String desc, String cod, String serie) {
		Statement stmt = null;
		StringBuilder sql = null;
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			sql = new StringBuilder();
			sql.append("INSERT INTO Conteudo");
			sql.append(" (nome_conteudo, descricao_conteudo) ");
			sql.append("VALUES ('" + nome + "', ");
			sql.append("'" + desc + "');");
			stmt.executeUpdate(sql.toString());
			
			c.commit();	
			c.setAutoCommit(false);
			sql.setLength(0);//Zerando
			
			sql.append("INSERT INTO Disciplina_Conteudo");
			sql.append(" (serie, cod_conteudo, cod_disciplina) ");
			sql.append("VALUES ('" + serie + "', ");
			sql.append("'" + Controladora.getConteudos(nome).get(0).getCod() + "',");			
			sql.append("'" + cod + "');");
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

	public static void excluirConteudo(String cod) {
		Statement stmt = null;
		StringBuilder sql = null;
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			sql = new StringBuilder();
			sql.append("DELETE FROM Disciplina_Conteudo WHERE cod_conteudo = '"+cod+"';");
			stmt.executeUpdate(sql.toString());
			
			c.commit();	
			c.setAutoCommit(false);
			sql.setLength(0);//Zerando
			
			sql.append("DELETE FROM Conteudo WHERE cod_conteudo = '"+cod+"';");
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
