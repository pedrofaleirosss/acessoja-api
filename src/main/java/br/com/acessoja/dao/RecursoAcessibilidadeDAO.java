
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.RecursoAcessibilidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RecursoAcessibilidadeDAO {

    // SELECT - Listar todos
    public List<RecursoAcessibilidade> selecionar()
            throws SQLException, ClassNotFoundException {

        List<RecursoAcessibilidade> recursos = new ArrayList<>();

        String sql = "SELECT * FROM RECURSO_ACESSIBILIDADE " +
                "ORDER BY id_recurso";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                recursos.add(mapearRecurso(rs));
            }
        }

        return recursos;
    }

    // SELECT - Buscar por ID
    public RecursoAcessibilidade buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM RECURSO_ACESSIBILIDADE " +
                "WHERE id_recurso = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearRecurso(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Recurso de acessibilidade não encontrado"
        );
    }

    // INSERT
    public void inserir(RecursoAcessibilidade recurso)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO RECURSO_ACESSIBILIDADE " +
                "(id_recurso, nome, descricao) VALUES (?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, recurso.getIdRecurso());
            stmt.setString(2, recurso.getNome());
            stmt.setString(3, recurso.getDescricao());

            stmt.executeUpdate();
        }
    }

    // UPDATE
    public void atualizar(RecursoAcessibilidade recurso)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE RECURSO_ACESSIBILIDADE " +
                "SET nome = ?, descricao = ? WHERE id_recurso = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, recurso.getNome());
            stmt.setString(2, recurso.getDescricao());
            stmt.setInt(3, recurso.getIdRecurso());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Recurso de acessibilidade não encontrado"
                );
            }
        }
    }

    // DELETE
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM RECURSO_ACESSIBILIDADE " +
                "WHERE id_recurso = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Recurso de acessibilidade não encontrado"
                );
            }
        }
    }

    // Converte ResultSet em objeto
    private RecursoAcessibilidade mapearRecurso(ResultSet rs)
            throws SQLException {

        RecursoAcessibilidade recurso =
                new RecursoAcessibilidade();

        recurso.setIdRecurso(rs.getInt("id_recurso"));
        recurso.setNome(rs.getString("nome"));
        recurso.setDescricao(rs.getString("descricao"));

        return recurso;
    }
}
