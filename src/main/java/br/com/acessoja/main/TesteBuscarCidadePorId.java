
package br.com.acessoja.main;

import br.com.acessoja.dao.CidadeDAO;
import br.com.acessoja.entities.Cidade;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class TesteBuscarCidadePorId {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        CidadeDAO cidadeDAO = new CidadeDAO();

        int id = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Informe o ID da cidade"
                )
        );

        try {
            Cidade cidade = cidadeDAO.buscarPorId(id);

            System.out.println("Cidade encontrada:");
            System.out.println("ID: " + cidade.getIdCidade());
            System.out.println("Nome: " + cidade.getNome());
            System.out.println("UF: " + cidade.getUf());

        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(
                    null, "Cidade não encontrada!"
            );
        }
    }
}
