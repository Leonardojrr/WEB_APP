
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SessionInfoServlet",urlPatterns = {"/session-info"})
public class SessionInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ObjectMapper objM = new ObjectMapper();
        response.setContentType("application/json");
        HttpSession session = request.getSession(false);
        response.getWriter().print(objM.writeValueAsString(session.getAttribute("leo")));
        
        
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    }
}
