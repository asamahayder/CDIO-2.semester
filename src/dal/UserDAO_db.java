package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.*;

public class UserDAO_db implements IUserDAO {

    //Alle variabler der bliver brugt

    //Connection og statement, for at bruge database metoderne
    private Connection connection;
    private Statement statement;

    public UserDAO_db() throws SQLException {
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
            showUser = statement.executeQuery("SELECT * FROM CDIO1 WHERE userid = '" + userID + "'");
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
            resultSet = statement.executeQuery("SELECT * FROM CDIO1");
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
        user.setIni(resultSet.getString(3));
        user.setCpr(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setRoles(Collections.singletonList(resultSet.getString(6)));

        System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) +
                resultSet.getString(4) + resultSet.getString(5) + resultSet.getString(6))
        ;
    }

    @Override
    //Opretter en bruger i databasen ud fra user objektet
    public void createUser(UserDTO user) throws DALException {
        //Definerer strengen som bliver brugt til at sende informationen ind i databasen
        String createUser = "INSERT INTO CDIO1 (userid, username, ini, cpr, pass, roles) " +
                "VALUES('" + user.getUserId() + "', '" + user.getUserName() + "', '" + user.getIni() + "', '" + user.getCpr() + "', '" + user.getPassword() + "', '" + user.getRoles()+ "')";
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
        String updateByID = "UPDATE CDIO1 SET username='" + user.getUserName() + "', ini='" + user.getIni() + "', cpr='" + user.getCpr() + "', pass='" + user.getPassword() + "', roles='" + user.getRoles() + "'" +
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
        String deleteByUserID = "DELETE FROM CDIO1 WHERE userid = '" + userId + "'";
        try {
            //bruger strengen til at opdatere databasen
            statement.executeUpdate(deleteByUserID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
