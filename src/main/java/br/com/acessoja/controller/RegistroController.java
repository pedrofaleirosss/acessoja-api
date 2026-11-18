
package br.com.acessoja.controller;

import br.com.acessoja.bo.RegistroBo;
import br.com.acessoja.entities.Registro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    RegistroBo registroBo = new RegistroBo();

    // GET - Listar todos
    @GetMapping
    public List<Registro> selecionar()
            throws SQLException, ClassNotFoundException {

        return registroBo.selecionarBo();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public Registro buscarPorId(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        return registroBo.buscarPorIdBo(id);
    }

    // GET - Buscar por tipo
    @GetMapping("/tipo/{tipo}")
    public List<Registro> buscarPorTipo(@PathVariable String tipo)
            throws SQLException, ClassNotFoundException {

        return registroBo.buscarPorTipoBo(tipo);
    }

    // POST - Inserir
    @PostMapping
    public ResponseEntity<Void> inserir(
            @RequestBody Registro registro)
            throws SQLException, ClassNotFoundException {

        registroBo.inserirBo(registro);

        URI location = URI.create(
                "/registros/" + registro.getIdRegistro()
        );

        return ResponseEntity.created(location).build();
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Registro registro)
            throws SQLException, ClassNotFoundException {

        registro.setIdRegistro(id);
        registroBo.atualizarBo(registro);

        return ResponseEntity.ok().build();
    }

    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id)
            throws SQLException, ClassNotFoundException {

        registroBo.deletarBo(id);

        return ResponseEntity.noContent().build();
    }
}
