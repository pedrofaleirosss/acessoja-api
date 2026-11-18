
package br.com.acessoja.bo;

import br.com.acessoja.dao.RelatoResolucaoDAO;
import br.com.acessoja.dao.BarreiraDAO;
import br.com.acessoja.dao.UsuarioDAO;

import br.com.acessoja.entities.RelatoResolucao;
import br.com.acessoja.entities.Barreira;
import br.com.acessoja.entities.Usuario;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RelatoResolucaoBo {

    RelatoResolucaoDAO relatoDAO;

    // SELECT - Listar todos
    public List<RelatoResolucao> selecionarBo()
            throws SQLException, ClassNotFoundException {

        relatoDAO = new RelatoResolucaoDAO();
        return relatoDAO.selecionar();
    }

    // SELECT - Buscar por ID
    public RelatoResolucao buscarPorIdBo(int id)
            throws SQLException, ClassNotFoundException {

        validarId(id);

        relatoDAO = new RelatoResolucaoDAO();
        return relatoDAO.buscarPorId(id);
    }

    // SELECT - Buscar por barreira
    public List<RelatoResolucao> buscarPorBarreiraBo(int idBarreira)
            throws SQLException, ClassNotFoundException {

        validarId(idBarreira);

        relatoDAO = new RelatoResolucaoDAO();
        return relatoDAO.buscarPorBarreira(idBarreira);
    }

    // INSERT - Cadastrar relato
    public void inserirBo(RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        if (relato == null) {
            throw new IllegalArgumentException(
                    "Os dados do relato são obrigatórios"
            );
        }

        validarId(relato.getIdRelato());
        validarId(relato.getIdBarreira());
        validarId(relato.getIdUsuario());
        validarDescricao(relato.getDescricao());

        // Verifica a barreira informada
        BarreiraDAO barreiraDAO = new BarreiraDAO();

        Barreira barreira = barreiraDAO.buscarPorId(
                relato.getIdBarreira()
        );

        if (!"APROVADO".equals(
                barreira.getStatusModeracao())) {

            throw new IllegalArgumentException(
                    "A barreira precisa estar aprovada"
            );
        }

        if (!"ATIVA".equals(barreira.getStatusBarreira())) {

            throw new IllegalArgumentException(
                    "A barreira não está ativa"
            );
        }

        // Define os dados iniciais
        relato.setDataRelato(LocalDate.now());
        relato.setStatusAnalise("PENDENTE");
        relato.setIdAdminRevisor(null);
        relato.setDataAnalise(null);

        relatoDAO = new RelatoResolucaoDAO();
        relatoDAO.inserir(relato);
    }

    // UPDATE - Alterar descrição
    public void atualizarBo(RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        if (relato == null) {
            throw new IllegalArgumentException(
                    "Os dados do relato são obrigatórios"
            );
        }

        validarId(relato.getIdRelato());
        validarDescricao(relato.getDescricao());

        relatoDAO = new RelatoResolucaoDAO();
        relatoDAO.atualizar(relato);
    }

    // UPDATE - Analisar relato
    public void analisarBo(RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        if (relato == null) {
            throw new IllegalArgumentException(
                    "Informe os dados da análise"
            );
        }

        validarId(relato.getIdRelato());
        validarId(relato.getIdAdminRevisor());

        String status = relato.getStatusAnalise();

        if (status == null ||
                (!status.equals("APROVADO") &&
                        !status.equals("REJEITADO"))) {

            throw new IllegalArgumentException(
                    "Use APROVADO ou REJEITADO"
            );
        }

        // Verifica se o revisor é administrador
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario admin = usuarioDAO.buscarPorId(
                relato.getIdAdminRevisor()
        );

        if (!"ADMIN".equals(admin.getPerfil()) ||
                !"S".equals(admin.getAtivo())) {

            throw new IllegalArgumentException(
                    "O revisor deve ser um administrador ativo"
            );
        }

        // Define a data da análise
        relato.setDataAnalise(LocalDate.now());

        relatoDAO = new RelatoResolucaoDAO();
        relatoDAO.analisar(relato);
    }

    // DELETE - Excluir relato pendente
    public void deletarBo(int id)
            throws SQLException, ClassNotFoundException {

        validarId(id);

        relatoDAO = new RelatoResolucaoDAO();
        relatoDAO.deletar(id);
    }

    // Validação de ID
    private void validarId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID válido"
            );
        }
    }

    // Validação da descrição
    private void validarDescricao(String descricao) {

        if (descricao == null ||
                descricao.isBlank() ||
                descricao.length() > 500) {

            throw new IllegalArgumentException(
                    "Informe uma descrição válida de até 500 caracteres"
            );
        }
    }
}
