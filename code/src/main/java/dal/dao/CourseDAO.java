package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.Course;

import java.sql.*;

public class CourseDAO extends AbstractDAO<Course> {

    public Course findByNameAndSession(String name,String session){
        Course course = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");

                String sql = "SELECT * FROM Course WHERE name = '" + name + "' AND session = '" + session + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    name = resultSet.getString("name");
                    session = resultSet.getString("session");
                    Date examDate = resultSet.getDate("examDate");
                    String enrollmentKey =resultSet.getString("enrollmentKey");

                    course = new Course(id,name,session,examDate,enrollmentKey);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return course;
    }


}
