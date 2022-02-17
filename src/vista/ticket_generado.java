/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import java.awt.*;
import java.awt.print.*;
import javax.swing.JOptionPane;
/**
 *
 * @author josh_
 */
public class ticket_generado extends javax.swing.JFrame implements Printable{
    Admin frmlAdmin;
  Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    DefaultTableModel md;
    int id;
    public ticket_generado() {
        initComponents();
        listar();
    }

   void listar() {

        String sql = "SELECT * FROM tickets";

        try {
            cn = con.getConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] tickets = new Object[7];
            md = (DefaultTableModel) tblGenerados.getModel();
            while (rs.next()) {
                tickets[0] = rs.getInt("id_ticket");
                tickets[1] = rs.getString("codigo");
                tickets[2] = rs.getString("Nombre");
                tickets[3] = rs.getString("Apellido");
                tickets[4] = rs.getString("tipo_turno");
                tickets[5] = rs.getString("fecha");
                tickets[6] = "EN ESPERA";
                md.addRow(tickets);
            }
            tblGenerados.setModel(md);
        } catch (SQLException e) {
            System.err.println(e);
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlDatos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGenerados = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("TICKETS GENERADOS"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Decker", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 204, 255));
        jLabel1.setText("AGENCIA BANCARIA MI PISTILLO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TICKETS EN ESPERA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Decker", 0, 14), new java.awt.Color(51, 204, 255))); // NOI18N
        pnlDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblGenerados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "NOMBRE", "APELLIDO", "TIPO", "FECHA", "ESTADO"
            }
        ));
        jScrollPane1.setViewportView(tblGenerados);

        pnlDatos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 950, 310));

        jPanel1.add(pnlDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 108, 970, 340));

        btnImprimir.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        btnActualizar.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, -1, -1));

        btnVolver.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 470, -1, -1));

        btnSalir.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 470, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    ticket_generado mostrar=new ticket_generado();
    mostrar.setVisible(true);
    this.setVisible(false);
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
      if(frmlAdmin== null){
    frmlAdmin= new Admin();
    frmlAdmin.setVisible(true);
      }
      this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
System.exit(WIDTH);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
       try{
          PrinterJob imp =PrinterJob.getPrinterJob();
          imp.setPrintable(this);
          boolean tp=imp.printDialog();
          if(tp){
         
              imp.print();
          }
}catch(PrinterException pex){
 JOptionPane.showMessageDialog(null, "ERROR DE PROGRAMA","ERROR\n"+pex,JOptionPane.INFORMATION_MESSAGE);
}
   
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
         if(frmlAdmin== null){
    frmlAdmin= new Admin();
    frmlAdmin.setVisible(true);
      }
      this.dispose();
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(ticket_generado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ticket_generado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ticket_generado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ticket_generado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ticket_generado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JTable tblGenerados;
    // End of variables declaration//GEN-END:variables

      @Override
    public int print(Graphics graf, PageFormat pagform, int index) throws PrinterException {
       
            if(index>0){
    return NO_SUCH_PAGE;
    
    }  
    Graphics2D hub= (Graphics2D) graf;
    hub.translate(pagform.getImageableX()+50,pagform.getImageableY()+50);
    hub.scale(0.5,0.5);
    
    pnlDatos.printAll(graf);
    return PAGE_EXISTS;
        
        
    }
}
