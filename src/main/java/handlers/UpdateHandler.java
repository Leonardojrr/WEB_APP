
package handlers;

import Models.MessageModel;
import Models.SessionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import utils.DBConnection;
import utils.PropReader;
import utils.SuperMapper;

public class UpdateHandler {
  private DBConnection db;
  private PropReader prpReader;
  private SuperMapper jackson;
  
  public String updateUser(HttpServletRequest request) throws SQLException, IOException {
    ObjectMapper objM = new ObjectMapper();
    prpReader = PropReader.getInstance();
    db = new DBConnection(prpReader.getValue("dbDriver"), prpReader.getValue("dbUrl"), prpReader.getValue("dbUser"), prpReader.getValue("dbPassword"));
    jackson = new SuperMapper();
    String resp=""; 
    SessionModel userSession = jackson.jsonToPlainObj(request, SessionModel.class);
    HttpSession session = request.getSession();
    session.setAttribute("user", userSession);
    db.update(prpReader.getValue("updateUser"), userSession.getName(),userSession.getLast_name(),userSession.getEmail(),userSession.getBirthday(),userSession.getAvatar(),userSession.isSex(),userSession.getUsername());
    
    MessageModel msgToUser = new MessageModel();
    msgToUser.setStatus(200);
    msgToUser.setMessage("Update Successful");
    msgToUser.setData(userSession);
    
    resp = objM.writeValueAsString(msgToUser);
    return resp;
  }
}
