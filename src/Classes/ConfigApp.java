/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Controllers.userController;
import Dialogs.DatEmpresa1;
import Dialogs.DatEmpresa2;
import Dialogs.newUser;
import Dialogs.Serial;
import Frames.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author OSBAL
 */
public class ConfigApp {

    boolean terminado = false;
    String nombreApp = "Easy Credit Management PRO v1.5.0";
    userController allUsers = userController.getInstance();

    public String getNombreApp() {
        return nombreApp;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public void activarApp() {
        boolean continuar = false;
        while (continuar == false) {
            Serial vs = new Serial(null, true);
            while (vs.getResp() != 1) {
                vs = new Serial(null, true);
                vs.show();
            }
            continuar = true;
        }
        JOptionPane.showMessageDialog(null, "<html><h1>PRODUCTO ACTIVADO...<p>¡Bienvenido a " + getNombreApp() + "!</h1><p>Clic para continuar...</html>", "¡Bienvenido!", JOptionPane.WARNING_MESSAGE);
//        Login cs = new Login();
//        cs.show();
    }

    public void InicioSistema() {
        boolean continuar = false;
        while (continuar == false) {
            int resp_0 = JOptionPane.showConfirmDialog(null, "<html><h1>Bienvenido a " + getNombreApp() + "!</h1><br>"
                    + "<center>Para comenzar a trabajar en este Software, es necesario establecer algunas <p>configuraciones importantes…</center></html>", "", JOptionPane.OK_CANCEL_OPTION);
            if (resp_0 == JOptionPane.CANCEL_OPTION || resp_0 == JOptionPane.CLOSED_OPTION) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "¡Atencion!", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    System.exit(resp);
                }
            } else {
                continuar = true;
            }
        }        
        allUsers.resetDataBase();
        continuar = false;
        while (continuar == false) {
            newUser iu = new newUser(null, true);
            iu.setDatFirstUse();
            iu.show();
            if (iu.isOk() == true) {
                continuar = true;
            } else if (iu.getContinuar() == 2) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "¡Atencion!", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    System.exit(resp);
                }
            }
        }
        continuar = false;
        while (continuar == false) {
            DatEmpresa1 dt = new DatEmpresa1(null, true);
            dt.setDatFirstUse();
            dt.show();
            if (dt.getContinuar() == 1) {
                continuar = true;
            } else if (dt.getContinuar() == 2) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "¡Atencion!", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    System.exit(resp);
                }
            }
        }
        DatEmpresa2 mdt = new DatEmpresa2(null, true);
        mdt.setDatFirstUse();
        mdt.asignar_datos();
        mdt.show();
//  AQUI HAY QUE METER OTRO PROCESO SI HACE FALTA
//        continuar = false;
//        while (continuar == false) {
//            
//        }
        new Classes.clientController().ClearDB();
        new Cobradores().ClearDB();
        new CreditHistory().ClearDB();
        new NoteAdmin().ClearDB();
        new Prospects().ClearDB();
        new RouteMapping().ClearDB1();
        new RouteMapping().ClearDB2();
        new Rutas().ClearDB();
        new LevelAdmin().ClearDB();
        new Sesiones().ClearDB();
        JOptionPane.showMessageDialog(null, "<html><h1>¡Bienvenido a " + getNombreApp() + "!</h1><font SIZE=5>"
                + "<p>Todo listo... ahora si, COMENCEMOS!</font></html>", "¡Bienvenido!", JOptionPane.WARNING_MESSAGE);
        Login cs = new Login();
        cs.show();
    }

}
