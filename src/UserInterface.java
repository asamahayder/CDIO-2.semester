import dal.Functionality;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Functionality functionality;


    public UserInterface(Functionality functionality) {
        this.functionality = functionality;
    }

    public void showmenu() throws SQLException {
        Scanner menuScanner = new Scanner(System.in);
        boolean menuIsOn = true;

        MainMenuText();
        while (menuIsOn) {
            int menuTal = 0;
            menuTal = menuScanner.nextInt();

            switch (menuTal) {
                case 1:
                    System.out.println("----Show User List Menu----");
                    functionality.createConnection();
                    functionality.getUserList();
                    printUserList();
                    functionality.closeConnection();
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
                    functionality.createConnection();
                    inputTilDeleteUser(menuScanner);
                    functionality.closeConnection();
                    System.out.println();
                    PressEnterToContinue(menuScanner);
                    break;
                case 5:
                    System.out.println("----Update User Menu----");
                    functionality.createConnection();
                    input("updateUser", menuScanner);
                    functionality.closeConnection();
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
    }

    private void PressEnterToContinue(Scanner menuScanner) {
        System.out.println("Indtast noget for at forsætte");
        menuScanner.nextLine();
        menuScanner.nextLine();
    }


    private void MainMenuText() {
        System.out.println("#################CDIO1##################");
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
        System.out.println("vælg roller: ");
        ArrayList<String> roles = handleRoleChoices(menuScanner);
        System.out.println("indtast CPR: ");
        String CPR = menuScanner.nextLine();
        System.out.println("indtast noget for at generere password");
        String password = functionality.createPassword();
        if (method.equals("createUser")){
            functionality.createUser(ID,username,ini, roles,CPR,password);
        }
        else if (method.equals("updateUser")){
            functionality.updateUser(ID,username,ini, roles,CPR,password);
        }
    }

    private void inputTilDeleteUser(Scanner menuScanner){
        menuScanner.nextLine();
        System.out.println("indtast et userID som skal slettes: ");
        int userID = menuScanner.nextInt();
        functionality.deleteUser(userID);
    }

    private void printUser(){
        System.out.println(" ");
        System.out.println("UserId: " + functionality.user.getUserId());
        System.out.println("Username: " + functionality.user.getUserName());
        System.out.println("Initials: " + functionality.user.getIni());
        System.out.println("CPR: " + functionality.user.getCpr());
        System.out.println("Password: " + functionality.user.getPassword());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < functionality.user.getRoles().size() ; i++) {
            stringBuilder.append(functionality.user.getRoles().get(i));
            if (i!=functionality.user.getRoles().size()-1){
                stringBuilder.append(",");
            }
        }
        String roleString = stringBuilder.toString();
        System.out.println("Roles: " + roleString);
    }

    private void printUserList(){
        for (int i = 0; i < functionality.users.size(); i++) {
            System.out.println(" ");
            System.out.println("UserId: " + functionality.users.get(i).getUserId());
            System.out.println("Username: " + functionality.users.get(i).getUserName());
            System.out.println("Initials: " + functionality.users.get(i).getIni());
            System.out.println("CPR: " + functionality.users.get(i).getCpr());
            System.out.println("Password: " + functionality.users.get(i).getPassword());
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < functionality.users.get(i).getRoles().size() ; j++) {
                stringBuilder.append(functionality.users.get(i).getRoles().get(j));
                stringBuilder.append(",");
            }
            String roleString = stringBuilder.toString();
            System.out.println("Roles: " + roleString);
        }

    }

    private ArrayList<String> handleRoleChoices(Scanner scanner){
        boolean moreRoles = true;
        ArrayList<String> roles = new ArrayList<>();
        do{
            System.out.println("press 1 for 'Master', 2 for 'Student', 3 for 'Admin'");
            int choice = 0;
            do {
                try{
                    choice = scanner.nextInt();

                }catch (InputMismatchException e){
                    scanner.nextLine();
                }
                if (choice != 1 && choice != 2 && choice != 3){
                    System.out.println("invalid input. try again");
                    choice = 0;
                }
            }while(choice <= 0);

            switch (choice) {
                case 1:
                    if (roles.contains("Master")){
                        System.out.println("this role is already chosen");
                        break;
                    }
                    roles.add("Master");
                    break;
                case 2:
                    if (roles.contains("Student")){
                        System.out.println("this role is already chosen");
                        break;
                    }
                    roles.add("Student");
                    break;
                case 3:
                    if (roles.contains("Admin")){
                        System.out.println("this role is already chosen");
                        break;
                    }
                    roles.add("Admin");
                    break;
            }
            System.out.println("add more roles? press y for 'yes' or anything else for 'no'");
            scanner.nextLine();
            String choiceTwo = scanner.nextLine();
            if (choiceTwo.equals("y")) {
                moreRoles = true;
            }else {
                moreRoles =false;
            }
        }while (moreRoles);
        return roles;
    }
}

