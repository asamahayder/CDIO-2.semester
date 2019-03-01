import dal.IUserDAO;
import dal.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        //Denne metode kører igennem UserDAO_al, så den gemmer brugerne i et array
        //IUserDAO IDAO = new UserDAO_al();
        //Functionality functionality = new Functionality(IDAO);
        //UserInterface ui = new UserInterface(functionality);
        //ui.showmenu();


        //Denne metode kører igennem UserDAO_db, så den gemmer brugerne i databasen
        //IUserDAO IDAO = new UserDAO_db();
        //Functionality functionality = new Functionality(IDAO);
        //UserInterface ui = new UserInterface(functionality);
        //ui.showmenu();


        //Denne metode kører igennem UserDAO_fs, så den gemmer brugerne i filer på computeren
        IUserDAO IDAO = new UserDAO_fs();
        Functionality functionality = new Functionality(IDAO);
        UserInterface ui = new UserInterface(functionality);
        ui.showmenu();
    }
}