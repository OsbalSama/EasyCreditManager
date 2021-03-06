/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.adminControls;

import Frames.Login;
import Classes.*;
import Dialogs.*;
import Models.User;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author OSBAL
 */
public final class adminHome extends javax.swing.JFrame {

    /**
     * Creates new form adminHome
     */
    int cont_mes = 0; //0 = hoy
    User loged;

    public adminHome() {
        initComponents();
        loadDefaultConfigs();
    }

    public void loadDefaultConfigs(){
        this.setExtendedState(adminHome.MAXIMIZED_BOTH);
        if (!new ConfigDat().isActivated()) {
            pnlBase.setBackground(Color.WHITE);
            pnlBase2.setBackground(Color.WHITE);
            pnlBase3.setBackground(Color.WHITE);
            pnlBase4.setBackground(Color.WHITE);
        }
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Salir();
            }
        });
        setIconImage(new ConfigDat().getImageIcon().getImage());
        this.setTitle("");
        this.setLocationRelativeTo(null);
        this.setTitle("BIENVENIDO A EASY CREDIT MANAGER");
    }
    
    public void Inicialize(User User) {
        try {
            loged = User;
            lblUser.setText(loged.getRole());
            cargar_logo();
            setPermisos();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void setPermisos() {
        if (!loged.getUsername().equals("root")) {
            menuRootTools.setVisible(false);
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

        pnlBase = new javax.swing.JPanel();
        pnlBase2 = new javax.swing.JPanel();
        pnlBase3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        pnlBase4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuCerrarSesion = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();
        menuCuentas = new javax.swing.JMenu();
        menuAllAccounts = new javax.swing.JMenuItem();
        menuSessions = new javax.swing.JMenuItem();
        menuClients = new javax.swing.JMenu();
        menuNuevoClient = new javax.swing.JMenuItem();
        menuRegClientes = new javax.swing.JMenuItem();
        menuCollectors = new javax.swing.JMenu();
        menuNuevoCollector = new javax.swing.JMenuItem();
        menuRegCollentors = new javax.swing.JMenuItem();
        menuRoutes = new javax.swing.JMenu();
        menuNuevaRoute = new javax.swing.JMenuItem();
        menuRegRoutes = new javax.swing.JMenuItem();
        menuRouteMapping = new javax.swing.JMenu();
        newRouteMapping = new javax.swing.JMenuItem();
        menuRegMapping = new javax.swing.JMenuItem();
        menuProspects = new javax.swing.JMenu();
        menuCredits = new javax.swing.JMenu();
        menuNewCredit = new javax.swing.JMenuItem();
        menuRegCredit = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuDatEmpresa = new javax.swing.JMenuItem();
        menuLevelAdmin = new javax.swing.JMenuItem();
        menuTools = new javax.swing.JMenu();
        menuRestoreSystem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuRootTools = new javax.swing.JMenu();
        menuCypher = new javax.swing.JMenuItem();
        menuAbout = new javax.swing.JMenu();
        menuInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SESION INICIADA");

        lblUser.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("USER");

        javax.swing.GroupLayout pnlBase3Layout = new javax.swing.GroupLayout(pnlBase3);
        pnlBase3.setLayout(pnlBase3Layout);
        pnlBase3Layout.setHorizontalGroup(
            pnlBase3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBase3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBase3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlBase3Layout.setVerticalGroup(
            pnlBase3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBase3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblLogo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlBase2Layout = new javax.swing.GroupLayout(pnlBase2);
        pnlBase2.setLayout(pnlBase2Layout);
        pnlBase2Layout.setHorizontalGroup(
            pnlBase2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBase2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBase3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBase2Layout.setVerticalGroup(
            pnlBase2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBase2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBase2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBase3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlBase4.setBackground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout pnlBase4Layout = new javax.swing.GroupLayout(pnlBase4);
        pnlBase4.setLayout(pnlBase4Layout);
        pnlBase4Layout.setHorizontalGroup(
            pnlBase4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBase4Layout.setVerticalGroup(
            pnlBase4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlBaseLayout = new javax.swing.GroupLayout(pnlBase);
        pnlBase.setLayout(pnlBaseLayout);
        pnlBaseLayout.setHorizontalGroup(
            pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBase4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBase2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBaseLayout.setVerticalGroup(
            pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBaseLayout.createSequentialGroup()
                        .addComponent(pnlBase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBase4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBaseLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        menuArchivo.setText("Archivo");

        menuCerrarSesion.setText("Cerrar Sesion");
        menuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCerrarSesionActionPerformed(evt);
            }
        });
        menuArchivo.add(menuCerrarSesion);

        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuSalir);

        jMenuBar1.add(menuArchivo);

        menuCuentas.setText("Cuentas");

        menuAllAccounts.setText("Todas las Cuentas");
        menuAllAccounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAllAccountsActionPerformed(evt);
            }
        });
        menuCuentas.add(menuAllAccounts);

        menuSessions.setText("Registro Sesiones");
        menuSessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSessionsActionPerformed(evt);
            }
        });
        menuCuentas.add(menuSessions);

        jMenuBar1.add(menuCuentas);

        menuClients.setText("Clientes");

        menuNuevoClient.setText("Nuevo Cliente");
        menuNuevoClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoClientActionPerformed(evt);
            }
        });
        menuClients.add(menuNuevoClient);

        menuRegClientes.setText("Registro de Clientes");
        menuRegClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegClientesActionPerformed(evt);
            }
        });
        menuClients.add(menuRegClientes);

        jMenuBar1.add(menuClients);

        menuCollectors.setText("Cobradores");

        menuNuevoCollector.setText("Nuevo cobrador");
        menuNuevoCollector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoCollectorActionPerformed(evt);
            }
        });
        menuCollectors.add(menuNuevoCollector);

        menuRegCollentors.setText("Registro de Cobradores");
        menuRegCollentors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegCollentorsActionPerformed(evt);
            }
        });
        menuCollectors.add(menuRegCollentors);

        jMenuBar1.add(menuCollectors);

        menuRoutes.setText("Rutas");

        menuNuevaRoute.setText("Nueva Ruta");
        menuNuevaRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevaRouteActionPerformed(evt);
            }
        });
        menuRoutes.add(menuNuevaRoute);

        menuRegRoutes.setText("Mostrar Rutas");
        menuRegRoutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegRoutesActionPerformed(evt);
            }
        });
        menuRoutes.add(menuRegRoutes);

        menuRouteMapping.setText("Mapeo de Rutas");

        newRouteMapping.setText("Nuevo Mapeo de Rutas");
        newRouteMapping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRouteMappingActionPerformed(evt);
            }
        });
        menuRouteMapping.add(newRouteMapping);

        menuRegMapping.setText("Ver Mapeos de Rutas");
        menuRegMapping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegMappingActionPerformed(evt);
            }
        });
        menuRouteMapping.add(menuRegMapping);

        menuRoutes.add(menuRouteMapping);

        jMenuBar1.add(menuRoutes);

        menuProspects.setText("Prospectos");

        menuCredits.setText("Creditos");

        menuNewCredit.setText("Nuevo Credito");
        menuNewCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewCreditActionPerformed(evt);
            }
        });
        menuCredits.add(menuNewCredit);

        menuRegCredit.setText("Mostrar Creditos");
        menuRegCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegCreditActionPerformed(evt);
            }
        });
        menuCredits.add(menuRegCredit);

        menuProspects.add(menuCredits);

        jMenuBar1.add(menuProspects);

        jMenu1.setText("Configuracion");

        menuDatEmpresa.setText("Datos de la Empresa");
        menuDatEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDatEmpresaActionPerformed(evt);
            }
        });
        jMenu1.add(menuDatEmpresa);

        menuLevelAdmin.setText("Niveles y recompenzas");
        menuLevelAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLevelAdminActionPerformed(evt);
            }
        });
        jMenu1.add(menuLevelAdmin);

        jMenuBar1.add(jMenu1);

        menuTools.setText("Herramientas");

        menuRestoreSystem.setText("Respaldo del sistema");
        menuRestoreSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRestoreSystemActionPerformed(evt);
            }
        });
        menuTools.add(menuRestoreSystem);

        jMenuItem1.setText("Modo de fabrica");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuTools.add(jMenuItem1);

        menuRootTools.setText("==ROOT TOOLS==");

        menuCypher.setText("EncryptDecrypt");
        menuCypher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCypherActionPerformed(evt);
            }
        });
        menuRootTools.add(menuCypher);

        menuTools.add(menuRootTools);

        jMenuBar1.add(menuTools);

        menuAbout.setText("Ayuda");

        menuInfo.setText("Info. de producto");
        menuInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInfoActionPerformed(evt);
            }
        });
        menuAbout.add(menuInfo);

        jMenuBar1.add(menuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        Salir();
    }//GEN-LAST:event_menuSalirActionPerformed

    private void menuInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInfoActionPerformed
        infoProd();
    }//GEN-LAST:event_menuInfoActionPerformed

    private void menuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCerrarSesionActionPerformed
        LogOut();
    }//GEN-LAST:event_menuCerrarSesionActionPerformed

    private void menuNuevoClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoClientActionPerformed
        InsClient ic = new InsClient(this, true);
        ic.insertClient();
        ic.show();
    }//GEN-LAST:event_menuNuevoClientActionPerformed

    private void menuNuevoCollectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoCollectorActionPerformed
        InsCobr ic = new InsCobr(this, true);
        ic.asingData();
        ic.show();
    }//GEN-LAST:event_menuNuevoCollectorActionPerformed

    private void menuSessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSessionsActionPerformed
        new AllSessions().show();
    }//GEN-LAST:event_menuSessionsActionPerformed

    private void menuAllAccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAllAccountsActionPerformed
        AllUsers au = new AllUsers();
        au.setContent(loged);
        au.showAccounts();
        au.show();
    }//GEN-LAST:event_menuAllAccountsActionPerformed

    private void menuRegClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegClientesActionPerformed
        AllClients ac = new AllClients();
        ac.showClients();
        ac.show();
    }//GEN-LAST:event_menuRegClientesActionPerformed

    private void menuNewCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewCreditActionPerformed
        InsCredit ic = new InsCredit(this, true);
        ic.asignData();
        ic.show();
    }//GEN-LAST:event_menuNewCreditActionPerformed

    private void menuRegCollentorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegCollentorsActionPerformed
        AllCollectors ac = new AllCollectors();
        ac.showCollectors();
        ac.show();
    }//GEN-LAST:event_menuRegCollentorsActionPerformed

    private void menuNuevaRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevaRouteActionPerformed
        InsRuta ir = new InsRuta(this, true);
        ir.insertRoute();
        ir.show();
    }//GEN-LAST:event_menuNuevaRouteActionPerformed

    private void menuRegRoutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegRoutesActionPerformed
        AllRoutes ac = new AllRoutes();
        ac.showRoutes();
        ac.show();
    }//GEN-LAST:event_menuRegRoutesActionPerformed

    private void menuLevelAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLevelAdminActionPerformed
        LevelsandRewards lrw = new LevelsandRewards(this, true);
        lrw.loadAllRegrants();
        lrw.show();
    }//GEN-LAST:event_menuLevelAdminActionPerformed

    private void menuRestoreSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRestoreSystemActionPerformed
        Confirm c = new Confirm(this, true);
        c.setVisible(true);
        if (c.getResp() == 1) {
            int resp = JOptionPane.showConfirmDialog(null, "<html><h1>??RESPALDO DE SISTEMA!</h1><font SIZE=5><p>??Seguro desea respaldar su base de datos?<p></font></html>", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                new SystemRecovery().MakeFileRestore();
            }
        } else if (c.getResp() == 0) {
            JOptionPane.showMessageDialog(null, "<html><h1>??ACCESO DENEGADO!</h1><font SIZE=5><p>Verifique sus credenciales...</font></html>", "??Atencion!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_menuRestoreSystemActionPerformed

    private void menuCypherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCypherActionPerformed
        new EncryptDecrypt(this, true).show();
    }//GEN-LAST:event_menuCypherActionPerformed

    private void menuRegCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegCreditActionPerformed
        AllProspects ap = new AllProspects();
        ap.AplicarFiltros();
        ap.show();
    }//GEN-LAST:event_menuRegCreditActionPerformed

    private void newRouteMappingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRouteMappingActionPerformed
        RouteMappingD mr = new RouteMappingD(this, true);
        mr.setNewRegData();
        mr.show();
    }//GEN-LAST:event_newRouteMappingActionPerformed

    private void menuRegMappingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegMappingActionPerformed
        AllRouteMappings arm = new AllRouteMappings();
        arm.showClients();
        arm.show();
    }//GEN-LAST:event_menuRegMappingActionPerformed

    private void menuDatEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDatEmpresaActionPerformed
        DatEmpresa2 mdt = new DatEmpresa2(this, true);
        mdt.asignar_datos();
        mdt.show();
        if (mdt.isOk()) {
            cargar_logo();
        }
    }//GEN-LAST:event_menuDatEmpresaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Confirm c = new Confirm(this, true);
        c.setVisible(true);
        if (c.getResp() == 1) {
            int resp = JOptionPane.showConfirmDialog(null, "<html><h1>??RESETEAR A MODO FABRICA!</h1><font SIZE=5><p>??Seguro desea poner en modo de fabrica la aplicaci??n?<p><p>Se perder??n todos los datos y archivos que se hayan generado<p>mientras se uso el sistema</font></html>", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                new Sesiones().ClearDB();
                JOptionPane.showMessageDialog(null, "<html><h1>??RESTAURAR A MODO DE FABRICA!</h1><font SIZE=5><p>Se requiere iniciar manualmente la aplicaci??n para restablecer el sistema...</font></html>", "", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        } else if (c.getResp() == 0) {
            JOptionPane.showMessageDialog(null, "<html><h1>??ACCESO DENEGADO!</h1><font SIZE=5><p>Verifique sus credenciales...</font></html>", "??Atencion!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    public void LogOut() {
        int resp = JOptionPane.showConfirmDialog(null, "<html><h1>CERRAR SESION</h1><font SIZE=5><p>Se perder??n todos los datos que no se hayan guardado??? ??desea continuar? </font></html>", "Cerrar Sesion!", JOptionPane.YES_NO_OPTION);
        if (resp == 0) {
            dispose();
            Login cs = new Login();
            cs.show();
        }
    }

    public void infoProd() {
        try {
            String[] dat = new Configurations().getAccountData();
            String proyectname = "Easy Credit Manager";
            String version = "1.0";
            JOptionPane.showMessageDialog(null, "<html><h1>Informaci??n del producto</h1><font SIZE=5><center><p>***<b>"+proyectname+" v"+version+"</center></b> ***<p>Titular de la cuenta: <p><b><center>" + dat[1] + "</b></center> <p>E-mail: <p><b><center>" + dat[2] + "</b></center><p>Clave de producto: <p><b><center>" + dat[3] + "</b><p></center><p><b><center>Desarrollado por</b></center><p>Osbaldo Toledo Ramos - PTB en Inform??tica<p>E-Mail: <b>osbaldo.toledoramos@hotmail.com</b><p><center><i>Prepare for Unforeseen Consequences<p></font></html>", "??Info. de producto!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "??ERROR!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

//    public void infoProd() {
//        try {
//            String[] dat = new ConfigDat().getDatLic();
//            JOptionPane.showMessageDialog(null, "<html><h1>Informaci??n del producto:</h1><font SIZE=5><p>***<b>Easy Credit Management PRO v1.5.0</b> ***<p>Titular de la cuenta: <b>" + dat[1] + "</b> <p>E-mail: <b>" + dat[2] + "</b><p>Clave de producto: <b>" + dat[3] + "</b><p><p><b><center>Desarrollado por</b></center><p>Osbaldo Toledo Ramos - PTB en Inform??tica<p>E-Mail: <b>osbaldo.toledoramos@hotmail.com</b><p><center><i>Prepare for Unforeseen Consequences<p></font></html>", "??Info. de producto!", JOptionPane.INFORMATION_MESSAGE);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e, "??ERROR!", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
    public void cargar_logo() {
        try {
            lblLogo.setIcon(new ConfigDat().getLogo(lblLogo.getWidth(), lblLogo.getHeight()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "??ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Salir() {
        int resp = JOptionPane.showConfirmDialog(null, "<html><h1>SALIR DE LA APLICACI??N</h1><font SIZE=5><p>Se perder??n todos los datos que no se hayan guardado??? ??desea continuar? </font></html>", "Salir", JOptionPane.YES_NO_OPTION);
        if (resp == 0) {
            System.exit(resp);
        }
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
            java.util.logging.Logger.getLogger(adminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUser;
    private javax.swing.JMenu menuAbout;
    private javax.swing.JMenuItem menuAllAccounts;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuCerrarSesion;
    private javax.swing.JMenu menuClients;
    private javax.swing.JMenu menuCollectors;
    private javax.swing.JMenu menuCredits;
    private javax.swing.JMenu menuCuentas;
    private javax.swing.JMenuItem menuCypher;
    private javax.swing.JMenuItem menuDatEmpresa;
    private javax.swing.JMenuItem menuInfo;
    private javax.swing.JMenuItem menuLevelAdmin;
    private javax.swing.JMenuItem menuNewCredit;
    private javax.swing.JMenuItem menuNuevaRoute;
    private javax.swing.JMenuItem menuNuevoClient;
    private javax.swing.JMenuItem menuNuevoCollector;
    private javax.swing.JMenu menuProspects;
    private javax.swing.JMenuItem menuRegClientes;
    private javax.swing.JMenuItem menuRegCollentors;
    private javax.swing.JMenuItem menuRegCredit;
    private javax.swing.JMenuItem menuRegMapping;
    private javax.swing.JMenuItem menuRegRoutes;
    private javax.swing.JMenuItem menuRestoreSystem;
    private javax.swing.JMenu menuRootTools;
    private javax.swing.JMenu menuRouteMapping;
    private javax.swing.JMenu menuRoutes;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menuSessions;
    private javax.swing.JMenu menuTools;
    private javax.swing.JMenuItem newRouteMapping;
    private javax.swing.JPanel pnlBase;
    private javax.swing.JPanel pnlBase2;
    private javax.swing.JPanel pnlBase3;
    private javax.swing.JPanel pnlBase4;
    // End of variables declaration//GEN-END:variables
}
