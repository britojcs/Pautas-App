package br.com.pautasapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import br.com.pautasapp.R;
import br.com.pautasapp.model.Preferences;
import br.com.pautasapp.model.entity.User;
import br.com.pautasapp.view.base.BaseActivity;
import br.com.pautasapp.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.OnClick;

import static br.com.pautasapp.model.Constants.PARAMS.PARAM1;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edtEmail)
    TextInputEditText edtEmail;
    @BindView(R.id.edtPassword)
    TextInputEditText edtPassword;

    private UserViewModel userViewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @OnClick(R.id.btn_login)
    public void loginOnClick() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        boolean valid = true;
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

        User user = userViewModel.getUserByEmail(email);

        if (user == null)
            showToastMessage(getString(R.string.user_does_not_register));
        else if (!user.getPassword().equals(password))
            showToastMessage(getString(R.string.invalid_password));
        else {
            Preferences.setUserLogged(this, user.getId(), email);
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @OnClick(R.id.txtRegister)
    public void registerOnClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra(PARAM1, edtEmail.getText().toString().trim());
        startActivity(intent);
    }

}
