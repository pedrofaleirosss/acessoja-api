
package br.com.acessoja.bo;

import br.com.acessoja.dao.BairroDAO;
import br.com.acessoja.entities.Bairro;

import java.sql.SQLException;
import java.util.List;

public class BairroBo {

    BairroDAO bairroDAO;

    // Selecionar
    public List<Bairro> selecionarBo()
            throws SQLException, ClassNotFoundException {

        bairroDAO = new BairroDAO();
        return bairroDAO.selecionar();
    }

    // Buscar por ID
    public Bairro buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        bairroDAO = new BairroDAO();
        return bairroDAO.buscarPorId(id);
    }

    // Buscar bairros por cidade
    public List<Bairro> buscarPorCidadeBo(int idCidade)
            throws SQLException, ClassNotFoundException {

        bairroDAO = new BairroDAO();
        return bairroDAO.buscarPorCidade(idCidade);
    }

    // Inserir
    public void inserirBo(Bairro bairro)
            throws SQLException, ClassNotFoundException {

        validarBairro(bairro);

        bairroDAO = new BairroDAO();
        bairroDAO.inserir(bairro);
    }

    // Atualizar
    public void atualizarBo(Bairro bairro)
            throws SQLException, ClassNotFoundException {

        validarBairro(bairro);

        bairroDAO = new BairroDAO();
        bairroDAO.atualizar(bairro);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        bairroDAO = new BairroDAO();
        bairroDAO.deletar(id);
    }

    // Regras de negócio
    private void validarBairro(Bairro bairro) {

        if (bairro.getIdBairro() == null ||
                bairro.getIdBairro() <= 0) {

            throw new IllegalArgumentException(
                    "O ID do bairro deve ser maior que zero"
            );
        }

        if (bairro.getNome() == null ||
                bairro.getNome().isBlank()) {

            throw new IllegalArgumentException(
                    "O nome do bairro é obrigatório"
            );
        }

        if (bairro.getIdCidade() == null ||
                bairro.getIdCidade() <= 0) {

            throw new IllegalArgumentException(
                    "É necessário informar uma cidade válida"
            );
        }
    }
}
