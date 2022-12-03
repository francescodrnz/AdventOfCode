package com.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class AoCUtils {
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

    public static int askDay() {
        System.out.print("Choose the day to run: ");
        return new Scanner(System.in).nextInt();
    }

    public static int askContinue() {
        System.out.print("Run another day? (0 to exit): ");
        return new Scanner(System.in).nextInt();
    }
}
