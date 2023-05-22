package org.ARuiz.TEST;

import org.ARuiz.Model.DAO.StopDAO;
import org.ARuiz.Model.Domain.StopAdmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class TestStopDAO {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bustracker";
        String user = "root";
        String pwd = "";

        try (Connection con = DriverManager.getConnection(url, user, pwd)) {
            StopDAO stopDAO = new StopDAO();
            stopDAO.setConnection(con);

            // Prueba de findAll()
            List<StopAdmin> stops = stopDAO.findAll();
            System.out.println("Paradas encontradas:");
            for (StopAdmin stop : stops) {
                System.out.println(stop.getName());
            }

            // Prueba de findById()
            int id = 1; // ID de parada a buscar
            StopAdmin stopById = stopDAO.findById(id);
            if (stopById != null) {
                System.out.println("Parada encontrada por ID " + id + ": " + stopById.getName());
            } else {
                System.out.println("No se encontró ninguna parada con ID " + id);
            }

            // Prueba de insert()
            String newStopName = "Nueva Parada";
            StopAdmin newStop = new StopAdmin(newStopName);
            StopAdmin insertedStop = stopDAO. insert(newStop);
            System.out.println("Nueva parada insertada: " + insertedStop.getName());

            // Prueba de update()
            int updateId = 1; // ID de parada a actualizar
            String updatedName = "Parada Actualizada";
            StopAdmin stopToUpdate = stopDAO.findById(updateId);
            if (stopToUpdate != null) {
                stopToUpdate.setName(updatedName);
                StopAdmin updatedStop = stopDAO.update(stopToUpdate);
                System.out.println("Parada actualizada: " + updatedStop.getName());
            } else {
                System.out.println("No se encontró ninguna parada con ID " + updateId);
            }

            // Prueba de delete()
            int deleteId = 2; // ID de parada a eliminar
            StopAdmin stopToDelete = stopDAO.findById(deleteId);
            if (stopToDelete != null) {
                stopDAO.delete(stopToDelete);
                System.out.println("Parada eliminada con ID " + deleteId);
            } else {
                System.out.println("No se encontró ninguna parada con ID " + deleteId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

