package org.example.adapter;

import java.io.IOException;
import java.time.Year;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class YearAdapter extends TypeAdapter<Year> {

    @Override
    public void write(JsonWriter out, Year value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public Year read(JsonReader in) throws IOException {
        String yearString = in.nextString();
        return Year.parse(yearString);
    }
}