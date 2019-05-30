package br.com.pautasapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import br.com.pautasapp.R;
import br.com.pautasapp.model.Preferences;
import br.com.pautasapp.model.entity.User;
import br.com.pautasapp.view.base.BaseToolbarActivity;
import br.com.pautasapp.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.OnClick;

import static br.com.pautasapp.model.Constants.DB.COLUMN_DETAILS;
import static br.com.pautasapp.model.Constants.DB.COLUMN_ID_USER;
import static br.com.pautasapp.model.Constants.DB.COLUMN_SHORT_DESCRIPTION;
import static br.com.pautasapp.model.Constants.DB.COLUMN_TITLE;

public class NoteActivity extends BaseToolbarActivity {

    @BindView(R.id.edtTitle)
    TextInputEditText edtTitle;
    @BindView(R.id.edtDescription)
    TextInputEditText edtDescription;
    @BindView(R.id.edtDetails)
    TextInputEditText edtDetails;
    @BindView(R.id.edtAuthor)
    TextInputEditText edtAuthor;
    @BindView(R.id.btnAdd)
    MaterialButton btnAdd;

    private User user;

    @Override
    protected int getLayout() {
        return R.layout.activity_note;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        showHomeAsUpEnabled();

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        try {
            String email = Preferences.getEmailUserLogged(this);
            user = userViewModel.getUserByEmail(email);
            edtAuthor.setText(user.getName().toUpperCase());
        } catch (Exception e) {
            finish();
            showToastMessage(e.getMessage());
        }

        btnAdd.setEnabled(false);
        edtTitle.addTextChangedListener(textWatcherCheckFieldsForEmptyValues);
        edtDescription.addTextChangedListener(textWatcherCheckFieldsForEmptyValues);
        edtDetails.addTextChangedListener(textWatcherCheckFieldsForEmptyValues);
    }

    @OnClick(R.id.btnAdd)
    public void btnAddOnClick() {
        Intent data = new Intent();
        data.putExtra(COLUMN_ID_USER, user.getId());
        data.putExtra(COLUMN_TITLE, edtTitle.getText().toString().trim());
        data.putExtra(COLUMN_SHORT_DESCRIPTION, edtDescription.getText().toString().trim());
        data.putExtra(COLUMN_DETAILS, edtDetails.getText().toString().trim());

        setResult(RESULT_OK, data);

        finish();
    }

    TextWatcher textWatcherCheckFieldsForEmptyValues = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String title = edtTitle.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();
            String details = edtDetails.getText().toString().trim();

            btnAdd.setEnabled(!title.isEmpty() && !description.isEmpty() && !details.isEmpty());
        }
    };

}
