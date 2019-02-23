/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
/**
 *
 * @author Hijos
 */
public class DBConnection {
		private Timestamp ts;
	private Connection con;
	private PreparedStatement pstm;
	private ResultSet rs;
	private int res;
        
	public DBConnection(String driver, String url, String usr, String pwd) {
		try {
                    Class.forName(driver);
                    this.con = DriverManager.getConnection(url, usr, pwd);
		} catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
		}
	}
	
  //Select simples para comprobaciones
  public ResultSet execute(String query, Object... values) {
    try {
      this.pstm = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      for (int i = 0; i < values.length; i++) {
        this.pstm.setObject(i + 1, values[i]);
      }
      this.rs = this.pstm.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return this.rs;
  }

  //Sentencias de modificaciones a la DB
  public int update(String query, Object... values) {
    try {
      this.pstm = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

      for (int i = 0; i < values.length; i++) {
        this.pstm.setObject(i + 1, values[i]);
      }
      this.res = this.pstm.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return this.res;
  }

  public Timestamp currentTimestamp() {
    ts = new Timestamp(System.currentTimeMillis());
    return ts;
  }
	
	//Cerrar conexiones
	public void closeCon() {
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
