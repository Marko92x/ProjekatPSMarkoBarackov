/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.File;
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
public class AdministrativniRadnik implements IDomenskiObjekat{
    
    private int administrativniRadnikID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String korisnickaSifra;
    
    

    public int getAdministrativniRadnikID() {
        return administrativniRadnikID;
    }

    public void setAdministrativniRadnikID(int administrativniRadnikID) {
        this.administrativniRadnikID = administrativniRadnikID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

    @Override
    public String vratiNazivTabele() {
        return "administrativniradnik";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "(ime, prezime, korisnickoIme, korisnickaSifra) VALUES ('" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + korisnickaSifra + "')";
    }

   

    @Override
    public List<IDomenskiObjekat> vratiListuPovezanihObjekata() {
        return new ArrayList<>();
    }

    @Override
    public IDomenskiObjekat napraviObjekat(ResultSet rs) {
        
        try {
            AdministrativniRadnik ar = new AdministrativniRadnik();
            ar.setAdministrativniRadnikID(rs.getInt("administrativniradnikid"));
            ar.setIme(rs.getString("ime"));
            ar.setPrezime(rs.getString("prezime"));
            ar.setKorisnickoIme(rs.getString("korisnickoime"));
            ar.setKorisnickaSifra(rs.getString("korisnickasifra"));
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(AdministrativniRadnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String vratiID() {
        return administrativniRadnikID + "";
    }

    @Override
    public void napuniObjekat(ResultSet rs) {
        try {
            administrativniRadnikID = rs.getInt("administrativniradnikid");
            ime = rs.getString("ime");
            prezime = rs.getString("prezime");
            korisnickoIme = rs.getString("korisnickoime");
            korisnickaSifra = rs.getString("korisnickasifra");
        } catch (SQLException ex) {
            Logger.getLogger(AdministrativniRadnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiNazivKoloneID() {
        return "administrativniradnikid";
    }

    @Override
    public String vratiStringZaUpdate() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', korisnickoime = '" + korisnickoIme + "', korisnickasifra = '" + korisnickaSifra + "'";
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
