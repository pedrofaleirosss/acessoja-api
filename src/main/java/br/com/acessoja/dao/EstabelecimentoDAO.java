
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Estabelecimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EstabelecimentoDAO {

    // SELECT - Listar todos
    public List<Estabelecimento> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Estabelecimento> estabelecimentos = new ArrayList<>();

        String sql = "SELECT r.*, e.categoria " +
                "FROM REGISTRO r " +
                "INNER JOIN ESTABELECIMENTO e " +
                "ON r.id_registro = e.id_registro " +
                "ORDER BY r.id_registro";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                estabelecimentos.add(mapearEstabelecimento(rs));
            }
        }

        return estabelecimentos;
    }

    // SELECT - Buscar por ID
    public Estabelecimento buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT r.*, e.categoria " +
                "FROM REGISTRO r " +
                "INNER JOIN ESTABELECIMENTO e " +
                "ON r.id_registro = e.id_registro " +
                "WHERE r.id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearEstabelecimento(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Estabelecimento não encontrado"
        );
    }

    // INSERT - Insere nas duas tabelas
    public void inserir(Estabelecimento estabelecimento)
            throws SQLException, ClassNotFoundException {

        String sqlRegistro =
                "INSERT INTO REGISTRO " +
                        "(id_registro, id_usuario_autor, id_bairro, " +
                        "tipo_registro, titulo, descricao, endereco, " +
                        "data_cadastro, status_moderacao) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlEstabelecimento =
                "INSERT INTO ESTABELECIMENTO " +
                        "(id_registro, categoria) VALUES (?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Primeiro insere o registro geral
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRegistro)) {

                    stmt.setInt(1, estabelecimento.getIdRegistro());
                    stmt.setInt(2, estabelecimento.getIdUsuarioAutor());
                    stmt.setInt(3, estabelecimento.getIdBairro());
                    stmt.setString(4, estabelecimento.getTipoRegistro());
                    stmt.setString(5, estabelecimento.getTitulo());
                    stmt.setString(6, estabelecimento.getDescricao());
                    stmt.setString(7, estabelecimento.getEndereco());
                    stmt.setDate(8, Date.valueOf(
                            estabelecimento.getDataCadastro()
                    ));
                    stmt.setString(9,
                            estabelecimento.getStatusModeracao());

                    stmt.executeUpdate();
                }

                // Depois insere o estabelecimento
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlEstabelecimento)) {

                    stmt.setInt(1, estabelecimento.getIdRegistro());
                    stmt.setString(2, estabelecimento.getCategoria());

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
    public void atualizar(Estabelecimento estabelecimento)
            throws SQLException, ClassNotFoundException {

        String sqlEstabelecimento =
                "UPDATE ESTABELECIMENTO SET categoria = ? " +
                        "WHERE id_registro = ?";

        String sqlRegistro =
                "UPDATE REGISTRO SET id_bairro = ?, " +
                        "titulo = ?, descricao = ?, endereco = ? " +
                        "WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Atualiza a categoria
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlEstabelecimento)) {

                    stmt.setString(1, estabelecimento.getCategoria());
                    stmt.setInt(2, estabelecimento.getIdRegistro());

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new NoSuchElementException(
                                "Estabelecimento não encontrado"
                        );
                    }
                }

                // Atualiza as informações gerais
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRegistro)) {

                    stmt.setInt(1, estabelecimento.getIdBairro());
                    stmt.setString(2, estabelecimento.getTitulo());
                    stmt.setString(3, estabelecimento.getDescricao());
                    stmt.setString(4, estabelecimento.getEndereco());
                    stmt.setInt(5, estabelecimento.getIdRegistro());

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

        String sqlEstabelecimento =
                "DELETE FROM ESTABELECIMENTO WHERE id_registro = ?";

        String sqlRegistro =
                "DELETE FROM REGISTRO WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Exclui primeiro da tabela filha
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlEstabelecimento)) {

                    stmt.setInt(1, id);

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new NoSuchElementException(
                                "Estabelecimento não encontrado"
                        );
                    }
                }

                // Depois exclui o registro geral
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

    // Converte o resultado do JOIN em Estabelecimento
    private Estabelecimento mapearEstabelecimento(ResultSet rs)
            throws SQLException {

        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setIdRegistro(
                rs.getInt("id_registro")
        );

        estabelecimento.setIdUsuarioAutor(
                rs.getInt("id_usuario_autor")
        );

        estabelecimento.setIdBairro(
                rs.getInt("id_bairro")
        );

        int idAdmin = rs.getInt("id_admin_moderador");

        if (!rs.wasNull()) {
            estabelecimento.setIdAdminModerador(idAdmin);
        }

        estabelecimento.setTipoRegistro(
                rs.getString("tipo_registro")
        );

        estabelecimento.setTitulo(
                rs.getString("titulo")
        );

        estabelecimento.setDescricao(
                rs.getString("descricao")
        );

        estabelecimento.setEndereco(
                rs.getString("endereco")
        );

        Date dataCadastro = rs.getDate("data_cadastro");

        if (dataCadastro != null) {
            estabelecimento.setDataCadastro(
                    dataCadastro.toLocalDate()
            );
        }

        estabelecimento.setStatusModeracao(
                rs.getString("status_moderacao")
        );

        Date dataModeracao = rs.getDate("data_moderacao");

        if (dataModeracao != null) {
            estabelecimento.setDataModeracao(
                    dataModeracao.toLocalDate()
            );
        }

        estabelecimento.setCategoria(
                rs.getString("categoria")
        );

        return estabelecimento;
    }
}
