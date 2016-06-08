/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dobavljac;

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
public class SOObrisiDobavljaca extends AbstractSO{

    IDomenskiObjekat dobavljac;
    
    public SOObrisiDobavljaca(IDomenskiObjekat dobavljac) {
        this.dobavljac = dobavljac;
    }
    
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        
    }


    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.delete(dobavljac);
        } catch (SQLException ex) {
            Logger.getLogger(SOObrisiDobavljaca.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greška pri brisanju dobavljača iz baze");
        }
    }
    
}
