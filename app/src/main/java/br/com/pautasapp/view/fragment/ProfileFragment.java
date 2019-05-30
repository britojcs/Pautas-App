package br.com.pautasapp.view.fragment;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import br.com.pautasapp.R;
import br.com.pautasapp.model.Preferences;
import br.com.pautasapp.model.entity.User;
import br.com.pautasapp.view.activity.LoginActivity;
import br.com.pautasapp.view.base.BaseFragment;
import br.com.pautasapp.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtEmail)
    TextView txtEmail;

    @Override
    protected int getLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onPostCreate() {
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        try {
            String email = Preferences.getEmailUserLogged(getContext());
            User user = userViewModel.getUserByEmail(email);

            txtName.setText(user.getName());
            txtEmail.setText(user.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.lytExit)
    void exitOnClick() {
        FragmentActivity activity = getActivity();

        Preferences.clearLoggedUser(activity);

        try {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);

            activity.finish();
        } catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
