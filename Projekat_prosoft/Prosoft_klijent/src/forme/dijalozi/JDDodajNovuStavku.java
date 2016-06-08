/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.dijalozi;

import domen.DnevnaBerba;
import forme.dijalozi.*;
import domen.StavkaDnevneBerbe;
import forme.paneli.PnlStavke;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.NitKlijent;
import konstante.Konstante;
import transfer.KlijentTransferObjekat;

/**
 *
 * @author Mare
 */
public class JDDodajNovuStavku extends javax.swing.JDialog {

    StavkaDnevneBerbe stavka;
    DnevnaBerba dnevnaBerba;

    /**
     * Creates new form JDIzmeniStavku
     */
    public JDDodajNovuStavku(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public JDDodajNovuStavku(java.awt.Frame parent, boolean modal, DnevnaBerba db){
        this(parent, modal);
        dnevnaBerba = db;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfTrecaKlasa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfDrugaKlasa = new javax.swing.JTextField();
        jtfPrvaKlasa = new javax.swing.JTextField();
        jtfTacne = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtfCenaTacne = new javax.swing.JTextField();
        jtfCenaPrvaKlasa = new javax.swing.JTextField();
        jtfCenaDrugaKlasa = new javax.swing.JTextField();
        jtfCenaTrecaKlasa = new javax.swing.JTextField();
        jbtnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tacne:");

        jLabel2.setText("Prva klasa:");

        jLabel3.setText("Druga klasa:");

        jLabel4.setText("Treca klasa:");

        jLabel5.setText("Cena:");

        jLabel6.setText("Cena:");

        jLabel7.setText("Cena:");

        jLabel8.setText("Cena:");

        jbtnSacuvaj.setText("Sacuvaj");
        jbtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfTrecaKlasa)
                            .addComponent(jtfDrugaKlasa)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(jtfPrvaKlasa))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jtfTacne, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jtfCenaPrvaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jtfCenaDrugaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jtfCenaTrecaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jtfCenaTacne, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jbtnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfTacne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtfCenaTacne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfPrvaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtfCenaPrvaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDrugaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jtfCenaDrugaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfTrecaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jtfCenaTrecaKlasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(47, 47, 47)
                .addComponent(jbtnSacuvaj)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        try {
            popuniPrazne();
            stavka = new StavkaDnevneBerbe();
            stavka.setDnevnaBerba(dnevnaBerba);
            stavka.setDobavljac(dnevnaBerba.getDobavljac());
            stavka.setTacne(Double.parseDouble(jtfTacne.getText()));
            stavka.setCenaTacne(Double.parseDouble(jtfCenaTacne.getText()));
            stavka.setPrvaKlasa(Double.parseDouble(jtfPrvaKlasa.getText()));
            stavka.setCenaPrvaKlasa(Double.parseDouble(jtfCenaPrvaKlasa.getText()));
            stavka.setDrugaKlasa(Double.parseDouble(jtfDrugaKlasa.getText()));
            stavka.setCenaDrugaKlasa(Double.parseDouble(jtfCenaDrugaKlasa.getText()));
            stavka.setTrecaKlasa(Double.parseDouble(jtfTrecaKlasa.getText()));
            stavka.setCenaTrecaKlasa(Double.parseDouble(jtfCenaTrecaKlasa.getText()));
            NitKlijent.getInstance().setDijalog(this);
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_SACUVAJ_STAVKU);
            kto.setParametar(stavka);
            NitKlijent.out.writeObject(kto);
        } catch (IOException ex) {
            Logger.getLogger(JDDodajNovuStavku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Pogresno ste uneli podatke!");
            Logger.getLogger(JDDodajNovuStavku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSacuvajActionPerformed

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
            java.util.logging.Logger.getLogger(JDDodajNovuStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDDodajNovuStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDDodajNovuStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDDodajNovuStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDDodajNovuStavku dialog = new JDDodajNovuStavku(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JTextField jtfCenaDrugaKlasa;
    private javax.swing.JTextField jtfCenaPrvaKlasa;
    private javax.swing.JTextField jtfCenaTacne;
    private javax.swing.JTextField jtfCenaTrecaKlasa;
    private javax.swing.JTextField jtfDrugaKlasa;
    private javax.swing.JTextField jtfPrvaKlasa;
    private javax.swing.JTextField jtfTacne;
    private javax.swing.JTextField jtfTrecaKlasa;
    // End of variables declaration//GEN-END:variables

    private void popuniPrazne() {
        if (jtfTacne.getText().equals("")) {
            jtfTacne.setText(0 + "");
        }
        if (jtfCenaTacne.getText().equals("")) {
            jtfCenaTacne.setText(0 + "");
        }
        if (jtfPrvaKlasa.getText().equals("")) {
            jtfPrvaKlasa.setText(0 + "");
        }
        if (jtfCenaPrvaKlasa.getText().equals("")) {
            jtfCenaPrvaKlasa.setText(0 + "");
        }
        if (jtfDrugaKlasa.getText().equals("")) {
            jtfDrugaKlasa.setText(0 + "");
        }
        if (jtfCenaDrugaKlasa.getText().equals("")) {
            jtfCenaDrugaKlasa.setText(0 + "");
        }
        if (jtfTrecaKlasa.getText().equals("")) {
            jtfTrecaKlasa.setText(0 + "");
        }
        if (jtfCenaTrecaKlasa.getText().equals("")) {
            jtfCenaTrecaKlasa.setText(0 + "");
        }
    }

}