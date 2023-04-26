package org.example.controller.author;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorController {
    private List<Author> authors = new ArrayList<>();

    public AuthorController() {
    }

    public List<Author> manageAuthors(JsonElement array, String label) {
        List<Author> authors = new ArrayList<>();
        for (JsonElement author : array.getAsJsonArray()) {
            JsonObject authorObject = author.getAsJsonObject();
            Author reference = new Author(
                    authorObject.get("name").getAsString(),
                    authorObject.get("cited_by").getAsInt(),
                    authorObject.get("author_id").getAsString(),
                    label
            );
            authors.add(reference);
        }
        this.authors = authors;
        return this.authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
