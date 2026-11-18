
package br.com.acessoja.controller;

import br.com.acessoja.bo.EstabelecimentoRecursoBo;
import br.com.acessoja.entities.EstabelecimentoRecurso;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/estabelecimentos-recursos")
public class EstabelecimentoRecursoController {

    EstabelecimentoRecursoBo associacaoBo =
            new EstabelecimentoRecursoBo();

    // GET - Listar todas
    @GetMapping
    public List<EstabelecimentoRecurso> selecionar()
            throws SQLException, ClassNotFoundException {

        return associacaoBo.selecionarBo();
    }

    // GET - Buscar associação pelos dois IDs
    @GetMapping("/{idEstabelecimento}/{idRecurso}")
    public EstabelecimentoRecurso buscarPorIds(
            @PathVariable int idEstabelecimento,
            @PathVariable int idRecurso)
            throws SQLException, ClassNotFoundException {

        return associacaoBo.buscarPorIdsBo(
                idEstabelecimento, idRecurso
        );
    }

    // GET - Listar recursos de um estabelecimento
    @GetMapping("/estabelecimento/{idEstabelecimento}")
    public List<EstabelecimentoRecurso> buscarPorEstabelecimento(
            @PathVariable int idEstabelecimento)
            throws SQLException, ClassNotFoundException {

        return associacaoBo.buscarPorEstabelecimentoBo(
                idEstabelecimento
        );
    }

    // POST - Associar recurso
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody EstabelecimentoRecurso associacao)
            throws SQLException, ClassNotFoundException {

        associacaoBo.inserirBo(associacao);

        URI location = URI.create(
                "/estabelecimentos-recursos/" +
                        associacao.getIdEstabelecimento() + "/" +
                        associacao.getIdRecurso()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Substituir recurso da associação
    @PutMapping("/{idEstabelecimento}/{idRecurso}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int idEstabelecimento,
            @PathVariable int idRecurso,
            @RequestBody EstabelecimentoRecurso associacao)
            throws SQLException, ClassNotFoundException {

        if (associacao == null ||
                associacao.getIdRecurso() == null) {

            throw new IllegalArgumentException(
                    "Informe o novo ID do recurso"
            );
        }

        associacaoBo.atualizarBo(
                idEstabelecimento,
                idRecurso,
                associacao.getIdRecurso()
        );

        return ResponseEntity.ok().build();
    }

    // DELETE - Remover associação
    @DeleteMapping("/{idEstabelecimento}/{idRecurso}")
    public ResponseEntity<Void> deletar(
            @PathVariable int idEstabelecimento,
            @PathVariable int idRecurso)
            throws SQLException, ClassNotFoundException {

        associacaoBo.deletarBo(
                idEstabelecimento, idRecurso
        );

        return ResponseEntity.noContent().build();
    }
}
