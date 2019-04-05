
package handlers;

import Models.ResponseModel;
import Models.SessionModel;
import Models.UserModel;
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
  

  public String loginUser(HttpServletRequest request) throws SQLException {
    prpReader = PropReader.getInstance();
    db = new DBConnection();
    jackson = new SuperMapper();
    String resp=""; 
    try {
      UserModel user = jackson.jsonToPlainObj(request, UserModel.class);
      rs = db.execute(prpReader.getValue("loginUser"), user.getUsername(), Encrypter.getMD5(user.getPassword()));
      if (rs.next()) {
       SessionModel userSession = new SessionModel();
       userSession.setData(rs);
       HttpSession session = request.getSession();
       session.setAttribute("user", userSession);
       ResponseModel msgToUser = new ResponseModel();
       msgToUser.setStatus(200);
       msgToUser.setMessage("login Successful");
       msgToUser.setData(userSession);
       resp = jackson.plainObjToJson(msgToUser);
      } 
      else {
          ResponseModel msgToUser = new ResponseModel();
          msgToUser.setStatus(401);
          msgToUser.setMessage("Not Logged In");
          resp = jackson.plainObjToJson(msgToUser);
      }
      db.closeCon();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resp;
  }
}
