import dal.Functionality;
import dal.IUserDAO;
import dal.UserDAO_db;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAO_db();
        Functionality functionality = new Functionality(IDAO);
        UserInterface ui = new UserInterface(functionality);
        ui.showmenu();
    }
}
