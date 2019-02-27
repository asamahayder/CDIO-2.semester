package dal;

import dto.User;

import java.sql.SQLException;
import java.util.List;

public class Functionaity {

    IUserDAO userDAO;

    public User user;
    public List<User> users;

    public Functionaity(IUserDAO userDAO) {
        this.userDAO=userDAO;
    }

    public void createConnection() throws SQLException {
        userDAO.createConnection();
    }

    public void closeConnection() throws SQLException{
        userDAO.closeConnection();
    }

    public User getUser(int userId){
        try {
            user = userDAO.getUser(userId);
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }catch (SQLException e){
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
        try {
            userDAO.createUser(user);
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }

    }

    public void updateUser(int ID, String username, String ini, List roles, String CPR, String password){
        User user = new User(0);
        user.setUserName(username);
        user.setUserId(ID);
        user.setCpr(CPR);
        user.setPassword(password);
        user.setIni(ini);
        user.setRoles(roles);
        try {
            userDAO.updateUser(user);
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }
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
            users = userDAO.getUserList();
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }
    }

}
