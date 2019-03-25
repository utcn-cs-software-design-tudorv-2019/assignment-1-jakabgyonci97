package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.Enrollment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO extends AbstractDAO<Enrollment> {

    public List<Enrollment> findByidStudent(int idStudent){
        List<Enrollment> enrollments = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");

                String sql = "SELECT * FROM enrollment WHERE idStudent = '" + idStudent + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    int idCourse = resultSet.getInt("idCourse");
                    idStudent = resultSet.getInt("idStudent");
                    double finalGrade = resultSet.getDouble("finalGrade");

                    Enrollment enrollment = new Enrollment(id,idCourse,idStudent,finalGrade);
                    enrollments.add(enrollment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return enrollments;
    }
}
