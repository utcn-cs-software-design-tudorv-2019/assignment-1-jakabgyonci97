package bll;

import dal.dao.UserDAO;
import dal.entity.User;

public class LoginBLL {

    public int loginOperation(String userName,String password){
        User user = null;
        UserDAO userDAO = new UserDAO();
        user = userDAO.userLogin(userName,password);
        return user.getUserType();
    }

}
