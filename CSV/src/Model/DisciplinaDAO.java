package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.FabricaConexao;

public class DisciplinaDAO {

	public static ArrayList<Disciplina> consultarDisciplinas(String nomeArea) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		StringBuilder sql = new StringBuilder();
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT * FROM Disciplina WHERE cod_area = (SELECT cod_area FROM Area_Conhecimento WHERE nome_area = '" + nomeArea + "');");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				disciplinas.add(new Disciplina(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			c.close();

			return disciplinas;
		} catch (Exception e) {
			System.out.println(sql.toString());
			JOptionPane.showMessageDialog(null, "ERRO: Na Classe QuestoesDAO, no método consultarQuestao(String id):\n" + e.getMessage());
		}
		return null;
	}

}
