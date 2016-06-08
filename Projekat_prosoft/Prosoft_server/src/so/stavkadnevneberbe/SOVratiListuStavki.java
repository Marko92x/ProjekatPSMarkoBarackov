/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkadnevneberbe;

import domen.DnevnaBerba;
import domen.IDomenskiObjekat;
import domen.StavkaDnevneBerbe;
import exception.SistemOperationException;
import exception.ValidationException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author Mare
 */
public class SOVratiListuStavki extends AbstractSO{

    private List<IDomenskiObjekat> stavke;
    IDomenskiObjekat dnevnaBerba;

    public SOVratiListuStavki(IDomenskiObjekat dnevnaBerba) {
        this.dnevnaBerba = dnevnaBerba;
    }
    
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {

    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            stavke = db.vratiListuOdredjenihObjekata(new StavkaDnevneBerbe((DnevnaBerba) dnevnaBerba));
        } catch (SQLException ex) {
            Logger.getLogger(SOVratiListuStavki.class.getName()).log(Level.SEVERE, null, ex);
            throw new SistemOperationException("Greska pri vracanju liste stavki iz baze !");
        }
    }

    public List<IDomenskiObjekat> getStavke() {
        return stavke;
    }
    
}
