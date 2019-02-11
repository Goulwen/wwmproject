package com.bgeiotdev.eval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewHoldUser extends AppCompatActivity {
    private TextView messageInfoBienvenue;

    private  String strNom;
    private  String strPrenom;
    private  String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hold_user);

        messageInfoBienvenue = (TextView) findViewById(R.id.messageInfoBienvenue);
    }
}
