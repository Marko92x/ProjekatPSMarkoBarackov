/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Marko
 */
public interface IDomenskiObjekat extends Serializable{
    
    public String vratiNazivTabele();

    public String vratiVrednostiZaInsert();
    
    public List<IDomenskiObjekat> vratiListuPovezanihObjekata();

    public IDomenskiObjekat napraviObjekat(ResultSet rs);

    public String vratiID();
    
    public void napuniObjekat(ResultSet rs);

    public String vratiNazivKoloneID();

    public String vratiStringZaUpdate();
    
    public String vratiNazivKoloneOdredjene();
    
    public String vratiIDOdredjene();
}
