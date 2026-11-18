
package br.com.acessoja.main;

import br.com.acessoja.dao.BarreiraDAO;
import br.com.acessoja.entities.Barreira;

import java.sql.SQLException;
import java.util.List;

public class TesteSelecionarBarreiras {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        BarreiraDAO barreiraDAO = new BarreiraDAO();

        List<Barreira> barreiras = barreiraDAO.selecionar();

        for (Barreira barreira : barreiras) {

            System.out.println("-------------------------");
            System.out.println("ID: " + barreira.getIdRegistro());
            System.out.println("Título: " + barreira.getTitulo());
            System.out.println("Tipo: " + barreira.getTipoBarreira());
            System.out.println("Endereço: " + barreira.getEndereco());
            System.out.println("Status: " + barreira.getStatusBarreira());
        }
    }
}
