
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Registro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RegistroDAO {

    // SELECT - Listar todos
    public List<Registro> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Registro> registros = new ArrayList<>();

        String sql = "SELECT * FROM REGISTRO ORDER BY id_registro";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                registros.add(mapearRegistro(rs));
            }
        }

        return registros;
    }

    // SELECT - Buscar por ID
    public Registro buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM REGISTRO WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearRegistro(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Registro não encontrado"
        );
    }

    // SELECT - Buscar por tipo
    public List<Registro> buscarPorTipo(String tipo)
            throws SQLException, ClassNotFoundException {

        List<Registro> registros = new ArrayList<>();

        String sql = "SELECT * FROM REGISTRO " +
                "WHERE tipo_registro = ? ORDER BY id_registro";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    registros.add(mapearRegistro(rs));
                }
            }
        }

        return registros;
    }

    // INSERT
    public void inserir(Registro registro)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO REGISTRO " +
                "(id_registro, id_usuario_autor, id_bairro, " +
                "tipo_registro, titulo, descricao, endereco, " +
                "data_cadastro, status_moderacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, registro.getIdRegistro());
            stmt.setInt(2, registro.getIdUsuarioAutor());
            stmt.setInt(3, registro.getIdBairro());
            stmt.setString(4, registro.getTipoRegistro());
            stmt.setString(5, registro.getTitulo());
            stmt.setString(6, registro.getDescricao());
            stmt.setString(7, registro.getEndereco());
            stmt.setDate(8, Date.valueOf(
                    registro.getDataCadastro()
            ));
            stmt.setString(9, registro.getStatusModeracao());

            stmt.executeUpdate();
        }
    }

    // UPDATE - Atualizar informações gerais
    public void atualizar(Registro registro)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE REGISTRO SET " +
                "id_bairro = ?, titulo = ?, descricao = ?, " +
                "endereco = ? WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, registro.getIdBairro());
            stmt.setString(2, registro.getTitulo());
            stmt.setString(3, registro.getDescricao());
            stmt.setString(4, registro.getEndereco());
            stmt.setInt(5, registro.getIdRegistro());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Registro não encontrado"
                );
            }
        }
    }

    // DELETE
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM REGISTRO WHERE id_registro = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Registro não encontrado"
                );
            }
        }
    }

    // Converte ResultSet em objeto Registro
    private Registro mapearRegistro(ResultSet rs)
            throws SQLException {

        Registro registro = new Registro();

        registro.setIdRegistro(rs.getInt("id_registro"));
        registro.setIdUsuarioAutor(
                rs.getInt("id_usuario_autor")
        );
        registro.setIdBairro(rs.getInt("id_bairro"));

        int idAdmin = rs.getInt("id_admin_moderador");

        if (!rs.wasNull()) {
            registro.setIdAdminModerador(idAdmin);
        }

        registro.setTipoRegistro(
                rs.getString("tipo_registro")
        );
        registro.setTitulo(rs.getString("titulo"));
        registro.setDescricao(rs.getString("descricao"));
        registro.setEndereco(rs.getString("endereco"));

        Date dataCadastro = rs.getDate("data_cadastro");

        if (dataCadastro != null) {
            registro.setDataCadastro(
                    dataCadastro.toLocalDate()
            );
        }

        registro.setStatusModeracao(
                rs.getString("status_moderacao")
        );

        Date dataModeracao = rs.getDate("data_moderacao");

        if (dataModeracao != null) {
            registro.setDataModeracao(
                    dataModeracao.toLocalDate()
            );
        }

        return registro;
    }
}
