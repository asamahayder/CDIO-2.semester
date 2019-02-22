import dal.IUserDAO;
import dal.UserDAOimpls185095;
import dto.UserDTO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAOimpls185095();
        UserDTO User = new UserDTO();
        UserInterface ui = new UserInterface(IDAO, User);
            //GOOD!
       // ui.showmenu();
            //GOOD!
        //IDAO.getUser(1);
            //GOOD!
        //IDAO.getUserList();
            //Kinda Done
        //IDAO.updateUser(User);
            //GOOD!
        //IDAO.deleteUser(0);


    }
}
