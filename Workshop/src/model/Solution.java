package model;


import java.sql.*;
import java.util.ArrayList;

public class Solution extends Exercise {

    private int id;
    private java.sql.Date created;
    private java.sql.Date updated;
    private String solDescription;
    private int exercise_id;
    private int users_id;

    public Solution() {

    }

    public Solution(String name, int groupId, int id, String username, String password, String email, int id1,
                    String title, String description, Date created, Date updated, String solDescription,
                    int exercise_id, int users_id) {
        super(name, groupId, id, username, password, email, id1, title, description);
        this.created = created;
        this.updated = updated;
        this.solDescription = solDescription;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }



    public int getId() {
        return id;
    }

    public java.sql.Date getCreated() {
        return created;
    }

    public java.sql.Date getUpdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setCreated(java.sql.Date created) {
        this.created = created;
    }

    public void setUpdated(java.sql.Date updated) {
        this.updated = updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public static Solution loadSolutionById(Connection conn, int id) throws SQLException {

        String sql = "SELECT * FROM Solution WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = rs.getInt("id");
            loadedSolution.created = rs.getDate("created");
            loadedSolution.updated = rs.getDate("updated");
            loadedSolution.solDescription = rs.getString("description");
            loadedSolution.exercise_id = rs.getInt("exercise_id");
            loadedSolution.users_id = rs.getInt("users_id");
            return loadedSolution;
        }
        return null;

    }

    public static ArrayList<Solution> loadAllSolutionsByUserID(Connection conn, int users_id) throws SQLException {

        ArrayList<Solution> solutionsById = new ArrayList<>();
        String sql = "SELECT * FROM solution JOIN users ON solution.users_id = users.id WHERE users.id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, users_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            while (rs.next()) {
                Solution allById = new Solution();
                allById.id = rs.getInt("id");
                allById.created = rs.getDate("created");
                allById.updated = rs.getDate("updated");
                allById.solDescription = rs.getString("description");
                allById.exercise_id = rs.getInt("excercise_id");
                allById.users_id = rs.getInt("users_id");
                allById.username = rs.getString("username");
                allById.email = rs.getString("email");
                solutionsById.add(allById);

            }
            return solutionsById;
        }
        return null;

    }

    public static ArrayList<Solution> loadAllSolutionsByNoUserID(Connection conn, int users_id) throws SQLException {

        ArrayList<Solution> solutionsById = new ArrayList<>();
        String sql = "SELECT solution.id, created, updated, description, excercise_id FROM solution JOIN users" +
                " ON solution.users_id = users.id WHERE users.id != ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, users_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            while (rs.next()) {
                Solution allById = new Solution();
                allById.id = rs.getInt(1);
                allById.created = rs.getDate("created");
                allById.updated = rs.getDate("updated");
                allById.solDescription = rs.getString("description");
                allById.exercise_id = rs.getInt("excercise_id");
                solutionsById.add(allById);

            }
            return solutionsById;
        }
        return null;

    }

    public static ArrayList<Solution> loadAllSolutionsByExId(Connection conn, int exercise_id)  throws SQLException {

        ArrayList<Solution> solutionsByExId = new ArrayList<>();
        String sql = "SELECT * FROM solution JOIN exercise ON solution.excercise_id = exercise.id WHERE exercise.id = ? " +
                "ORDER BY created DESC";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setInt(1, exercise_id);
        ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    Solution allById2 = new Solution();
                    allById2.id = rs.getInt("id");
                    allById2.created = rs.getDate("created");
                    allById2.updated = rs.getDate("updated");
                    allById2.solDescription = rs.getString("description");
                    allById2.exercise_id = rs.getInt("excercise_id");
                    allById2.users_id = rs.getInt("users_id");
                    allById2.title = rs.getString("title");
                    allById2.description = rs.getString(9);
                    solutionsByExId.add(allById2);
                }
               return solutionsByExId;

            }
            return null;
    }

    public static ArrayList<Solution> loadAllSolutions(Connection conn) throws SQLException {

        ArrayList<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM Solution";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = rs.getInt("id");
            loadedSolution.created = rs.getDate("created");
            loadedSolution.updated = rs.getDate("updated");
            loadedSolution.description = rs.getString("description");
            loadedSolution.exercise_id = rs.getInt("excercise_id");
            loadedSolution.users_id = rs.getInt("users_id");
            solutions.add(loadedSolution);

        }
        return solutions;
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO Solution(created, updated, description, excercise_id, users_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            String generatedColumns[] = {"ID"};
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setDate(1, this.created);
            preparedStatement.setDate(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise_id);
            preparedStatement.setInt(5, this.users_id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE Solutions SET created=?, updated=?, descripton=?, exercise_id, users_id " +
                    "where id = ?";

            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, this.created);
            preparedStatement.setDate(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise_id);
            preparedStatement.setInt(5, this.users_id);
            preparedStatement.setInt(6, this.id);
            preparedStatement.executeUpdate();
        }

    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM Solutions WHERE id= ?";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }

    }

    public String printLoadedSolutions() {
        return ("ID rozwiązania: " + id + " | Data utworzenia: " +
                created + " | Data edycji: " + updated + " | opis: " + description +
                " | ID ćwiczenia: " + exercise_id + " | ID użytkownika: " + users_id);
    }


    public String printAllSolutions() {
        return ("ID rozwiązania: " + id + " | Data utworzenia: " +
                created + " | Data edycji: " + updated + " | opis: " + description +
                " | ID ćwiczenia: " + exercise_id + " | ID użytkownika: " + users_id);
    }

    public String printAllSolutionsByUserID() {
        return ("ID rozwiązania: " + id + " | Nazwa użyrkownika: " + username + " | email: " + email +
                " | Data utworzenia: " + created + " | Data edycji: " + updated + " | opis: " + description +
                " | ID ćwiczenia: " + exercise_id);

    }

    public String printAllSolutionsByExId()  {
        return ("ID rozwiązania: " + id + " | Nazwa użyrkownika: " + username + " | email: " + email +
                " | Data utworzenia: " + created + " | Data edycji: " + updated + " | opis: " + solDescription +
                " | ID ćwiczenia: " + exercise_id + " | Id użytkownika: " + users_id + " | Tytuł ćwiczenia: " +
                title + " | Opis ćwiczenia: " + description);

    }


    @Override
    public String toString() {
        return this.id + " " + this.username + " " + this.email + "  " + this.created + " " + this.updated +
                " " + this.solDescription + " " + this.exercise_id + " " + this.users_id + " " +  this.title +
                " " + this.description;
}

}






