package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveEtud extends Thread{
DatagramSocket sc;
public ReceiveEtud(DatagramSocket s){
    this.sc=s;
}
public void run(){
    while (true){
        try{
            DatagramPacket paqueRcv=new DatagramPacket(new byte[2048],2048);
            sc.receive(paqueRcv);
            String messageRecu=new String(paqueRcv.getData());
            System.out.println(messageRecu);

        }catch(Exception e){
            e.printStackTrace();

        }
    }
}

}
