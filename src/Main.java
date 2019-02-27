import dal.Functionaity;
import dal.Funtionality_al;
import dal.IUserDAO;
import dal.UserDAO_db;
import dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        IUserDAO IDAO = new UserDAO_db();
        //Functionaity functionaity = new Functionaity(IDAO);
        Funtionality_al funtionality_al = new Funtionality_al(IDAO);
        UserInterface_al ui_al = new UserInterface_al(funtionality_al);
        //UserInterface ui = new UserInterface(functionaity);
        ui_al.showmenu();
    }
}
