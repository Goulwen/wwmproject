package com.bgeiotdev.eval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class GameDifficultyActivity extends AppCompatActivity {
    private int selectedDifficulty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_difficulty);
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
        Intent intent = new Intent(GameDifficultyActivity.this, GameActivity.class);
        intent.putExtra("difficulty", selectedDifficulty);
        startActivity(intent);
    }
}
