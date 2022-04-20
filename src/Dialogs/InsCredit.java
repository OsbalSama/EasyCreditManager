/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialogs;

import Classes.clientController;
import Classes.DatEmpresa;
import Classes.LevelAdmin;
import Classes.Notifications;
import Classes.Prospects;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author OSBAL
 */
public class InsCredit extends javax.swing.JDialog {

    /**
     * Creates new form InsPrestamo
     */
    public InsCredit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jdateLimit.requestFocus();
    }

    public void asignData() {
        loadClients();
        jdateSolicitud.setDate(new Date());
        setIntereses();
        actualizarResumen();
        lblID.setText(new Prospects().genID());
    }

    public double getMontoSol() {
        double resp = 0;
        try {
            if (!txtMonto.getText().equals("")) {
                resp = Double.parseDouble(txtMonto.getText());
            } else {
                resp = Double.parseDouble("0.0");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
        return resp;
    }

    public double getInteres() {
        double resp = 0;
        try {
            if (!txtInteres.getText().equals("")) {
                resp = Double.parseDouble(txtInteres.getText());
            } else {
                resp = Double.parseDouble("0.0");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
        return resp;
    }

    public double getInteresMorat() {
        double resp = 0;
        try {
            if (!txtIMorat.getText().equals("")) {
                resp = Double.parseDouble(txtIMorat.getText());
            } else {
                resp = Double.parseDouble("0.0");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
        return resp;
    }

    public double calcInteres(double credit, double interes) {
        return (interes / 100) * credit;
    }

    public double calcInteresMorat(double credit, double morat) {
        return (morat / 100) * credit;
    }

    public double calcTotalInteres(double total, double intereses) {
        return total + intereses;
    }

    public double calcTotalInteresMorat(double total, double intereses) {
        return total + intereses;
    }

    public void actualizarResumen() {
        if (cmbClients.getItemCount() > 0 && new clientController().getClientbyID(cmbClients.getSelectedItem().toString()) != null) {
            String[] cliente = new clientController().getClientbyID(cmbClients.getSelectedItem().toString());
            lblCredDisp.setText(new LevelAdmin().getMaxCredit(Integer.parseInt(cliente[11])));
            txtaResult.setText("---DATOS DE NUEVO CREDITO---\n"
                    + "Datos de Cliente \n"
                    + "     Numero Cliente: " + cliente[0] + "\n"
                    + "     Nombre Cliente: " + cliente[1] + " " + cliente[2] + "\n"
                    + "     Nivel: " + cliente[11] + "\n"
                    + "Datos de Credito\n"
                    + "     Credito solicitado: " + new DecimalFormat("$#.00").format(getMontoSol()) + "\n"
                    + "     Intereses (%): " + getInteres() + " % \n"
                    + "     Intereses ($): " + new DecimalFormat("$#.00").format(calcInteres(getMontoSol(), getInteres())) + "\n"
                    + "     Total + Interes: " + new DecimalFormat("$#.00").format(calcTotalInteres(getMontoSol(), calcInteres(getMontoSol(), getInteres()))) + "\n"
                    + "     Interes Moratorio (%): " + getInteresMorat() + " % \n"
                    + "     Interes Moratorio ($): " + new DecimalFormat("$#.00").format(calcInteresMorat(getMontoSol(), getInteresMorat())) + "\n"
                    + "     Total + Interes Moratorio: " + new DecimalFormat("$#.00").format(calcTotalInteresMorat(getMontoSol(), calcInteresMorat(getMontoSol(), getInteresMorat()))) + "\n"
                    + "Plazos y Fechas\n"
                    + "     Fecha solicitud: " + getSolDate() + "\n"
                    + "     Fecha Limite de Pago: " + getLimitDate() + "\n"
                    + "     Estado: ESPERANDO AUTORIZACION"
            );
        }
    }

    public String getSolDate() {
        String resp = "";
        if (!isChooserEmpty(jdateSolicitud)) {
            resp = new SimpleDateFormat("dd-MM-YYYY").format(jdateSolicitud.getDate());
        } else {
            resp = "SIN FECHA0";
        }
        return resp;
    }

    public String getLimitDate() {
        String resp = "";
        if (!isChooserEmpty(jdateLimit)) {
            resp = new SimpleDateFormat("dd-MM-YYYY").format(jdateLimit.getDate());
        } else {
            resp = "SIN FECHA1";
        }
        return resp;
    }

    public boolean isChooserEmpty(JDateChooser comp) {
        boolean resp = false;
        if (comp.getDate() == null) {
            resp = true;
        }
        return resp;
    }

    public void setIntereses() {
        try {
            String[] impuestos = new DatEmpresa().getIntereses();
            txtInteres.setText(new DecimalFormat("#.00").format(Double.parseDouble(impuestos[0])));
            txtIMorat.setText(new DecimalFormat("#.00").format(Double.parseDouble(impuestos[1])));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbClients = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        txtInteres = new javax.swing.JTextField();
        txtIMorat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdateSolicitud = new com.toedter.calendar.JDateChooser();
        jdateLimit = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaResult = new javax.swing.JTextArea();
        lblConfig = new javax.swing.JLabel();
        lblCredDisp = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Solicitar Nuevo Credito");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Basicos"));

        lblID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblID.setText("000000000000");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID CREDITO");

        jLabel4.setText("Cliente");

        cmbClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClientsActionPerformed(evt);
            }
        });

        jLabel3.setText("Monto ($)");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Interes (%)");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Inter. Moratorio (%)");

        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        txtInteres.setEditable(false);

        txtIMorat.setEditable(false);

        jLabel7.setText("Fecha Solicitud");

        jLabel8.setText("Fecha Limite de Pago");

        jdateSolicitud.setDateFormatString("dd/MM/yyyy");
        jdateSolicitud.setDoubleBuffered(false);
        jdateSolicitud.setEnabled(false);
        jdateSolicitud.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdateSolicitudPropertyChange(evt);
            }
        });

        jdateLimit.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdateLimitPropertyChange(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));

        txtaResult.setEditable(false);
        txtaResult.setColumns(20);
        txtaResult.setLineWrap(true);
        txtaResult.setRows(5);
        jScrollPane1.setViewportView(txtaResult);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        lblConfig.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblConfig.setText("Config");
        lblConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConfigMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblConfigMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblConfigMouseExited(evt);
            }
        });

        lblCredDisp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCredDisp.setText("10000");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel10.setText("Cred. disp.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtInteres)
                                        .addComponent(txtIMorat, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel4)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblConfig)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblCredDisp))))
                        .addComponent(cmbClients, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jdateSolicitud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jdateLimit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdateSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdateLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCredDisp)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIMorat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConfig)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnCancel.setText("CANDELAR");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAceptar.setText("GUARDAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmbClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClientsActionPerformed
        if (cmbClients.getItemCount() > 0) {
            cmbActionPreformed(cmbClients.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbClientsActionPerformed

    public void cmbActionPreformed(String Item) {
        if (Item.equals("+ Nuevo Cliente")) {
            insClient();
        }
        actualizarResumen();
    }

    public void insClient() {
        InsClient ic = new InsClient(null, true);
        ic.insertClient();
        ic.show();
        String Client = "";
        if (ic.getSave() == 1) {
            Client = ic.getIdClient();
        }
        loadClients();
        cmbClients.setSelectedItem(Client);
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        saveCredit();
    }//GEN-LAST:event_btnAceptarActionPerformed

    public boolean LimiteCredito() {
        double monto = getMontoSol();
        double limite = Double.parseDouble(lblCredDisp.getText());
        if (monto < limite) {
            txtMonto.setBackground(Color.WHITE);
            return true;
        } else if (monto == limite) {
            txtMonto.setBackground(Color.YELLOW);
            return true;
        } else {
            txtMonto.setBackground(Color.RED);
            return false;
        }
    }

    public void saveCredit() {
        try {
            if (!txtMonto.getText().replace(" ", "").equals("") && !isChooserEmpty(jdateSolicitud) && !isChooserEmpty(jdateLimit) && LimiteCredito() && Double.parseDouble(txtMonto.getText().replace(" ", "")) > 0) {
                String nreg = lblID.getText() + "," + cmbClients.getSelectedItem().toString() + "," + "CREDITO," + txtMonto.getText() + ","
                        + calcInteres(getMontoSol(), getInteres()) + "," + calcInteresMorat(getMontoSol(), getInteresMorat()) + ","
                        + getSolDate() + "," + getLimitDate() + "," + "SIN AUTORIZAR," + "NO DATE," + new SimpleDateFormat("dd-MM-YYYY").format(new Date()) + ",";
                if (btnAceptar.getText().equals("GUARDAR")) {
                    int DB = JOptionPane.showConfirmDialog(null, "<html><h1>AÑADIR NUEVO CREDITO</h1><font SIZE=5><p> Desea crear un nuevo Credito?</font></html>", "NUEVO CREDITO", JOptionPane.YES_NO_OPTION);
                    if (DB == 0) {
                        new Prospects().insProspect(nreg);
                        String notif = new Notifications().genID()+","+"CREDITO "+lblID.getText()+" GUARDADO - REQUIERE AUTORIZACION"+",0,"+new SimpleDateFormat("dd-MM-YYYY").format(new Date())+","+new SimpleDateFormat("HH:MM").format(new Date())+",";
                        new Notifications().insNotif(notif);
                        JOptionPane.showMessageDialog(null, "<html><h1>CREDITO REGISTRADO CORRECTAMENTE</h1><font SIZE=5><p> Credito registrado correctamente,<p>esperando autorizacion...</font></html>", "CREDITO REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                } else {
                    int dialogButton = JOptionPane.showConfirmDialog(this, "DESEA ACTUALIZAR EL CREDITO SELECCIONADO?", "ACTUALIZAR CLIENTE", JOptionPane.YES_NO_OPTION);
                    if (dialogButton == 0) {
                        new Prospects().updateProspect(nreg);
                        JOptionPane.showMessageDialog(this, "CREDITO ACTUALIZADO CORRECTAMENTE", "ACTUALIZAR CREDITO", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
    }

    public void updateIntereses() {
        lblConfig.setForeground(Color.YELLOW);
        ConfigIntereses ci = new ConfigIntereses(null, true);
        ci.asignData();
        ci.show();
        setIntereses();
        lblConfig.setForeground(Color.BLUE);
    }

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtMonto.getText().contains(".")) {
            evt.consume();
        }
        if (txtMonto.getText().equals(".")) {
            txtMonto.setText("0.");
        }
        LimiteCredito();
    }//GEN-LAST:event_txtMontoKeyTyped

    private void lblConfigMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigMouseEntered
        lblConfig.setForeground(Color.BLUE);
    }//GEN-LAST:event_lblConfigMouseEntered

    private void lblConfigMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigMouseExited
        lblConfig.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblConfigMouseExited

    private void lblConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConfigMouseClicked
        updateIntereses();
    }//GEN-LAST:event_lblConfigMouseClicked

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        if (txtMonto.getText().equals(".")) {
            txtMonto.setText("0.");
        }
        LimiteCredito();
        actualizarResumen();
    }//GEN-LAST:event_txtMontoKeyReleased

    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
//        LimiteCredito();
    }//GEN-LAST:event_txtMontoKeyPressed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        saveCredit();
    }//GEN-LAST:event_txtMontoActionPerformed

    private void jdateSolicitudPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdateSolicitudPropertyChange
        actualizarResumen();
    }//GEN-LAST:event_jdateSolicitudPropertyChange

    private void jdateLimitPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdateLimitPropertyChange
        actualizarResumen();
    }//GEN-LAST:event_jdateLimitPropertyChange

    public void printDocument() {
        JOptionPane.showMessageDialog(this, "Falta imprimir registro");
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
            java.util.logging.Logger.getLogger(InsCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsCredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InsCredit dialog = new InsCredit(new javax.swing.JFrame(), true);
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

    public void loadClients() {
        cmbClients.removeAllItems();
        List clientes = new clientController().loadClientes();
        if (clientes.isEmpty()) {
            cmbClients.addItem("SIN CLIENTES");
            cmbClients.addItem("+ Nuevo Cliente");
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                cmbClients.addItem(clientes.get(i).toString());
            }
            cmbClients.addItem("+ Nuevo Cliente");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cmbClients;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdateLimit;
    private com.toedter.calendar.JDateChooser jdateSolicitud;
    private javax.swing.JLabel lblConfig;
    private javax.swing.JLabel lblCredDisp;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField txtIMorat;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextArea txtaResult;
    // End of variables declaration//GEN-END:variables
}
