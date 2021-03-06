package com.example.myquery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import myqueryServices.TreatTeachers;
import myquerymodel.Teachers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TraitementEnseignant extends AppCompatActivity {

    private Button btntraitement;
    private String motif;
    private EditText nom_enseignant;
    private EditText nom_etudiant;
    private String filiere;
    private EditText note_traiter;
    //private EditText note_v;
    private EditText UE;
    private EditText matricule;
    private String niveau;
    private String url = "http://192.168.8.100/MyQueryPHP/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement_enseignant);
        //motif = (EditText)findViewById(R.id.txtmotif);
        nom_enseignant =(EditText)findViewById(R.id.txtnomenseignant);
        matricule = (EditText)findViewById(R.id.txtmatricule);
        //filiere = (EditText)findViewById(R.id.txtfiliere);
        note_traiter =(EditText)findViewById(R.id.txtnotetraiter);
        //note_v = (EditText)findViewById(R.id.txtnote_v);
        UE = (EditText)findViewById(R.id.txtue);
        nom_etudiant = (EditText)findViewById(R.id.txtnometudiant);
        //niveau = (EditText)findViewById(R.id.txtniveau);
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
        btntraitement = (Button)findViewById(R.id.btntraitement);
        btntraitement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierSichampnonvide();
            }
        });
    }

    public void verifierSichampnonvide(){

        if ((nom_enseignant.getText().length()==0)||(nom_etudiant.getText().length()==0)||(motif.length()==0)||(filiere.length()==0)||
                (note_traiter.getText().length()==0)||(UE.getText().length()==0)||(matricule.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            nom_enseignant.getText().clear();
            //motif.getText().clear();

            nom_etudiant.getText().clear();
            //filiere.getText().clear();
            note_traiter.getText().clear();
            //note_v.getText().clear();
            UE.getText().clear();
            matricule.getText().clear();
            //return false;
        }
        else if ((Float.parseFloat(note_traiter.getText().toString()))>= 0
                &&(Float.parseFloat(note_traiter.getText().toString()))<=20 ){
            Teachers teachers = new Teachers(motif,
                    matricule.getText().toString(),
                    nom_etudiant.getText().toString(),
                    filiere,
                    niveau,
                    note_traiter.getText().toString(),
                    UE.getText().toString(),
                    nom_enseignant.getText().toString());
                    envoyerParamNotes(teachers);
            Toast.makeText(this,"le motif et les notes sont valables",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"note non valable entrer une note comprise entre 0 et 20 ",Toast.LENGTH_LONG).show();
            //motif.getText().clear();
            //note_v.getText().clear();
            note_traiter.getText().clear();
        }
    }
    public void envoyerParamNotes(Teachers teachers){

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
        TreatTeachers client = retrofit.create(TreatTeachers.class);
        Call<Teachers> call = client.teachersTreat(teachers);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<Teachers>() {
            @Override
            public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                if (response.isSuccessful()){
                    Toast.makeText(TraitementEnseignant.this,"user matricule",Toast.LENGTH_LONG).show();}
                else {
                    Toast.makeText(TraitementEnseignant.this,"problème",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Teachers> call, Throwable t) {
                Toast.makeText(TraitementEnseignant.this, "Un problème est survenue",Toast.LENGTH_LONG).show();
            }
        });
    }
}
