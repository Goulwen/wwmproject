package com.bgeiotdev.eval.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName= "user")
public class User {
    @NonNull
    @ColumnInfo(name = "nom")
    private String mNom;
    @NonNull
    @ColumnInfo(name = "prenom")
    private String mPrenom;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String mEmail;
    @NonNull
    @ColumnInfo(name = "score")
    private int mScore;

    @Ignore
    /**
     * Constructor avec 3 paramètres pas pris en compte
     * lors de l'insertion dans la base de donnée
     * @param nom obligatoire
     * @param prenom obligatoire
     * @param email obligatoire
     */
    public User(String nom, String prenom, String email) {
        mNom = nom;
        mPrenom = prenom;
        mEmail = email;
        mScore = 0;
    }

    /**
     * Constructor avec 4 paramètres qui sera pris en compte
     * lors de l'insertion dans la base de donnée
     * @param nom obligatoire
     * @param prenom obligatoire
     * @param email obligatoire
     * @param score obligatoire
     */
    public User(String nom, String prenom, String email, int score) {
        mNom = nom;
        mPrenom = prenom;
        mEmail = email;
        mScore = score;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

    public String getPrenom() {
        return mPrenom;
    }

    public void setPrenom(String prenom) {
        mPrenom = prenom;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}
