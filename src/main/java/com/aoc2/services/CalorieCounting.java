package com.aoc2.services;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CalorieCounting {
    static final String ELF = "elf";

    public static void main(String[] args) {
        File file = new File("./externalfile/calories.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            Map<String, Integer> elvesMap = new HashMap<>();
            AtomicInteger counter = new AtomicInteger(0);
            elvesMap.put(ELF + counter, 0);
            while ((st = br.readLine()) != null) {
                if (!"".equals(st)) {
                    elvesMap.put(ELF + counter, elvesMap.get(ELF + counter) + Integer.parseInt(st));
                } else {
                    counter.addAndGet(1);
                    elvesMap.put(ELF + counter, 0);
                }
            }
            int maxCalories = elvesMap.values().stream().mapToInt(i -> i).max().getAsInt();
            log.info("maximum calories: " + maxCalories);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
