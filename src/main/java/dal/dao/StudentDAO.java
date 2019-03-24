package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.Student;
import dal.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO extends AbstractDAO<Student> {

    public Student findByIdUser(int idUser){
        Student student = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");

                String sql = "SELECT * FROM student WHERE idUser = '" + idUser + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    idUser = resultSet.getInt("idUser");
                    student = new Student(id,idUser);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return student;
    }


}
