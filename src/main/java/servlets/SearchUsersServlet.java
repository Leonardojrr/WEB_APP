/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import handlers.SearchUsers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilio
 */
@WebServlet(name = "SearchUsersServlet", urlPatterns = {"/search"})
public class SearchUsersServlet extends HttpServlet {


  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    SearchUsers search = new SearchUsers();
    PrintWriter out = response.getWriter();
        try{
            response.setContentType("application/json");
            String json = search.searchUsers(request);
            out.write(json);
        } catch (SQLException ex) {
      Logger.getLogger(SearchUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
}
