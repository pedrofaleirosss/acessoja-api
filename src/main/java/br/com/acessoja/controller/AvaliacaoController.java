
package br.com.acessoja.controller;

import br.com.acessoja.bo.AvaliacaoBo;
import br.com.acessoja.entities.Avaliacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    AvaliacaoBo avaliacaoBo = new AvaliacaoBo();

    // GET - Listar todas
    @GetMapping
    public List<Avaliacao> selecionar()
            throws SQLException, ClassNotFoundException {

        return avaliacaoBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public Avaliacao buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return avaliacaoBo.buscarPorIdBo(id);
    }

    // GET - Buscar por estabelecimento
    @GetMapping("/estabelecimento/{idEstabelecimento}")
    public List<Avaliacao> buscarPorEstabelecimento(
            @PathVariable int idEstabelecimento)
            throws SQLException, ClassNotFoundException {

        return avaliacaoBo.buscarPorEstabelecimentoBo(
                idEstabelecimento
        );
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody Avaliacao avaliacao)
            throws SQLException, ClassNotFoundException {

        avaliacaoBo.inserirBo(avaliacao);

        URI location = URI.create(
                "/avaliacoes/" + avaliacao.getIdAvaliacao()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Avaliacao avaliacao)
            throws SQLException, ClassNotFoundException {

        avaliacao.setIdAvaliacao(id);
        avaliacaoBo.atualizarBo(avaliacao);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        avaliacaoBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
