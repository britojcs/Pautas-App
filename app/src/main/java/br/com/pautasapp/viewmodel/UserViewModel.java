package br.com.pautasapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import br.com.pautasapp.model.entity.User;
import br.com.pautasapp.model.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void createUser(User user) {
        userRepository.insert(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

}
