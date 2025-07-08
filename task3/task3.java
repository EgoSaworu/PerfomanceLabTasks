package org.example;

import org.json.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите путь к файлу values.json:");
            String valuesPath = scanner.nextLine();

            System.out.println("Введите путь к файлу tests.json:");
            String testsPath = scanner.nextLine();

            System.out.println("Введите путь для сохранения report.json:");
            String reportPath = scanner.nextLine();

            JSONObject values = new JSONObject(new String(Files.readAllBytes(Paths.get(valuesPath))));
            JSONObject tests = new JSONObject(new String(Files.readAllBytes(Paths.get(testsPath))));

            JSONArray valuesArray = values.getJSONArray("values");
            Map<Integer, String> valueMap = new HashMap<>();
            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject item = valuesArray.getJSONObject(i);
                valueMap.put(item.getInt("id"), item.getString("value"));
            }

            processNode(tests, valueMap);
            Files.write(Paths.get(reportPath), tests.toString(2).getBytes());

            System.out.println("Отчет успешно сохранен в " + reportPath);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
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