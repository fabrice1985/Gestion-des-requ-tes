package myquerymodel;

public class ParamConnexion {
    private String pseudo;
    private String password;

    public ParamConnexion(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }
    public String level;
    public String echec;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
