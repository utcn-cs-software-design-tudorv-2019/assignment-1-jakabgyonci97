package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.StudentInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SiDAO extends AbstractDAO<StudentInformation> {

    public StudentInformation findByIdStudent(int idStudent){
        StudentInformation si = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");

                String sql = "SELECT * FROM StudentInformation WHERE idStudent = '" + idStudent + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    idStudent = resultSet.getInt("idStudent");
                    String group = resultSet.getString("group");
                    int scholarShipState = resultSet.getInt("scholarShipState");
                    double gradeAvrg = resultSet.getDouble("gradeAvrg");

                    si = new StudentInformation(id,idStudent,group,scholarShipState,gradeAvrg);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return si;
    }
}
