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
public class Prospects {

    String archivo = "source\\DBS_0008.dat";

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
    
    public List getDBProspectsbyState(String indicio1) {
        List<String> resp = new ArrayList<>();
        String[] database = getDBProspects();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] temp = database[i].split(",");
                if (indicio1.equals(temp[8])) {
                    resp.add(database[i]);
                }
            }
        }
        return resp;
    }

    public int getClientProspects(String idClient, int ClientLVL) {
        int resp = 0;
        String[] database = getDBProspects();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] datos = database[i].split(",");
                if (datos[1].equals(idClient) && datos[8].equals("PAGADO")) {
                    resp++;
                }
            }
        }
        return resp;
    }

    public List getDBProspectsbyClient(String indicio1) {
        List<String> resp = new ArrayList<>();
        String[] database = getDBProspects();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] temp = database[i].split(",");
                if (indicio1.equals(temp[1])) {
                    resp.add(database[i]);
                }
            }
        }
        return resp;
    }

    public List getDBProspectsSpecial(String indicio1, String indicio2) {
        List<String> resp = new ArrayList<>();
        String[] database = getDBProspects();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] temp = database[i].split(",");
                if (indicio1.equals(temp[1]) && indicio2.equals(temp[8])) {
                    resp.add(database[i]);
                }
            }
        }
        return resp;
    }

    public List loadProspectStates() {
        String[] database = getDBProspects();
        List<String> resp = new ArrayList<>();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] temp = database[i].split(",");
                if (!resp.contains(temp[8])) {
                    resp.add(temp[8]);
                }
            }
        }
        return resp;
    }

    public void FinishProspect(String idProspect) {
        String[] prospect = getProspectByID(idProspect);
        prospect[8] = "PAGADO";
        String nreg = "";
        for (int i = 0; i < prospect.length; i++) {
            nreg += prospect[i] + ",";
        }
        updateProspect(nreg);
        System.out.println("actualizado");
    }

    public String[] getDBProspects() {
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

    public List getProspectsByIDClient(String idClient) {
        List<String> dbresp = new ArrayList<>();
//        String resp = "";
        try {
            String[] database = getDBProspects();
            if (database != null) {
                for (int i = 0; i < database.length; i++) {
                    String[] temp = database[i].split(",");
                    if (idClient.equals(temp[1])) {
                        dbresp.add(database[i]);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return dbresp;
    }

    public String[] getProspectByID(String id) {
        String resp = "";
        try {
            String[] database = getDBProspects();
            if (database != null) {
                for (int i = 0; i < database.length; i++) {
                    String[] temp = database[i].split(",");
                    if (id.equals(temp[0])) {
                        resp = database[i];
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        if (resp.equals("")) {
            return null;
        } else {
            return resp.split(",");
        }
    }

    //OBTENER IDCLIENTE EN REGISTRO DE PROSPECTO
    public String getProspectIDClient(String idProspect) {
        String resp = "";
        String[] database = getDBProspects();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] datos = database[i].split(",");
                if (datos[0].equals(idProspect)) {
                    resp = datos[1];
                }
            }
        }
        return resp;
    }

    public boolean insProspect(String nuevo_reg) {
        nuevo_reg = new Encryptor().EncryptData(nuevo_reg);
        try {
            String[] database = getDBProspects();
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

    public String genID() {
        try {
            String id = "RTE_";
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
            String[] datos = getDBProspects();
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

    public boolean authorizeProspect(String idProspect, String Estado, String Fecha) {
        try {
            String[] reg = getProspectByID(idProspect);
            reg[8] = Estado;
            reg[9] = Fecha;
            String nreg = "";
            for (int i = 0; i < reg.length; i++) {
                nreg += reg[i] + ",";
            }
            updateProspect(nreg);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public boolean updateProspect(String nuevo_reg) {
        try {
            String[] database = getDBProspects();
            FileWriter w = new FileWriter(getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < database.length; i++) {
                String[] reg_ind = database[i].split(",");
                String[] nreg = nuevo_reg.split(",");
                if (reg_ind[0].equals(nreg[0])) {
                    bw.write(new Encryptor().EncryptData(nuevo_reg));
                    bw.newLine();
                } else {
                    bw.write(new Encryptor().EncryptData(database[i]));
                    bw.newLine();
                }
            }
            bw.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
