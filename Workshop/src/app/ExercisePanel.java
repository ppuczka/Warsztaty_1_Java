package app;

import model.Exercise;
import util.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Exercise.loadAllExercises;
import static model.Exercise.loadExerciseById;

public class ExercisePanel {

    public static void exercisePanel() throws SQLException {

            Exercise exercise = new Exercise();
            Scanner scan;
            scan = new Scanner(System.in);

            ArrayList<Exercise> exercises = loadAllExercises(DbManager.getInstance().getConnection());
            for (Exercise e : exercises) {
                System.out.println(e.printAllExercises());

            }
            System.out.println("\n" + "--------------------------------------------------------------------------------------"
                    + "--------------------------------------------------------------------------------------"
                    + "\n" + "Wybierz jedną z opcji: " + "\n" + "add - dodanie ćwiczenia " + "\n" +
                    "edit - edycja ćwiczenia" + "\n" + "delete - usunięcie ćwiczenia " + "\n"
                    + "quit - zakończenie programu");

// pętla wyboru programu administracyjnego

            String choice2 = scan.next();
            while (!choice2.equals("add") && !choice2.equals("edit") && !choice2.equals("delete") && !choice2.equals("quit")) {
                System.out.println("Niepoprawny wybór");
                choice2 = scan.next();
            }

            //dodanie użytkownika
            if (choice2.equals("add")) {
                System.out.println("Podaj nazwę nowego ćwiczenia: ");
                String title = scan.nextLine();
                exercise.setTitle(title);

                System.out.println("Podaj opis: ");
                String desc = scan.nextLine();
                exercise.setDescription(desc);

                exercise.saveToDb(DbManager.getInstance().getConnection());
                exercisePanel();
                System.out.println("Cwiczenie zostało dodane do bazy");

            }
            //edycja uzytkownika
            if (choice2.equals("edit")) {

                ArrayList<Exercise> exercises1 = loadAllExercises(DbManager.getInstance().getConnection());
                ArrayList<Integer> tempIds2 = new ArrayList<>();

                for (Exercise e : exercises1) {
                    System.out.println(e.printAllExercises());
                    tempIds2.add(e.getId());
                }

                System.out.println("Podaj id ćwiczenia do edycji: ");

                int exeId = scan.nextInt();

                while (!tempIds2.contains(exeId)) {
                    System.out.println("Podaj poprawne id");
                    exeId = scan.nextInt();
                }

                Exercise editExe = loadExerciseById(DbManager.getInstance().getConnection(), exeId);
                System.out.println(editExe.printLoadedExercise());

                System.out.println("Podaj nową nazwę ćwiczenia: ");
                String exeName = scan.next();
                editExe.setTitle(exeName);

                System.out.println("Podaj nowy opis: ");
                String desc = scan.next();
                editExe.setDescription(desc);

                editExe.saveToDb(DbManager.getInstance().getConnection());
                exercisePanel();
                System.out.println("Użytkownik został wyedytowany");

//usunięcie użytkownika

            } else if (choice2.equals("delete")) {

                ArrayList<Exercise> exercises2= loadAllExercises(DbManager.getInstance().getConnection());
                ArrayList<Integer> tempIds4 = new ArrayList<>();
                for (Exercise e : exercises2) {
                    System.out.println(e.printAllExercises());
                    tempIds4.add(e.getId());

                }
                System.out.println("Podaj id ćwiczenia do usunięcia: ");

                int exeId = scan.nextInt();
                while (!tempIds4.contains(exeId)) {
                    System.out.println("Podaj poprawne id");
                    exeId = scan.nextInt();
                }
                Exercise delExe = loadExerciseById(DbManager.getInstance().getConnection(), exeId);
                delExe.delete(DbManager.getInstance().getConnection());
                System.out.println("ćwiczenie zostało usuniięte z bazy");
                exercisePanel();

            } else if (choice2.equals("quit")) {
                System.out.println("Do widzenia");
                Scanners.welcomeScanner();
            }

        }
    }

