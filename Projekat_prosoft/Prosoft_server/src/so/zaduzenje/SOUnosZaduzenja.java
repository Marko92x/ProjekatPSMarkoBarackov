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
public class SOUnosZaduzenja extends AbstractSO{

    IDomenskiObjekat zaduzenje;

    public SOUnosZaduzenja(IDomenskiObjekat zaduzenje) {
        this.zaduzenje = zaduzenje;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        Zaduzenje z = (Zaduzenje) zaduzenje;
        if (z.getDobavljac() == null || z.getZaduzio() == null || z.getDatumZaduzenja() == null || z.getBrojVreca() < 0 || (!z.isKompost() && !z.isPrevoz())){
            throw  new ValidationException("Niste ispravno uneli podatke");
        }
        if (z.isKompost() && z.getBrojVreca() < 1){
            throw  new ValidationException("Niste uneli broj vreca");
        }
    }

    

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.insert(zaduzenje);
        } catch (SQLException ex) {
            Logger.getLogger(SOUnosZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Uneli ste zaduženja sa istim id-em");
        }
    }
    
}
