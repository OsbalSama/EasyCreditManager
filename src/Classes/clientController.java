/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author OSBAL
 */
public class clientController {

    String archivo = "source\\DBS_0003.dat";
    String RutaImagenes = "source\\images\\";

    public String getRutaImagenes() {
        return RutaImagenes;
    }

    public void setRutaImagenes(String RutaImagenes) {
        this.RutaImagenes = RutaImagenes;
    }

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

    public boolean upClientLevel(String idClient, String Level) {
        boolean resp = false;
        String[] cliente = getClientbyID(idClient);
        cliente[11] = Level;
        String nreg = "";
        for (int i = 0; i < cliente.length; i++) {
            nreg += cliente[i] + ",";
        }
        try {
            if (updateClient(nreg)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public String[] getDBClients() {
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

    public String getClientToString(String IDClient) {
        String resp = "--- DATOS DE CLIENTE ---\n";
        String[] client = getClientbyID(IDClient);
        if (client != null) {
            resp += "Nombre: " + client[1] + " " + client[2] + "\n";
            resp += "CURP: " + client[3] + "\n";
            resp += "RFC: " + client[4] + "\n";
            resp += "Tel: " + client[5] + "\n";
            resp += "Email: " + client[6] + "\n";
            resp += "Tipo Persona: " + client[7] + "\n";
            resp += "Direccion: " + client[9] + ", CP. " + client[8] + "\n";
            resp += "Ruta: " + client[10];
        } else {
            resp = "--- SIN CLIENTE SELECCIONADO ---";
        }
        return resp;
    }

    public String getLvlClientToString(String IDClient) {
//        String resp = "--- DATOS DE CLIENTE ---\n";
        String resp = "";
        String[] client = getClientbyID(IDClient);
        if (client != null) {
            resp += "Nombre: " + client[1] + " " + client[2] + "\n";
            resp += "CURP: " + client[3] + "\n";
            resp += "RFC: " + client[4] + "\n";
            resp += "Nivel alcanzado: " + client[11];
        } else {
            resp = "--- SIN CLIENTE SELECCIONADO ---";
        }
        return resp;
    }

    public boolean insClient(String nuevo_reg) {
        nuevo_reg = new Encryptor().EncryptData(nuevo_reg);
        try {
            String[] database = getDBClients();
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

    public int getID() {
        try {
            String id = null;
            for (int i = 0; i < 7; i++) {
                id += (int) (Math.random() * 10);
            }
            if (existID(id)) {
                genID();
            } else {
                return Integer.parseInt(id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    
    
    public String genID() {
        try {
            String id = "CLT_";
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
            String[] datos = getDBClients();
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

    public List loadClientesB() {
        List<String> resp = new ArrayList<>();
        String[] database = getDBClients();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] cuenta = database[i].split(",");
                resp.add(cuenta[0] + " --> " + cuenta[1] + " " + cuenta[2]);
            }
        }
        return resp;
    }

    public List loadClientes() {
        List<String> resp = new ArrayList<>();
        String[] database = getDBClients();
        if (database != null) {
            for (int i = 0; i < database.length; i++) {
                String[] cuenta = database[i].split(",");
                resp.add(cuenta[0]);
            }
        }
        return resp;
    }

    public String[] getClientbyID(String id) {
        String resp = "";
        try {
            String[] database = getDBClients();
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

    public boolean updateClient(String nuevo_reg) {
        try {
            String[] database = getDBClients();
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

    public boolean DelClient(String ClientID) {
        try {
            String[] allUsers = getDBClients();
            BufferedWriter bw = new BufferedWriter(new FileWriter(getArchivo()));
            for (int i = 0; i < allUsers.length; i++) {
                String[] registro = allUsers[i].split(",");
                if (!ClientID.equals(registro[0])) {
                    allUsers[i] = new Encryptor().EncryptData(allUsers[i]);
                    bw.write(allUsers[i]);
                    bw.newLine();
                }
            }
            bw.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public int getClientLevel(String idClient) {
        int resp = 0;
        try {
            String[] cliente = getClientbyID(idClient);
            resp = Integer.parseInt(cliente[11]);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return resp;
    }

    public boolean addClientPicture(String idDish) {
        boolean resp = false;
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filtro_PNG = new FileNameExtensionFilter("AImagenes PNG", "PNG", "png");
            chooser.setFileFilter(filtro_PNG);
            chooser.setAcceptAllFileFilterUsed(false);
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                String rutaDestino = getRutaImagenes() + idDish + ".png";
                new File(rutaDestino).createNewFile();
                Path destino = Paths.get(rutaDestino);
                String rutaOrigen = chooser.getSelectedFile().getAbsolutePath();
                Path origen = Paths.get(rutaOrigen);
                Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                resp = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar imagen: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    public ImageIcon getClientPicture(String idClient, int Width, int Height) {
        String ruta = RutaImagenes + idClient + ".png";
        File f = new File(ruta);
        if (!f.exists()) {
            ImageIcon icon = new ImageIcon(RutaImagenes+"\\user.png");            
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
            return new ImageIcon(newimg);
        } else {
            System.out.println("");
            ImageIcon icon = new ImageIcon(f.getAbsolutePath());
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
            return new ImageIcon(newimg);
        }
    }

}
