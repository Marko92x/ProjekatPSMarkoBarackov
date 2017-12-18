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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import so.AbstractSO;

/**
 *
 * @author Marko
 */
public class SOSacuvajStavkeDnevneBerbe extends AbstractSO{
    
    List<IDomenskiObjekat> stavke;

    public SOSacuvajStavkeDnevneBerbe(List<IDomenskiObjekat> stavke) {
        this.stavke = stavke;
    }
    
    @Override
    protected void izvrsiValidaciju() throws ValidationException {
        for (int i = 0; i < stavke.size(); i++) {
            StavkaDnevneBerbe s = (StavkaDnevneBerbe) stavke.get(i);
            if (s.getDobavljac() == null || s.getDnevnaBerba() == null ||
                    s.getTacne() < 0 || s.getPrvaKlasa() < 0 || s.getDrugaKlasa() < 0 || s.getTrecaKlasa() < 0 ||
                    s.getCenaTacne() < 0 || s.getCenaPrvaKlasa() < 0 || s.getCenaDrugaKlasa() < 0 || s.getCenaTrecaKlasa() < 0 ||
                    s.getBraonTacne() < 0 || s.getBraonRimfuz() < 0 || s.getBukovaca() < 0 || s.getCenaBraonTacne() < 0 ||
                    s.getCenaBraonRimfuz() < 0 || s.getCenaBukovaca() < 0){
                throw new ValidationException("Niste ispravno uneli podatke");
            }
            if (s.getTacne() == 0 && s.getPrvaKlasa() == 0 && s.getDrugaKlasa() == 0 && s.getTrecaKlasa() == 0 &&
                    s.getCenaTacne() == 0 && s.getCenaPrvaKlasa() == 0 && s.getCenaDrugaKlasa() == 0 && s.getCenaTrecaKlasa() == 0 &&
                    s.getBraonTacne() == 0 && s.getBraonRimfuz() == 0 && s.getBukovaca() == 0 && s.getCenaBraonTacne() == 0 &&
                    s.getCenaBraonRimfuz() == 0 && s.getCenaBukovaca() == 0){
                throw new ValidationException("Morate uneti var jednu vrstu u kilogramima i bar jednu cenu. Obrisite stavku iz liste!");
            }
            if (((s.getTacne() > 0 && s.getCenaTacne() == 0) || (s.getTacne() == 0 && s.getCenaTacne() > 0)) || 
                    ((s.getPrvaKlasa()> 0 && s.getCenaPrvaKlasa()== 0) || (s.getPrvaKlasa()== 0 && s.getCenaPrvaKlasa()> 0)) || 
                    ((s.getDrugaKlasa()> 0 && s.getCenaDrugaKlasa()== 0) || (s.getDrugaKlasa()== 0 && s.getCenaDrugaKlasa()> 0)) ||
                    ((s.getTrecaKlasa()> 0 && s.getCenaTrecaKlasa()== 0) || (s.getTrecaKlasa()== 0 && s.getCenaTrecaKlasa()> 0)) ||
                    ((s.getBraonTacne()> 0 && s.getCenaBraonTacne()== 0) || (s.getBraonTacne()== 0 && s.getCenaBraonTacne()> 0)) ||
                    ((s.getBraonRimfuz()> 0 && s.getCenaBraonRimfuz()== 0) || (s.getBraonRimfuz()== 0 && s.getCenaBraonRimfuz()> 0)) ||
                    ((s.getBukovaca()> 0 && s.getCenaBukovaca()== 0) || (s.getBukovaca()== 0 && s.getCenaBukovaca()> 0))){
                throw new ValidationException("Ukoliko unesete neku vrednost u kilogramima, morate uneti i cenu za tu vrednost i obrnuto! Obrisite sttavku iz liste!");
            }
        }
    }

   

    @Override
    protected void izvrsiTransakciju() throws SistemOperationException {
        for (int i = 0; i < stavke.size(); i++) {
            try {
                db.insert(stavke.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(SOSacuvajStavkeDnevneBerbe.class.getName()).log(Level.SEVERE, null, ex);
                throw new SistemOperationException("Uneli ste stavke sa istim id-em");
            }
        }
    }
    
}
