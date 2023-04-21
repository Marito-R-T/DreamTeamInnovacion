package org.example.controller;

import com.google.gson.*;
import org.example.controller.author.AuthorController;
import org.example.model.Author;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleScholarController {

    private AuthorController authorController;

    public GoogleScholarController() {
        this.authorController = new AuthorController();
    }

    public String searchProfileByLabel(String label) {
        Map<String, String> parameter = new HashMap<>();

        parameter.put("engine", "google_scholar_profiles");
        parameter.put("mauthors", "label:"+label);
        parameter.put("api_key", "d6b24ff15bc3bcd7a0d7f15b60db34552a57a41adeded06f7c1d3da627c955f2");

        GoogleSearch search = new GoogleSearch(parameter);

        try
        {
            JsonObject results = search.getJson();
            JsonElement profiles = results.get("profiles");
            System.out.println(profiles);
            List<Author> authors = this.authorController.manageAuthors(profiles);

            return this.toJson(authors);
        }
        catch (SerpApiSearchException ex)
        {
            System.out.println("Exception:");
            System.out.println(ex.toString());
            return ex.toString();
        }
    }

    private static String format(JsonElement json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(json);
        JsonElement je = JsonParser.parseString(jsonString);
        return gson.toJson(je);
    }

    private String toJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(object);
        JsonElement je = JsonParser.parseString(jsonString);
        return gson.toJson(je);
    }

}
