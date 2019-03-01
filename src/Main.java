import dal.IUserDAO;
import dal.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        //IUserDAO IDAO = new UserDAO_al();
        //Functionality functionality = new Functionality(IDAO);
        //UserInterface ui = new UserInterface(functionality);
        //ui.showmenu();

        //Nedenstående kode er brugt til at lave et randomized password
        /*PassGen ps = new PassGen();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 20; i++){
            sb.append(ps.randomChar());
        }
        System.out.println(sb);
        */

        IUserDAO IDAO = new UserDAO_fs();
        ((UserDAO_fs) IDAO).createDirectory();
        Functionality functionality = new Functionality(IDAO);
        UserInterface ui = new UserInterface(functionality);
        ui.showmenu();

    }
}
