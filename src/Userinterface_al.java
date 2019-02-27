import dal.*;

import java.sql.*;
import java.util.Collections;
import java.util.Scanner;

class UserInterface_al {
    Funtionality_al functionality_al;

    public UserInterface_al(Funtionality_al functionality) {
        this.functionality_al = functionality;
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
                    functionality_al.getUserList();
                    prin
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
                    input("createUser", menuScanner);
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
                    input("updateUser",menuScanner);
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
            functionality_al.createUser(ID,username,ini, Collections.singletonList(role),CPR,password);
        }
        else if (method.equals("updateUser")){
            functionality_al.updateUser(ID,username,ini, Collections.singletonList(role),CPR,password);
        }
    }

    private void inputTilDeleteUser(Scanner menuScanner){
        System.out.println("indtast et userID som skal slettes: ");
        int userID = menuScanner.nextInt();
        functionality_al.deleteUser(userID);
    }

    private void printUser(int id) throws IUserDAO.DALException {
        System.out.println(" ");
        System.out.println("Username: " + functionality_al.getUser(id).getUserName());
        System.out.println("UserId: " + functionality_al.getUser(id).getUserId());
        System.out.println("User initials: " + functionality_al.getUser(id).getIni());
        System.out.println("User CPR: " + functionality_al.getUser(id).getCpr());
        System.out.println("User password: " + functionality_al.getUser(id).getPassword());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <functionality_al.getUser(id).getRoles().size() ; i++) {
            stringBuilder.append(functionality_al.getUser(id).getRoles().get(i));
            stringBuilder.append(",");
        }
        String roles = stringBuilder.toString();
        System.out.println("roles: " + roles);
    }



}

