/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import Model.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import util.DBConnection;
import util.Encrypter;
import util.PropReader;
import util.SuperMapper;

/**
 *
 * @author Emilio
 */
public class SessionHandler {

  private DBConnection db;
  private PropReader prpReader;
  private SuperMapper jackson;
  private ResultSet rs;

  public String loginUser(HttpServletRequest request) throws SQLException {

    prpReader = PropReader.getInstance();
    db = new DBConnection(prpReader.getValue("dbDriver"), prpReader.getValue("dbUrl"), prpReader.getValue("dbUser"), prpReader.getValue("dbPassword"));
    jackson = new SuperMapper();
    String resp = "";
    System.out.print("Response:" + resp);
    try {
      UserModel user = jackson.jsonToPlainObj(request, UserModel.class);
      rs = db.execute(prpReader.getValue("loginUser"), user.getUsername(), Encrypter.getMD5(user.getPassword()));
      if (rs.next()) {
        resp = "Ok";
      } else {
        resp = "Error";
      }
      db.closeCon();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resp;
  }
}
