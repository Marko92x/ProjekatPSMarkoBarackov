/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marko
 */
public class StavkaDnevneBerbe implements IDomenskiObjekat{
    
    private DnevnaBerba dnevnaBerba;
    private Dobavljac dobavljac;
    private int stavkaID;
    private double tacne;
    private double prvaKlasa;
    private double drugaKlasa;
    private double trecaKlasa;
    private double cenaTacne;
    private double cenaPrvaKlasa;
    private double cenaDrugaKlasa;
    private double cenaTrecaKlasa;
    private double braonTacne;
    private double braonRimfuz;
    private double bukovaca;
    private double cenaBraonTacne;
    private double cenaBraonRimfuz;
    private double cenaBukovaca;

    public StavkaDnevneBerbe() {
        dobavljac = new Dobavljac();
        dnevnaBerba = new DnevnaBerba();
    }
    
    public StavkaDnevneBerbe(DnevnaBerba d){
        dnevnaBerba = d;
    }

    public DnevnaBerba getDnevnaBerba() {
        return dnevnaBerba;
    }

    public void setDnevnaBerba(DnevnaBerba dnevnaBerba) {
        this.dnevnaBerba = dnevnaBerba;
    }

    public int getStavkaID() {
        return stavkaID;
    }

    public void setStavkaID(int stavkaID) {
        this.stavkaID = stavkaID;
    }

    public double getTacne() {
        return tacne;
    }

    public void setTacne(double tacne) {
        this.tacne = tacne;
    }

    public double getPrvaKlasa() {
        return prvaKlasa;
    }

    public void setPrvaKlasa(double prvaKlasa) {
        this.prvaKlasa = prvaKlasa;
    }

    public double getDrugaKlasa() {
        return drugaKlasa;
    }

    public void setDrugaKlasa(double drugaKlasa) {
        this.drugaKlasa = drugaKlasa;
    }

    public double getTrecaKlasa() {
        return trecaKlasa;
    }

    public void setTrecaKlasa(double trecaKlasa) {
        this.trecaKlasa = trecaKlasa;
    }

    public double getCenaTacne() {
        return cenaTacne;
    }

    public void setCenaTacne(double cenaTacne) {
        this.cenaTacne = cenaTacne;
    }

    public double getCenaPrvaKlasa() {
        return cenaPrvaKlasa;
    }

    public void setCenaPrvaKlasa(double cenaPrvaKlasa) {
        this.cenaPrvaKlasa = cenaPrvaKlasa;
    }

    public double getCenaDrugaKlasa() {
        return cenaDrugaKlasa;
    }

    public void setCenaDrugaKlasa(double cenaDrugaKlasa) {
        this.cenaDrugaKlasa = cenaDrugaKlasa;
    }

    public double getCenaTrecaKlasa() {
        return cenaTrecaKlasa;
    }

    public void setCenaTrecaKlasa(double cenaTrecaKlasa) {
        this.cenaTrecaKlasa = cenaTrecaKlasa;
    }

    public double getBraonTacne() {
        return braonTacne;
    }

    public void setBraonTacne(double braonTacne) {
        this.braonTacne = braonTacne;
    }

    public double getBraonRimfuz() {
        return braonRimfuz;
    }

    public void setBraonRimfuz(double braonRimfuz) {
        this.braonRimfuz = braonRimfuz;
    }

    public double getBukovaca() {
        return bukovaca;
    }

    public void setBukovaca(double bukovaca) {
        this.bukovaca = bukovaca;
    }

    public double getCenaBraonTacne() {
        return cenaBraonTacne;
    }

    public void setCenaBraonTacne(double cenaBraonTacne) {
        this.cenaBraonTacne = cenaBraonTacne;
    }

    public double getCenaBraonRimfuz() {
        return cenaBraonRimfuz;
    }

    public void setCenaBraonRimfuz(double cenaBraonRimfuz) {
        this.cenaBraonRimfuz = cenaBraonRimfuz;
    }

    public double getCenaBukovaca() {
        return cenaBukovaca;
    }

