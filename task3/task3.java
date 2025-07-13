package org.example;

import org.json.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class task3 {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Необходимо указать три аргумента: values.json tests.json report.json");
            return;
        }

        try {
            JSONObject values = new JSONObject(new String(Files.readAllBytes(Paths.get(args[0]))));
            JSONObject tests = new JSONObject(new String(Files.readAllBytes(Paths.get(args[1]))));

            JSONArray valuesArray = values.getJSONArray("values");
            Map<Integer, String> valueMap = new HashMap<>();
            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject item = valuesArray.getJSONObject(i);
                valueMap.put(item.getInt("id"), item.getString("value"));
            }

            processNode(tests, valueMap);
            Files.write(Paths.get(args[2]), tests.toString(2).getBytes());

            System.out.println("Отчет успешно сохранен в " + args[2]);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void processNode(JSONObject node, Map<Integer, String> valueMap) {
        if (node.has("id") && node.has("value") && valueMap.containsKey(node.getInt("id"))) {
            node.put("value", valueMap.get(node.getInt("id")));
        }
        if (node.has("values")) {
            JSONArray children = node.getJSONArray("values");
            for (int i = 0; i < children.length(); i++) {
                processNode(children.getJSONObject(i), valueMap);
            }
        }
    }
}
