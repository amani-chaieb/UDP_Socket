package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ServerEtd {
    public static void main(String [] args){
        try{
            DatagramSocket sc=new DatagramSocket();
            SendEtudiant setd=new SendEtudiant(sc);
            ReceiveEtud retd=new ReceiveEtud(sc);
            setd.start();
            retd.start();


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
