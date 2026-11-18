
package br.com.acessoja.bo;

import br.com.acessoja.dao.AvaliacaoDAO;
import br.com.acessoja.entities.Avaliacao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AvaliacaoBo {

    AvaliacaoDAO avaliacaoDAO;

    // Selecionar
    public List<Avaliacao> selecionarBo()
            throws SQLException, ClassNotFoundException {

        avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.selecionar();
    }

    // Buscar por ID
    public Avaliacao buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.buscarPorId(id);
    }

    // Buscar avaliações de um estabelecimento
    public List<Avaliacao> buscarPorEstabelecimentoBo(
            int idEstabelecimento)
            throws SQLException, ClassNotFoundException {

        validarId(idEstabelecimento);

        avaliacaoDAO = new AvaliacaoDAO();

        return avaliacaoDAO.buscarPorEstabelecimento(
                idEstabelecimento
        );
    }

    // Inserir
    public void inserirBo(Avaliacao avaliacao)
            throws SQLException, ClassNotFoundException {

        if (avaliacao == null) {
            throw new IllegalArgumentException(
                    "Os dados da avaliação são obrigatórios"
            );
        }

        validarId(avaliacao.getIdAvaliacao());
        validarId(avaliacao.getIdUsuario());
        validarId(avaliacao.getIdEstabelecimento());
        validarCampos(avaliacao);

        avaliacao.setDataAvaliacao(LocalDate.now());

        avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.inserir(avaliacao);
    }

    // Atualizar
    public void atualizarBo(Avaliacao avaliacao)
            throws SQLException, ClassNotFoundException {

        if (avaliacao == null) {
            throw new IllegalArgumentException(
                    "Os dados da avaliação são obrigatórios"
            );
        }

        validarId(avaliacao.getIdAvaliacao());
        validarCampos(avaliacao);

        avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.atualizar(avaliacao);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        validarId(id);

        avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.deletar(id);
    }

    // Validações
    private void validarCampos(Avaliacao avaliacao) {

        if (avaliacao.getNota() == null ||
                avaliacao.getNota() < 1 ||
                avaliacao.getNota() > 5) {

            throw new IllegalArgumentException(
                    "A nota deve estar entre 1 e 5"
            );
        }

        if (avaliacao.getComentario() != null &&
                avaliacao.getComentario().length() > 500) {

            throw new IllegalArgumentException(
                    "O comentário deve ter até 500 caracteres"
            );
        }
    }

    private void validarId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID válido"
            );
        }
    }
}
