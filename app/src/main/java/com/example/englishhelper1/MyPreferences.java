package com.example.englishhelper1;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.englishhelper1.activities.MainActivity;

public final class MyPreferences {

    public static final String APP_PREFERENCES = "appSettings";
    public static final String APP_PREFERENCES_GRADE = "Grade";
    public static final String APP_PREFERENCES_IS_NEW_USER = "IsNewUser";

    public static final String APP_PREFERENCES_LANGUAGE = "Language";
    public static final String APP_PREFERENCES_AUTO_VOLUME = "autoVolume";

    public static final String SELECTED_MODULE = "MODULE_NAME";
    public static final String SELECTED_SECTION = "SECTION_NAME";


    public static final SharedPreferences.Editor settingEditor = MainActivity.mySettings.edit();
}
