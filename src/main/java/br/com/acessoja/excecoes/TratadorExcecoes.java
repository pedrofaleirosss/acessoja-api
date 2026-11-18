
package br.com.acessoja.excecoes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorExcecoes {

    // Erros de validação
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> erroValidacao(
            IllegalArgumentException e) {

        return ResponseEntity.badRequest().body(
                Map.of("erro", e.getMessage())
        );
    }

    // Registro não encontrado
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> naoEncontrado(
            NoSuchElementException e) {

        return ResponseEntity.status(404).body(
                Map.of("erro", e.getMessage())
        );
    }

    // Erros do banco Oracle
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, String>> erroBanco(
            SQLException e) {

        int codigo = e.getErrorCode();

        // Restrição de integridade
        if (codigo == 1 || codigo == 2291 ||
                codigo == 2292 || codigo == 2290) {

            return ResponseEntity.status(409).body(
                    Map.of("erro",
                            "Operação viola uma restrição do banco")
            );
        }

        return ResponseEntity.status(500).body(
                Map.of("erro", "Erro ao acessar o banco Oracle")
        );
    }

    // Driver JDBC não encontrado
    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<Map<String, String>> erroDriver(
            ClassNotFoundException e) {

        return ResponseEntity.status(500).body(
                Map.of("erro", "Driver Oracle não encontrado")
        );
    }

    // Configuração inválida
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> erroConfiguracao(
            IllegalStateException e) {

        return ResponseEntity.status(500).body(
                Map.of("erro", "Erro na configuração da API")
        );
    }
}
