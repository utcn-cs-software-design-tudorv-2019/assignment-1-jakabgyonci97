package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.StudentActivity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentActivityDAO extends AbstractDAO<StudentActivity> {

    public List<StudentActivity> findByIdStudent(int idStudent){
        List<StudentActivity> studentActivities = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");

                String sql = "SELECT * FROM studentactivity WHERE idStudent = '" + idStudent + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    idStudent = resultSet.getInt("idStudent");
                    Date activityDate = resultSet.getDate("activityDate");
                    String activityType = resultSet.getString("activityType");
                    String description = resultSet.getString("description");

                    studentActivities.add(new StudentActivity(id,idStudent,activityDate,activityType,description));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return studentActivities;
    }
}
