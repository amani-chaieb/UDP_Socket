package com.company;

import java.util.ArrayList;
import java.util.List;

public class GroupDIS {
private String titre;
private List<Etudiant>listeEtu;
private List<Message>listemsg;
public GroupDIS(String ti){
    this.titre=ti;
    List<Etudiant>ls=new ArrayList<Etudiant>();
    List<Message>MS=new ArrayList<Message>();

}

public GroupDIS(){
    super();
}

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Etudiant> getListeEtu() {
        return listeEtu;
    }

    public void setListeEtu(List<Etudiant> listeEtu) {
        this.listeEtu = listeEtu;
    }

    public List<Message> getListemsg() {
        return listemsg;
    }

    public void setListemsg(List<Message> listemsg) {
        this.listemsg = listemsg;
    }
    public String toString(){
        return "Groupe de discussion [name=" +titre + "]";
    }
}
