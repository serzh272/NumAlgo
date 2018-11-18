package com.example.sergey.numalgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn_click(View view) {
        Fraction fr1 = new Fraction();
        Fraction fr2 = new Fraction();
        fr1.Read(((EditText)findViewById(R.id.editText)).getText().toString());
        fr2.Read(((EditText)findViewById(R.id.editText2)).getText().toString());
        if (view == findViewById(R.id.btn_add))
        {
            ((TextView)findViewById(R.id.textView)).setText(Fraction.Add(fr1, fr2).Write());
        }
        if (view == findViewById(R.id.btn_sub))
        {
            ((TextView)findViewById(R.id.textView)).setText(Fraction.Sub(fr1, fr2).Write());
        }
        if (view == findViewById(R.id.btn_mult))
        {
            ((TextView)findViewById(R.id.textView)).setText(Fraction.Mult(fr1, fr2).Write());
        }
        if (view == findViewById(R.id.btn_div))
        {
            ((TextView)findViewById(R.id.textView)).setText(Fraction.Div(fr1, fr2).Write());
        }
    }
}
