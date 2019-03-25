/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import Models.ResponseModel;
import Models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import utils.DBConnection;
import utils.PropReader;
import utils.SuperMapper;

/**
 *
 * @author Emilio
 */
public class SearchUsers {
  private DBConnection db;
  private PropReader prpReader;
  private SuperMapper jackson;
  private ResultSet rs;
  

  public String searchUsers(HttpServletRequest request) throws SQLException {
    ObjectMapper objM = new ObjectMapper();
    prpReader = PropReader.getInstance();
    db = new DBConnection();
    jackson = new SuperMapper();
    ResponseModel msgToUser = new ResponseModel();
    		ArrayList<UserModel> users = new ArrayList<>();

    String resp=""; 
    try {
      UserModel user = jackson.jsonToPlainObj(request, UserModel.class);
      rs = db.execute(prpReader.getValue("searchUsers"), user.getUsername(),user.getName(),user.getLastName() );
      while (rs.next()) {
       user.setData(rs);
       users.add(user);
      } 
          msgToUser.setData(users);
          msgToUser.setMessage("List Returned");
          msgToUser.setStatus(200);
          resp = objM.writeValueAsString(msgToUser);
      
      db.closeCon();
    } catch (Exception e) {
      e.printStackTrace();
      msgToUser.setMessage("DB Connection Error");
			msgToUser.setStatus(500);
    }

    return resp;
  }
}
