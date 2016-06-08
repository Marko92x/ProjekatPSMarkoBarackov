/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeltabele;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domen.Zaduzenje;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class TableModelZaduzenja extends AbstractTableModel{

    private List<Zaduzenje> zaduzenja = new ArrayList<>();

    public TableModelZaduzenja(List<Zaduzenje> zaduzenja) {
//        if (zaduzenja.isEmpty()){
//            
//            this.zaduzenja = new ArrayList<>();
//        } else {
            
            this.zaduzenja = zaduzenja;
//        }
        
    }
    
    String[] nazivKolona = new String[]{"Datum zaduženja", "Datum razduženja", "Kompost", "Prezov", "Broj vreća", "Zadužio", "Razdužio"};
    
    @Override
    public int getRowCount() {
        return zaduzenja.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return zaduzenja.get(rowIndex).getDatumZaduzenja();
            case 1:
                return zaduzenja.get(rowIndex).getDatumRazduzenja();
            case 2:
                return zaduzenja.get(rowIndex).isKompost()?"DA":"NE";
            case 3:
                return zaduzenja.get(rowIndex).isPrevoz()?"DA":"NE";
            case 4:
                return zaduzenja.get(rowIndex).getBrojVreca();
            case 5:
                return zaduzenja.get(rowIndex).getZaduzio();
            case 6:
                return zaduzenja.get(rowIndex).getRazduzio().getIme()==null?"":zaduzenja.get(rowIndex).getRazduzio();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }

    public List<Zaduzenje> getZaduzenja() {
        return zaduzenja;
    }
    
    
    
}
