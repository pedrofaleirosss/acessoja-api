
package br.com.acessoja.controller;

import br.com.acessoja.bo.RelatoResolucaoBo;
import br.com.acessoja.entities.RelatoResolucao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/relatos-resolucao")
public class RelatoResolucaoController {

    RelatoResolucaoBo relatoBo = new RelatoResolucaoBo();

    // GET - Listar todos
    @GetMapping
    public List<RelatoResolucao> selecionar()
            throws SQLException, ClassNotFoundException {

        return relatoBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public RelatoResolucao buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return relatoBo.buscarPorIdBo(id);
    }

    // GET - Buscar relatos de uma barreira
    @GetMapping("/barreira/{idBarreira}")
    public List<RelatoResolucao> buscarPorBarreira(
            @PathVariable int idBarreira)
            throws SQLException, ClassNotFoundException {

        return relatoBo.buscarPorBarreiraBo(idBarreira);
    }

    // POST - Cadastrar relato
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        relatoBo.inserirBo(relato);

        URI location = URI.create(
                "/relatos-resolucao/" + relato.getIdRelato()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar descrição
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        relato.setIdRelato(id);
        relatoBo.atualizarBo(relato);

        return ResponseEntity.ok().build();
    }

    // PUT - Aprovar ou rejeitar relato
    @PutMapping("/{id}/analisar")
    public ResponseEntity<Void> analisar(
            @PathVariable int id,
            @RequestBody RelatoResolucao relato)
            throws SQLException, ClassNotFoundException {

        relato.setIdRelato(id);
        relatoBo.analisarBo(relato);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir relato pendente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        relatoBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
