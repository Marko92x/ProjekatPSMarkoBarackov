/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.AdministrativniRadnik;
import domen.DnevnaBerba;
import domen.IDomenskiObjekat;
import domen.StavkaDnevneBerbe;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;
import kontroler.Kontroler;
import main.PnlServer;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author Marko
 */
public class KlijentNit extends Thread {

    Socket s;
    ObjectInputStream in;
    ObjectOutputStream out;

    int ulogovaniID;

    public KlijentNit(Socket s) {
        try {
            this.s = s;
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {

        try {
            while (true) {
                KlijentTransferObjekat kto = (KlijentTransferObjekat) in.readObject();
                int operacija = kto.getOperacija();
                if (operacija == Konstante.OPERACIJA_NADJI_ADMINISTRATIVNOG_RADNIKA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    try {
                        IDomenskiObjekat lm = Kontroler.getInstance().nadjiAdministrativnogRadnika((IDomenskiObjekat) kto.getParametar());
                        ulogovaniID = ((AdministrativniRadnik) lm).getAdministrativniRadnikID();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(lm);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija nadjiAdministrativnogRadnika(), Uspesnost: 1");
                        sto.setOperacija(Konstante.OPERACIJA_NADJI_ADMINISTRATIVNOG_RADNIKA);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija nadjiAdministrativnogRadnika(), Uspesnost: -1");
                        sto.setOperacija(Konstante.OPERACIJA_NADJI_ADMINISTRATIVNOG_RADNIKA);
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_UCITAJ_LISTU_MESTA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_UCITAJ_LISTU_MESTA);
                    try {
                        List<IDomenskiObjekat> mesta = Kontroler.getInstance().ucitajListuMesta();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(mesta);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija ucitajListuMesta(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Nema mesta u bazi!"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija ucitajListuMesta(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_KREIRAJ_NOVOG_DOBAVLJACA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_KREIRAJ_NOVOG_DOBAVLJACA);
                    try {
                        Kontroler.getInstance().kreirajNovogDobavljaca((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija kreirajNovogDobavljaca(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setException(ex);
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija kreirajNovogDobavljaca(), Uspesnost: -1");
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERAIJA_VRATI_LISTU_DOBAVLJACA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERAIJA_VRATI_LISTU_DOBAVLJACA);
                    try {
                        List<IDomenskiObjekat> dobavljaci = Kontroler.getInstance().vratiSveDobavljace();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(dobavljaci);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuDobavljaca(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Nema dobavljaca u bazi"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuDobavljaca(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_OBRISI_DOBAVLJACA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_OBRISI_DOBAVLJACA);
                    try {
                        Kontroler.getInstance().obrisiDobavljaca((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija operacijaObrisiDobavljaca, Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setException(new Exception("Greska pri brisanju dobavljaca"));
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija operacijaObrisiDobavljaca, Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_IZMENI_DOBAVLJACA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_IZMENI_DOBAVLJACA);
                    try {
                        Kontroler.getInstance().izmeniDobavljaca((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija izmeniDobavljaca(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setException(ex);
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija izmeniDobavljaca(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_VRATI_ZADUZENJA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_VRATI_ZADUZENJA);
                    try {
                        List<IDomenskiObjekat> lista = Kontroler.getInstance().vratiZaduzenja((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(lista);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiZaduzenja(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Greska pri vracanju liste zaduzenja!"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiZaduzenja(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_UNOS_ZADUZENJA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_UNOS_ZADUZENJA);
                    try {
                        Kontroler.getInstance().unosZaduzenja((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija unosZaduzenja(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija unosZaduzenja(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_IZMENI_ZADUZENJA) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_IZMENI_ZADUZENJA);
                    try {
                        Kontroler.getInstance().izmeniZaduzenje((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija unosZaduzenja(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija unosZaduzenja(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_VRATI_LISTU_DNEVNIH_BERBI) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_VRATI_LISTU_DNEVNIH_BERBI);
                    try {
                        List<IDomenskiObjekat> lista = Kontroler.getInstance().vratiListuDnevnihBerbi((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(lista);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuDnevnihBerbi(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Greska pri vracanju liste dnevnih berbi"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuDnevnihBerbi(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_UBACI_DNEVNU_BERBU) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_UBACI_DNEVNU_BERBU);
                    try {
                        Kontroler.getInstance().ubaciDnevnuBerbu((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(Kontroler.getInstance().vratiListuDnevnihBerbi(((DnevnaBerba) (IDomenskiObjekat) kto.getParametar()).getDobavljac()).get(Kontroler.getInstance().vratiListuDnevnihBerbi(((DnevnaBerba) (IDomenskiObjekat) kto.getParametar()).getDobavljac()).size() - 1));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija ubaciDnevnuBerbu(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija ubaciDnevnuBerbu(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_SACUVAJ_STAVKE_DNEVNE_BERBE) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_SACUVAJ_STAVKE_DNEVNE_BERBE);
                    try {
                        Kontroler.getInstance().ubaciStavkeDnevneBerbe((List<IDomenskiObjekat>) kto.getParametar());
//                        Kontroler.getInstance().ubaciStavkeDnevneBerbe(((Test)kto.getParametar()).getStavke());
//                        kto.setParametar(null);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci("Uspesno ubacivanje stavki");
                        PnlServer.jtfKonzolnaLinija.setText("Operacija sacuvajStavkeDnevneBerbe(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija sacuvajStavkeDnevneBerbe(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
//                if (operacija == Konstante.OPERACIJA_SACUVAJ_STAVKE_DNEVNE_BERBE1){
//                    ServerTransferObjekat sto = new ServerTransferObjekat();
//                    sto.setOperacija(Konstante.OPERACIJA_SACUVAJ_STAVKE_DNEVNE_BERBE1);
//                    try {
//                        Kontroler.getInstance().ubaciStavkeDnevneBerbe((List<IDomenskiObjekat>) kto.getParametar());
//                        sto.setUspesnostIzvrsenjaOperacije(1);
//                        sto.setPodaci("Uspesno ubacivanje stavki");
//                    } catch (Exception ex) {
//                        sto.setUspesnostIzvrsenjaOperacije(-1);
//                        sto.setException(ex);
//                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    out.writeObject(sto);
//                }
                if (operacija == Konstante.OPERACIJA_OBRISI_DNEVNU_BERBU) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_OBRISI_DNEVNU_BERBU);
                    try {
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci("Obrisana dnevna berba");
                        Kontroler.getInstance().obrisiDnevnuBerbu((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiDnevnuBerbu(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Greska pri brisanju dnevne berbe"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiDnevnuBerbu(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_OBRISI_DNEVNU_BERBU1) {

                    try {

                        Kontroler.getInstance().obrisiDnevnuBerbu((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiDnevnuBerbu(), Uspesnost: 1");
                    } catch (Exception ex) {
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiDnevnuBerbu(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (operacija == Konstante.OPERACIJA_VIDI_LISTU_STAVKI) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_VIDI_LISTU_STAVKI);
                    try {
                        List<IDomenskiObjekat> lista = Kontroler.getInstance().vratiListuStavki((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(lista);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuStavki(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Greska pri vracanju liste stavki"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuStavki(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_IZMENI_STAVKU) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_IZMENI_STAVKU);
                    try {
                        Kontroler.getInstance().izmeniStavku((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija izmeniStavku(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setException(ex);
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija izmeniStavku(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_SACUVAJ_STAVKU) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_SACUVAJ_STAVKU);
                    try {
                        Kontroler.getInstance().dodajStavku((IDomenskiObjekat) kto.getParametar());
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(Kontroler.getInstance().vratiListuStavki(((StavkaDnevneBerbe) kto.getParametar()).getDnevnaBerba()));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuStavki(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija vratiListuStavki(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_KREIRAJ_OBRACUNE) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_KREIRAJ_OBRACUNE);
                    try {
                        List<StavkaDnevneBerbe> stavke = Kontroler.getInstance().obracun(((List<Date>)kto.getParametar()).get(0), ((List<Date>)kto.getParametar()).get(1));
                        Kontroler.getInstance().createPdfObracuni(stavke, ((List<Date>)kto.getParametar()).get(0), ((List<Date>)kto.getParametar()).get(1));
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci("Uspesno ste kreirali obračun!");
                        PnlServer.jtfKonzolnaLinija.setText("Operacija ,createPdfObracuni(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija createPdfObracuni(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_KREIRAJ_STATISTIKU) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_KREIRAJ_STATISTIKU);
                    try {
                        List<StavkaDnevneBerbe> stavke = Kontroler.getInstance().obracun(((List<Date>)kto.getParametar()).get(0), ((List<Date>)kto.getParametar()).get(1));
                        Kontroler.getInstance().createPdfStatistika(stavke, ((List<Date>)kto.getParametar()).get(0), ((List<Date>)kto.getParametar()).get(1));
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci("Uspesno ste kreirali statistiku!");
                        PnlServer.jtfKonzolnaLinija.setText("Operacija ,createPdfStatistika(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        PnlServer.jtfKonzolnaLinija.setText("Operacija createPdfStatistika(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_OBRISI_DNEVNU_BERBU_2) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_OBRISI_DNEVNU_BERBU_2);
                    try {
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        Kontroler.getInstance().obrisiDnevnuBerbu((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiDnevnuBerbu(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Greska pri brisanju dnevne berbe"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiDnevnuBerbu(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
                if (operacija == Konstante.OPERACIJA_OBRISI_STAVKU) {
                    ServerTransferObjekat sto = new ServerTransferObjekat();
                    sto.setOperacija(Konstante.OPERACIJA_OBRISI_STAVKU);
                    try {
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci((IDomenskiObjekat) kto.getParametar());
                        Kontroler.getInstance().obrisiStavku((IDomenskiObjekat) kto.getParametar());
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiStavku(), Uspesnost: 1");
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(new Exception("Greska pri brisanju dnevne berbe"));
                        PnlServer.jtfKonzolnaLinija.setText("Operacija obrisiStavku(), Uspesnost: -1");
                        Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject(sto);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
