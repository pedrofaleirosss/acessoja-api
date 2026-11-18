
package br.com.acessoja.main;

import br.com.acessoja.dao.CidadeDAO;
import br.com.acessoja.entities.Cidade;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteAtualizarCidade {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        Cidade cidade = new Cidade();
        CidadeDAO cidadeDAO = new CidadeDAO();

        cidade.setIdCidade(Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Informe o ID da cidade a ser atualizada"
                )
        ));

        cidade.setNome(
                JOptionPane.showInputDialog("Informe o novo nome")
        );

        cidade.setUf(
                JOptionPane.showInputDialog("Informe a nova UF")
        );

        cidadeDAO.atualizar(cidade);

        System.out.println("Cidade atualizada com sucesso!");
    }
}
