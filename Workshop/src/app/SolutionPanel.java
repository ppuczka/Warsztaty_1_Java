package app;

import model.Exercise;
import model.Solution;
import model.User;
import util.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import static model.Exercise.loadAllExercises;
import static model.Solution.loadAllSolutions;
import static model.Solution.loadAllSolutionsByUserID;
import static model.User.loadAllUsers;

public class SolutionPanel {

    public static void solutionPanel() throws SQLException {

        Solution solution = new Solution();
        Scanner scan;
        scan = new Scanner(System.in);


        System.out.println("\n" + "--------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------------"
                + "\n" + "Wybierz jedną z opcji: " + "\n" + "add - dodanie rozwiązania " + "\n" +
                "view - przeglądanie rozwiązań" + "\n" + "quit - zakończenie programu");

// pętla wyboru programu administracyjnego

        String choice2 = scan.next();
        while (!choice2.equals("add") && !choice2.equals("view") && !choice2.equals("quit")) {
            System.out.println("Niepoprawny wybór");
            choice2 = scan.next();
        }

        if (choice2.equals("add")) {
            ArrayList<User> users = loadAllUsers(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds4 = new ArrayList<>();
            for (User u : users) {
                System.out.println(u.printAllUsers());
                tempIds4.add(u.getId());
            }
            System.out.println("Podaj id użytkownika");
            int usrId = scan.nextInt();

            while (!tempIds4.contains(usrId)) {
                System.out.println("Podaj poprawne id");
                usrId = scan.nextInt();
            }
            ArrayList<Exercise> allEX = loadAllExercises(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds5 = new ArrayList<>();
            for (Exercise e : allEX) {
                System.out.println(e.printAllExercises());
                tempIds5.add(e.getId());
            }
            System.out.println("Podaj id ćwiczenia");
            int exId = scan.nextInt();

            while (!tempIds5.contains(exId)) {
                System.out.println("Podaj poprawne id");
                exId = scan.nextInt();
            }

            Solution userSolution = new Solution();
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            userSolution.setUsers_id(usrId);
            userSolution.setExercise_id(exId);
            userSolution.setCreated(date);
            userSolution.setUpdated(null);
            userSolution.setDescription(null);

            userSolution.saveToDB(DbManager.getInstance().getConnection());
            System.out.println("Rozwiązanie zostało przypisane");
            solutionPanel();


        } else if (choice2.equals("view")) {
            ArrayList<Solution> allSolutions = loadAllSolutions(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempSolId = new ArrayList<>();
            ArrayList<User> users = loadAllUsers(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds5 = new ArrayList<>();
            for (User u : users) {
                System.out.println(u.printAllUsers());
                tempIds5.add(u.getId());
            }
            Solution solution1 = new Solution();
            for (Solution s : allSolutions) {
                System.out.println(s.printAllSolutions());
              //  tempSolId.add(s.getUsers_id());
            }

//            System.out.println("Id użytkowników posiadających rozwiązania w bazie: " + tempSolId.toString() + "\n" +
//            "Podaj id użytkownika: ");

            int usrId2 = scan.nextInt();
            while (!tempSolId.contains(usrId2)) {
                System.out.println("Brak zadań dla danego użytkownika, podaj poprawne id");
                usrId2 = scan.nextInt();
            }
            ArrayList<Solution> solutionsByUserID = loadAllSolutionsByUserID(DbManager.getInstance().getConnection(), usrId2);
     //      solutionsByUserID.set(0, solution1);
            System.out.println(solutionsByUserID.size());
            for (Solution s2 : solutionsByUserID) {
                System.out.println(s2.printLoadedSolutions());
                tempIds5.add(s2.getId());
            }
            solutionPanel();

        } else if (choice2.equals("quit")) {
            System.out.println("Do widzenia");
            Scanners.welcomeScanner();
        }

    }
}
