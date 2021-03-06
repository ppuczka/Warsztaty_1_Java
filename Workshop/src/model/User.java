package model;

import org.mindrot.BCrypt;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Group {

    private int id;
    protected String username;
    private String password;
    protected String email;


    public User() {
    }

    public User(String name, int groupId, int id, String username, String password, String email) {
        super(groupId, name);
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static User loadUserById(Connection conn, int id) throws SQLException {

        String sql = "SELECT * FROM Users where id=?";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            return loadedUser;
        }
        return null;
    }

    public static ArrayList<User> loadAllUsers(Connection conn) throws SQLException {

        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM Users";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            users.add(loadedUser);
        }
        return users;
    }

    public void saveUserToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO Users(username, email, password, person_group_id) VALUES (?, ?, ?, ?)";
            String generatedColumns[] = {"ID"};
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4,this.groupId);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE Users SET username=?, email=?, password=? where id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.id);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM Users WHERE id= ?";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }

    }


    public String printLoaderUser() {
        return ("ID użytkownika: " + id + " | Nazwa użytkownika: " +
                username + " | email: " + email + " | Id Grupy: " + groupId + " | Nazwa grupy: " +name);
    }


    public String printAllUsers() {
        return ("ID użytkownika: " + id + " | Nazwa użytkownika: " +
                username + " | email: " + email + " | hasło: " + password);
    }

    public String printAllByGroupId() {
        return ("ID użytkownika: " + id + " | Nazwa użytkownika: " +
                username + " | Email: " + email + " | Nazwa grupy: " + name);
    }

    @Override
    public String toString() {
        return this.username + " " + this.email + " " + this.password + " " +
                this.email + " " + this.name + " " + this.groupId;
    }
}



