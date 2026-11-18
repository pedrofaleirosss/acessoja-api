
package br.com.acessoja.controller;

import br.com.acessoja.bo.ValidacaoBo;
import br.com.acessoja.entities.Validacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/validacoes")
public class ValidacaoController {

    ValidacaoBo validacaoBo = new ValidacaoBo();

    // GET - Listar todas
    @GetMapping
    public List<Validacao> selecionar()
            throws SQLException, ClassNotFoundException {

        return validacaoBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public Validacao buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return validacaoBo.buscarPorIdBo(id);
    }

    // GET - Buscar validações de um registro
    @GetMapping("/registro/{idRegistro}")
    public List<Validacao> buscarPorRegistro(
            @PathVariable int idRegistro)
            throws SQLException, ClassNotFoundException {

        return validacaoBo.buscarPorRegistroBo(idRegistro);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody Validacao validacao)
            throws SQLException, ClassNotFoundException {

        validacaoBo.inserirBo(validacao);

        URI location = URI.create(
                "/validacoes/" + validacao.getIdValidacao()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Validacao validacao)
            throws SQLException, ClassNotFoundException {

        validacao.setIdValidacao(id);
        validacaoBo.atualizarBo(validacao);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        validacaoBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
