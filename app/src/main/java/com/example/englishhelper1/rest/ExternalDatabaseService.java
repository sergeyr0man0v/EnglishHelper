package com.example.englishhelper1.rest;

import com.example.englishhelper1.rest.models.GradeDescription;
import com.example.englishhelper1.rest.models.ModuleDescription;
import com.example.englishhelper1.rest.models.SectionDescription;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ExternalDatabaseService {

    @GET("grade{grade_num}/description.json")
    Call<GradeDescription> getGrade(@Path("grade_num") int grade);

    @GET("grade{grade_num}/Module{module_num}/description.json")
    Call<ModuleDescription> getModule(@Path("grade_num") int grade,
                                      @Path("module_num") int module
    );

    @GET("grade{grade_num}/Module{module_num}/Section{section_num}.json")
    Call<SectionDescription> getSection(@Path("grade_num") int grade,
                                        @Path("module_num") int module,
                                        @Path("section_num") int section);

}
