package com.aoc2022.controller;

import com.aoc2022.services.CalorieCounting;
import com.utils.EmptyService;
import com.aoc2022.services.RockPaperScissors;
import com.aoc2022.services.RucksackReorganization;
import org.springframework.stereotype.Controller;

import static com.utils.AoCUtils.askContinue;
import static com.utils.AoCUtils.askDay;

@Controller
public class MainClass {

    public static void main(String[] args) {
        do {
            selectDay(askDay());
        } while (selectDay(askContinue()) != 0);
    }

    private static int selectDay(int askDay) {
        switch (askDay) {
            case 0:
                return 0;
            case 1:
                CalorieCounting.run();
                break;
            case 2:
                RockPaperScissors.run();
                break;
            case 3:
                RucksackReorganization.run();
                break;
            default:
                EmptyService.run();
        }
        return 1;
    }
}
