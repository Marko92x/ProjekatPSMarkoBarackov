/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marko
 */
public class Dobavljac implements IDomenskiObjekat{
    
    private String jmbg;
    private String brojGazdinstva;
    private String brojLicneKarte;
    private String ime;
    private String prezime;
    private String tekuciRacun;
    private String ulica;
    private String broj;
    private Mesto mesto;

    public Dobavljac() {
        mesto = new Mesto();
    }
    
    

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBrojGazdinstva() {
        return brojGazdinstva;
    }

    public void setBrojGazdinstva(String brojGazdinstva) {
        this.brojGazdinstva = brojGazdinstva;
    }

    public String getBrojLicneKarte() {
        return brojLicneKarte;
    }

    public void setBrojLicneKarte(String brojLicneKarte) {
        this.brojLicneKarte = brojLicneKarte;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTekuciRacun() {
        return tekuciRacun;
    }

    public void setTekuciRacun(String tekuciRacun) {
        this.tekuciRacun = tekuciRacun;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return prezime +  " " + ime;
    }

    @Override
    public String vratiNazivTabele() {
        return "dobavljac";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "(jmbg, brojgazdinstva, brojlicnekarte, ime, prezime, tekuciracun, ulica, broj, mesto) VALUES ('" + jmbg + "', '" + brojGazdinstva + "', '" + brojLicneKarte + "', '" + ime + "', '" + prezime + "', '" + tekuciRacun + "', '" + ulica + "', '" + broj + "', " + mesto.getPtt() + ")";
    }

    @Override
    public List<IDomenskiObjekat> vratiListuPovezanihObjekata() {
        List<IDomenskiObjekat> lista = new ArrayList<>();
        lista.add(mesto);
        return lista;
    }

    @Override
    public IDomenskiObjekat napraviObjekat(ResultSet rs) {
        try {
            Dobavljac d = new Dobavljac();
            d.setBroj(rs.getString("broj"));
            d.setBrojGazdinstva(rs.getString("brojgazdinstva"));
            d.setBrojLicneKarte(rs.getString("brojlicnekarte"));
            d.setIme(rs.getString("ime"));
            d.setJmbg(rs.getString("jmbg"));
            d.setPrezime(rs.getString("prezime"));
            d.setTekuciRacun(rs.getString("tekuciracun"));
            d.setUlica(rs.getString("ulica"));
            d.getMesto().setPtt(rs.getLong("mesto"));
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(Dobavljac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String vratiID() {
        return jmbg;
    }

    @Override
    public void napuniObjekat(ResultSet rs) {
        try {
            jmbg = rs.getString("jmbg");
            brojGazdinstva = rs.getString("brojgazdinstva");
            brojLicneKarte = rs.getString("brojlicnekarte");
            ime = rs.getString("ime");
            prezime = rs.getString("prezime");
            tekuciRacun = rs.getString("tekuciracun");
            ulica = rs.getString("ulica");
            broj = rs.getString("broj");
            mesto.setPtt(rs.getLong("mesto"));
        } catch (SQLException ex) {
            Logger.getLogger(Dobavljac.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String vratiNazivKoloneID() {
        return "jmbg";
    }

    @Override
    public String vratiStringZaUpdate() {
        return "jmbg = '" + jmbg + "', brojgazdinstva = '" + brojGazdinstva + "', brojlicnekarte = '" + brojLicneKarte + "', ime = '" + ime + "', prezime = '" + prezime + "', tekuciracun = '" + tekuciRacun + "', ulica = '" + ulica + "', broj = '" + broj + "', mesto = " + mesto.getPtt() ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Dobavljac other = (Dobavljac) obj;
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivKoloneOdredjene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiIDOdredjene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
