package myquerymodel;

public class NoteExam {

    private String Matricule;
    private String Nom;
    private String Filiere;
    private String Niveau;
    private String Note_attribue;
    private String Note_valable;
    private String UE;

    public NoteExam(String matricule, String nom, String filiere, String niveau, String note_attribue, String note_valable, String UE) {
        Matricule = matricule;
        Nom = nom;
        Filiere = filiere;
        Niveau = niveau;
        Note_attribue = note_attribue;
        Note_valable = note_valable;
        this.UE = UE;
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
}
