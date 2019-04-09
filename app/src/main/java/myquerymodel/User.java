package myquerymodel;

public class User {
    private String matricule;
    private String nom;
    private String prenom;
    private String filiere;
    private String niveau;
    private String adressmail;
    private String password1;
    private String password2;

    public User (String matricule, String nom, String prenom, String filiere, String niveau, String adressmail, String password1){
        this.matricule = matricule;
        this.nom = nom;
        this.prenom= prenom;
        this.filiere = filiere;
        this.niveau = niveau;
        this.adressmail = adressmail;
        this.password1 = password1;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
