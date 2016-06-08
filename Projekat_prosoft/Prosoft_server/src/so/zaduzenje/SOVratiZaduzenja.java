/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaduzenje;

import domen.Dobavljac;
import domen.IDomenskiObjekat;
import domen.Zaduzenje;

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
public class SOVratiZaduzenja extends AbstractSO {

    private List<IDomenskiObjekat> zaduzenja;
    IDomenskiObjekat dobavljac;

    public SOVratiZaduzenja(IDomenskiObjekat ido) {
        dobavljac = ido;
    }

    @Override
    protected void izvrsiValidaciju() throws ValidationException {

    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            zaduzenja = db.vratiListuOdredjenihObjekata(new Zaduzenje((Dobavljac) dobavljac));
        } catch (Exception ex) {
            
            Logger.getLogger(SOVratiZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greska pri vracanju zaduzenja u bazi!");
        }
    }

    public List<IDomenskiObjekat> getZaduzenja() {
        return zaduzenja;
    }

}
