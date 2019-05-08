package myquerymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("pseudo")
    @Expose
    private String pseudo;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("prenom")
    @Expose
    private String prenom;
    @SerializedName("filiere")
    @Expose
    private String filiere;
    @SerializedName("niveau")
    @Expose
    private String niveau;
    @SerializedName("adressmail")
    @Expose
    private String adressmail;
    @SerializedName("password")
    @Expose
    private String password;
   // private String password1;

    public User (String pseudo, String nom, String prenom, String filiere, String niveau, String adressmail, String password){
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom= prenom;
        this.filiere = filiere;
        this.niveau = niveau;
        this.adressmail = adressmail;
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getAdressmail() {
        return adressmail;
    }

    public void setAdressmail(String adressmail) {
        this.adressmail = adressmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
