package com.example.englishhelper1.rest;

import com.example.englishhelper1.domain.Module;
import com.example.englishhelper1.domain.Section;
import com.example.englishhelper1.domain.Word;

import java.util.ArrayList;

public class ExternalData {

    public static final ArrayList<Module> modules = new ArrayList<>();
    public static final ArrayList<Section> sections = new ArrayList<>();
    public static final ArrayList<Word> words = new ArrayList<>();




    /*public static final String EXTERNAL_DATA_TEST = "EXTERNAL_DATA_TEST";
    public static final String BASE_URL = "https://raw.githubusercontent.com/sergeyr0man0v/EnglishHelperData/main/";*/

    /*public static void getDataFromExternalDb() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ExternalDatabaseService service = retrofit.create(ExternalDatabaseService.class);

        int grade = 11;
        //MainActivity.mySettings.getInt(MyPreferences.APP_PREFERENCES_GRADE, 11);
        Call<GradeDescription> grade1 = service.getGrade(grade);


        *//*try {
            Response<GradeDescription> gradeDescription = grade1.execute();
            numberOfModules = gradeDescription.body().getModules();
            Log.d(EXTERNAL_DATA_TEST, String.valueOf(ExternalData.numberOfModules));

        } catch (IOException e) {
            e.printStackTrace();
        }

        *//**//*try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*//**//*

        for (int i = 1; i <= numberOfModules; i++) {
            Call<ModuleDescription> moduleDescriptionCall = service.getModule(grade, i);

            Response<ModuleDescription> module = null;
            try {
                module = moduleDescriptionCall.execute();
                moduleDescriptions.add(module.body());;
                //Log.d(EXTERNAL_DATA_TEST, String.valueOf(module.body().getSections()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        *//**//*try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*//**//*
        int sectionId = 0;
        for (int i = 1; i <= moduleDescriptions.size(); i++) {
            for (int j = 1; i <= moduleDescriptions.get(i - 1).getSections(); j++) {
                sectionId++;
                Call<SectionDescription> sectionDescriptionCall = service.getSection(grade, i, j);
                Response<SectionDescription> sectionDescriptionResponse = null;
                try {
                    sectionDescriptionResponse = sectionDescriptionCall.execute();

                    SectionDescription sectionDescription = sectionDescriptionResponse.body();

                    Section section = SectionMapper.sectionFromSectionDescriptionTest(sectionDescription, sectionId, i);
                    sections.add(section);

                    *//**//*try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*//**//*

                    for (ServerWord serverWord : sectionDescription.words) {
                        Word word = WordMapper.wordFromServerWord(serverWord, sectionId);
                        words.add(word);
                    }

                    //Log.d(EXTERNAL_DATA_TEST, String.valueOf(section.body().name));
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        }*//*


        *//*grade1.enqueue(new Callback<GradeDescription>() {
            @Override
            public void onResponse(Call<GradeDescription> call, Response<GradeDescription> response) {
                if (response.isSuccessful()) {

                    int mod = response.body().getModules();
                    ExternalData.numberOfModules = mod;


                    for (int i = 1; i <= numberOfModules; i++) {
                        Call<ModuleDescription> moduleDescriptionCall = service.getModule(grade, i);
                        int moduleId = i;

                        moduleDescriptionCall.enqueue(new Callback<ModuleDescription>() {
                            @Override
                            public void onResponse(Call<ModuleDescription> call, Response<ModuleDescription> response) {
                                Log.d(EXTERNAL_DATA_TEST, response.toString());
                                ModuleDescription moduleDescription = response.body();
                                Module module = ModuleMapper.moduleFromModuleDescriptionTest(moduleDescription, moduleId);
                                modules.add(module);

                                int sectionId = 0;

                                for (int j = 1; j <= moduleDescription.sections; j++) {
                                    Call<SectionDescription> sectionDescriptionCall = service.getSection(grade, moduleId, j);
                                    sectionId++;
                                    int finalSectionId = sectionId;
                                    sectionDescriptionCall.enqueue(new Callback<SectionDescription>() {
                                        @Override
                                        public void onResponse(Call<SectionDescription> call, Response<SectionDescription> response) {
                                            Log.d(EXTERNAL_DATA_TEST, response.toString());
                                            SectionDescription sectionDescription = response.body();
                                            Section section = SectionMapper.sectionFromSectionDescriptionTest(sectionDescription, finalSectionId, moduleId);
                                            sections.add(section);
                                            if (sectionDescription.words != null)
                                                for (ServerWord serverWord: sectionDescription.words) {
                                                    Word word = WordMapper.wordFromServerWord(serverWord, finalSectionId);
                                                    words.add(word);
                                                }
                                            Log.d(EXTERNAL_DATA_TEST, String.valueOf(words.size()));
                                            Log.d(EXTERNAL_DATA_TEST, String.valueOf(sections.size()));
                                        }

                                        @Override
                                        public void onFailure(Call<SectionDescription> call, Throwable t) {
                                            Log.d(EXTERNAL_DATA_TEST, t.toString());
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<ModuleDescription> call, Throwable t) {
                                Log.d(EXTERNAL_DATA_TEST, t.toString());
                            }
                        });
                    }
                }
                Log.d(EXTERNAL_DATA_TEST, response.toString());
            }

            @Override
            public void onFailure(Call<GradeDescription> call, Throwable t) {
                Log.d(EXTERNAL_DATA_TEST, t.toString());
            }
        });*//*
    }*/

}
