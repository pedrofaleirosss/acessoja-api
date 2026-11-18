
package br.com.acessoja.entities;

import java.time.LocalDate;

public class Registro {
    private Integer idRegistro;
    private Integer idUsuarioAutor;
    private Integer idBairro;
    private Integer idAdminModerador;
    private String tipoRegistro;
    private String titulo;
    private String descricao;
    private String endereco;
    private LocalDate dataCadastro;
    private String statusModeracao;
    private LocalDate dataModeracao;

    public Registro() {
        super();
    }

    public Registro(Integer idRegistro, Integer idUsuarioAutor,
                    Integer idBairro, Integer idAdminModerador,
                    String tipoRegistro, String titulo,
                    String descricao, String endereco,
                    LocalDate dataCadastro, String statusModeracao,
                    LocalDate dataModeracao) {

        this.idRegistro = idRegistro;
        this.idUsuarioAutor = idUsuarioAutor;
        this.idBairro = idBairro;
        this.idAdminModerador = idAdminModerador;
        this.tipoRegistro = tipoRegistro;
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.statusModeracao = statusModeracao;
        this.dataModeracao = dataModeracao;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdUsuarioAutor() {
        return idUsuarioAutor;
    }

    public void setIdUsuarioAutor(Integer idUsuarioAutor) {
        this.idUsuarioAutor = idUsuarioAutor;
    }

    public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public Integer getIdAdminModerador() {
        return idAdminModerador;
    }

    public void setIdAdminModerador(Integer idAdminModerador) {
        this.idAdminModerador = idAdminModerador;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatusModeracao() {
        return statusModeracao;
    }

    public void setStatusModeracao(String statusModeracao) {
        this.statusModeracao = statusModeracao;
    }

    public LocalDate getDataModeracao() {
        return dataModeracao;
    }

    public void setDataModeracao(LocalDate dataModeracao) {
        this.dataModeracao = dataModeracao;
    }

    @Override
    public String toString() {
        return "\nRegistro" +
                "\nidRegistro=" + idRegistro +
                "\nidUsuarioAutor=" + idUsuarioAutor +
                "\nidBairro=" + idBairro +
                "\ntipoRegistro='" + tipoRegistro + '\'' +
                "\ntitulo='" + titulo + '\'' +
                "\nstatusModeracao='" + statusModeracao + '\'';
    }
}
