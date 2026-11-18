
package br.com.acessoja.dao;

import br.com.acessoja.conexoes.ConexaoFactory;
import br.com.acessoja.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UsuarioDAO {

    // SELECT - Listar todos
    public List<Usuario> selecionar()
            throws SQLException, ClassNotFoundException {

        List<Usuario> usuarios = new ArrayList<>();

        String sql =
                "SELECT * FROM USUARIO ORDER BY id_usuario";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }

        return usuarios;
    }

    // SELECT - Buscar por ID
    public Usuario buscarPorId(int id)
            throws SQLException, ClassNotFoundException {

        String sql =
                "SELECT * FROM USUARIO WHERE id_usuario = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        throw new NoSuchElementException(
                "Usuário não encontrado"
        );
    }

    // INSERT
    public void inserir(Usuario usuario)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO USUARIO " +
                "(id_usuario, nome, email, senha_hash, " +
                "perfil, data_cadastro, ativo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenhaHash());
            stmt.setString(5, usuario.getPerfil());
            stmt.setDate(6, Date.valueOf(
                    usuario.getDataCadastro()
            ));
            stmt.setString(7, usuario.getAtivo());

            stmt.executeUpdate();
        }
    }

    // UPDATE
    public void atualizar(Usuario usuario)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE USUARIO SET " +
                "nome = ?, email = ?, senha_hash = ? " +
                "WHERE id_usuario = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setInt(4, usuario.getIdUsuario());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Usuário não encontrado"
                );
            }
        }
    }

    // DELETE
    public void deletar(int id)
            throws SQLException, ClassNotFoundException {

        String sql =
                "DELETE FROM USUARIO WHERE id_usuario = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                throw new NoSuchElementException(
                        "Usuário não encontrado"
                );
            }
        }
    }

    // Converte o ResultSet em objeto Usuario
    private Usuario mapearUsuario(ResultSet rs)
            throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenhaHash(rs.getString("senha_hash"));
        usuario.setPerfil(rs.getString("perfil"));

        Date data = rs.getDate("data_cadastro");

        if (data != null) {
            usuario.setDataCadastro(data.toLocalDate());
        }

        usuario.setAtivo(rs.getString("ativo"));

        return usuario;
    }
}
