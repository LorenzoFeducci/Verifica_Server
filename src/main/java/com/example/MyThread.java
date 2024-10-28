package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MyThread extends Thread{
    Socket socket;

    public MyThread(Socket s){
        this.socket = s;
        
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            ArrayList <String> lista = new ArrayList<>();
            String stringaRicevuta = "";
            do {
                
            stringaRicevuta = in.readLine();
            if(stringaRicevuta.equals("?")){
                for (String elemento : lista) {
                    out.writeBytes(elemento + "\n");
                }
                out.writeBytes("@" + "\n");
            }else{
                lista.add(stringaRicevuta);
                out.writeBytes("Nota salvata" + "\n");
            }
            } while (! stringaRicevuta.equals("!"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }                                                                            // (ricevere)
        
    }
}
