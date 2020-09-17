package com.example.lesson2ori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch s;
    EditText etn;
    Intent si;
    EditText etd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = (Switch) findViewById(R.id.sw);
        etn = (EditText)findViewById(R.id.etn);
        etd = (EditText)findViewById(R.id.etd);
    }

    public void results(View view) {
        si = new Intent(this,results.class);
        si.putExtra("type",s.isChecked());
        si.putExtra("x1",etd.getText().toString());
        si.putExtra("n",etn.getText().toString());
        startActivity(si);
    }
}