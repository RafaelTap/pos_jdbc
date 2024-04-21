package edu.rafael.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class InserirProcedure {

	public static void main(String[] args) {

		Connection cn;
		PreparedStatement ps;

		String conexao = "jdbc:mysql://localhost:3306/escola";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(conexao, "root", "dev@123");

			// obter os dados de entrada do usuário.
			String nome = JOptionPane.showInputDialog("Nome");
			String curso = JOptionPane.showInputDialog("curso");
			int rm = Integer.parseInt(JOptionPane.showInputDialog("Matrícula"));

			/*
			 * interface para utilizarmos a procedure de inserção de dados criada no
			 * workbench
			 */
			CallableStatement cstm = cn.prepareCall("{call p_incluir_aluno(?,?,?)}");
			cstm.setString(1, nome);
			cstm.setString(2, curso);
			cstm.setInt(3, rm);

			cstm.execute();

			JOptionPane.showMessageDialog(null, "aluno inserido.");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

	}

}
