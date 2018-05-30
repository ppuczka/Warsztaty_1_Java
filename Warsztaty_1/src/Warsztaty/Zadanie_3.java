package Warsztaty;

import java.util.ArrayList;
import java.util.Scanner;

public class Zadanie_3 {
// gra w zgadywanie liczb - komputer zgaduje liczbę pomyślaną przez użytkownika

    public static void main(String[] args) {

        System.out.println("Pomyśl liczbę z zakresu 0 - 1000 a ja zgadnę ją w 10 próbach!");

        int min = 0;
        int max = 1000;
        int guess = 0;
        int countGuess = 0;

        String more = "więcej";
        String less = "mniej";
        String corret = "trafiłeś";
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<Integer>();

        while (!corret.equals(answer) || nums.contains(guess)) {
            guess = ((max - min) / 2) + min;
            System.out.println("Zgaduję: " + guess + " więcej? / mniej?  / trafiłeś?");
            answer = scanner.next();


            if (nums.contains(guess)) {
                System.out.println("Nie oszukuj");
            }
            if (corret.equals(answer)) {
                System.out.println("Wygrałem w " + countGuess + " próbach");
                countGuess = countGuess + 1;
                break;

            } else if (more.equals(answer)) {
                min = guess;
                countGuess = countGuess + 1;
                nums.add(guess);
            } else if (less.equals(answer)) {
                max = guess;
                countGuess = countGuess + 1;
                nums.add(guess);
            }


        }

    }
}
