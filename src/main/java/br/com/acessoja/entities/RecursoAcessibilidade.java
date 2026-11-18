
package br.com.acessoja.entities;

public class RecursoAcessibilidade {
    private Integer idRecurso;
    private String nome;
    private String descricao;

    public RecursoAcessibilidade() {
        super();
    }

    public RecursoAcessibilidade(Integer idRecurso,
                                 String nome, String descricao) {
        this.idRecurso = idRecurso;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "\nRecursoAcessibilidade" +
                "\nidRecurso=" + idRecurso +
                "\nnome='" + nome + '\'' +
                "\ndescricao='" + descricao + '\'';
    }
}
