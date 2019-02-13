package com.bgeiotdev.eval.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AccountManager extends RoomDatabase {
    private static final String LOG_TAG = AccountManager.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "user";

    private static AccountManager sInstance;

    public static AccountManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AccountManager.class, AccountManager.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }
    public abstract UserDao UserDao();
}