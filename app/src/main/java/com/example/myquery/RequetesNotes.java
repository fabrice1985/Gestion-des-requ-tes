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

import myqueryServices.UserRequeteNotes;
import myquerymodel.Notes;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import myquerymodel.User;

public class RequetesNotes extends AppCompatActivity {
    private Button btnrequetenote;
    private String motif;
    private String pseudo = "";
    private EditText nom;
    private String filiere;
    private String niveau;
    private EditText note_a;
    private EditText note_v;
    private EditText UE;
    private EditText enseignant;
    private String url = "http://192.168.8.100/MyQueryPHP/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requetes_notes);
        Intent intent = getIntent();
        pseudo = intent.getStringExtra("pseudo");
        // récuperation des vues

        //motif = (EditText)findViewById(R.id.txtmotif);
        nom =(EditText)findViewById(R.id.txtname);
        //pseudo = (EditText)findViewById(R.id.txtmatricule);
        //filiere = (EditText)findViewById(R.id.txtfiliere);
        note_a =(EditText)findViewById(R.id.txtnote_a);
        note_v = (EditText)findViewById(R.id.txtnote_v);
        UE = (EditText)findViewById(R.id.txtue);
        enseignant = (EditText)findViewById(R.id.txtenseignant);
        //niveau = (EditText)findViewById(R.id.txtniveau);
        btnrequetenote = (Button)findViewById(R.id.btnrequetenote);
        btnrequetenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             verifierSichampnonvide();
            }
        });
        // recuperation des listes
        Spinner spinnermotif = findViewById(R.id.spinnermotif);
        ArrayAdapter<CharSequence> adaptermotif = ArrayAdapter.createFromResource(this, R.array.listmotif,android.R.layout.simple_spinner_item);
        adaptermotif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermotif.setAdapter(adaptermotif);
        spinnermotif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                motif = parent.getItemAtPosition(position).toString();
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
                filiere = parent.getItemAtPosition(position).toString();

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
                niveau = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void verifierSichampnonvide(){

        if ((nom.getText().length()==0)||(motif.length()==0)||(filiere.length()==0)||
                (note_a.getText().length()==0)||(note_v.getText().length()==0)||(UE.getText().length()==0)||(enseignant.getText().length()==0)||(niveau.length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            nom.getText().clear();
            //motif.getText().clear();
            //pseudo.getText().clear();
            //filiere.getText().clear();
            note_a.getText().clear();
            note_v.getText().clear();
            UE.getText().clear();
            enseignant.getText().clear();
            //return false;
        }
        else if  ((Integer.parseInt(note_a.getText().toString()))>= 0 && (Integer.parseInt(note_a.getText().toString()))<=20
                  &&(Integer.parseInt(note_v.getText().toString()))>=0 && (Integer.parseInt(note_v.getText().toString()))<=20){
            Notes notes = new Notes(motif,
                                    pseudo,
                                    nom.getText().toString(),
                                    filiere,
                                    niveau,
                                    note_a.getText().toString(),
                                    note_v.getText().toString(),
                                    UE.getText().toString(),
                                    enseignant.getText().toString());
            envoyerParamNotes(notes);
            Toast.makeText(this,"le motif et les notes sont valables",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"le motif ou les notes ne sont pas valables",Toast.LENGTH_LONG).show();
            //motif.getText().clear();
            note_v.getText().clear();
            note_a.getText().clear();
        }
    }
        public void envoyerParamNotes(Notes notes){

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
            UserRequeteNotes client = retrofit.create(UserRequeteNotes.class);
            Call<Notes> call = client.queryNotes(notes);
            //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
            call.enqueue(new Callback<Notes>() {
                @Override
                public void onResponse(Call<Notes> call, Response<Notes> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(RequetesNotes.this,"user matricule",Toast.LENGTH_LONG).show();}
                    else {
                        Toast.makeText(RequetesNotes.this,"problème",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Notes> call, Throwable t) {
                    Toast.makeText(RequetesNotes.this, "Un problème est survenue",Toast.LENGTH_LONG).show();
                }
            });
        }

}
