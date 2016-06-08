/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkadnevneberbe;

import domen.IDomenskiObjekat;
import exception.SistemOperationException;
import exception.ValidationException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author Mare
 */
public class SOObrisiStavku extends AbstractSO{

    IDomenskiObjekat stavka;

    public SOObrisiStavku(IDomenskiObjekat stavka) {
        this.stavka = stavka;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {

    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.delete(stavka);
        } catch (SQLException ex) {
            Logger.getLogger(SOObrisiStavku.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greška pri brisanju stavke iz baze");
        }
    }
    
}
