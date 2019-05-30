package br.com.pautasapp.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.pautasapp.model.entity.Note;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note")
    void deleteAll();

    @Query("SELECT * FROM note WHERE id_user = :idUser ORDER BY id DESC")
    LiveData<List<Note>> getAll(int idUser);

    @Query("SELECT * FROM note WHERE id_user = :idUser AND open = :open ORDER BY id DESC")
    LiveData<List<Note>> getAllByOpen(int idUser, boolean open);
}
