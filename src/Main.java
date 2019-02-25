import dal.Functionaity;
import dal.IUserDAO;
import dal.UserDAO_db;
import dto.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAO_db();
        Functionaity functionaity = new Functionaity(IDAO);
        UserInterface ui = new UserInterface(functionaity);
        ui.showmenu();
    }
}
