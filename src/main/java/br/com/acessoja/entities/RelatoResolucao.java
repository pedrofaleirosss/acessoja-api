
package br.com.acessoja.entities;

import java.time.LocalDate;

public class RelatoResolucao {
    private Integer idRelato;
    private Integer idBarreira;
    private Integer idUsuario;
    private Integer idAdminRevisor;
    private String descricao;
    private LocalDate dataRelato;
    private String statusAnalise;
    private LocalDate dataAnalise;

    public RelatoResolucao() {
        super();
    }

    public RelatoResolucao(Integer idRelato,
                           Integer idBarreira,
                           Integer idUsuario,
                           Integer idAdminRevisor,
                           String descricao,
                           LocalDate dataRelato,
                           String statusAnalise,
                           LocalDate dataAnalise) {

        this.idRelato = idRelato;
        this.idBarreira = idBarreira;
        this.idUsuario = idUsuario;
        this.idAdminRevisor = idAdminRevisor;
        this.descricao = descricao;
        this.dataRelato = dataRelato;
        this.statusAnalise = statusAnalise;
        this.dataAnalise = dataAnalise;
    }

    public Integer getIdRelato() {
        return idRelato;
    }

    public void setIdRelato(Integer idRelato) {
        this.idRelato = idRelato;
    }

    public Integer getIdBarreira() {
        return idBarreira;
    }

    public void setIdBarreira(Integer idBarreira) {
        this.idBarreira = idBarreira;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdAdminRevisor() {
        return idAdminRevisor;
    }

    public void setIdAdminRevisor(Integer idAdminRevisor) {
        this.idAdminRevisor = idAdminRevisor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataRelato() {
        return dataRelato;
    }

    public void setDataRelato(LocalDate dataRelato) {
        this.dataRelato = dataRelato;
    }

    public String getStatusAnalise() {
        return statusAnalise;
    }

    public void setStatusAnalise(String statusAnalise) {
        this.statusAnalise = statusAnalise;
    }

    public LocalDate getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(LocalDate dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    @Override
    public String toString() {
        return "\nRelatoResolucao" +
                "\nidRelato=" + idRelato +
                "\nidBarreira=" + idBarreira +
                "\nidUsuario=" + idUsuario +
                "\nstatusAnalise='" + statusAnalise + '\'';
    }
}
