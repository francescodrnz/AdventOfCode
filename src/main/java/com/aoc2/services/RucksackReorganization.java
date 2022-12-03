package com.aoc2.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.ArrayList;
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

        List<String> list = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 3) {
            String[] words = {lines.get(i), lines.get(i + 1), lines.get(i + 2)};
            char[] c = words[0].toCharArray();
            Set<String> set = new HashSet<>();
            for (char value : c) {
                if (isContains(value, words)) set.add(String.valueOf(value));
            }
            list.addAll(set);
        }
        int tot = 0;
        for (String c : list) {
            tot += ALPHABET.indexOf(c);
        }
        log.info("total priorities for badges: " + tot);


        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));

    }

    private static boolean isContains(char c1, String[] words) {
        for (int i = 1; i < words.length; i++) {
            if (!words[i].contains(String.valueOf(c1))) return false;
            else words[i] = words[i].replaceFirst(String.valueOf(c1), "@");
        }
        return true;
    }
}
