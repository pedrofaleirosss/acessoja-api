
package br.com.acessoja.main;

import br.com.acessoja.dao.CidadeDAO;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteDeletarCidade {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        CidadeDAO cidadeDAO = new CidadeDAO();

        int id = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Informe o ID da cidade a ser excluída"
                )
        );

        cidadeDAO.deletar(id);

        System.out.println("Cidade excluída com sucesso!");
    }
}
