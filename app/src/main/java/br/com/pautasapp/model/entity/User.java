package br.com.pautasapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static br.com.pautasapp.model.Constants.DB.COLUMN_EMAIL;
import static br.com.pautasapp.model.Constants.DB.COLUMN_ID;
import static br.com.pautasapp.model.Constants.DB.COLUMN_NAME;
import static br.com.pautasapp.model.Constants.DB.COLUMN_PASSWORD;
import static br.com.pautasapp.model.Constants.DB.TABLE_USER;

@Entity(tableName = TABLE_USER)
public class User {

    @ColumnInfo(name = COLUMN_ID)
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    @ColumnInfo(name = COLUMN_EMAIL)
    private String email;

    @ColumnInfo(name = COLUMN_PASSWORD)
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name;
    }

}
