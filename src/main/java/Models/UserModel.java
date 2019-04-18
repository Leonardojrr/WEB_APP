
package Models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class UserModel {
    private String name,lastName,password,email,username,birthday,avatar;
    private int typeId, id;
    private boolean sex,enabled;
    private Date creationTime;
        //para guardar la info de usuarios registrados
    public void setData(ResultSet rs) throws SQLException{
	  this.setId(rs.getInt(1));
		this.setUsername(rs.getString(2));
		this.setName(rs.getString(5));
		this.setLastName(rs.getString(6));
		this.setEmail(rs.getString(7));
		this.setBirthday(rs.getString(8));
		this.setCreationTime(rs.getDate(9));
		this.setAvatar(rs.getString(10));
		this.setTypeId(rs.getInt(11));
		this.setSex(rs.getBoolean(12));
		this.setEnabled(rs.getBoolean(13));
		this.setPassword(null);
    }


  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
