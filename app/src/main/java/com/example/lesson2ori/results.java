package com.example.lesson2ori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class results extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView etx1;
    TextView etn;
    TextView etd;
    TextView Sn;
    Intent gi;
    float[] sn;
    String[] arr;
    float x1;
    float d;
    boolean type; // 0 geo 1 math
    ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        arr = new String[20];
        sn = new float[20];
        ls = (ListView)findViewById(R.id.ls);
        etx1 = (TextView)findViewById(R.id.x1);
        etn = (TextView)findViewById(R.id.n);
        etd = (TextView)findViewById(R.id.d);
        Sn = (TextView)findViewById(R.id.Sn);

        gi = getIntent();
        x1 = Float.valueOf(gi.getStringExtra("x1"));
        d = Float.valueOf(gi.getStringExtra("n"));
        type = gi.getBooleanExtra("type",true);

        if(type)
        {
            for (int i = 1; i <= 20; i++)
            {
                arr[i-1] = editNumbers(x1 * (float)Math.pow((double)d,(double)(i-1)));
                if(i == 1)
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]);
                }
                else
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]) + (sn[i-2]);
                }
            }
        }
        else
        {
            for (int i = 1; i <= 20; i++)
            {
                arr[i-1] = editNumbers(x1 + (i-1)*d);
                if(i == 1)
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]);
                }
                else
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]) + (sn[i-2]);
                }

            }
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this
                ,R.layout.support_simple_spinner_dropdown_item,arr);
        ls.setAdapter(adp);

        ls.setOnItemClickListener(this);

        etd.setText(editNumbers(d));

        etx1.setText(editNumbers(x1));
    }

    private String editNumbers(float number)
    {

        if(Float.isNaN(number)) {
            return ("no solution");
        }
        if((((float)((int)number)) == (float)number))
        {
            return String.valueOf((int)number);
        }
        return String.valueOf(number);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long is) {
        etn.setText(editNumbers(pos+1));
        Sn.setText(editNumbers(sn[pos]));

    }

    public void finish(View view) {
        finish();
    }
}