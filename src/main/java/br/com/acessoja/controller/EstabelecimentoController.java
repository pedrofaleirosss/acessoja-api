
package br.com.acessoja.controller;

import br.com.acessoja.bo.EstabelecimentoBo;
import br.com.acessoja.entities.Estabelecimento;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    EstabelecimentoBo estabelecimentoBo = new EstabelecimentoBo();

    // GET - Listar todos
    @GetMapping
    public List<Estabelecimento> selecionar()
            throws SQLException, ClassNotFoundException {

        return estabelecimentoBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public Estabelecimento buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return estabelecimentoBo.buscarPorIdBo(id);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody Estabelecimento estabelecimento)
            throws SQLException, ClassNotFoundException {

        estabelecimentoBo.inserirBo(estabelecimento);

        URI location = URI.create(
                "/estabelecimentos/" + estabelecimento.getIdRegistro()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Estabelecimento estabelecimento)
            throws SQLException, ClassNotFoundException {

        estabelecimento.setIdRegistro(id);
        estabelecimentoBo.atualizarBo(estabelecimento);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        estabelecimentoBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
