package com.example.myquery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Compte_Etudiant extends AppCompatActivity {

    String pseudo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte__etudiant);

        Intent  intent = getIntent();
        pseudo = intent.getStringExtra("pseudo");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.compte_etudiant,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.depotrequete:
                Toast.makeText(this, "depot requête",Toast.LENGTH_LONG).show();
                return true;
            case R.id.consulter:
                Toast.makeText(this, "consulter",Toast.LENGTH_LONG).show();
                return true;
            case R.id.deconnecter:
                Toast.makeText(this, "deconnecter",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(Compte_Etudiant.this,MainActivity.class);
                startActivity(intent2);
                return true;
           case R.id.note:
                Toast.makeText(this, "Notes",Toast.LENGTH_LONG).show();
               Intent intent = new Intent(Compte_Etudiant.this,RequetesNotes.class);
               intent.putExtra("pseudo",pseudo);
               startActivity(intent);
                return true;
            case R.id.note_examen:
                Toast.makeText(this, "Notes",Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(Compte_Etudiant.this,Notes_exam.class);
                intent3.putExtra("pseudo",pseudo);
                startActivity(intent3);
                return true;
            case R.id.autre:
                Intent intent1 = new Intent(Compte_Etudiant.this,AutreRequete.class);
                intent1.putExtra("pseudo",pseudo);
                startActivity(intent1);
                Toast.makeText(this, "Autres requêtes",Toast.LENGTH_LONG).show();
                return true;
            case R.id.requetedeposer:
                Toast.makeText(this, "requêtes déposées"+pseudo,Toast.LENGTH_LONG).show();
                return true;
            case R.id.requetetraiter:
                Toast.makeText(this, "requêtes traitées",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
