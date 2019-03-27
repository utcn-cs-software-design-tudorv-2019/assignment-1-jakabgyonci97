package dal.dao;

import dal.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class AbstractDAO<T> {
    private final Class<T> type;

    public AbstractDAO(){
        this.type = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public boolean insert(T t){
        Connection connection = null;
        Statement statement = null;
        boolean state = false;
        try{
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");
                statement = connection.createStatement();
                System.out.println(this.createInsertStatement(t));
                statement.executeUpdate(this.createInsertStatement(t));
                state = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return state;
    }

    public String createInsertStatement(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(type.getSimpleName()).append(" (");
        for(Field field: type.getDeclaredFields()){
            if(field.getName().compareTo("id") != 0)
                sb.append(field.getName()).append(", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(", "));
        sb.append(") VALUES (");
        for(Field field: type.getDeclaredFields()){
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if(field.getName().compareTo("id") != 0)
                    sb.append("'").append(value).append("',");
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(");");
        return sb.toString();
    }

    public boolean update(T t){
        Connection connection = null;
        Statement statement = null;
        boolean state = false;
        try{
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");
                statement = connection.createStatement();
                statement.executeUpdate(this.createUpdateStatement(t));
                state = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return state;
    }

    public String createUpdateStatement(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
        Integer id = 0;
        for(Field field: type.getDeclaredFields()){
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if(field.getName().compareTo("id") != 0)
                    sb.append(field.getName()).append(" = '").append(value).append("', ");
                else
                    id = (Integer) value;
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(", "));
        sb.append("WHERE ID = ").append(id.toString()).append(" ;");

        return sb.toString();
    }

    public boolean delete(T t){
        Connection connection = null;
        Statement statement = null;
        boolean state = false;
        try{
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");
                statement = connection.createStatement();
                statement.executeUpdate(this.createDeleteStatement(t));
                state = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return state;
    }

    public String createDeleteStatement(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = '");
        try {
            Field field = type.getDeclaredField("id");
            field.setAccessible(true);
            sb.append(field.get(t).toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sb.append("' ;");
        return sb.toString();
    }

    public T findByID(int ID){
        T searchfor = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");
                String sql = "SELECT * FROM "+type.getSimpleName()+" WHERE id = "+ID;
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                try {
                    List<T> findAll = new ArrayList<>();
                    while (resultSet.next()) {
                        T instance = type.newInstance();
                        for (Field field : type.getDeclaredFields()) {
                            Object value = resultSet.getObject(field.getName());
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                            Method method = propertyDescriptor.getWriteMethod();
                            method.invoke(instance, value);
                        }
                        findAll.add(instance);
                    }
                    if(!findAll.isEmpty())
                        searchfor = findAll.get(0);
                } catch(InstantiationException e) {
                    e.printStackTrace();
                } catch(IllegalAccessException e) {
                    e.printStackTrace();
                } catch(SecurityException e){
                    e.printStackTrace();
                } catch(IllegalArgumentException e){
                    e.printStackTrace();
                } catch(InvocationTargetException e) {
                    e.printStackTrace();
                } catch(SQLException e){
                    e.printStackTrace();
                } catch(IntrospectionException e) {
                    e.printStackTrace();
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
            ConnectionFactory.close(resultSet);
        }
        return searchfor;
    }

    public List<T> findAll(){
        List<T> findResult = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            if(connection != null){
                System.out.println("Connection established!");
                String sql = "SELECT * FROM "+type.getSimpleName();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                try {
                    while (resultSet.next()) {
                        T instance = type.newInstance();
                        for (Field field : type.getDeclaredFields()) {
                            Object value = resultSet.getObject(field.getName());
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                            Method method = propertyDescriptor.getWriteMethod();
                            method.invoke(instance, value);
                        }
                        findResult.add(instance);
                    }
                } catch(InstantiationException e) {
                    e.printStackTrace();
                } catch(IllegalAccessException e) {
                    e.printStackTrace();
                } catch(SecurityException e){
                    e.printStackTrace();
                } catch(IllegalArgumentException e){
                    e.printStackTrace();
                } catch(InvocationTargetException e) {
                    e.printStackTrace();
                } catch(SQLException e){
                    e.printStackTrace();
                } catch(IntrospectionException e) {
                    e.printStackTrace();
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
            ConnectionFactory.close(resultSet);
        }
        return findResult;
    }
}
