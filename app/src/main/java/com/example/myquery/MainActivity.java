package com.example.myquery;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import myqueryServices.Connexion;
import myquerymodel.ParamConnexion;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnInscription;
    private Button btnConnexion;
    private EditText login;
    private EditText password;
    private String url = "http://192.168.8.100/MyQueryPHP/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInscription = (Button)findViewById(R.id.btnInscription);
        btnConnexion = (Button)findViewById(R.id.btnConnexion);
        login = (EditText)findViewById(R.id.txtLogin);
        password = (EditText)findViewById(R.id.txtPwd);
        // Se connecter à l'activité de création de compte_etudiant
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Inscription.class);
                startActivity(intent);
            }
        });
        // Se connecter à son compte_etudiant
      btnConnexion.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              seConnecter();

             // Intent intent = new Intent (MainActivity.this, Compte_etudiant.class);
              //startActivity(intent);
          }
      });
    }
    public void seConnecter(){
        // tester la connectivité à internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            verifierSichampnonvide();
        }

        else{
            Toast.makeText(this,"Connexion internet non disponible",Toast.LENGTH_LONG).show();
        }
    }
    // vérifier si les champs de saisies sont non vides
    public void verifierSichampnonvide(){
        if ((login.getText().length()==0)||(password.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            login.getText().clear();
            password.getText().clear();

        }
        else{
            Toast.makeText(this,"bravo vos paramètres de connexion sont bons",Toast.LENGTH_LONG).show();

            ParamConnexion paramconnexion = new ParamConnexion(login.getText().toString(),
                                                                password.getText().toString());
             envoyerParamConnexion(paramconnexion);
             String pseudo = login.getText().toString();
             Intent intent = new Intent(MainActivity.this, Compte_Enseignant.class);
             intent.putExtra("pseudo",pseudo);
             startActivity(intent);


        }
    }
    public void envoyerParamConnexion (ParamConnexion paramconnexion){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);
        // créons une instance de retrofit
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        Connexion client = retrofit.create(Connexion.class);
        Call<ParamConnexion> call = client.createConnexion(paramconnexion);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<ParamConnexion>() {
            @Override
            public void onResponse(Call<ParamConnexion> call, Response<ParamConnexion> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "user matricule", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "reponse non OK", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<ParamConnexion> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Un problème est survenue",Toast.LENGTH_LONG).show();
            }
        });
    }

}
