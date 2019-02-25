
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SessionModel {
    private String username, name,last_name, email,avatar;
    
    public void setData(ResultSet rs) throws SQLException{
        this.username = rs.getString("user_username");
        this.name = rs.getString("user_name");
        this.last_name = rs.getString("user_lastname");
        this.email = rs.getString("user_email");
        this.avatar = rs.getString("user_avatar");
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
    public String toString(){
        String json = "{"+"}";
        return json;
    }
}
