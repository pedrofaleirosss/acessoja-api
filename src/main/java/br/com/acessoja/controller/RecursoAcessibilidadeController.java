
package br.com.acessoja.controller;

import br.com.acessoja.bo.RecursoAcessibilidadeBo;
import br.com.acessoja.entities.RecursoAcessibilidade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/recursos-acessibilidade")
public class RecursoAcessibilidadeController {

    RecursoAcessibilidadeBo recursoBo =
            new RecursoAcessibilidadeBo();

    // GET - Listar todos
    @GetMapping
    public List<RecursoAcessibilidade> selecionar()
            throws SQLException, ClassNotFoundException {

        return recursoBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public RecursoAcessibilidade buscarPorId(
            @PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return recursoBo.buscarPorIdBo(id);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody RecursoAcessibilidade recurso)
            throws SQLException, ClassNotFoundException {

        recursoBo.inserirBo(recurso);

        URI location = URI.create(
                "/recursos-acessibilidade/" + recurso.getIdRecurso()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody RecursoAcessibilidade recurso)
            throws SQLException, ClassNotFoundException {

        recurso.setIdRecurso(id);
        recursoBo.atualizarBo(recurso);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        recursoBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
