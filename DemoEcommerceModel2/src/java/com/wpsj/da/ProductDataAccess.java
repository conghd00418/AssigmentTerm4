/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpsj.da;

import com.wpsj.entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Delll
 */
@WebServlet(name = "ProductDataAccess", urlPatterns = {"/ProductDataAccess"})
public class ProductDataAccess extends HttpServlet {

    private PreparedStatement searchStatement;

    private PreparedStatement getSearchStatement() throws SQLException, ClassNotFoundException {
        if (searchStatement == null) {
            Connection connection = new DBConnection().getConnection();
            searchStatement = connection.prepareStatement("SELECT pro_id, pro_name, pro_desc FROM ProductStore WHERE pro_name like?");
        }
        return searchStatement;
    }

    public List<Product> getProductsByName(String name) {
        try {
            PreparedStatement statement = getSearchStatement();
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            List<Product> products = new LinkedList<Product>();
            while (rs.next()) {
                products.add(new Product(rs.getInt("pro_id"), rs.getString("pro_name"),
                        rs.getString("pro_desc")));
            }
            return products;

        } catch (Exception e) {
        e.printStackTrace();
        return null;
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductDataAccess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDataAccess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
