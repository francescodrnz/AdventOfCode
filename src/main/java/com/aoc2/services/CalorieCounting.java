package com.aoc2.services;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CalorieCounting {
    static final String ELF = "elf";

    public static void main(String[] args) {

        Instant start = Instant.now();

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

            int top3CaloriesSum = elvesMap.values().stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).limit(3).mapToInt(i -> i).sum();
            log.info("sum of top three calories: " + top3CaloriesSum);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.info("elapsed time (ms): "+ (Instant.now().toEpochMilli() - start.toEpochMilli()));
        }
    }
}
