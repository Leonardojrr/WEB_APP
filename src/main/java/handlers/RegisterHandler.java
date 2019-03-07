package handlers;

import Models.ResponseModel;
import Models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    ObjectMapper objM = new ObjectMapper();
    prpReader = PropReader.getInstance();
    db = new DBConnection();
    jackson = new SuperMapper();
    String resp = "";
     ResponseModel msgToUser = new ResponseModel();

    System.out.print("Response:" + resp);
    try {
      UserModel user = jackson.jsonToPlainObj(request, UserModel.class);
      boolean isValid = !db.validateUser(prpReader.getValue("checkUser"), user.getUsername(),user.getEmail());
                if(isValid){
                db.update(prpReader.getValue("registerUser"),user.getUsername(),
                Encrypter.getMD5(user.getPassword()),user.getName(),user.getLastName(),user.getEmail(),user.getBirthday(),db.currentTimestamp(),
                user.isSex());
                msgToUser.setStatus(200);
                msgToUser.setMessage("Registro exitoso");
                resp = objM.writeValueAsString(msgToUser);
                }
                else{
                msgToUser.setStatus(401);
                msgToUser.setMessage("Usuario o Email ya registrado");
                resp = objM.writeValueAsString(msgToUser);
                }
      db.closeCon();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resp;
  }
}
