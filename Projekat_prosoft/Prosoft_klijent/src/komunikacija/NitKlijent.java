/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.AdministrativniRadnik;
import domen.DnevnaBerba;
import domen.Dobavljac;
import domen.IDomenskiObjekat;
import domen.StavkaDnevneBerbe;
import domen.Zaduzenje;
import forme.FrmGlavna;

import forme.modeltabele.TableModelDnevnaBerba;
import forme.modeltabele.TableModelDobavljac;
import forme.modeltabele.TableModelStavkaDnevneBerbe;
import forme.modeltabele.TableModelZaduzenja;
import forme.paneli.PnlDnevnaBerba;
import forme.paneli.PnlDobavljac;
import forme.paneli.PnlStavke;
import forme.paneli.PnlZaduzenja;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import konstante.Konstante;
import transfer.ServerTransferObjekat;

/**
 *
 * @author Marko
 */
public class NitKlijent extends Thread {

    public static NitKlijent instance;
    Socket s;
    public ObjectInputStream in;
    public static ObjectOutputStream out;
    private JFrame frmG;
    private JFrame frmL;
    private JDialog dijalog;
    private List<Dobavljac> dobavljaci;
    private List<Zaduzenje> zaduzenjaZaDobavljaca;
    private AdministrativniRadnik ulogovani;
    private List<DnevnaBerba> dnevneBerbe;
    private List<StavkaDnevneBerbe> stavke;
    private List<StavkaDnevneBerbe> pomocnaListaStavki;

