/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.administrativniradnik;

import domen.AdministrativniRadnik;
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
public class SONadjiAdministrativnogRadnika extends AbstractSO{
    
    private List<IDomenskiObjekat> radnici;

    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        
    }

   

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            radnici = db.vratiListuSvihObjekata(new AdministrativniRadnik());
        } catch (Exception ex) {
            Logger.getLogger(SONadjiAdministrativnogRadnika.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greska pri trazenju Administratora!");
        }
    }

    public List<IDomenskiObjekat> getRadnici() {
        return radnici;
    }
    
    
    
}
