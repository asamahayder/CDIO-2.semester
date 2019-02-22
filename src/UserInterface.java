import dal.*;
import dto.*;

import java.sql.*;
import java.util.Scanner;

public class UserInterface {
    IUserDAO IDAO;
    UserDTO User;

    public UserInterface(IUserDAO IDAO, UserDTO User) {
        this.IDAO=IDAO;
        this.User=User;
    }

    public void showmenu() throws IUserDAO.DALException, SQLException {
        Scanner menuScanner = new Scanner(System.in);
        boolean menuIsOn = true;

        MainMenuText();
        while (menuIsOn) {
            int menuTal = 0;
            menuTal = menuScanner.nextInt();

            switch (menuTal) {
                case 1:
                    IDAO.getUserList();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 2:
                    //IDAO.getUser();
                    PressEnterToContinue(menuScanner);
                    break;
                case 3:
                    IDAO.createUser(User);
                    PressEnterToContinue(menuScanner);
                    break;
                case 4:
                    //IDAO.deleteUser();
                    PressEnterToContinue(menuScanner);
                    break;
                case 5:
                    IDAO.updateUser(User);
                    PressEnterToContinue(menuScanner);
                    break;
                case 6:
                    menuIsOn = false;
                    System.out.println("Goodbye!");
                    break;

            }
            if (menuIsOn = true) MainMenuText();
        }
    }

    private void PressEnterToContinue(Scanner menuScanner) {
        System.out.println("Indtast noget for at forsætte");
        menuScanner.nextLine();
    }


    private void MainMenuText() {
        System.out.println("########################################");
        System.out.println("Dette er Aflevering1 databasen");
        System.out.println("Indtast:");
        System.out.println("1: Se listen over databasen");
        System.out.println("2: Hent en specifik bruger i databasen");
        System.out.println("3: Opret en bruger i databasen");
        System.out.println("4: Slet en bruger i databasen");
        System.out.println("5: Opdater en bruger i databasen");
        System.out.println("6: Afslut programmet");
        System.out.println("########################################");
    }

}

