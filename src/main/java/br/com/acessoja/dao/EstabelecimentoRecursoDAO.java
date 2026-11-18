
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.EstabelecimentoRecurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EstabelecimentoRecursoDAO {

    // SELECT - Listar todas as associações
    public List<EstabelecimentoRecurso> selecionar()
            throws SQLException, ClassNotFoundException {

        List<EstabelecimentoRecurso> associacoes =
                new ArrayList<>();

        String sql = "SELECT * FROM ESTABELECIMENTO_RECURSO " +
                "ORDER BY id_estabelecimento, id_recurso";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                associacoes.add(mapearAssociacao(rs));
            }
        }

        return associacoes;
    }

    // SELECT - Buscar associação pelos dois IDs
    public EstabelecimentoRecurso buscarPorIds(
            int idEstabelecimento, int idRecurso)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM ESTABELECIMENTO_RECURSO " +
                "WHERE id_estabelecimento = ? AND id_recurso = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEstabelecimento);
            stmt.setInt(2, idRecurso);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearAssociacao(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Associação não encontrada"
        );
    }

    // SELECT - Listar recursos de um estabelecimento
    public List<EstabelecimentoRecurso> buscarPorEstabelecimento(
            int idEstabelecimento)
            throws SQLException, ClassNotFoundException {

        List<EstabelecimentoRecurso> associacoes =
                new ArrayList<>();

        String sql = "SELECT * FROM ESTABELECIMENTO_RECURSO " +
                "WHERE id_estabelecimento = ? ORDER BY id_recurso";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEstabelecimento);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    associacoes.add(mapearAssociacao(rs));
                }
            }
        }

        return associacoes;
    }

    // INSERT - Associar recurso ao estabelecimento
    public void inserir(EstabelecimentoRecurso associacao)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO ESTABELECIMENTO_RECURSO " +
                "(id_estabelecimento, id_recurso) VALUES (?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, associacao.getIdEstabelecimento());
            stmt.setInt(2, associacao.getIdRecurso());

            stmt.executeUpdate();
        }
    }

    // UPDATE - Trocar o recurso associado
    public void atualizar(int idEstabelecimento,
                          int idRecursoAtual,
                          int idNovoRecurso)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE ESTABELECIMENTO_RECURSO " +
                "SET id_recurso = ? " +
                "WHERE id_estabelecimento = ? AND id_recurso = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idNovoRecurso);
            stmt.setInt(2, idEstabelecimento);
            stmt.setInt(3, idRecursoAtual);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Associação não encontrada"
                );
            }
        }
    }

    // DELETE - Remover associação
    public void deletar(int idEstabelecimento, int idRecurso)
            throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM ESTABELECIMENTO_RECURSO " +
                "WHERE id_estabelecimento = ? AND id_recurso = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEstabelecimento);
            stmt.setInt(2, idRecurso);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Associação não encontrada"
                );
            }
        }
    }

    // Converte ResultSet em objeto
    private EstabelecimentoRecurso mapearAssociacao(ResultSet rs)
            throws SQLException {

        EstabelecimentoRecurso associacao =
                new EstabelecimentoRecurso();

        associacao.setIdEstabelecimento(
                rs.getInt("id_estabelecimento")
        );

        associacao.setIdRecurso(
                rs.getInt("id_recurso")
        );

        return associacao;
    }
}
