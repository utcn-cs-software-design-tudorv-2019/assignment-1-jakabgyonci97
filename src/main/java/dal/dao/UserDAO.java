package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.User;

import java.sql.*;

public class UserDAO extends AbstractDAO<User> {

    public User userLogin(String userName,String password){
        User user = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");
                //System.out.println("User login request authentication!Data: "+userName+" "+password);

                String sql = "SELECT * FROM user WHERE userName = '" + userName + "' AND password = '" + password +"';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    userName = resultSet.getString("userName");
                    password = resultSet.getString("password");
                    int userType = resultSet.getInt("userType");
                    user = new User();
                    user.setId(id);
                    user.setUserName(userName);
                    user.setPassword(password);
                    user.setUserType(userType);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return user;
    }
}
