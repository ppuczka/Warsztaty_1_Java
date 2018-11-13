package app;

import model.Group;
import model.User;
import util.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Group.loadAllGroups;
import static model.User.loadAllUsers;
import static model.User.loadUserById;

public class UserPanel {

    public static void userPanel() throws SQLException {

        User user1 = new User();
        Scanner scan;
        scan = new Scanner(System.in);

        ArrayList<User> users = loadAllUsers(DbManager.getInstance().getConnection());
        for (User u : users) {
            System.out.println(u.printAllUsers());

        }
        System.out.println("\n" + "--------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------------"
                + "\n" + "Wybierz jedną z opcji: " + "\n" + "add - dodanie użytkownika " + "\n" +
                "edit - edycja użytkownika" + "\n" + "delete - usunięcie użytkownika " + "\n"
                + "quit - zakończenie programu");

// pętla wyboru programu administracyjnego

        String choice2 = scan.next();
        while (!choice2.equals("add") && !choice2.equals("edit") && !choice2.equals("delete") && !choice2.equals("quit")) {
            System.out.println("Niepoprawny wybór");
            choice2 = scan.next();
        }

        //dodanie użytkownika
        if (choice2.equals("add")) {
            System.out.println("Podaj nazwę nowego użytkownika: ");
            String userName = scan.next();
            user1.setUsername(userName);
            ArrayList<String> tempEmails = new ArrayList<>();
            for (User u : users) {
                tempEmails.add(u.getEmail());
            }
            System.out.println("Podaj email: ");
            String email = scan.next();
            // sprawdzam czy email juz isnieje w bazie danych
            while (tempEmails.contains(email)) {
                System.out.println("Użytkownik o podanym adresie istnieje w bazie");
                email = scan.next();
            }

            user1.setEmail(email);
            System.out.println("Podaj hasło: ");
            String pass = scan.next();
            user1.setPassword(pass);
            System.out.println("Podaj id grupy ");


            ArrayList<Group> groups = loadAllGroups(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds = new ArrayList<>();

            for (Group g : groups) {
                System.out.println(g.printLoadedGroup());
                tempIds.add(g.getGroupIdId());
            }

            int groupId = 0;
            while (!scan.hasNextInt()) {
                System.out.println("Niepoprawny typ danych ");
                scan.next();
            }
            groupId = scan.nextInt();

            while (!tempIds.contains(groupId)) {
                System.out.print("Nie poprawne id podaj poprawne id: ");
                groupId = scan.nextInt();
            }

            user1.setGroupId(groupId);
            user1.saveUserToDB(DbManager.getInstance().getConnection());
            userPanel();
            System.out.println("Użytkownik został dodany do bazy");

        }
        //edycja uzytkownika
        if (choice2.equals("edit")) {

            ArrayList<User> users2 = loadAllUsers(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds2 = new ArrayList<>();
            ArrayList<String> tempEmails = new ArrayList<>();
            for (User u : users2) {
                System.out.println(u.printAllUsers());
                tempIds2.add(u.getId());
            }

            for (User u : users) {
                tempEmails.add(u.getEmail());
            }

            System.out.println("Podaj id użytkownika do edycji: ");

            int usrId = scan.nextInt();

            while (!tempIds2.contains(usrId)) {
                System.out.println("Podaj poprawne id");
                usrId = scan.nextInt();
            }

            User editUser = loadUserById(DbManager.getInstance().getConnection(), usrId);
            System.out.println(editUser.printLoaderUser());

            System.out.println("Podaj nową nazwę użytkownika: ");
            String userName = scan.next();
            editUser.setUsername(userName);

            System.out.println("Podaj nowy email: ");
            String email = scan.next();

            // sprawdzam czy email juz isnieje w bazie danych
            while (tempEmails.contains(email)) {
                System.out.println("Użytkownik o podanym adresie istnieje w bazie");
                email = scan.next();
            }

            editUser.setEmail(email);
            System.out.println("Podaj hasło: ");
            String pass = scan.next();
            editUser.setPassword(pass);
            System.out.println("Podaj id grupy ");
// załadowanie tabeli group + stworzenie tabeli z IDs

            ArrayList<Group> groups = loadAllGroups(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds3 = new ArrayList<>();

            for (Group g : groups) {
                System.out.println(g.printLoadedGroup());
                tempIds3.add(g.getGroupIdId());
            }
            int groupId = 0;
            while (!scan.hasNextInt()) {
                System.out.println("Podaj poprawne id");
                scan.nextInt();

            }
            groupId = scan.nextInt();

            while (!tempIds3.contains(groupId)) {
                System.out.println("Podaj poprawne id");
                groupId = scan.nextInt();

            }
            editUser.setGroupId(groupId);
            editUser.saveUserToDB(DbManager.getInstance().getConnection());
            userPanel();
            System.out.println("Użytkownik został wyedytowany");

//usunięcie użytkownika

        } else if (choice2.equals("delete")) {

            ArrayList<User> users3 = loadAllUsers(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds4 = new ArrayList<>();
            for (User u : users3) {
                System.out.println(u.printAllUsers());
                tempIds4.add(u.getId());

            }
            System.out.println("Podaj id użytkownika do usunięcia: ");

            int usrId = scan.nextInt();
            while (!tempIds4.contains(usrId)) {
                System.out.println("Podaj poprawne id");
                usrId = scan.nextInt();
            }
            User delUser = loadUserById(DbManager.getInstance().getConnection(), usrId);
            delUser.delete(DbManager.getInstance().getConnection());
            System.out.println("Użytkownik został usuniięty z bazy");
            userPanel();

        } else if (choice2.equals("quit")) {
            System.out.println("Do widzenia");
            Scanners.welcomeScanner();

        }
    }
}