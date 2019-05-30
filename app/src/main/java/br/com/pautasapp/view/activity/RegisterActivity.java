package br.com.pautasapp.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import br.com.pautasapp.R;
import br.com.pautasapp.model.entity.User;
import br.com.pautasapp.view.base.BaseActivity;
import br.com.pautasapp.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.OnClick;

import static br.com.pautasapp.model.Constants.PARAMS.PARAM1;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.edtName)
    TextInputEditText edtName;
    @BindView(R.id.edtEmail)
    TextInputEditText edtEmail;
    @BindView(R.id.edtPassword)
    TextInputEditText edtPassword;

    private UserViewModel userViewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        String email = getIntent().getStringExtra(PARAM1);
        if (email != null)
            edtEmail.setText(email);
    }

    @OnClick(R.id.btnRegister)
    public void registerOnClick() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        boolean valid = true;
        if (name.isEmpty()) {
            edtName.setError(getString(R.string.enter_name));
            valid = false;
        }
        if (email.isEmpty()) {
            edtEmail.setError(getString(R.string.enter_email));
            valid = false;
        }
        if (password.isEmpty()) {
            edtPassword.setError(getString(R.string.enter_password));
            valid = false;
        }

        if (!valid)
            return;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern))
            showToastMessage(getString(R.string.enter_valid_email));
        else {
            User user = userViewModel.getUserByEmail(email);
            if (user != null) {
                showToastMessage(getString(R.string.email_already_registeredl));
                return;
            }

            user = new User(name, email, password);
            userViewModel.createUser(user);
            finish();
        }
    }

}
