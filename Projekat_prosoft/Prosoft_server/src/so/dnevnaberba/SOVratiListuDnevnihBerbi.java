/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dnevnaberba;

import domen.DnevnaBerba;
import domen.Dobavljac;
import domen.IDomenskiObjekat;

import exception.SistemOperationException;
import exception.ValidationException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import so.AbstractSO;

/**
 *
 * @author Marko
 */
public class SOVratiListuDnevnihBerbi extends AbstractSO{

    private List<IDomenskiObjekat> dnevneBerbe;
    IDomenskiObjekat dobavljac;

    public SOVratiListuDnevnihBerbi(IDomenskiObjekat dobavljac) {
        this.dobavljac = dobavljac;
    }
    
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        
    }

    

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            dnevneBerbe = db.vratiListuOdredjenihObjekata(new DnevnaBerba((Dobavljac) dobavljac));
        } catch (SQLException ex) {
            Logger.getLogger(SOVratiListuDnevnihBerbi.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greska pri vracanju liste dnevnih berbi iz baze !");
        }
    }

    public List<IDomenskiObjekat> getDnevneBerbe() {
        return dnevneBerbe;
    }
    
    
    
}
