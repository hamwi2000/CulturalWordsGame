package com.barmej.culturalwords;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    int[] images = {
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_11,
            R.drawable.icon_12,
            R.drawable.icon_13
    };
    int mCurrentIndex = 0;
    private String[] ANSWER_DESC;
    private String mAD;

    private String[] answers;
    private String[] answer_description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("app pref", MODE_PRIVATE);
        String appLang = sharedPreferences.getString("app-lang", "");
        if (!appLang.equals(""))
            LocaleHelper.setLocale(this, appLang);

        setContentView(R.layout.activity_main);
        answers = getResources().getStringArray(R.array.answers);
        answer_description = getResources().getStringArray(R.array.answer_description);
        img = findViewById(R.id.image_view_question);

    }

    public void display(View view) {
        if (mCurrentIndex >= images.length) {
            mCurrentIndex = 0;
        }
        Drawable imagedrawable = ContextCompat.getDrawable(this, images[mCurrentIndex++]);
        img.setImageDrawable(imagedrawable);
    }

    public void openAnswer(View view) {
        ANSWER_DESC = getResources().getStringArray(R.array.answer_description);
        Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
        intent.putExtra("Answery", mAD);
        startActivity(intent);
    }

    public void openShareActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ShareActivity.class);
        intent.putExtra("image_res_id", images[mCurrentIndex]);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuChangLang) {
            showLanguageDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showLanguageDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang)
                .setItems(R.array.Languages, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                String language = "ar";
                                switch (which) {
                                    case 0:
                                        language = "ar";
                                        break;
                                    case 1:
                                        language = "en";
                                        break;
                                }
                                saveLanguage(language);

                                LocaleHelper.setLocale(MainActivity.this, language);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        }
                ).create();
        alertDialog.show();
    }

    private void saveLanguage(String Lang) {
        SharedPreferences sharedPreferences = getSharedPreferences("app pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang", Lang);
        editor.apply();
    }

}
