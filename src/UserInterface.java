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
        while (menuIsOn == true) {
            int menuTal = 0;
            menuTal = menuScanner.nextInt();

            switch (menuTal) {
                case 1:
                    System.out.println("----Show User List Menu----");
                    IDAO.getUserList();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 2:
                    System.out.println("----Lookup User Menu----");
                    System.out.println("Indtast userid som skal findes");
                    IDAO.getUser(menuScanner.nextInt());
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 3:
                    System.out.println("----Create User Menu----");
                    IDAO.createUser(User);
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 4:
                    System.out.println("----Delete User Menu----");
                    System.out.println("Indtast userid som skal slettes");
                    IDAO.deleteUser(menuScanner.nextInt());
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 5:
                    System.out.println("----Update User Menu----");
                    IDAO.updateUser(User);
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 6:
                    System.out.println("----Goodbye!----");
                    menuIsOn = false;
                    break;

            }
            if (menuIsOn == false) break;
            else MainMenuText();
        }
    }

    private void PressEnterToContinue(Scanner menuScanner) {
        System.out.println("Indtast noget for at forsætte");
        menuScanner.nextLine();
        menuScanner.nextLine();
    }


    private void MainMenuText() {
        System.out.println("#########Aflevering1 databasen##########");
        System.out.println("1: Se listen over databasen            #");
        System.out.println("2: Hent en specifik bruger             #");
        System.out.println("3: Opret en bruger                     #");
        System.out.println("4: Slet en bruger                      #");
        System.out.println("5: Opdater en bruger                   #");
        System.out.println("6: Afslut programmet                   #");
        System.out.println("########################################");
    }

}

