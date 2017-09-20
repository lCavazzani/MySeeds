package megaleios.com.myseeds.Util;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import megaleios.com.myseeds.Models.Auth;

/**
 * Created by MegaDev02 on 13/07/2017.
 */

public class SessionManager {
    private static final String PREF_NAME = "user";
    private final Context _context;
    private final Gson gson;
    private static final String USUARIO = "bearer";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        gson = new Gson();
    }

    public void createLoginSession(Auth user) {
        String json = gson.toJson(user);
        editor.putString(USUARIO, json);
        editor.commit();

    }

    public boolean checkLogin() {
        String json = pref.getString(USUARIO, "");

        if (json.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Auth getUsuario() {
        String json = pref.getString(USUARIO, "{}");
        Auth auth = gson.fromJson(json, Auth.class);
        return auth;
    }

    public void logoutUser() {
        editor.putString(USUARIO, null);

        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }
}
