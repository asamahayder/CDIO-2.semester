package dal;

import dto.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO_al implements IUserDAO {

    private List<User> userList = new ArrayList<>();
    private User user;




    @Override
    public User getUser(int userId) {
        for (User _user : userList)
            if (_user.getUserId() == userId) {
                System.out.println(userList.get(userId-1));
                return user;
            }
        return null;
    }

    @Override
    public List<User> getUserList() {

        /*for (int i = 0; i < userList.size(); i++){
            System.out.println(userList.get(i));
        }*/

        return userList;
    }

    @Override
    public void createUser(User user)  {
        /*for (User _user : userList) {
            if (_user.getUserId() == user.getUserId()) {
            }
        }*/
        userList.add(user);


    }

    @Override
    public void updateUser(User user) {

        userList.get(1).setRoles(Collections.singletonList("asd"));
    }

    @Override
    public void deleteUser(int userId) {

        userList.remove(userId-1);
    }
}
