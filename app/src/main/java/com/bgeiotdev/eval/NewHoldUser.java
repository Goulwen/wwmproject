package com.bgeiotdev.eval;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bgeiotdev.eval.data.AccountManager;

import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class NewHoldUser extends AppCompatActivity {
    private TextView messageInfoBienvenue;
    public static final String NOM_KEY = "nom";
    public static final String PRENOM_KEY = "prenom";
    public static final String EMAIL_KEY = "email";
    public static final String SCORE_KEY = "score";

    private  String strNom;
    private  String strPrenom;
    private  String strEmail;
    private int intScore;

    private AccountManager mBd;

    private Button mScoring;
    private Button mNewGame;
    private Button mInstruc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hold_user);

        mBd = AccountManager.getInstance(getApplicationContext());
        intScore = mBd.UserDao().getUserScore(strNom, strPrenom, strEmail);

        messageInfoBienvenue = (TextView) findViewById(R.id.messageInfoBienvenue);

        final Intent startingIntent  = getIntent();
        messageInfoBienvenue.append("Bienvenue mon cher\n\n");
        if (startingIntent.hasExtra(MainActivity.NOM_KEY)){
            strNom = startingIntent.getStringExtra(MainActivity.NOM_KEY);
            if (strNom !=null){
                messageInfoBienvenue.append("nom :  " + strNom +"\n\n");
            }
        }

        if (startingIntent.hasExtra(MainActivity.PRENOM_KEY)){
            strPrenom = startingIntent.getStringExtra(MainActivity.PRENOM_KEY);
            if (strPrenom !=null){
                messageInfoBienvenue.append("Prénom :  " + strPrenom +"\n\n");
            }
        }

        if (startingIntent.hasExtra(MainActivity.EMAIL_KEY)){
            strEmail = startingIntent.getStringExtra(MainActivity.EMAIL_KEY);
            if (strEmail !=null){
                messageInfoBienvenue.append("Email :  " + strEmail +"\n\n");
                messageInfoBienvenue.append("Score :  " + intScore +"\n\n");
            }
        }

        // Pour aller sur les scores
        mScoring = (Button) findViewById(R.id.boutonScoring);
        mScoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageScoring = new Intent(NewHoldUser.this, PageScoring.class);
                pageScoring.putExtra(NOM_KEY, strNom);
                pageScoring.putExtra(PRENOM_KEY, strPrenom);
                pageScoring.putExtra(EMAIL_KEY, strEmail);
                pageScoring.putExtra(SCORE_KEY, intScore);
                startActivity(pageScoring);
            }
        });

        // Pour commencer une grille
        mNewGame = (Button) findViewById(R.id.buttonStartNewGame);
        mNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameActivity = new Intent(NewHoldUser.this, GameDifficultyActivity.class);
                startActivity(gameActivity);
            }
        });

        // Pour voir les règles du jeu
        mInstruc = (Button) findViewById(R.id.buttonShowInstructions);
        mInstruc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructActivity = new Intent(NewHoldUser.this, InstructionsActivity.class);
                startActivity(instructActivity);
            }
        });
    }
}
