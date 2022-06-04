package com.example.englishhelper1.localDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.englishhelper1.activities.MainActivity;
import com.example.englishhelper1.activities.ModuleActivity;
import com.example.englishhelper1.models.Module;
import com.example.englishhelper1.models.Section;
import com.example.englishhelper1.models.Word;
import com.example.englishhelper1.rest.ExternalData;

import java.util.ArrayList;

public class OpenHelper extends SQLiteOpenHelper {

    // Данные базы данных и таблиц
    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 1;

    //Названия таблиц
    private static final String MODULES_TABLE = "modules";
    private static final String SECTIONS_TABLE = "sections";
    private static final String WORDS_TABLE = "words";

    // Название столбцов
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PROGRESS = "progress";

    private static final String COLUMN_MODULE_ID = "module_id";
    private static final String COLUMN_SECTION_ID = "section_id";

    private static final String COLUMN_ENG_VALUE = "eng_value";
    private static final String COLUMN_RUS_VALUE = "rus_value";
    private static final String COLUMN_IS_LEARNED = "is_learned";

    private SQLiteDatabase dbWriteable = this.getWritableDatabase();
    private SQLiteDatabase dbReadable = this.getReadableDatabase();

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryModule = "CREATE TABLE IF NOT EXISTS " + MODULES_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL UNIQUE, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PROGRESS + " INTEGER);";
        sqLiteDatabase.execSQL(queryModule);

        String querySection = "CREATE TABLE IF NOT EXISTS " + SECTIONS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PROGRESS + " INTEGER, " +
                COLUMN_MODULE_ID + " INTEGER  NOT NULL);";
        sqLiteDatabase.execSQL(querySection);

        String queryWord = "CREATE TABLE IF NOT EXISTS " + WORDS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENG_VALUE + " TEXT NOT NULL, " +
                COLUMN_RUS_VALUE + " TEXT NOT NULL, " +
                COLUMN_IS_LEARNED + " INTEGER, " +
                COLUMN_SECTION_ID + " INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(queryWord);
    }

    public void fillLocalDb(){
        for (Module module: ExternalData.modules) {
            insertModule(module);
        }
        ExternalData.modules.clear();

        for (Section section : ExternalData.sections){
            insertSection(section);
        }
        ExternalData.sections.clear();

        for (Word word : ExternalData.words){
            insertWord(word);
        }
        ExternalData.words.clear();
    }

    public boolean insertModule(Module module){

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, module.getName());
        contentValues.put(COLUMN_DESCRIPTION, module.getDescription());
        contentValues.put(COLUMN_PROGRESS, 0);

        dbWriteable.insert(MODULES_TABLE, null, contentValues);

        return true;
    }

    public boolean insertSection(Section section){

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, section.getName());
        contentValues.put(COLUMN_DESCRIPTION, section.getDescription());
        contentValues.put(COLUMN_PROGRESS, 0);
        contentValues.put(COLUMN_MODULE_ID, section.getModuleId());

        dbWriteable.insert(SECTIONS_TABLE, null, contentValues);
        return true;
    }

    public boolean insertWord(Word word){

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ENG_VALUE, word.getEngValue());
        contentValues.put(COLUMN_RUS_VALUE, word.getRuValue());
        contentValues.put(COLUMN_IS_LEARNED, 0);
        contentValues.put(COLUMN_SECTION_ID, word.getSectionId());

        dbWriteable.insert(WORDS_TABLE, null, contentValues);
        return true;
    }

    public ArrayList<Module> getModules(){
        Cursor cursor = dbReadable.rawQuery("SELECT * FROM " + MODULES_TABLE, null);
        //cursor.moveToFirst();
        ArrayList<Module> arrayList = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            int progress = cursor.getInt(cursor.getColumnIndex(COLUMN_PROGRESS));
            Module module = new Module(id, name, description, progress);
            arrayList.add(module);
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Section> getSectionsByModuleId(int moduleId){
        Cursor cursor = dbReadable.rawQuery("SELECT * FROM " + SECTIONS_TABLE + " WHERE " + COLUMN_MODULE_ID + " = " + moduleId, null);
        //cursor.moveToFirst();
        ArrayList<Section> arrayList = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            int progress = cursor.getInt(cursor.getColumnIndex(COLUMN_PROGRESS));
            Section section = new Section(id, name, description, progress);
            arrayList.add(section);
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Word> getWordsBySectionId(int sectionId){
        Cursor cursor = dbReadable.rawQuery("SELECT * FROM " + WORDS_TABLE + " WHERE " + COLUMN_SECTION_ID + " = " + sectionId, null);
        //cursor.moveToFirst();
        ArrayList<Word> arrayList = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String eng = cursor.getString(cursor.getColumnIndex(COLUMN_ENG_VALUE));
            String rus = cursor.getString(cursor.getColumnIndex(COLUMN_RUS_VALUE));
            boolean is_learned = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_LEARNED)) == 1;
            Word word = new Word(id, eng, rus, is_learned);
            arrayList.add(word);
        }
        cursor.close();
        return arrayList;
    }

    public int getNumberOfWords(int sectionId){
        Cursor cursor = dbReadable.rawQuery("SELECT COUNT(*) FROM " + WORDS_TABLE + " WHERE " + COLUMN_SECTION_ID + " = " + sectionId, null);
        cursor.moveToFirst();
        int counter = cursor.getInt(0);
        cursor.close();

        return counter;
    }

    public int getNumberOfLearnedWords(int sectionId){
        Cursor cursor = dbReadable.rawQuery("SELECT COUNT(*) FROM " + WORDS_TABLE + " WHERE " + COLUMN_SECTION_ID + " = " + sectionId + " AND " + COLUMN_IS_LEARNED + " = 1", null);
        cursor.moveToFirst();
        int counter = cursor.getInt(0);
        cursor.close();
        return counter;
    }

    public boolean updateModuleProgress(int moduleId, int newProgress){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PROGRESS, newProgress);
        dbWriteable.update(
                MODULES_TABLE,
                contentValues,
                COLUMN_ID + " = ?",
                new String[] {String.valueOf(moduleId)}
                );
        return true;
    }

    public boolean updateSectionProgress(int sectionId, int newProgress){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PROGRESS, newProgress);
        dbWriteable.update(
                SECTIONS_TABLE,
                contentValues,
                COLUMN_ID + " = ?",
                new String[] {String.valueOf(sectionId)}
        );
        Log.d("------------",String.valueOf(sectionId + "  " + newProgress));
        return true;
    }

    public boolean updateWordLearnedStatusBySectionId(int sectionId, boolean newLearnedStatus){
        ContentValues contentValues = new ContentValues();
        int value = newLearnedStatus ? 1 : 0;
        contentValues.put(COLUMN_IS_LEARNED, value);
        dbWriteable.update(
                WORDS_TABLE,
                contentValues,
                COLUMN_SECTION_ID + " = ?",
                new String[] {String.valueOf(sectionId)}
        );
        return true;
    }

    public boolean updateWordLearnedStatus(int wordId, boolean newLearnedStatus){
        ContentValues contentValues = new ContentValues();
        int value = newLearnedStatus ? 1 : 0;
        contentValues.put(COLUMN_IS_LEARNED, value);
        dbWriteable.update(
                WORDS_TABLE,
                contentValues,
                COLUMN_ID + " = ?",
                new String[] {String.valueOf(wordId)}
        );
        return true;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);*/
    }
}
