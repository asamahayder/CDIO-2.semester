import dal.IUserDAO;
import dal.UserDAOimpls185095;
import dto.UserDTO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAOimpls185095();
        UserDTO newUser = new UserDTO();
        UserDTO oldUser = new UserDTO();
        UserInterface ui = new UserInterface(IDAO, newUser, oldUser);
        ui.showmenu();


    }
}