    private NitKlijent() {
        try {
            s = new Socket("localhost", 9000);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            dobavljaci = new ArrayList<>();
            pomocnaListaStavki = new ArrayList<>();

        } catch (IOException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static NitKlijent getInstance() {
        if (instance == null) {
            instance = new NitKlijent();
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            while (true) {

                ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
                if (sto.getOperacija() == Konstante.OPERACIJA_NADJI_ADMINISTRATIVNOG_RADNIKA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        setUlogovani((AdministrativniRadnik) sto.getPodaci());
                        JOptionPane.showMessageDialog(null, "Uspešno ste ulogovani");
                        ((FrmGlavna) frmG).spPaneli.setViewportView(new PnlDobavljac(((FrmGlavna) frmG).spPaneli));
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_UCITAJ_LISTU_MESTA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {

                        PnlDobavljac.jcbMesto.setModel(new DefaultComboBoxModel(((List<IDomenskiObjekat>) sto.getPodaci()).toArray()));
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_KREIRAJ_NOVOG_DOBAVLJACA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        dobavljaci.add((Dobavljac) sto.getPodaci());
                        TableModelDobavljac tblm = (TableModelDobavljac) PnlDobavljac.jtblDobavljaci.getModel();
                        tblm.fireTableDataChanged();
                        JOptionPane.showMessageDialog(null, "Uspešno ste ubacili dobavljača");
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

                    }
                }
                if (sto.getOperacija() == Konstante.OPERAIJA_VRATI_LISTU_DOBAVLJACA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        dobavljaci = (List<Dobavljac>) sto.getPodaci();
                        PnlDobavljac.jtblDobavljaci.setModel(new TableModelDobavljac(dobavljaci));
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_OBRISI_DOBAVLJACA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        dobavljaci.remove((Dobavljac) sto.getPodaci());
                        TableModelDobavljac tblm = (TableModelDobavljac) PnlDobavljac.jtblDobavljaci.getModel();
                        tblm.fireTableDataChanged();
                        JOptionPane.showMessageDialog(null, "Uspešno ste obrisali dobavljača");
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_IZMENI_DOBAVLJACA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        Dobavljac d = (Dobavljac) sto.getPodaci();
                        for (int i = 0; i < dobavljaci.size(); i++) {
                            if (d.getJmbg().equals(dobavljaci.get(i).getJmbg())) {

                                dobavljaci.get(i).setIme(d.getIme());
                                dobavljaci.get(i).setPrezime(d.getPrezime());
                                dobavljaci.get(i).setBrojLicneKarte(d.getBrojLicneKarte());
                                dobavljaci.get(i).setBrojGazdinstva(d.getBrojGazdinstva());
                                dobavljaci.get(i).setBroj(d.getBroj());
                                dobavljaci.get(i).setUlica(d.getUlica());
                                dobavljaci.get(i).setMesto(d.getMesto());
                                dobavljaci.get(i).setTekuciRacun(d.getTekuciRacun());
                            }
                        }
                        TableModelDobavljac tblm = (TableModelDobavljac) PnlDobavljac.jtblDobavljaci.getModel();
                        tblm.fireTableDataChanged();
                        JOptionPane.showMessageDialog(null, "Uspešno ste promenili podatke");
                        dijalog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_VRATI_ZADUZENJA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        zaduzenjaZaDobavljaca = (List<Zaduzenje>) sto.getPodaci();
                        PnlZaduzenja.jtblZaduzenja.setModel(new TableModelZaduzenja(zaduzenjaZaDobavljaca));
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_UNOS_ZADUZENJA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        zaduzenjaZaDobavljaca.add((Zaduzenje) sto.getPodaci());
                        JOptionPane.showMessageDialog(null, "Uspešno ste uneli zaduženje!");
                        TableModelZaduzenja tblm = (TableModelZaduzenja) PnlZaduzenja.jtblZaduzenja.getModel();
                        tblm.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_IZMENI_ZADUZENJA) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        Zaduzenje z = (Zaduzenje) sto.getPodaci();
                        for (int i = 0; i < zaduzenjaZaDobavljaca.size(); i++) {
                            if (z.getZaduzenjeID() == zaduzenjaZaDobavljaca.get(i).getZaduzenjeID()) {
                                zaduzenjaZaDobavljaca.get(i).setDatumRazduzenja(z.getDatumRazduzenja());
                                zaduzenjaZaDobavljaca.get(i).setRazduzio(z.getRazduzio());
                            }
                        }
                        TableModelZaduzenja tblm = (TableModelZaduzenja) PnlZaduzenja.jtblZaduzenja.getModel();
                        tblm.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_VRATI_LISTU_DNEVNIH_BERBI) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        dnevneBerbe = (List<DnevnaBerba>) sto.getPodaci();
                        PnlDnevnaBerba.jtblDnevneBerbe.setModel(new TableModelDnevnaBerba(dnevneBerbe));
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_UBACI_DNEVNU_BERBU) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        JOptionPane.showMessageDialog(null, "Uspešno ste kreirali dnevnu berbu");
                        PnlDnevnaBerba.jlbIDDnevneBerbe.setText(((DnevnaBerba) sto.getPodaci()).getDnevnaBerbaID() + "");
                        PnlDnevnaBerba.id = PnlDnevnaBerba.jlbIDDnevneBerbe.getText();
                        dnevneBerbe.add((DnevnaBerba) sto.getPodaci());
                        TableModelDnevnaBerba tblm = (TableModelDnevnaBerba) PnlDnevnaBerba.jtblDnevneBerbe.getModel();
                        tblm.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_SACUVAJ_STAVKE_DNEVNE_BERBE) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        JOptionPane.showMessageDialog(null, "Uspešno ste sačuvali stavke");
                        pomocnaListaStavki.clear();
                        TableModelStavkaDnevneBerbe tblm = (TableModelStavkaDnevneBerbe) PnlDnevnaBerba.jtblStavke.getModel();
                        tblm.fireTableDataChanged();

                        PnlDnevnaBerba.flag = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
//                if (sto.getOperacija() == Konstante.OPERACIJA_SACUVAJ_STAVKE_DNEVNE_BERBE1){
//                    if (sto.getUspesnostIzvrsenjaOperacije() == 1){
//                        JOptionPane.showMessageDialog(null, "Uspesno ste sacuvali stavke");
//                        
//                        JDDodajJosStavki.getStavke().clear();
//                        TableModelStavkaDnevneBerbe tb = (TableModelStavkaDnevneBerbe) JDDodajJosStavki.jtblStavke.getModel();
//                        tb.fireTableDataChanged();
//                        dijalog.dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(null, sto.getException().getMessage());
//                    }
//                }
                if (sto.getOperacija() == Konstante.OPERACIJA_OBRISI_DNEVNU_BERBU) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        ((FrmGlavna) frmG).setujPanelDobavljaci();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_VIDI_LISTU_STAVKI) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        stavke = (List<StavkaDnevneBerbe>) sto.getPodaci();
                        TableModelStavkaDnevneBerbe tmsdb = new TableModelStavkaDnevneBerbe(stavke);
                        PnlStavke.jtblStavke.setModel(tmsdb);
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_IZMENI_STAVKU) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        StavkaDnevneBerbe s = (StavkaDnevneBerbe) sto.getPodaci();
                        for (int i = 0; i < stavke.size(); i++) {
                            if (s.getStavkaID() == stavke.get(i).getStavkaID()) {

                                stavke.get(i).setTacne(s.getTacne());
                                stavke.get(i).setCenaTacne(s.getCenaTacne());
                                stavke.get(i).setPrvaKlasa(s.getPrvaKlasa());
                                stavke.get(i).setCenaPrvaKlasa(s.getCenaPrvaKlasa());
                                stavke.get(i).setDrugaKlasa(s.getDrugaKlasa());
                                stavke.get(i).setCenaDrugaKlasa(s.getCenaDrugaKlasa());
                                stavke.get(i).setTrecaKlasa(s.getTrecaKlasa());
                                stavke.get(i).setCenaTrecaKlasa(s.getCenaTrecaKlasa());
                            }
                        }
                        TableModelStavkaDnevneBerbe tblm = (TableModelStavkaDnevneBerbe) PnlStavke.jtblStavke.getModel();
                        tblm.fireTableDataChanged();
                        JOptionPane.showMessageDialog(null, "Uspešno ste promenili podatke");
                        dijalog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_SACUVAJ_STAVKU) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        stavke = (List<StavkaDnevneBerbe>) sto.getPodaci();
