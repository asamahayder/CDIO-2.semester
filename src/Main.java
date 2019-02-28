import dal.Functionality;
import dal.IUserDAO;
import dal.UserDAO_al;

import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAO_al();
        Functionality functionality = new Functionality(IDAO);
        UserInterface ui = new UserInterface(functionality);
        ui.showmenu();

    }
}
