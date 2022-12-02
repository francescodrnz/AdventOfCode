package com.aoc2.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.aoc2.utils.AocUtils.readFile;

@Slf4j
public class RockPaperScissors {

    public static void main(String[] args) {

        Instant start = Instant.now();

        List<String> lines = readFile("./externalfile/rockpaperscissors.txt");

        int totalPoints = 0;
        Map<String, List<String>> combinations = lines.stream().collect(Collectors.groupingBy(s -> s));
        for (Map.Entry<String, List<String>> entry : combinations.entrySet()) {
            String key = entry.getKey();
            int points = 0;
            switch (key.charAt(key.length() - 1)) {
                case 'X':
                    points += 1;
                    switch (key.charAt(0)) {
                        case 'A':
                            points += 3;
                            break;
                        case 'B':
//                            points += 0;
                            break;
                        case 'C':
                            points += 6;
                            break;
                    }
                    break;
                case 'Y':
                    points += 2;
                    switch (key.charAt(0)) {
                        case 'A':
                            points += 6;
                            break;
                        case 'B':
                            points += 3;
                            break;
                        case 'C':
//                            points += 0;
                            break;
                    }
                    break;
                case 'Z':
                    points += 3;
                    switch (key.charAt(0)) {
                        case 'A':
//                            points += 0;
                            break;
                        case 'B':
                            points += 6;
                            break;
                        case 'C':
                            points += 3;
                            break;
                    }
                    break;
            }
            totalPoints += points * entry.getValue().size();
        }
        log.info("total points: " + totalPoints);

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));

    }
}
