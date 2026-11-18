
package br.com.acessoja.bo;

import br.com.acessoja.dao.ValidacaoDAO;
import br.com.acessoja.dao.RegistroDAO;
import br.com.acessoja.entities.Validacao;
import br.com.acessoja.entities.Registro;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ValidacaoBo {

    ValidacaoDAO validacaoDAO;

    // Selecionar
    public List<Validacao> selecionarBo()
            throws SQLException, ClassNotFoundException {

        validacaoDAO = new ValidacaoDAO();
        return validacaoDAO.selecionar();
    }

    // Buscar por ID
    public Validacao buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        validacaoDAO = new ValidacaoDAO();
        return validacaoDAO.buscarPorId(id);
    }

    // Buscar por registro
    public List<Validacao> buscarPorRegistroBo(int idRegistro)
            throws SQLException, ClassNotFoundException {

        validarId(idRegistro);

        validacaoDAO = new ValidacaoDAO();
        return validacaoDAO.buscarPorRegistro(idRegistro);
    }

    // Inserir
    public void inserirBo(Validacao validacao)
            throws SQLException, ClassNotFoundException {

        if (validacao == null) {
            throw new IllegalArgumentException(
                    "Os dados da validação são obrigatórios"
            );
        }

        validarId(validacao.getIdValidacao());
        validarId(validacao.getIdUsuario());
        validarId(validacao.getIdRegistro());
        validarCampos(validacao);

        // Consultar o registro que será validado
        RegistroDAO registroDAO = new RegistroDAO();

        Registro registro = registroDAO.buscarPorId(
                validacao.getIdRegistro()
        );

        // Impedir que o autor valide o próprio registro
        if (registro.getIdUsuarioAutor().equals(
                validacao.getIdUsuario())) {

            throw new IllegalArgumentException(
                    "O usuário não pode validar o próprio registro"
            );
        }

        // Validar somente registros aprovados
        if (!"APROVADO".equals(registro.getStatusModeracao())) {

            throw new IllegalArgumentException(
                    "Somente registros aprovados podem ser validados"
            );
        }

        validacao.setDataValidacao(LocalDate.now());

        validacaoDAO = new ValidacaoDAO();
        validacaoDAO.inserir(validacao);
    }

    // Atualizar
    public void atualizarBo(Validacao validacao)
            throws SQLException, ClassNotFoundException {

        if (validacao == null) {
            throw new IllegalArgumentException(
                    "Os dados da validação são obrigatórios"
            );
        }

        validarId(validacao.getIdValidacao());
        validarCampos(validacao);

        validacaoDAO = new ValidacaoDAO();
        validacaoDAO.atualizar(validacao);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        validarId(id);

        validacaoDAO = new ValidacaoDAO();
        validacaoDAO.deletar(id);
    }

    // Regras de negócio
    private void validarCampos(Validacao validacao) {

        String tipo = validacao.getTipoValidacao();

        if (tipo == null ||
                (!tipo.equals("CONFIRMA") &&
                        !tipo.equals("CONTESTA"))) {

            throw new IllegalArgumentException(
                    "Use CONFIRMA ou CONTESTA"
            );
        }

        if (validacao.getComentario() != null &&
                validacao.getComentario().length() > 500) {

            throw new IllegalArgumentException(
                    "O comentário deve ter até 500 caracteres"
            );
        }

        // Contestações precisam de justificativa
        if (tipo.equals("CONTESTA") &&
                (validacao.getComentario() == null ||
                        validacao.getComentario().isBlank())) {

            throw new IllegalArgumentException(
                    "Informe o motivo da contestação"
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
