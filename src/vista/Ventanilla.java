/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author josh_
 */
public class Ventanilla extends javax.swing.JFrame {

    public Ventanilla() {
        initComponents();
        setLocationRelativeTo(null);
        t = new Timer(10, acciones);
    }

    Pantalla frmP;
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    DefaultTableModel md;
    int id;
    private Timer t;
    private int h, m, s, cs;
    private ActionListener acciones = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ++cs;
            if (cs == 100) {
                cs = 0;
                ++s;
            }
            if (s == 60) {
                s = 0;
                ++m;
            }
            if (m == 60) {
                m = 0;
                ++h;
            }
            actualizarLabel();

            String Alerta = "00:02:00:00";
            if (lblTiempo.getText().equals(Alerta)) {
                alerta mostrar = new alerta();
                mostrar.setVisible(true);
                alerta.lblCaja.setText(cmbVentanilla.getSelectedItem().toString());
            }
        }

    };

    private void actualizarLabel() {
        String tiempo = (h <= 9 ? "0" : "") + h + ":" + (m <= 9 ? "0" : "") + m + ":" + (s <= 9 ? "0" : "") + s + ":" + (cs <= 9 ? "0" : "") + cs;
        lblTiempo.setText(tiempo);
    }

    public void cerrarConexion() {
        try {
            cn.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error al cerrar conexion", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, sqle);
        }

    }

    public void cierraConsultas() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error cerrando la conexion!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Recepcion.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    public void limpiarCajas() {
        txtCodigo.setText(null);
        txtTurno.setText(null);
        txtTurno.setText(null);
        txtNumero.setText(null);
        txtTurno.setText(null);
    }

    public void iniciar_pantalla() {
        if (frmP == null) {
            frmP = new Pantalla();
            frmP.setVisible(true);
        }
        Pantalla.txtNumero.setText(txtNumero.getText());
        Pantalla.txtVentanilla.setText(cmbVentanilla.getSelectedItem().toString());
    }

    public void ordinario() {

        try {
            cn = con.getConexion();
            ps = cn.prepareStatement("SELECT MIN(id_ticket) id_ticket,codigo,Nombre,Apellido,tipo_turno FROM tickets WHERE tipo_turno=? ");
            ps.setString(1, "ORDINARIO");
            rs = ps.executeQuery();
            while (rs.next()) {
                txtNumero.setText(rs.getString("id_ticket"));
                txtCodigo.setText(rs.getString("codigo"));
                txtNombre.setText(rs.getString("Nombre"));
                txtApellido.setText(rs.getString("Apellido"));
                txtTurno.setText(rs.getString("tipo_turno"));
            }
            cn.close();

        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    public void prioritario() {

        try {
            cn = con.getConexion();
            ps = cn.prepareStatement("SELECT MIN(id_ticket) id_ticket,codigo,Nombre,Apellido,tipo_turno FROM tickets WHERE tipo_turno=? ");
            ps.setString(1, "PRIORITARIO");
            rs = ps.executeQuery();
            while (rs.next()) {
                txtNumero.setText(rs.getString("id_ticket"));
                txtCodigo.setText(rs.getString("codigo"));
                txtNombre.setText(rs.getString("Nombre"));
                txtApellido.setText(rs.getString("Apellido"));
                txtTurno.setText(rs.getString("tipo_turno"));
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    public void eliminar() {

        try {

            cn = con.getConexion();
            ps = cn.prepareStatement("DELETE FROM tickets WHERE id_ticket=?");
            ps.setInt(1, Integer.parseInt(txtNumero.getText()));

            int res = ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Atencion Finalizada con Exito");
                limpiarCajas();

            } else {
                JOptionPane.showMessageDialog(null, "No se ha finalizado la atencion");
                limpiarCajas();
            }

            cerrarConexion();
            cierraConsultas();

        } catch (HeadlessException | NumberFormatException | SQLException e) {
            System.err.println(e);
        }

        cerrarConexion();
        cierraConsultas();

    }

    public void cliente_atendido() {
        t.stop();
        try {

            cn = con.getConexion();
            ps = cn.prepareStatement("INSERT INTO estado_ticket (codigo, nombre, "
                    + "apellido, tipo_ticket,estado, Ven_atencion,urs_atencion,Tiempo_atencion ) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, txtCodigo.getText());
            ps.setString(2, txtTurno.getText());
            ps.setString(3, txtTurno.getText());
            ps.setString(4, txtTurno.getText());
            ps.setString(5, "ATENDIDO");
            ps.setString(6, cmbVentanilla.getSelectedItem().toString());
            ps.setString(7, txtUsuario.getText());
            ps.setString(8, lblTiempo.getText());
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Ticket Atendido");
            } else {
                JOptionPane.showMessageDialog(null, "Error al asignar");
            }
            cn.close();

        } catch (HeadlessException | SQLException e) {
            System.err.println(e);
        }
        cerrarConexion();
        cierraConsultas();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTurno = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        txtNumero = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        btbSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbVentanilla = new javax.swing.JComboBox<>();
        btnSiguiente = new javax.swing.JButton();
        btnAtendido = new javax.swing.JButton();
        txtUsuario = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTiempo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(848, 557));
        setName("VENTANILLA DE ATENCION"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jButton1.setText("CERRAR SESION");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, -1, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Decker", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLabel3.setText("NOMBRE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLabel4.setText("APELLIDO:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLabel5.setText("TURNO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel6.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLabel6.setText("NUMERO DE TICKET:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLabel7.setText("CODIGO:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 97, -1));

        txtTurno.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        txtTurno.setText("++++");
        jPanel1.add(txtTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 140, 30));

        txtCodigo.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        txtCodigo.setText("++++");
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 140, 30));

        txtApellido.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        txtApellido.setText("++++");
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 140, 30));

        txtNumero.setBackground(new java.awt.Color(255, 255, 255));
        txtNumero.setFont(new java.awt.Font("Decker", 0, 24)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(0, 153, 255));
        txtNumero.setText("-------");
        jPanel1.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 60, 60));

        txtNombre.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        txtNombre.setText("++++");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 140, 30));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 530, 280));

        btbSalir.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btbSalir.setText("SALIR");
        btbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbSalirActionPerformed(evt);
            }
        });
        jPanel3.add(btbSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 420, -1, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BIENVENIDO USUARIO VENTANILLA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Decker", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("NOMBRE DE USUARIO");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel9.setText("No. VENTANILLA");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 107, -1));

        cmbVentanilla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "VENTANILLA 1", "VENTANILLA 2", "VENTANILLA 3", "VENTENILLA 4", "VENTANILLA 5", "VENTANILLA 6", "VENTANILLA 7", "VENTANILLA 8", "VENTANILLA 9", "VENTANILLA 10", " " }));
        jPanel2.add(cmbVentanilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 140, -1));

        btnSiguiente.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        btnSiguiente.setText("CLIENTE SIGUIENTE");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, 40));

        btnAtendido.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        btnAtendido.setText("CLIENTE ATENDIDO");
        btnAtendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtendidoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAtendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, 40));

        txtUsuario.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        txtUsuario.setText("Nombre_cajero");
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 120, 30));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 800, 120));

        jLabel10.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jLabel10.setText("TIEMPO");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

        lblTiempo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTiempo.setText("00:00:00");
        jPanel3.add(lblTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 554, 35));

        jLabel12.setFont(new java.awt.Font("Decker", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 204, 255));
        jLabel12.setText("TICKETS");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 80, -1));

        jLabel14.setFont(new java.awt.Font("Decker", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 204, 255));
        jLabel14.setText("MI PISTILLO");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 220, -1));

        jLabel1.setFont(new java.awt.Font("Decker", 0, 18)); // NOI18N
        jLabel1.setText(" VENTANILLA DE ATENCION");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 25));

        jButton2.setFont(new java.awt.Font("Decker", 0, 13)); // NOI18N
        jButton2.setText("Iniciar pantalla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 370, 130, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        h = 0;
        m = 0;
        s = 0;
        cs = 0;
        actualizarLabel();
        t.start();

        String pr = "PRIORITARIO";
        try {
            cn = con.getConexion();
            ps = cn.prepareStatement("SELECT MIN(id_ticket)id_ticket,codigo,Nombre,Apellido,tipo_turno FROM tickets WHERE tipo_turno=?");
            ps.setString(1, pr);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (pr.equals(rs.getString("tipo_turno"))) {
                    prioritario();
                } else {
                    ordinario();
                }

            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        iniciar_pantalla();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbSalirActionPerformed
        System.exit(WIDTH);
    }//GEN-LAST:event_btbSalirActionPerformed

    private void btnAtendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtendidoActionPerformed
        cliente_atendido();
        eliminar();

    }//GEN-LAST:event_btnAtendidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login mostrar = new login();
        mostrar.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        iniciar_pantalla();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Ventanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventanilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventanilla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbSalir;
    private javax.swing.JButton btnAtendido;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> cmbVentanilla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblTiempo;
    public static javax.swing.JLabel txtApellido;
    public javax.swing.JLabel txtCodigo;
    public static javax.swing.JLabel txtNombre;
    public javax.swing.JLabel txtNumero;
    public javax.swing.JLabel txtTurno;
    public static javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables

}
