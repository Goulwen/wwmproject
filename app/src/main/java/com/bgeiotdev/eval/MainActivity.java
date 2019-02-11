package com.bgeiotdev.eval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String NOM_KEY = "nom";
    private static final String PRENOM_KEY = "prenom";
    private static final String EMAIL_KEY = "email";
    private static final String SCORE_KEY = "score";

    private int score = 0;

    private EditText mNom;
    private EditText mPrenom;
    private EditText mEmail;

    private Button mConfirmUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Je récupère les attributs qui seront utilisés
        mNom = (EditText) findViewById(R.id.nomUser);
        mPrenom = (EditText) findViewById(R.id.prenomUser);
        mEmail = (EditText) findViewById(R.id.emailUser);
        mConfirmUser = (Button) findViewById(R.id.confirmUser);

        mConfirmUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Je récupère les valeurs mis dans les champs
                String verifMnom = mNom.getText().toString();
                String verifMprenom = mPrenom.getText().toString();
                String verifMemail = mEmail.getText().toString();

                // Vérification que tous les champs soient remplis
                if (verifMnom.matches("") || verifMprenom.matches("") || verifMemail.matches("")) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // Je vérifie si nouvel utilisateur ou existe déjà dans la BDD


                    // Si nouvel utilisateur, je le rentre dans la BDD


                    // Si ancien utilisateur, je récupère son score
                    // score = ?????


                    // J'ouvre une autre activité
                    Intent newHoldUserIntent = new Intent(MainActivity.this, NewHoldUser.class);
                    newHoldUserIntent.putExtra(NOM_KEY, verifMnom);
                    newHoldUserIntent.putExtra(PRENOM_KEY, verifMprenom);
                    newHoldUserIntent.putExtra(EMAIL_KEY, verifMemail);
                    newHoldUserIntent.putExtra(SCORE_KEY, score);
                    startActivity(newHoldUserIntent);
                }
            }
        });
    }
}
