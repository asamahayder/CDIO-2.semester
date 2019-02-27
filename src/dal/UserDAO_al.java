package dal;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
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
       /* user.setUserId(userList.get(userId).getUserId());
        user.setUserName(userList.get(userId).getUserName());
        user.setIni(userList.get(userId).getIni());
        user.setRoles(userList.get(userId).getRoles());
        user.setCpr(userList.get(userId).getCpr());
        user.setPassword(userList.get(userId).getPassword());*/
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

        userList.get(1).setRoles(Collections.singletonList("asd"));
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
