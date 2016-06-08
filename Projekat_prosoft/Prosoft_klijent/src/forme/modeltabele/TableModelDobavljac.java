/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeltabele;

import domen.Dobavljac;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class TableModelDobavljac extends AbstractTableModel{
    
    private List<Dobavljac> dobavljaci = new ArrayList<>();
    
    private List<Dobavljac> visak = new ArrayList();
    
    String[] nazivKolona = new String[]{"JMBG", "Ime i prezime", "Broj lične karte", "Broj gazdinstva", "Tekuci račun", "Ulica i broj", "Mesto"};

    public TableModelDobavljac(List<Dobavljac> dobavljaci) {
        this.dobavljaci = dobavljaci;
    }
    
    @Override
    public int getRowCount() {
        return dobavljaci.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dobavljac d = dobavljaci.get(rowIndex);
        switch(columnIndex){
            case 0:
                return d.getJmbg();
            case 1:
                return d.getIme() + " " + d.getPrezime();
            case 2:
                return d.getBrojLicneKarte();
            case 3:
                return d.getBrojGazdinstva();
            case 4:
                return d.getTekuciRacun();
            case 5:
                return d.getUlica() + " " + d.getBroj();
            case 6:
                return d.getMesto();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nazivKolona[column];
    }
    
    

    public List<Dobavljac> getDobavljaci() {
        return dobavljaci;
    }
    
    public void skloniSaListe(String kriterijum) {
        
        String s = "[\\p{Z}\\p{L}[0-9]-]*" + kriterijum.toLowerCase().trim() + "[\\p{Z}\\p{L}[0-9]-]*";
        for (int i = 0; i < dobavljaci.size(); i++) {
            String imePrezime = dobavljaci.get(i).getIme().toLowerCase() + " " + dobavljaci.get(i).getPrezime().toLowerCase();
            if (!imePrezime.matches(s) && !dobavljaci.get(i).getMesto().toString().toLowerCase().matches(s)) {
                visak.add(dobavljaci.get(i));
                dobavljaci.remove(i);
                i--;
            }
        }
        fireTableDataChanged();
    }
    
    public void vratiNaListu(String kriterijum){
        if (kriterijum.length() == 0) {
            for (int i = 0; i < visak.size(); i++) {
                dobavljaci.add(visak.get(i));
                visak.remove(i);
                i--;
            }
            fireTableDataChanged();
            return;
        }
        String s = "[\\p{Z}\\p{L}[0-9]-]*" + kriterijum.toLowerCase().trim() + "[\\p{Z}\\p{L}[0-9]-]*";
        
        for (int i = 0; i < visak.size(); i++) {
            String imePrezime = visak.get(i).getIme().toLowerCase() + " " + visak.get(i).getPrezime().toLowerCase();
            if (imePrezime.matches(s) || visak.get(i).getMesto().toString().toLowerCase().matches(s)) {
                dobavljaci.add(visak.get(i));
                visak.remove(i);
                i--;
            }
        }
        fireTableDataChanged();
    }
}
