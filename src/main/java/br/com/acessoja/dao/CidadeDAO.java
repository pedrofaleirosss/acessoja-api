
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CidadeDAO {
    // SELECT - Listar todas
    public List<Cidade> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Cidade> cidades = new ArrayList<>();

        String sql = "SELECT * FROM CIDADE ORDER BY id_cidade";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cidade cidade = new Cidade();

                cidade.setIdCidade(rs.getInt("id_cidade"));
                cidade.setNome(rs.getString("nome"));
                cidade.setUf(rs.getString("uf"));

                cidades.add(cidade);
            }
        }

        return cidades;
    }

    // SELECT - Buscar por ID
    public Cidade buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM CIDADE WHERE id_cidade = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Cidade cidade = new Cidade();

                    cidade.setIdCidade(rs.getInt("id_cidade"));
                    cidade.setNome(rs.getString("nome"));
                    cidade.setUf(rs.getString("uf"));

                    return cidade;
                }
            }
        }

        throw new NoSuchElementException("Cidade não encontrada");
    }

    // INSERT
    public void inserir(Cidade cidade)
            throws SQLException, ClassNotFoundException {

        String sql =
                "INSERT INTO CIDADE (id_cidade, nome, uf) " +
                        "VALUES (?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, cidade.getIdCidade());
            stmt.setString(2, cidade.getNome());
            stmt.setString(3, cidade.getUf());

            stmt.executeUpdate();
        }
    }

    // UPDATE
    public void atualizar(Cidade cidade)
            throws SQLException, ClassNotFoundException {

        String sql =
                "UPDATE CIDADE SET nome = ?, uf = ? " +
                        "WHERE id_cidade = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cidade.getNome());
            stmt.setString(2, cidade.getUf());
            stmt.setInt(3, cidade.getIdCidade());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Cidade não encontrada"
                );
            }
        }
    }

    // DELETE
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM CIDADE WHERE id_cidade = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Cidade não encontrada"
                );
            }
        }
    }
}