    public void setCenaBukovaca(double cenaBukovaca) {
        this.cenaBukovaca = cenaBukovaca;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "stavkadnevneberbe";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "(jmbg, dnevnaberbaid, tacne, prvaklasa, drugaklasa, trecaklasa, cenatacne, cenaprvaklasa, cenadrugaklasa, cenatrecaklasa, braontacne, braonrimfuz, bukovaca, cenabraontacne, cenabraonrimfuz, cenabukovaca) VALUES ('" 
                + dobavljac.getJmbg() + "', " + dnevnaBerba.getDnevnaBerbaID() 
                + ", " + tacne + ", " + prvaKlasa + ", " + drugaKlasa 
                + ", " + trecaKlasa + "," + cenaTacne + ", " + cenaPrvaKlasa 
                + ", " + cenaDrugaKlasa + ", " + cenaTrecaKlasa + ", " + braonTacne 
                + ", " + braonRimfuz + ", " + bukovaca + ", " + cenaBraonTacne + ", " 
                + cenaBraonRimfuz + ", " + cenaBukovaca + ")";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuPovezanihObjekata() {
        List<IDomenskiObjekat> lista = new ArrayList<>();
        lista.add(dobavljac);
        lista.add(dnevnaBerba);
        return lista;
    }

    @Override
    public IDomenskiObjekat napraviObjekat(ResultSet rs) {
        try {
            StavkaDnevneBerbe sdb = new StavkaDnevneBerbe();
            sdb.getDobavljac().setJmbg(rs.getString("jmbg"));
            sdb.getDnevnaBerba().setDnevnaBerbaID(rs.getInt("dnevnaberbaid"));
            sdb.setStavkaID(rs.getInt("stavkaid"));
            sdb.setTacne(rs.getDouble("tacne"));
            sdb.setPrvaKlasa(rs.getDouble("prvaklasa"));
            sdb.setDrugaKlasa(rs.getDouble("drugaklasa"));
            sdb.setTrecaKlasa(rs.getDouble("trecaklasa"));
            sdb.setCenaTacne(rs.getDouble("cenatacne"));
            sdb.setCenaPrvaKlasa(rs.getDouble("cenaprvaklasa"));
            sdb.setCenaDrugaKlasa(rs.getDouble("cenadrugaklasa"));
            sdb.setCenaTrecaKlasa(rs.getDouble("cenatrecaklasa"));
            sdb.setBraonTacne(rs.getDouble("braontacne"));
            sdb.setBraonRimfuz(rs.getDouble("braonrimfuz"));
            sdb.setBukovaca(rs.getDouble("bukovaca"));
            sdb.setCenaBraonTacne(rs.getDouble("cenabraontacne"));
            sdb.setCenaBraonRimfuz(rs.getDouble("cenabraonrimfuz"));
            sdb.setCenaBukovaca(rs.getDouble("cenabukovaca"));
            return sdb;
        } catch (SQLException ex) {
            Logger.getLogger(StavkaDnevneBerbe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String vratiID() {
        return stavkaID + "";
    }

    @Override
    public void napuniObjekat(ResultSet rs) {
        try {
            dobavljac.setJmbg(rs.getString("jmbg"));
            dnevnaBerba.setDnevnaBerbaID(rs.getInt("dnevnaberbaid"));
            stavkaID = rs.getInt("stavkaid");
            tacne = rs.getDouble("tacne");
            prvaKlasa = rs.getDouble("prvaklasa");
            drugaKlasa = rs.getDouble("drugaklasa");
            trecaKlasa = rs.getDouble("trecaklasa");
            cenaTacne = rs.getDouble("cenatacne");
            cenaPrvaKlasa = rs.getDouble("cenaprvaklasa");
            cenaDrugaKlasa = rs.getDouble("cenadrugaklasa");
            cenaTrecaKlasa = rs.getDouble("cenatrecaklasa");
            braonTacne = rs.getDouble("braontacne");
            braonRimfuz = rs.getDouble("braonrimfuz");
            bukovaca = rs.getDouble("bukovaca");
            cenaBraonTacne = rs.getDouble("cenabraontacne");
            cenaBraonRimfuz = rs.getDouble("cenabraonrimfuz");
            cenaBukovaca = rs.getDouble("cenabukovaca");
        } catch (SQLException ex) {
            Logger.getLogger(StavkaDnevneBerbe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiNazivKoloneID() {
        return "stavkaid";
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    @Override
    public String vratiStringZaUpdate() {
        return "jmbg = '" + dobavljac.getJmbg() 
                + "', dnevnaberbaid = " + dnevnaBerba.getDnevnaBerbaID() 
                + ", tacne = " + tacne 
                + ", prvaklasa = " + prvaKlasa 
                + ", drugaklasa = " + drugaKlasa 
                + ", trecaklasa = " + trecaKlasa 
                + ", cenatacne = " + cenaTacne 
                + ", cenaprvaklasa = " + cenaPrvaKlasa 
                + ", cenadrugaklasa = " + cenaDrugaKlasa 
                + ", cenatrecaklasa = " + cenaTrecaKlasa
                + ", braontacne = " + braonTacne
                + ", braonrimfuz = " + braonRimfuz
                + ", bukovaca = " + bukovaca
                + ", cenabraontacne = " + cenaBraonTacne
                + ", cenabraonrimfuz = " + cenaBraonRimfuz
                + ", cenabukovaca = " + bukovaca;
    }

    @Override
    public String vratiNazivKoloneOdredjene() {
        return "dnevnaberbaid";
    }

    @Override
    public String vratiIDOdredjene() {
        return dnevnaBerba.getDnevnaBerbaID() + "";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.stavkaID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaDnevneBerbe other = (StavkaDnevneBerbe) obj;
        if (this.stavkaID != other.stavkaID) {
            return false;
        }
        return true;
    }
    
    
    
}
