package com.aoc2022.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.utils.AoCUtils.readFile;

@Slf4j
public class D6TuningTrouble {
    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("datastreambuffer");

        for (String line : lines) {
            log.info("index of the first group of 4 unique characters: "+ getFirstGroupOfNUniqueChars(line, 4));
            log.info("index of the first group of 14 unique characters: "+ getFirstGroupOfNUniqueChars(line, 14));
        }

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }

    private static int getFirstGroupOfNUniqueChars(String input, int n) {
        // Edge case: input string is empty or has less than 4 characters
        if (input == null || input.length() < n) {
            return -1;
        }

        // Iterate over the input string, starting from the first character
        for (int i = 0; i < input.length() - n - 1; i++) {
            // Check if the current group of 4 characters is unique
            if (isUnique(input, i, n)) {
                return i + n; // return the index of the last character of the group
            }
        }

        // If no group of 4 unique consecutive characters was found, return -1
        return -1;
    }

    private static boolean isUnique(String input, int startIndex, int n) {
        // Create a set to store the characters in the current group
        Set<Character> set = new HashSet<>();

        // Iterate over the current group of 4 characters
        for (int i = startIndex; i < startIndex + n; i++) {
            // If the current character is already in the set, return false
            if (set.contains(input.charAt(i))) {
                return false;
            }

            // Otherwise, add the current character to the set
            set.add(input.charAt(i));
        }

        // If all characters are unique, return true
        return true;
    }
}
