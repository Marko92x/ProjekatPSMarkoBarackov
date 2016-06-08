/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeltabele;

import domen.DnevnaBerba;
import domen.StavkaDnevneBerbe;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class TableModelDnevnaBerba extends AbstractTableModel{
    
   

    private List<DnevnaBerba> dnevneBerbe = new ArrayList<>();
    
    String[] nazivKolona = new String[]{"JMBG", "Ime i prezime", "ID dnevne berbe", "Datum"};

    public TableModelDnevnaBerba(List<DnevnaBerba> dnevneBerbe) {
        this.dnevneBerbe = dnevneBerbe;
    }

    
    
    @Override
    public int getRowCount() {
        return dnevneBerbe.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return dnevneBerbe.get(rowIndex).getDobavljac().getJmbg();
            case 1:
                return dnevneBerbe.get(rowIndex).getDobavljac().getIme() + " " + dnevneBerbe.get(rowIndex).getDobavljac().getPrezime();
            case 2:
                return dnevneBerbe.get(rowIndex).getDnevnaBerbaID();
            case 3:
                return dnevneBerbe.get(rowIndex).getDatum();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }

    public List<DnevnaBerba> getDnevneBerbe() {
        return dnevneBerbe;
    }
    
    
    
}
