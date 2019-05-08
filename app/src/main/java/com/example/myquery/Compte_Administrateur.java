package com.example.myquery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Compte_Administrateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte__administrateur);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.compte_administrateur,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.consulter:
                Toast.makeText(this, "consulter",Toast.LENGTH_LONG).show();
                return true;
            case R.id.compte_etudiant:
                Toast.makeText(this, "Consultez compte étudiant",Toast.LENGTH_LONG).show();
                return true;
            case R.id.compte_enseignant:
                Toast.makeText(this, "Consultez compte enseignant",Toast.LENGTH_LONG).show();
                return true;
            case R.id.compte_DES:
                Toast.makeText(this, "Consulter compte DES",Toast.LENGTH_LONG).show();
                return true;
            case R.id.deconnecter:
                Toast.makeText(this, "deconnecter",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(Compte_Administrateur.this,MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.enregitrerenseignant:
                Toast.makeText(this, "Enregistrer enseignant",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Compte_Administrateur.this,EnregistrerEnseignant.class);
                //intent.putExtra("pseudo",pseudo);
                startActivity(intent);
                return true;
            case R.id.enregistrerDES:
                Intent intent1 = new Intent(Compte_Administrateur.this,EnregistrerDES.class);
                //intent1.putExtra("pseudo",pseudo);
                startActivity(intent1);
                Toast.makeText(this, "Enregistrer agent DES",Toast.LENGTH_LONG).show();
                return true;
            case R.id.supprimercompte:
                Toast.makeText(this, "Supprimer compte",Toast.LENGTH_LONG).show();
                return true;
            case R.id.modifiercompte:
                Toast.makeText(this, "Modifier compte",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
