package com.aoc2.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.aoc2.utils.AocUtils.readFile;

@Slf4j
public class RucksackReorganization {
    static final String ALPHABET = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {

        Instant start = Instant.now();

        List<String> lines = readFile("rucksacks");

        int sumOfPriorities = 0;
        for (String line : lines) {
            String firstHalf = line.substring(0, line.length() / 2);
            String secondHalf = line.substring(line.length() / 2);
            Set<Character> duplicatesSet = new HashSet<>();
            firstHalf.chars().forEach(i -> {
                if (secondHalf.indexOf((char) i) >= 0)
                    duplicatesSet.add((char) i);
            });
            sumOfPriorities += duplicatesSet.stream().mapToInt(ALPHABET::indexOf).sum();
        }
        log.info("sum of duplicates priorities: " + sumOfPriorities);

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));

    }
}
