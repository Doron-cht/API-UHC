package fr.doron.dcuhc.ressources;

import com.google.gson.*;
import org.bukkit.ChatColor;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DescriptionManager {

    private static JsonObject root;

    static {
        try (Reader reader = new FileReader("descriptions.json")) {
            Gson gson = new Gson();
            root = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getColored(String key) {
        String raw = root.getAsJsonObject("Description").get(key).getAsString();
        return ChatColor.translateAlternateColorCodes('&', raw);
    }
}