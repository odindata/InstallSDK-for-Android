package com.odin.install.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class OdinSpUtil {

    /**
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getString(key, "");
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putString(key, value).commit();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getInt(key, 0);
    }


    public static long getLong(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        return settings.getLong(key, 0);
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setInt(Context context, String key, int value) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putInt(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setLong(Context context, String key, long value) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putLong(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit = defaultSharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String kay) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean aBoolean = defaultSharedPreferences.getBoolean(kay, false);
        return aBoolean;
    }

    /**
     * 清除默认的Preference中的内容
     *
     * @param context
     */
    public static void clearPreference(Context context) {
        SharedPreferences p = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }

    public static void clearPreferenceContent(Context context, String key) {
        SharedPreferences p = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor editor = p.edit();
        editor.remove(key);
        editor.commit();
    }
}