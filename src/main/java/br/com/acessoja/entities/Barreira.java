
package br.com.acessoja.entities;

public class Barreira extends Registro {
    private String tipoBarreira;
    private String statusBarreira;

    public Barreira() {
        super();
    }

    public Barreira(String tipoBarreira, String statusBarreira) {
        super();
        this.tipoBarreira = tipoBarreira;
        this.statusBarreira = statusBarreira;
    }

    public String getTipoBarreira() {
        return tipoBarreira;
    }

    public void setTipoBarreira(String tipoBarreira) {
        this.tipoBarreira = tipoBarreira;
    }

    public String getStatusBarreira() {
        return statusBarreira;
    }

    public void setStatusBarreira(String statusBarreira) {
        this.statusBarreira = statusBarreira;
    }

    @Override
    public String toString() {
        return "\nBarreira" +
                "\nidRegistro=" + getIdRegistro() +
                "\ntitulo='" + getTitulo() + '\'' +
                "\ntipoBarreira='" + tipoBarreira + '\'' +
                "\nstatusBarreira='" + statusBarreira + '\'';
    }
}
