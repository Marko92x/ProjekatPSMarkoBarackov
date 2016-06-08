/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import domen.IDomenskiObjekat;
import domen.StavkaDnevneBerbe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marko
 */
public class DBBroker {

    Connection connection;

    public DBBroker() {
    }

    public void otvoriKonekciju() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/projekat_prosoft?useUnicode=true&characterEncoding=UTF-8";
        String user = "mare";
        String password = "mare";

        connection = DriverManager.getConnection(url, user, password);

        connection.setAutoCommit(false);
        System.out.println("Otvorena konekcija!");
    }

    public void zatvoriKonekciju() throws SQLException {
        connection.close();
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public List vratiListuOdredjenihObjekata(IDomenskiObjekat ido) throws SQLException {
        String upit = "Select * FROM " + ido.vratiNazivTabele() + " where " + ido.vratiNazivKoloneOdredjene() + " = " + "'" + ido.vratiIDOdredjene() + "'";
        Statement naredba = connection.createStatement();

        ResultSet rs = naredba.executeQuery(upit);
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        while (rs.next()) {
            IDomenskiObjekat ido1 = ido.napraviObjekat(rs);
            napuniObjekat(ido1);
            objekti.add(ido1);
        }

        return objekti;
    }

    public List vratiListuSvihObjekata(IDomenskiObjekat ido) throws Exception {

        String upit = "Select * FROM " + ido.vratiNazivTabele();
        Statement naredba = connection.createStatement();

        ResultSet rs = naredba.executeQuery(upit);
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        while (rs.next()) {
            IDomenskiObjekat ido1 = ido.napraviObjekat(rs);
            napuniObjekat(ido1);
            objekti.add(ido1);
        }

        return objekti;

    }

    public void napuniObjekat(IDomenskiObjekat ido) {
        if (ido.vratiListuPovezanihObjekata().size() > 0) {
            for (IDomenskiObjekat o : ido.vratiListuPovezanihObjekata()) {
                try {
                    String u = "Select * FROM " + o.vratiNazivTabele() + " where " + o.vratiNazivKoloneID() + " = " + o.vratiID();
                    Statement n = connection.createStatement();

                    ResultSet rs1 = n.executeQuery(u);
                    if (rs1.next()) {
                        o.napuniObjekat(rs1);
                    }
                    napuniObjekat(o);
                } catch (SQLException ex) {
                    Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void delete(IDomenskiObjekat ido) throws SQLException {

        String upit = "delete from " + ido.vratiNazivTabele()
                + " where " + ido.vratiNazivKoloneID() + " = " + "'" + ido.vratiID() + "'";
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
        s.close();

    }

    public void update(IDomenskiObjekat ido) throws SQLException {

        String upit = "update " + ido.vratiNazivTabele()
                + " set " + ido.vratiStringZaUpdate() + " where " + ido.vratiNazivKoloneID() + " = " + ido.vratiID();
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
        s.close();

    }

    public void insert(IDomenskiObjekat ido) throws SQLException {
        String upit = "INSERT INTO " + ido.vratiNazivTabele()
                + ido.vratiVrednostiZaInsert();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
        s.close();
    }

    public List vratiListuStavki(Date pocetak, Date kraj) throws SQLException {
        String query = "Select * from stavkadnevenberbe s join dnevnaberba d using (jbmg) where d.datum between '" + pocetak + "' and '" + kraj + "'";
        List<StavkaDnevneBerbe> stavke = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            StavkaDnevneBerbe s = new StavkaDnevneBerbe();
            s.napuniObjekat(rs);
            stavke.add(s);
        }
        return stavke;
    }

    public List vratiListuSvihObjekata(Date pocetak, Date kraj) throws Exception {

//        String upit = "Select * FROM " + ido.vratiNazivTabele();
        String upit = "Select * from stavkadnevneberbe s join dnevnaberba d using (dnevnaBerbaID) where d.datum between '" + pocetak + "' and '" + kraj + "' order by s.jmbg";
        System.out.println(upit);
        Statement naredba = connection.createStatement();
        IDomenskiObjekat ido = new StavkaDnevneBerbe();
        ResultSet rs = naredba.executeQuery(upit);
        List<IDomenskiObjekat> objekti = new ArrayList<>();
        while (rs.next()) {
            IDomenskiObjekat ido1 = ido.napraviObjekat(rs);
            napuniObjekat(ido1);
            objekti.add(ido1);
        }

        return objekti;

    }

}
