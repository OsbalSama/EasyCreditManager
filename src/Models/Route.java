/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author ATENEA
 */
public class Route {
    int id;
    String state;
    String postalCode;
    String Ciudad;
    String municipio;
    String Colonia;
    String Calle;
    String Reference1;
    String Reference2;
    Date created;
    Date edited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getReference1() {
        return Reference1;
    }

    public void setReference1(String Reference1) {
        this.Reference1 = Reference1;
    }

    public String getReference2() {
        return Reference2;
    }

    public void setReference2(String Reference2) {
        this.Reference2 = Reference2;
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
    
    public Route() {
        this.setCreated();
        this.setEdited();
    }
    
    
}
