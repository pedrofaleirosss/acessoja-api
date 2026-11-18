
package br.com.acessoja.bo;

import br.com.acessoja.dao.RegistroDAO;
import br.com.acessoja.entities.Registro;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RegistroBo {

    RegistroDAO registroDAO;

    // Selecionar
    public List<Registro> selecionarBo()
            throws SQLException, ClassNotFoundException {

        registroDAO = new RegistroDAO();
        return registroDAO.selecionar();
    }

    // Buscar por ID
    public Registro buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        registroDAO = new RegistroDAO();
        return registroDAO.buscarPorId(id);
    }

    // Buscar por tipo
    public List<Registro> buscarPorTipoBo(String tipo)
            throws SQLException, ClassNotFoundException {

        validarTipo(tipo);

        registroDAO = new RegistroDAO();
        return registroDAO.buscarPorTipo(tipo);
    }

    // Inserir
    public void inserirBo(Registro registro)
            throws SQLException, ClassNotFoundException {

        validarCadastro(registro);
        validarCampos(registro);

        registro.setDataCadastro(LocalDate.now());
        registro.setStatusModeracao("PENDENTE");
        registro.setIdAdminModerador(null);
        registro.setDataModeracao(null);

        registroDAO = new RegistroDAO();
        registroDAO.inserir(registro);
    }

    // Atualizar
    public void atualizarBo(Registro registro)
            throws SQLException, ClassNotFoundException {

        if (registro == null ||
                registro.getIdRegistro() == null ||
                registro.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um ID de registro válido"
            );
        }

        validarCampos(registro);

        registroDAO = new RegistroDAO();
        registroDAO.atualizar(registro);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        registroDAO = new RegistroDAO();
        registroDAO.deletar(id);
    }

    // Validações específicas do cadastro
    private void validarCadastro(Registro registro) {

        if (registro == null) {
            throw new IllegalArgumentException(
                    "Os dados do registro são obrigatórios"
            );
        }

        if (registro.getIdRegistro() == null ||
                registro.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "O ID do registro deve ser maior que zero"
            );
        }

        if (registro.getIdUsuarioAutor() == null ||
                registro.getIdUsuarioAutor() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um usuário autor válido"
            );
        }

        validarTipo(registro.getTipoRegistro());
    }

    // Validações dos campos gerais
    private void validarCampos(Registro registro) {

        if (registro == null) {
            throw new IllegalArgumentException(
                    "Os dados do registro são obrigatórios"
            );
        }

        if (registro.getIdBairro() == null ||
                registro.getIdBairro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um bairro válido"
            );
        }

        if (registro.getTitulo() == null ||
                registro.getTitulo().isBlank() ||
                registro.getTitulo().length() > 150) {

            throw new IllegalArgumentException(
                    "Informe um título válido"
            );
        }

        if (registro.getDescricao() == null ||
                registro.getDescricao().isBlank() ||
                registro.getDescricao().length() > 500) {

            throw new IllegalArgumentException(
                    "Informe uma descrição válida"
            );
        }

        if (registro.getEndereco() == null ||
                registro.getEndereco().isBlank() ||
                registro.getEndereco().length() > 200) {

            throw new IllegalArgumentException(
                    "Informe um endereço válido"
            );
        }
    }

    // Validação da categoria
    private void validarTipo(String tipo) {

        if (tipo == null ||
                (!tipo.equals("ESTABELECIMENTO") &&
                        !tipo.equals("AREA") &&
                        !tipo.equals("BARREIRA"))) {

            throw new IllegalArgumentException(
                    "Tipo inválido. Use ESTABELECIMENTO, AREA ou BARREIRA"
            );
        }
    }
}
