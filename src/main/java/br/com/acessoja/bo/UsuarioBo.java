
package br.com.acessoja.bo;

import br.com.acessoja.dao.UsuarioDAO;
import br.com.acessoja.entities.Usuario;
import br.com.acessoja.entities.UsuarioEntrada;
import br.com.acessoja.entities.UsuarioResposta;
import br.com.acessoja.util.SenhaUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBo {

    UsuarioDAO usuarioDAO;

    // Selecionar
    public List<UsuarioResposta> selecionarBo()
            throws SQLException, ClassNotFoundException {

        usuarioDAO = new UsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.selecionar();
        List<UsuarioResposta> respostas = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            respostas.add(new UsuarioResposta(usuario));
        }

        return respostas;
    }

    // Buscar por ID
    public UsuarioResposta buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.buscarPorId(id);

        return new UsuarioResposta(usuario);
    }

    // Inserir
    public void inserirBo(UsuarioEntrada entrada)
            throws SQLException, ClassNotFoundException {

        validarDados(entrada);
        validarSenha(entrada.getSenha());

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(entrada.getIdUsuario());
        usuario.setNome(entrada.getNome().trim());
        usuario.setEmail(entrada.getEmail().trim());
        usuario.setSenhaHash(
                SenhaUtil.gerarHash(entrada.getSenha())
        );
        usuario.setPerfil("CIDADAO");
        usuario.setDataCadastro(LocalDate.now());
        usuario.setAtivo("S");

        usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(usuario);
    }

    // Atualizar
    public void atualizarBo(UsuarioEntrada entrada)
            throws SQLException, ClassNotFoundException {

        validarDados(entrada);

        usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.buscarPorId(
                entrada.getIdUsuario()
        );

        usuario.setNome(entrada.getNome().trim());
        usuario.setEmail(entrada.getEmail().trim());

        // A senha só muda se for informada
        if (entrada.getSenha() != null &&
                !entrada.getSenha().isBlank()) {

            validarSenha(entrada.getSenha());

            usuario.setSenhaHash(
                    SenhaUtil.gerarHash(entrada.getSenha())
            );
        }

        usuarioDAO.atualizar(usuario);
    }

    // Deletar
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        usuarioDAO = new UsuarioDAO();
        usuarioDAO.deletar(id);
    }

    // Regras de negócio
    private void validarDados(UsuarioEntrada entrada) {

        if (entrada == null) {
            throw new IllegalArgumentException(
                    "Os dados do usuário são obrigatórios"
            );
        }

        if (entrada.getIdUsuario() == null ||
                entrada.getIdUsuario() <= 0) {

            throw new IllegalArgumentException(
                    "O ID do usuário deve ser maior que zero"
            );
        }

        if (entrada.getNome() == null ||
                entrada.getNome().isBlank() ||
                entrada.getNome().length() > 120) {

            throw new IllegalArgumentException(
                    "Informe um nome válido"
            );
        }

        if (entrada.getEmail() == null ||
                entrada.getEmail().isBlank() ||
                entrada.getEmail().length() > 150 ||
                !entrada.getEmail().matches(
                        "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {

            throw new IllegalArgumentException(
                    "Informe um e-mail válido"
            );
        }
    }

    private void validarSenha(String senha) {

        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException(
                    "A senha deve ter pelo menos 8 caracteres"
            );
        }
    }
}
