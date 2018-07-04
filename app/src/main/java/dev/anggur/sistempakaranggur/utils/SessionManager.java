package dev.anggur.sistempakaranggur.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import dev.anggur.sistempakaranggur.activity.DiagnosaActivity;
import dev.anggur.sistempakaranggur.activity.LoginActivity;

/**
 * Created by Imam Abu Mansur on 04/07/2018.
 */

public class SessionManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static final String KEY_NAMA = "KEY_NAMA";
    public static final String KEY_EMAIL = "KEY_EMAIL";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String is_login = "login";
    private final String SHARE_NAME = "loginsession";
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    //Masukkan nilai yang ingin disimpan
    public void storeLogin(String nama, String email, String token){
        editor.putBoolean(is_login,true);
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
        Intent intent = new Intent(context, DiagnosaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public HashMap<String,String> getDetaiLogin(){
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_NAMA,preferences.getString(KEY_NAMA,null));
        map.put(KEY_EMAIL,preferences.getString(KEY_EMAIL,null));
        map.put(KEY_TOKEN,preferences.getString(KEY_TOKEN,null));
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
