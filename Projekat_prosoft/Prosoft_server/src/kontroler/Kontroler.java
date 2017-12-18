/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import domen.AdministrativniRadnik;
import domen.Dobavljac;
import domen.IDomenskiObjekat;
import domen.StavkaDnevneBerbe;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;
import so.administrativniradnik.SONadjiAdministrativnogRadnika;
import so.dnevnaberba.SOObrisiDnevnuBerbu;
import so.dnevnaberba.SOUbaciDnevnuBerbu;
import so.dnevnaberba.SOVratiListuDnevnihBerbi;
import so.dobavljac.SOIzmeniDobavljaca;
import so.dobavljac.SOKreirajNovogDobavljaca;
import so.dobavljac.SOObrisiDobavljaca;
import so.dobavljac.SOVratiListuDobavljaca;
import so.mesto.SOUcitajListuMesta;
import so.stavkadnevneberbe.SOIzmeniStavku;
import so.stavkadnevneberbe.SONedeljniObracun;
import so.stavkadnevneberbe.SOObrisiStavku;
import so.stavkadnevneberbe.SOSacuvajStavkeDnevneBerbe;
import so.stavkadnevneberbe.SOSacuvajStavku;
import so.stavkadnevneberbe.SOVratiListuStavki;
import so.zaduzenje.SOIzmeniZaduzenja;
import so.zaduzenje.SOUnosZaduzenja;
import so.zaduzenje.SOVratiZaduzenja;

/**
 *
 * @author Marko
 */
public class Kontroler {

    public static Kontroler instance;
    private String jmbgRegex = "(((0[1-9]|[12]\\d|3[01])(0[1-9]|1[012]))|((0[1-9]|[12]\\d)02))"
            + "(9|0)\\d\\d\\d\\d(5|0)\\d\\d\\d";
    private String intRegex = "\\d+";
    private String tekuciRacunRegex = "\\d+\\p{Z}*-\\p{Z}*\\d+\\p{Z}*-\\p{Z}*\\d+";

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public IDomenskiObjekat nadjiAdministrativnogRadnika(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SONadjiAdministrativnogRadnika();
        sos.izvrsiOperaciju();
        List<IDomenskiObjekat> lista = ((SONadjiAdministrativnogRadnika) sos).getRadnici();
        for (int i = 0; i < lista.size(); i++) {
            if (((AdministrativniRadnik) ido).getKorisnickoIme().equals(((AdministrativniRadnik) lista.get(i)).getKorisnickoIme()) && ((AdministrativniRadnik) ido).getKorisnickaSifra().equals(((AdministrativniRadnik) lista.get(i)).getKorisnickaSifra())) {
                return lista.get(i);
            }
        }
        throw new Exception("Sistem ne moze da nadje administrativnog radnika na osnovu unetih vrednosti");
    }

    public List<IDomenskiObjekat> ucitajListuMesta() throws Exception {
        AbstractSO sos = new SOUcitajListuMesta();
        sos.izvrsiOperaciju();
        return ((SOUcitajListuMesta) sos).getMesta();
    }

    public void kreirajNovogDobavljaca(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOKreirajNovogDobavljaca((Dobavljac) ido);
        sos.izvrsiOperaciju();
    }

    public List<IDomenskiObjekat> vratiSveDobavljace() throws Exception {
        AbstractSO sos = new SOVratiListuDobavljaca();
        sos.izvrsiOperaciju();
        return ((SOVratiListuDobavljaca) sos).getDobavljaci();
    }

    public void obrisiDobavljaca(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOObrisiDobavljaca(ido);
        sos.izvrsiOperaciju();

    }

    public void izmeniDobavljaca(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOIzmeniDobavljaca(ido);
        sos.izvrsiOperaciju();
    }

    public List<IDomenskiObjekat> vratiZaduzenja(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOVratiZaduzenja(ido);
        sos.izvrsiOperaciju();
        return ((SOVratiZaduzenja) sos).getZaduzenja();
    }

    public void unosZaduzenja(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOUnosZaduzenja(ido);
        sos.izvrsiOperaciju();
    }

    public void izmeniZaduzenje(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOIzmeniZaduzenja(ido);
        sos.izvrsiOperaciju();
    }

    public List<IDomenskiObjekat> vratiListuDnevnihBerbi(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOVratiListuDnevnihBerbi(ido);
        sos.izvrsiOperaciju();
        return ((SOVratiListuDnevnihBerbi) sos).getDnevneBerbe();
    }

    public void ubaciDnevnuBerbu(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOUbaciDnevnuBerbu(ido);
        sos.izvrsiOperaciju();
    }

    public void ubaciStavkeDnevneBerbe(List<IDomenskiObjekat> lido) throws Exception {
        AbstractSO sos = new SOSacuvajStavkeDnevneBerbe(lido);
        sos.izvrsiOperaciju();
    }

    public void obrisiDnevnuBerbu(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOObrisiDnevnuBerbu(ido);
        sos.izvrsiOperaciju();
    }

    public List<IDomenskiObjekat> vratiListuStavki(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOVratiListuStavki(ido);
        sos.izvrsiOperaciju();
        return ((SOVratiListuStavki) sos).getStavke();
    }

    public void izmeniStavku(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOIzmeniStavku(ido);
        sos.izvrsiOperaciju();
    }

    public void dodajStavku(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOSacuvajStavku(ido);
        sos.izvrsiOperaciju();
    }

    public List<StavkaDnevneBerbe> obracun(Date pocetak, Date kraj) throws Exception {
        AbstractSO sos = new SONedeljniObracun(pocetak, kraj);
        sos.izvrsiOperaciju();
        return ((SONedeljniObracun) sos).getStavke();
    }
    
    public void obrisiStavku(IDomenskiObjekat ido) throws Exception {
        AbstractSO sos = new SOObrisiStavku(ido);
        sos.izvrsiOperaciju();

    }

    public String getJmbgRegex() {
        return jmbgRegex;
    }

    public String getIntRegex() {
        return intRegex;
    }

    public String getTekuciRacunRegex() {
        return tekuciRacunRegex;
    }

    public void createPdfObracuni(List<StavkaDnevneBerbe> stavke, Date pocetak, Date kraj) {
        Document document = new Document();
//        document.setPageSize(PageSize.A4.rotate());
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(System.getProperty("user.home") + "/Desktop/Obracuni/" + pocetak + " " + kraj + ".pdf"));
            document.open();
//                        String text = "";
//                        for (int i = 0; i < 10000; i++) {
//                                text += "test";
//              ,          }
//            String jmbg = stavke.get(0).getDobavljac().getJmbg();

            List<String> jmbgovi = new ArrayList<>();
            Map<Double, Double> kt = new HashMap<>();
            Map<Double, Double> k1 = new HashMap<>();
            Map<Double, Double> k2 = new HashMap<>();
            Map<Double, Double> k3 = new HashMap<>();
            Map<Double, Double> kbt = new HashMap<>();
            Map<Double, Double> kbr = new HashMap<>();
            Map<Double, Double> kb = new HashMap<>();

