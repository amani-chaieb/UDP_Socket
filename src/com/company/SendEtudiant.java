package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendEtudiant extends Thread{
    DatagramSocket sc;
    String login;

    public SendEtudiant(DatagramSocket sc) {

        this.sc = sc;
    }


    public void run(){

        while (true){
            try{
                BufferedReader inclavier=new BufferedReader(new InputStreamReader(System.in));

                InetAddress adresse=InetAddress.getByName("127.0.0.1");
                String msg=inclavier.readLine();
                DatagramPacket paquetSnd=new DatagramPacket(msg.getBytes(),msg.length(),adresse,3000);
                sc.send(paquetSnd);



            }catch (Exception e){
                e.printStackTrace();
            }

        }



    }



}
