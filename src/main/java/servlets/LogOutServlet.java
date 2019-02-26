
package servlets;

import Models.MessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LogOutServlet",urlPatterns = "/logout")
public class LogOutServlet extends HttpServlet {

      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper objM = new ObjectMapper();
        request.getSession().invalidate();
        MessageModel msgToUser = new MessageModel();
        msgToUser.setMsg(200, "Session finished");
        String json = objM.writeValueAsString(msgToUser);
        response.getWriter().print(json);
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    }
}
