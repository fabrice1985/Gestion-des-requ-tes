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

import myqueryServices.Interface_Delete_Account;
import myquerymodel.NullOnEmptyConverterFactory;
import myquerymodel.ParamDelete;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupprimerCompte extends AppCompatActivity {
    private Button btnrequetenote;
    private String compte = "";
    private EditText pseudo;
    private String url = "http://192.168.8.100/MyQueryPHP/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_compte);

        // récuperation des vues
        pseudo = (EditText)findViewById(R.id.txtmatricule);
        btnrequetenote = (Button)findViewById(R.id.btnrequetenote);
        btnrequetenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierSichampnonvide();
            }
        });
        // recuperation des listes
        Spinner spinnercompte = findViewById(R.id.spinnercompte);
        ArrayAdapter<CharSequence> adaptercompte = ArrayAdapter.createFromResource(this, R.array.listcompte,android.R.layout.simple_spinner_item);
        adaptercompte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercompte.setAdapter(adaptercompte);
        spinnercompte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                compte = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void verifierSichampnonvide(){

        if ((pseudo.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            pseudo.getText().clear();
        }
        else {
            //Toast.makeText(this, "le motif ou les notes ne sont pas valables", Toast.LENGTH_LONG).show();
            ParamDelete paramDelete = new ParamDelete(compte,pseudo.getText().toString());
             envoyerParamSupp(paramDelete);
        }
    }
    public void envoyerParamSupp(ParamDelete paramDelete){

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
        Interface_Delete_Account client = retrofit.create(Interface_Delete_Account.class);
        Call<ParamDelete> call = client.deleteAccount(paramDelete);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<ParamDelete>() {
            @Override
            public void onResponse(Call<ParamDelete> call, Response<ParamDelete> response) {
                //if (response.isSuccessful()){
                    Toast.makeText(SupprimerCompte.this,"user matricule",Toast.LENGTH_LONG).show();
                //}
            }

            @Override
            public void onFailure(Call<ParamDelete> call, Throwable t) {
                Toast.makeText(SupprimerCompte.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
