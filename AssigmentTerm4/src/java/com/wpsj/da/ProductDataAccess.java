/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpsj.da;

import com.wpsj.entity.Product;
import com.wpsj.entity.ProductHistory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
    private PreparedStatement searchStt;
    private PreparedStatement insertStt;
    private PreparedStatement updateStt;

     private PreparedStatement getSearchAllStt() throws ClassNotFoundException, SQLException{
        if(searchStt == null){
            Connection con = new DBConnection().getConnection();
            searchStt = 
                    con.prepareStatement(
                            "SELECT * from BookStore");
            
        }
        return searchStt;
    }
    
    private PreparedStatement getSearchStt() throws ClassNotFoundException, SQLException{
        if(searchStt == null){
            Connection con = new DBConnection().getConnection();
            searchStt = 
                    con.prepareStatement(
                            "SELECT * from BookStore where Code like ? or Name like ?");
            
        }
        return searchStt;
    }
    
    private int GetMaxID()throws ClassNotFoundException, SQLException{
        Connection con = new DBConnection().getConnection();
        Statement stt = con.createStatement();
        ResultSet rs= stt.executeQuery("select max(Id) as Id from BookHistory");
        while(rs.next()){
            return rs.getInt("Id");
        }
        
        return -1;
    }
    
    private PreparedStatement getInsertStatement() throws ClassNotFoundException, SQLException{
        if(insertStt == null){
            Connection con = new DBConnection().getConnection();
            insertStt = 
                    con.prepareStatement(
                            "Insert into BookHistory (Id, BookID, CreatedDTG, Status)"
                                    + " values (?, ?, ?, ?)");
        }
        return insertStt;
    }
    
    private PreparedStatement getUpdateStt() throws ClassNotFoundException, SQLException{
        if(updateStt == null){
            Connection con = new DBConnection().getConnection();
            updateStt = 
                    con.prepareStatement(
                            "Update BookStore set Status = ? where Id = ?");
        }
        return updateStt;
    }
    
    private PreparedStatement getSearchHisStt() throws ClassNotFoundException, SQLException{
        if(searchStt == null){
            Connection con = new DBConnection().getConnection();
            searchStt = 
                    con.prepareStatement(
                            "SELECT his.*, bk.Name "
                                    + " from BookHistory his"
                                    + " join BookStore bk on bk.Id = his.BookId "
                                    + " where his.BookId = ?"
                                    + " order by his.CreatedDTG desc");
            
        }
        return searchStt;
    }
    
    public List<Product> getBookByKeyword(String keyword){
        try{
            PreparedStatement stt;
            if (keyword.trim().equals("")){
            stt = getSearchAllStt();
            }else
            {
            stt = getSearchStt();
            stt.setString(1, "%" + keyword + "%");
            stt.setString(2, "%" + keyword + "%");
            }
            ResultSet rs = stt.executeQuery();
            List<Product> books = new LinkedList<Product>();
            while(rs.next()){
                books.add(new Product(rs.getInt("Id"),
                        rs.getString("Code"), 
                        rs.getString("Name"), 
                        rs.getString("Author"),
                        rs.getString("Status")));
            }
            return books;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ProductHistory> getBookHistoriesByID(int id){
        try{
            PreparedStatement stt = getSearchHisStt();
            stt.setInt(1, id);
            ResultSet rs = stt.executeQuery();
            List<ProductHistory> his = new LinkedList<ProductHistory>();
            
            int index = 1;
            while(rs.next()){
                ProductHistory item = new ProductHistory(rs.getString("Name"),
                        rs.getDate("CreatedDTG"),
                        rs.getString("Status"));
                item.setOrderNo(index);
                his.add(item);
                index++;
            }
            return his;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void InsertBorrowHistory(int id, String newStt){
    try{
            PreparedStatement stt = getInsertStatement();
            int maxId = GetMaxID();
            if (maxId == -1){
            throw new Exception("Can't select max Id");
                    }
            
            stt.setInt(1, (maxId + 1));
            stt.setInt(2, id);
            stt.setDate(3, new java.sql.Date(new Date().getTime()));
            stt.setString(4, newStt);
            int rs = stt.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void UpdateBookStatus(int id, String newStt){
        try{
            PreparedStatement stt = getUpdateStt();
            stt.setString(1, newStt);
            stt.setInt(2, id);
            int rs = stt.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
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
