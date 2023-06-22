package org.ARuiz.TEST;

import org.ARuiz.Model.DAO.StopDAO;
import org.ARuiz.Model.DAO.LineDAO;
import org.ARuiz.Model.Domain.Stop;
import org.ARuiz.Model.Domain.Line;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class TestStopDAO {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bustracker";
        String user = "root";
        String pwd = "";

        try (Connection con = DriverManager.getConnection(url, user, pwd)) {
            StopDAO stopDAO = new StopDAO(con);
            LineDAO lineDAO = new LineDAO(con);


            // Prueba de findAll()
            List<Stop> stops = stopDAO.findAll();
            System.out.println("Paradas encontradas:");
            for (Stop stop : stops) {
                System.out.println("ID: " + stop.getId_stop() + ", Nombre: " + stop.getName());
            }

            System.out.println();

            // Prueba de findById()
            int id = 1; // ID de parada a buscar
            Stop stopById = stopDAO.findById(id);
            if (stopById != null) {
                System.out.println("Parada encontrada por ID " + id + ": " + stopById.getName());
            } else {
                System.out.println("No se encontró ninguna parada con ID " + id);
            }

            // Prueba de findLinesByStop()
            int stopId = 1; // ID de la parada para buscar las líneas
            List<Line> linesByStop = lineDAO.findLinesByStop(stopId);
            if (!linesByStop.isEmpty()) {
                System.out.println("Líneas que pasan por la parada con ID " + stopId + ":");
                for (Line line : linesByStop) {
                    System.out.println("ID: " + line.getId_bus() + ", Nombre: " + line.getName() + ", Plazas: " + line.getPlace());
                }
            } else {
                System.out.println("No se encontraron líneas que pasen por la parada con ID " + stopId);
            }

            /*
            // Prueba de getAllStopNames()
            List<String> allStopNames = StopDAO.getAllStopNames();
            for (String stopName : allStopNames) {
                System.out.println(stopName);
            } */

            // Prueba de findStopsByLine()
            int lineId = 1; // ID de la línea para buscar las paradas
            List<Stop> stopsByLine = stopDAO.findStopsByLine(lineId);
            if (!stopsByLine.isEmpty()) {
                System.out.println("Paradas asociadas a la línea con ID " + lineId + ":");
                for (Stop stop : stopsByLine) {
                    System.out.println("ID: " + stop.getId_stop() + ", Nombre: " + stop.getName());
                }
            } else {
                System.out.println("No se encontraron paradas asociadas a la línea con ID " + lineId);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