//                        TableModelStavkaDnevneBerbe tblm = (TableModelStavkaDnevneBerbe) PnlStavke.jtblStavke.getModel();
//                        tblm.fireTableDataChanged();
//                        PnlStavke.jtblStavke.repaint();
                        PnlStavke.jtblStavke.setModel(new TableModelStavkaDnevneBerbe(stavke));
                        JOptionPane.showMessageDialog(null, "Uspešno ste ubacili stavku");
                        dijalog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                        
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_KREIRAJ_OBRACUNE) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        JOptionPane.showMessageDialog(null, sto.getPodaci());
                        dijalog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                        
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_KREIRAJ_STATISTIKU) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        JOptionPane.showMessageDialog(null, sto.getPodaci());
                        dijalog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                        
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_OBRISI_DNEVNU_BERBU_2) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        dnevneBerbe.remove((DnevnaBerba) sto.getPodaci());
                        TableModelDnevnaBerba tblm = (TableModelDnevnaBerba) PnlDnevnaBerba.jtblDnevneBerbe.getModel();
                        tblm.fireTableDataChanged();
                        JOptionPane.showMessageDialog(null, "Uspešno ste obrisali dnevnu berbu!");
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (sto.getOperacija() == Konstante.OPERACIJA_OBRISI_STAVKU) {
                    if (sto.getUspesnostIzvrsenjaOperacije() == 1) {
                        stavke.remove((StavkaDnevneBerbe) sto.getPodaci());
                        TableModelStavkaDnevneBerbe tblm = (TableModelStavkaDnevneBerbe) PnlStavke.jtblStavke.getModel();
                        tblm.fireTableDataChanged();
                        JOptionPane.showMessageDialog(null, "Uspešno ste obrisali stavku!");
                    } else {
                        JOptionPane.showMessageDialog(null, sto.getException().getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex){
            System.out.println("######" + ex.getMessage());
            ex.printStackTrace();
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inicijalizuj() {

    }

    public void setFrmG(JFrame frmG) {
        this.frmG = frmG;
    }

    public void setFrmL(JFrame frmL) {
        this.frmL = frmL;
    }

    public List<Dobavljac> getDobavljaci() {
        return dobavljaci;
    }

    public AdministrativniRadnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(AdministrativniRadnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public List<Zaduzenje> getZaduzenjaZaDobavljaca() {
        return zaduzenjaZaDobavljaca;
    }

    public List<DnevnaBerba> getDnevneBerbe() {
        return dnevneBerbe;
    }
    
    public List<StavkaDnevneBerbe> getStavke() {
        return stavke;
    }

    public JDialog getDijalog() {
        return dijalog;
    }

    public void setDijalog(JDialog dijalog) {
        this.dijalog = dijalog;
    }

    public List<StavkaDnevneBerbe> getPomocnaListaStavki() {
        return pomocnaListaStavki;
    }

    public void setPomocnaListaStavki(List<StavkaDnevneBerbe> pomocnaListaStavki) {
        this.pomocnaListaStavki = pomocnaListaStavki;
    }

}
