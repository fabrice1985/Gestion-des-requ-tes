package com.example.myquery;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import myqueryServices.UserClient;
import myquerymodel.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Inscription extends AppCompatActivity  {
    private Button btnInscription1;
    private EditText pseudo1;
    private EditText nom1;
    private EditText prenom1;
   // private EditText filiere1;
   //  private EditText niveau1;
    private EditText adressmail1;
    private EditText password2;
    private EditText password1;
    private String url = "http://192.168.8.100/MyQueryPHP/";
    private String pseudo,nom,prenom,filiere,niveau,adressmail,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // récuperation des vues

        nom1 = (EditText)findViewById(R.id.txtname);
        prenom1 =(EditText)findViewById(R.id.txtsurname);
        pseudo1 = (EditText)findViewById(R.id.txtmatricule);
        //filiere1 = (EditText)findViewById(R.id.txtfiliere);
        //niveau1 =(EditText)findViewById(R.id.txtlevel);
        adressmail1 = (EditText)findViewById(R.id.txtadress);
        password2 = (EditText)findViewById(R.id.txtPwd1);
        password1 = (EditText)findViewById(R.id.txtPwd2);
        btnInscription1 = (Button)findViewById(R.id.btnInscription1);
        // recuperation de la liste deroulante pour les filieres
        Spinner spinnerfiliere = findViewById(R.id.spinnerfiliere);
        ArrayAdapter<CharSequence> adapterfiliere = ArrayAdapter.createFromResource(this, R.array.listfiliere,android.R.layout.simple_spinner_item);
        adapterfiliere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfiliere.setAdapter(adapterfiliere);
        spinnerfiliere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filiere = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // recuperation de la liste deroulante pour les niveau
        Spinner spinnerniveau = findViewById(R.id.spinnerniveau);
        ArrayAdapter<CharSequence> adapterniveau = ArrayAdapter.createFromResource(this, R.array.listniveau,android.R.layout.simple_spinner_item);
        adapterniveau.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerniveau.setAdapter(adapterniveau);
        spinnerniveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                niveau = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

        if ((nom1.getText().length()==0)||(prenom1.getText().length()==0)||(pseudo1.getText().length()==0)||(filiere.length()==0)||
                (niveau.length()==0)||(adressmail1.getText().length()==0)||(password2.getText().length()==0)||(password1.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            nom1.getText().clear();
            prenom1.getText().clear();
            pseudo1.getText().clear();
            //filiere1.getText().clear();
            //niveau1.getText().clear();
            adressmail1.getText().clear();
            password2.getText().clear();
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
        if (password2.getText().toString().equals(password1.getText().toString())== true){

            pseudo = pseudo1.getText().toString();
            nom = nom1.getText().toString();
            prenom = prenom1.getText().toString();
            //filiere = filiere1.getText().toString();
            //niveau = niveau1.getText().toString();
            adressmail = adressmail1.getText().toString();
            password = password2.getText().toString();
            User user = new User(pseudo,nom,prenom,filiere,niveau,adressmail,password);
            /*User user = new User (pseudo1.getText().toString(),
                                  nom1.getText().toString(),
                                  prenom1.getText().toString(),
                                  filiere1.getText().toString(),
                                  niveau1.getText().toString(),
                                  adressmail1.getText().toString(),
                                  password2.getText().toString());*/
                                  envoyerParamEtudiant(user);
            Toast.makeText(this,"bravo les mots de passe sont identiques "+ nom1.getText().toString(),Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"les mots de passe ne sont pas identiques, Veuillez réessayer",Toast.LENGTH_LONG).show();
            password2.getText().clear();
            password1.getText().clear();
            //return false;
        }
    }
    public void envoyerParamEtudiant (User user){

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
        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.createAccount(user);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Inscription.this, "user matricule", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Inscription.this, "reponse non OK", Toast.LENGTH_LONG).show();
                }
            }


               @Override
            public void onFailure(Call<User> call, Throwable t) {

             Toast.makeText(Inscription.this, "Un problème est survenue",Toast.LENGTH_LONG).show();
            }
        });
    }


}