            for (StavkaDnevneBerbe stavkaDnevneBerbe : stavke) {
                if (!jmbgovi.contains(stavkaDnevneBerbe.getDobavljac().getJmbg())) {
                    jmbgovi.add(stavkaDnevneBerbe.getDobavljac().getJmbg());
                }
            }
            Map<Double, Double> ukupnoTacne = new HashMap<>();
            Map<Double, Double> ukupnoPrvaKlasa = new HashMap<>();
            Map<Double, Double> ukupnoDrugaKlasa = new HashMap<>();
            Map<Double, Double> ukupnoTrecaKlasa = new HashMap<>();
            Map<Double, Double> ukupnoBraonTacne = new HashMap<>();
            Map<Double, Double> ukupnoBraonRimfuz = new HashMap<>();
            Map<Double, Double> ukupnoBukovaca = new HashMap<>();
            for (String j : jmbgovi) {
                String imeDobavljaca = null;
                for (StavkaDnevneBerbe stavkaDnevneBerbe : stavke) {
                    if (stavkaDnevneBerbe.getDobavljac().getJmbg().equals(j)) {
                        if (imeDobavljaca == null) {
                            imeDobavljaca = stavkaDnevneBerbe.getDobavljac().getIme() + " " + stavkaDnevneBerbe.getDobavljac().getPrezime();

                        }
                        if (!kt.containsKey(stavkaDnevneBerbe.getCenaTacne()) && stavkaDnevneBerbe.getTacne() != 0) {
                            kt.put(stavkaDnevneBerbe.getCenaTacne(), new Double(0));
                        }
                        if (!k1.containsKey(stavkaDnevneBerbe.getCenaPrvaKlasa()) && stavkaDnevneBerbe.getCenaPrvaKlasa() != 0) {
                            k1.put(stavkaDnevneBerbe.getCenaPrvaKlasa(), new Double(0));
                        }
                        if (!k2.containsKey(stavkaDnevneBerbe.getCenaDrugaKlasa()) && stavkaDnevneBerbe.getCenaDrugaKlasa() != 0) {
                            k2.put(stavkaDnevneBerbe.getCenaDrugaKlasa(), new Double(0));
                        }
                        if (!k3.containsKey(stavkaDnevneBerbe.getCenaTrecaKlasa()) && stavkaDnevneBerbe.getCenaTrecaKlasa() != 0) {
                            k3.put(stavkaDnevneBerbe.getCenaTrecaKlasa(), new Double(0));
                        }
                        if (!kbt.containsKey(stavkaDnevneBerbe.getCenaBraonTacne()) && stavkaDnevneBerbe.getCenaBraonTacne()!= 0) {
                            kbt.put(stavkaDnevneBerbe.getCenaBraonTacne(), new Double(0));
                        }
                        if (!kbr.containsKey(stavkaDnevneBerbe.getCenaBraonRimfuz()) && stavkaDnevneBerbe.getCenaBraonRimfuz()!= 0) {
                            kbr.put(stavkaDnevneBerbe.getCenaBraonRimfuz(), new Double(0));
                        }
                        if (!kb.containsKey(stavkaDnevneBerbe.getCenaBukovaca()) && stavkaDnevneBerbe.getCenaBukovaca()!= 0) {
                            kb.put(stavkaDnevneBerbe.getCenaBukovaca(), new Double(0));
                        }
                    }
                }
                for (StavkaDnevneBerbe s : stavke) {
                    if (s.getDobavljac().getJmbg().equals(j)) {
                        if (kt.containsKey(s.getCenaTacne())) {
                            kt.put(s.getCenaTacne(), kt.get(s.getCenaTacne()) + s.getTacne());
                        }
                        if (k1.containsKey(s.getCenaPrvaKlasa())) {
                            k1.put(s.getCenaPrvaKlasa(), k1.get(s.getCenaPrvaKlasa()) + s.getPrvaKlasa());
                        }
                        if (k2.containsKey(s.getCenaDrugaKlasa())) {
                            k2.put(s.getCenaDrugaKlasa(), k2.get(s.getCenaDrugaKlasa()) + s.getDrugaKlasa());
                        }
                        if (k3.containsKey(s.getCenaTrecaKlasa())) {
                            k3.put(s.getCenaTrecaKlasa(), k3.get(s.getCenaTrecaKlasa()) + s.getTrecaKlasa());
                        }
                        if (kbt.containsKey(s.getCenaBraonTacne())) {
                            kbt.put(s.getCenaBraonTacne(), kbt.get(s.getCenaBraonTacne()) + s.getBraonTacne());
                        }
                        if (kbr.containsKey(s.getCenaBraonRimfuz())) {
                            kbr.put(s.getCenaBraonRimfuz(), kbr.get(s.getCenaBraonRimfuz()) + s.getBraonRimfuz());
                        }
                        if (kb.containsKey(s.getCenaBukovaca())) {
                            kb.put(s.getCenaBukovaca(), kb.get(s.getCenaBukovaca()) + s.getBukovaca());
                        }
                    }
                }
                PdfPTable table = new PdfPTable(4);
                Paragraph p = new Paragraph(imeDobavljaca);
                document.add(p);
                p = new Paragraph("Od: " + pocetak + " do: " + kraj);
                p.add(new Paragraph(" "));
                document.add(p);
                PdfPCell c = new PdfPCell(new Phrase(""));
                PdfPCell c1 = new PdfPCell(new Phrase("Naziv"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase("Kolicina"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase("Cena"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase("Ukupno"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
//                table.setHeaderRows(1);
                Iterator it = kt.entrySet().iterator();
                Double ukupnoKolicinaT = 0.0;
                Double ukupnoCenaT = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    table.addCell("Tacne");
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaT += (Double) pair.getValue();
                    ukupnoCenaT += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                    if (!ukupnoTacne.containsKey((Double) pair.getKey())) {
                        ukupnoTacne.put((Double) pair.getKey(), (Double) pair.getValue());
                    } else {
                        ukupnoTacne.put((Double) pair.getKey(), ukupnoTacne.get((Double) pair.getKey()) + (Double) pair.getValue());
                    }
                }
                if (ukupnoKolicinaT != 0) {
                    c.setBorder(0);
                    table.addCell(c);
                    table.addCell(round(ukupnoKolicinaT, 2) + "");
                    table.addCell(c);
                    table.addCell(round(ukupnoCenaT, 2) + "");
                }

                it = k1.entrySet().iterator();
                Double ukupnoKolicinaP = 0.0;
                Double ukupnoCenaP = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    table.addCell("Prva klasa");
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaP += (Double) pair.getValue();
                    ukupnoCenaP += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                    if (!ukupnoPrvaKlasa.containsKey((Double) pair.getKey())) {
                        ukupnoPrvaKlasa.put((Double) pair.getKey(), (Double) pair.getValue());
                    } else {
                        ukupnoPrvaKlasa.put((Double) pair.getKey(), ukupnoPrvaKlasa.get((Double) pair.getKey()) + (Double) pair.getValue());
                    }
                }
                if (ukupnoKolicinaP != 0) {
                    table.addCell(c);
                    table.addCell(round(ukupnoKolicinaP, 2) + "");
                    table.addCell(c);
                    table.addCell(round(ukupnoCenaP, 2) + "");
                }

                it = k2.entrySet().iterator();
                Double ukupnoKolicinaD = 0.0;
                Double ukupnoCenaD = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    table.addCell("Druga klasa");
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaD += (Double) pair.getValue();
                    ukupnoCenaD += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                    if (!ukupnoDrugaKlasa.containsKey((Double) pair.getKey())) {
                        ukupnoDrugaKlasa.put((Double) pair.getKey(), (Double) pair.getValue());
                    } else {
                        ukupnoDrugaKlasa.put((Double) pair.getKey(), ukupnoDrugaKlasa.get((Double) pair.getKey()) + (Double) pair.getValue());
                    }
                }
                if (ukupnoKolicinaD != 0) {
                    table.addCell(c);
                    table.addCell(round(ukupnoKolicinaD, 2) + "");
                    table.addCell(c);
                    table.addCell(round(ukupnoCenaD, 2) + "");
                }

                it = k3.entrySet().iterator();
                Double ukupnoKolicinaTr = 0.0;
                Double ukupnoCenaTr = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    table.addCell("Treca klasa");
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaTr += (Double) pair.getValue();
                    ukupnoCenaTr += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                    if (!ukupnoTrecaKlasa.containsKey((Double) pair.getKey())) {
                        ukupnoTrecaKlasa.put((Double) pair.getKey(), (Double) pair.getValue());
                    } else {
                        ukupnoTrecaKlasa.put((Double) pair.getKey(), ukupnoTrecaKlasa.get((Double) pair.getKey()) + (Double) pair.getValue());
                    }
                }
                if (ukupnoKolicinaTr != 0) {
                    table.addCell(c);
                    table.addCell(round(ukupnoKolicinaTr, 2) + "");
                    table.addCell(c);
                    table.addCell(round(ukupnoCenaTr, 2) + "");
                }
                
                it = kbt.entrySet().iterator();
                Double ukupnoKolicinaBt = 0.0;
                Double ukupnoCenaBt = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    table.addCell("Braon tacne");
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaBt += (Double) pair.getValue();
                    ukupnoCenaBt += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                    if (!ukupnoBraonTacne.containsKey((Double) pair.getKey())) {
                        ukupnoBraonTacne.put((Double) pair.getKey(), (Double) pair.getValue());
                    } else {
                        ukupnoBraonTacne.put((Double) pair.getKey(), ukupnoBraonTacne.get((Double) pair.getKey()) + (Double) pair.getValue());
                    }
                }
                if (ukupnoKolicinaBt != 0) {
                    table.addCell(c);
                    table.addCell(round(ukupnoKolicinaBt, 2) + "");
                    table.addCell(c);
                    table.addCell(round(ukupnoCenaBt, 2) + "");
                }
                
                it = kbr.entrySet().iterator();
                Double ukupnoKolicinaBr = 0.0;
                Double ukupnoCenaBr = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    table.addCell("Braon rimfuz");
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaBr += (Double) pair.getValue();
                    ukupnoCenaBr += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                    if (!ukupnoBraonRimfuz.containsKey((Double) pair.getKey())) {
                        ukupnoBraonRimfuz.put((Double) pair.getKey(), (Double) pair.getValue());
                    } else {
                        ukupnoBraonRimfuz.put((Double) pair.getKey(), ukupnoBraonRimfuz.get((Double) pair.getKey()) + (Double) pair.getValue());
                    }
                }
                if (ukupnoKolicinaBr != 0) {
                    table.addCell(c);
                    table.addCell(round(ukupnoKolicinaBr, 2) + "");
                    table.addCell(c);
                    table.addCell(round(ukupnoCenaBr, 2) + "");
                }
                
