
package br.com.acessoja.controller;

import br.com.acessoja.bo.BairroBo;
import br.com.acessoja.entities.Bairro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bairros")
public class BairroController {

    BairroBo bairroBo = new BairroBo();

    // GET - Listar todos
    @GetMapping
    public List<Bairro> selecionar()
            throws SQLException, ClassNotFoundException {

        return bairroBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public Bairro buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return bairroBo.buscarPorIdBo(id);
    }

    // GET - Buscar por cidade
    @GetMapping("/cidade/{idCidade}")
    public List<Bairro> buscarPorCidade(@PathVariable int idCidade)
            throws SQLException, ClassNotFoundException {

        return bairroBo.buscarPorCidadeBo(idCidade);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody Bairro bairro)
            throws SQLException, ClassNotFoundException {

        bairroBo.inserirBo(bairro);

        URI location = URI.create(
                "/bairros/" + bairro.getIdBairro()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Bairro bairro)
            throws SQLException, ClassNotFoundException {

        bairro.setIdBairro(id);
        bairroBo.atualizarBo(bairro);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        bairroBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
