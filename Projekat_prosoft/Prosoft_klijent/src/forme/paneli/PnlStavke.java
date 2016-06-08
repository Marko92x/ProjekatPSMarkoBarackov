/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.paneli;

import domen.DnevnaBerba;
import domen.Dobavljac;
import domen.StavkaDnevneBerbe;
import forme.dijalozi.JDDodajNovuStavku;
import forme.dijalozi.JDIzmeniStavku;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import komunikacija.NitKlijent;
import konstante.Konstante;
import transfer.KlijentTransferObjekat;

/**
 *
 * @author Mare
 */
public class PnlStavke extends javax.swing.JPanel {

    JScrollPane glavna;
    Dobavljac dobavljac;
    DnevnaBerba dnevnaBerba;

    /**
     * Creates new form PnlStavke
     */
    public PnlStavke() {
        initComponents();
    }

    public PnlStavke(Dobavljac d, JScrollPane g, DnevnaBerba db) {
        this();
        glavna = g;
        dobavljac = d;
        dnevnaBerba = db;
        popuniTabelu();
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
        jtblStavke = new javax.swing.JTable();
        jbtnIzmeniStavkku = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(834, 434));

        jtblStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblStavke);

        jbtnIzmeniStavkku.setText("Izmeni");
        jbtnIzmeniStavkku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIzmeniStavkkuActionPerformed(evt);
            }
        });

        jButton1.setText("Dodaj Novu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Vrati se nazad");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Obriši");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnIzmeniStavkku, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jbtnIzmeniStavkku)
                    .addComponent(jButton3))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        glavna.setViewportView(new PnlDnevnaBerba(dobavljac, glavna));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbtnIzmeniStavkkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIzmeniStavkkuActionPerformed
        if (jtblStavke.getSelectedRowCount() == 1) {
            StavkaDnevneBerbe s = NitKlijent.getInstance().getStavke().get(jtblStavke.getSelectedRow());
            JDIzmeniStavku jd = new JDIzmeniStavku(null, true, s);
            jd.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Izabrali ste vise ili niste izabrali ni jednu stavku!");
        }
    }//GEN-LAST:event_jbtnIzmeniStavkkuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JDDodajNovuStavku jd = new JDDodajNovuStavku(null, true, dnevnaBerba);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jtblStavke.getSelectedRowCount() == 1) {
            try {
                KlijentTransferObjekat kto = new KlijentTransferObjekat();
                kto.setOperacija(Konstante.OPERACIJA_OBRISI_STAVKU);
                kto.setParametar(NitKlijent.getInstance().getStavke().get(jtblStavke.getSelectedRow()));
                NitKlijent.out.writeObject(kto);
            } catch (IOException ex) {
                Logger.getLogger(PnlDnevnaBerba.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Izabrali ste vise ili niste izabrali ni jednu stavku!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnIzmeniStavkku;
    public static javax.swing.JTable jtblStavke;
    // End of variables declaration//GEN-END:variables

    private void popuniTabelu() {
        try {
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_VIDI_LISTU_STAVKI);
            kto.setParametar(dnevnaBerba);
            NitKlijent.out.writeObject(kto);
        } catch (IOException ex) {
            Logger.getLogger(PnlStavke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}