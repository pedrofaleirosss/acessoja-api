
package br.com.acessoja.controller;

import br.com.acessoja.bo.AreaAcessivelBo;
import br.com.acessoja.entities.AreaAcessivel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/areas-acessiveis")
public class AreaAcessivelController {

    AreaAcessivelBo areaBo = new AreaAcessivelBo();

    // GET - Listar todas
    @GetMapping
    public List<AreaAcessivel> selecionar()
            throws SQLException, ClassNotFoundException {

        return areaBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public AreaAcessivel buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return areaBo.buscarPorIdBo(id);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody AreaAcessivel area)
            throws SQLException, ClassNotFoundException {

        areaBo.inserirBo(area);

        URI location = URI.create(
                "/areas-acessiveis/" + area.getIdRegistro()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody AreaAcessivel area)
            throws SQLException, ClassNotFoundException {

        area.setIdRegistro(id);
        areaBo.atualizarBo(area);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        areaBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
