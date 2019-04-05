package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.FabricaConexao;

public class Area_ConhecimentoDAO {

	public static ArrayList<Area_Conhecimento> consultarAreas() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Area_Conhecimento> areas = new ArrayList<Area_Conhecimento>();
		StringBuilder sql = new StringBuilder();
		
		try {
			Connection c = FabricaConexao.conectarSQLITE();
			stmt = c.createStatement();
			sql.append("SELECT * FROM [Area_Conhecimento];");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				areas.add(new Area_Conhecimento(rs.getString(1), rs.getString(2)));
			}
			c.close();
			return areas;
		} catch (Exception e) {
			System.out.println(sql.toString());
			JOptionPane.showMessageDialog(null, "ERRO: Na Classe QuestoesDAO, no método consultarAreas()\n" + e.getMessage());
		}
		return null;
	}

}
