/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dobavljac;

import domen.Dobavljac;
import domen.IDomenskiObjekat;

import exception.SistemOperationException;
import exception.ValidationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import so.AbstractSO;

/**
 *
 * @author Marko
 */
public class SOVratiListuDobavljaca extends AbstractSO{

    private List<IDomenskiObjekat> dobavljaci;
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        
    }

    

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            dobavljaci = db.vratiListuSvihObjekata(new Dobavljac());
        } catch (Exception ex) {
            Logger.getLogger(SOVratiListuDobavljaca.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greška pri vraćanju liste dobavljača iz baze!");
        }
    }

    public List<IDomenskiObjekat> getDobavljaci() {
        return dobavljaci;
    }
    
    
}
