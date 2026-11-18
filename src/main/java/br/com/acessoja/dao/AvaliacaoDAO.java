
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AvaliacaoDAO {

    // SELECT - Listar todas
    public List<Avaliacao> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Avaliacao> avaliacoes = new ArrayList<>();

        String sql = "SELECT * FROM AVALIACAO ORDER BY id_avaliacao";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                avaliacoes.add(mapearAvaliacao(rs));
            }
        }

        return avaliacoes;
    }

    // SELECT - Buscar por ID
    public Avaliacao buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM AVALIACAO WHERE id_avaliacao = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearAvaliacao(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Avaliação não encontrada"
        );
    }

    // SELECT - Avaliações de um estabelecimento
    public List<Avaliacao> buscarPorEstabelecimento(
            int idEstabelecimento)
            throws SQLException, ClassNotFoundException {

        List<Avaliacao> avaliacoes = new ArrayList<>();

        String sql = "SELECT * FROM AVALIACAO " +
                "WHERE id_estabelecimento = ? ORDER BY id_avaliacao";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEstabelecimento);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    avaliacoes.add(mapearAvaliacao(rs));
                }
            }
        }

        return avaliacoes;
    }

    // INSERT
    public void inserir(Avaliacao avaliacao)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO AVALIACAO " +
                "(id_avaliacao, id_usuario, id_estabelecimento, " +
                "nota, comentario, data_avaliacao) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, avaliacao.getIdAvaliacao());
            stmt.setInt(2, avaliacao.getIdUsuario());
            stmt.setInt(3, avaliacao.getIdEstabelecimento());
            stmt.setInt(4, avaliacao.getNota());
            stmt.setString(5, avaliacao.getComentario());
            stmt.setDate(6, Date.valueOf(
                    avaliacao.getDataAvaliacao()
            ));

            stmt.executeUpdate();
        }
    }

    // UPDATE - Alterar nota e comentário
    public void atualizar(Avaliacao avaliacao)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE AVALIACAO " +
                "SET nota = ?, comentario = ? " +
                "WHERE id_avaliacao = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setInt(3, avaliacao.getIdAvaliacao());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Avaliação não encontrada"
                );
            }
        }
    }

    // DELETE
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM AVALIACAO WHERE id_avaliacao = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Avaliação não encontrada"
                );
            }
        }
    }

    // Converte ResultSet em objeto Avaliacao
    private Avaliacao mapearAvaliacao(ResultSet rs)
            throws SQLException {

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setIdAvaliacao(rs.getInt("id_avaliacao"));
        avaliacao.setIdUsuario(rs.getInt("id_usuario"));
        avaliacao.setIdEstabelecimento(
                rs.getInt("id_estabelecimento")
        );
        avaliacao.setNota(rs.getInt("nota"));
        avaliacao.setComentario(rs.getString("comentario"));

        Date data = rs.getDate("data_avaliacao");

        if (data != null) {
            avaliacao.setDataAvaliacao(data.toLocalDate());
        }

        return avaliacao;
    }
}
