package com.example.englishhelper1;


import android.app.Application;
import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.example.englishhelper1.activities.SectionActivity;

import java.util.Locale;

public final class Speaker {

    private static TextToSpeech textToSpeech;

    public Speaker(Context context) {
        this.textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int initStatus) {
                if (initStatus == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(1.3f);
                    textToSpeech.setSpeechRate(0.7f);
                }
            }
        });;
    }

    public static void volume(String engValue){
        String utteranceId = String.valueOf(engValue.hashCode());
        textToSpeech.speak(engValue, TextToSpeech.QUEUE_FLUSH,null, utteranceId);
    }

    public void shutdown(){
        textToSpeech.shutdown();
    }


}
