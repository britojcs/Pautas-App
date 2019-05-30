package br.com.pautasapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    private static final String KEY_ID_USER_LOGGED = "id_email_logged";
    private static final String KEY_EMAIL_USER_LOGGED = "user_email_logged";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserLogged(Context context, int id, String email) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(KEY_ID_USER_LOGGED, id);
        editor.putString(KEY_EMAIL_USER_LOGGED, email);
        editor.apply();
    }

    public static int getIdUserLogged(Context context) {
        return getSharedPreference(context).getInt(KEY_ID_USER_LOGGED, -1);
    }

    public static String getEmailUserLogged(Context context) {
        return getSharedPreference(context).getString(KEY_EMAIL_USER_LOGGED, null);
    }

    public static void clearLoggedUser(Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_EMAIL_USER_LOGGED);
        editor.apply();
    }

}
