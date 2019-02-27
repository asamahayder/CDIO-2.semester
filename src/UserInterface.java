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
                    functionality.getUserList();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 2:
                    System.out.println("----Lookup User Menu----");
                    System.out.println("Indtast userid som skal findes");
                    int id = menuScanner.nextInt();
                   // functionality.getUser(id);
                    printUser(id);
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 3:
                    System.out.println("----Create User Menu----");
                    input("createUser");
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 4:
                    System.out.println("----Delete User Menu----");
                    inputTilDeleteUser();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 5:
                    System.out.println("----Update User Menu----");
                    input("updateUser");
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

    private void input(String method){
        Scanner scanner = new Scanner(System.in);
        System.out.println("indtast ID: ");
        int ID = scanner.nextInt();
        System.out.println("indtast username: ");
        scanner.nextLine();
        String username = scanner.nextLine();
        System.out.println("indtast Initialer: ");
        String ini = scanner.nextLine();
        System.out.println("indtast én rolle: ");
        String role = scanner.nextLine();
        System.out.println("indtast CPR: ");
        String CPR = scanner.nextLine();
        System.out.println("indtast password: ");
        String password = scanner.nextLine();
        if (method.equals("createUser")){
            functionality.createUser(ID,username,ini, Collections.singletonList(role),CPR,password);
        }
        else if (method.equals("updateUser")){
            functionality.updateUser(ID,username,ini, Collections.singletonList(role),CPR,password);
        }
        scanner.close();
    }

    private void inputTilDeleteUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("indtast et userID som skal slettes: ");
        int userID = scanner.nextInt();
        functionality.deleteUser(userID);
    }

    private void printUser(int id){
        System.out.println(" ");
        System.out.println("Username: " + functionality.getUser(id).getUserName());
        System.out.println("UserId: " + functionality.getUser(id).getUserId());
        System.out.println("User initials: " + functionality.getUser(id).getIni());
        System.out.println("User CPR: " + functionality.getUser(id).getCpr());
        System.out.println("User password: " + functionality.getUser(id).getPassword());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <functionality.getUser(id).getRoles().size() ; i++) {
            stringBuilder.append(functionality.getUser(id).getRoles().get(i));
            stringBuilder.append(",");
        }
        String roles = stringBuilder.toString();
        System.out.println("roles: " + roles);
    }

}

