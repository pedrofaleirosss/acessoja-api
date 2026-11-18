
package br.com.acessoja.entities;

public class Cidade {
    private Integer idCidade;
    private String nome;
    private String uf;

    public Cidade() {
        super();
    }

    public Cidade(Integer idCidade, String nome, String uf) {
        this.idCidade = idCidade;
        this.nome = nome;
        this.uf = uf;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "\nCidade" +
                "\nidCidade=" + idCidade +
                "\nnome='" + nome + '\'' +
                "\nuf='" + uf + '\'';
    }
}
