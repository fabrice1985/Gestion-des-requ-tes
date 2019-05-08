package com.example.myquery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import myqueryServices.OthersrequestInterface;
import myquerymodel.Othersrequest;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import myqueryServices.UserRequeteNotes;
//import myquerymodel.Notes;

public class AutreRequete extends AppCompatActivity {

    private Button btnautrerequete;
    private String Motif;
    private String Matricule = "";
    private EditText nom;
    private String Filiere;
    private String Niveau;
    private EditText avec_erreur;
    private EditText sans_erreur;
    //private EditText UE;
    //private EditText enseignant;
    private String url = "http://192.168.8.100/MyQueryPHP/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autre_requete);
        Intent intent = getIntent();
        Matricule = intent.getStringExtra("pseudo");
        avec_erreur = (EditText)findViewById(R.id.txtavec_erreur);
        sans_erreur = (EditText)findViewById(R.id.txtsans_erreur);
        btnautrerequete = (Button)findViewById(R.id.btnautrerequete);
        btnautrerequete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    verifierSichampnonvide();
            }
        });
        Spinner spinnermotif = findViewById(R.id.spinnermotif);
        ArrayAdapter<CharSequence> adaptermotif = ArrayAdapter.createFromResource(this, R.array.listmotif,android.R.layout.simple_spinner_item);
        adaptermotif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermotif.setAdapter(adaptermotif);
        spinnermotif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Motif = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinnerfiliere = findViewById(R.id.spinnerfiliere);
        ArrayAdapter<CharSequence> adapterfiliere = ArrayAdapter.createFromResource(this, R.array.listfiliere,android.R.layout.simple_spinner_item);
        adapterfiliere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfiliere.setAdapter(adapterfiliere);
        spinnerfiliere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Filiere = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinnerniveau = findViewById(R.id.spinnerniveau);
        ArrayAdapter<CharSequence> adapterniveau = ArrayAdapter.createFromResource(this, R.array.listniveau,android.R.layout.simple_spinner_item);
        adapterniveau.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerniveau.setAdapter(adapterniveau);
        spinnerniveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Niveau = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void verifierSichampnonvide(){

        if ((Motif.length()==0)||(Filiere.length()==0)|| (avec_erreur.getText().length()==0)||(sans_erreur.getText().length()==0)
                ||(Matricule.length()==0)||(Niveau.length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            avec_erreur.getText().clear();
            //motif.getText().clear();
            //pseudo.getText().clear();
            //filiere.getText().clear();
            sans_erreur.getText().clear();

            //return false;
        }
        else  {
            Othersrequest othersrequest = new Othersrequest(Motif, Matricule, Filiere, Niveau,
                    avec_erreur.getText().toString(),
                    sans_erreur.getText().toString());
            envoyerParamNotes(othersrequest);
            Toast.makeText(this,"le motif et les notes sont valables",Toast.LENGTH_LONG).show();
        }
    }
    public void envoyerParamNotes(Othersrequest othersrequest){

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
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        OthersrequestInterface client = retrofit.create(OthersrequestInterface.class);
        Call<Othersrequest> call = client.sendOthers(othersrequest);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<Othersrequest>() {
            @Override
            public void onResponse(Call<Othersrequest> call, Response<Othersrequest> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AutreRequete.this,"user matricule",Toast.LENGTH_LONG).show();}
                else {
                    Toast.makeText(AutreRequete.this,"problème",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Othersrequest> call, Throwable t) {
                Toast.makeText(AutreRequete.this, "Un problème est survenue",Toast.LENGTH_LONG).show();
            }
        });
    }
}
