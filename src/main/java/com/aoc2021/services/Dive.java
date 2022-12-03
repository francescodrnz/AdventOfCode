package com.aoc2021.services;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.utils.AoCUtils.readFile;

@Slf4j
public class Dive {

    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("course");

        Map<String, List<String>> map = lines.stream().collect(Collectors.groupingBy(i -> i.split(" ")[0]));

        int forward = map.get("forward").stream().mapToInt(i -> Integer.parseInt(i.split(" ")[1])).sum();
        int up = map.get("up").stream().mapToInt(i -> Integer.parseInt(i.split(" ")[1])).sum();
        int down = map.get("down").stream().mapToInt(i -> Integer.parseInt(i.split(" ")[1])).sum();
        int finalPosition = forward * (down - up);
        log.info("Final course position: " + finalPosition);


        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }

}
