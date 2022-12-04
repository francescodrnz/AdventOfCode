package com.aoc2022.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.utils.AoCUtils.readFile;

@Slf4j
public class D4CampCleanup {
    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("campcleanup");

        AtomicInteger fullOverlapCounter = new AtomicInteger(0);
        AtomicInteger anyOverlapCounter = new AtomicInteger(0);
        for (String line : lines) {
            String interval1 = line.split(",")[0];
            String interval2 = line.split(",")[1];
            if (checkIntervalFullOverlap(interval1, interval2))
                fullOverlapCounter.addAndGet(1);
            if (checkIntervalAnyOverlap(interval1, interval2))
                anyOverlapCounter.addAndGet(1);
        }
        log.info("pairs in which one range contains the other: " + fullOverlapCounter.get());
        log.info("pairs in which ranges overlap: " + anyOverlapCounter.get());

        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }

    private static boolean checkIntervalFullOverlap(String interval1, String interval2) {
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

    private static boolean checkIntervalAnyOverlap(String interval1, String interval2) {
        int in1id1 = Integer.parseInt(interval1.split("-")[0]);
        int in1id2 = Integer.parseInt(interval1.split("-")[1]);
        List<Integer> range1 = IntStream.rangeClosed(in1id1, in1id2).boxed().collect(Collectors.toList());
        int in2id1 = Integer.parseInt(interval2.split("-")[0]);
        int in2id2 = Integer.parseInt(interval2.split("-")[1]);
        List<Integer> range2 = IntStream.rangeClosed(in2id1, in2id2).boxed().collect(Collectors.toList());
        return range2.stream().anyMatch(range1::contains);
    }
}


