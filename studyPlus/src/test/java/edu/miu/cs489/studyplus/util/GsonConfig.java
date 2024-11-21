package edu.miu.cs489.studyplus.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class GsonConfig {
    public static Gson createGsonWithLocalDateSupport() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()) // Register the adapter
                .create();
    }
}
