package myquerymodel;

public class User {
    private String pseudo;
    private String nom;
    private String prenom;
    private String filiere;
    private String niveau;
    private String adressmail;
    private String password;
    private String password1;

    public User (String pseudo, String nom, String prenom, String filiere, String niveau, String adressmail, String password){
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom= prenom;
        this.filiere = filiere;
        this.niveau = niveau;
        this.adressmail = adressmail;
        this.password = password1;
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

}
