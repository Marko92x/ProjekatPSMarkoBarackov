/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marko
 */
public class ServerNit extends Thread{

    ServerSocket ss;
    
    
    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            while (true) {                
                Socket s = ss.accept();
                System.out.println("Povezan klijent");
                KlijentNit k = new KlijentNit(s);
                k.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
