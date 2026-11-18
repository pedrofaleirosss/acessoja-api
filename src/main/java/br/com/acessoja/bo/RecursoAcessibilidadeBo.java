
package br.com.acessoja.bo;

import br.com.acessoja.dao.RecursoAcessibilidadeDAO;
import br.com.acessoja.entities.RecursoAcessibilidade;

import java.sql.SQLException;
import java.util.List;

public class RecursoAcessibilidadeBo {

    RecursoAcessibilidadeDAO recursoDAO;

    // Selecionar
    public List<RecursoAcessibilidade> selecionarBo()
            throws SQLException, ClassNotFoundException {

        recursoDAO = new RecursoAcessibilidadeDAO();
        return recursoDAO.selecionar();
    }

    // Buscar por ID
    public RecursoAcessibilidade buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        recursoDAO = new RecursoAcessibilidadeDAO();
        return recursoDAO.buscarPorId(id);
    }

    // Inserir
    public void inserirBo(RecursoAcessibilidade recurso)
            throws SQLException, ClassNotFoundException {

        validarRecurso(recurso);

        recursoDAO = new RecursoAcessibilidadeDAO();
        recursoDAO.inserir(recurso);
    }

    // Atualizar
    public void atualizarBo(RecursoAcessibilidade recurso)
            throws SQLException, ClassNotFoundException {

        validarRecurso(recurso);

        recursoDAO = new RecursoAcessibilidadeDAO();
        recursoDAO.atualizar(recurso);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID válido"
            );
        }

        recursoDAO = new RecursoAcessibilidadeDAO();
        recursoDAO.deletar(id);
    }

    // Regras de negócio
    private void validarRecurso(RecursoAcessibilidade recurso) {

        if (recurso == null) {
            throw new IllegalArgumentException(
                    "Os dados do recurso são obrigatórios"
            );
        }

        if (recurso.getIdRecurso() == null ||
                recurso.getIdRecurso() <= 0) {

            throw new IllegalArgumentException(
                    "O ID do recurso deve ser maior que zero"
            );
        }

        if (recurso.getNome() == null ||
                recurso.getNome().isBlank() ||
                recurso.getNome().length() > 100) {

            throw new IllegalArgumentException(
                    "Informe um nome válido"
            );
        }

        if (recurso.getDescricao() != null &&
                recurso.getDescricao().length() > 300) {

            throw new IllegalArgumentException(
                    "A descrição deve ter até 300 caracteres"
            );
        }
    }
}
