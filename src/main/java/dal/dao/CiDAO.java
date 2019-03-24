package dal.dao;

import dal.connection.ConnectionFactory;
import dal.entity.ContactInformation;
import dal.entity.StudentInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CiDAO extends AbstractDAO<ContactInformation> {

    public ContactInformation findByIdStudent(int idStudent){
        ContactInformation ci = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");

                String sql = "SELECT * FROM ContactInformation WHERE idStudent = '" + idStudent + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    idStudent = resultSet.getInt("idStudent");
                    String address = resultSet.getString("address");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String emailAddress = resultSet.getString("emailAddress");

                    ci= new ContactInformation(id,idStudent,address,phoneNumber,emailAddress);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
        return ci;
    }
}
