
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Validacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ValidacaoDAO {

    // SELECT - Listar todas
    public List<Validacao> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Validacao> validacoes = new ArrayList<>();

        String sql = "SELECT * FROM VALIDACAO ORDER BY id_validacao";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                validacoes.add(mapearValidacao(rs));
            }
        }

        return validacoes;
    }

    // SELECT - Buscar por ID
    public Validacao buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM VALIDACAO WHERE id_validacao = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearValidacao(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Validação não encontrada"
        );
    }

    // SELECT - Buscar validações de um registro
    public List<Validacao> buscarPorRegistro(int idRegistro)
            throws SQLException, ClassNotFoundException {

        List<Validacao> validacoes = new ArrayList<>();

        String sql = "SELECT * FROM VALIDACAO " +
                "WHERE id_registro = ? ORDER BY id_validacao";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idRegistro);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    validacoes.add(mapearValidacao(rs));
                }
            }
        }

        return validacoes;
    }

    // INSERT
    public void inserir(Validacao validacao)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO VALIDACAO " +
                "(id_validacao, id_usuario, id_registro, " +
                "tipo_validacao, comentario, data_validacao) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, validacao.getIdValidacao());
            stmt.setInt(2, validacao.getIdUsuario());
            stmt.setInt(3, validacao.getIdRegistro());
            stmt.setString(4, validacao.getTipoValidacao());
            stmt.setString(5, validacao.getComentario());
            stmt.setDate(6, Date.valueOf(
                    validacao.getDataValidacao()
            ));

            stmt.executeUpdate();
        }
    }

    // UPDATE - Alterar confirmação ou contestação
    public void atualizar(Validacao validacao)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE VALIDACAO " +
                "SET tipo_validacao = ?, comentario = ? " +
                "WHERE id_validacao = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, validacao.getTipoValidacao());
            stmt.setString(2, validacao.getComentario());
            stmt.setInt(3, validacao.getIdValidacao());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Validação não encontrada"
                );
            }
        }
    }

    // DELETE
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM VALIDACAO WHERE id_validacao = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Validação não encontrada"
                );
            }
        }
    }

    // Converte ResultSet em objeto Validacao
    private Validacao mapearValidacao(ResultSet rs)
            throws SQLException {

        Validacao validacao = new Validacao();

        validacao.setIdValidacao(rs.getInt("id_validacao"));
        validacao.setIdUsuario(rs.getInt("id_usuario"));
        validacao.setIdRegistro(rs.getInt("id_registro"));
        validacao.setTipoValidacao(
                rs.getString("tipo_validacao")
        );
        validacao.setComentario(rs.getString("comentario"));

        Date data = rs.getDate("data_validacao");

        if (data != null) {
            validacao.setDataValidacao(data.toLocalDate());
        }

        return validacao;
    }
}
