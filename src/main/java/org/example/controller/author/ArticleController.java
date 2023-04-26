package org.example.controller.author;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.model.Article;
import org.example.model.Author;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    private List<Article> articles = new ArrayList<>();

    public List<Article> manageArticles(JsonElement array, Author author) {
        List<Article> articles = new ArrayList<>();
        for (JsonElement article : array.getAsJsonArray()) {
            JsonObject articleObject = article.getAsJsonObject();
            JsonElement publication = articleObject.get("publication");
            Article reference = new Article(
                    articleObject.get("title").getAsString(),
                    (publication != null) ? publication.getAsString() : null,
                    articleObject.get("citation_id").getAsString(),
                    author,
                    Year.of(Integer.parseInt(articleObject.get("year").getAsString()))
            );
            articles.add(reference);
        }
        this.articles = articles;
        return this.articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

}
