
package br.com.acessoja.bo;

import br.com.acessoja.dao.AreaAcessivelDAO;
import br.com.acessoja.entities.AreaAcessivel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AreaAcessivelBo {

    AreaAcessivelDAO areaDAO;

    // Selecionar
    public List<AreaAcessivel> selecionarBo()
            throws SQLException, ClassNotFoundException {

        areaDAO = new AreaAcessivelDAO();
        return areaDAO.selecionar();
    }

    // Buscar por ID
    public AreaAcessivel buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        areaDAO = new AreaAcessivelDAO();
        return areaDAO.buscarPorId(id);
    }

    // Inserir
    public void inserirBo(AreaAcessivel area)
            throws SQLException, ClassNotFoundException {

        validarCadastro(area);
        validarCampos(area);

        area.setTipoRegistro("AREA");
        area.setDataCadastro(LocalDate.now());
        area.setStatusModeracao("PENDENTE");
        area.setIdAdminModerador(null);
        area.setDataModeracao(null);

        areaDAO = new AreaAcessivelDAO();
        areaDAO.inserir(area);
    }

    // Atualizar
    public void atualizarBo(AreaAcessivel area)
            throws SQLException, ClassNotFoundException {

        if (area == null ||
                area.getIdRegistro() == null ||
                area.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um ID de área válido"
            );
        }

        validarCampos(area);

        areaDAO = new AreaAcessivelDAO();
        areaDAO.atualizar(area);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        areaDAO = new AreaAcessivelDAO();
        areaDAO.deletar(id);
    }

    // Validações do cadastro
    private void validarCadastro(AreaAcessivel area) {

        if (area == null ||
                area.getIdRegistro() == null ||
                area.getIdRegistro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um ID de registro válido"
            );
        }

        if (area.getIdUsuarioAutor() == null ||
                area.getIdUsuarioAutor() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um usuário autor válido"
            );
        }
    }

    // Validações dos campos
    private void validarCampos(AreaAcessivel area) {

        if (area.getIdBairro() == null ||
                area.getIdBairro() <= 0) {

            throw new IllegalArgumentException(
                    "Informe um bairro válido"
            );
        }

        if (area.getTitulo() == null ||
                area.getTitulo().isBlank() ||
                area.getTitulo().length() > 150) {

            throw new IllegalArgumentException(
                    "Informe um título válido"
            );
        }

        if (area.getDescricao() == null ||
                area.getDescricao().isBlank() ||
                area.getDescricao().length() > 500) {

            throw new IllegalArgumentException(
                    "Informe uma descrição válida"
            );
        }

        if (area.getEndereco() == null ||
                area.getEndereco().isBlank() ||
                area.getEndereco().length() > 200) {

            throw new IllegalArgumentException(
                    "Informe um endereço válido"
            );
        }

        if (area.getTipoArea() == null ||
                area.getTipoArea().isBlank() ||
                area.getTipoArea().length() > 60) {

            throw new IllegalArgumentException(
                    "Informe um tipo de área válido"
            );
        }

        if (area.getCaracteristicas() == null ||
                area.getCaracteristicas().isBlank() ||
                area.getCaracteristicas().length() > 500) {

            throw new IllegalArgumentException(
                    "Informe as características da área"
            );
        }
    }
}
