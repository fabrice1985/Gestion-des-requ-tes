package myquerymodel;

public class Othersrequest {
    private String Motif;
    private String Matricule;
    private String Filiere;
    private String Niveau;
    private String Avec_erreur;
    private String Sans_erreur;

    public Othersrequest(String motif, String matricule, String filiere, String niveau, String avec_erreur, String sans_erreur) {
        Motif = motif;
        Matricule = matricule;
        Filiere = filiere;
        Niveau = niveau;
        Avec_erreur = avec_erreur;
        Sans_erreur = sans_erreur;
    }

    public String getMotif() {
        return Motif;
    }

    public void setMotif(String motif) {
        Motif = motif;
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String matricule) {
        Matricule = matricule;
    }

    public String getFiliere() {
        return Filiere;
    }

    public void setFiliere(String filiere) {
        Filiere = filiere;
    }

    public String getNiveau() {
        return Niveau;
    }

    public void setNiveau(String niveau) {
        Niveau = niveau;
    }

    public String getAvec_erreur() {
        return Avec_erreur;
    }

    public void setAvec_erreur(String avec_erreur) {
        Avec_erreur = avec_erreur;
    }

    public String getSans_erreur() {
        return Sans_erreur;
    }

    public void setSans_erreur(String sans_erreur) {
        Sans_erreur = sans_erreur;
    }
}
