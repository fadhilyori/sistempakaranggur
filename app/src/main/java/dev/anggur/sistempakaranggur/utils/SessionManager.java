package dev.anggur.sistempakaranggur.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import dev.anggur.sistempakaranggur.activity.DiagnosaActivity;
import dev.anggur.sistempakaranggur.activity.LoginActivity;
import dev.anggur.sistempakaranggur.activity.MenuActivity;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class SessionManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static final String KEY_USERNAME = "KEY_USERNAME";
    public static final String KEY_LEVEL = "KEY_LEVEL";
    private static final String is_login = "login";
    private final String SHARE_NAME = "loginsession";
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    //Masukkan nilai yang ingin disimpan
    public void storeLogin(String username, String level){
        editor.putBoolean(is_login,true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_LEVEL, level);
        editor.commit();
        Intent intent = new Intent(context, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public HashMap<String,String> getDetaiLogin(){
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_USERNAME,preferences.getString(KEY_USERNAME,null));
        map.put(KEY_LEVEL,preferences.getString(KEY_LEVEL,null));
        return map;
    }

    public boolean isLogin(){
        return preferences.getBoolean(is_login,false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
