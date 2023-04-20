package org.example;

import com.google.gson.JsonObject;
import org.example.serpapi.GoogleSearch;
import org.example.serpapi.SerpApiSearchException;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> parameter = new HashMap<>();

        parameter.put("engine", "google_scholar");
        parameter.put("q", "biology");
        parameter.put("api_key", "d6b24ff15bc3bcd7a0d7f15b60db34552a57a41adeded06f7c1d3da627c955f2");

        GoogleSearch search = new GoogleSearch(parameter);

        try
        {
            JsonObject results = search.getJson();
            var organic_results = results.get("organic_results");
            System.out.println(organic_results);
        }
        catch (SerpApiSearchException ex)
        {
            System.out.println("Exception:");
            System.out.println(ex.toString());
        }
    }
}