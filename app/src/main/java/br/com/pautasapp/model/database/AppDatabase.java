package br.com.pautasapp.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.pautasapp.model.Constants;
import br.com.pautasapp.model.dao.NoteDao;
import br.com.pautasapp.model.dao.UserDao;
import br.com.pautasapp.model.entity.Note;
import br.com.pautasapp.model.entity.User;

@Database(entities = {User.class, Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract NoteDao noteDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, Constants.DB.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
