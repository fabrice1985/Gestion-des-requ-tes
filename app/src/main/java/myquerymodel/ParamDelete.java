package myquerymodel;

public class ParamDelete {
    private String Motif;
    private String pseudo;

    public ParamDelete(String motif, String pseudo) {
        Motif = motif;
        this.pseudo = pseudo;
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
}
