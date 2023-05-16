package org.ARuiz.TEST;

import org.ARuiz.Model.DAO.LineDAO;
import org.ARuiz.Model.Domain.Line;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class TestLineDAO {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bustracker";
        String user = "root";
        String pwd = "";

        try (Connection connection = DriverManager.getConnection(url, user, pwd)) {

            LineDAO lineDAO = new LineDAO(connection);
            /*
            // Crear una nueva línea
            Line newLine1 = new Line();
            newLine1.setLine_name("Línea 1");
            newLine1.setPlace(1);
            newLine1.setRoute("Ruta 1");
            newLine1.setTimetable(LocalTime.of(10, 0));

            // Insertar la nueva línea
            Line insertedLine1 = lineDAO.insert(newLine1);
            System.out.println("Línea insertada 1: " + insertedLine1);

            // Crear otra línea
            Line newLine2 = new Line();
            newLine2.setLine_name("Línea 2");
            newLine2.setPlace(2);
            newLine2.setRoute("Ruta 2");
            newLine2.setTimetable(LocalTime.of(12, 0));

            // Insertar la otra línea
            Line insertedLine2 = lineDAO.insert(newLine2);
            System.out.println("Línea insertada 2: " + insertedLine2);

            // Obtener todas las líneas
            List<Line> allLines = lineDAO.findAll();
            System.out.println("Lista de líneas:");
            for (Line line : allLines) {
                System.out.println(line);
            }

            /*
            // Obtener una línea por su ID
            int lineId = 1;
            Line lineById = lineDAO.findById(String.valueOf(lineId));
            if (lineById != null) {
                System.out.println("Línea encontrada por ID: " + lineById);
            } else {
                System.out.println("No se encontró ninguna línea con el ID: " + lineId);
            }
*/
            int lineId = 3;
            Line lineById = lineDAO.findById(String.valueOf(lineId));

            // Actualizar la primera línea
            lineById.setLine_name("Línea 1 actualizada");
            lineById.setPlace(3);
            lineById.setRoute("Ruta 1 actualizada");
            lineById.setTimetable(LocalTime.of(15, 0)); // Establecer el nuevo horario
            Line updatedLine1 = lineDAO.update(lineById);
            System.out.println("Línea actualizada 1: " + updatedLine1);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


