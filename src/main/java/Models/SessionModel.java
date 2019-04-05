
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SessionModel {
    private String username, name,last_name, email,avatar,birthday;
    private boolean sex;
    private int id;
    
    public void setData(ResultSet rs) throws SQLException{
        this.setId(rs.getInt(1));
        this.setUsername(rs.getString(2));
        this.setName(rs.getString(4));
        this.setLast_name(rs.getString(5));
        this.setEmail(rs.getString(6));
        this.setBirthday(rs.getString(7));
        this.setAvatar(rs.getString(9));
        this.setSex(rs.getBoolean(11));
    }
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
