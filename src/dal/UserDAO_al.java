package dal;
import dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserDAO_al implements IUserDAO {

    private List<User> userList = new ArrayList<>();

    @Override
    public User getUser(int userId) {
        User user = new User(0);
        for (User userfromlist:userList){
            if (userfromlist.getUserId()==userId){
                user=userfromlist;
            }
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }



    @Override
    public void createUser(User user)  {
        userList.add(user);
    }

    @Override
    public void updateUser(User user) {
        userList.set(user.getUserId()-1, user);
    }

    @Override
    public void deleteUser(int userId) {

        userList.remove(userId-1);
    }

    @Override
    public void createConnection() throws SQLException {

    }

    @Override
    public void closeConnection() throws SQLException {

    }
}
