package app;

import model.Solution;
import util.DbManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import static model.Solution.loadAllSolutionsByNoUserID;

public class UserSolutionPanel {

    int id;

    {
        try {
            id = Scanners.idScanner();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void userSolutionPanel(int id) throws SQLException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Solution> allSolutions = loadAllSolutionsByNoUserID(DbManager.getInstance().getConnection(), id);
        ArrayList<Integer> tempIds2 = new ArrayList<>();
        System.out.println("Zadania do których użytkownik nie dodał rozwiązań: ");
        for (Solution s : allSolutions) {
            System.out.println(s.printAllSolutionsByUserID());
            tempIds2.add(s.getExercise_id());

        }
        System.out.println("Podaj id zadania do którego chcesz dodać rozwiązanie: ");

        System.out.println("Podaj id ćwiczenia: ");

        int solId = scan.nextInt();

        while (!tempIds2.contains(solId)) {
            System.out.println("Podaj poprawne id: ");
            solId = scan.nextInt();
        }

        System.out.println("Podaj rozwiązanie zadania: ");
        String description = scan.next();

        Solution userSolution = new Solution();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        userSolution.setUsers_id(id);
        userSolution.setExercise_id(solId);
        userSolution.setUpdated(date);
        userSolution.setDescription(description);
        userSolution.saveToDB(DbManager.getInstance().getConnection());
        System.out.println("Rozwiązanie zostało dodane");
    }

}
