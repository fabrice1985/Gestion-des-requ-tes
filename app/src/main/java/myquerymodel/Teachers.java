package myquerymodel;

public class Teachers {
    private String Motif;
    private String Matricule;
    private String Nom;
    private String Filiere;
    private String Niveau;
    private String Note;
    private String UE;
    private String Enseignant;

    public Teachers(String motif, String matricule, String nom, String filiere, String niveau, String note, String UE, String enseignant) {
        Motif = motif;
        Matricule = matricule;
        Nom = nom;
        Filiere = filiere;
        Niveau = niveau;
        Note = note;
        this.UE = UE;
        Enseignant = enseignant;
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

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
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

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getUE() {
        return UE;
    }

    public void setUE(String UE) {
        this.UE = UE;
    }

    public String getEnseignant() {
        return Enseignant;
    }

    public void setEnseignant(String enseignant) {
        Enseignant = enseignant;
    }
}


