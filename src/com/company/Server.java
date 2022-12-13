package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Server {
        public static List<Etudiant> listEtudiant;
        public static List<Message>listMessage=new ArrayList<Message>();
        public static List<GroupDIS>listGroupDiscu=new ArrayList<GroupDIS>();
        private int port=3000;
        public Server(){}

        public static List<Etudiant> getListEtudiant() {
                return listEtudiant;
        }

        public static void setListEtudiant(List<Etudiant> listEtudiant) {
                Server.listEtudiant = listEtudiant;
        }

        public static List<Message> getListMessage() {
                return listMessage;
        }

        public static void setListMessage(List<Message> listMessage) {
                Server.listMessage = listMessage;
        }

        public static List<GroupDIS> getListGroupDiscu() {
                return listGroupDiscu;
        }

        public static void setListGroupDiscu(List<GroupDIS> listGroupDiscu) {
                Server.listGroupDiscu = listGroupDiscu;
        }

        public int getPort() {
                return port;
        }

        public void setPort(int port) {
                this.port = port;
        }

        public static void main(String args[]){
        try {

        DatagramSocket ServerSocket=new DatagramSocket(3000);


        listEtudiant=new ArrayList<Etudiant>();

        while(true) {
                byte[] dataRcv = new byte[1024];
                byte[] dataSend = new byte[1024];

                DatagramPacket pkRcv = new DatagramPacket(dataRcv, dataRcv.length);
                ServerSocket.receive(pkRcv);

                String message = new String(pkRcv.getData(), 0, pkRcv.getLength());
                System.out.println(message);


                if (message.startsWith("##")) {
                        String login = message.substring(2);
                        boolean test = false;
                        for (Etudiant e : Server.listEtudiant) {
                                if (e.getLogin().equals(login)) {
                                        test = true;
                                }
                        }
                        if (test == true) {
                                String ConnectMsg = "Cet etudiant est connecté!!";
                                DatagramPacket pk = new DatagramPacket(ConnectMsg.getBytes(), ConnectMsg.length(), pkRcv.getAddress(),
                                        pkRcv.getPort());
                                ServerSocket.send(pk);

                        } else {

                                Etudiant etd = new Etudiant(login, "1", login, pkRcv.getAddress(), pkRcv.getPort(), true);
                                //  System.out.println(etd);

                                Server.listEtudiant.add(etd);

                                String ConnectMsg2 = "Etudiant ajouté avec succès";
                                System.out.println(ConnectMsg2);
                                DatagramPacket pk = new DatagramPacket(ConnectMsg2.getBytes(), ConnectMsg2.length(), pkRcv.getAddress(),
                                        pkRcv.getPort());
                                ServerSocket.send(pk);

                        }

                }
                else if (message.startsWith("#LIST_EDTS")) {
                        for (Etudiant e : Server.listEtudiant) {
                                if (e.isStateConnection()) {
                                        String ETD=e.toString();

                                        DatagramPacket pk=new DatagramPacket(ETD.getBytes(),ETD.length(),pkRcv.getAddress(),pkRcv.getPort());
                                        ServerSocket.send(pk);

                                }
                        }
                }


                else if (message.startsWith("@#")) {
                        String msg[] = message.split("@#");
                        String login = msg[1];
                        String text = msg[2];
                        text=text.substring(0);
                        Message ME=new Message(text);
                        listMessage.add(ME);

                for (Etudiant e : Server.listEtudiant) {
                                if (e.getLogin().equals(login)) {
                                        InetAddress adr = e.getAddress();
                                        int port = e.getPort();
                                        DatagramPacket pk = new DatagramPacket(text.getBytes(), text.length(), adr, port);
                                        ServerSocket.send(pk);
                                }
                        }


                }
                else if (message.startsWith("#HISTO")) {
                        for(Message m:Server.listMessage){

                                                String sms=m.toString();

                                                DatagramPacket pk=new DatagramPacket(sms.getBytes(),sms.length(),pkRcv.getAddress(), pkRcv.getPort());
                                                ServerSocket.send(pk);

                                        }





                }

                else if (message.startsWith("#GROUP#")) {
                        String ps[] = message.split("#");
                        String titreGrp = ps[2];

                        GroupDIS grp = new GroupDIS(titreGrp);
                        listGroupDiscu.add(grp);
                System.out.println(grp);
                        String msg = "Group" +""+ titreGrp ;
                        DatagramPacket pk = new DatagramPacket(msg.getBytes(), msg.length(), pkRcv.getAddress(), pkRcv.getPort());
                        ServerSocket.send(pk);


                }
                else if (message.startsWith("#GROUPS")) {
                        for (GroupDIS gp : Server.listGroupDiscu) {
                                String groupe=gp.toString();

                                DatagramPacket PK1=new DatagramPacket(groupe.getBytes(),groupe.length(),pkRcv.getAddress(),pkRcv.getPort());
                                ServerSocket.send(PK1);

                        }

                }
                else if (message.startsWith("#>")) {
                        String tab[] = message.split("#>");
                        String titre = tab[1];
                        InetAddress addr=pkRcv.getAddress();
                        int prt=pkRcv.getPort();

                        for (GroupDIS GP : Server.listGroupDiscu) {
                                if (GP.getTitre().equals(titre)) {
                                        String msg="Congratulation You are connected in this Group "+titre;
                                        DatagramPacket pq=new DatagramPacket(msg.getBytes(),msg.length(),addr,prt);
                                        ServerSocket.send(pq);

                                }



                        }

                }
                else if(message.startsWith("#ETDS")) {
                        String T2[]=message.split("#");
                        String titregroup=T2[2];
                        for(GroupDIS g:listGroupDiscu) {
                                if( g.getTitre().equals(titregroup)){
                                        for(Etudiant e11:g.getListeEtu()) {
                                                String msg3 = ("liste de etudiant connecté  " + e11.getNom()+"+"+e11.getAddress()+"+"+e11.getPort());
                                                DatagramPacket pk = new DatagramPacket(msg3.getBytes(), msg3.length(), pkRcv.getAddress(),pkRcv.getPort());
                                                ServerSocket.send(pk);
                                        }
                                }
                                else {
                                        String msgk="Groupe n'existe pas";
                                        DatagramPacket pk1 = new DatagramPacket(msgk.getBytes(),msgk .length(), pkRcv.getAddress(),pkRcv.getPort());

                                        ServerSocket.send(pk1);


                                }

                        }


                }
                else if(message.startsWith("@>")) {
                        String T3[]=message.split("@>");
                        String titregroup=T3[1];
                        String msgenv=T3[2];
                        for(GroupDIS g:listGroupDiscu) {
                                if(g.getTitre().equals(titregroup)) {
                                        for(Etudiant e:g.getListeEtu() ) {

                                                DatagramPacket pk = new DatagramPacket(msgenv.getBytes(), msgenv.length(), e.getAddress(), e.getPort());
                                                ServerSocket.send(pk);}}




                                else {
                                        String msgk="Groupe n'existe pas";
                                        DatagramPacket pk1 = new DatagramPacket(msgk.getBytes(),msgk .length(), pkRcv.getAddress(),pkRcv.getPort());

                                        ServerSocket.send(pk1);

                                }
                        }

                }











        }





        }catch (Exception E){
        E.printStackTrace();
    }
}
}
