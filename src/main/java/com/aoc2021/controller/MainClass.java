package com.aoc2021.controller;

import com.aoc2021.services.Dive;
import com.aoc2021.services.SonarSweep;
import com.utils.EmptyService;

import static com.utils.AoCUtils.askContinue;
import static com.utils.AoCUtils.askDay;

public class MainClass {

    public static void main(String[] args) {
        selectDay(askDay());
        while (true) {
            if (selectDay(askContinue()) == 0)
                break;
        }
    }

    private static int selectDay(int askDay) {
        switch (askDay) {
            case 0:
                return 0;
            case 1:
                SonarSweep.run();
                break;
            case 2:
                Dive.run();
                break;
            default:
                EmptyService.run();
        }
        return 1;
    }
}
