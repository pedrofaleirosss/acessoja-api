
package br.com.acessoja.main;

import br.com.acessoja.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection conexao =
                     new ConexaoFactory().conexao()) {

            System.out.println("Conectado com o Banco de Dados Oracle!");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro na conexão: " + e.getMessage());

        } catch (IllegalStateException e) {
            System.out.println("Erro de configuração: " + e.getMessage());
        }
    }
}
