package com.aoc2022.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.utils.AoCUtils.readFile;

@Slf4j
public class D4CampCleanup {
    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("campcleanup");

        AtomicInteger rangeCounter = new AtomicInteger(0);
        for (String line : lines) {
            String interval1 = line.split(",")[0];
            String interval2 = line.split(",")[1];
            if (checkInterval(interval1, interval2))
                rangeCounter.addAndGet(1);
        }
        log.info("pairs in which one range contains the other: " + rangeCounter.get());

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }

    private static boolean checkInterval(String interval1, String interval2) {
        int in1id1 = Integer.parseInt(interval1.split("-")[0]);
        int in1id2 = Integer.parseInt(interval1.split("-")[1]);
        int in2id1 = Integer.parseInt(interval2.split("-")[0]);
        int in2id2 = Integer.parseInt(interval2.split("-")[1]);
        if (in1id1 <= in2id1 && in2id2 <= in1id2)
            return true;
        else if (in2id1 <= in1id1 && in1id2 <= in2id2)
            return true;
        return false;
    }
}


