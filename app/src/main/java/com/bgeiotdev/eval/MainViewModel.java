package com.bgeiotdev.eval;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.bgeiotdev.eval.data.bdd.AccountManager;
import com.bgeiotdev.eval.data.bdd.User;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<User>> listeUserComplete;

    public MainViewModel(Application application) {
        super(application);
        AccountManager database = AccountManager.getInstance(this.getApplication());
        listeUserComplete = database.UserDao().getAllUser();
    }

    public LiveData<List<User>> getUsers() {
        return listeUserComplete;
    }
}
