
package br.com.acessoja.entities;

public class Bairro {
    private Integer idBairro;
    private String nome;
    private Integer idCidade;

    public Bairro() {
        super();
    }

    public Bairro(Integer idBairro, String nome, Integer idCidade) {
        this.idBairro = idBairro;
        this.nome = nome;
        this.idCidade = idCidade;
    }

    public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    @Override
    public String toString() {
        return "\nBairro" +
                "\nidBairro=" + idBairro +
                "\nnome='" + nome + '\'' +
                "\nidCidade=" + idCidade;
    }
}
