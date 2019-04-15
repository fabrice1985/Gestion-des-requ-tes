package com.example.myquery;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import myqueryServices.UserClient;
import myquerymodel.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inscription extends AppCompatActivity {
    private Button btnInscription1;
    private EditText pseudo;
    private EditText nom;
    private EditText prenom;
    private EditText filiere;
    private EditText niveau;
    private EditText adressmail;
    private EditText password;
    private EditText password1;
    private String url = "http://192.168.8.100/MyQueryPHP/Inscription/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // récuperation des vues

        nom = (EditText)findViewById(R.id.txtname);
        prenom =(EditText)findViewById(R.id.txtsurname);
        pseudo = (EditText)findViewById(R.id.txtmatricule);
        filiere = (EditText)findViewById(R.id.txtfiliere);
        niveau =(EditText)findViewById(R.id.txtlevel);
        adressmail = (EditText)findViewById(R.id.txtadress);
        password = (EditText)findViewById(R.id.txtPwd1);
        password1 = (EditText)findViewById(R.id.txtPwd2);
        btnInscription1 = (Button)findViewById(R.id.btnInscription1);
        btnInscription1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierParamInscription();
            }
        });
    }
    public void verifierParamInscription (){
        // tester la connectivité à internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            //boolean test1 = verifierSichampnonvide();
            verifierSichampnonvide();
                //Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            }

            //Toast.makeText(this,"Network is available",Toast.LENGTH_LONG).show();

        else{
            Toast.makeText(this,"Connexion internet non disponible",Toast.LENGTH_LONG).show();
        }

    }
     // verifier si les champs de saisies sont non vide avant l'envoi des données
    public void verifierSichampnonvide(){

        if ((nom.getText().length()==0)||(prenom.getText().length()==0)||(pseudo.getText().length()==0)||(filiere.getText().length()==0)||
                (niveau.getText().length()==0)||(adressmail.getText().length()==0)||(password.getText().length()==0)||(password1.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            nom.getText().clear();
            prenom.getText().clear();
            pseudo.getText().clear();
            filiere.getText().clear();
            niveau.getText().clear();
            adressmail.getText().clear();
            password.getText().clear();
            password1.getText().clear();
            //return false;
        }
        else{
            verifierMotdePasse();
           //return true;
        }
    }
    public void verifierMotdePasse(){
        // verifier si les deux mots de passe sont identiques
        if (password.getText().toString().equals(password1.getText().toString())== true){

            User user = new User (pseudo.getText().toString(),
                                  nom.getText().toString(),
                                  prenom.getText().toString(),
                                  filiere.getText().toString(),
                                  niveau.getText().toString(),
                                  adressmail.getText().toString(),
                                  password.getText().toString());
                                  envoyerParamEtudiant(user);
            Toast.makeText(this,"bravo les mots de passe sont identiques"+nom.getText().toString(),Toast.LENGTH_LONG).show();
            /*String nom = txtname.getText().toString();
            String prenom = txtsurname.getText().toString();
            String matricule = txtmatricule.getText().toString();
            String filiere = txtfiliere.getText().toString();
            String level = txtlevel.getText().toString();
            String adressmail = txtadress.getText().toString();
            String password1 = txtPwd1.getText().toString(); */
            //return true;
        }
        else{
            Toast.makeText(this,"les mots de passe ne sont pas identiques, Veuillez réessayer",Toast.LENGTH_LONG).show();
            password.getText().clear();
            password1.getText().clear();
            //return false;
        }
    }
    public void envoyerParamEtudiant (User user){
       // créons une instance de retrofit
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.createAccount(user);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
             Toast.makeText(Inscription.this,"user matricule",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
             Toast.makeText(Inscription.this, "Un problème est survenue",Toast.LENGTH_LONG).show();
            }
        });
    }
}
