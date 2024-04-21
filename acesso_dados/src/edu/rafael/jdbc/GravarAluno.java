package edu.rafael.jdbc;

import java.sql.*;

import javax.swing.JOptionPane;

public class GravarAluno {

	public static void main(String[] args) {
		/*
		 * interface que permite definir um objeto responsável por fazer e manter uma
		 * conexão com BC.
		 */
		Connection cn;
		PreparedStatement ps;

		// string que representa as informações do BC.

		String conexao = "jdbc:mysql://localhost:3306/escola";

		try {
			// driver de acesso (qual é o banco de dados)
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(conexao, "root", "dev@123");

			// obter os dados de entrada do usuário.
			String nome = JOptionPane.showInputDialog("Nome");
			String curso = JOptionPane.showInputDialog("curso");
			int rm = Integer.parseInt(JOptionPane.showInputDialog("Matrícula"));

			// string sql para iserção de dados
			ps = cn.prepareStatement("INSERT INTO ALUNOS (NOME, CURSO, MATRICULA) VALUES (?,?,?)");

			// fornecimento de parâmetros, seguindo a ordem de inserção.
			ps.setString(1, nome);
			ps.setString(2, curso);
			ps.setInt(3, rm);

			// efetivação dos dados fornecidos
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso.");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

	}
}
