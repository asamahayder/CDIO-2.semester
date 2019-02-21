package dal;

import dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IUserDAO {

    UserDTO getUser(int userId) throws DALException, SQLException;
    List<UserDTO> getUserList() throws DALException, SQLException;
    void createUser(UserDTO user) throws DALException, SQLException;
    void updateUser(UserDTO user) throws DALException, SQLException;
    void deleteUser(int userId) throws DALException, SQLException;

    public class DALException extends Exception {

        /**
         *
         */
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }

}

