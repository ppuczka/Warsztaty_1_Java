package Warsztaty;

import sun.jvm.hotspot.runtime.StaticBaseConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Zadanie_4 {
    // Symulator rzutu kostką
    public static void main(String[] args) {

        System.out.println(cube("10D6-3"));
    }

    static String cube(String parameters) {

        Random r = new Random();
        int cubeSize = 0;
        int option = 0;
        int numOfThrows = 0;
        int indexOfSep = 0;
        int simulateThrow = 0;
        String optionStr = "";

        if (parameters.contains("D3")) {
            cubeSize = 3;
        } else if ((parameters.contains("D4"))) {
            cubeSize = 4;
        } else if ((parameters.contains("D6"))) {
            cubeSize = 6;
        } else if ((parameters.contains("D8"))) {
            cubeSize = 8;
        } else if ((parameters.contains("D10")) && !parameters.contains("00")) {
            cubeSize = 10;
        } else if ((parameters.contains("D12"))) {
            cubeSize = 12;
        } else if ((parameters.contains("D20"))) {
            cubeSize = 20;
        } else if ((parameters.contains("D100"))) {
            cubeSize = 100;
        }
        int dIndex = parameters.indexOf('D');
//sprawdzanie ilości rzutów
        if (Character.isDigit(parameters.charAt(0))) {
            numOfThrows = Integer.parseInt("" + parameters.charAt(0));
        }
//ustawienie dla wartości 10 - nie działa pow 10
        if (numOfThrows == 1) {
            numOfThrows = 10;
        }

//sprawdzanie modyfikatora
        if (parameters.contains("+")) {
            indexOfSep = parameters.indexOf("+");
            optionStr = parameters.substring(indexOfSep + 1);
            option = Integer.parseInt(optionStr);

        } else if (parameters.contains("-")) {
            indexOfSep = parameters.indexOf("-");
            optionStr = parameters.substring(indexOfSep + 1);
            option = -(Integer.parseInt(optionStr));

        } else {
            option = 0;
        }
//tworzymy ArrayListę która gromadzi rzuty następnie generujemy rzuty
        ArrayList<Integer> listOfThrows = new ArrayList<>();

        if (dIndex == 0) {
            simulateThrow = (r.nextInt(cubeSize) + 1) + option;
            listOfThrows.add(simulateThrow);

        } else if (dIndex != 0) {

            for (int i = 0; i <= numOfThrows - 1; i++) {
                simulateThrow = (r.nextInt(cubeSize) + 1) + option;
                listOfThrows.add(simulateThrow);
            }
        }

        return listOfThrows.toString();


    }
}