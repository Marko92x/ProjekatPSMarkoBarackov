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
public class DnevnaBerba implements IDomenskiObjekat{
    
    Dobavljac dobavljac;
    int dnevnaBerbaID;
    Date datum;

    public DnevnaBerba() {
        dobavljac = new Dobavljac();
    }
    
    public DnevnaBerba(Dobavljac d){
        this();
        dobavljac = d;
    }
    
    

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public int getDnevnaBerbaID() {
        return dnevnaBerbaID;
    }

    public void setDnevnaBerbaID(int dnevnaBerbaID) {
        this.dnevnaBerbaID = dnevnaBerbaID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String vratiNazivTabele() {
        return "dnevnaberba";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "(jmbg, datum) VALUES ('" + dobavljac.getJmbg() + "', '" + datum + "')";
    }

    

    @Override
    public List<IDomenskiObjekat> vratiListuPovezanihObjekata() {
        List<IDomenskiObjekat> lista = new ArrayList<>();
        lista.add(dobavljac);
        return lista;
    }

    @Override
    public IDomenskiObjekat napraviObjekat(ResultSet rs) {
        try {
            DnevnaBerba db = new DnevnaBerba();
            db.setDnevnaBerbaID(rs.getInt("dnevnaberbaid"));
            db.getDobavljac().setJmbg(rs.getString("jmbg"));
            db.setDatum(rs.getDate("datum"));
            return db;
        } catch (SQLException ex) {
            Logger.getLogger(DnevnaBerba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String vratiID() {
        return dnevnaBerbaID + "";
    }

    @Override
    public void napuniObjekat(ResultSet rs) {
        try {
            dobavljac.setJmbg(rs.getString("jmbg"));
            datum = rs.getDate("datum");
            dnevnaBerbaID = rs.getInt("dnevnaberbaid");
        } catch (SQLException ex) {
            Logger.getLogger(DnevnaBerba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiNazivKoloneID() {
        return "dnevnaberbaid";
    }

    @Override
    public String vratiStringZaUpdate() {
        return "jmbg = '" + dobavljac.getJmbg() + "', datum = '" + datum + "'";
    }

    @Override
    public String vratiNazivKoloneOdredjene() {
        return "jmbg";
    }

    @Override
    public String vratiIDOdredjene() {
        return dobavljac.getJmbg();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.dnevnaBerbaID;
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
        final DnevnaBerba other = (DnevnaBerba) obj;
        if (this.dnevnaBerbaID != other.dnevnaBerbaID) {
            return false;
        }
        return true;
    }
    
    
}
