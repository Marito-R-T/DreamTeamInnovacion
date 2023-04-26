package org.example.controller.mysql;

import org.example.controller.GoogleScholarController;
import org.example.db.MysqlInstance;
import org.example.model.Article;
import org.example.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.JDBCType;
import java.time.LocalDate;

public class DatabaseSaveArticleController {

    public DatabaseSaveArticleController() {

    }
    public void addArticles(GoogleScholarController scholarController) {
        Connection connection = null;
        try {
            // Cargar la conexión
            connection = MysqlInstance.getInstance().getConnection();
            System.out.println(connection.getAutoCommit());
            // Deshabilitar el autocommit
            connection.setAutoCommit(false);
            System.out.println(connection.getAutoCommit());
            for (Article author : scholarController.getArticleController().getArticles()) {
                this.addArticle(author, connection);
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

    private void addArticle(Article article, Connection connection) throws SQLException {
        // Preparar la consulta
        PreparedStatement statement = connection.prepareStatement(this.queryAddArticle());
        statement.setString(1, article.getId());
        statement.setString(2, article.getTitle());
        statement.setString(3, article.getAuthor().getId());
        statement.setString(4, article.getPublication());
        statement.setInt(5, article.getYear().getValue());
        // Ejecutar la consulta
        statement.executeUpdate();
    }

    private String queryAddArticle() {
        return "INSERT INTO articles (id, title, author, publication, year) VALUES (?, ?, ?, ?, ?)";
    }

}
