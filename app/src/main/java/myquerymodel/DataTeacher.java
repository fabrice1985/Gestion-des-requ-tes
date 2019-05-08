package myquerymodel;

public class DataTeacher {
    private String nom;
    private String prenom;
    private String grade;
    private String Departement;

    public DataTeacher(String nom, String prenom, String grade, String departement) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
        Departement = departement;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDepartement() {
        return Departement;
    }

    public void setDepartement(String departement) {
        Departement = departement;
    }
}
