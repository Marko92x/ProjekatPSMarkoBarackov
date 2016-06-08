/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dobavljac;

import domen.Dobavljac;

import exception.SistemOperationException;
import exception.ValidationException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import kontroler.Kontroler;
import so.AbstractSO;

/**
 *
 * @author Marko
 */
public class SOKreirajNovogDobavljaca extends AbstractSO{

    Dobavljac dobavljac;
    
    public SOKreirajNovogDobavljaca(Dobavljac d) {
        this.dobavljac = d;
    }

    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        Dobavljac d = (Dobavljac) dobavljac;
        if (!d.getJmbg().matches(Kontroler.getInstance().getJmbgRegex()) || 
                !d.getBrojLicneKarte().matches(Kontroler.getInstance().getIntRegex()) || 
//                !d.getBrojGazdinstva().matches(Kontroler.getInstance().getIntRegex()) || 
//                !d.getTekuciRacun().matches(Kontroler.getInstance().getTekuciRacunRegex()) ||
                d.getUlica().equals("") ||
                d.getBroj().equals("") ||
                d.getMesto() == null ||
                d.getIme().equals("") ||
                d.getPrezime().equals("")){
            throw new ValidationException("Niste ispravno uneli podatke");
        }
    }

    

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            db.insert(dobavljac);
        } catch (SQLException ex) {
            Logger.getLogger(SOKreirajNovogDobavljaca.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Uneli ste dobavljača sa istim jmbg-om!");
        }
    }
    
}
