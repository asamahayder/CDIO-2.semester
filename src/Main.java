import dal.Functionality;
import dal.IUserDAO;
import dal.UserDAO_al;
import dal.UserDAO_db;
import dal.UserDAO_fs;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IUserDAO.DALException {
        startProgram();
    }

    private static void startProgram() throws SQLException, IUserDAO.DALException {
        Functionality f;
        UserInterface ui;
        boolean menuIsOn = true;
        Scanner menuScanner = new Scanner(System.in);

        menuText();
        int menuTal = menuScanner.nextInt();

        while (menuIsOn) {
            switch (menuTal) {
                case 1:
                    IUserDAO IDAO_db = new UserDAO_db();
                    f = new Functionality(IDAO_db);
                    ui = new UserInterface(f);
                    ui.showmenu();
                    break;
                case 2:
                    IUserDAO IDAO_fs = new UserDAO_fs();
                    ((UserDAO_fs) IDAO_fs).createDirectory();
                    f = new Functionality(IDAO_fs);
                    ui = new UserInterface(f);
                    ui.showmenu();
                    break;
                case 3:
                    IUserDAO IDAO_al = new UserDAO_al();
                    f = new Functionality(IDAO_al);
                    ui = new UserInterface(f);
                    ui.showmenu();
                    break;
                case 4:
                    menuIsOn = false;
                    break;
            }
            if (!menuIsOn) {
                break;
            }
            else {
                menuText();
                menuTal = menuScanner.nextInt();
            }
        }
        menuScanner.close();
    }

    private static void menuText() {
        System.out.println("##########################################");
        System.out.println("Vælg hvilken måde du vil gemme dataet    #");
        System.out.println("1: Database (Database struktur)          #");
        System.out.println("2: Filsystemet (Mappe struktur)          #");
        System.out.println("3: Programmet (Arrayliste struktur)      #");
        System.out.println("4: Luk programmet                        #");
        System.out.println("##########################################");
    }
}
