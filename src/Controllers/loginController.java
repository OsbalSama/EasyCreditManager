/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;

/**
 *
 * @author ATENEA
 */
public class loginController {

    private static loginController instance;
    userController allUsers = userController.getInstance();

    private loginController() {

    }

    public static loginController getInstance() {
        if (instance == null) {
            instance = new loginController();
        }
        return instance;
    }

    public boolean ConfirmAcceso(String username, String password) {
        boolean resp = false;
        try {
            User finded = allUsers.findUserByName(username);
            if (finded != null) {
                if (finded.getUsername().equals(username) && finded.getPassword().equals(password)) {
                    resp = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return resp;
    }

    public int tryLogin(String username, String Password) {
        //0 todo OK
        //1 Usuario no encontrado
        //2 Contraseñas no coinciden
        int resp = 0;
        try {
            User finded = allUsers.findUserByName(username);
            if (finded != null) {
                if (!finded.getPassword().equals(Password)) {
                    resp = 2;
                }
            } else {
                resp = 1;
            }
        } catch (Exception e) {
            System.out.println("Error en Login: " + e);
        }
        return resp;
    }
}
