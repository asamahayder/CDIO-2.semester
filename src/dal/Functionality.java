package dal;

import dto.User;

import java.sql.SQLException;
import java.util.List;

public class Functionality {

    IUserDAO userDAO;

    public User user;
    public List<User> users;

    public Functionality(IUserDAO userDAO) {
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
        setIntoUser(ID, username, ini, roles, CPR, password, user);
        try {
            userDAO.createUser(user);
        }catch (IUserDAO.DALException e){
            e.printStackTrace();
        }

    }

    private void setIntoUser(int ID, String username, String ini, List roles, String CPR, String password, User user) {
        user.setUserName(username);
        user.setUserId(ID);
        user.setCpr(CPR);
        user.setPassword(password);
        user.setIni(ini);
        user.setRoles(roles);
    }

    public void updateUser(int ID, String username, String ini, List roles, String CPR, String password){
        User user = new User(0);
        setIntoUser(ID, username, ini, roles, CPR, password, user);
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
