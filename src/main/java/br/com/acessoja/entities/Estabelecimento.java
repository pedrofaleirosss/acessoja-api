
package br.com.acessoja.entities;

public class Estabelecimento extends Registro {
    private String categoria;

    public Estabelecimento() {
        super();
    }

    public Estabelecimento(String categoria) {
        super();
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "\nEstabelecimento" +
                "\nidRegistro=" + getIdRegistro() +
                "\ntitulo='" + getTitulo() + '\'' +
                "\ncategoria='" + categoria + '\'';
    }
}
