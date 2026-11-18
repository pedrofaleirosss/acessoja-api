
package br.com.acessoja.bo;

import br.com.acessoja.dao.EstabelecimentoRecursoDAO;
import br.com.acessoja.entities.EstabelecimentoRecurso;

import java.sql.SQLException;
import java.util.List;

public class EstabelecimentoRecursoBo {

    EstabelecimentoRecursoDAO associacaoDAO;

    // Selecionar
    public List<EstabelecimentoRecurso> selecionarBo()
            throws SQLException, ClassNotFoundException {

        associacaoDAO = new EstabelecimentoRecursoDAO();
        return associacaoDAO.selecionar();
    }

    // Buscar por IDs
    public EstabelecimentoRecurso buscarPorIdsBo(
            int idEstabelecimento, int idRecurso)
            throws SQLException, ClassNotFoundException {

        validarId(idEstabelecimento);
        validarId(idRecurso);

        associacaoDAO = new EstabelecimentoRecursoDAO();

        return associacaoDAO.buscarPorIds(
                idEstabelecimento, idRecurso
        );
    }

    // Buscar por estabelecimento
    public List<EstabelecimentoRecurso> buscarPorEstabelecimentoBo(
            int idEstabelecimento)
            throws SQLException, ClassNotFoundException {

        validarId(idEstabelecimento);

        associacaoDAO = new EstabelecimentoRecursoDAO();

        return associacaoDAO.buscarPorEstabelecimento(
                idEstabelecimento
        );
    }

    // Inserir
    public void inserirBo(EstabelecimentoRecurso associacao)
            throws SQLException, ClassNotFoundException {

        if (associacao == null) {
            throw new IllegalArgumentException(
                    "Informe os dados da associação"
            );
        }

        if (associacao.getIdEstabelecimento() == null ||
                associacao.getIdRecurso() == null) {

            throw new IllegalArgumentException(
                    "Informe o estabelecimento e o recurso"
            );
        }

        validarId(associacao.getIdEstabelecimento());
        validarId(associacao.getIdRecurso());

        associacaoDAO = new EstabelecimentoRecursoDAO();
        associacaoDAO.inserir(associacao);
    }

    // Atualizar
    public void atualizarBo(int idEstabelecimento,
                            int idRecursoAtual,
                            int idNovoRecurso)
            throws SQLException, ClassNotFoundException {

        validarId(idEstabelecimento);
        validarId(idRecursoAtual);
        validarId(idNovoRecurso);

        associacaoDAO = new EstabelecimentoRecursoDAO();

        associacaoDAO.atualizar(
                idEstabelecimento,
                idRecursoAtual,
                idNovoRecurso
        );
    }

    // Deletar
    public void deletarBo(int idEstabelecimento, int idRecurso)
            throws SQLException, ClassNotFoundException {

        validarId(idEstabelecimento);
        validarId(idRecurso);

        associacaoDAO = new EstabelecimentoRecursoDAO();

        associacaoDAO.deletar(idEstabelecimento, idRecurso);
    }

    // Regra de negócio
    private void validarId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Os IDs devem ser maiores que zero"
            );
        }
    }
}
