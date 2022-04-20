/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ATENEA
 */
public final class User {

    int id;
    String username;
    String password;
    String role;
    Date created;
    Date edited;
    String dateFormat = "dd/MM/yyyy";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedToString() {
        return new SimpleDateFormat(dateFormat).format(created);
    }
    
    public void setCreated() {
        this.created = new Date();
    }

    public void setCreated(String date) {
        try {
            this.created = new SimpleDateFormat(dateFormat).parse(date);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Date getEdited() {
        return edited;
    }

    public String getEditedToString() {
        return new SimpleDateFormat(dateFormat).format(edited);
    }
    
    public void setEdited() {
        this.edited = new Date();
    }

    public void setEdited(String date) {
        try {
            this.edited = new SimpleDateFormat(dateFormat).parse(date);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public User() {
        this.setCreated();
        this.setEdited();
    }

    ;
    public void craftUser(String[] data) {
        this.setId(Integer.parseInt(data[0]));
        this.setUsername(data[1]);
        this.setPassword(data[2]);
        this.setRole(data[3]);
        if (data[4] != null) {
            this.setCreated(data[4]);
        }
        if (data[5] != null) {
            this.setCreated(data[5]);
        }
    }

    @Override
    public String toString() {
        String resp = id + ","
                + username + ","
                + password + ","
                + role + ","
                + new SimpleDateFormat(dateFormat).format(created) + ","
                + new SimpleDateFormat(dateFormat).format(edited) + ",";
        return resp;
    }
//    public String toString() {
//        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", created=" + created + ", edited=" + edited + '}';
//    }

}
