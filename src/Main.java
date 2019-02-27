import dal.*;
import dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        //IUserDAO IDAO = new UserDAO_db();
        IUserDAO IDAO = new UserDAO_al();
        //Functionaity functionaity = new Functionaity(IDAO);
        Functionaity funtionality = new Functionaity(IDAO);
        UserInterface_al ui_al = new UserInterface_al(funtionality_al);
        //UserInterface ui = new UserInterface(functionaity);
        ui_al.showmenu();
    }
}
