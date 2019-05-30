package br.com.pautasapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static br.com.pautasapp.model.Constants.DB.COLUMN_DETAILS;
import static br.com.pautasapp.model.Constants.DB.COLUMN_ID;
import static br.com.pautasapp.model.Constants.DB.COLUMN_ID_USER;
import static br.com.pautasapp.model.Constants.DB.COLUMN_OPEN;
import static br.com.pautasapp.model.Constants.DB.COLUMN_SHORT_DESCRIPTION;
import static br.com.pautasapp.model.Constants.DB.COLUMN_TITLE;
import static br.com.pautasapp.model.Constants.DB.TABLE_NOTE;

@Entity(tableName = TABLE_NOTE,
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = COLUMN_ID,
                childColumns = COLUMN_ID_USER))
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = COLUMN_ID_USER, index = true)
    private int idUser;

    @ColumnInfo(name = COLUMN_TITLE)
    private String title;

    @ColumnInfo(name = COLUMN_SHORT_DESCRIPTION)
    private String shortDescription;

    @ColumnInfo(name = COLUMN_DETAILS)
    private String details;

    @ColumnInfo(name = COLUMN_OPEN)
    private boolean open;

    public Note(int idUser, String title, String shortDescription, String details, boolean open) {
        this.idUser = idUser;
        this.title = title;
        this.shortDescription = shortDescription;
        this.details = details;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}