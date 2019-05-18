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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import myqueryServices.ParamTeacher;
import myquerymodel.DataTeacher;
import myquerymodel.NullOnEmptyConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import myqueryServices.UserClient;
//import myquerymodel.User;

public class EnregistrerEnseignant extends AppCompatActivity {

     private EditText nom;
     private EditText prenom;
     private String departement;
     private String grade;
     private Button btnInscription1;
     private String url = "http://192.168.8.100/MyQueryPHP/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_enseignant);

        // récuperation des vues

        nom = (EditText)findViewById(R.id.nomenseignant);
        prenom =(EditText)findViewById(R.id.prenomenseignant);
        btnInscription1 = (Button)findViewById(R.id.btnenrenseignant);
        // recuperation de la liste deroulante pour les filieres
        Spinner spinnerdepartement = findViewById(R.id.spinnerfiliere);
        ArrayAdapter<CharSequence> adapterfiliere = ArrayAdapter.createFromResource(this, R.array.listfiliere,android.R.layout.simple_spinner_item);
        adapterfiliere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdepartement.setAdapter(adapterfiliere);
        spinnerdepartement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departement = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // recuperation de la liste deroulante pour les niveau
        Spinner spinnergrade = findViewById(R.id.spinnergrade);
        ArrayAdapter<CharSequence> adaptergrade = ArrayAdapter.createFromResource(this, R.array.listgrade,android.R.layout.simple_spinner_item);
        adaptergrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergrade.setAdapter(adaptergrade);
        spinnergrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnInscription1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierSichampnonvide();
            }
        });

    }
    public void verifierSichampnonvide(){

        if ((nom.getText().length()==0)||(prenom.getText().length()==0)||(departement.length()==0)||
                (grade.length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            nom.getText().clear();
            prenom.getText().clear();

            //return false;
        }
        else{
            DataTeacher dataTeacher = new DataTeacher(nom.getText().toString(),
                                                      prenom.getText().toString(),
                                                      grade,
                                                      departement);
            envoyerParamEnseignant(dataTeacher);
            //return true;
        }
    }
    public void envoyerParamEnseignant (DataTeacher dataTeacher){

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
        ParamTeacher client = retrofit.create(ParamTeacher.class);
        Call<DataTeacher> call = client.registerTeacher(dataTeacher);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<DataTeacher>() {
            @Override
            public void onResponse(Call<DataTeacher> call, Response<DataTeacher> response) {
               // if (response.isSuccessful()) {
                    Toast.makeText(EnregistrerEnseignant.this, "user matricule", Toast.LENGTH_LONG).show();
               // }
            }


            @Override
            public void onFailure(Call<DataTeacher> call, Throwable t) {

                Toast.makeText(EnregistrerEnseignant.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
