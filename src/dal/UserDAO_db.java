package dal;

import dto.User;

import java.sql.*;
import java.util.*;

public class UserDAO_db implements IUserDAO {

    //Alle variabler der bliver brugt

    //Connection og statement, for at bruge database metoderne
    private Connection connection;
    private Statement statement;

    public UserDAO_db() throws SQLException {
        createConnection();
    }

    public void createConnection() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185095?" + "user=s185095&password=qSmM4qcR0JF1sAnR6OZss");
        statement = connection.createStatement();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public User getUser(int userID) throws DALException, SQLException {
        User user = null;
        ArrayList<String> roles = new ArrayList<>();
        ResultSet showUser;
        try {
            showUser = statement.executeQuery("SELECT * FROM CDIO1 WHERE userid = '" + userID + "'");
            while (showUser.next()) {
                ResultSetLoop(showUser, user, roles);
            }
            showUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUserList() throws DALException {
        ResultSet showAllUsers;
        ArrayList<User> userList = new ArrayList<>();
        try {
            showAllUsers = statement.executeQuery("SELECT * FROM CDIO1");

            while (showAllUsers.next()) {
                User user = null;
                ArrayList<String> roles = new ArrayList<>();
                ResultSetLoop(showAllUsers, user, roles);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void ResultSetLoop(ResultSet resultSet, User user, ArrayList<String> roles) throws SQLException {
        user.setUserId(resultSet.getInt(1));
        user.setUserName(resultSet.getString(2));
        user.setIni(resultSet.getString(3));
        user.setCpr(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        String roleString = (resultSet.getString(6));
        String[] roleArray = roleString.split(", ");
        for (int i = 0; i <roleArray.length ; i++) {
            roles.add(roleArray[i]);
        }
        user.setRoles(roles);
    }

    @Override
    public void createUser(User user) throws DALException {
        //Definerer strengen som bliver brugt til at sende informationen ind i databasen
        String createUser = "INSERT INTO CDIO1 (userid, username, ini, cpr, pass, roles) " +
                "VALUES('" + user.getUserId() + "', '" + user.getUserName() + "', '" + user.getIni() + "', '" + user.getCpr() + "', '" + user.getPassword() + "', '" + createRoleString(user)+ "')";
        try {
            //Opdaterer databasen med de nye værdier
            statement.executeUpdate(createUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) throws DALException {
        createRoleString(user);
        //Definerer strengen som bliver brugt til at ændre på informationen, der er en constraint til at kun ændre på de
        //informationer der hærer til et specifikt id
        int userid = user.getUserId();
        String updateByID = "UPDATE CDIO1 SET username='" + user.getUserName() + "', ini='" + user.getIni() + "', cpr='" + user.getCpr() + "', pass='" + user.getPassword() + "', roles='" + createRoleString(user) + "'" +
                "WHERE userid='" + userid + "'";
        try {
            //Opdaterer databasen ved brug at strengen
            statement.executeUpdate(updateByID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createRoleString(User user) {
        StringBuilder rolesStringBuilder = new StringBuilder();
        List<String> roleListFromUser = user.getRoles();
        for (int i = 0; i < roleListFromUser.size() ; i++) {
            rolesStringBuilder.append(roleListFromUser.get(i));
            if (i != roleListFromUser.size() - 1) {
                rolesStringBuilder.append(", ");
            }
        }
        String roles = rolesStringBuilder.toString();
        return roles;
    }

    @Override
    public int getNextAvailableID() {
        return 0;
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        //Definerer strengen med clause koden er i
        String deleteByUserID = "DELETE FROM CDIO1 WHERE userid = '" + userId + "'";
        try {
            //bruger strengen til at opdatere databasen
            statement.executeUpdate(deleteByUserID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
