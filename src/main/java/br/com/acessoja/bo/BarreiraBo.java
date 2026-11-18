
package br.com.acessoja.bo;

import br.com.acessoja.dao.BarreiraDAO;
import br.com.acessoja.entities.Barreira;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BarreiraBo {

    BarreiraDAO barreiraDAO;

    // Selecionar
    public List<Barreira> selecionarBo()
            throws SQLException, ClassNotFoundException {

        barreiraDAO = new BarreiraDAO();
        return barreiraDAO.selecionar();
    }

    // Buscar por ID
    public Barreira buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        barreiraDAO = new BarreiraDAO();
        return barreiraDAO.buscarPorId(id);
    }

    // Inserir
    public void inserirBo(Barreira barreira)
            throws SQLException, ClassNotFoundException {

        validarCadastro(barreira);
        validarCampos(barreira);

        barreira.setTipoRegistro("BARREIRA");
        barreira.setDataCadastro(LocalDate.now());
        barreira.setStatusModeracao("PENDENTE");
        barreira.setIdAdminModerador(null);
        barreira.setDataModeracao(null);
        barreira.setStatusBarreira("ATIVA");

        barreiraDAO = new BarreiraDAO();
        barreiraDAO.inserir(barreira);
    }

    // Atualizar
    public void atualizarBo(Barreira barreira)
            throws SQLException, ClassNotFoundException {

        if (barreira == null ||
                barreira.getIdRegistro() == null ||
                barreira.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um ID de barreira válido"
            );
        }

        validarCampos(barreira);

        barreiraDAO = new BarreiraDAO();
        barreiraDAO.atualizar(barreira);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        barreiraDAO = new BarreiraDAO();
        barreiraDAO.deletar(id);
    }

    // Validações do cadastro
    private void validarCadastro(Barreira barreira) {

        if (barreira == null ||
                barreira.getIdRegistro() == null ||
                barreira.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um ID de registro válido"
            );
        }

        if (barreira.getIdUsuarioAutor() == null ||
                barreira.getIdUsuarioAutor() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um usuário autor válido"
            );
        }
    }

    // Validações dos campos
    private void validarCampos(Barreira barreira) {

        if (barreira.getIdBairro() == null ||
                barreira.getIdBairro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um bairro válido"
            );
        }

        if (barreira.getTitulo() == null ||
                barreira.getTitulo().isBlank() ||
                barreira.getTitulo().length() > 150) {

            throw new IllegalArgumentException(
                    "Informe um título válido"
            );
        }

        if (barreira.getDescricao() == null ||
                barreira.getDescricao().isBlank() ||
                barreira.getDescricao().length() > 500) {

            throw new IllegalArgumentException(
                    "Informe uma descrição válida"
            );
        }

        if (barreira.getEndereco() == null ||
                barreira.getEndereco().isBlank() ||
                barreira.getEndereco().length() > 200) {

            throw new IllegalArgumentException(
                    "Informe um endereço válido"
            );
        }

        if (barreira.getTipoBarreira() == null ||
                barreira.getTipoBarreira().isBlank() ||
                barreira.getTipoBarreira().length() > 60) {

            throw new IllegalArgumentException(
                    "Informe um tipo de barreira válido"
            );
        }
    }
}
