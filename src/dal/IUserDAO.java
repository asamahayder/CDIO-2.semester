package dal;

import dto.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {

    User getUser(int userId) throws DALException, SQLException;
    List<User> getUserList() throws DALException;
    void createUser(User user) throws DALException;
    void updateUser(User user) throws DALException;
    void deleteUser(int userId) throws DALException;
    void createConnection() throws SQLException;
    void closeConnection() throws SQLException;

    public class DALException extends Exception {

        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }


    }

}

