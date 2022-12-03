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

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }

}
