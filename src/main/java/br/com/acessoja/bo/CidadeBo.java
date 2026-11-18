
package br.com.acessoja.bo;

import br.com.acessoja.dao.CidadeDAO;
import br.com.acessoja.entities.Cidade;

import java.sql.SQLException;
import java.util.List;

public class CidadeBo {

    CidadeDAO cidadeDAO;

    // Selecionar
    public List<Cidade> selecionarBo()
            throws SQLException, ClassNotFoundException {

        cidadeDAO = new CidadeDAO();
        return cidadeDAO.selecionar();
    }

    // Buscar por ID
    public Cidade buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        cidadeDAO = new CidadeDAO();
        return cidadeDAO.buscarPorId(id);
    }

    // Inserir
    public void inserirBo(Cidade cidade)
            throws SQLException, ClassNotFoundException {

        validarCidade(cidade);

        cidadeDAO = new CidadeDAO();
        cidadeDAO.inserir(cidade);
    }

    // Atualizar
    public void atualizarBo(Cidade cidade)
            throws SQLException, ClassNotFoundException {

        validarCidade(cidade);

        cidadeDAO = new CidadeDAO();
        cidadeDAO.atualizar(cidade);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        cidadeDAO = new CidadeDAO();
        cidadeDAO.deletar(id);
    }

    // Regra de negócio
    private void validarCidade(Cidade cidade) {

        if (cidade.getIdCidade() == null || cidade.getIdCidade() <= 0) {
            throw new IllegalArgumentException(
                    "O ID da cidade deve ser maior que zero"
            );
        }

        if (cidade.getNome() == null ||
                cidade.getNome().isBlank()) {

            throw new IllegalArgumentException(
                    "O nome da cidade é obrigatório"
            );
        }

        if (cidade.getUf() == null ||
                cidade.getUf().length() != 2) {

            throw new IllegalArgumentException(
                    "A UF deve conter duas letras"
            );
        }
    }
}
