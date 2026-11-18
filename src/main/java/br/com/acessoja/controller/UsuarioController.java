
package br.com.acessoja.controller;

import br.com.acessoja.bo.UsuarioBo;
import br.com.acessoja.entities.UsuarioEntrada;
import br.com.acessoja.entities.UsuarioResposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    UsuarioBo usuarioBo = new UsuarioBo();

    // GET - Listar todos
    @GetMapping
    public List<UsuarioResposta> selecionar()
            throws SQLException, ClassNotFoundException {

        return usuarioBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public UsuarioResposta buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return usuarioBo.buscarPorIdBo(id);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody UsuarioEntrada entrada)
            throws SQLException, ClassNotFoundException {

        usuarioBo.inserirBo(entrada);

        URI location = URI.create(
                "/usuarios/" + entrada.getIdUsuario()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody UsuarioEntrada entrada)
            throws SQLException, ClassNotFoundException {

        entrada.setIdUsuario(id);
        usuarioBo.atualizarBo(entrada);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        usuarioBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