                it = kb.entrySet().iterator();
                Double ukupnoKolicinaB = 0.0;
                Double ukupnoCenaB = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    table.addCell("Bukovaca");
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaB += (Double) pair.getValue();
                    ukupnoCenaB += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                    if (!ukupnoBukovaca.containsKey((Double) pair.getKey())) {
                        ukupnoBukovaca.put((Double) pair.getKey(), (Double) pair.getValue());
                    } else {
                        ukupnoBukovaca.put((Double) pair.getKey(), ukupnoBukovaca.get((Double) pair.getKey()) + (Double) pair.getValue());
                    }
                }
                if (ukupnoKolicinaB != 0) {
                    table.addCell(c);
                    table.addCell(round(ukupnoKolicinaB, 2) + "");
                    table.addCell(c);
                    table.addCell(round(ukupnoCenaB, 2) + "");
                }

                Double ukupnoCena = ukupnoCenaD + ukupnoCenaP + ukupnoCenaT + ukupnoCenaTr
                        + ukupnoCenaBt + ukupnoCenaBr + ukupnoCenaB;
                Double ukupnoKolicina = ukupnoKolicinaD + ukupnoKolicinaP + ukupnoKolicinaT + ukupnoKolicinaTr
                        + ukupnoKolicinaBt + ukupnoKolicinaBr + ukupnoKolicinaB;

                table.addCell("UKUPNO:");
                table.addCell(round(ukupnoKolicina, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoCena, 2) + "");

                document.add(table);
                document.add(new Paragraph(" "));
                kt.clear();
                k1.clear();
                k2.clear();
                k3.clear();
                kbt.clear();
                kbr.clear();
                kb.clear();
                //dodavanje u pdf
                //ciscenje lista
            }
            PdfPTable table = new PdfPTable(4);
            Paragraph p = new Paragraph("SVE UKUPNO");
            p.add(new Paragraph(" "));
            document.add(p);

            PdfPCell c = new PdfPCell(new Phrase(""));

            Double ukupnoKolicinaT = 0.0;
            Double ukupnoVrednostT = 0.0;
            Iterator it = ukupnoTacne.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                table.addCell("Tacne");
                if ((Double) pair.getKey() == 110) {
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round(106.0, 2) + "");
                    ukupnoKolicinaT += (Double) pair.getValue();
                    ukupnoVrednostT += (106 * (Double) pair.getValue());
                    table.addCell(round(106 * (Double) pair.getValue(), 2) + "");
                } else {
                    table.addCell(round((Double) pair.getValue(), 2) + "");
                    table.addCell(round((Double) pair.getKey(), 2) + "");
                    ukupnoKolicinaT += (Double) pair.getValue();
                    ukupnoVrednostT += ((Double) pair.getKey() * (Double) pair.getValue());
                    table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
                }
            }
            if (ukupnoKolicinaT != 0) {
                c.setBorder(0);
                table.addCell(c);
                table.addCell(round(ukupnoKolicinaT, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoVrednostT, 2) + "");
            }

            it = ukupnoPrvaKlasa.entrySet().iterator();
            Double ukupnoKolicinaP = 0.0;
            Double ukupnoVrednostP = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                table.addCell("Prva klasa");
                table.addCell(round((Double) pair.getValue(), 2) + "");
                table.addCell(round((Double) pair.getKey(), 2) + "");
                ukupnoKolicinaP += (Double) pair.getValue();
                ukupnoVrednostP += ((Double) pair.getKey() * (Double) pair.getValue());
                table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
            }
            if (ukupnoKolicinaP != 0) {
                table.addCell(c);
                table.addCell(round(ukupnoKolicinaP, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoVrednostP, 2) + "");
            }

            it = ukupnoDrugaKlasa.entrySet().iterator();
            Double ukupnoKolicinaD = 0.0;
            Double ukupnoVrednostD = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                table.addCell("Druga klasa");
                table.addCell(round((Double) pair.getValue(), 2) + "");
                table.addCell(round((Double) pair.getKey(), 2) + "");
                ukupnoKolicinaD += (Double) pair.getValue();
                ukupnoVrednostD += ((Double) pair.getKey() * (Double) pair.getValue());
                table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
            }
            if (ukupnoKolicinaD != 0) {
                table.addCell(c);
                table.addCell(round(ukupnoKolicinaD, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoVrednostD, 2) + "");
            }

            it = ukupnoTrecaKlasa.entrySet().iterator();
            Double ukupnoKolicinaTr = 0.0;
            Double ukupnoVrednostTr = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                table.addCell("Treca klasa");
                table.addCell(round((Double) pair.getValue(), 2) + "");
                table.addCell(round((Double) pair.getKey(), 2) + "");
                ukupnoKolicinaTr += (Double) pair.getValue();
                ukupnoVrednostTr += ((Double) pair.getKey() * (Double) pair.getValue());
                table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
            }
            if (ukupnoKolicinaTr != 0) {
                table.addCell(c);
                table.addCell(round(ukupnoKolicinaTr, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoVrednostTr, 2) + "");
            }
            
            it = ukupnoBraonTacne.entrySet().iterator();
            Double ukupnoKolicinaBt = 0.0;
            Double ukupnoVrednostBt = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                table.addCell("Braon tacne");
                table.addCell(round((Double) pair.getValue(), 2) + "");
                table.addCell(round((Double) pair.getKey(), 2) + "");
                ukupnoKolicinaBt += (Double) pair.getValue();
                ukupnoVrednostBt += ((Double) pair.getKey() * (Double) pair.getValue());
                table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
            }
            if (ukupnoKolicinaBt != 0) {
                table.addCell(c);
                table.addCell(round(ukupnoKolicinaBt, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoVrednostBt, 2) + "");
            }

            it = ukupnoBraonRimfuz.entrySet().iterator();
            Double ukupnoKolicinaBr = 0.0;
            Double ukupnoVrednostBr = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                table.addCell("Braon rimfuz");
                table.addCell(round((Double) pair.getValue(), 2) + "");
                table.addCell(round((Double) pair.getKey(), 2) + "");
                ukupnoKolicinaBr += (Double) pair.getValue();
                ukupnoVrednostBr += ((Double) pair.getKey() * (Double) pair.getValue());
                table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
            }
            if (ukupnoKolicinaBr != 0) {
                table.addCell(c);
                table.addCell(round(ukupnoKolicinaBr, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoVrednostBr, 2) + "");
            }
            
            it = ukupnoBukovaca.entrySet().iterator();
            Double ukupnoKolicinaB = 0.0;
            Double ukupnoVrednostB = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                table.addCell("Bukovaca");
                table.addCell(round((Double) pair.getValue(), 2) + "");
                table.addCell(round((Double) pair.getKey(), 2) + "");
                ukupnoKolicinaB += (Double) pair.getValue();
                ukupnoVrednostB += ((Double) pair.getKey() * (Double) pair.getValue());
                table.addCell(round((Double) pair.getKey() * (Double) pair.getValue(), 2) + "");
            }
            if (ukupnoKolicinaB != 0) {
                table.addCell(c);
                table.addCell(round(ukupnoKolicinaB, 2) + "");
                table.addCell(c);
                table.addCell(round(ukupnoVrednostB, 2) + "");
            }
            
            Double ukupnoVrednostSve = ukupnoVrednostT + ukupnoVrednostP + ukupnoVrednostD + ukupnoVrednostTr 
                    + ukupnoVrednostBt + ukupnoVrednostBr + ukupnoVrednostB;
            Double ukupnoKolicina = ukupnoKolicinaD + ukupnoKolicinaP + ukupnoKolicinaT + ukupnoKolicinaTr
                    + ukupnoKolicinaBt + ukupnoKolicinaBr + ukupnoKolicinaB;

            table.addCell("UKUPNO:");
            table.addCell(round(ukupnoKolicina, 2) + "");
            table.addCell(c);
            table.addCell(round(ukupnoVrednostSve, 2) + "");

            document.add(table);
