package app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static app.ExercisePanel.exercisePanel;
import static app.GroupPanel.groupPanel;
import static app.SolutionPanel.solutionPanel;
import static app.UserPanel.userPanel;
import static app.UserSolutionPanel.userSolutionPanel;


public class Application {

    public static void main(String[] args) throws SQLException {

        Scanner scan = new Scanner(System.in);

        try {

            if (Scanners.welcomeScanner() == 1) {

                String welcome = "+--------------------------------------------------------------------------------------+" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                 **************PANEL ADMINISTRACYJNY**************                     |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "+--------------------------------------------------------------------------------------+" + "\n" +
                        "WITAJ W PROGRAMIE SZKOŁA PROGRAMOWANIA WYBIERZ OPCJE:";

                String decision = "\n" + "--------------------------------------------------------------------------------------"
                        + "--------------------------------------------------------------------------------------"
                        + "\n" + "user - Panel użytkownika " + "\n" +
                        "excersise  - Panel zadań" + "\n" + "group - Panel grupy " + "\n"
                        + "addEx  - Panel przypisywania zadań" + "\n" + "quit - zakończenie programu";
                System.out.println(welcome);
                System.out.println(decision);

                // pętla wyboru programu
                String userChoice = scan.next();
                while (!userChoice.equals("user") && !userChoice.equals("excersise") && !userChoice.equals("group") &&
                        !userChoice.equals("addEx") && !userChoice.equals("quit")) {
                    System.out.println("Niepoprawny wybór");
                    userChoice = scan.next();
                }
                if (userChoice.equals("user")) {
                    System.out.println("Witaj w panelu użytkownika");
                    userPanel();

                } else if (userChoice.equals("excersise")) {
                    System.out.println("Witaj w panelu zadań");
                    exercisePanel();

                } else if (userChoice.equals("group")) {
                    System.out.println("Witaj w panelu grup");
                    groupPanel();

                } else if (userChoice.equals("addEx")) {
                    System.out.println("Witaj w panelu przypisywania zadań");
                    solutionPanel();

                } else if (userChoice.equals("quit")) {
                    System.out.println("Do widzenia");

                }


            } else {

                Scanners.idScanner();

                String welcome2 = "+--------------------------------------------------------------------------------------+" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                 *******************PANEL ZADAŃ******************                     |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "|                                                                                      |" + "\n" +
                        "+--------------------------------------------------------------------------------------+" + "\n" +
                        "WITAJ W PANELU ZADAŃ TWOJE ID TO: ";


                String decision2 = "\n" + "--------------------------------------------------------------------------------------"
                        + "--------------------------------------------------------------------------------------"
                        + "\n" + "add - dodawanie rozwiązania" + "\n" +
                        "view  - przeglądanie swoich rozwiązań";

                int id = Scanners.idScanner();
                System.out.println(welcome2 + Scanners.idScanner());
                System.out.println(decision2);

                String userChoice = scan.next();
                while (!userChoice.equals("add") && !userChoice.equals("view")) {
                    System.out.println("Niepoprawny wybór");
                    userChoice = scan.next();
                }
                if (userChoice.equals("add")) {
                    System.out.println("Witaj w panelu dodawania rozwiązań");
                    userSolutionPanel(id);

                } else if (userChoice.equals("view")) {
                    System.out.println("Witaj w panelu przeglądania rozwiązań");

                }
            }

        } catch (SQLException e) {
            System.out.println("Błąd");
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Błąd");
            e.printStackTrace();
        }


    }
}


//        try {
//            initialize();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DbManager.getInstance().close();
//        }
//
//
//    }
//
//    public static void initialize() throws SQLException {
//
//
//


// Group group = new Group("Grupa1");
//  Group group1 = new Group("Grupa2");
// group.savetoDB(DbManager.getInstance().getConnection());
// group1.savetoDB(DbManager.getInstance().getConnection());
// Group group3 = Group.loadGroupById(DbManager.getInstance().getConnection(),4);
// System.out.println(group3.toString());
//        System.out.println(group3.printLoadedGroup());
//        ArrayList<Group> allgroups = Group.loadAllGroups(DbManager.getInstance().getConnection());
//            for (Group g : allgroups) {
//                System.out.println(g.printLoadedGroup());
//            }
//        System.out.println(allgroups.toString());

/*
        User user1 = loadUserById(DbManager.getInstance().getConnection(),29);
        user1.printLoaderUser();
        ArrayList<User> users = User.loadAllUsers(DbManager.getInstance().getConnection());
        for (User u: users){
            System.out.println();
        }
        System.out.println(users.toString());

        }
*/
//Exercise ex1 = new Exercise("Cwiczenie","To jest pierwsze cwiczenie");
//Exercise ex2 = new Exercise("Cwiczenie 2", "To jest drugie ćwiczenie");
// ex1.SaveToDb(DbManager.getInstance().getConnection());
// ex2.SaveToDb(DbManager.getInstance().getConnection());
//        Exercise load1 = Exercise.loadExerciseById(DbManager.getInstance().getConnection(),1);
//        System.out.println(load1.toString());
//        System.out.println(load1.printLoadedExercise());
//        ArrayList<Exercise> allEx = Exercise.loadAllExercises(DbManager.getInstance().getConnection());
//        for (Exercise e: allEx) {
//            System.out.println(e.printLoadedExercise());
//        }
//        System.out.println(allEx.toString());
//
//        Exercise exToDell = new Exercise();
////       // load1.delete(DbManager.getInstance().getConnection());
////        load1.setDescription("nowy opis");
////        load1.saveToDb(DbManager.getInstance().getConnection());
//        ArrayList<User> Allgr = User.loadAllByGroupId(DbManager.getInstance().getConnection(), groupId);
//
//       for (User g : Allgr) {
//           System.out.println(g.printAllByGroupId());
//       }
//        System.out.println(Allgr.toString());

//    }




