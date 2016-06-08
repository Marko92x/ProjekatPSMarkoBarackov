/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dnevnaberba;

import domen.IDomenskiObjekat;

import exception.SistemOperationException;
import exception.ValidationException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import so.AbstractSO;

/**
 *
 * @author Marko
 */
public class SOObrisiDnevnuBerbu extends AbstractSO{
    IDomenskiObjekat dnevnaBerba;

    public SOObrisiDnevnuBerbu(IDomenskiObjekat dnevnaBerba) {
        this.dnevnaBerba = dnevnaBerba;
    }
    
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        
    }

    

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.delete(dnevnaBerba);
        } catch (SQLException ex) {
            Logger.getLogger(SOObrisiDnevnuBerbu.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greska pri brisanju dnevne berbe iz baze !");
        }
    }
    
}
