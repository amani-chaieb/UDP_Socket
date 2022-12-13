package com.company;

public class Message {
    Etudiant etuSend;
    Etudiant etuRecev;
    private String msg;
    public Message(){
        super();
    }
    public Message(Etudiant etuSend,Etudiant etuRecev,String m){
        this.etuSend=etuSend;
        this.etuRecev=etuRecev;
        this.msg=m;
    }
    public Message(String M){
        this.msg=M ;
    }

    public Etudiant getEtuSend() {
        return etuSend;
    }

    public void setEtuSend(Etudiant etuSend) {
        this.etuSend = etuSend;
    }

    public Etudiant getEtuRecev() {
        return etuRecev;
    }

    public void setEtuRecev(Etudiant etuRecev) {
        this.etuRecev = etuRecev;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String toString(){
        return "le message ="+msg+"]";
    }
}
