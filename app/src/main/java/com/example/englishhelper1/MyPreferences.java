package com.example.englishhelper1;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.englishhelper1.activities.StartActivity;

public final class MyPreferences {

    Context context;
    public static SharedPreferences mySettings;

    public static SharedPreferences.Editor settingEditor;

    public static final String APP_PREFERENCES = "appSettings";
    public static final String APP_PREFERENCES_GRADE = "Grade";
    public static final String APP_PREFERENCES_IS_NEW_USER = "IsNewUser";

    public static final String APP_PREFERENCES_LANGUAGE = "Language";
    public static final String APP_PREFERENCES_AUTO_VOLUME = "autoVolume";

    public static final String SELECTED_MODULE = "MODULE_NAME";
    public static final String SELECTED_SECTION = "SECTION_NAME";

    public static final String RESULT_VALUE = "resultValue";
    public static final String WRONG_ANSWERS_ID = "wrongAnswersId";
    public static final String WRONG_ANSWERS = "wrongAnswers";

    public MyPreferences(Context context) {
        this.context = context;
        mySettings = context.getSharedPreferences(MyPreferences.APP_PREFERENCES,
                Context.MODE_PRIVATE);
        settingEditor = mySettings.edit();
    }
}
