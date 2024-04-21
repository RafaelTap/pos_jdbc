package edu.rafael.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import edu.rafael.entidade.Contato;

public class DaoContatos {

	// objetos de acesso a dados
	private Connection cn;
	private PreparedStatement ps;
	private ResultSet rs;

	// url de conexão com schema
	private String url = "jdbc:mysql://localhost:3306/agenda";

	private void abrirConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// drivermanager para abrir conexão com o banco de dados
			cn = DriverManager.getConnection(url, "root", "dev@123");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
	}

	public void fecharConexao() throws Exception {
		cn.close();
	}

	public void incluir(Contato contato) throws Exception {
		try {
			abrirConexao();
			ps = cn.prepareStatement("INSERT INTO CONTATOS (NOME, TELEFONE, EMAIL, DATA) VALUES (?,?,?,?)");
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getTelefone());
			ps.setString(3, contato.getEmail());
			ps.setDate(4, new java.sql.Date(contato.getData().getTime()));

			ps.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			fecharConexao();

		}
	}

	public void remover(int id) throws Exception {
		try {
			abrirConexao();
			ps = cn.prepareStatement("DELETE FROM CONTATOS WHERE ID=?");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			fecharConexao();

		}
	}

	public Contato buscar(int id) throws Exception {
		Contato contato = null;
		try {
			abrirConexao();
			ps = cn.prepareStatement("SELECT * FROM CONTATOS WHERE ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				contato = new Contato();
				contato.setId(id);
				contato.setNome(rs.getString("NOME"));
				contato.setEmail(rs.getString("EMAIL"));
				contato.setTelefone(rs.getString("TELEFONE"));
				contato.setData(rs.getDate("DATA"));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			fecharConexao();
		}
		return contato;
	}

	public void alterar(Contato contato) throws Exception {
		try {
			abrirConexao();
			ps = cn.prepareStatement("UPDATE CONTATOS SET NOME=?, EMAIL=?, TELEFONE=?, DATA=? WHERE ID=?");

			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getTelefone());
			ps.setDate(4, new java.sql.Date(contato.getData().getTime()));
			ps.setInt(5, contato.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		} finally {
			fecharConexao();

		}
	}

	public List<Contato> lista() throws Exception {
		List<Contato> contatos = new ArrayList<>();

		try {
			abrirConexao();
			ps = cn.prepareStatement("SELCT * FROM CONTATOS");
			rs = ps.executeQuery();

			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getInt("ID"));
				contato.setNome(rs.getString("NOME"));
				contato.setTelefone(rs.getString("TELEFONE"));
				contato.setEmail(rs.getString("EMAIL"));
				contato.setData(rs.getDate("DATA"));

				contatos.add(contato);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			fecharConexao();

		}
		return contatos;
	}
	
	
}
