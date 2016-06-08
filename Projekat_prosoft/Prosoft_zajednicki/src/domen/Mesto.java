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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marko
 */
public class Mesto implements IDomenskiObjekat{
       
    private long ptt;
    private String naziv;
    
    

    public long getPtt() {
        return ptt;
    }

    public void setPtt(long ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return ptt + " " + naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "(ptt, naziv) VALUES (" + ptt + ", '" + naziv + "')";
    }

    

    @Override
    public List<IDomenskiObjekat> vratiListuPovezanihObjekata() {
        return new ArrayList<>();
    }

    @Override
    public IDomenskiObjekat napraviObjekat(ResultSet rs) {
        try {
            Mesto m = new Mesto();
            m.setPtt(rs.getLong("ptt"));
            m.setNaziv(rs.getString("naziv"));
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String vratiID() {
        return ptt + "";
    }

    @Override
    public void napuniObjekat(ResultSet rs) {
        try {
            ptt = rs.getLong("ptt");
            naziv = rs.getString("naziv");
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiNazivKoloneID() {
        return "ptt";
    }

    @Override
    public String vratiStringZaUpdate() {
        return "ptt = " + ptt + ", naziv = '" + naziv + "'";
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
        final Mesto other = (Mesto) obj;
        if (this.ptt != other.ptt) {
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
