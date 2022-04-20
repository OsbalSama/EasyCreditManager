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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author OSBAL
 */
public class LevelAdmin {

    //ARCHIVO DE BASE DE DATOS
    String archivo = "source\\DBS_0014.dat";

    public String getArchivo() {
        return archivo;
    }

    public void ClearDB() {
        try {
            new File(getArchivo()).delete();
            new File(getArchivo()).createNewFile();
            List<String> database = new ArrayList<>();
            database.add("1,0,5000,");
            database.add("2,3,10000,");
            database.add("3,3,15000,");
            database.add("4,3,20000,");
            database.add("5,3,25000,");
            for (int i = 0; i < database.size(); i++) {
                insLevels(database.get(i));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void msgUpLevel(String lvl) {
        JOptionPane.showMessageDialog(null, "El cliente ha accedido al nivel " + lvl);
    }

    public boolean insLevels(String nuevo_reg) {
        nuevo_reg = new Encryptor().EncryptData(nuevo_reg);
        try {
            String[] database = getDBLevels();
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

    public void upClientLevel(String id) {
        try {
            List dbCredHistfClient = new Prospects().getProspectsByIDClient(id);
            if (dbCredHistfClient != null) {
                int clientlevel = new clientController().getClientLevel(id);
                int prospectosPagados = calcRestantes(new clientController().getClientLevel(id), new Prospects().getClientProspects(id, clientlevel));
                String[] Levels = getDBLevels();
                List<String> niveles = new ArrayList<>();
                for (int j = 0; j < Levels.length; j++) {
                    String[] level = Levels[j].split(",");
                    niveles.add(level[1]);
                }
                if (prospectosPagados >= Integer.parseInt(niveles.get(0)) && prospectosPagados < Integer.parseInt(niveles.get(1))) {
                    if (clientlevel < 1) {
                        new clientController().upClientLevel(id, "1");
                        msgUpLevel("1");
                    }
                } else if (prospectosPagados >= Integer.parseInt(niveles.get(1)) && prospectosPagados < Integer.parseInt(niveles.get(2))) {
                    if (clientlevel < 2) {
                        new clientController().upClientLevel(id, "2");
                        msgUpLevel("2");
                    }
                } else if (prospectosPagados >= Integer.parseInt(niveles.get(2)) && prospectosPagados < Integer.parseInt(niveles.get(3))) {
                    if (clientlevel < 3) {
                        new clientController().upClientLevel(id, "3");
                        msgUpLevel("3");
                    }
                } else if (prospectosPagados >= Integer.parseInt(niveles.get(3)) && prospectosPagados < Integer.parseInt(niveles.get(4))) {
                    if (clientlevel < 4) {
                        new clientController().upClientLevel(id, "4");
                        msgUpLevel("4");
                    } else {

                    }
                } else if (prospectosPagados >= Integer.parseInt(niveles.get(4))) {
                    if (clientlevel < 5) {
                        new clientController().upClientLevel(id, "5");
                        msgUpLevel("5");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    public String getMaxCredit(int level) {
        String[] fila = getLevelRow(level);
        String resp = new DecimalFormat("#.00").format(Double.parseDouble(fila[2]));
        return resp;
    }

    //OBTENER TODOS LOS REGISTROS DEL ARCHIVO
    public String[] getDBLevels() {
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

    //OBTENER FILA DE BASE DE DATOS
    public String[] getLevelRow(int number) {
        String[] database = getDBLevels();
        String[] resp = null;
        for (int i = 0; i < database.length; i++) {
            String[] reg = database[i].split(",");
            if (reg[0].equals(String.valueOf(number))) {
                resp = database[i].split(",");
            }
        }
        return resp;
    }

    //OBTENER CADENA DE TEXTO "3/3 Para subir a nivel 2"
    public String getNextLevel(String idClient, int level) {
        String[] database = getDBLevels();
        if (level < database.length) {
            return getRestantProspects(idClient, level) + " Para subir a nivel " + (level + 1);
        } else {
            return "5 Nivel Maximo";
        }
    }

    public String getNewtLevel(int level) {
        String[] database = getDBLevels();
        if (level < database.length) {
            return "0/" + getNextLevelProspects(level) + " Para subir a nivel " + (level + 1);
        } else {
            return "5 Nivel Maximo";
        }
    }

    public int resarSumatNivelPrevios(int level) {
        int resp = 0;
        String[] niveles = getDBLevels();
        for (int i = 0; i < niveles.length; i++) {
            if (i < level) {
                String[] nivel = niveles[i].split(",");
                int temp = Integer.parseInt(nivel[1]);
                resp += temp;
            }
        }

        return resp;
    }

    public int calcRestantes(int level, int prospectos) {
        int resp = 0;
        String[] nivel = getLevelRow(level);
        resp = (prospectos - resarSumatNivelPrevios(level));
        if (resp < 0) {
            resp = 0;
        }
        System.out.println("resp " + resp);
        return resp;
    }

    //OBTENER PROSPECTOS PARA ORDENARLOS "2/3" --> INCOMPLETO
    public String getRestantProspects(String idClient, int level) {
        int ProspectosCliente = new Prospects().getClientProspects(idClient, level);
        int ProspectosNextLevel = getNextLevelProspects(level);
        String resp = "";
        resp = calcRestantes(level, ProspectosCliente) + "/" + ProspectosNextLevel;
        return resp;
    }

    //OBTENER PROGRESO DE UN CLIENTE --> INCOMPLETO
    public int getClientProgress(String idClient, int level) {
        int resp = new Prospects().getClientProspects(idClient, level);
        return resp;
    }

    public int[] getNewClientPanelDataData() {
        int[] resp = new int[3];
        resp[0] = 1;//NIVEL ACTUAL
        resp[1] = 0;//PROSPECTOS AUTORIZADOS Y TERMINADOS CORRECTAMENTE EN NIVEL
        resp[2] = (new LevelAdmin().getNextLevelProspects(1));//PROSPECTOS MINIMOS PARA SIGUIENTE NIVEL
        return resp;
    }

    public int[] getClientPanelData(String idClient, int nivel) {
        int[] resp = new int[3];
        resp[0] = nivel;//NIVEL ACTUAL
        resp[1] = new LevelAdmin().calcRestantes(nivel, new LevelAdmin().getClientProgress(idClient, nivel));//PROSPECTOS AUTORIZADOS Y TERMINADOS CORRECTAMENTE EN NIVEL
        resp[2] = (new LevelAdmin().getNextLevelProspects(nivel));//PROSPECTOS MINIMOS PARA SIGUIENTE NIVEL
        return resp;
    }

    //OBTENER PROGRESO DE UN CLIENTE PARA COMPLETAR EL NIVEL --> INCOMPLETO
    public int getCompletProspects(String IDClient, int Level) {
        int resp = 0;
        if (Level > 0 && Level <= 5) {

        }
        return resp;
    }

    //OBTENER LOS PROSPECTOS NECESARIOS PARA SUBIR DE NIVEL
    public int getNextLevelProspects(int level) {
        int resp = 0;
        String[] database = getDBLevels();
        for (int i = 0; i < database.length; i++) {
            String[] registro = database[i].split(",");
            if (registro[0].equals((level + 1) + "")) {
                resp = Integer.parseInt(registro[1]);
            }
        }
        return resp;
    }

    public int getLevelProspects(int level) {
        int resp = 0;
        String[] database = getDBLevels();
        for (int i = 0; i < database.length; i++) {
            String[] registro = database[i].split(",");
            if (registro[0].equals((level) + "")) {
                resp = Integer.parseInt(registro[1]);
            }
        }
        return resp;
    }
//    public String getCreditDisp(int level) {
//        String resp = "";
//        String[] database = getDBLevels();
//        for (int i = 0; i < database.length; i++) {
//            String[] registro = database[i].split(",");
//            if (registro[0].equals(level + "")) {
//                resp = registro[2];
//            }
//        }
//        return resp;
//    }   

    //ACTUALIZAR NIVEL EN DB
    public boolean updateLevel(String nuevo_reg) {
        try {
            String[] database = getDBLevels();
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
