package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise extends User {

    private int id;
    protected String title;
    protected String description;

    public  Exercise() {

    }

    public Exercise(String name, int groupId, int id, String username, String password, String email, int id1, String title, String description) {
        super(name, groupId, id, username, password, email);
        this.id = id1;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Exercise loadExerciseById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM Exercise WHERE id =?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Exercise loadedExercise = new Exercise();
            loadedExercise.id = rs.getInt("id");
            loadedExercise.title = rs.getString("title");
            loadedExercise.description = rs.getString("description");
            return loadedExercise;
        }

        return null;
    }

    public static ArrayList<Exercise> loadAllExercises(Connection conn) throws SQLException {

        ArrayList<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT * FROM exercise";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Exercise loadedExcercise = new Exercise();
            loadedExcercise.id = rs.getInt("id");
            loadedExcercise.title = rs.getString("title");
            loadedExcercise.description = rs.getString("description");
            exercises.add(loadedExcercise);
        }

        return exercises;

    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM exercise WHERE id =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.executeUpdate();
            this.id = 0;
        }
    }

    public void saveToDb(Connection conn) throws SQLException {

        if (this.id == 0) {
            String sql = "INSERT INTO exercise (title, description) VALUES (?,?)";
            String generatedColumns[] = {"ID"};
            PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
            ps.setString(1, this.title);
            ps.setString(2, this.description);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);

            }

        } else {

            String sql = "UPDATE exercise SET title =?, description =? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.title);
            ps.setString(2, this.description);
            ps.setInt(3, this.id);
            ps.executeUpdate();
        }
    }

    public String printLoadedExercise() {
        return ("ID ćwiczenia: " + id + " | Tytuł: " + title + " | Opis: " + description);

    }

    public String printAllExercises() {
        return ("ID ćwiczenia: " + id + " | Tytuł: " + title + " | Opis: " + description);

    }

    @Override
    public String toString() {
        return this.id + " " + this.title + " " + this.description;
    }


}