//            String text = "Marko";
//
//            // t.setBorderColor(BaseColor.GRAY);
//            // t.setPadding(4);
//            // t.setSpacing(4);
//            // t.setBorderWidth(1);
//            PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//
//            c1 = new PdfPCell(new Phrase("Table Header 2"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//
//            c1 = new PdfPCell(new Phrase("Table Header 3"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//            table.setHeaderRows(1);
//
//            table.addCell("1.0");
//            table.addCell("1.1");
//            table.addCell("1.2");
//            table.addCell("2.1");
//            table.addCell("2.2");
//            table.addCell("2.3");
//            Paragraph p = new Paragraph(text);
//            p.add(new Paragraph(" "));
//            document.add(p);
//            document.add(table);
        } catch (DocumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        document.close();
    }

    public void createPdfStatistika(List<StavkaDnevneBerbe> stavke, Date pocetak, Date kraj) {
        Document document = new Document();
        try {
            document.setPageSize(PageSize.A4.rotate());
            PdfWriter.getInstance(document,
                    new FileOutputStream(System.getProperty("user.home") + "/Desktop/Statistike/" + pocetak + " " + kraj + ".pdf"));
            document.open();
//                        String text = "";
//                        for (int i = 0; i < 10000; i++) {
//                                text += "test";
//              ,          }
//            String jmbg = stavke.get(0).getDobavljac().getJmbg();

            PdfPTable table = new PdfPTable(new float[]{300f, 150f, 150f, 150f, 150f, 150f, 150f, 150f, 150f, 150f, 150f, 150f, 150f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);
            Paragraph p = new Paragraph("Od: " + pocetak + " do: " + kraj);
            p.add(new Paragraph(" "));
            document.add(p);
            PdfPCell c = new PdfPCell(new Phrase(""));
//            PdfPCell c1 = new PdfPCell(new Phrase("Sifra"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setRowspan(2);
//            c1.setColspan(2);
//            table.addCell(c1);
            PdfPCell c1 = new PdfPCell(new Phrase("Dobavljac"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            c1.setRowspan(2);
            c1.setColspan(2);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Tacne"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("I Klasa"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("II Klasa"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("III Klasa"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("III Klasa"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setColspan(2);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Braon Tacne"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setColspan(2);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Braon Rimfuz"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setColspan(2);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Bukovaca"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setColspan(2);
//            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Svega"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(3);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Iznos"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Kol"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Iznos"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Kol"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Iznos"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase("Kol"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Cena"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL);

//                table.setHeaderRows(3);
            List<String> jmbgovi = new ArrayList<>();
            Map<Double, Double> kt = new HashMap<>();
            Map<Double, Double> k1 = new HashMap<>();
            Map<Double, Double> k2 = new HashMap<>();
            Map<Double, Double> k3 = new HashMap<>();
//            Map<Double, Double> kbt = new HashMap<>();
//            Map<Double, Double> kbr = new HashMap<>();
//            Map<Double, Double> kb = new HashMap<>();

            Map<Double, Double> ukupnoTacneVrednost = new HashMap<>();
            Map<Double, Double> ukupnoPrvaKlasaVrednost = new HashMap<>();
            Map<Double, Double> ukupnoDrugaKlasaVrednost = new HashMap<>();
            Map<Double, Double> ukupnoTrecaKlasaVrednost = new HashMap<>();
//            Map<Double, Double> ukupnoBraonTacneVrednost = new HashMap<>();
//            Map<Double, Double> ukupnoBraonRimfuzVrednost = new HashMap<>();
//            Map<Double, Double> ukupnoBukovacaVrednost = new HashMap<>();
            for (StavkaDnevneBerbe stavkaDnevneBerbe : stavke) {
                if (!jmbgovi.contains(stavkaDnevneBerbe.getDobavljac().getJmbg())) {
                    jmbgovi.add(stavkaDnevneBerbe.getDobavljac().getJmbg());
                }
            }
            Double ukupnoKolicinaTacne = 0.0;
            Double ukupnoKolicinaPrvaKlasa = 0.0;
            Double ukupnoKolicinaDrugaKlasa = 0.0;
            Double ukupnoKolicinaTrecaKlasa = 0.0;
//            Double ukupnoKolicinaBraonTacne = 0.0;
//            Double ukupnoKolicinaBraonRimfuz = 0.0;
//            Double ukupnoKolicinaBukovaca = 0.0;
            Double ukupnoCenaTacne = 0.0;
            Double ukupnoCenaPrvaKlasa = 0.0;
            Double ukupnoCenaDrugaKlasa = 0.0;
            Double ukupnoCenaTrecaKlasa = 0.0;
//            Double ukupnoCenaBraonTacne = 0.0;
//            Double ukupnoCenaBraonRimfuz = 0.0;
//            Double ukupnoCenaBukovaca = 0.0;
            Double ukupanIznosSvih = 0.0;
            for (String j : jmbgovi) {
                String imeDobavljaca = null;
                for (StavkaDnevneBerbe stavkaDnevneBerbe : stavke) {
                    if (stavkaDnevneBerbe.getDobavljac().getJmbg().equals(j)) {
                        if (imeDobavljaca == null) {
                            imeDobavljaca = stavkaDnevneBerbe.getDobavljac().getIme() + " " + stavkaDnevneBerbe.getDobavljac().getPrezime();

                        }
                        if (!kt.containsKey(stavkaDnevneBerbe.getCenaTacne()) && stavkaDnevneBerbe.getTacne() != 0) {
                            kt.put(stavkaDnevneBerbe.getCenaTacne(), new Double(0));
                        }
                        if (!k1.containsKey(stavkaDnevneBerbe.getCenaPrvaKlasa()) && stavkaDnevneBerbe.getCenaPrvaKlasa() != 0) {
                            k1.put(stavkaDnevneBerbe.getCenaPrvaKlasa(), new Double(0));
                        }
                        if (!k2.containsKey(stavkaDnevneBerbe.getCenaDrugaKlasa()) && stavkaDnevneBerbe.getCenaDrugaKlasa() != 0) {
                            k2.put(stavkaDnevneBerbe.getCenaDrugaKlasa(), new Double(0));
                        }
                        if (!k3.containsKey(stavkaDnevneBerbe.getCenaTrecaKlasa()) && stavkaDnevneBerbe.getCenaTrecaKlasa() != 0) {
                            k3.put(stavkaDnevneBerbe.getCenaTrecaKlasa(), new Double(0));
                        }
//                        if (!kbt.containsKey(stavkaDnevneBerbe.getCenaBraonTacne()) && stavkaDnevneBerbe.getCenaBraonTacne()!= 0) {
//                            kbt.put(stavkaDnevneBerbe.getCenaBraonTacne(), new Double(0));
//                        }
//                        if (!kbr.containsKey(stavkaDnevneBerbe.getCenaBraonRimfuz()) && stavkaDnevneBerbe.getCenaBraonRimfuz()!= 0) {
//                            kbr.put(stavkaDnevneBerbe.getCenaBraonRimfuz(), new Double(0));
//                        }
//                        if (!kb.containsKey(stavkaDnevneBerbe.getCenaBukovaca()) && stavkaDnevneBerbe.getCenaBukovaca()!= 0) {
//                            kb.put(stavkaDnevneBerbe.getCenaBukovaca(), new Double(0));
//                        }
                    }
                }
                for (StavkaDnevneBerbe s : stavke) {
                    if (s.getDobavljac().getJmbg().equals(j)) {
                        if (kt.containsKey(s.getCenaTacne())) {
                            kt.put(s.getCenaTacne(), kt.get(s.getCenaTacne()) + s.getTacne());
                        }
                        if (k1.containsKey(s.getCenaPrvaKlasa())) {
                            k1.put(s.getCenaPrvaKlasa(), k1.get(s.getCenaPrvaKlasa()) + s.getPrvaKlasa());
                        }
                        if (k2.containsKey(s.getCenaDrugaKlasa())) {
                            k2.put(s.getCenaDrugaKlasa(), k2.get(s.getCenaDrugaKlasa()) + s.getDrugaKlasa());
                        }
                        if (k3.containsKey(s.getCenaTrecaKlasa())) {
                            k3.put(s.getCenaTrecaKlasa(), k3.get(s.getCenaTrecaKlasa()) + s.getTrecaKlasa());
                        }
//                        if (kbt.containsKey(s.getCenaBraonTacne())) {
//                            kbt.put(s.getCenaBraonTacne(), kbt.get(s.getCenaBraonTacne()) + s.getBraonTacne());
//                        }
//                        if (kbr.containsKey(s.getCenaBraonRimfuz())) {
//                            kbr.put(s.getCenaBraonRimfuz(), kbr.get(s.getCenaBraonRimfuz()) + s.getBraonRimfuz());
//                        }
//                        if (kb.containsKey(s.getCenaBukovaca())) {
//                            kb.put(s.getCenaBukovaca(), kb.get(s.getCenaBukovaca()) + s.getBukovaca());
//                        }
                    }
                }
                c1 = new PdfPCell(new Phrase(imeDobavljaca));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setColspan(2);
                table.addCell(c1);
                Iterator it = kt.entrySet().iterator();
                Double ukupnoKolicinaT = 0.0;
                Double ukupnoCenaT = 0.0;
                Double ukupanIznos = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    ukupnoKolicinaT += (Double) pair.getValue();
                    ukupnoCenaT += (Double) pair.getKey();
                    ukupanIznos += ((Double) pair.getValue() * (Double) pair.getKey());
                    //ukupno vrednost
                    if (ukupnoTacneVrednost.containsKey((Double) pair.getKey())) {
                        ukupnoTacneVrednost.put((Double) pair.getKey(), ukupnoTacneVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
                    } else {
                        ukupnoTacneVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
                    }
                }
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaT, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupanIznos, 2) + "", font));
                table.addCell(c1);

                it = k1.entrySet().iterator();
                Double ukupnoKolicinaP = 0.0;
                Double ukupnoCenaP = 0.0;
                Double ukupanIznosP = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    ukupnoKolicinaP += (Double) pair.getValue();
                    ukupnoCenaP += (Double) pair.getKey();
                    ukupanIznosP += ((Double) pair.getValue() * (Double) pair.getKey());
                    if (ukupnoPrvaKlasaVrednost.containsKey((Double) pair.getKey())) {
                        ukupnoPrvaKlasaVrednost.put((Double) pair.getKey(), ukupnoPrvaKlasaVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
                    } else {
                        ukupnoPrvaKlasaVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
                    }
                }
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaP, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupanIznosP, 2) + "", font));
                table.addCell(c1);

                it = k2.entrySet().iterator();
                Double ukupnoKolicinaD = 0.0;
                Double ukupnoCenaD = 0.0;
                Double ukupanIznosD = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    ukupnoKolicinaD += (Double) pair.getValue();
                    ukupnoCenaD += (Double) pair.getKey();
                    ukupanIznosD += ((Double) pair.getValue() * (Double) pair.getKey());
                    if (ukupnoDrugaKlasaVrednost.containsKey((Double) pair.getKey())) {
                        ukupnoDrugaKlasaVrednost.put((Double) pair.getKey(), ukupnoDrugaKlasaVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
                    } else {
                        ukupnoDrugaKlasaVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
                    }
                }
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaD, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupanIznosD, 2) + "", font));
                table.addCell(c1);

                it = k3.entrySet().iterator();
                Double ukupnoKolicinaTr = 0.0;
                Double ukupnoCenaTr = 0.0;
                Double ukupanIznosT = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    ukupnoKolicinaTr += (Double) pair.getValue();
                    ukupnoCenaTr += (Double) pair.getKey();
                    ukupanIznosT += ((Double) pair.getValue() * (Double) pair.getKey());
                    if (ukupnoTrecaKlasaVrednost.containsKey((Double) pair.getKey())) {
                        ukupnoTrecaKlasaVrednost.put((Double) pair.getKey(), ukupnoTrecaKlasaVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
                    } else {
                        ukupnoTrecaKlasaVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
                    }
                }
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaTr, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupanIznosT, 2) + "", font));
                table.addCell(c1);
