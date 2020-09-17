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
    String[] arr;
    float x1;
    float n;
    boolean type; // 0 geo 1 math
    ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        arr = new String[20];
        ls = (ListView)findViewById(R.id.ls);
        etx1 = (TextView)findViewById(R.id.x1);
        etn = (TextView)findViewById(R.id.n);
        etd = (TextView)findViewById(R.id.d);
        Sn = (TextView)findViewById(R.id.Sn);

        gi = getIntent();
        x1 = Float.valueOf(gi.getStringExtra("x1"));
        n = Float.valueOf(gi.getStringExtra("n"));
        type = gi.getBooleanExtra("type",true);

        if(type)
        {
            for (int i = 1; i <= 20; i++)
            {
                arr[i-1] = editNumbers(x1 * (float)Math.pow((double)n,(double)(i-1)));
            }
        }
        else
        {
            for (int i = 1; i <= 20; i++)
            {
                arr[i-1] = editNumbers(x1 + (i-1)*n);
            }
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this
                ,R.layout.support_simple_spinner_dropdown_item,arr);
        ls.setAdapter(adp);
        ls.setOnItemClickListener(this);
    }

    private  String editNumbers(float number)
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
        float summery = 0;
        
        for(int i = 0; i < pos; i++)
        {
            summery += Float.valueOf(arr[i]);
        }
        if(type)
        {
            etd.setText("geo");
        }
        else
        {
            etd.setText("math");
        }
        etx1.setText(editNumbers(x1));
        etn.setText(editNumbers(pos+1));
        Sn.setText(editNumbers(summery));

    }

    public void finish(View view) {
        finish();
    }
}