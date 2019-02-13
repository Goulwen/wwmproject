package com.bgeiotdev.eval.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user ORDER BY score ASC")
    LiveData<List<User>> getAllUser();

    @Insert
    void insertUser(User user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT count(*) FROM user WHERE (nom = :mNom AND prenom = :mPrenom AND email = :mEmail)")
    int getUserCount(String mNom, String mPrenom, String mEmail);

    @Query("SELECT nom FROM user WHERE nom = :mNom")
    String getUserNom(String mNom);

    @Query("SELECT prenom FROM user WHERE prenom = :mPrenom")
    String getUserPrenom(String mPrenom);

    @Query("SELECT email FROM user WHERE email = :mEmail")
    String getUserEmail(String mEmail);

    @Query("SELECT score FROM user WHERE (nom = :mNom AND prenom = :mPrenom AND email = :mEmail)")
    int getUserScore(String mNom, String mPrenom, String mEmail);
}
