package app;

import model.User;
import util.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


import static model.User.loadAllUsers;

public class Scanners {

    public static int welcomeScanner() {

        Scanner scan;
        scan = new Scanner(System.in);
        int choice = 0;
        String welcome = "+--------------------------------------------------------------------------------------+" + "\n" +
                "|                                                                                      |" + "\n" +
                "|                                                                                      |" + "\n" +
                "|                                                                                      |" + "\n" +
                "|                 **************SZKOŁA PROGRAMOWANIA**************                     |" + "\n" +
                "|                                                                                      |" + "\n" +
                "|                                                                                      |" + "\n" +
                "|                                                                                      |" + "\n" +
                "+--------------------------------------------------------------------------------------+" + "\n" +
                "WITAJ W PROGRAMIE SZKOŁA PROGRAMOWANIA WYBIERZ OPCJE:";

        String decision = "\n" + "--------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------------"
                + "\n" + "1  - Program Administracyjny " + "\n" +
                "2  - Program Użytkownika (dodawanie zadań)";
        System.out.println(welcome);
        System.out.println(decision);
        System.out.print("Twój wybór: ");


        int userChoice = scan.nextInt();

        while ((userChoice != 1) && (userChoice != 2)) {
            System.out.println("Niepoprawny wybór");
            userChoice = scan.nextInt();

        }
        return userChoice;
    }

    public static int idScanner() throws SQLException {

        Scanner scan;
        scan = new Scanner(System.in);

        ArrayList<User> users2 = loadAllUsers(DbManager.getInstance().getConnection());
        ArrayList<Integer> tempIds2 = new ArrayList<>();

        for (User u : users2) {
            tempIds2.add(u.getId());
        }

        System.out.println("Podaj id użytkownika");

        int usrId = scan.nextInt();
        while (!tempIds2.contains(usrId)) {
            System.out.println("Podaj poprawne id");
            usrId = scan.nextInt();
        }
        return usrId;

    }
}






