package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {

    protected int groupId;
    protected String name;


    public Group() {
    }

    public Group(int groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public Group(String name) {
        this.name = name;
    }

    public int getGroupIdId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public static Group loadGroupById(Connection conn, int groupId) throws SQLException {

        String sql = "SELECT * FROM User_group WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1 , groupId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Group loadedGroup = new Group();
            loadedGroup.groupId = resultSet.getInt("id");
            loadedGroup.name = resultSet.getString("name");
            return loadedGroup;

        }
        return null;
    }

    public static ArrayList<Group> loadAllGroups(Connection conn) throws SQLException {
        ArrayList<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM User_group";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())  {
            Group loadedGroup = new Group();
            loadedGroup.groupId = rs.getInt("id");
            loadedGroup.name = rs.getString("name");
            groups.add(loadedGroup);

        }
        return groups;
    }
    public static ArrayList<Group> loadAllGroupsID(Connection conn) throws SQLException {
        ArrayList<Group> groups = new ArrayList<>();
        String sql = "SELECT id FROM User_group";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())  {
            Group loadedGroup = new Group();
            loadedGroup.groupId = rs.getInt("id");
            groups.add(loadedGroup);

        }
        return groups;
    }

    public void delete(Connection conn) throws  SQLException {
        if (this.groupId != 0)  {
            String sql = "DELETE FROM User_group WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, this.groupId);
            ps.executeUpdate();
            this.groupId = 0;
        }
    }

    public void savetoDB(Connection conn) throws SQLException {
        if (this.groupId == 0) {
            String sql = "INSERT INTO User_group(name) VALUES(?)";
            String generatedColumns[] = {"ID"};
            PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
            ps.setString(1, this.name);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())  {
                this.groupId = rs.getInt(1);
            }

        } else {
            String sql = "UPDATE User_group SET name=? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.name);
            ps.setInt(2, this.groupId);
            ps.executeUpdate();

        }
    }

    public String printLoadedGroup() {
        return ("ID Grupy: " + groupId + " | Nazwa: " + name);

    }

    public String printAllGroups() {
        return ("ID Grupy: " + groupId + " | Nazwa: " + name);

    }

    @Override
    public String toString() {
        return this.groupId + this.name;
    }
}


