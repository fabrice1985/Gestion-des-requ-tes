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
    private EditText txtname;
    private EditText txtsurname;
    private EditText txtmatricule;
    private EditText txtfiliere;
    private EditText txtlevel;
    private EditText txtadress;
    private EditText txtPwd1;
    private EditText txtPwd2;
    private String url = "http://192.168.8.100/MyQueryPHP/Inscription.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // récuperation des vues

        txtname = (EditText)findViewById(R.id.txtname);
        txtsurname =(EditText)findViewById(R.id.txtsurname);
        txtmatricule = (EditText)findViewById(R.id.txtmatricule);
        txtfiliere = (EditText)findViewById(R.id.txtfiliere);
        txtlevel =(EditText)findViewById(R.id.txtlevel);
        txtadress = (EditText)findViewById(R.id.txtadress);
        txtPwd1 = (EditText)findViewById(R.id.txtPwd1);
        txtPwd2 = (EditText)findViewById(R.id.txtPwd2);
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

        if ((txtname.getText().length()==0)||(txtsurname.getText().length()==0)||(txtmatricule.getText().length()==0)||(txtfiliere.getText().length()==0)||
                (txtlevel.getText().length()==0)||(txtadress.getText().length()==0)||(txtPwd1.getText().length()==0)||(txtPwd2.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            txtname.getText().clear();
            txtsurname.getText().clear();
            txtmatricule.getText().clear();
            txtfiliere.getText().clear();
            txtlevel.getText().clear();
            txtadress.getText().clear();
            txtPwd1.getText().clear();
            txtPwd2.getText().clear();
            //return false;
        }
        else{
            verifierMotdePasse();
           //return true;
        }
    }
    public void verifierMotdePasse(){
        // verifier si les deux mots de passe sont identiques
        if (txtPwd1.getText().toString().equals(txtPwd2.getText().toString())== true){
            Toast.makeText(this,"bravo les mots de passe sont identiques",Toast.LENGTH_LONG).show();
            User user = new User (txtmatricule.getText().toString(),
                                  txtname.getText().toString(),
                                  txtsurname.getText().toString(),
                                  txtfiliere.getText().toString(),
                                  txtlevel.getText().toString(),
                                  txtadress.getText().toString(),
                                  txtPwd1.getText().toString());
                                  envoyerParamEtudiant(user);
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
            txtPwd1.getText().clear();
            txtPwd2.getText().clear();
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
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
             Toast.makeText(Inscription.this,"user matricule"+response.body().getMatricule(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
             Toast.makeText(Inscription.this, "Un problème est survenue",Toast.LENGTH_LONG).show();
            }
        });
    }
}
