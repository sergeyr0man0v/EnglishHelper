package com.example.englishhelper1.rest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.activities.MainActivity;
import com.example.englishhelper1.models.Module;
import com.example.englishhelper1.models.Section;
import com.example.englishhelper1.models.Word;
import com.example.englishhelper1.localDb.OpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerApiVolley implements ServerApi{

    public static final String BASE_URL = "https://raw.githubusercontent.com/sergeyr0man0v/EnglishHelperData/main/";
    public static final String TEST = "TEST";

    private final Context context;

    RequestQueue requestQueue;

    Response.ErrorListener errorListener;


    public ServerApiVolley(Context context) {
        this.context = context;

        requestQueue = Volley.newRequestQueue(context);

        errorListener = error -> Log.d("DATA_ERROR", error.toString());
    }

    @Override
    public void fillGrade(int grade) {
        String url = BASE_URL + "grade_" + grade + ".json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ExternalData.modules.clear();
                        ExternalData.sections.clear();
                        ExternalData.words.clear();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject module1 = response.getJSONObject(i);

                                Module module = new Module(
                                        i + 1,
                                        module1.getString("name"),
                                        module1.getString("description"),
                                        0
                                );

                                ExternalData.modules.add(module);
                                Log.d("TEST_API", String.valueOf(ExternalData.modules.size()));

                                JSONArray sections = module1.getJSONArray("sections");

                                for (int j = 0; j < sections.length(); j++) {

                                    JSONObject section = sections.getJSONObject(j);

                                    int sectionId = ExternalData.sections.size() + 1;

                                    Section section1 = new Section(
                                            sectionId,
                                            section.getString("name"),
                                            i + 1,
                                            section.getString("description")
                                    );

                                    ExternalData.sections.add(section1);

                                    JSONArray words = section.getJSONArray("words");

                                    for (int k = 0; k < words.length(); k++) {
                                        JSONObject word = words.getJSONObject(k);

                                        Word word1 = new Word(
                                                word.getString("eng"),
                                                word.getString("rus"),
                                                sectionId
                                        );

                                        ExternalData.words.add(word1);

                                    }

                                }
                            }


                            /*Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    OpenHelper openHelper = new OpenHelper(context);
                                    openHelper.fillLocalDb();
                                }
                            });
                            thread.start();*/

                            LocalDbTask localDbTask = new LocalDbTask();
                            localDbTask.execute();
                            //MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);
    }
    class LocalDbTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            OpenHelper openHelper = new OpenHelper(context);
            openHelper.fillLocalDb();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

    /*@Override
    public void fillGrade(int grade) {

        String url = BASE_URL + "/grade" + grade + "/description.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ExternalData.modules.clear();
                        ExternalData.sections.clear();
                        ExternalData.words.clear();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                int modules = jsonObject.getInt("modules");

                                for (int j = 1; j <= modules ; j++) {
                                    fillModule(grade, j);
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                errorListener
        );
        requestQueue.add(arrayRequest);
    }

    @Override
    public void fillModule(int grade, int moduleNumber) {
        //int grade = StartActivity.mySettings.getInt(MyPreferences.APP_PREFERENCES_GRADE, 11);


        String url = BASE_URL + "/grade" + grade + "/Module" + moduleNumber + "/description.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                ModuleDescription moduleDescription = new ModuleDescription(
                                        jsonObject.getString("name"),
                                        jsonObject.getString("description"),
                                        jsonObject.getInt("sections")
                                );

                                ExternalData.modules.add(ModuleMapper.moduleFromModuleDescriptionTest(moduleDescription, i + 1));

                                for (int j = 1; j <= moduleDescription.getSections() ; j++) {
                                    fillSection(grade, moduleNumber, j);
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                errorListener
        );
        requestQueue.add(arrayRequest);
    }

    @Override
    public void fillSection(int grade, int moduleNumber, int sectionNumber) {
        String url = BASE_URL + "/grade" + grade + "/Module" + moduleNumber + "/Section" + sectionNumber + ".json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                            SectionDescription sectionDescription = new SectionDescription(
                                    jsonObject1.getString("name"),
                                    jsonObject1.getString("description")
                            );
                            int sectionId = ExternalData.sections.size() + 1;
                            Section section = SectionMapper.sectionFromSectionDescriptionTest(sectionDescription, sectionId, moduleNumber);

                            ExternalData.sections.add(section);

                            JSONArray response = jsonObject1.getJSONArray("words");

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                Word word = new Word(
                                        jsonObject.getString("eng"),
                                        jsonObject.getString("rus"),
                                        sectionId
                                );
                                ExternalData.words.add(word);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                errorListener
        );
        requestQueue.add(arrayRequest);
    }

    @Override
    public void fillWord() {

    }*/
}
