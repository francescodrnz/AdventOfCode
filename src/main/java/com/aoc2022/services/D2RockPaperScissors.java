package com.aoc2022.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.utils.AoCUtils.readFile;

@Slf4j
public class D2RockPaperScissors {

    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("rockpaperscissors");

        int totalPoints = 0;
        Map<String, List<String>> combinations = lines.stream().collect(Collectors.groupingBy(s -> s));
        for (Map.Entry<String, List<String>> entry : combinations.entrySet()) {
            String key = entry.getKey();
            int points = 0;
            switch (key.charAt(key.length() - 1)) {
                case 'X': // rock
                    points += 1;
                    switch (key.charAt(0)) {
                        case 'A': // rock
                            points += 3;
                            break;
                        case 'B': // paper
//                            points += 0;
                            break;
                        case 'C': // scissors
                            points += 6;
                            break;
                    }
                    break;
                case 'Y': // paper
                    points += 2;
                    switch (key.charAt(0)) {
                        case 'A': // rock
                            points += 6;
                            break;
                        case 'B': // paper
                            points += 3;
                            break;
                        case 'C': // scissors
//                            points += 0;
                            break;
                    }
                    break;
                case 'Z': // scissors
                    points += 3;
                    switch (key.charAt(0)) {
                        case 'A': // rock
//                            points += 0;
                            break;
                        case 'B': // paper
                            points += 6;
                            break;
                        case 'C': // scissors
                            points += 3;
                            break;
                    }
                    break;
            }
            totalPoints += points * entry.getValue().size();
        }
        log.info("total points: " + totalPoints);

        totalPoints = 0;
        for (Map.Entry<String, List<String>> entry : combinations.entrySet()) {
            String key = entry.getKey();
            int points = 0;
            switch (key.charAt(key.length() - 1)) {
                case 'X': // lose
//                    points += 0;
                    switch (key.charAt(0)) {
                        case 'A': // rock
                            points += 3;
                            break;
                        case 'B': // paper
                            points += 1;
                            break;
                        case 'C': // scissors
                            points += 2;
                            break;
                    }
                    break;
                case 'Y': // draw
                    points += 3;
                    switch (key.charAt(0)) {
                        case 'A': // rock
                            points += 1;
                            break;
                        case 'B': // paper
                            points += 2;
                            break;
                        case 'C': // scissors
                            points += 3;
                            break;
                    }
                    break;
                case 'Z': // win
                    points += 6;
                    switch (key.charAt(0)) {
                        case 'A': // rock
                            points += 2;
                            break;
                        case 'B': // paper
                            points += 3;
                            break;
                        case 'C': // scissors
                            points += 1;
                            break;
                    }
                    break;
            }
            totalPoints += points * entry.getValue().size();
        }
        log.info("total points (second logic): " + totalPoints);

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));

    }
}