//                
//                it = kbt.entrySet().iterator();
//                Double ukupnoKolicinaBt = 0.0;
//                Double ukupnoCenaBt = 0.0;
//                Double ukupanIznosBt = 0.0;
//                while (it.hasNext()) {
//                    Map.Entry pair = (Map.Entry) it.next();
//                    ukupnoKolicinaBt += (Double) pair.getValue();
//                    ukupnoCenaBt += (Double) pair.getKey();
//                    ukupanIznosBt += ((Double) pair.getValue() * (Double) pair.getKey());
//                    if (ukupnoBraonTacneVrednost.containsKey((Double) pair.getKey())) {
//                        ukupnoBraonTacneVrednost.put((Double) pair.getKey(), ukupnoBraonTacneVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
//                    } else {
//                        ukupnoBraonTacneVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
//                    }
//                }
//                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaBt, 2) + "", font));
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase(round(ukupanIznosBt, 2) + "", font));
//                table.addCell(c1);
//                
//                it = kbr.entrySet().iterator();
//                Double ukupnoKolicinaBr = 0.0;
//                Double ukupnoCenaBr = 0.0;
//                Double ukupanIznosBr = 0.0;
//                while (it.hasNext()) {
//                    Map.Entry pair = (Map.Entry) it.next();
//                    ukupnoKolicinaBr += (Double) pair.getValue();
//                    ukupnoCenaBr += (Double) pair.getKey();
//                    ukupanIznosBr += ((Double) pair.getValue() * (Double) pair.getKey());
//                    if (ukupnoBraonRimfuzVrednost.containsKey((Double) pair.getKey())) {
//                        ukupnoBraonRimfuzVrednost.put((Double) pair.getKey(), ukupnoBraonRimfuzVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
//                    } else {
//                        ukupnoBraonRimfuzVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
//                    }
//                }
//                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaBr, 2) + "", font));
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase(round(ukupanIznosBr, 2) + "", font));
//                table.addCell(c1);
//                
//                it = kb.entrySet().iterator();
//                Double ukupnoKolicinaB = 0.0;
//                Double ukupnoCenaB = 0.0;
//                Double ukupanIznosB = 0.0;
//                while (it.hasNext()) {
//                    Map.Entry pair = (Map.Entry) it.next();
//                    ukupnoKolicinaB += (Double) pair.getValue();
//                    ukupnoCenaB += (Double) pair.getKey();
//                    ukupanIznosB += ((Double) pair.getValue() * (Double) pair.getKey());
//                    if (ukupnoBukovacaVrednost.containsKey((Double) pair.getKey())) {
//                        ukupnoBukovacaVrednost.put((Double) pair.getKey(), ukupnoBukovacaVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
//                    } else {
//                        ukupnoBukovacaVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
//                    }
//                }
//                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaB, 2) + "", font));
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase(round(ukupanIznosB, 2) + "", font));
//                table.addCell(c1);
                ////////////
                
                Double ukupnoKolicina = ukupnoKolicinaT + ukupnoKolicinaP + ukupnoKolicinaD + ukupnoKolicinaTr;
