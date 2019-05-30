package br.com.pautasapp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.pautasapp.R;
import br.com.pautasapp.model.entity.Note;
import br.com.pautasapp.view.activity.NoteActivity;
import br.com.pautasapp.view.adapter.NoteAdapter;
import br.com.pautasapp.view.base.BaseFragment;
import br.com.pautasapp.viewmodel.NoteViewModel;
import butterknife.BindView;

import static br.com.pautasapp.model.Constants.DB.COLUMN_DETAILS;
import static br.com.pautasapp.model.Constants.DB.COLUMN_ID_USER;
import static br.com.pautasapp.model.Constants.DB.COLUMN_SHORT_DESCRIPTION;
import static br.com.pautasapp.model.Constants.DB.COLUMN_TITLE;
import static br.com.pautasapp.model.Constants.PARAMS.PARAM1;

public class NoteFragment extends BaseFragment {

    private static final int ADD_NOTE_REQUEST = 100;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private NoteViewModel noteViewModel;
    private boolean openNotes;

    public static NoteFragment newInstance(boolean openNotes) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putBoolean(PARAM1, openNotes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openNotes = getArguments().getBoolean(PARAM1, true);
        setHasOptionsMenu(openNotes);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (openNotes)
            inflater.inflate(R.menu.add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_action) {
            Intent intent = new Intent(getActivity(), NoteActivity.class);
            startActivityForResult(intent, ADD_NOTE_REQUEST);
        }
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_note;
    }

    @Override
    protected void onPostCreate() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter(openNotes);
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        if (openNotes)
            noteViewModel.getAllNotesOpen().observe(this, adapter::submitList);
        else
            noteViewModel.getAllNotesFinalized().observe(this, adapter::submitList);

        adapter.setStatusOnClickListener(note -> {
            note.setOpen(!openNotes);
            noteViewModel.update(note);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            int idUser = data.getIntExtra(COLUMN_ID_USER, 0);
            String title = data.getStringExtra(COLUMN_TITLE);
            String shortDescription = data.getStringExtra(COLUMN_SHORT_DESCRIPTION);
            String details = data.getStringExtra(COLUMN_DETAILS);

            Note note = new Note(idUser, title, shortDescription, details, true);
            noteViewModel.insert(note);
            Toast.makeText(getContext(), R.string.saved_note, Toast.LENGTH_LONG).show();
        }
    }

}
