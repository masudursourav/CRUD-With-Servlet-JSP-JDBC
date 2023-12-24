package com.learn.servlet.usersm.DAO;

import com.learn.servlet.usersm.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAO {
    public static final String url = "jdbc:mysql://localhost:3306/servletcrud";
    public static final String username = "root";
    public static final String password = "12346";
    private static final String insertUserSQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
    private static final String selectUserById = "select * from users where id = ?";
    private static final String selectAllUsers = "select * from users";
    private static final String deleteUsersSQL = "delete from users where id = ?";
    private static final String updateUsersSQL = "update users set name = ?, email = ?, country = ? where id = ?";
    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
    public void insertUser(User user){
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public boolean updateUser(User user){
        boolean rowUpdate = false;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateUsersSQL);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setInt(4,user.getId());

            rowUpdate = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rowUpdate;
    }

    public User selectUser(int id){
        User user = null;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectUserById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, name, email, country);

            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }


    public List<User> selectAllUsers(){

        List<User> users = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                users.add( new User(id, name, email, country));

            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }


    public boolean deleteUser(int id){
        boolean rowDeleted = false;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUsersSQL);
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rowDeleted;
    }

}

