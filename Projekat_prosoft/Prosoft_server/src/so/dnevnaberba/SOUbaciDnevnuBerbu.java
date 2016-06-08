/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dnevnaberba;

import domen.DnevnaBerba;
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
public class SOUbaciDnevnuBerbu extends AbstractSO{

    IDomenskiObjekat dnevnaBerba;

    public SOUbaciDnevnuBerbu(IDomenskiObjekat dnevnaBerba) {
        this.dnevnaBerba = dnevnaBerba;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        DnevnaBerba db = (DnevnaBerba) dnevnaBerba;
        if (db.getDobavljac() == null || db.getDatum() == null){
            throw new ValidationException("Neki od atributa dnevne berbe su null");
        }
    }

  

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.insert(dnevnaBerba);
        } catch (SQLException ex) {
            Logger.getLogger(SOUbaciDnevnuBerbu.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Uneli ste dnevnu berbu sa istim id-om!");
        }
    }
    
}
