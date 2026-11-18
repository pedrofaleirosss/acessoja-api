package br.com.acessoja.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    private static final String URL =
            "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public Connection conexao()
            throws SQLException, ClassNotFoundException {

        String usuario = System.getenv("DB_USUARIO");
        String senha = System.getenv("DB_SENHA");

        if (usuario == null || usuario.isBlank()
                || senha == null || senha.isBlank()) {

            throw new IllegalStateException(
                    "Configure DB_USUARIO e DB_SENHA"
            );
        }

        Class.forName("oracle.jdbc.OracleDriver");

        return DriverManager.getConnection(
                URL, usuario, senha
        );
    }
}
