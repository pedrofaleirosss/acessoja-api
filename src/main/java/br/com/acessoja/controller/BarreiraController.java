
package br.com.acessoja.controller;

import br.com.acessoja.bo.BarreiraBo;
import br.com.acessoja.entities.Barreira;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/barreiras")
public class BarreiraController {

    BarreiraBo barreiraBo = new BarreiraBo();

    // GET - Listar todas
    @GetMapping
    public List<Barreira> selecionar()
            throws SQLException, ClassNotFoundException {

        return barreiraBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public Barreira buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return barreiraBo.buscarPorIdBo(id);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody Barreira barreira)
            throws SQLException, ClassNotFoundException {

        barreiraBo.inserirBo(barreira);

        URI location = URI.create(
                "/barreiras/" + barreira.getIdRegistro()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Barreira barreira)
            throws SQLException, ClassNotFoundException {

        barreira.setIdRegistro(id);
        barreiraBo.atualizarBo(barreira);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        barreiraBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
