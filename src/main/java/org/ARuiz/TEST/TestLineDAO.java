package org.ARuiz.TEST;

import org.ARuiz.Model.DAO.LineDAO;
import org.ARuiz.Model.Domain.Line;
import org.ARuiz.Model.Domain.Stop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TestLineDAO {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bustracker";
        String user = "root";
        String pwd = "";

        try (Connection con = DriverManager.getConnection(url, user, pwd)) {
            LineDAO lineDAO = new LineDAO(con);

            // Prueba del método findAll
            List<Line> lines = lineDAO.findAll();

            System.out.println("Líneas encontradas:");
            for (Line line : lines) {
                System.out.println("ID: " + line.getId_bus() + ", Nombre: " + line.getName() + ", Plazas: " + line.getPlace());

                List<Stop> stops = line.getStops();
                if (!stops.isEmpty()) {
                    System.out.println("Paradas asociadas a la línea:");
                    for (Stop stop : stops) {
                        System.out.println("ID: " + stop.getId_stop() + ", Nombre: " + stop.getName());
                    }
                } else {
                    System.out.println("No hay paradas asociadas a la línea.");
                }

                System.out.println("------------------------");
            }

            // Prueba del método findById
            int idToFind = 1;
            Line lineById = lineDAO.findById(idToFind);

            if (lineById != null) {
                System.out.println("Línea encontrada con ID " + idToFind + ":");
                System.out.println("ID: " + lineById.getId_bus() + ", Nombre: " + lineById.getName() + ", Plazas: " + lineById.getPlace());

                List<Stop> stops = lineById.getStops();
                if (!stops.isEmpty()) {
                    System.out.println("Paradas asociadas a la línea:");
                    for (Stop stop : stops) {
                        System.out.println("ID: " + stop.getId_stop() + ", Nombre: " + stop.getName());
                    }
                } else {
                    System.out.println("No hay paradas asociadas a la línea.");
                }
            } else {
                System.out.println("No se encontró ninguna línea con el ID " + idToFind + ".");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}







