
package br.com.acessoja.entities;

import java.time.LocalDate;

public class Validacao {
    private Integer idValidacao;
    private Integer idUsuario;
    private Integer idRegistro;
    private String tipoValidacao;
    private String comentario;
    private LocalDate dataValidacao;

    public Validacao() {
        super();
    }

    public Validacao(Integer idValidacao, Integer idUsuario,
                     Integer idRegistro, String tipoValidacao,
                     String comentario, LocalDate dataValidacao) {
        this.idValidacao = idValidacao;
        this.idUsuario = idUsuario;
        this.idRegistro = idRegistro;
        this.tipoValidacao = tipoValidacao;
        this.comentario = comentario;
        this.dataValidacao = dataValidacao;
    }

    public Integer getIdValidacao() {
        return idValidacao;
    }

    public void setIdValidacao(Integer idValidacao) {
        this.idValidacao = idValidacao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getTipoValidacao() {
        return tipoValidacao;
    }

    public void setTipoValidacao(String tipoValidacao) {
        this.tipoValidacao = tipoValidacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(LocalDate dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    @Override
    public String toString() {
        return "\nValidacao" +
                "\nidValidacao=" + idValidacao +
                "\nidUsuario=" + idUsuario +
                "\nidRegistro=" + idRegistro +
                "\ntipoValidacao='" + tipoValidacao + '\'';
    }
}
