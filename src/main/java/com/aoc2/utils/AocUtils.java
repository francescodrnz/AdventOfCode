package com.aoc2.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AocUtils {
    public static List<String> readFile(String fileName) {
        File file = new File("./externalfile/" + fileName + ".txt");

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            while ((st = br.readLine()) != null)
                lines.add(st);
        } catch (Exception e) {
            log.info("Error while reading the file");
        }
        return lines;
    }
}
