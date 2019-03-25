
package handlers;

import Models.ResponseModel;
import Models.SessionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import utils.DBConnection;
import utils.DateDB;
import utils.PropReader;
import utils.SuperMapper;

public class UpdateHandler {
  private DBConnection db;
  private PropReader prpReader;
  private SuperMapper jackson;
  
  public String updateUser(HttpServletRequest request) throws SQLException, IOException {
    ObjectMapper objM = new ObjectMapper();
    prpReader = PropReader.getInstance();
    db = new DBConnection();
    jackson = new SuperMapper();
    String resp; 
    SessionModel userSession = jackson.jsonToPlainObj(request, SessionModel.class);
    java.util.Date birthday = DateDB.getBirthdayFromString(userSession.getBirthday());
    HttpSession session = request.getSession();
    session.setAttribute("user", userSession);
    db.update(prpReader.getValue("updateUser"), userSession.getName(),userSession.getLast_name(),userSession.getEmail(),birthday,userSession.isSex(),userSession.getUsername());
    
    ResponseModel msgToUser = new ResponseModel();
    msgToUser.setStatus(200);
    msgToUser.setMessage("Update Successful");
    msgToUser.setData(userSession);
    
    resp = objM.writeValueAsString(msgToUser);
    return resp;
  }
}
