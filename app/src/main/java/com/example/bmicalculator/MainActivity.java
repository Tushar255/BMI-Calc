package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Light);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            // set Dark theme
            setTheme(R.style.Theme_Dark);
        } else {
            // set Light Mode
            setTheme(R.style.Theme_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchCompat = findViewById(R.id.bt_switch);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Dark Mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    // Light Mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        EditText W, H;
        TextView txtRes, txtInter;
        Button Result, Reset;

        W = (EditText) findViewById(R.id.weight);
        H = (EditText) findViewById(R.id.height);

        txtInter = (TextView) findViewById(R.id.txtinter);
        txtRes = (TextView) findViewById(R.id.txtres);

        Result = (Button) findViewById(R.id.resut);
        Reset = (Button) findViewById(R.id.reset);

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strweg = W.getText().toString();
                String strhei = H.getText().toString();

                if (strweg.equals("")) {
                    W.setError("Please Enter Your Weight");
                    W.requestFocus();
                    return;
                }
                if (strhei.equals("")) {
                    H.setError("Please Enter Your Height");
                    H.requestFocus();
                    return;
                }

                float weight = Float.parseFloat(strweg);
                float height = Float.parseFloat(strhei) / 100;

                float bmiValue = BMICalculate(weight, height);

                txtInter.setText(interpreteBMI(bmiValue));
                txtRes.setText("BMI = " + bmiValue);
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                H.setText("");
                W.setText("");
                txtInter.setText("");
                txtRes.setText("");
            }
        });
    }

    public float BMICalculate(float weight, float height) {
        return weight / (height * height);
    }

    public String interpreteBMI(float bmiValue) {
        if (bmiValue < 16) {
            return "Servely Unnderweight";
        }
        else if (bmiValue < 18.5) {
            return "Underweight";
        }
        else if (bmiValue < 25) {
            return "Normal";
        }
        else if(bmiValue < 30) {
            return "Overweight";
        }
        else {
            return "Obese";
        }
    }
}