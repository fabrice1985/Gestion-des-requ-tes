package com.example.myquery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import myqueryServices.ParamDES;
import myquerymodel.DataDES;
import myquerymodel.NullOnEmptyConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnregistrerDES extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private Button btnInscription1;
    private String url = "http://192.168.8.100/MyQueryPHP/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_des);
        // récuperation des vues

        nom = (EditText)findViewById(R.id.nomdes);
        prenom =(EditText)findViewById(R.id.prenomdes);
        btnInscription1 = (Button)findViewById(R.id.btnenrdes);
        btnInscription1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             verifierSichampnonvide();
            }
        });
    }
    public void verifierSichampnonvide(){

        if ((nom.getText().length()==0)||(prenom.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            nom.getText().clear();
            prenom.getText().clear();
            //return false;
        }
        else{
            DataDES dataDES = new DataDES(nom.getText().toString(),
                                          prenom.getText().toString());
            envoyerParamDES(dataDES);
            //return true;
        }
    }
    public void envoyerParamDES (DataDES dataDES){

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
        ParamDES client = retrofit.create(ParamDES.class);
        Call<DataDES> call = client.registerDES(dataDES);
        //Toast.makeText(this,"URL envoyée",Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<DataDES>() {
            @Override
            public void onResponse(Call<DataDES> call, Response<DataDES> response) {
               // if (response.isSuccessful()) {
                    Toast.makeText(EnregistrerDES.this, "user matricule", Toast.LENGTH_LONG).show();
               // }
            }


            @Override
            public void onFailure(Call<DataDES> call, Throwable t) {

                Toast.makeText(EnregistrerDES.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
