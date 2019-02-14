package com.bgeiotdev.eval.ui.score;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bgeiotdev.eval.MainViewModel;
import com.bgeiotdev.eval.R;
import com.bgeiotdev.eval.UserAdapter;
import com.bgeiotdev.eval.data.bdd.AccountManager;
import com.bgeiotdev.eval.data.bdd.User;
import com.bgeiotdev.eval.ui.orientationJeuScore.NewHoldUserActivity;

import java.util.List;

public class PageScoringActivity extends AppCompatActivity implements UserAdapter.ListItemClickListener {

    private static final String TAG = PageScoringActivity.class.getSimpleName();
    private UserAdapter mAdapter;
    private RecyclerView mRecyclerView;

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
        strNom = startingIntent.getStringExtra(NewHoldUserActivity.NOM_KEY);
        strPrenom = startingIntent.getStringExtra(NewHoldUserActivity.PRENOM_KEY);
        strEmail = startingIntent.getStringExtra(NewHoldUserActivity.EMAIL_KEY);
        intScore = mBd.UserDao().getUserScore(strNom, strPrenom, strEmail);

        mRecyclerView = (RecyclerView) findViewById(R.id.afficheScoreJoueurs);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new UserAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        setupViewModel();
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
                mAdapter.notifyDataSetChanged();
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

    private void setupViewModel() {


        List<User> users =  mBd.UserDao().getAllUserNoLive();

        Log.d(TAG," size users " + users.size());
        mAdapter.setListUser(users);


        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                mAdapter.setListUser(users);
            }
        });
    }

    public void onGoBackButtonClicked(View view) {
        finish();
    }

}
