import dal.*;

import java.sql.*;
import java.util.Collections;
import java.util.Scanner;

public class UserInterface {
    Functionaity functionality;

    public UserInterface(Functionaity functionality) {
        this.functionality = functionality;
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
                    functionality.createConnection();
                    functionality.getUserList();
                    functionality.closeConnection();
                    printUserList();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 2:
                    System.out.println("----Lookup User Menu----");
                    System.out.println("Indtast userid som skal findes");
                    int id = menuScanner.nextInt();
                    functionality.createConnection();
                    functionality.getUser(id);
                    functionality.closeConnection();
                    printUser();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 3:
                    System.out.println("----Create User Menu----");
                    functionality.createConnection();
                    input("createUser", menuScanner);
                    functionality.closeConnection();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 4:
                    System.out.println("----Delete User Menu----");
                    inputTilDeleteUser(menuScanner);
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 5:
                    System.out.println("----Update User Menu----");
                    input("updateUser", menuScanner);
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 6:
                    System.out.println("----Goodbye!----");
                    menuIsOn = false;
                    break;

            }
            if (!menuIsOn) break;
            else MainMenuText();
        }
        menuScanner.close();
    }

    private void PressEnterToContinue(Scanner menuScanner) {
        System.out.println("Indtast noget for at forsætte");
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

    private void input(String method, Scanner menuScanner){
        System.out.println("indtast ID: ");
        int ID = menuScanner.nextInt();
        System.out.println("indtast username: ");
        menuScanner.nextLine();
        String username = menuScanner.nextLine();
        System.out.println("indtast Initialer: ");
        String ini = menuScanner.nextLine();
        System.out.println("indtast én rolle: ");
        String role = menuScanner.nextLine();
        System.out.println("indtast CPR: ");
        String CPR = menuScanner.nextLine();
        System.out.println("indtast password: ");
        String password = menuScanner.nextLine();
        if (method.equals("createUser")){
            functionality.createUser(ID,username,ini, Collections.singletonList(role),CPR,password);
        }
        else if (method.equals("updateUser")){
            functionality.updateUser(ID,username,ini, Collections.singletonList(role),CPR,password);
        }
    }

    private void inputTilDeleteUser(Scanner menuScanner){
        System.out.println("indtast et userID som skal slettes: ");
        int userID = menuScanner.nextInt();
        functionality.deleteUser(userID);
    }

    private void printUser(){
        System.out.println(" ");
        System.out.println("UserId: " + functionality.user.getUserId());
        System.out.println("Username: " + functionality.user.getUserName());
        System.out.println("User initials: " + functionality.user.getIni());
        System.out.println("User CPR: " + functionality.user.getCpr());
        System.out.println("User password: " + functionality.user.getPassword());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < functionality.user.getRoles().size() ; i++) {
            stringBuilder.append(functionality.user.getRoles().get(i));
            stringBuilder.append(",");
        }
        String roleString = stringBuilder.toString();
        System.out.println("users: " + roleString);
    }

    private void printUserList(){
        for (int i = 0; i < functionality.users.size(); i++) {
            System.out.println(" ");
            System.out.println("UserId: " + functionality.users.get(i).getUserId());
            System.out.println("Username: " + functionality.users.get(i).getUserName());
            System.out.println("User initials: " + functionality.users.get(i).getIni());
            System.out.println("User CPR: " + functionality.users.get(i).getCpr());
            System.out.println("User password: " + functionality.users.get(i).getPassword());
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < functionality.users.get(i).getRoles().size() ; j++) {
                stringBuilder.append(functionality.user.getRoles().get(j));
                stringBuilder.append(",");
            }
            String roleString = stringBuilder.toString();
            System.out.println("users: " + roleString);
        }

    }



}

