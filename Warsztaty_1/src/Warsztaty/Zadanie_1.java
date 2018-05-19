package Warsztaty;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// gra w Zgadywanie liczb
public class Zadanie_1 {

	public static void main(String[] args) {

		Random r = new Random();
		int random = r.nextInt(99) + 1;
		Scanner scanner = new Scanner(System.in);
		int user = 0;
		System.out.println("Zgadnij liczbę z zakresu 1 - 100");

		System.out.println(random);
		while (!(user == random)) {
			while (!scanner.hasNextInt()) {
				System.out.println("To nie jest poprawna liczba");
				scanner.next();
			}
			user = scanner.nextInt();
			if (user < random) {
				System.out.println("Za mało. Spróbuj jeszcze raz");

			} else if (user > random) {
				System.out.println("Za dużo. Spróbuj jeszcze raz");
			
			} else if (user == random) {

				break;
			}

		}
		System.out.println("Zgadłeś");
	}

}
