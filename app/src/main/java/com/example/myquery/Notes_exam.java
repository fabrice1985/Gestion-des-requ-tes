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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import myqueryServices.Interface_Note_Exam;
import myquerymodel.NoteExam;
import myquerymodel.NullOnEmptyConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Notes_exam extends AppCompatActivity {

    private Button btnrequetenote;
    private String pseudo = "";
    private EditText nom;
    private String filiere;
    private String niveau;
    private EditText note_a;
    private EditText note_v;
    private EditText UE;
    private String url = "http://192.168.8.100/MyQueryPHP/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_exam);
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
        //niveau = (EditText)findViewById(R.id.txtniveau);
        btnrequetenote = (Button)findViewById(R.id.btnrequetenote);
        btnrequetenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierSichampnonvide();
            }
        });
        // recuperation des listes
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

        if ((nom.getText().length()==0)||(filiere.length()==0)|| (note_a.getText().length()==0)||
                (note_v.getText().length()==0)||(UE.getText().length()==0)||(niveau.length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            nom.getText().clear();
            note_a.getText().clear();
            note_v.getText().clear();
            UE.getText().clear();

            //return false;
        }
        else if  ((Integer.parseInt(note_a.getText().toString()))>= 0 && (Integer.parseInt(note_a.getText().toString()))<=20
                &&(Integer.parseInt(note_v.getText().toString()))>=0 && (Integer.parseInt(note_v.getText().toString()))<=20){
            NoteExam exam_notes = new NoteExam(pseudo,
                    nom.getText().toString(),
                    filiere,
                    niveau,
                    note_a.getText().toString(),
                    note_v.getText().toString(),
                    UE.getText().toString());
            envoyerParamNotes(exam_notes);
            Toast.makeText(this,"le motif et les notes sont valables",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"le motif ou les notes ne sont pas valables",Toast.LENGTH_LONG).show();
            //motif.getText().clear();
            note_v.getText().clear();
            note_a.getText().clear();
        }
    }
    public void envoyerParamNotes(NoteExam exam_notes){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);
        // créons une instance de retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url)
                .client(httpClient.build())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        Interface_Note_Exam client = retrofit.create(Interface_Note_Exam.class);
        Call<NoteExam> call = client.registerExam(exam_notes);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<NoteExam>() {
            @Override
            public void onResponse(Call<NoteExam> call, Response<NoteExam> response) {
                //if (response.isSuccessful()){
                    Toast.makeText(Notes_exam.this,"user matricule",Toast.LENGTH_LONG).show();
            //}

            }

            @Override
            public void onFailure(Call<NoteExam> call, Throwable t) {
                Toast.makeText(Notes_exam.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
