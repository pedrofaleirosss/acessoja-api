
package br.com.acessoja.controller;

import br.com.acessoja.bo.CidadeBo;
import br.com.acessoja.entities.Cidade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    CidadeBo cidadeBo = new CidadeBo();

    // GET - Listar todas
    @GetMapping
    public List<Cidade> selecionar()
            throws SQLException, ClassNotFoundException {

        return cidadeBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public Cidade buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return cidadeBo.buscarPorIdBo(id);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody Cidade cidade)
            throws SQLException, ClassNotFoundException {

        cidadeBo.inserirBo(cidade);

        URI location = URI.create(
                "/cidades/" + cidade.getIdCidade()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Cidade cidade)
            throws SQLException, ClassNotFoundException {

        cidade.setIdCidade(id);
        cidadeBo.atualizarBo(cidade);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        cidadeBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
