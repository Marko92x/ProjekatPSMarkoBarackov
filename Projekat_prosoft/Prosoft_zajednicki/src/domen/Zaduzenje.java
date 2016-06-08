/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.Date;
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
public class Zaduzenje implements IDomenskiObjekat{
    
    private Dobavljac dobavljac;
    private int zaduzenjeID;
    private Date datumZaduzenja;
    private Date datumRazduzenja;
    private boolean kompost;
    private boolean prevoz;
    private int brojVreca;
    private AdministrativniRadnik zaduzio;
    private AdministrativniRadnik razduzio;

    public Zaduzenje() {
        dobavljac = new Dobavljac();
        zaduzio = new AdministrativniRadnik();
        razduzio = new AdministrativniRadnik();
    }

    public Zaduzenje(Dobavljac dobavljac) {
        this();
        this.dobavljac = dobavljac;
    }
    
    
    
    

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public int getZaduzenjeID() {
        return zaduzenjeID;
    }

    public void setZaduzenjeID(int zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
    }

    public Date getDatumZaduzenja() {
        return datumZaduzenja;
    }

    public void setDatumZaduzenja(Date datumZaduzenja) {
        this.datumZaduzenja = datumZaduzenja;
    }

    public Date getDatumRazduzenja() {
        return datumRazduzenja;
    }

    public void setDatumRazduzenja(Date datumRazduzenja) {
        this.datumRazduzenja = datumRazduzenja;
    }

    public boolean isKompost() {
        return kompost;
    }

    public void setKompost(boolean kompost) {
        this.kompost = kompost;
    }

    public boolean isPrevoz() {
        return prevoz;
    }

    public void setPrevoz(boolean prevoz) {
        this.prevoz = prevoz;
    }

    public int getBrojVreca() {
        return brojVreca;
    }

    public void setBrojVreca(int brojVreca) {
        this.brojVreca = brojVreca;
    }

    public AdministrativniRadnik getZaduzio() {
        return zaduzio;
    }

    public void setZaduzio(AdministrativniRadnik zaduzio) {
        this.zaduzio = zaduzio;
    }

    public AdministrativniRadnik getRazduzio() {
        return razduzio;
    }

    public void setRazduzio(AdministrativniRadnik razduzio) {
        this.razduzio = razduzio;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaduzenje";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "(jmbg, datumzaduzenja, datumrazduzenja, kompost, prevoz, brojvreca, zaduzio, razduzio) VALUES ('" + dobavljac.getJmbg() + "', '" + datumZaduzenja + "', " + (datumRazduzenja==null?datumRazduzenja:"'"+datumRazduzenja+"'") + ", " + (kompost==true?"1":"0") + ", " + (prevoz==true?"1":"0") + ", " + brojVreca + ", " + zaduzio.getAdministrativniRadnikID() + ", " + (razduzio.getAdministrativniRadnikID()==0?null:razduzio.getAdministrativniRadnikID()) + ")";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuPovezanihObjekata() {
        List<IDomenskiObjekat> lista = new ArrayList<>();
        lista.add(dobavljac);
        lista.add(zaduzio);
        lista.add(razduzio);
        return lista;
    }

    @Override
    public IDomenskiObjekat napraviObjekat(ResultSet rs) {
        try {
            Zaduzenje z = new Zaduzenje();
            z.getDobavljac().setJmbg(rs.getString("jmbg"));
            z.getZaduzio().setAdministrativniRadnikID(rs.getInt("zaduzio"));
            z.getRazduzio().setAdministrativniRadnikID(rs.getInt("razduzio"));
            z.setBrojVreca(rs.getInt("brojvreca"));
            z.setDatumRazduzenja(rs.getDate("datumrazduzenja"));
            z.setDatumZaduzenja(rs.getDate("datumzaduzenja"));
            z.setKompost(rs.getBoolean("kompost"));
            z.setPrevoz(rs.getBoolean("prevoz"));
            z.setZaduzenjeID(rs.getInt("zaduzenjeid"));
            return z;
        } catch (SQLException ex) {
            Logger.getLogger(Zaduzenje.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String vratiID() {
        return zaduzenjeID + "";
    }

    @Override
    public void napuniObjekat(ResultSet rs) {
        try {
            dobavljac.setJmbg(rs.getString("jmbg"));
            zaduzio.setAdministrativniRadnikID(rs.getInt("zaduzio"));
            razduzio.setAdministrativniRadnikID(rs.getInt("razduzio"));
            datumZaduzenja = rs.getDate("datumzaduzenja");
            datumRazduzenja = rs.getDate("datumrazduzenja");
            zaduzenjeID = rs.getInt("zaduzenjeid");
            kompost = rs.getBoolean("kompost");
            prevoz = rs.getBoolean("prevoz");
            brojVreca = rs.getInt("brojvreca");
        } catch (SQLException ex) {
            Logger.getLogger(Zaduzenje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiNazivKoloneID() {
        return "zaduzenjeid";
    }

    @Override
    public String vratiStringZaUpdate() {
        return "jmbg = '" + dobavljac.getJmbg() + "', datumzaduzenja = '" + datumZaduzenja + "', datumrazduzenja = '" + datumRazduzenja + "', kompost = " + (kompost==true?"1":"0") + ", prevoz = " + (prevoz==true?"1":"0") + ", brojvreca = " + brojVreca + ", zaduzio = " + zaduzio.getAdministrativniRadnikID() + ", razduzio = " + razduzio.getAdministrativniRadnikID() ;
    }

    @Override
    public String vratiNazivKoloneOdredjene() {
        return "jmbg";
    }

    @Override
    public String vratiIDOdredjene() {
        return dobavljac.getJmbg() + "";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zaduzenje other = (Zaduzenje) obj;
        if (this.zaduzenjeID != other.zaduzenjeID) {
            return false;
        }
        return true;
    }
    
    
}
