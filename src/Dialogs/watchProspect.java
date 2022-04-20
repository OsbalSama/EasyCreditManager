/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialogs;

import Classes.clientController;
import Classes.PDFDocs;
import Classes.Prospects;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author OSBAL
 */
public class watchProspect extends javax.swing.JDialog {

    /**
     * Creates new form watchProspect
     */
    String IDPros = "";

    public String getIDPros() {
        return IDPros;
    }

    public void setIDPros(String IDPros) {
        this.IDPros = IDPros;
    }

    public watchProspect(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnPrint.requestFocus();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void actualizarResumen(String IDProspect) {
        String[] registro = new Prospects().getProspectByID(IDProspect);
        setIDPros(IDProspect);
        String[] cliente = new clientController().getClientbyID(registro[1]);
        if (cliente != null) {
            btnCreditHistory.setVisible(false);
            btnPrint.setVisible(false);
            btnAutorizar.setVisible(false);
            btnCancel.setVisible(false);
            if (registro[8].equals("SIN AUTORIZAR")) {
                btnAutorizar.setVisible(true);
                btnPrint.setVisible(true);
            } else if (registro[8].equals("AUTORIZADO")) {
                btnPrint.setVisible(true);
                btnCreditHistory.setVisible(true);
                btnCancel.setVisible(true);
            } else if (registro[8].equals("PAGADO")) {
                btnPrint.setVisible(true);
                btnCreditHistory.setVisible(true);
            } else if (registro[8].equals("CANCELADO")) {
                btnPrint.setVisible(true);
            }
            btnPrint.requestFocus();
            txtaResult.setText("---DATOS DE CREDITO---\n"
                    + "Datos de Cliente \n"
                    + "     Numero Cliente: " + cliente[0] + "\n"
                    + "     Nombre Cliente: " + cliente[1] + " " + cliente[2] + "\n"
                    + "     Nivel: " + cliente[11] + "\n"
                    + "Datos de Prospecto\n"
                    + "     ID prospecto: " + registro[0] + "  \n"
                    + "     Tipo de prospecto: " + registro[2] + "  \n"
                    + "     Monto solicitado: " + new DecimalFormat("$#.00").format(Double.parseDouble(registro[3])) + "\n"
                    + "     Intereses ($): " + new DecimalFormat("$#.00").format(Double.parseDouble(registro[4])) + "\n"
                    + "     Total + Interes: " + new DecimalFormat("$#.00").format(calcTotalInteres(Double.parseDouble(registro[3]), Double.parseDouble(registro[4]))) + "\n"
                    + "     Interes Moratorio ($): " + new DecimalFormat("$#.00").format(Double.parseDouble(registro[5])) + "\n"
                    + "     Total + Interes Moratorio: " + new DecimalFormat("$#.00").format(calcTotalInteres(Double.parseDouble(registro[3]), Double.parseDouble(registro[5]))) + "\n"
                    + "Plazos y Fechas\n"
                    + "     Fecha solicitud: " + registro[6] + "\n"
                    + "     Fecha Limite de Pago: " + registro[7] + "\n"
                    + "     Estado: " + registro[8] + "\n"
                    + "     Fecha autorizacion: " + registro[9] + "\n"
                    + "     Fecha registro: " + registro[10]
            );
        } else {
            JOptionPane.showMessageDialog(this, "El Cliente de de este proyecto ya no existe... no se puede acceder al historial crediticio");
            btnCreditHistory.setVisible(false);
            btnPrint.setVisible(false);
            btnAutorizar.setVisible(false);
            btnCancel.setVisible(false);
            if (registro[8].equals("SIN AUTORIZAR")) {
                btnAutorizar.setVisible(true);
                btnPrint.setVisible(true);
            } else if (registro[8].equals("AUTORIZADO")) {
                btnPrint.setVisible(true);
                btnCreditHistory.setVisible(true);
                btnCancel.setVisible(true);
            } else if (registro[8].equals("PAGADO")) {
                btnPrint.setVisible(true);
                btnCreditHistory.setVisible(true);
            } else if (registro[8].equals("CANCELADO")) {
                btnPrint.setVisible(true);
            }
            btnCreditHistory.setVisible(false);
            btnPrint.requestFocus();
            txtaResult.setText("---DATOS DE CREDITO---\n"
                    + "Datos de Cliente \n"
                    + "\n     ---CLIENTE NO ENCONTRADO EN EL SISTEMA---\n\n"
                    + "Datos de Prospecto\n"
                    + "     ID prospecto: " + registro[0] + "  \n"
                    + "     Tipo de prospecto: " + registro[2] + "  \n"
                    + "     Monto solicitado: " + new DecimalFormat("$#.00").format(Double.parseDouble(registro[3])) + "\n"
                    + "     Intereses ($): " + new DecimalFormat("$#.00").format(Double.parseDouble(registro[4])) + "\n"
                    + "     Total + Interes: " + new DecimalFormat("$#.00").format(calcTotalInteres(Double.parseDouble(registro[3]), Double.parseDouble(registro[4]))) + "\n"
                    + "     Interes Moratorio ($): " + new DecimalFormat("$#.00").format(Double.parseDouble(registro[5])) + "\n"
                    + "     Total + Interes Moratorio: " + new DecimalFormat("$#.00").format(calcTotalInteres(Double.parseDouble(registro[3]), Double.parseDouble(registro[5]))) + "\n"
                    + "Plazos y Fechas\n"
                    + "     Fecha solicitud: " + registro[6] + "\n"
                    + "     Fecha Limite de Pago: " + registro[7] + "\n"
                    + "     Estado: " + registro[8] + "\n"
                    + "     Fecha autorizacion: " + registro[9] + "\n"
                    + "     Fecha registro: " + registro[10]
            );
        }

    }

    public double calcTotalInteres(double total, double intereses) {
        return total + intereses;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtaResult = new javax.swing.JTextArea();
        btnCerrar = new javax.swing.JButton();
        btnAutorizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnCreditHistory = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtaResult.setEditable(false);
        txtaResult.setColumns(20);
        txtaResult.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtaResult.setLineWrap(true);
        txtaResult.setRows(5);
        jScrollPane1.setViewportView(txtaResult);

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnAutorizar.setText("AUTORIZAR");
        btnAutorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutorizarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Datos de Credito");

        btnPrint.setText("IMPRIMIR");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnCreditHistory.setText("VER HISTORIAL CREDIT");
        btnCreditHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditHistoryActionPerformed(evt);
            }
        });

        btnCancel.setText("CANCELAR");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCreditHistory)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPrint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAutorizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCerrar)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreditHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnAutorizar)
                    .addComponent(btnPrint)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnAutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutorizarActionPerformed
        autorizar();
    }//GEN-LAST:event_btnAutorizarActionPerformed

    private void btnCreditHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditHistoryActionPerformed
        ShowWCH();
    }//GEN-LAST:event_btnCreditHistoryActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        PrintDoc();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        CancelProspect();
    }//GEN-LAST:event_btnCancelActionPerformed

    public void PrintDoc() {
        try {
//            new PDFDocs().PrintSaleTicket(idv_usr_tot.split(","), c.getImpresora());
        } catch (Exception e) {
        }
        JOptionPane.showMessageDialog(this, "Falta imprimir documento");
        btnCerrar.requestFocus();
    }

    public void ShowWCH() {
        WCHClient wch = new WCHClient(null, true);
        wch.setData(getIDPros());
        wch.show();
        actualizarResumen(getIDPros());
        btnCerrar.requestFocus();
    }

    public void autorizar() {
        Confirm c = new Confirm(null, true);
        c.setVisible(true);
        if (c.getResp() == 1) {
            String faut = new SimpleDateFormat("dd-MM-YYYY").format(new Date()) + " --> " + new SimpleDateFormat("hh:mm").format(new Date());
            if (new Prospects().authorizeProspect(getIDPros(), "AUTORIZADO", faut)) {
                actualizarResumen(getIDPros());
                revalidate();
                repaint();
            }
        } else if (c.getResp() == 0) {
            JOptionPane.showMessageDialog(null, "<html><h1>¡ACCESO DENEGADO!</h1><font SIZE=5><p>Verifique sus credenciales...</font></html>", "¡Atencion!", JOptionPane.WARNING_MESSAGE);
        }
        btnCerrar.requestFocus();
    }

    public void CancelProspect() {
        Confirm c = new Confirm(null, true);
        c.setVisible(true);
        if (c.getResp() == 1) {
            String faut = new SimpleDateFormat("dd-MM-YYYY").format(new Date()) + " --> " + new SimpleDateFormat("hh:mm").format(new Date());
            if (new Prospects().authorizeProspect(getIDPros(), "CANCELADO", faut)) {
                actualizarResumen(getIDPros());
                revalidate();
                repaint();
            }
        } else if (c.getResp() == 0) {
            JOptionPane.showMessageDialog(null, "<html><h1>¡ACCESO DENEGADO!</h1><font SIZE=5><p>Verifique sus credenciales...</font></html>", "¡Atencion!", JOptionPane.WARNING_MESSAGE);
        }
        btnCerrar.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(watchProspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(watchProspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(watchProspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(watchProspect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                watchProspect dialog = new watchProspect(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutorizar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCreditHistory;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtaResult;
    // End of variables declaration//GEN-END:variables
}
