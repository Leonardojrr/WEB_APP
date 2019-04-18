
package handlers;

import Models.ResponseModel;
import Models.SessionModel;
import Models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import utils.DBConnection;
import utils.Encrypter;
import utils.PropReader;
import utils.SuperMapper;

public class SessionHandler {

  private DBConnection db;
  private PropReader prpReader;
  private SuperMapper jackson;
  private ResultSet rs;
  

  public String loginUser(HttpServletRequest request) throws SQLException, JsonProcessingException {
    prpReader = PropReader.getInstance();
    db = new DBConnection();
    jackson = new SuperMapper();
    String resp = "";
    ResponseModel msgToUser = new ResponseModel();    
    try {
      UserModel user = jackson.jsonToPlainObj(request, UserModel.class);
      rs = db.execute(prpReader.getValue("loginUser"), user.getUsername(), Encrypter.getMD5(user.getPassword()));
      if (rs.next()) {
       SessionModel userSession = new SessionModel();
       userSession.setData(rs);
       HttpSession session = request.getSession();
       session.setAttribute("user", userSession.getUsername());
        System.out.println(session.getAttribute("user").toString());
       msgToUser.setStatus(200);
       msgToUser.setMessage("login Successful");
       msgToUser.setData(userSession);
      } 
      else {
          msgToUser.setStatus(401);
          msgToUser.setMessage("Not Logged In");
      }
      db.closeCon();
    } catch (Exception e) {
      e.printStackTrace();
      msgToUser.setMessage("DB Connection Error");
			msgToUser.setStatus(500);      
    }
    resp = jackson.plainObjToJson(msgToUser);
    return resp;
  }
}
