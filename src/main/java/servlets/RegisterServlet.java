/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import handler.RegisterHandler;
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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    RegisterHandler newUser = new RegisterHandler();
    PrintWriter out = response.getWriter();

    try {
      String status = newUser.insertUser(request);
      switch (status) {
        case "Ok":
          out.print(200);
          response.setStatus(200);
          break;
        case "Error":
          out.print(409);
          response.setStatus(409);
          break;
        default:
          out.print(500);
          response.setStatus(500);
          break;
      }
    } catch (SQLException ex) {
      Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
}
