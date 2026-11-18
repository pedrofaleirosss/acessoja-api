
package br.com.acessoja.main;

import br.com.acessoja.dao.CidadeDAO;
import br.com.acessoja.entities.Cidade;

import java.sql.SQLException;
import java.util.List;

public class TesteSelecionarCidade {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        CidadeDAO cidadeDAO = new CidadeDAO();

        List<Cidade> cidades = cidadeDAO.selecionar();

        for (Cidade cidade : cidades) {

            System.out.println(
                    "ID: " + cidade.getIdCidade() +
                            " | Nome: " + cidade.getNome() +
                            " | UF: " + cidade.getUf()
            );
        }
    }
}