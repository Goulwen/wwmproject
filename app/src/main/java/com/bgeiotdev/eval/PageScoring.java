package com.bgeiotdev.eval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bgeiotdev.eval.data.AccountManager;

public class PageScoring extends AppCompatActivity implements UserAdapter.ListItemClickListener {
    private UserAdapter mAdapter;
    private RecyclerView mNumbersList;

    private static final int NUM_LIST_ITEMS = 10;

    private AccountManager mBd;

    private Toast mToast;

    private  String strNom;
    private  String strPrenom;
    private  String strEmail;
    private int intScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_scoring);

        mBd = AccountManager.getInstance(getApplicationContext());

        final Intent startingIntent  = getIntent();
        strNom = startingIntent.getStringExtra(NewHoldUser.NOM_KEY);
        strPrenom = startingIntent.getStringExtra(NewHoldUser.PRENOM_KEY);
        strEmail = startingIntent.getStringExtra(NewHoldUser.EMAIL_KEY);
        intScore = mBd.UserDao().getUserScore(strNom, strPrenom, strEmail);

        mNumbersList = (RecyclerView) findViewById(R.id.afficheScoreJoueurs);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);

        mNumbersList.setHasFixedSize(true);

        mAdapter = new UserAdapter(NUM_LIST_ITEMS,this, mBd, strNom, strPrenom, strEmail, intScore);
        mNumbersList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_refresh:
                mAdapter = new UserAdapter(NUM_LIST_ITEMS, this, mBd, strNom, strPrenom, strEmail, intScore);
                mNumbersList.setAdapter(mAdapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

    public void onGoBackButtonClicked(View view) {
        finish();
    }
}
