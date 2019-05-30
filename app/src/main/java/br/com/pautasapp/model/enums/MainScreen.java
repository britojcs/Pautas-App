package br.com.pautasapp.model.enums;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

import br.com.pautasapp.R;
import br.com.pautasapp.view.fragment.NoteFragment;
import br.com.pautasapp.view.fragment.ProfileFragment;

public enum MainScreen {

    OPENED(R.id.bottom_navigation_item_opened, R.drawable.ic_note_add, R.string.opened, NoteFragment.newInstance(true)),
    FINALIZED(R.id.bottom_navigation_item_finalized, R.drawable.ic_note, R.string.finalized, NoteFragment.newInstance(false)),
    PROFILE(R.id.bottom_navigation_item_profile, R.drawable.ic_person, R.string.profile, new ProfileFragment());

    private final int idMenu;
    private final int idIcon;
    private final int title;
    private final Fragment fragment;

    MainScreen(int idMenu, int idIcon, int title, Fragment fragment) {
        this.idMenu = idMenu;
        this.idIcon = idIcon;
        this.title = title;
        this.fragment = fragment;
    }

    public static List<MainScreen> asList() {
        return Arrays.asList(MainScreen.values());
    }

    public static MainScreen getMainScreenForMenuItem(int menuItemId) {
        for (MainScreen mainScreen : MainScreen.values())
            if (mainScreen.idMenu == menuItemId)
                return mainScreen;

        return null;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public int getIdIcon() {
        return idIcon;
    }

    public int getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

}
