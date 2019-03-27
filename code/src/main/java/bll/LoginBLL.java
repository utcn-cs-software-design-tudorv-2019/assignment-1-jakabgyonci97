package bll;

import dal.dao.StudentDAO;
import dal.dao.UserDAO;
import dal.entity.Student;
import dal.entity.User;

public class LoginBLL {

    public User loginOperation(String userName,String password){
        UserDAO userDAO = new UserDAO();
        if(!loginDataValidator(userName,password))
            return null;
        User user = userDAO.userLogin(userName,password);
        return user;
    }

    public Student loginAsStudent(int idUser){
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.findByIdUser(idUser);
        return student;
    }

    private boolean loginDataValidator(String userName,String password){
        ValidatorResponse vrUserName = Validator.validateUserName(userName,15,Validator.CheckType.CHECK_ALL);
        ValidatorResponse vrPassword = Validator.validatePassword(password,12,Validator.CheckType.CHECK_ALL);

        if(vrUserName.isValid() && vrPassword.isValid())
            return true;
        return false;
    }

}
