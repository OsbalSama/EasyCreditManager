/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

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
 * @author OSBAL
 */
public class CreditHistory {

    String archivo = "source\\DBS_0011.dat";
    //String archivo = "database\\db_credit_history.dat";

    public String getArchivo() {
        return archivo;
    }
    
    public void ClearDB() {
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[] getDBCreditHistory() {
        List<String> database = new ArrayList<>();
        try {
            String cadena;
            FileReader f = new FileReader(getArchivo());
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                database.add(new Encryptor().DecryptData(cadena));
            }
            b.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        if (database.isEmpty()) {
            return null;
        } else {
            String[] resp = new String[database.size()];
            for (int i = 0; i < resp.length; i++) {
                resp[i] = database.get(i);
            }
            return resp;
        }
    }
    
    public String genID() {
        try {
            String id = "";
            for (int i = 0; i < 7; i++) {
                id += (int) (Math.random() * 10);
            }
            if (existID(id)) {
                genID();
            } else {
                return id;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public boolean existID(String id) {
        boolean resp = false;
        try {
            String[] datos = getDBCreditHistory();
            if (datos != null) {
                for (int i = 0; i < datos.length; i++) {
                    String[] sesion = datos[i].split(",");
                    if (sesion[0].equals(id)) {
                        resp = true;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

     public boolean InsCredHistRegistry(String nuevo_reg) {
        nuevo_reg = new Encryptor().EncryptData(nuevo_reg);
        try {
            String[] database = getDBCreditHistory();
            if (database == null) {
                FileWriter w = new FileWriter(this.getArchivo());
                BufferedWriter bw = new BufferedWriter(w);
                bw.write(nuevo_reg);
                bw.newLine();
                bw.close();
            } else {
                FileWriter w = new FileWriter(this.getArchivo());
                BufferedWriter bw = new BufferedWriter(w);
                for (int i = 0; i < database.length; i++) {
                    bw.write(new Encryptor().EncryptData(database[i]));
                    bw.newLine();
                }
                bw.write(nuevo_reg);
                bw.newLine();
                bw.close();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
     
    public String[] getCreditHistoryForClient(String IDClient) {
        String[] resp = null;
        String[] database = getDBCreditHistory();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] temp = database[i].split(",");                
                if (IDClient.equals(temp[2])) {
                    resp = database[i].split(",");
                }
            }
        }
        return resp;
    }
    
    public List getCreditHistoryForProspect(String IDProspect) {
        List<String> resp = new ArrayList<>();
        String[] database = getDBCreditHistory();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] temp = database[i].split(",");
                if (IDProspect.equals(temp[1])) {
                    resp.add(database[i]);
                }
            }
        }
        return resp;
    }

    //OBTENER IDPROSPECTO EN REGISTRO DE HISTORIAL CREDITICIO
    public String getCHistoryIDProspect(String Registry) {
        String resp = "";
        String[] database = getDBCreditHistory();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] datos = database[i].split(",");
                if (datos[0].equals(Registry)) {
                    resp = datos[1];
                }
            }
        }
        return resp;
    }
}
