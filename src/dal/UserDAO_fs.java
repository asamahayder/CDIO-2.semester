package dal;
import dto.User;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_fs implements IUserDAO {


    @Override
    public User getUser(int userId) throws DALException {
        User user = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.home") + "/Desktop/users/" + userId + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            user = (User) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("User class not found");
            e.printStackTrace();

        }
        return user;
    }

    @Override
    public List<User> getUserList() throws DALException {
        ArrayList<User> users = new ArrayList<>();
        User user;
        int numberOfUsers = new File(System.getProperty("user.home") + "/Desktop/users/").list().length;
        int userId = 1;
        int numberOfImportedUsers = 0;
        try {
            FileInputStream fileInputStream;
            ObjectInputStream objectInputStream;
            while (numberOfImportedUsers < numberOfUsers){
                boolean fileExists = new File(System.getProperty("user.home") + "/Desktop/users/" + userId + ".ser").exists();
                if (fileExists) {
                    fileInputStream = new FileInputStream(System.getProperty("user.home")+ "/Desktop/users/" + userId + ".ser");
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    user = (User) objectInputStream.readObject();
                    users.add(user);
                    userId++;
                    numberOfImportedUsers++;
                    fileInputStream.close();
                    objectInputStream.close();
                }
                else{
                    userId++;
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return  users;
    }

    @Override
    public void createUser(User user) throws DALException {

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.home") + "/Desktop/users/" + user.getUserId() + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){

        }


    }

    @Override
    public void updateUser(User user) throws DALException {
        deleteUser(user.getUserId());
        createUser(user);

    }

    @Override
    public void deleteUser(int userId) throws DALException {
        File file = new File(System.getProperty("user.home")+"/Desktop/users/"+userId+".ser");
        Boolean deleted = file.delete();
        if (!deleted){
            System.out.println("failed to delete the file");
        }
    }

    @Override
    public void createConnection() throws SQLException {

    }

    @Override
    public void closeConnection() throws SQLException {

    }

    @Override
    public String createRoleString(User user) {
        StringBuilder rolesStringBuilder = new StringBuilder();
        List<String> roleListFromUser = user.getRoles();
        for (int i = 0; i < roleListFromUser.size() ; i++) {
            rolesStringBuilder.append(roleListFromUser.get(i));
            if (i != roleListFromUser.size() - 1) {
                rolesStringBuilder.append(", ");
            }
        }
        String roles = rolesStringBuilder.toString();
        return roles;
    }

    public void createDirectory(){
        File newDirectory = new File(System.getProperty("user.home") + "/Desktop/users");
        if (newDirectory.exists()){
            return;
        }else{
            newDirectory.mkdir();
            System.out.println("Skabte en mappe ved navn 'users' på skrivebordet.");
        }
    }

    public int getNextAvailableID(){
        int currentId = 1;
        boolean idTaken;
        do {
            File file = new File(System.getProperty("user.home")+"/Desktop/users/"+currentId+".ser");
            if (file.exists()){
                idTaken=true;
                currentId++;
            }else {
                idTaken=false;
            }
        }while (idTaken);
        return currentId;
    }
}
