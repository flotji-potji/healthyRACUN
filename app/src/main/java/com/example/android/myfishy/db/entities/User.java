package com.example.android.myfishy.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    private String username;

    private String firstname;

    private String surname;

    private Date birthday;

    private String gender;

    private float weight;

    private float height;

    public User(@NonNull String username, String firstname, String surname, Date birthday, String gender, float weight, float height) {
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }
}
