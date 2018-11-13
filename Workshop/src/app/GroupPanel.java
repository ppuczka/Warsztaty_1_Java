package app;

import model.Group;
import model.User;
import util.DbManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Group.loadAllGroups;
import static model.Group.loadGroupById;
import static model.User.loadAllUsers;
import static model.User.loadUserById;

public class GroupPanel {

    public static void groupPanel() throws SQLException {
        Group group = new Group();
        Scanner scan;
        scan = new Scanner(System.in);

        ArrayList<Group> groups = loadAllGroups(DbManager.getInstance().getConnection());
        for (Group g : groups) {
            System.out.println(g.printAllGroups());

        }
        System.out.println("\n" + "--------------------------------------------------------------------------------------"
                + "--------------------------------------------------------------------------------------"
                + "\n" + "Wybierz jedną z opcji: " + "\n" + "add - dodanie grupy " + "\n" +
                "edit - edycja grupy" + "\n" + "delete - usunięcie grupy " + "\n"
                + "quit - zakończenie programu");

// pętla wyboru programu administracyjnego

        String choice2 = scan.next();
        while (!choice2.equals("add") && !choice2.equals("edit") && !choice2.equals("delete") && !choice2.equals("quit")) {
            System.out.println("Niepoprawny wybór");
            choice2 = scan.next();
        }

        //dodanie użytkownika
        if (choice2.equals("add")) {
            System.out.println("Podaj nazwę nowej grupy: ");
            String groupName = scan.next();
            group.setName(groupName);


            group.savetoDB(DbManager.getInstance().getConnection());
            groupPanel();
            System.out.println("Grupa została dodana do bazy");

        } else if (choice2.equals("edit")) {

            ArrayList<Group> groups2 = loadAllGroups(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds2 = new ArrayList<>();

            for (Group g : groups2) {
                System.out.println(g.printAllGroups());
                tempIds2.add(g.getGroupIdId());
            }


            System.out.println("Podaj id grupy do edycji: ");

            int gId = scan.nextInt();

            while (!tempIds2.contains(gId)) {
                System.out.println("Podaj poprawne id");
                gId = scan.nextInt();
            }

            Group editGroup = loadGroupById(DbManager.getInstance().getConnection(), gId);
            System.out.println(editGroup.printLoadedGroup());

            System.out.println("Podaj nową nazwę grupy: ");
            String groupName1 = scan.next();
            editGroup.setName(groupName1);

            editGroup.savetoDB(DbManager.getInstance().getConnection());
            groupPanel();
            System.out.println("Grupa została wyedytowana");

//usunięcie grupy

        } else if (choice2.equals("delete")) {


            ArrayList<Group> groups2 = loadAllGroups(DbManager.getInstance().getConnection());
            ArrayList<Integer> tempIds2 = new ArrayList<>();

            for (Group g : groups2) {
                System.out.println(g.printAllGroups());
                tempIds2.add(g.getGroupIdId());
            }


            System.out.println("Podaj id grupy do usunięcia: ");
            int gId = scan.nextInt();

            while (!tempIds2.contains(gId)) {
                System.out.println("Podaj poprawne id");
                gId = scan.nextInt();

            }
            try {
                Group delGroup = loadGroupById(DbManager.getInstance().getConnection(), gId);
                delGroup.delete(DbManager.getInstance().getConnection());
                System.out.println("Grupa została usuniięta z bazy");
                groupPanel();
            }catch (SQLException e) {
                System.out.println("Nie można usunąć grupy ponieważ jest powiązana z użytkownikami");
                System.out.println("\n" + "--------------------------------------------------------------------");
                groupPanel();
            }
        } else if (choice2.equals("quit")) {
            System.out.println("Do widzenia");
            Scanners.welcomeScanner();
        }
    }
}


