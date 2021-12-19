package com.latitude.genoapay.codingchallenge.dto.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime time) throws IOException {
        if(time == null) {
            jsonWriter.nullValue();
            return;
        }
        try {
            String timeString = time.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
            jsonWriter.value(timeString);
        } catch (DateTimeException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        try {
            return LocalDateTime.parse(jsonReader.nextString(), DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        } catch (DateTimeParseException exception) {
            throw new IOException("Malformed date time: " + exception.getMessage());
        }
    }
}
