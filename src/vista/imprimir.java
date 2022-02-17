
package vista;

import java.awt.*;
import java.awt.print.*;
import javax.swing.JOptionPane;


/**
 *
 * @author josh_
 */
public class imprimir extends javax.swing.JFrame implements Printable {

   
    public imprimir() {
        initComponents();
    }

       

    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTicket = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblticket = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        imprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("TICKET"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTicket.setName("ticket"); // NOI18N
        panelTicket.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel2.setText("AGENCIA BANCARIA MI PISTILLO");
        panelTicket.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 19, -1, 30));

        lblFecha.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        panelTicket.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 51, 110, 14));

        lblticket.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblticket.setText("No. TICKET");
        panelTicket.add(lblticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 83, -1, 31));
        lblticket.getAccessibleContext().setAccessibleName("TICKET");

        lblNumero.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        panelTicket.add(lblNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 120, 95, 44));

        lblTipo.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        panelTicket.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 182, 83, 13));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("DATOS:");
        panelTicket.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 206, 53, -1));

        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        panelTicket.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 225, 102, 13));

        lblApellido.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        panelTicket.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 244, 102, 12));

        getContentPane().add(panelTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 260));

        imprimir.setText("OK");
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });
        getContentPane().add(imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 70, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
         
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
      this.setVisible(false);
    }//GEN-LAST:event_imprimirActionPerformed

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
            java.util.logging.Logger.getLogger(imprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(imprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(imprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(imprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new imprimir().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel lblApellido;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblNombre;
    public static javax.swing.JLabel lblNumero;
    public static javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblticket;
    public static java.awt.Panel panelTicket;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graf, PageFormat pagform, int index) throws PrinterException 
    {
          
    if(index>0){
    return NO_SUCH_PAGE;
    
    }  
    Graphics2D hub= (Graphics2D) graf;
    hub.translate(pagform.getImageableX()+100,pagform.getImageableY()+100);
    hub.scale(1.0,1.0);
    
    panelTicket.printAll(graf);
    return PAGE_EXISTS;

    }
}
