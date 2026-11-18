
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.RelatoResolucao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RelatoResolucaoDAO {

    // SELECT - Listar todos
    public List<RelatoResolucao> selecionar()
            throws SQLException, ClassNotFoundException {

        List<RelatoResolucao> relatos = new ArrayList<>();

        String sql = "SELECT * FROM RELATO_RESOLUCAO " +
                "ORDER BY id_relato";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                relatos.add(mapearRelato(rs));
            }
        }

        return relatos;
    }

    // SELECT - Buscar por ID
    public RelatoResolucao buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM RELATO_RESOLUCAO " +
                "WHERE id_relato = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearRelato(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Relato de resolução não encontrado"
        );
    }

    // SELECT - Buscar relatos de uma barreira
    public List<RelatoResolucao> buscarPorBarreira(int idBarreira)
            throws SQLException, ClassNotFoundException {

        List<RelatoResolucao> relatos = new ArrayList<>();

        String sql = "SELECT * FROM RELATO_RESOLUCAO " +
                "WHERE id_barreira = ? ORDER BY id_relato";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idBarreira);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    relatos.add(mapearRelato(rs));
                }
            }
        }

        return relatos;
    }

    // INSERT - Cadastrar relato e atualizar barreira
    public void inserir(RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        String sqlBarreira =
                "UPDATE BARREIRA " +
                        "SET status_barreira = 'RESOLUCAO_INFORMADA' " +
                        "WHERE id_registro = ? " +
                        "AND status_barreira = 'ATIVA'";

        String sqlRelato =
                "INSERT INTO RELATO_RESOLUCAO " +
                        "(id_relato, id_barreira, id_usuario, " +
                        "descricao, data_relato, status_analise) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                // Atualiza a situação da barreira
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBarreira)) {

                    stmt.setInt(1, relato.getIdBarreira());

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new IllegalArgumentException(
                                "A barreira não está ativa"
                        );
                    }
                }

                // Cadastra o relato
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRelato)) {

                    stmt.setInt(1, relato.getIdRelato());
                    stmt.setInt(2, relato.getIdBarreira());
                    stmt.setInt(3, relato.getIdUsuario());
                    stmt.setString(4, relato.getDescricao());
                    stmt.setDate(5, Date.valueOf(
                            relato.getDataRelato()
                    ));
                    stmt.setString(6, relato.getStatusAnalise());

                    stmt.executeUpdate();
                }

                conexao.commit();

            } catch (SQLException | RuntimeException e) {
                conexao.rollback();
                throw e;
            }
        }
    }

    // UPDATE - Alterar descrição de relato pendente
    public void atualizar(RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE RELATO_RESOLUCAO " +
                "SET descricao = ? " +
                "WHERE id_relato = ? AND status_analise = 'PENDENTE'";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, relato.getDescricao());
            stmt.setInt(2, relato.getIdRelato());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new IllegalArgumentException(
                        "Relato inexistente ou já analisado"
                );
            }
        }
    }

    // UPDATE - Análise administrativa
    public void analisar(RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        String sqlBuscar =
                "SELECT id_barreira FROM RELATO_RESOLUCAO " +
                        "WHERE id_relato = ? AND status_analise = 'PENDENTE' " +
                        "FOR UPDATE";

        String sqlRelato =
                "UPDATE RELATO_RESOLUCAO SET " +
                        "id_admin_revisor = ?, status_analise = ?, " +
                        "data_analise = ? WHERE id_relato = ?";

        String sqlBarreira =
                "UPDATE BARREIRA SET status_barreira = ? " +
                        "WHERE id_registro = ? " +
                        "AND status_barreira = 'RESOLUCAO_INFORMADA'";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                int idBarreira;

                // Localiza o relato pendente
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBuscar)) {

                    stmt.setInt(1, relato.getIdRelato());

                    try (ResultSet rs = stmt.executeQuery()) {

                        if (!rs.next()) {
                            throw new IllegalArgumentException(
                                    "Relato inexistente ou já analisado"
                            );
                        }

                        idBarreira = rs.getInt("id_barreira");
                    }
                }

                // Registra a análise
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRelato)) {

                    stmt.setInt(1, relato.getIdAdminRevisor());
                    stmt.setString(2, relato.getStatusAnalise());
                    stmt.setDate(3, Date.valueOf(
                            relato.getDataAnalise()
                    ));
                    stmt.setInt(4, relato.getIdRelato());

                    stmt.executeUpdate();
                }

                // Define o novo status da barreira
                String novoStatus;

                if ("APROVADO".equals(relato.getStatusAnalise())) {
                    novoStatus = "RESOLVIDA";
                } else {
                    novoStatus = "ATIVA";
                }

                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBarreira)) {

                    stmt.setString(1, novoStatus);
                    stmt.setInt(2, idBarreira);

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new IllegalStateException(
                                "Situação da barreira inconsistente"
                        );
                    }
                }

                conexao.commit();

            } catch (SQLException | RuntimeException e) {
                conexao.rollback();
                throw e;
            }
        }
    }

    // DELETE - Excluir relato pendente
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sqlBuscar =
                "SELECT id_barreira FROM RELATO_RESOLUCAO " +
                        "WHERE id_relato = ? AND status_analise = 'PENDENTE' " +
                        "FOR UPDATE";

        String sqlRelato =
                "DELETE FROM RELATO_RESOLUCAO WHERE id_relato = ?";

        String sqlBarreira =
                "UPDATE BARREIRA SET status_barreira = 'ATIVA' " +
                        "WHERE id_registro = ? " +
                        "AND status_barreira = 'RESOLUCAO_INFORMADA'";

        try (Connection conexao = new ConexaoFactory().conexao()) {

            conexao.setAutoCommit(false);

            try {
                int idBarreira;

                // Localiza o relato pendente
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBuscar)) {

                    stmt.setInt(1, id);

                    try (ResultSet rs = stmt.executeQuery()) {

                        if (!rs.next()) {
                            throw new IllegalArgumentException(
                                    "Somente relatos pendentes podem ser excluídos"
                            );
                        }

                        idBarreira = rs.getInt("id_barreira");
                    }
                }

                // Exclui o relato
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlRelato)) {

                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }

                // Retorna a barreira para ATIVA
                try (PreparedStatement stmt =
                             conexao.prepareStatement(sqlBarreira)) {

                    stmt.setInt(1, idBarreira);

                    int linhas = stmt.executeUpdate();

                    if (linhas == 0) {
                        throw new IllegalStateException(
                                "Situação da barreira inconsistente"
                        );
                    }
                }

                conexao.commit();

            } catch (SQLException | RuntimeException e) {
                conexao.rollback();
                throw e;
            }
        }
    }

    // Converte ResultSet em objeto
    private RelatoResolucao mapearRelato(ResultSet rs)
            throws SQLException {

        RelatoResolucao relato = new RelatoResolucao();

        relato.setIdRelato(rs.getInt("id_relato"));
        relato.setIdBarreira(rs.getInt("id_barreira"));
        relato.setIdUsuario(rs.getInt("id_usuario"));

        int idAdmin = rs.getInt("id_admin_revisor");

        if (!rs.wasNull()) {
            relato.setIdAdminRevisor(idAdmin);
        }

        relato.setDescricao(rs.getString("descricao"));

        Date dataRelato = rs.getDate("data_relato");

        if (dataRelato != null) {
            relato.setDataRelato(dataRelato.toLocalDate());
        }

        relato.setStatusAnalise(
                rs.getString("status_analise")
        );

        Date dataAnalise = rs.getDate("data_analise");

        if (dataAnalise != null) {
            relato.setDataAnalise(dataAnalise.toLocalDate());
        }

        return relato;
    }
}
