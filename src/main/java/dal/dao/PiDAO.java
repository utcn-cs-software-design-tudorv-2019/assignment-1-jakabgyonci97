package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.PersonalInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PiDAO extends AbstractDAO<PersonalInformation> {

    public PersonalInformation findByIdStudent(int idStudent){
        PersonalInformation pi = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");

                String sql = "SELECT * FROM PersonalInformation WHERE idStudent = '" + idStudent + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    idStudent = resultSet.getInt("idStudent");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String icn = resultSet.getString("icn");
                    String pnc = resultSet.getString("pnc");

                    pi = new PersonalInformation(id,idStudent,firstName,lastName,icn,pnc);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return pi;
    }


}
