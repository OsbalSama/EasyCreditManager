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
public class Credit {

    int id;

    int idClient;
    double amount;
    double intereses;
    double moratorio;

    Date solicitado;
    Date autorized;
    Date finished;

    Date created;
    Date edited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getMoratorio() {
        return moratorio;
    }

    public void setMoratorio(double moratorio) {
        this.moratorio = moratorio;
    }

    public Date getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Date solicitado) {
        this.solicitado = solicitado;
    }

    public Date getAutorized() {
        return autorized;
    }

    public void setAutorized(Date autorized) {
        this.autorized = autorized;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
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

    public Credit() {
        this.setCreated();
        this.setEdited();
    }

    
}
