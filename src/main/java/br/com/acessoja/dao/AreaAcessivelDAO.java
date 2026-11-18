
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.AreaAcessivel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AreaAcessivelDAO {

    // SELECT - Listar todas
    public List<AreaAcessivel> selecionar()
            throws SQLException, ClassNotFoundException {

        List<AreaAcessivel> areas = new ArrayList<>();

        String sql = "SELECT r.*, a.tipo_area, a.caracteristicas " +
                "FROM REGISTRO r " +
                "INNER JOIN AREA_ACESSIVEL a " +
                "ON r.id_registro = a.id_registro " +
                "ORDER BY r.id_registro";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                areas.add(mapearArea(rs));
            }
        }

        return areas;
    }

    // SELECT - Buscar por ID
    public AreaAcessivel buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT r.*, a.tipo_area, a.caracteristicas " +
                "FROM REGISTRO r " +
                "INNER JOIN AREA_ACESSIVEL a " +
                "ON r.id_registro = a.id_registro " +
                "WHERE r.id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearArea(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Área acessível não encontrada"
        );
    }

    // INSERT - Insere nas duas tabelas
    public void inserir(AreaAcessivel area)
            throws SQLException, ClassNotFoundException {

        String sqlRegistro =
                "INSERT INTO REGISTRO " +
                        "(id_registro, id_usuario_autor, id_bairro, " +
                        "tipo_registro, titulo, descricao, endereco, " +
                        "data_cadastro, status_moderacao) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlArea =
                "INSERT INTO AREA_ACESSIVEL " +
                        "(id_registro, tipo_area, caracteristicas) " +
                        "VALUES (?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Insere primeiro o registro geral
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRegistro)) {

                    stmt.setInt(1, area.getIdRegistro());
                    stmt.setInt(2, area.getIdUsuarioAutor());
                    stmt.setInt(3, area.getIdBairro());
                    stmt.setString(4, area.getTipoRegistro());
                    stmt.setString(5, area.getTitulo());
                    stmt.setString(6, area.getDescricao());
                    stmt.setString(7, area.getEndereco());
                    stmt.setDate(8, Date.valueOf(
                            area.getDataCadastro()
                    ));
                    stmt.setString(9, area.getStatusModeracao());

                    stmt.executeUpdate();
                }

                // Insere a área acessível
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlArea)) {

                    stmt.setInt(1, area.getIdRegistro());
                    stmt.setString(2, area.getTipoArea());
                    stmt.setString(3, area.getCaracteristicas());

                    stmt.executeUpdate();
                }

                conexao.commit();

            } catch (SQLException | RuntimeException e) {
                conexao.rollback();
                throw e;
            }
        }
    }

    // UPDATE - Atualiza as duas tabelas
    public void atualizar(AreaAcessivel area)
            throws SQLException, ClassNotFoundException {

        String sqlArea =
                "UPDATE AREA_ACESSIVEL " +
                        "SET tipo_area = ?, caracteristicas = ? " +
                        "WHERE id_registro = ?";

        String sqlRegistro =
                "UPDATE REGISTRO SET id_bairro = ?, " +
                        "titulo = ?, descricao = ?, endereco = ? " +
                        "WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Atualiza as informações específicas
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlArea)) {

                    stmt.setString(1, area.getTipoArea());
                    stmt.setString(2, area.getCaracteristicas());
                    stmt.setInt(3, area.getIdRegistro());

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new NoSuchElementException(
                                "Área acessível não encontrada"
                        );
                    }
                }

                // Atualiza o registro geral
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRegistro)) {

                    stmt.setInt(1, area.getIdBairro());
                    stmt.setString(2, area.getTitulo());
                    stmt.setString(3, area.getDescricao());
                    stmt.setString(4, area.getEndereco());
                    stmt.setInt(5, area.getIdRegistro());

                    stmt.executeUpdate();
                }

                conexao.commit();

            } catch (SQLException | RuntimeException e) {
                conexao.rollback();
                throw e;
            }
        }
    }

    // DELETE - Exclui das duas tabelas
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sqlArea =
                "DELETE FROM AREA_ACESSIVEL WHERE id_registro = ?";

        String sqlRegistro =
                "DELETE FROM REGISTRO WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Exclui da tabela filha
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlArea)) {

                    stmt.setInt(1, id);

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new NoSuchElementException(
                                "Área acessível não encontrada"
                        );
                    }
                }

                // Exclui o registro geral
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRegistro)) {

                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }

                conexao.commit();

            } catch (SQLException | RuntimeException e) {
                conexao.rollback();
                throw e;
            }
        }
    }

    // Converte o ResultSet em AreaAcessivel
    private AreaAcessivel mapearArea(ResultSet rs)
            throws SQLException {

        AreaAcessivel area = new AreaAcessivel();

        area.setIdRegistro(rs.getInt("id_registro"));
        area.setIdUsuarioAutor(rs.getInt("id_usuario_autor"));
        area.setIdBairro(rs.getInt("id_bairro"));

        int idAdmin = rs.getInt("id_admin_moderador");

        if (!rs.wasNull()) {
            area.setIdAdminModerador(idAdmin);
        }

        area.setTipoRegistro(rs.getString("tipo_registro"));
        area.setTitulo(rs.getString("titulo"));
        area.setDescricao(rs.getString("descricao"));
        area.setEndereco(rs.getString("endereco"));

        Date dataCadastro = rs.getDate("data_cadastro");

        if (dataCadastro != null) {
            area.setDataCadastro(dataCadastro.toLocalDate());
        }

        area.setStatusModeracao(
                rs.getString("status_moderacao")
        );

        Date dataModeracao = rs.getDate("data_moderacao");

        if (dataModeracao != null) {
            area.setDataModeracao(dataModeracao.toLocalDate());
        }

        area.setTipoArea(rs.getString("tipo_area"));
        area.setCaracteristicas(
                rs.getString("caracteristicas")
        );

        return area;
    }
}
