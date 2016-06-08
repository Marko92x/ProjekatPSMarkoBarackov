/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkadnevneberbe;

import domen.IDomenskiObjekat;
import domen.StavkaDnevneBerbe;
import exception.SistemOperationException;
import exception.ValidationException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author Mare
 */
public class SONedeljniObracun extends AbstractSO{

    List<StavkaDnevneBerbe> stavke;
    Date pocetak;
    Date kraj;
    
    public SONedeljniObracun(Date pocetak, Date kraj){
        this.pocetak = pocetak;
        this.kraj = kraj;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        try {
            stavke = db.vratiListuSvihObjekata(pocetak, kraj);
        } catch (Exception ex) {
            Logger.getLogger(SONedeljniObracun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StavkaDnevneBerbe> getStavke() {
        return stavke;
    }
    
    
    
}
