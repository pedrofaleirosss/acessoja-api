
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Barreira;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BarreiraDAO {

    // SELECT - Listar todas
    public List<Barreira> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Barreira> barreiras = new ArrayList<>();

        String sql = "SELECT r.*, b.tipo_barreira, b.status_barreira " +
                "FROM REGISTRO r " +
                "INNER JOIN BARREIRA b " +
                "ON r.id_registro = b.id_registro " +
                "ORDER BY r.id_registro";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                barreiras.add(mapearBarreira(rs));
            }
        }

        return barreiras;
    }

    // SELECT - Buscar por ID
    public Barreira buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT r.*, b.tipo_barreira, b.status_barreira " +
                "FROM REGISTRO r " +
                "INNER JOIN BARREIRA b " +
                "ON r.id_registro = b.id_registro " +
                "WHERE r.id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearBarreira(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Barreira não encontrada"
        );
    }

    // INSERT - Insere nas duas tabelas
    public void inserir(Barreira barreira)
            throws SQLException, ClassNotFoundException {

        String sqlRegistro =
                "INSERT INTO REGISTRO " +
                        "(id_registro, id_usuario_autor, id_bairro, " +
                        "tipo_registro, titulo, descricao, endereco, " +
                        "data_cadastro, status_moderacao) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlBarreira =
                "INSERT INTO BARREIRA " +
                        "(id_registro, tipo_barreira, status_barreira) " +
                        "VALUES (?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Insere primeiro em REGISTRO
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRegistro)) {

                    stmt.setInt(1, barreira.getIdRegistro());
                    stmt.setInt(2, barreira.getIdUsuarioAutor());
                    stmt.setInt(3, barreira.getIdBairro());
                    stmt.setString(4, barreira.getTipoRegistro());
                    stmt.setString(5, barreira.getTitulo());
                    stmt.setString(6, barreira.getDescricao());
                    stmt.setString(7, barreira.getEndereco());
                    stmt.setDate(8, Date.valueOf(
                            barreira.getDataCadastro()
                    ));
                    stmt.setString(9, barreira.getStatusModeracao());

                    stmt.executeUpdate();
                }

                // Insere em BARREIRA
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBarreira)) {

                    stmt.setInt(1, barreira.getIdRegistro());
                    stmt.setString(2, barreira.getTipoBarreira());
                    stmt.setString(3, barreira.getStatusBarreira());

                    stmt.executeUpdate();
                }

                conexao.commit();

            } catch (SQLException | RuntimeException e) {
                conexao.rollback();
                throw e;
            }
        }
    }

    // UPDATE - Atualiza informações gerais e tipo
    public void atualizar(Barreira barreira)
            throws SQLException, ClassNotFoundException {

        String sqlBarreira =
                "UPDATE BARREIRA SET tipo_barreira = ? " +
                        "WHERE id_registro = ?";

        String sqlRegistro =
                "UPDATE REGISTRO SET id_bairro = ?, " +
                        "titulo = ?, descricao = ?, endereco = ? " +
                        "WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Atualiza o tipo da barreira
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBarreira)) {

                    stmt.setString(1, barreira.getTipoBarreira());
                    stmt.setInt(2, barreira.getIdRegistro());

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new NoSuchElementException(
                                "Barreira não encontrada"
                        );
                    }
                }

                // Atualiza o registro geral
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRegistro)) {

                    stmt.setInt(1, barreira.getIdBairro());
                    stmt.setString(2, barreira.getTitulo());
                    stmt.setString(3, barreira.getDescricao());
                    stmt.setString(4, barreira.getEndereco());
                    stmt.setInt(5, barreira.getIdRegistro());

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

        String sqlBarreira =
                "DELETE FROM BARREIRA WHERE id_registro = ?";

        String sqlRegistro =
                "DELETE FROM REGISTRO WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Exclui primeiro da tabela BARREIRA
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBarreira)) {

                    stmt.setInt(1, id);

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new NoSuchElementException(
                                "Barreira não encontrada"
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

    // Converte ResultSet em objeto Barreira
    private Barreira mapearBarreira(ResultSet rs)
            throws SQLException {

        Barreira barreira = new Barreira();

        barreira.setIdRegistro(rs.getInt("id_registro"));
        barreira.setIdUsuarioAutor(
                rs.getInt("id_usuario_autor")
        );
        barreira.setIdBairro(rs.getInt("id_bairro"));

        int idAdmin = rs.getInt("id_admin_moderador");

        if (!rs.wasNull()) {
            barreira.setIdAdminModerador(idAdmin);
        }

        barreira.setTipoRegistro(
                rs.getString("tipo_registro")
        );
        barreira.setTitulo(rs.getString("titulo"));
        barreira.setDescricao(rs.getString("descricao"));
        barreira.setEndereco(rs.getString("endereco"));

        Date dataCadastro = rs.getDate("data_cadastro");

        if (dataCadastro != null) {
            barreira.setDataCadastro(
                    dataCadastro.toLocalDate()
            );
        }

        barreira.setStatusModeracao(
                rs.getString("status_moderacao")
        );

        Date dataModeracao = rs.getDate("data_moderacao");

        if (dataModeracao != null) {
            barreira.setDataModeracao(
                    dataModeracao.toLocalDate()
            );
        }

        barreira.setTipoBarreira(
                rs.getString("tipo_barreira")
        );
        barreira.setStatusBarreira(
                rs.getString("status_barreira")
        );

        return barreira;
    }
}