//                        + ukupnoKolicinaBt + ukupnoKolicinaBr + ukupnoKolicinaB;
                Double ukupnoVrednost = ukupanIznos + ukupanIznosP + ukupanIznosD + ukupanIznosT;
//                        + ukupanIznosBt + ukupanIznosBr + ukupanIznosB;
                Double ukupnoCena = ukupnoVrednost / ukupnoKolicina;
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicina, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupnoCena, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupnoVrednost, 2) + "", font));
                table.addCell(c1);

                ukupanIznosSvih += ukupnoVrednost;

                ukupnoKolicinaTacne += ukupnoKolicinaT;
                ukupnoKolicinaPrvaKlasa += ukupnoKolicinaP;
                ukupnoKolicinaDrugaKlasa += ukupnoKolicinaD;
                ukupnoKolicinaTrecaKlasa += ukupnoKolicinaTr;
//                ukupnoKolicinaBraonTacne += ukupnoKolicinaBt;
//                ukupnoKolicinaBraonRimfuz += ukupnoKolicinaBr;
//                ukupnoKolicinaBukovaca += ukupnoKolicinaB;

                ukupnoCenaTacne += ukupanIznos;
                ukupnoCenaPrvaKlasa += ukupanIznosP;
                ukupnoCenaDrugaKlasa += ukupanIznosD;
                ukupnoCenaTrecaKlasa += ukupanIznosT;
//                ukupnoCenaBraonTacne += ukupanIznosBt;
//                ukupnoCenaBraonRimfuz += ukupanIznosBr;
//                ukupnoCenaBukovaca += ukupanIznosB;

                kt.clear();
                k1.clear();
                k2.clear();
                k3.clear();
