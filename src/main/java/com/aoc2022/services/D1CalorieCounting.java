package com.aoc2022.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.utils.AoCUtils.readFile;

@Slf4j
@Service
public class D1CalorieCounting {
    static final String ELF = "elf";

    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("calories");

        Map<String, Integer> elvesMap = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(0);
        elvesMap.put(ELF + counter, 0);
        lines.forEach(line -> {
            if (!"".equals(line)) {
                elvesMap.put(ELF + counter, elvesMap.get(ELF + counter) + Integer.parseInt(line));
            } else {
                counter.addAndGet(1);
                elvesMap.put(ELF + counter, 0);
            }
        });

        int maxCalories = elvesMap.values().stream().mapToInt(i -> i).max().getAsInt();
        log.info("maximum calories: " + maxCalories);

        int top3CaloriesSum = elvesMap.values().stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).limit(3).mapToInt(i -> i).sum();
        log.info("sum of top three calories: " + top3CaloriesSum);

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }
}
