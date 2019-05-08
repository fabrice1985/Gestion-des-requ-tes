package myquerymodel;

public class Notes {
    private String Motif;
    private String pseudo;
    private String Nom_E;
    private String Filiere;
    private String Niveau;
    private String Note_attribue;
    private String Note_valable;
    private String UE;
    private String Enseignant;

    public Notes(String motif, String pseudo, String nom_E, String filiere, String niveau, String note_attribue, String note_valable, String UE, String enseignant) {
        Motif = motif;
        this.pseudo = pseudo;
        Nom_E = nom_E;
        Filiere = filiere;
        Niveau = niveau;
        Note_attribue = note_attribue;
        Note_valable = note_valable;
        this.UE = UE;
        Enseignant = enseignant;
    }

    public String getMotif() {
        return Motif;
    }

    public void setMotif(String motif) {
        Motif = motif;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom_E() {
        return Nom_E;
    }

    public void setNom_E(String nom_E) {
        Nom_E = nom_E;
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

    public String getNote_attribue() {
        return Note_attribue;
    }

    public void setNote_attribue(String note_attribue) {
        Note_attribue = note_attribue;
    }

    public String getNote_valable() {
        return Note_valable;
    }

    public void setNote_valable(String note_valable) {
        Note_valable = note_valable;
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
