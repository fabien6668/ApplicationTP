package android.ei6.biz.rdvlistemodule;

/**
 * Created by fabien on 23/03/2016.
 */
public class Contacts {
    private String nom;
    private String horaire;
    private String telephone;
    private int id;

    public Contacts(String nom, String horaire, String telephone){
        this.nom = nom;
        this.horaire = horaire;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }
    public String getHoraire() {
        return horaire;
    }
    public String getTelephone() {
        return telephone;
    }
    public int getId(){
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setId(int id) {
        this.id = id;
    }

}
