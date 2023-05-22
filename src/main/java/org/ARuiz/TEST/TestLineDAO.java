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

        try (Connection con = DriverManager.getConnection(url, user, pwd)) {

            LineDAO lineDAO = new LineDAO(con);

            // Crear y insertar una nueva línea
            Line newLine1 = createLine("Línea 1", 1, "Ruta 1", LocalTime.of(10, 0));
            Line insertedLine1 = insertLine(lineDAO, newLine1);
            System.out.println("Línea insertada 1: " + insertedLine1);

            // Imprimir un párrafo en blanco
            System.out.println();

            // Crear y insertar otra línea
            Line newLine2 = createLine("Línea 2", 2, "Ruta 2", LocalTime.of(12, 0));
            Line insertedLine2 = insertLine(lineDAO, newLine2);
            System.out.println("Línea insertada 2: " + insertedLine2);

            // Imprimir un párrafo en blanco
            System.out.println();

            // Obtener todas las líneas
            List<Line> allLines = lineDAO.findAll();
            System.out.println("Lista de líneas:");
            for (Line line : allLines) {
                System.out.println(line);

            }

            // Imprimir un párrafo en blanco
            System.out.println();

            // Obtener una línea por su ID
            int lineId = 10;
            Line lineById = lineDAO.findById(Integer.valueOf(lineId));
            if (lineById != null) {
                System.out.println("Línea encontrada por ID: " + lineById);
            } else {
                System.out.println("No se encontró ninguna línea con el ID: " + lineId);
            }

            // Imprimir un párrafo en blanco
            System.out.println();

            // Actualizar la primera línea
            updateLine(lineById, "Línea 1 actualizada", 3, "Ruta 1 actualizada", LocalTime.of(15, 0));
            Line updatedLine1 = lineDAO.update(lineById);
            System.out.println("Línea actualizada 1: " + updatedLine1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Line createLine(String lineName, int place, String route, LocalTime timetable) {
        Line line = new Line();
        line.setLine_name(lineName);
        line.setPlace(place);
        line.setRoute(route);
        line.setTimetable(timetable);
        return line;
    }

    private static Line insertLine(LineDAO lineDAO, Line line) throws SQLException {
        Line insertedLine = lineDAO.insert(line);
        System.out.println();
        return insertedLine;
    }

    private static void updateLine(Line line, String lineName, int place, String route, LocalTime timetable) {
        line.setLine_name(lineName);
        line.setPlace(place);
        line.setRoute(route);
        line.setTimetable(timetable);
    }
}



