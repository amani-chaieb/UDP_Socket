package com.company;

import java.net.InetAddress;

public class Etudiant {
    private String nom;
    private String niveau;
    private String login;
    private boolean StateConnection=true;
    private InetAddress address;
    private int port;
    public Etudiant(String nm,String nv,String lg,InetAddress ad,int port,boolean state){
        super();
        this.nom=nm;
        this.niveau=nv;
        this.login=lg;
        this.address=ad;
        this.port=port;
        this.StateConnection=state;
    }
    public Etudiant(){
        super();

    }

    public String getNom() {
        return nom;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getLogin() {
        return login;
    }

    public boolean isStateConnection() {
        return StateConnection;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setStateConnection(boolean stateConnection) {
        StateConnection = stateConnection;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public String toString(){
        return "[nom=" +nom+"]";
    }
}
