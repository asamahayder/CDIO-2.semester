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

    private Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185095?" + "user=s185095&password=qSmM4qcR0JF1sAnR6OZss");
    private Statement statement = connection.createStatement();
    private ResultSet resultSet = statement.executeQuery("SELECT * FROM aflevering1");
    private ResultSet showUser = statement.executeQuery("SELECT * FROM aflevering1 WHERE id = '" + userId + "'");
    private Scanner sqlScanner = new Scanner(System.in);
    private String deleteByUserID = "DELETE FROM aflevering WHERE userid = '" + userId + "'";
    private String updateByID = "UPDATE aflevering1 SET username='" + username + "', roles='" + roles + "', ini='" + ini + "' WHERE userid='" + userId + "'";
    private String createUser = "INSERT INTO aflevering1 (userID, username, roles, ini) VALUES('" + userId + "', '" + username + "', '" + roles + "', '" + ini + "')";




    public UserDAOimpls185095() throws SQLException {
    }

    @Override
    //Færdigt?
    public UserDTO getUser(int userID) throws DALException, SQLException {
        this.userId=userID;
        System.out.println(showUser.getString(1) + ": " + resultSet.getString(2) + ": "+
                resultSet.getString(3) + ": " + resultSet.getString(4));
        return null;
    }

    @Override
    //Færdigt?
    public List<UserDTO> getUserList() throws DALException, SQLException {
        //Viser en liste ud fra hvad er i databasen
        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2) + ": "+
                    resultSet.getString(3) + ": " + resultSet.getString(4));
        }
        resultSet.close();
        return null;
    }

    @Override
    //Færdigt?
    public void createUser(UserDTO user) throws DALException, SQLException {
        //Indtaster et UserID
        userId = user.getUserId();
        user.setUserId(userId);

        //Indtaster et brugernavn
        username = user.getUserName();
        user.setUserName(username);

        //Indtaster en rolle
        roles = user.getRoles();
        user.setRoles(roles);

        //Indtaster et ini
        ini = user.getIni();
        user.setIni(ini);

        //Laver en bruger i Databasen
        statement.executeUpdate(createUser);
    }

    @Override
    //Færdigt?
    public void updateUser(UserDTO user) throws DALException, SQLException {
        //Brugeren vælger et userid hvor han vil ændre på
        userId = sqlScanner.nextInt();
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
        statement.executeUpdate(updateByID);

    }

    @Override
    //Færdigt?
    public void deleteUser(int userId) throws DALException, SQLException {
        this.userId=userId;
        statement.executeUpdate(deleteByUserID);
    }
}
