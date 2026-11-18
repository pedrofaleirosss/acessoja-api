
package br.com.acessoja.bo;

import br.com.acessoja.dao.EstabelecimentoDAO;
import br.com.acessoja.entities.Estabelecimento;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EstabelecimentoBo {

    EstabelecimentoDAO estabelecimentoDAO;

    // SELECT - Listar todos
    public List<Estabelecimento> selecionarBo()
            throws SQLException, ClassNotFoundException {

        estabelecimentoDAO = new EstabelecimentoDAO();
        return estabelecimentoDAO.selecionar();
    }

    // SELECT - Buscar por ID
    public Estabelecimento buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        estabelecimentoDAO = new EstabelecimentoDAO();
        return estabelecimentoDAO.buscarPorId(id);
    }

    // INSERT
    public void inserirBo(Estabelecimento estabelecimento)
            throws SQLException, ClassNotFoundException {

        validarCadastro(estabelecimento);
        validarCampos(estabelecimento);

        estabelecimento.setTipoRegistro("ESTABELECIMENTO");
        estabelecimento.setDataCadastro(LocalDate.now());
        estabelecimento.setStatusModeracao("PENDENTE");
        estabelecimento.setIdAdminModerador(null);
        estabelecimento.setDataModeracao(null);

        estabelecimentoDAO = new EstabelecimentoDAO();
        estabelecimentoDAO.inserir(estabelecimento);
    }

    // UPDATE
    public void atualizarBo(Estabelecimento estabelecimento)
            throws SQLException, ClassNotFoundException {

        if (estabelecimento == null ||
                estabelecimento.getIdRegistro() == null ||
                estabelecimento.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um ID de estabelecimento válido"
            );
        }

        validarCampos(estabelecimento);

        estabelecimentoDAO = new EstabelecimentoDAO();
        estabelecimentoDAO.atualizar(estabelecimento);
    }

    // DELETE
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        estabelecimentoDAO = new EstabelecimentoDAO();
        estabelecimentoDAO.deletar(id);
    }

    // Validação dos dados do cadastro
    private void validarCadastro(Estabelecimento estabelecimento) {

        if (estabelecimento == null) {
            throw new IllegalArgumentException(
                    "Os dados do estabelecimento são obrigatórios"
            );
        }

        if (estabelecimento.getIdRegistro() == null ||
                estabelecimento.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "O ID deve ser maior que zero"
            );
        }

        if (estabelecimento.getIdUsuarioAutor() == null ||
                estabelecimento.getIdUsuarioAutor() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um usuário autor válido"
            );
        }
    }

    // Validação dos campos gerais e específicos
    private void validarCampos(Estabelecimento estabelecimento) {

        if (estabelecimento == null) {
            throw new IllegalArgumentException(
                    "Os dados do estabelecimento são obrigatórios"
            );
        }

        if (estabelecimento.getIdBairro() == null ||
                estabelecimento.getIdBairro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um bairro válido"
            );
        }

        if (estabelecimento.getTitulo() == null ||
                estabelecimento.getTitulo().isBlank() ||
                estabelecimento.getTitulo().length() > 150) {

            throw new IllegalArgumentException(
                    "Informe um título válido"
            );
        }

        if (estabelecimento.getDescricao() == null ||
                estabelecimento.getDescricao().isBlank() ||
                estabelecimento.getDescricao().length() > 500) {

            throw new IllegalArgumentException(
                    "Informe uma descrição válida"
            );
        }

        if (estabelecimento.getEndereco() == null ||
                estabelecimento.getEndereco().isBlank() ||
                estabelecimento.getEndereco().length() > 200) {

            throw new IllegalArgumentException(
                    "Informe um endereço válido"
            );
        }

        if (estabelecimento.getCategoria() == null ||
                estabelecimento.getCategoria().isBlank() ||
                estabelecimento.getCategoria().length() > 60) {

            throw new IllegalArgumentException(
                    "Informe uma categoria válida"
            );
        }
    }
}
