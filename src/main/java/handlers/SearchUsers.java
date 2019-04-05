/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import Models.ResponseModel;
import Models.UserModel;
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
  private ResultSet rs;
  private SuperMapper jackson;

  public String searchUsers(HttpServletRequest request) throws SQLException {
    jackson = new SuperMapper();
    prpReader = PropReader.getInstance();
    db = new DBConnection();
    ResponseModel msgToUser = new ResponseModel();
    ArrayList<UserModel> users = new ArrayList<>();
    String resp=""; 
    try {
      rs = db.search(prpReader.getValue("searchUsers"),request.getParameter("name"));
      while (rs.next()) {
        UserModel user = new UserModel();
        user.setData(rs);
        users.add(user);
      } 
      msgToUser.setData(users);
      msgToUser.setMessage("List Returned");
      msgToUser.setStatus(200);
      resp = jackson.plainObjToJson(msgToUser);
    } catch (Exception e) {
      e.printStackTrace();
      msgToUser.setMessage("DB Connection Error");
			msgToUser.setStatus(500);
    }
    db.closeCon();
    return resp;
  }
}