//                kbt.clear();
//                kbr.clear();
//                kb.clear();
            }
            c1 = new PdfPCell(new Phrase("UKUPNO"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoKolicinaTacne + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoCenaTacne + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoKolicinaPrvaKlasa + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoCenaPrvaKlasa + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoKolicinaDrugaKlasa + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoCenaDrugaKlasa + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoKolicinaTrecaKlasa + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoCenaTrecaKlasa + "", font));
            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase(ukupnoKolicinaBraonTacne + "", font));
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase(ukupnoCenaBraonTacne + "", font));
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase(ukupnoKolicinaBraonRimfuz + "", font));
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase(ukupnoCenaBraonRimfuz + "", font));
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase(ukupnoKolicinaBukovaca + "", font));
//            table.addCell(c1);
//            c1 = new PdfPCell(new Phrase(ukupnoCenaBukovaca + "", font));
//            table.addCell(c1);

            Double ukupnoKolicinaSve = ukupnoKolicinaTacne + ukupnoKolicinaPrvaKlasa + ukupnoKolicinaDrugaKlasa + ukupnoKolicinaTrecaKlasa;
//                    + ukupnoKolicinaBraonTacne + ukupnoKolicinaBraonRimfuz + ukupnoKolicinaBukovaca;
            Double prosecnaCena = ukupanIznosSvih / ukupnoKolicinaSve;

            c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaSve, 2) + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(round(prosecnaCena, 2) + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(round(ukupanIznosSvih, 2) + "", font));
            table.addCell(c1);

            //PDV
            c1 = new PdfPCell(new Phrase("PDV"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            table.addCell(c);

            Iterator it = ukupnoTacneVrednost.entrySet().iterator();
            Double ukupnoTacnePdv = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if ((Double) pair.getKey() == 110) {
                    ukupnoTacnePdv += (((Double) pair.getValue() / (Double) pair.getKey()) * 106);
                } else {
                    ukupnoTacnePdv += (Double) pair.getValue();
                }
            }
            c1 = new PdfPCell(new Phrase(round(ukupnoTacnePdv * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);
            table.addCell(c);

            it = ukupnoPrvaKlasaVrednost.entrySet().iterator();
            Double ukupnoPrvaKlasaPdv = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                ukupnoPrvaKlasaPdv += (Double) pair.getValue();
            }
            c1 = new PdfPCell(new Phrase(round(ukupnoPrvaKlasaPdv * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);
            table.addCell(c);

            it = ukupnoDrugaKlasaVrednost.entrySet().iterator();
            Double ukupnoDrugaKlasaPdv = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                ukupnoDrugaKlasaPdv += (Double) pair.getValue();
            }
            c1 = new PdfPCell(new Phrase(round(ukupnoDrugaKlasaPdv * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);
            table.addCell(c);

            it = ukupnoTrecaKlasaVrednost.entrySet().iterator();
            Double ukupnoTrecaKlasaPdv = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                ukupnoTrecaKlasaPdv += (Double) pair.getValue();
            }
            c1 = new PdfPCell(new Phrase(round(ukupnoTrecaKlasaPdv * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);
            c = new PdfPCell(new Phrase(""));
            c.setColspan(2);
            table.addCell(c);
            
//            it = ukupnoBraonTacneVrednost.entrySet().iterator();
//            Double ukupnoBraonTacnePdv = 0.0;
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry) it.next();
//                ukupnoBraonTacnePdv += (Double) pair.getValue();
//            }
//            c1 = new PdfPCell(new Phrase(round(ukupnoBraonTacnePdv * (110.0f / 100.0f), 2) + "", font));
//            table.addCell(c1);
//            c = new PdfPCell(new Phrase(""));
//            c.setColspan(2);
//            table.addCell(c);
//            
//            it = ukupnoBraonRimfuzVrednost.entrySet().iterator();
//            Double ukupnoBraonRimfuzPdv = 0.0;
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry) it.next();
//                ukupnoBraonRimfuzPdv += (Double) pair.getValue();
//            }
//            c1 = new PdfPCell(new Phrase(round(ukupnoBraonRimfuzPdv * (110.0f / 100.0f), 2) + "", font));
//            table.addCell(c1);
//            c = new PdfPCell(new Phrase(""));
//            c.setColspan(2);
//            table.addCell(c);
//            
//            it = ukupnoBukovacaVrednost.entrySet().iterator();
//            Double ukupnoBukovacaPdv = 0.0;
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry) it.next();
//                ukupnoBukovacaPdv += (Double) pair.getValue();
//            }
//            c1 = new PdfPCell(new Phrase(round(ukupnoBukovacaPdv * (110.0f / 100.0f), 2) + "", font));
//            table.addCell(c1);
//            c = new PdfPCell(new Phrase(""));
//            c.setColspan(2);
//            table.addCell(c);

            Double ukupnoSvePDV = ukupnoTacnePdv + ukupnoPrvaKlasaPdv + ukupnoDrugaKlasaPdv + ukupnoTrecaKlasaPdv;
//                    + ukupnoBraonTacnePdv + ukupnoBraonRimfuzPdv + ukupnoBukovacaPdv;
            c1 = new PdfPCell(new Phrase(round(ukupnoSvePDV * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);

            c = new PdfPCell(new Phrase(""));
            c.setColspan(10);
            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);

            c1 = new PdfPCell(new Phrase("DOBITAK"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(round((ukupnoSvePDV * (110.0f / 100.0f)) - ukupanIznosSvih, 2) + "", font));
            table.addCell(c1);

            document.add(table);
            document.add(new Paragraph(" "));
        } catch (DocumentException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
    }

    public void createPdfStatistikaBraonTacne(List<StavkaDnevneBerbe> stavke, Date pocetak, Date kraj) {
        Document document = new Document();
        try {
            document.setPageSize(PageSize.A4.rotate());
            PdfWriter.getInstance(document,
                    new FileOutputStream(System.getProperty("user.home") + "/Desktop/StatistikeBraoTacne/" + pocetak + " " + kraj + ".pdf"));
            document.open();
//                        String text = "";
//                        for (int i = 0; i < 10000; i++) {
//                                text += "test";
//              ,          }
//            String jmbg = stavke.get(0).getDobavljac().getJmbg();

            PdfPTable table = new PdfPTable(new float[]{300f, 150f, 150f, 150f, 150f, 150f, 150f, 150f, 150f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);
            Paragraph p = new Paragraph("Od: " + pocetak + " do: " + kraj);
            p.add(new Paragraph(" "));
            document.add(p);
            PdfPCell c = new PdfPCell(new Phrase(""));
//            PdfPCell c1 = new PdfPCell(new Phrase("Sifra"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setRowspan(2);
//            c1.setColspan(2);
//            table.addCell(c1);
            PdfPCell c1 = new PdfPCell(new Phrase("Dobavljac"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            c1.setRowspan(2);
            c1.setColspan(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Braon Tacne"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Braon Rimfuz"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Svega"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(3);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Kol"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Cena"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Iznos"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL);

//                table.setHeaderRows(3);
            List<String> jmbgovi = new ArrayList<>();
            Map<Double, Double> kbt = new HashMap<>();
            Map<Double, Double> kbr = new HashMap<>();

            Map<Double, Double> ukupnoBraonTacneVrednost = new HashMap<>();
            Map<Double, Double> ukupnoBraonRimfuzVrednost = new HashMap<>();
            for (StavkaDnevneBerbe stavkaDnevneBerbe : stavke) {
                if (!jmbgovi.contains(stavkaDnevneBerbe.getDobavljac().getJmbg())) {
                    jmbgovi.add(stavkaDnevneBerbe.getDobavljac().getJmbg());
                }
            }
            Double ukupnoKolicinaBraonTacne = 0.0;
            Double ukupnoKolicinaBraonRimfuz = 0.0;
            Double ukupnoCenaBraonTacne = 0.0;
            Double ukupnoCenaBraonRimfuz = 0.0;
            Double ukupanIznosSvih = 0.0;
            for (String j : jmbgovi) {
                String imeDobavljaca = null;
                for (StavkaDnevneBerbe stavkaDnevneBerbe : stavke) {
                    if (stavkaDnevneBerbe.getDobavljac().getJmbg().equals(j)) {
                        if (imeDobavljaca == null) {
                            imeDobavljaca = stavkaDnevneBerbe.getDobavljac().getIme() + " " + stavkaDnevneBerbe.getDobavljac().getPrezime();

                        }
                        if (!kbt.containsKey(stavkaDnevneBerbe.getCenaBraonTacne()) && stavkaDnevneBerbe.getCenaBraonTacne()!= 0) {
                            kbt.put(stavkaDnevneBerbe.getCenaBraonTacne(), new Double(0));
                        }
                        if (!kbr.containsKey(stavkaDnevneBerbe.getCenaBraonRimfuz()) && stavkaDnevneBerbe.getCenaBraonRimfuz()!= 0) {
                            kbr.put(stavkaDnevneBerbe.getCenaBraonRimfuz(), new Double(0));
                        }
                    }
                }
                for (StavkaDnevneBerbe s : stavke) {
                    if (s.getDobavljac().getJmbg().equals(j)) {
                        if (kbt.containsKey(s.getCenaBraonTacne())) {
                            kbt.put(s.getCenaBraonTacne(), kbt.get(s.getCenaBraonTacne()) + s.getBraonTacne());
                        }
                        if (kbr.containsKey(s.getCenaBraonRimfuz())) {
                            kbr.put(s.getCenaBraonRimfuz(), kbr.get(s.getCenaBraonRimfuz()) + s.getBraonRimfuz());
                        }
                    }
                }
                c1 = new PdfPCell(new Phrase(imeDobavljaca));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setColspan(2);
                table.addCell(c1);
                Iterator it = kbt.entrySet().iterator();
                Double ukupnoKolicinaBt = 0.0;
                Double ukupnoCenaBt = 0.0;
                Double ukupanIznosBt = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    ukupnoKolicinaBt += (Double) pair.getValue();
                    ukupnoCenaBt += (Double) pair.getKey();
                    ukupanIznosBt += ((Double) pair.getValue() * (Double) pair.getKey());
                    if (ukupnoBraonTacneVrednost.containsKey((Double) pair.getKey())) {
                        ukupnoBraonTacneVrednost.put((Double) pair.getKey(), ukupnoBraonTacneVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
                    } else {
                        ukupnoBraonTacneVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
                    }
                }
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaBt, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupanIznosBt, 2) + "", font));
                table.addCell(c1);

                it = kbr.entrySet().iterator();
                Double ukupnoKolicinaBr = 0.0;
                Double ukupnoCenaBr = 0.0;
                Double ukupanIznosBr = 0.0;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    ukupnoKolicinaBr += (Double) pair.getValue();
                    ukupnoCenaBr += (Double) pair.getKey();
                    ukupanIznosBr += ((Double) pair.getValue() * (Double) pair.getKey());
                    if (ukupnoBraonRimfuzVrednost.containsKey((Double) pair.getKey())) {
                        ukupnoBraonRimfuzVrednost.put((Double) pair.getKey(), ukupnoBraonRimfuzVrednost.get((Double) pair.getKey()) + ((Double) pair.getKey() * (Double) pair.getValue()));
                    } else {
                        ukupnoBraonRimfuzVrednost.put((Double) pair.getKey(), (Double) pair.getKey() * (Double) pair.getValue());
                    }
                }
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaBr, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupanIznosBr, 2) + "", font));
                table.addCell(c1);

                Double ukupnoKolicina = ukupnoKolicinaBt + ukupnoKolicinaBr;
                Double ukupnoVrednost = ukupanIznosBt + ukupanIznosBr;
                Double ukupnoCena = ukupnoVrednost / ukupnoKolicina;
                c1 = new PdfPCell(new Phrase(round(ukupnoKolicina, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupnoCena, 2) + "", font));
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(round(ukupnoVrednost, 2) + "", font));
                table.addCell(c1);

                ukupanIznosSvih += ukupnoVrednost;

                ukupnoKolicinaBraonTacne += ukupnoKolicinaBt;
                ukupnoKolicinaBraonRimfuz += ukupnoKolicinaBr;

                ukupnoCenaBraonTacne += ukupanIznosBt;
                ukupnoCenaBraonRimfuz += ukupanIznosBr;

                kbt.clear();
                kbr.clear();
            }
            c1 = new PdfPCell(new Phrase("UKUPNO"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoKolicinaBraonTacne + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoCenaBraonTacne + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoKolicinaBraonRimfuz + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(ukupnoCenaBraonRimfuz + "", font));
            table.addCell(c1);

            Double ukupnoKolicinaSve = ukupnoKolicinaBraonTacne + ukupnoKolicinaBraonRimfuz;
            Double prosecnaCena = ukupanIznosSvih / ukupnoKolicinaSve;

            c1 = new PdfPCell(new Phrase(round(ukupnoKolicinaSve, 2) + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(round(prosecnaCena, 2) + "", font));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase(round(ukupanIznosSvih, 2) + "", font));
            table.addCell(c1);

            //PDV
            c1 = new PdfPCell(new Phrase("PDV"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);
            table.addCell(c);

            Iterator it = ukupnoBraonTacneVrednost.entrySet().iterator();
            Double ukupnoBraonTacnePdv = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                ukupnoBraonTacnePdv += (Double) pair.getValue();
            }
            c1 = new PdfPCell(new Phrase(round(ukupnoBraonTacnePdv * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);
            c = new PdfPCell(new Phrase(""));
            table.addCell(c);

            it = ukupnoBraonRimfuzVrednost.entrySet().iterator();
            Double ukupnoBraonRimfuzPdv = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                ukupnoBraonRimfuzPdv += (Double) pair.getValue();
            }
            c1 = new PdfPCell(new Phrase(round(ukupnoBraonRimfuzPdv * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);
            c = new PdfPCell(new Phrase(""));
            c.setColspan(2);
            table.addCell(c);

            Double ukupnoSvePDV = ukupnoBraonTacnePdv + ukupnoBraonRimfuzPdv;
            c1 = new PdfPCell(new Phrase(round(ukupnoSvePDV * (110.0f / 100.0f), 2) + "", font));
            table.addCell(c1);

            c = new PdfPCell(new Phrase(""));
            c.setColspan(6);
            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);
//            table.addCell(c);

            c1 = new PdfPCell(new Phrase("DOBITAK"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(round((ukupnoSvePDV * (110.0f / 100.0f)) - ukupanIznosSvih, 2) + "", font));
            table.addCell(c1);

            document.add(table);
            document.add(new Paragraph(" "));
        } catch (DocumentException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
