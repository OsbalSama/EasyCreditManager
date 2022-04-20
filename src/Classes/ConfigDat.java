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
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
public class ConfigDat {

    String archivo = "config\\config.dat";

    public String getArchivo() {
        return archivo;
    }

    public void resetLogo() {
        try {
            Path origen = FileSystems.getDefault().getPath("iconos\\Logo.png");
            File f = new File("iconos\\LogoCOPY.png");
            File file2 = new File("iconos\\LOGO.png");
            file2.createNewFile();
            f.renameTo(file2);
            Path destino = FileSystems.getDefault().getPath(f.getAbsolutePath());
            Files.copy(destino, origen, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar imagen: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadLogo() {
        try {
            Path origen = FileSystems.getDefault().getPath("iconos\\Logo.png");
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filtro_PNG = new FileNameExtensionFilter("AImagenes PNG", "PNG", "png");
            chooser.setFileFilter(filtro_PNG);
            chooser.setAcceptAllFileFilterUsed(false);
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                File f = new File(chooser.getSelectedFile().getAbsolutePath());
                File file2 = new File("iconos\\LOGO.png");
                file2.createNewFile();
                f.renameTo(file2);
                Path destino = FileSystems.getDefault().getPath(f.getAbsolutePath());
                Files.copy(destino, origen, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar imagen: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isActivated() {
        boolean resp = false;
        String[] Config = getDatLic();
        if (Config[0].equals("activado")) {
            resp = true;
        }
        return resp;
    }

    public void upDayTryalVersion() {
        try {
            String[] Config = getDatLic();
            Config[Config.length - 1] = (getTryalDays() + 1) + "";
            String nreg = "";
            for (int i = 0; i < Config.length; i++) {
                nreg += Config[i] + ",";
            }
            setDatLic(nreg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setFirstUse(String Estado) {
        try {
            String[] Config = getDatLic();
            Config[0] = Estado;
            String nreg = "";
            for (int i = 0; i < Config.length; i++) {
                nreg += Config[i] + ",";
            }
            setDatLic(nreg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setLicData(String user, String email, String serial) {
        try {
            String[] Config = getDatLic();
            Config[0] = "activado";
            Config[1] = user;
            Config[2] = email;
            Config[3] = serial;
            String nreg = "";
            for (int i = 0; i < Config.length; i++) {
                nreg += Config[i] + ",";
            }
            setDatLic(nreg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setDatLic(String nreg) {
        try {
            String[] database = getConfigDatContent();
            database[0] = nreg;
            FileWriter w = new FileWriter(this.getArchivo());
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 0; i < database.length; i++) {
                bw.write(new Encryptor().EncryptData(database[i]));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getTryalDays() {
        String[] dl = getDatLic();
        return Integer.parseInt(dl[4]);
    }

    public String[] getDatLic() {
        String resp = "";
        try {
            String cadena;
            FileReader f = new FileReader(getArchivo());
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                resp = (new Encryptor().DecryptData(cadena));
            }
            b.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp.split(",");
    }

    public String[] getConfigDatContent() {
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

    public void showConfigDatContent() {
        try {
            String[] condig_dat = getConfigDatContent();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ImageIcon getNotifics(int Width, int Height) {
        File f = new File("iconos\\notificIconPNG.png");
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getNonNotifics(int Width, int Height) {
        File f = new File("iconos\\notificIcon2PNG.png");
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    //ICONOS E IMAGENES
    public ImageIcon getLogo(int Width, int Height) {
        File f = new File("iconos\\Logo.png");
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getLevelGrantedIcon(int Width, int Height) {
        File f = new File("iconos\\LevelGranted.png");
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getLevelLockedIcon(int Width, int Height) {
        File f = new File("iconos\\LevelLocked.png");
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getLevelAdminIcon(int Width, int Height) {
        File f = new File("iconos\\LevelAdmin.png");
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }
    
    public ImageIcon getExample(int Width, int Height) {
        File f = new File("iconos\\Example.png");
        ImageIcon icon = new ImageIcon(f.getAbsolutePath());
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(Width, Height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }

    public ImageIcon getImageIcon() {
        ImageIcon resp = null;
        try {
            File f = new File("iconos\\appPNG.png");
            resp = new ImageIcon(f.getAbsolutePath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

}
