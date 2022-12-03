package com.aoc2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AocUtils {
    public static List<String> readFile(String fileName) {
        File file = new File("./externalfile/" + fileName + ".txt");

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            while ((st = br.readLine()) != null)
                lines.add(st);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
