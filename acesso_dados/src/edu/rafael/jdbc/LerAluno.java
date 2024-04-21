package edu.rafael.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class LerAluno {

	public static void main(String[] args) {

		Connection cn;
		PreparedStatement ps;
		ResultSet rs;

		String conexao = "jdbc:mysql://localhost:3306/escola";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(conexao, "root", "dev@123");

			// string para retorna a lista de alunos cadastrados
			ps = cn.prepareStatement("SELECT * FROM ALUNOS");
			// executar a query somente para consultas
			rs = ps.executeQuery();

			/*
			 * em uma estrutura de repetição definir um método de resultset (next) quando
			 * true, retorna o que foi encontrado até retornar false
			 */
			while (rs.next()) {
				System.out.println(rs.getString("Nome"));
				System.out.println(rs.getString("Curso"));
				System.out.println(rs.getInt("matricula"));
				System.out.println("---------------------");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
