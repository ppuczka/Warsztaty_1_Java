package Warsztaty;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Zadanie_2 {

	public static void main(String[] args) {

		Integer[] arr = new Integer[49];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		Collections.shuffle(Arrays.asList(arr));

		Integer[] arrLotto = Arrays.copyOf(arr, 6);
		Integer[] userLotto = new Integer[6];

		Scanner scanner = new Scanner(System.in);
		int number = 0;
		System.out.println("Wpisz liczbę z zakresu 1-49");
		while (!scanner.hasNextInt()) {
			System.out.println("To nie jest poprawna liczba");
			scanner.next();
		}
			for (int i = 0; i < userLotto.length; i++) {
				while (!scanner.hasNextInt())  {
					System.out.println("To nie jest poprawna wartość");
					scanner.next();
				}
				number = scanner.nextInt();
				if (Arrays.asList(userLotto).contains(number)) {
					System.out.println("Już podałeś tę liczbę");
					i--;

					} else if ((number <= 49) && (number > 0) ) {
						userLotto[i] = number;
					} 	else if (number < 1) {
						System.out.println("Za mała liczba");
						i--;
					} else if (number > 49) {
						System.out.println("Za duża liczba");
						i--;

					}
			}
		Arrays.sort(userLotto);
		Arrays.sort(arrLotto);

		System.out.println("Twoje liczby: " + Arrays.toString(userLotto));
		System.out.println("Wylosowane liczby: " + Arrays.toString(arrLotto));
		int correct = 0;
			for (int i = 0; i < userLotto.length; i++) {
				if (Arrays.asList(arrLotto).contains(userLotto[i])) {
					correct = correct + 1;
				}
			}
					if (correct ==3)  {
						System.out.println("Trafiłeś trójkę");
					}	else if (correct == 4)  {
						System.out.println("Trafiłeś czwórkę");
					}	else if (correct ==5)  {
						System.out.println("Trafiłeś piątkę");
					}	else if (correct == 6) {
						System.out.println("BINGO główna wygrana");
					}	else {
						System.out.println("Niestety nie wygrałeś. Trafiłeś " + correct + " poprawnych liczb");
					}


		}
	}

