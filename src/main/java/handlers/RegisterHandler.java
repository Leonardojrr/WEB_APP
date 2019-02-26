package handlers;

import Models.UserModel;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import utils.DBConnection;
import utils.Encrypter;
import utils.PropReader;
import utils.SuperMapper;

public class RegisterHandler {

  private DBConnection db;
  private PropReader prpReader;
  private SuperMapper jackson;

  public String insertUser(HttpServletRequest request) throws SQLException {

    prpReader = PropReader.getInstance();
    db = new DBConnection(prpReader.getValue("dbDriver"), prpReader.getValue("dbUrl"), prpReader.getValue("dbUser"), prpReader.getValue("dbPassword"));
    jackson = new SuperMapper();
    String resp = "";

    System.out.print("Response:" + resp);
    try {
      UserModel user = jackson.jsonToPlainObj(request, UserModel.class);
      boolean isValid = !db.validateUser(prpReader.getValue("checkUser"), user.getUsername(),user.getEmail());
                if(isValid){
                db.update(prpReader.getValue("registerUser"),user.getUsername(),
                Encrypter.getMD5(user.getPassword()),user.getName(),user.getLastName(),user.getEmail(),user.getBirthday(),db.currentTimestamp(),
                user.isSex());
                resp = "Ok";
                }
                else{
                resp = "Error"; 
                }
      db.closeCon();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resp;
  }
}
