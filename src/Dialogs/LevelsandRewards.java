/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialogs;

import Classes.clientController;
import Classes.ConfigDat;
import Classes.LevelAdmin;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author OSBAL
 */
public class LevelsandRewards extends javax.swing.JDialog {

    /**
     * Creates new form LevelsandRewards
     */
    int longitud = 50;

    public LevelsandRewards(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
    }

    public void LoadClientLevels(String IDClient) {
        String[] db = new LevelAdmin().getLevelRow(new clientController().getClientLevel(IDClient));
        setPicture(IDClient);
        String texto = "---INFO DE CLIENTE,  NIVELES Y RECOMPENZAS---\n";
        texto += new clientController().getLvlClientToString(IDClient) + "\n";
        texto += "Recompensas: Acceso a Credito de hasta  " + new DecimalFormat("$#.00").format(Double.parseDouble(db[2]));
        txtaInfo.setText(texto);
        setIconsLevelUnlocked(new clientController().getClientLevel(IDClient));
    }

    public void setPicture(String IDClient)  {
        try {
            lblLvlIcon.setIcon(new clientController().getClientPicture(IDClient, lblLvlIcon.getWidth(), lblLvlIcon.getHeight()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setIconsLevelUnlocked(int level) {
        lblRecomp0.setIcon(new ConfigDat().getLevelLockedIcon(lblRecomp0.getWidth() - 5, lblRecomp0.getHeight() - 5));
        lblRecomp1.setIcon(new ConfigDat().getLevelLockedIcon(lblRecomp1.getWidth() - 5, lblRecomp1.getHeight() - 5));
        lblRecomp2.setIcon(new ConfigDat().getLevelLockedIcon(lblRecomp2.getWidth() - 5, lblRecomp2.getHeight() - 5));
        lblRecomp3.setIcon(new ConfigDat().getLevelLockedIcon(lblRecomp3.getWidth() - 5, lblRecomp3.getHeight() - 5));
        lblRecomp4.setIcon(new ConfigDat().getLevelLockedIcon(lblRecomp4.getWidth() - 5, lblRecomp4.getHeight() - 5));
        switch (level) {
            case 1:
                lblRecomp0.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp0.getWidth() - 5, lblRecomp0.getHeight() - 5));
                break;
            case 2:
                lblRecomp0.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp0.getWidth() - 5, lblRecomp0.getHeight() - 5));
                lblRecomp1.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp1.getWidth() - 5, lblRecomp1.getHeight() - 5));
                break;
            case 3:
                lblRecomp0.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp0.getWidth() - 5, lblRecomp0.getHeight() - 5));
                lblRecomp1.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp1.getWidth() - 5, lblRecomp1.getHeight() - 5));
                lblRecomp2.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp2.getWidth() - 5, lblRecomp2.getHeight() - 5));
                break;
            case 4:
                lblRecomp0.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp0.getWidth() - 5, lblRecomp0.getHeight() - 5));
                lblRecomp1.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp1.getWidth() - 5, lblRecomp1.getHeight() - 5));
                lblRecomp2.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp2.getWidth() - 5, lblRecomp2.getHeight() - 5));
                lblRecomp3.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp3.getWidth() - 5, lblRecomp3.getHeight() - 5));
                break;
            case 5:
                lblRecomp0.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp0.getWidth() - 5, lblRecomp0.getHeight() - 5));
                lblRecomp1.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp1.getWidth() - 5, lblRecomp1.getHeight() - 5));
                lblRecomp2.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp2.getWidth() - 5, lblRecomp2.getHeight() - 5));
                lblRecomp3.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp3.getWidth() - 5, lblRecomp3.getHeight() - 5));
                lblRecomp4.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp4.getWidth() - 5, lblRecomp4.getHeight() - 5));
                break;
        }
    }

    public void updateData() {
        String[] Levels = new LevelAdmin().getDBLevels();
        String texto = "---INFORMACION SOBRE  NIVELES Y RECOMPENZAS---";
        for (int i = 0; i < Levels.length; i++) {
            String[] nivel = Levels[i].split(",");
            texto += "\nNIVEL " + nivel[0] + ": " + " Req. minimos:" + nivel[1] + ", Credito Disponible: " + nivel[2] + "";
        }
        txtaInfo.setText(texto);
        txtaInfo.setCaretPosition(0);
    }

    public void loadAllRegrants() {
        lblLvlIcon.setIcon(new ConfigDat().getLevelAdminIcon(lblLvlIcon.getWidth(), lblLvlIcon.getHeight()));
        updateData();
        lblRecomp0.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp0.getWidth() - 5, lblRecomp0.getHeight() - 5));
        lblRecomp1.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp1.getWidth() - 5, lblRecomp1.getHeight() - 5));
        lblRecomp2.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp2.getWidth() - 5, lblRecomp2.getHeight() - 5));
        lblRecomp3.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp3.getWidth() - 5, lblRecomp3.getHeight() - 5));
        lblRecomp4.setIcon(new ConfigDat().getLevelGrantedIcon(lblRecomp4.getWidth() - 5, lblRecomp4.getHeight() - 5));
        setNewActionPredormeds();
    }

    public void setNewActionPredormeds() {
        pnlrecomp0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                newActionPreformed(pnlrecomp0, lblNivel0);
            }
        });
        pnlrecomp1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                newActionPreformed(pnlrecomp1, lblNivel1);
            }
        });
        pnlrecomp2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                newActionPreformed(pnlrecomp2, lblNivel2);
            }
        });
        pnlrecomp3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                newActionPreformed(pnlrecomp3, lblNivel3);
            }
        });
        pnlrecomp4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                newActionPreformed(pnlrecomp4, lblNivel4);
            }
        });
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
        jPanel16 = new javax.swing.JPanel();
        pnlrecomp0 = new javax.swing.JPanel();
        lblRecomp0 = new javax.swing.JLabel();
        pnlrecomp1 = new javax.swing.JPanel();
        lblRecomp1 = new javax.swing.JLabel();
        pnlrecomp2 = new javax.swing.JPanel();
        lblRecomp2 = new javax.swing.JLabel();
        pnlrecomp3 = new javax.swing.JPanel();
        lblRecomp3 = new javax.swing.JLabel();
        pnlrecomp4 = new javax.swing.JPanel();
        lblRecomp4 = new javax.swing.JLabel();
        lblNivel0 = new javax.swing.JLabel();
        lblNivel1 = new javax.swing.JLabel();
        lblNivel2 = new javax.swing.JLabel();
        lblNivel3 = new javax.swing.JLabel();
        lblNivel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblLvlIcon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaInfo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NIVELES Y RECOMPENSAS");

        pnlrecomp0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRecomp0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlrecomp0Layout = new javax.swing.GroupLayout(pnlrecomp0);
        pnlrecomp0.setLayout(pnlrecomp0Layout);
        pnlrecomp0Layout.setHorizontalGroup(
            pnlrecomp0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlrecomp0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp0, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlrecomp0Layout.setVerticalGroup(
            pnlrecomp0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrecomp0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp0, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlrecomp1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRecomp1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlrecomp1Layout = new javax.swing.GroupLayout(pnlrecomp1);
        pnlrecomp1.setLayout(pnlrecomp1Layout);
        pnlrecomp1Layout.setHorizontalGroup(
            pnlrecomp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlrecomp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlrecomp1Layout.setVerticalGroup(
            pnlrecomp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrecomp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlrecomp2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRecomp2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlrecomp2Layout = new javax.swing.GroupLayout(pnlrecomp2);
        pnlrecomp2.setLayout(pnlrecomp2Layout);
        pnlrecomp2Layout.setHorizontalGroup(
            pnlrecomp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlrecomp2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlrecomp2Layout.setVerticalGroup(
            pnlrecomp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrecomp2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlrecomp3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRecomp3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlrecomp3Layout = new javax.swing.GroupLayout(pnlrecomp3);
        pnlrecomp3.setLayout(pnlrecomp3Layout);
        pnlrecomp3Layout.setHorizontalGroup(
            pnlrecomp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrecomp3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp3, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlrecomp3Layout.setVerticalGroup(
            pnlrecomp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrecomp3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp3, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlrecomp4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRecomp4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlrecomp4Layout = new javax.swing.GroupLayout(pnlrecomp4);
        pnlrecomp4.setLayout(pnlrecomp4Layout);
        pnlrecomp4Layout.setHorizontalGroup(
            pnlrecomp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrecomp4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp4, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlrecomp4Layout.setVerticalGroup(
            pnlrecomp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrecomp4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRecomp4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblNivel0.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivel0.setText("1");

        lblNivel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivel1.setText("2");

        lblNivel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivel2.setText("3");

        lblNivel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivel3.setText("4");

        lblNivel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivel4.setText("5");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("NIVEL");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("NIVEL");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("NIVEL");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("NIVEL");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("NIVEL");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNivel0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlrecomp0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNivel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlrecomp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNivel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlrecomp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNivel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlrecomp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlrecomp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNivel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblNivel2)
                        .addComponent(lblNivel3)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(lblNivel4)
                        .addComponent(lblNivel1))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblNivel0)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlrecomp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlrecomp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlrecomp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlrecomp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlrecomp0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblLvlIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtaInfo.setEditable(false);
        txtaInfo.setColumns(20);
        txtaInfo.setLineWrap(true);
        txtaInfo.setRows(5);
        jScrollPane2.setViewportView(txtaInfo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLvlIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(lblLvlIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
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

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    public void newActionPreformed(JPanel pnl, JLabel lbl) {
        try {
            LvlSettings ls = new LvlSettings(null, true);
            ls.setData(Integer.parseInt(lbl.getText()));
            ls.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error: " + e);
        }
        updateData();
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
            java.util.logging.Logger.getLogger(LevelsandRewards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LevelsandRewards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LevelsandRewards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LevelsandRewards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LevelsandRewards dialog = new LevelsandRewards(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLvlIcon;
    private javax.swing.JLabel lblNivel0;
    private javax.swing.JLabel lblNivel1;
    private javax.swing.JLabel lblNivel2;
    private javax.swing.JLabel lblNivel3;
    private javax.swing.JLabel lblNivel4;
    private javax.swing.JLabel lblRecomp0;
    private javax.swing.JLabel lblRecomp1;
    private javax.swing.JLabel lblRecomp2;
    private javax.swing.JLabel lblRecomp3;
    private javax.swing.JLabel lblRecomp4;
    private javax.swing.JPanel pnlrecomp0;
    private javax.swing.JPanel pnlrecomp1;
    private javax.swing.JPanel pnlrecomp2;
    private javax.swing.JPanel pnlrecomp3;
    private javax.swing.JPanel pnlrecomp4;
    private javax.swing.JTextArea txtaInfo;
    // End of variables declaration//GEN-END:variables
}
