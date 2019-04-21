package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.FabricaConexao;

public class ConteudoDAO {

	public static ArrayList<String> consultarConteudos(String nomeDisc) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> conteudos = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT nome_conteudo FROM Conteudo c, Disciplina_Conteudo dc where dc.cod_disciplina = (SELECT cod_disciplina FROM Disciplina WHERE nome_disciplina = '"+nomeDisc+"') and c.cod_conteudo = dc.cod_conteudo;");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				conteudos.add(rs.getString(1));
			}
			c.close();

			return conteudos;
		} catch (Exception e) {
			System.out.println(sql.toString());
			JOptionPane.showMessageDialog(null, "ERRO: Na Classe QuestoesDAO, no método consultarConteudos(String nomeDisc):\n" + e.getMessage());
		}
		return null;
	}
	public static ArrayList<String> getConteudos(String nomeConte) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> conteudos = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT * FROM Conteudo WHERE nome_conteudo like '%"+ nomeConte + "%';");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				conteudos.add(rs.getString(2));
			}
			c.close();

			return conteudos;
		} catch (Exception e) {
			System.out.println(sql.toString());
			JOptionPane.showMessageDialog(null, "ERRO: Na Classe QuestoesDAO, no método consultarConteudos(String nomeDisc):\n" + e.getMessage());
		}
		return null;
	}

}
