package edu.rafael.programa;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import edu.rafael.dao.DaoContatos;
import edu.rafael.entidade.Contato;

public class Teste {

	public static void main(String[] args) {
		// inserindo registro
		Contato contato = new Contato();

		contato.setNome("rafael taparica");
		contato.setTelefone("21967813777");
		contato.setEmail("rafael.taparica@gmail.com");
		contato.setData(new Date());

		DaoContatos dao = new DaoContatos();
		try {
			dao.incluir(contato);
			JOptionPane.showMessageDialog(null, "contato incluído !");

			// listando contatos
			List<Contato> listaContatos = dao.lista();
			for (Contato c : listaContatos) {
				System.out.println("ID: " + c.getId());
				System.out.println("NOME: " + c.getNome());
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
