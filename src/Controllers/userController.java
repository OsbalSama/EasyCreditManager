/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Classes.Encryptor;
import Models.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ATENEA
 */
public class userController {

    //Global Variables
    String archivo = "source\\DBS_0002.dat";
    List<User> userList;

    public List<User> getUserList() {
        return this.userList;
    }

    //Singleton Instance;
    private static userController instance;

    public static userController getInstance() {
        if (instance == null) {
            instance = new userController();
        }
        return instance;
    }

    protected userController() {
        updateUserList();
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    //Functions & Methods
    //Local List Controller
    public void updateUserList() {
        try {
            userList = new ArrayList<>();
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.getArchivo()));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String[] data = new Encryptor().DecryptData(line).split(",");
                    User temp = new User();
                    temp.craftUser(data);
                    userList.add(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Database Controllers
    public void resetDataBase() {
        System.out.println("Reset Database Loaded");
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
            updateUserList();
            addRootUser();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean updateDataBase() {
        boolean resp = false;
        try {
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < userList.size(); i++) {
                bw.write(new Encryptor().EncryptData(userList.get(i).toString()));
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            updateUserList();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }

        return resp;
    }

    public void addRootUser() {
        try {
            User root = new User();
            root.setUsername("root");
            root.setPassword("root");
            root.setRole("Administrador");
            createUser(root);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int genID() {
        int resp = getRandomNumber(100000, 999999);
        if (findUser(resp) != null) {
            genID();
        }
        return resp;
    }

    public boolean existUser(User User) {
        boolean resp = false;
        if (!userList.isEmpty()) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUsername().equals(User.getUsername())) {
                    resp = true;
                    break;
                }
            }
        }
        return resp;
    }

    public User findUser(int idUser) {
        User resp = null;
        try {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getId() == idUser) {
                    resp = userList.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    public User findUserByName(String username) {
        User resp = null;
        try {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUsername().equals(username)) {
                    resp = userList.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    public boolean createUser(User newUser) {
        boolean resp = false;
        try {
            if (!existUser(newUser)) {
                userList.add(newUser);
                resp = true;
                updateDataBase();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error CreateUser: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    public boolean updateUser(User User) {
        boolean resp = false;
        try {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getId() == User.getId()) {
                    userList.set(i, User);
                    updateDataBase();
                    resp = true;
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    public boolean deleleUser(User User) {
        System.out.println("DelUser");
        try {
            userList.remove(User);
            updateDataBase();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
