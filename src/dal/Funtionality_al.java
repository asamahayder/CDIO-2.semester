package dal;

import dto.User;

import java.util.List;

public class Funtionality_al {

    IUserDAO userDAO;

    public Funtionality_al(IUserDAO userDAO) {
        this.userDAO=userDAO;
    }

    public User getUser(int userId){
        User user = new User(0);
        try {
            user = userDAO.getUser(userId);
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }
        return user;
    }

    public void createUser(int ID, String username, String ini, List roles, String CPR, String password){
        User user = new User(ID, username,ini,roles,CPR,password);
        user.setUserName(username);
        user.setUserId(ID);
        user.setCpr(CPR);
        user.setPassword(password);
        user.setIni(ini);
        user.setRoles(roles);


    }

    public void updateUser(int ID, String username, String ini, List roles, String CPR, String password){
        User user = new User(0);
        user.setUserName(username);
        user.setUserId(ID);
        user.setCpr(CPR);
        user.setPassword(password);
        user.setIni(ini);
        user.setRoles(roles);

    }

    public void deleteUser(int userID){
        try{
            userDAO.deleteUser(userID);
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }
    }

    public void getUserList(){
        try {
            userDAO.getUserList();
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }
    }


}
