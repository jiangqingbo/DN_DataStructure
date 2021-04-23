package com.huyunit.dn.datastructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huyunit.dn.datastructure.lesson7_20181210.Lesson7Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLesson7(View view) {
        startActivity(new Intent(this, Lesson7Activity.class));
    }



}
