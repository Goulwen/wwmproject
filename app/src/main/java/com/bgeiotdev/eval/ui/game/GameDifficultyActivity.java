package com.bgeiotdev.eval.ui.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.bgeiotdev.eval.R;
import com.bgeiotdev.eval.data.bdd.AccountManager;
import com.bgeiotdev.eval.ui.orientationJeuScore.NewHoldUserActivity;

public class GameDifficultyActivity extends AppCompatActivity {
    private int selectedDifficulty = 0;

    public static final String NOM_KEY = "nom";
    public static final String PRENOM_KEY = "prenom";
    public static final String EMAIL_KEY = "email";
    public static final String SCORE_KEY = "score";

    private AccountManager mBd;
    private  String strNom;
    private  String strPrenom;
    private  String strEmail;
    private int intScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_difficulty);

        mBd = AccountManager.getInstance(getApplicationContext());
    }

    public void onGoBackButtonClicked(View view) {
        finish();
    }

    /**
     * Pour connaitre quelle difficulté a été sélectionnée
     * @param view
     */
    public void onDifficultyRadioButtonsClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonEasy:
                if (checked) {
                    selectedDifficulty = 0;
                }
                break;
            case R.id.radioButtonNormal:
                if (checked) {
                    selectedDifficulty = 1;
                }
                break;
            case R.id.radioButtonHard:
                if (checked) {
                    selectedDifficulty = 2;
                }
                break;
        }
    }

    /**
     * On démarre la nouvelle activité avec la difficulté choisie
     * @param view
     */
    public void onStartGameButtonClicked(View view) {
        final Intent startingIntent  = getIntent();
        strNom = startingIntent.getStringExtra(NewHoldUserActivity.NOM_KEY);
        strPrenom = startingIntent.getStringExtra(NewHoldUserActivity.PRENOM_KEY);
        strEmail = startingIntent.getStringExtra(NewHoldUserActivity.EMAIL_KEY);
        intScore = mBd.UserDao().getUserScore(strNom, strPrenom, strEmail);

        Intent intent = new Intent(GameDifficultyActivity.this, GameActivity.class);
        intent.putExtra("difficulty", selectedDifficulty);
        intent.putExtra(NOM_KEY, strNom);
        intent.putExtra(PRENOM_KEY, strPrenom);
        intent.putExtra(EMAIL_KEY, strEmail);
        intent.putExtra(SCORE_KEY, intScore);
        startActivity(intent);
    }
}
