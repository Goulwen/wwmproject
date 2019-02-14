package com.bgeiotdev.eval;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.bgeiotdev.eval.data.bdd.AccountManager;
import com.bgeiotdev.eval.data.bdd.User;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<User>> listeUserComplete;

    public MainViewModel(Application application) {
        super(application);
        AccountManager database = AccountManager.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        listeUserComplete = database.UserDao().getAllUser();
    }

    public LiveData<List<User>> getUsers() {
        return listeUserComplete;
    }
}
