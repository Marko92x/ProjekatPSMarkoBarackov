/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.dijalozi;

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
public class JDIzmeniStavku extends javax.swing.JDialog {

    StavkaDnevneBerbe stavka;

    /**
     * Creates new form JDIzmeniStavku
     */
    public JDIzmeniStavku(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public JDIzmeniStavku(java.awt.Frame parent, boolean modal, StavkaDnevneBerbe stavka) {
        this(parent, modal);
        this.stavka = stavka;
        popuni();
        pack();
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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jtfBraonTacne = new javax.swing.JTextField();
        jtfBraonRimfuz = new javax.swing.JTextField();
        jtfBukovaca = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jtfCenaBraonTacne = new javax.swing.JTextField();
        jtfCenaBraonRimfuz = new javax.swing.JTextField();
        jtfCenaBukovaca = new javax.swing.JTextField();

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

        jLabel9.setText("Braon tacne:");

        jLabel10.setText("Braon rimfuz:");

        jLabel11.setText("Bukovaca:");

        jLabel12.setText("Cena:");

        jLabel13.setText("Cena:");

        jLabel14.setText("Cena:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfTrecaKlasa)
                                    .addComponent(jtfDrugaKlasa)
                                    .addComponent(jtfBraonTacne)
                                    .addComponent(jtfBraonRimfuz)
                                    .addComponent(jtfBukovaca)))
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
                                .addComponent(jtfCenaTacne, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jtfCenaBraonTacne, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jtfCenaBraonRimfuz, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jtfCenaBukovaca, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)))
                .addGap(43, 43, 43))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtfBraonTacne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jtfCenaBraonTacne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtfBraonRimfuz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jtfCenaBraonRimfuz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtfBukovaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jtfCenaBukovaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jbtnSacuvaj)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        try {
            StavkaDnevneBerbe s = new StavkaDnevneBerbe();
            s.setStavkaID(stavka.getStavkaID());
            s.setDobavljac(stavka.getDobavljac());
            s.setDnevnaBerba(stavka.getDnevnaBerba());
            s.setTacne(Double.parseDouble(jtfTacne.getText()));
            s.setCenaTacne(Double.parseDouble(jtfCenaTacne.getText()));
            s.setPrvaKlasa(Double.parseDouble(jtfPrvaKlasa.getText()));
            s.setCenaPrvaKlasa(Double.parseDouble(jtfCenaPrvaKlasa.getText()));
            s.setDrugaKlasa(Double.parseDouble(jtfDrugaKlasa.getText()));
            s.setCenaDrugaKlasa(Double.parseDouble(jtfCenaDrugaKlasa.getText()));
            s.setTrecaKlasa(Double.parseDouble(jtfTrecaKlasa.getText()));
            s.setCenaTrecaKlasa(Double.parseDouble(jtfCenaTrecaKlasa.getText()));
            s.setBraonTacne(Double.parseDouble(jtfBraonTacne.getText()));
            s.setCenaBraonTacne(Double.parseDouble(jtfCenaBraonTacne.getText()));
            s.setBraonRimfuz(Double.parseDouble(jtfBraonRimfuz.getText()));
            s.setCenaBraonRimfuz(Double.parseDouble(jtfCenaBraonRimfuz.getText()));
            s.setBukovaca(Double.parseDouble(jtfBukovaca.getText()));
            s.setCenaBukovaca(Double.parseDouble(jtfCenaBukovaca.getText()));
            NitKlijent.getInstance().setDijalog(this);
            KlijentTransferObjekat kto = new KlijentTransferObjekat();
            kto.setOperacija(Konstante.OPERACIJA_IZMENI_STAVKU);
            kto.setParametar(s);
            NitKlijent.out.writeObject(kto);
        } catch (IOException ex) {
            Logger.getLogger(JDIzmeniStavku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Pogresno ste uneli podatke!");
            Logger.getLogger(JDIzmeniStavku.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(JDIzmeniStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDIzmeniStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDIzmeniStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDIzmeniStavku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDIzmeniStavku dialog = new JDIzmeniStavku(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JTextField jtfBraonRimfuz;
    private javax.swing.JTextField jtfBraonTacne;
    private javax.swing.JTextField jtfBukovaca;
    private javax.swing.JTextField jtfCenaBraonRimfuz;
    private javax.swing.JTextField jtfCenaBraonTacne;
    private javax.swing.JTextField jtfCenaBukovaca;
    private javax.swing.JTextField jtfCenaDrugaKlasa;
    private javax.swing.JTextField jtfCenaPrvaKlasa;
    private javax.swing.JTextField jtfCenaTacne;
    private javax.swing.JTextField jtfCenaTrecaKlasa;
    private javax.swing.JTextField jtfDrugaKlasa;
    private javax.swing.JTextField jtfPrvaKlasa;
    private javax.swing.JTextField jtfTacne;
    private javax.swing.JTextField jtfTrecaKlasa;
    // End of variables declaration//GEN-END:variables

    private void popuni() {
        jtfTacne.setText(stavka.getTacne() + "");
        jtfPrvaKlasa.setText(stavka.getPrvaKlasa() + "");
        jtfDrugaKlasa.setText(stavka.getDrugaKlasa() + "");
        jtfTrecaKlasa.setText(stavka.getTrecaKlasa() + "");
        jtfCenaTacne.setText(stavka.getCenaTacne() + "");
        jtfCenaPrvaKlasa.setText(stavka.getCenaPrvaKlasa() + "");
        jtfCenaDrugaKlasa.setText(stavka.getCenaDrugaKlasa() + "");
        jtfCenaTrecaKlasa.setText(stavka.getCenaTrecaKlasa() + "");
        jtfBraonTacne.setText(stavka.getBraonTacne() + "");
        jtfCenaBraonTacne.setText(stavka.getCenaBraonTacne() + "");
        jtfBraonRimfuz.setText(stavka.getBraonRimfuz() + "");
        jtfCenaBraonRimfuz.setText(stavka.getCenaBraonRimfuz() + "");
        jtfBukovaca.setText(stavka.getBukovaca() + "");
        jtfCenaBukovaca.setText(stavka.getCenaBukovaca() + "");
    }
}
