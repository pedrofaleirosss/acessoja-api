
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Bairro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BairroDAO {

    // SELECT - Listar todos
    public List<Bairro> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Bairro> bairros = new ArrayList<>();

        String sql = "SELECT * FROM BAIRRO ORDER BY id_bairro";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bairro bairro = new Bairro();

                bairro.setIdBairro(rs.getInt("id_bairro"));
                bairro.setNome(rs.getString("nome"));
                bairro.setIdCidade(rs.getInt("id_cidade"));

                bairros.add(bairro);
            }
        }

        return bairros;
    }

    // SELECT - Buscar por ID
    public Bairro buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM BAIRRO WHERE id_bairro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Bairro bairro = new Bairro();

                    bairro.setIdBairro(rs.getInt("id_bairro"));
                    bairro.setNome(rs.getString("nome"));
                    bairro.setIdCidade(rs.getInt("id_cidade"));

                    return bairro;
                }
            }
        }

        throw new NoSuchElementException("Bairro não encontrado");
    }

    // SELECT - Buscar bairros por cidade
    public List<Bairro> buscarPorCidade(int idCidade)
            throws SQLException, ClassNotFoundException {

        List<Bairro> bairros = new ArrayList<>();

        String sql = "SELECT * FROM BAIRRO WHERE id_cidade = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCidade);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Bairro bairro = new Bairro();

                    bairro.setIdBairro(rs.getInt("id_bairro"));
                    bairro.setNome(rs.getString("nome"));
                    bairro.setIdCidade(rs.getInt("id_cidade"));

                    bairros.add(bairro);
                }
            }
        }

        return bairros;
    }

    // INSERT
    public void inserir(Bairro bairro)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO BAIRRO " +
                "(id_bairro, nome, id_cidade) VALUES (?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, bairro.getIdBairro());
            stmt.setString(2, bairro.getNome());
            stmt.setInt(3, bairro.getIdCidade());

            stmt.executeUpdate();
        }
    }

    // UPDATE
    public void atualizar(Bairro bairro)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE BAIRRO SET nome = ?, id_cidade = ? " +
                "WHERE id_bairro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, bairro.getNome());
            stmt.setInt(2, bairro.getIdCidade());
            stmt.setInt(3, bairro.getIdBairro());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Bairro não encontrado"
                );
            }
        }
    }

    // DELETE
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM BAIRRO WHERE id_bairro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Bairro não encontrado"
                );
            }
        }
    }
}
