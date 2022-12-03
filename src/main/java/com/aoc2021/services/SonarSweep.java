package com.aoc2021.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;

import static com.utils.AoCUtils.readFile;

@Slf4j
public class SonarSweep {

    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("depths");

        int depthIncreases = 0;
        for (int i = 1; i < lines.size(); i++) {
            depthIncreases += Integer.parseInt(lines.get(i - 1)) < Integer.parseInt(lines.get(i)) ? 1 : 0;
        }
        log.info("Times measurements are larger than the previous measurement: " + depthIncreases);

        depthIncreases = 0;
        for (int i = 0; i < lines.size() - 3; i++) {
            int firstThreeDepths = 0, secondThreeDepths = 0;
            for (int j = i; j < i + 3; j++)
                firstThreeDepths += Integer.parseInt(lines.get(j));
            for (int j = i + 1; j < i + 4; j++)
                secondThreeDepths += Integer.parseInt(lines.get(j));
            depthIncreases += firstThreeDepths < secondThreeDepths ? 1 : 0;
        }
        log.info("Times group of 3 measurements are larger than the previous group of 3 measurements: " + depthIncreases);

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }

}
