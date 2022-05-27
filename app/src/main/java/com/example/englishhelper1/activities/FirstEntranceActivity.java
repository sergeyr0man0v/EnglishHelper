package com.example.englishhelper1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.rest.ServerApiVolley;

public class FirstEntranceActivity extends AppCompatActivity {

    public static int lastSemesterMark;
    ImageButton[] marksBtn;
    ImageButton next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_entrance_activity);



        ///////////////////////////////////////////////
        /////// спинер для выбора класса
        ///////////////////////////////////////////////
        // Получаем экземпляр элемента Spinner
        Spinner spinner = findViewById(R.id.first_entrance_activity_spinner);

        // Настраиваем адаптер
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.grades,
                        R.layout.first_entrance_activivty_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Вызываем адаптер
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                int[] choose = getResources().getIntArray(R.array.grades);
                MyPreferences.settingEditor.putInt(MyPreferences.APP_PREFERENCES_GRADE, selectedItemPosition + 1);
                MyPreferences.settingEditor.apply();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                MyPreferences.settingEditor.putInt(MyPreferences.APP_PREFERENCES_GRADE, 11);
                MyPreferences.settingEditor.apply();
            }
        });

        ///////////////////////////////////////////////
        /////// подключение кнопок
        ///////////////////////////////////////////////

        next_btn = (ImageButton) findViewById(R.id.first_entrance_activity_next_btn);

        ServerApiVolley serverApiVolley = new ServerApiVolley(this);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverApiVolley.fillGrade(MyPreferences.mySettings.getInt(
                        MyPreferences.APP_PREFERENCES_GRADE, 11));
                Intent intent = new Intent(FirstEntranceActivity.this, SettingsActivity.class);
                //intent.putExtra(MainActivity.currentAct, MainActivity.class);
                startActivity(intent);
            }
        });



        marksBtn = new ImageButton[4];

        marksBtn[0] = findViewById(R.id.first_entrance_activity_btn2);
        marksBtn[1] = findViewById(R.id.first_entrance_activity_btn3);
        marksBtn[2] = findViewById(R.id.first_entrance_activity_btn4);
        marksBtn[3] = findViewById(R.id.first_entrance_activity_btn5);

        class MarkChooseListener implements View.OnClickListener {
            private final int mark;

            public MarkChooseListener(int mark){
                this.mark = mark;
            }
            @Override
            public void onClick(View view) {
                showToast(mark);
                lastSemesterMark = mark;
            }
        }

        for (int i = 0; i < marksBtn.length; i++){
            marksBtn[i].setOnClickListener(new MarkChooseListener(i + 2));
        }


    }

    ///////////////////////////////////////////////
    /////// метод для отображение тоста с характерным смайлом
    ///////////////////////////////////////////////
    public void showToast(int btnId) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        Toast toast = new Toast(getApplicationContext());
        ImageView img = (ImageView) layout.findViewById(R.id.custom_toast_img);
        //img.setImageResource(R.drawable.ic_custom_toast3);
        switch (btnId){
            case 2:
                img.setImageResource(R.drawable.ic_custom_toast2);
                break;
            case 3:
                img.setImageResource(R.drawable.ic_custom_toast3);
                break;
            case 4:
                img.setImageResource(R.drawable.ic_custom_toast4);
                break;
            case 5:
                img.setImageResource(R.drawable.ic_custom_toast5);
                break;

        }
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();


        /*Toast toast3 = Toast.makeText(getApplicationContext(),
                R.string.catfood, Toast.LENGTH_LONG);
        toast3.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastContainer = (LinearLayout) toast3.getView();
        ImageView catImageView = new ImageView(getApplicationContext());
        catImageView.setImageResource(R.drawable.hungrycat);
        toastContainer.addView(catImageView, 0);
        toast3.show();*/
    }

}
