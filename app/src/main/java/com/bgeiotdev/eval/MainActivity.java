package com.bgeiotdev.eval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bgeiotdev.eval.data.AccountManager;
import com.bgeiotdev.eval.data.User;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Message debug : ";

    public static final String NOM_KEY = "nom";
    public static final String PRENOM_KEY = "prenom";
    public static final String EMAIL_KEY = "email";
    public static final String SCORE_KEY = "score";

    private EditText mNom;
    private EditText mPrenom;
    private EditText mEmail;

    private int score = 0;

    private Button mConfirmUser;

    private AccountManager mBd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBd = AccountManager.getInstance(getApplicationContext());
        // Je récupère les attributs qui seront utilisés
        initViews();

        // Je mets une écoute sur le bouton quand l'utilisateur clique dessus
        mConfirmUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Je récupère les valeurs mis dans les champs
                String verifMnom = mNom.getText().toString();
                String verifMprenom = mPrenom.getText().toString();
                String verifMemail = mEmail.getText().toString();

                // Je récupère les données de la bdd
                String emailbdd = mBd.UserDao().getUserEmail(verifMemail);
                String nombdd = mBd.UserDao().getUserNom(verifMnom);
                String prenombdd = mBd.UserDao().getUserPrenom(verifMprenom);

                // Vérification que tous les champs soient remplis
                if (verifMnom.matches("") || verifMprenom.matches("") || verifMemail.matches("")) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Vérification par email (primary key BDD) que bon utilisateur si existe déjà
                else if (verifMemail.equals(emailbdd) && ((!verifMnom.equals(nombdd)) || (!verifMprenom.equals(prenombdd)))) {
                    Log.d(TAG, "Verif compte, ok");
                    Toast.makeText(MainActivity.this, "Erreur de champs car email deja pris", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    User user = new User(verifMnom, verifMprenom, verifMemail);


                    Log.d(TAG, "Compte verifié, ok");

                    // Si nouvel utilisateur je le rentre dans la BDD
                    int count = mBd.UserDao().getUserCount(verifMnom, verifMprenom, verifMemail);
                    if (count <= 0) {
                        Log.d(TAG, "Nouvel utilisateur, ok : "+ count );
                        // Si nouvel utilisateur, je le rentre dans la BDD
                        mBd.UserDao().insertUser(user);
                    }
                    // Si ancien utilisateur, je récupère son score
                    else {
                        Log.d(TAG, "Ancien utilisateur, ok : " + count);
                        score = mBd.UserDao().getUserScore(verifMnom, verifMprenom, verifMemail);
                    }

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

    /**
     * initViews est appelée de onCreate pour initialiser
     * les variables qui sont dans le layout de activity_main.xml
     */
    private void initViews() {
        mNom = (EditText) findViewById(R.id.nomUser);
        mPrenom = (EditText) findViewById(R.id.prenomUser);
        mEmail = (EditText) findViewById(R.id.emailUser);
        mConfirmUser = (Button) findViewById(R.id.confirmUser);
    }
}
