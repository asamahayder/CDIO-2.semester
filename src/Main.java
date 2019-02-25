import dal.IUserDAO;
import dal.UserDAO_db;
import dto.UserDTO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAO_db();
        UserDTO User = new UserDTO();
        UserInterface ui = new UserInterface(IDAO, User);
        ui.showmenu();


    }
}
