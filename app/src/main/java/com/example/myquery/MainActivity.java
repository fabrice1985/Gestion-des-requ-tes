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

public class MainActivity extends AppCompatActivity {

    private Button btnInscription;
    private Button btnConnexion;
    private EditText txtLogin;
    private EditText txtPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInscription = (Button)findViewById(R.id.btnInscription);
        btnConnexion = (Button)findViewById(R.id.btnConnexion);
        txtLogin = (EditText)findViewById(R.id.txtLogin);
        txtPwd = (EditText)findViewById(R.id.txtPwd);
        // Se connecter à l'activité de création de compte
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Inscription.class);
                startActivity(intent);
            }
        });
        // Se connecter à son compte
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
        if ((txtLogin.getText().length()==0)||(txtPwd.getText().length()==0)){
            Toast.makeText(this,"certain(s) champ(s) sont vides veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
            txtLogin.getText().clear();
            txtPwd.getText().clear();

        }
        else{
            Toast.makeText(this,"bravo vos paramètres de connexion sont bons",Toast.LENGTH_LONG).show();
            /*String login = txtLogin.getText().toString();
            String password = txtPwd.getText().toString();*/

        }
    }
}
