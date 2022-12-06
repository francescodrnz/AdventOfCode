package com.aoc2022.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.utils.AoCUtils.readFile;

@Slf4j
public class D5SupplyStacks {
    public static void run() {

        Instant start = Instant.now();

        List<String> lines = readFile("supplystacks");

        List<String> cratesLines = ListUtils.partition(lines, lines.indexOf("") - 1).get(0);
        List<String> moves = lines.subList(lines.indexOf("") + 1, lines.size() - 1);

        Map<String, List<String>> cratesOld = new HashMap<>();
        AtomicInteger cratesNumber = new AtomicInteger();
        for (String cratesLine : cratesLines) {
            cratesLine.chars().forEach(ch -> {
                if (!Arrays.asList(' ', '[', ']').contains((char) ch))
                    cratesNumber.getAndIncrement();
            });
        }
        AtomicInteger counter = new AtomicInteger(0);
        lines.get(lines.indexOf("") - 1).chars().forEach(ch -> {
            if (ch != ' ') {
                String key = (char) ch + " " + counter.get();
                cratesOld.put(key, new ArrayList<>(Collections.nCopies(cratesNumber.get(), " ")));
            }
            counter.addAndGet(1);
        });
        List<String> cratesIndices = cratesOld.keySet().stream().map(o -> o.split(" ")[1]).collect(Collectors.toList());
        AtomicInteger crateNumber = new AtomicInteger(1);
        for (String line : cratesLines) {
            counter.set(0);
            line.chars().forEach(ch -> {
                if (cratesIndices.contains(String.valueOf(counter.get()))) {
                    cratesOld.get(crateNumber.get() + " " + counter.get()).add(String.valueOf((char) ch));
                    crateNumber.addAndGet(1);
                }
                counter.addAndGet(1);
            });
            crateNumber.set(1);
        }

        Map<String, List<String>> crates = cratesOld.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().split(" ")[0], Map.Entry::getValue));
        for (String move : moves) {
            int numOfCrates = Integer.parseInt(move.substring(move.indexOf(" ") + 1, move.indexOf(" ", move.indexOf(" ") + 1)));
            move = move.replace("move " + numOfCrates + " from ", "");
            List<String> crateFrom = crates.get(move.substring(0, move.indexOf(" ")));
            List<String> crateTo = crates.get(move.substring(move.lastIndexOf(" ") + 1));

            for (int i = 0; i < numOfCrates; i++) {
                int firstElementIndex = 0;
                while (" ".equals(crateFrom.get(firstElementIndex))) {
                    firstElementIndex++;
                }
                int lastSpaceIndex = 0;
                while (lastSpaceIndex < crateTo.size() && " ".equals(crateTo.get(lastSpaceIndex))) {
                    lastSpaceIndex++;
                }
                lastSpaceIndex -= 1;
                crateTo.set(lastSpaceIndex, crateFrom.get(firstElementIndex));

                crateFrom.set(firstElementIndex, " ");
            }
        }

        StringBuilder topCrate = new StringBuilder();
        TreeMap<String, List<String>> sortedCrates = new TreeMap<>(crates);
        for (Map.Entry<String, List<String>> entry : sortedCrates.entrySet()) {
            int firstElementIndex = 0;
            while (firstElementIndex < entry.getValue().size() && " ".equals(entry.getValue().get(firstElementIndex))) {
                firstElementIndex++;
            }
            if (firstElementIndex == entry.getValue().size())
                firstElementIndex--;
            topCrate.append(entry.getValue().get(firstElementIndex));
        }

        log.info("Crates on top: " + topCrate);


        log.info("elapsed time (ms): " + (Instant.now().toEpochMilli() - start.toEpochMilli()));
    }
}
