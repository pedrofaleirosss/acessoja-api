
package br.com.acessoja.main;

import br.com.acessoja.dao.CidadeDAO;
import br.com.acessoja.entities.Cidade;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteInserirCidade {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        Cidade cidade = new Cidade();
        CidadeDAO cidadeDAO = new CidadeDAO();

        cidade.setIdCidade(Integer.parseInt(
                JOptionPane.showInputDialog("Informe o ID da cidade")
        ));

        cidade.setNome(
                JOptionPane.showInputDialog("Informe o nome da cidade")
        );

        cidade.setUf(
                JOptionPane.showInputDialog("Informe a UF")
        );

        cidadeDAO.inserir(cidade);

        System.out.println("Cidade cadastrada com sucesso!");
    }
}
