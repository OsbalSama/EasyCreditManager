/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bonum
 */
public class DatEmpresa {

    String archivo = "source\\DBS_0009.dat";

    public String getArchivo() {
        return archivo;
    }

    public String[] detDBDatEmpresa() {
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

    public String[] getDatEmpresa() {
        String db[] = detDBDatEmpresa();
        return db[0].split(",");
    }

    public void setDatEmpresa(String nReg) {
        try {
            String[] condig_dat = detDBDatEmpresa();
            condig_dat[0] = nReg;
            FileWriter save = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(save);
            for (int i = 0; i < condig_dat.length; i++) {
                bw.write(new Encryptor().EncryptData(condig_dat[i]));
                bw.newLine();
            }
            bw.close();
            save.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[] getIntereses() {
        String[] db = detDBDatEmpresa();
        return db[1].split(",");
    }

    public void setIntereses(String nReg) {
        try {
            String[] condig_dat = detDBDatEmpresa();
            condig_dat[1] = nReg;
            FileWriter save = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(save);
            for (int i = 0; i < condig_dat.length; i++) {
                bw.write(new Encryptor().EncryptData(condig_dat[i]));
                bw.newLine();
            }
            bw.close();
            save.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String datEmpresatoString() {
        String[] datos = getDatEmpresa();
        String resp = "---DATOS EMPRESA---\n"
                + "NOMBRE EMPRESA: " + datos[1] + "\n"
                + "RFC: " + datos[2] + "\n"
                + "NOMBRE ENCARGADO: " + datos[3] + "\n"
                + "DIRECCION: " + datos[4] + "\n"
                + "TELEFONO: " + datos[5];
        return resp;
    }

}
