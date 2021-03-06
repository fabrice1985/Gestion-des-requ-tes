package com.example.myquery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Compte_Enseignant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte__enseignant);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.compte_enseignant,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.traiterparenseignant:
                Toast.makeText(this, "depot requête",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Compte_Enseignant.this,TraitementEnseignant.class);
                startActivity(intent);
                return true;
            case R.id.consulter:
                Toast.makeText(this, "consulter",Toast.LENGTH_LONG).show();
                return true;
            case R.id.deconnecter:
                Toast.makeText(this, "deconnecter",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(Compte_Enseignant.this,MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
