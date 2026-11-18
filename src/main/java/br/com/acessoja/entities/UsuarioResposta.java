
package br.com.acessoja.entities;

import java.time.LocalDate;

public class UsuarioResposta {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String perfil;
    private LocalDate dataCadastro;
    private String ativo;

    public UsuarioResposta(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfil = usuario.getPerfil();
        this.dataCadastro = usuario.getDataCadastro();
        this.ativo = usuario.getAtivo();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPerfil() {
        return perfil;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getAtivo() {
        return ativo;
    }
}
