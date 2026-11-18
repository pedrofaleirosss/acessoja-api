
package br.com.acessoja.entities;

import java.time.LocalDate;

public class Avaliacao {
    private Integer idAvaliacao;
    private Integer idUsuario;
    private Integer idEstabelecimento;
    private Integer nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    public Avaliacao() {
        super();
    }

    public Avaliacao(Integer idAvaliacao, Integer idUsuario,
                     Integer idEstabelecimento, Integer nota,
                     String comentario, LocalDate dataAvaliacao) {
        this.idAvaliacao = idAvaliacao;
        this.idUsuario = idUsuario;
        this.idEstabelecimento = idEstabelecimento;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Integer idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    @Override
    public String toString() {
        return "\nAvaliacao" +
                "\nidAvaliacao=" + idAvaliacao +
                "\nidUsuario=" + idUsuario +
                "\nidEstabelecimento=" + idEstabelecimento +
                "\nnota=" + nota;
    }
}
