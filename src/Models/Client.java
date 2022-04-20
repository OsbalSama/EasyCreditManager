/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Classes.clientController;
import java.util.Date;

/**
 *
 * @author ATENEA
 */
public class Client {
    
    int id;
    String name;
    String lastname;
    String adress;
    String type;
    String curp;
    String rfc;
    String phone;
    String email;
    Date created;
    Date edited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = new Date();
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited() {
        this.created = new Date();
    }
    
    
    public Client(){
        this.setCreated();
        this.setEdited();
    }
    
    public void craftUser(String[] data) {
        this.setId(Integer.parseInt(data[0]));
        this.setName(data[1]);
        this.setLastname(data[2]);
        this.setAdress(data[3]);
        this.setType(data[4]);
        this.setCurp(data[5]);
        this.setRfc(data[6]);
        this.setEmail(data[7]);
    }
   
    @Override
    public String toString() {
        String resp = id + ","
                + name + ","
                + lastname + ","
                + adress + ","
                + type + ","
                + curp + ","
                + rfc + ","
                + phone + ","
                + email + ","
                + created + ","
                + edited + ",";
        return resp;
    }
}
