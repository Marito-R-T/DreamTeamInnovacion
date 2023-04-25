package org.example.controller.mysql;

import org.example.controller.GoogleScholarController;
import org.example.db.MysqlInstance;
import org.example.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSaveAuthorController {

    public DatabaseSaveAuthorController() {

    }

    public void addAuthors(GoogleScholarController scholarController) {
        Connection connection = null;
        try {
            // Cargar la conexión
            connection = MysqlInstance.getInstance().getConnection();
            System.out.println(connection.getAutoCommit());
            // Deshabilitar el autocommit
            connection.setAutoCommit(false);
            System.out.println(connection.getAutoCommit());
            for (Author author : scholarController.getAuthorController().getAuthors()) {
                this.addAuthor(author, connection);
            }

            // Confirmar los cambios
            connection.commit();
            System.out.println(connection.getClientInfo());
        } catch (SQLException e) {
            e.printStackTrace();
            // Revertir los cambios en caso de error
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        } finally {
            // Cerrar la conexión y los recursos
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addAuthor(Author author, Connection connection) throws SQLException {
        // Preparar la consulta
        PreparedStatement statement = connection.prepareStatement(this.queryAddAuthor());
        statement.setString(1, author.getId());
        statement.setString(2, author.getName());
        statement.setInt(3, author.getCitations());
        statement.setString(4, author.getLabel());
        // Ejecutar la consulta
        statement.executeUpdate();
    }

    private String queryAddAuthor() {
        return "INSERT INTO author (id, name, citations, label) VALUES (?, ?, ?, ?)";
    }

}
