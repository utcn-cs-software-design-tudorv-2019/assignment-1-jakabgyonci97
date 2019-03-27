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
                    String group = resultSet.getString("studGroup");
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

    public String createInsertStatement(StudentInformation si){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO studentInformation (idStudent,studGroup,scholarShipState,gradeAvrg) VALUES (");
        sb.append("'").append(si.getIdStudent()).append("',");
        sb.append("'").append(si.getGroup()).append("',");
        sb.append("'").append(si.getScholarShip()).append("',");
        sb.append("'").append(si.getGradeAvrg()).append("');");
        return sb.toString();
    }

    public String createUpdateStatement(StudentInformation si){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE studentInformation SET ");
        sb.append("idStudent = '").append(si.getIdStudent()).append("', ");
        sb.append("studGroup = '").append(si.getGroup()).append("', ");
        sb.append("scholarShipState = '").append(si.getScholarShip()).append("', ");
        sb.append("gradeAvrg = '").append(si.getGradeAvrg()).append("' ");
        sb.append("WHERE id = '").append(si.getId()).append("';");
        return sb.toString();
    }

    public String createDeleteStatement(StudentInformation si){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM studentInformation ");
        sb.append("WHERE id = '").append(si.getId()).append("';");
        return sb.toString();
    }
}
