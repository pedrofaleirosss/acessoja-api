
package br.com.acessoja.entities;

public class AreaAcessivel extends Registro {
    private String tipoArea;
    private String caracteristicas;

    public AreaAcessivel() {
        super();
    }

    public AreaAcessivel(String tipoArea, String caracteristicas) {
        super();
        this.tipoArea = tipoArea;
        this.caracteristicas = caracteristicas;
    }

    public String getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(String tipoArea) {
        this.tipoArea = tipoArea;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "\nAreaAcessivel" +
                "\nidRegistro=" + getIdRegistro() +
                "\ntitulo='" + getTitulo() + '\'' +
                "\ntipoArea='" + tipoArea + '\'' +
                "\ncaracteristicas='" + caracteristicas + '\'';
    }
}
