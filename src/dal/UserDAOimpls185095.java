package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.*;

public class UserDAOimpls185095 implements IUserDAO {

    //Alle variabler der bliver brugt

    //Connection og statement, for at bruge database metoderne
    private Connection connection;
    private Statement statement;

    public UserDAOimpls185095() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185095?" + "user=s185095&password=qSmM4qcR0JF1sAnR6OZss");
        statement = connection.createStatement();
    }

    @Override
    //Et userid bliver defineret i parameteren og derefter finder metoden brugeren ud fra det id.
    //Denne metode sætter også den valgte bruger ind i objektet da den har fundet id'et.
    public UserDTO getUser(int userID) throws DALException {
        UserDTO user = new UserDTO();
        ResultSet showUser;
        try {
            showUser = statement.executeQuery("SELECT * FROM aflevering1 WHERE userid = '" + userID + "'");
            //Sætter nuværende værdier ind i user objektet og viser en tekst af hvilken bruger det id hører sammen med
            while (showUser.next()) {
                ResultSetLoop(showUser, user);
            }
            showUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //returnerer det objekt med de nye værdier
        return user;
    }

    @Override
    //Laver en liste af tabellen som er defineret i connection
    public List<UserDTO> getUserList() throws DALException {
        //Laver en arrayliste og definerer hvad for en query der skal søges efter i databasen
        ResultSet resultSet;
        ArrayList<UserDTO> userList = new ArrayList<>();

        try {
            //Viser en liste ud fra hvad er i databasen og hænger det sammen med nye user objekter for hver række i tabellen
            resultSet = statement.executeQuery("SELECT * FROM aflevering1");
            while (resultSet.next()) {
                UserDTO user = new UserDTO();
                ResultSetLoop(resultSet, user);
                userList.add(user);
                System.out.println(userList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Returnerer listen over alle brugere i tabellen som en arrayliste.
        return userList;
    }

    //Extractet metode: Det er loopen der tjekker rækkerene og indtaster det ind i objekternes plads hvor der er nødvendigt
    private void ResultSetLoop(ResultSet resultSet, UserDTO user) throws SQLException {
        user.setUserId(resultSet.getInt(1));
        user.setUserName(resultSet.getString(2));
        user.setRoles(Collections.singletonList(resultSet.getString(3)));
        user.setIni(resultSet.getString(4));

        System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2) + ": " +
                resultSet.getString(3) + ": " + resultSet.getString(4));
        ;
    }

    @Override
    //Opretter en bruger i databasen ud fra user objektet
    public void createUser(UserDTO user) throws DALException {
        //Definerer strengen som bliver brugt til at sende informationen ind i databasen
        String createUser = "INSERT INTO aflevering1 (userID, username, roles, ini) " +
                "VALUES('" + user.getUserId() + "', '" + user.getUserName() + "', '" + user.getRoles() + "', '" + user.getIni() + "')";
        try {
            //Opdaterer databasen med de nye værdier
            statement.executeUpdate(createUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    //Opdaterer en gammel bruger med ny information ved brug at user objektet
    public void updateUser(UserDTO user) throws DALException {
        //Definerer strengen som bliver brugt til at ændre på informationen, der er en constraint til at kun ændre på de
        //informationer der hærer til et specifikt id
        int userid = user.getUserId();
        String updateByID = "UPDATE aflevering1 SET username='" + user.getUserName() + "', roles='" + user.getRoles() + "', ini='" + user.getIni() + "' " +
                "WHERE userid='" + userid + "'";
        try {
            //Opdaterer databasen ved brug at strengen
            statement.executeUpdate(updateByID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    //Sletter en bruger ud fra deres bruger id
    public void deleteUser(int userId) throws DALException {
        //Definerer strengen med clause koden er i
        String deleteByUserID = "DELETE FROM aflevering1 WHERE userid = '" + userId + "'";
        try {
            //bruger strengen til at opdatere databasen
            statement.executeUpdate(deleteByUserID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
