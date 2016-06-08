/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import dbb.DBBroker;

import exception.SistemOperationException;
import exception.ValidationException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Marko
 */
public abstract class AbstractSO {

    protected DBBroker db;

    public AbstractSO() {
        db = new DBBroker();
    }

    public void izvrsiOperaciju() throws Exception {
        otvoriKonekciju();
        try {
            izvrsiValidaciju();

            izvrsiTransakciju();
            potvrdiTransakciju();
        } catch (ValidationException ve) {
            throw new Exception(ve.getMessage());

        } catch (SistemOperationException soe) {
            ponistiTransakciju();
            throw new Exception(soe.getMessage());

        } finally {
            zatvoriKonekciju();
        }
    }

    private void otvoriKonekciju() {
        try {
            db.otvoriKonekciju();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void zatvoriKonekciju() {
        try {
            db.zatvoriKonekciju();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract void izvrsiValidaciju() throws ValidationException;

    protected abstract void izvrsiTransakciju() throws SistemOperationException;

    private void potvrdiTransakciju() {
        try {
            db.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ponistiTransakciju() {
        try {
            db.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
