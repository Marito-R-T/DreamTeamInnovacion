package org.example.controller.mysql;

import org.example.controller.scholar.GoogleScholarController;
import org.example.db.MysqlInstance;
import org.example.model.Article;
import org.example.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSaveAuthorController {

    public DatabaseSaveAuthorController() {

    }

    public void addAuthors(GoogleScholarController scholarController) {
        Connection connection = null;
        try {
            // Cargar la conexión
            connection = MysqlInstance.getInstance().getConnection();
            // Deshabilitar el autocommit
            connection.setAutoCommit(false);
            for (Author author : scholarController.getAuthorController().getAuthors()) {
                if (!this.existAuthor(author, MysqlInstance.getInstance().getGetsConnection())) {
                    this.addAuthor(author, connection);
                } else {
                    this.updateAuthor(author, connection);
                }
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

    private void updateAuthor(Author author, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(this.queryUpdateAuthor());
        statement.setString(1, author.getName());
        statement.setInt(2, author.getCitations());
        statement.setString(3, author.getLabel());
        statement.setString(4, author.getId());
        statement.executeUpdate();
    }

    private boolean existAuthor(Author author, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(this.queryGetAuthorByID());
        statement.setString(1, author.getId());
        return statement.executeQuery().next();
    }

    private String queryUpdateAuthor() {
        return "UPDATE authors SET name=?, citations=?, label=? WHERE id=?";
    }

    private String queryAddAuthor() {
        return "INSERT INTO authors (id, name, citations, label) VALUES (?, ?, ?, ?)";
    }

    private String queryGetAuthorByID() {
        return "SELECT * FROM authors WHERE id = ?";
    }

}
