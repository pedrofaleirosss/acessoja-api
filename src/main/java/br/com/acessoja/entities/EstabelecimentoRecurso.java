
package br.com.acessoja.entities;

public class EstabelecimentoRecurso {
    private Integer idEstabelecimento;
    private Integer idRecurso;

    public EstabelecimentoRecurso() {
        super();
    }

    public EstabelecimentoRecurso(Integer idEstabelecimento,
                                  Integer idRecurso) {
        this.idEstabelecimento = idEstabelecimento;
        this.idRecurso = idRecurso;
    }

    public Integer getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Integer idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public String toString() {
        return "\nEstabelecimentoRecurso" +
                "\nidEstabelecimento=" + idEstabelecimento +
                "\nidRecurso=" + idRecurso;
    }
}
