package com.learn.servlet.usersm.web;

import com.learn.servlet.usersm.DAO.UserDAO;
import com.learn.servlet.usersm.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
    public UserServlet(){
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action){
            case "/new":
                showNewFrom(req,resp);
                break;
            case "/insert":
                insertUser(req,resp);
                break;
            case "/delete":
                deleteUser(req,resp);
                break;
            case "/edit":
                showEditForm(req,resp);
                break;
            case "/update":
                updateUser(req, resp);
                break;
            default:
                listUser(req,resp);
                break;
        }
    }


    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUser = userDAO.selectAllUsers();
        req.setAttribute("listUser",listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req,resp);
    }
    private void showNewFrom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(("user-form.jsp"));
        dispatcher.forward(req,resp);
    }
    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User newUser = new User(name,email,country);
        userDAO.insertUser(newUser);
        resp.sendRedirect("list");
    }
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user",existingUser);
        dispatcher.forward(req,resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter(("country"));
        User update = new User(id, name, email, country);
        userDAO.updateUser(update);
        resp.sendRedirect("list");
    }
}
