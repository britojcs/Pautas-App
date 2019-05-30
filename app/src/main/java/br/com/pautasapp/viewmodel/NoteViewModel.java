package br.com.pautasapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.pautasapp.model.entity.Note;
import br.com.pautasapp.model.repository.NoteRepository;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotesOpen;
    private LiveData<List<Note>> allNotesFinalized;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotesOpen = repository.getAllNotesOpen();
        allNotesFinalized = repository.getAllNotesFinalized();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotesOpen() {
        return allNotesOpen;
    }

    public LiveData<List<Note>> getAllNotesFinalized() {
        return allNotesFinalized;
    }

}