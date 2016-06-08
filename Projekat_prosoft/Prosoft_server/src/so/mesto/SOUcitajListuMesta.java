/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.mesto;

import domen.IDomenskiObjekat;
import domen.Mesto;

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
public class SOUcitajListuMesta extends AbstractSO{

    private List<IDomenskiObjekat> mesta;

    public List<IDomenskiObjekat> getMesta() {
        return mesta;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        
    }

    

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            mesta = db.vratiListuSvihObjekata(new Mesto());
        } catch (Exception ex) {
            Logger.getLogger(SOUcitajListuMesta.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greška pri vraćanju liste mesta iz baze");
        }
    }
    
}
