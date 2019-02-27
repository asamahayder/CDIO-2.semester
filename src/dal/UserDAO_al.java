package dal;

import dto.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO_al implements IUserDAO {

    private List<User> userList = new ArrayList<>();


    @Override
    public User getUser(int userId) {
        User user = new User(0);

        user.setUserId(user.getUserId());
        user.setUserName(user.getUserName());
        user.setIni(user.getIni());
        user.setRoles(user.getRoles());
        user.setCpr(user.getCpr());
        user.setPassword(user.getPassword());


        /*for (User _user : userList)
            if (_user.getUserId() == userId) {
                System.out.println(userList.get(userId-1));
                return user;
            }*/
        return user;
    }

    @Override
    public List<User> getUserList() {
        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; i < userArrayList.size(); i++) {
            User user = new User(0);
            userArrayList.add(user);

        }

        /*for (int i = 0; i < userList.size(); i++){
            System.out.println(userList.get(i));
        }*/

        return userList;
    }

    @Override
    public void createUser(User user)  {
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
