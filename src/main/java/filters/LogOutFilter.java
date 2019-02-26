
package filters;

import Models.MessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author XxlrgamersxX
 */
@WebFilter(filterName = "LogOutFilter", servletNames = {"LogOutServlet"})
public class LogOutFilter implements Filter {
 
    public LogOutFilter() {
    }    
    
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest) request;
      HttpServletResponse resp = (HttpServletResponse) response;
      resp.setContentType("application/json");
      String json;
      PrintWriter out = resp.getWriter();
      ObjectMapper objM = new ObjectMapper();
      HttpSession session = req.getSession(false);
      if(session!=null){
          chain.doFilter(req, resp);
      }
      else{
          MessageModel msgToUser = new MessageModel();
          msgToUser.setStatus(401);
          msgToUser.setMessage("Not Logged In");          
          json = objM.writeValueAsString(msgToUser);
          out.print(json);
      }
        
    }

    public void destroy() {        
    }
    public void init(FilterConfig filterConfig) {        
      
    }
}
