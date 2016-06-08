/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaduzenje;

import domen.IDomenskiObjekat;
import domen.Zaduzenje;


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
public class SOIzmeniZaduzenja extends AbstractSO{

    IDomenskiObjekat zaduzenje;

    public SOIzmeniZaduzenja(IDomenskiObjekat z) {
        zaduzenje = z;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        Zaduzenje z = (Zaduzenje) zaduzenje;
        if (z.getRazduzio() == null || z.getDatumRazduzenja() == null){
            throw  new ValidationException("Greska pri razduzivanju");
        }
    }

    

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.update(zaduzenje);
        } catch (SQLException ex) {
            Logger.getLogger(SOIzmeniZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greška pri izmeni zaduženja");
        }
    }
    
}
