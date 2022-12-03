package com.aoc2.controller;

import com.aoc2.services.CalorieCounting;
import com.aoc2.services.EmptyService;
import com.aoc2.services.RockPaperScissors;
import com.aoc2.services.RucksackReorganization;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

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

    public static int askDay() {
        System.out.print("Choose the day to run: ");
        return new Scanner(System.in).nextInt();
    }

    public static int askContinue() {
        System.out.print("Run another day? (0 to exit): ");
        return new Scanner(System.in).nextInt();
    }
}
