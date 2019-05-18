package com.example.myquery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import myqueryServices.GetDataCompte;
import myquerymodel.CompteAdapter;
import myquerymodel.CompteList;
import myquerymodel.Comptes;
import myquerymodel.NullOnEmptyConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Compte_Administrateur extends AppCompatActivity {
    private TextView textView;
    private CompteAdapter adapter;
    private RecyclerView recyclerView;
    private String url = "http://192.168.8.100/MyQueryPHP/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte__administrateur);
        textView =(TextView)findViewById(R.id.text1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.compte_administrateur,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.consulter:
                Toast.makeText(this, "consulter", Toast.LENGTH_LONG).show();
                return true;
            case R.id.compte_etudiant:
                Toast.makeText(this, "Consultez compte étudiant", Toast.LENGTH_LONG).show();
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
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(url)
                        .client(httpClient.build())
                        .addConverterFactory(new NullOnEmptyConverterFactory())
                        .addConverterFactory(GsonConverterFactory.create(gson));
                Retrofit retrofit = builder.build();
                GetDataCompte client = retrofit.create(GetDataCompte.class);
                Call<CompteList> call = client.getAllCompte();
                call.enqueue(new Callback<CompteList>() {
                    @Override
                    public void onResponse(Call<CompteList> call, Response<CompteList> response) {
                        generateEmployeeList(response.body().getCompteList());
                        Toast.makeText(Compte_Administrateur.this, "Okay", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<CompteList> call, Throwable t) {
                        Toast.makeText(Compte_Administrateur.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        textView.setText(t.getMessage());
                    }
                });


            return true;
            case R.id.compte_enseignant:
                Toast.makeText(this, "Consultez compte enseignant", Toast.LENGTH_LONG).show();
                return true;
            case R.id.compte_DES:
                Toast.makeText(this, "Consulter compte DES", Toast.LENGTH_LONG).show();
                return true;
            case R.id.deconnecter:
                Toast.makeText(this, "deconnecter", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(Compte_Administrateur.this, MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.enregitrerenseignant:
                Toast.makeText(this, "Enregistrer enseignant", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Compte_Administrateur.this, EnregistrerEnseignant.class);
                //intent.putExtra("pseudo",pseudo);
                startActivity(intent);
                return true;
            case R.id.enregistrerDES:
                Intent intent1 = new Intent(Compte_Administrateur.this, EnregistrerDES.class);
                //intent1.putExtra("pseudo",pseudo);
                startActivity(intent1);
                Toast.makeText(this, "Enregistrer agent DES", Toast.LENGTH_LONG).show();
                return true;
            case R.id.supprimercompte:
                Toast.makeText(this, "Supprimer compte", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(Compte_Administrateur.this, SupprimerCompte.class);
                startActivity(intent3);
                return true;
            case R.id.modifiercompte:
                Toast.makeText(this, "Modifier compte", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList (ArrayList<Comptes> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new CompteAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Compte_Administrateur.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}

