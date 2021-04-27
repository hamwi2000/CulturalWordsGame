package com.barmej.culturalwords;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {
    private TextView mAnswerTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAnswerTextView= findViewById(R.id.text_view_answer);
        String answer= getIntent().getStringExtra("Answery");
        if (answer != null)
            mAnswerTextView.setText(answer);
    }
    public void onReturn(View view) {
        finish();
    }
}
