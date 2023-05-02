package org.example.controller.mysql;

import org.example.controller.scholar.GoogleScholarController;
import org.example.db.MysqlInstance;
import org.example.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSaveArticleController {

    public DatabaseSaveArticleController() {

    }
    public void addArticles(GoogleScholarController scholarController) {
        Connection connection = null;
        try {
            // Cargar la conexión
            connection = MysqlInstance.getInstance().getConnection();
            // Deshabilitar el autocommit
            connection.setAutoCommit(false);
            for (Article article : scholarController.getArticleController().getArticles()) {
                if(!this.existArticle(article, MysqlInstance.getInstance().getGetsConnection())) {
                    this.addArticle(article, connection);
                } else {
                    this.updateArticle(article, connection);
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

    private void updateArticle(Article article, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(this.queryUpdateArticle());
        statement.setString(1, article.getTitle());
        statement.setString(2, article.getAuthor().getId());
        statement.setString(3, article.getPublication());
        statement.setInt(4, article.getYear().getValue());
        statement.setString(5, article.getId());
        statement.executeUpdate();
    }

    private boolean existArticle(Article article, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(this.queryGetArticleByID());
        statement.setString(1, article.getId());
        return statement.executeQuery().next();
    }

    private String queryUpdateArticle() {
        return "UPDATE articles SET title=?, author=?, publication=?, year=? WHERE id=?";
    }

    private String queryAddArticle() {
        return "INSERT INTO articles (id, title, author, publication, year) VALUES (?, ?, ?, ?, ?)";
    }

    private String queryGetArticleByID() {
        return "SELECT * FROM articles WHERE id = ?";
    }

}
