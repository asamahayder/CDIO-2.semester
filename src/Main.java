import dal.*;
import dto.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAO_fs();
        Functionality functionality = new Functionality(IDAO);
        UserInterface ui = new UserInterface(functionality);
        ui.showmenu();

    }
}
