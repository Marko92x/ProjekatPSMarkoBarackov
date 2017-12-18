/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeltabele;

import domen.StavkaDnevneBerbe;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class TableModelStavkaDnevneBerbe extends AbstractTableModel{

    private List<StavkaDnevneBerbe> stavke = new ArrayList<>();

    public TableModelStavkaDnevneBerbe(List<StavkaDnevneBerbe> stavke) {
        this.stavke = stavke;
    }
    
    String[] nazivKolona = new String[]{"Tacne", "Prva klasa", "Druga klasa", "Treca klasa", "Braon tacne", "Braon rimfuz", "Bukovaca"};
    
    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaDnevneBerbe stavka = stavke.get(rowIndex);
        switch(columnIndex){
            case 0:
                return stavka.getTacne();
            case 1:
                return stavka.getPrvaKlasa();
            case 2:
                return stavka.getDrugaKlasa();
            case 3:
                return stavka.getTrecaKlasa();
            case 4:
                return stavka.getBraonTacne();
            case 5:
                return stavka.getBraonRimfuz();
            case 6:
                return stavka.getBukovaca();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }

    public List<StavkaDnevneBerbe> getStavke() {
        return stavke;
    }
    
    
}
