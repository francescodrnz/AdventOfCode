package com.aoc2022.controller;

import com.aoc2022.services.D1CalorieCounting;
import com.aoc2022.services.D4CampCleanup;
import com.utils.EmptyService;
import com.aoc2022.services.D2RockPaperScissors;
import com.aoc2022.services.D3RucksackReorganization;
import org.springframework.stereotype.Controller;

import static com.utils.AoCUtils.askContinue;
import static com.utils.AoCUtils.askDay;

@Controller
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
                D1CalorieCounting.run();
                break;
            case 2:
                D2RockPaperScissors.run();
                break;
            case 3:
                D3RucksackReorganization.run();
                break;
            case 4:
                D4CampCleanup.run();
                break;
            default:
                EmptyService.run();
        }
        return 1;
    }
}
