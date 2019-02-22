package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAOimpls185095 implements IUserDAO {

    private int userId;
    private String username;
    private String ini;
    private List<String> roles;

    private Connection connection;
    private Statement statement;

    public UserDAOimpls185095() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185095?" + "user=s185095&password=qSmM4qcR0JF1sAnR6OZss");
        statement = connection.createStatement();
    }

    @Override
    //Færdigt!!!!!!
    public UserDTO getUser(int userID) throws DALException, SQLException {
        this.userId=userID;
        ResultSet showUser = statement.executeQuery("SELECT * FROM aflevering1 WHERE userid = '" + userId + "'");
        while (showUser.next()) {
            System.out.println(showUser.getString(1) + ": " + showUser.getString(2) + ": " +
                    showUser.getString(3) + ": " + showUser.getString(4));
        }

        return null;
    }

    @Override
    //Færdigt!!!!!!
    public List<UserDTO> getUserList() throws DALException, SQLException {
        //Viser en liste ud fra hvad er i databasen
        ResultSet resultSet = statement.executeQuery("SELECT * FROM aflevering1");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2) + ": "+
                    resultSet.getString(3) + ": " + resultSet.getString(4));
        }
        return null;
    }

    @Override
    //Færdigt? - NOPE! Rollen skal lavest om eller finde ud af hvordan man bruger List<String>,
    //Dog, ville det se såden her ud sikkert, det er bare implementationen som jeg ikke ved hvordan jeg får det til at fungere
    public void createUser(UserDTO user) throws DALException, SQLException {
        //Indtaster et UserID
        userId = user.getUserId();
        //user.setUserId(userId);

        //Indtaster et brugernavn
        username = user.getUserName();
        //user.setUserName(username);

        //Indtaster en rolle
        roles = user.getRoles();
        //user.setRoles(roles);

        //Indtaster et ini
        ini = user.getIni();
        //user.setIni(ini);

        //Laver en bruger i Databasen
        String createUser = "INSERT INTO aflevering1 (userID, username, roles, ini) " +
                "VALUES('" + userId + "', '" + username + "', '" + roles + "', '" + ini + "')";
        statement.executeUpdate(createUser);
    }

    @Override
    //Færdigt? NOPE! Rollen skal lavest om eller finde ud af hvordan man bruger List<String>,
    //Dog, ville det se såden her ud sikkert, det er bare implementationen som jeg ikke ved hvordan jeg får det
    public void updateUser(UserDTO user) throws DALException, SQLException {
        //Brugeren vælger et userid hvor han vil ændre på
        System.out.println("Hvilket userid vil du lave en ædnring på?");
        userId = user.getUserId();
        user.setUserId(userId);

        //Henter brugernavnet, som er hvor brugeren indtaster et nyt brugernavn
        username = user.getUserName();
        user.setUserName(username);

        //Henter rollen, hvor rollen bliver skabt
        roles = user.getRoles();
        user.setRoles(roles);

        //Henter ini'et, hvor ini'et bliver scannet og indtastet
        ini = user.getIni();
        user.setIni(ini);

        //Opdaterer databasen med den nye information, baseret på hvilket userid der blev indputtet
        String updateByID = "UPDATE aflevering1 SET username='" + username + "', roles='" + roles + "', ini='" + ini + "' " +
                "WHERE userid='" + userId + "'";
        statement.executeUpdate(updateByID);

    }

    @Override
    //Færdigt!!!!!!
    public void deleteUser(int userId) throws DALException, SQLException {
        this.userId=userId;
        String deleteByUserID = "DELETE FROM aflevering1 WHERE userid = '" + userId + "'";
        statement.executeUpdate(deleteByUserID);
    }
}
