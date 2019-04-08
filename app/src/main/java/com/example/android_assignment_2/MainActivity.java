package com.example.android_assignment_2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ImageView rockImage, classicImage, popImage;
    TextView rockText, classicText, popText;

    static boolean isRockFragmentDisplayed = true;
    static boolean isClassicFragmentDisplayed = false;
    static boolean isPopFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rockText = findViewById(R.id.tv_rock);
        rockImage = findViewById(R.id.iv_rock);

        classicText = findViewById(R.id.tv_classic);
        classicImage = findViewById(R.id.iv_classic);

        popText = findViewById(R.id.tv_pop);
        popImage = findViewById(R.id.iv_pop);

        displayFragment();

        rockImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRockFragmentDisplayed = true;
                isClassicFragmentDisplayed = isPopFragmentDisplayed = false;

                displayFragment();
            }
        });

        classicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClassicFragmentDisplayed = true;
                isRockFragmentDisplayed = isPopFragmentDisplayed = false;

                displayFragment();
            }
        });

        popImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopFragmentDisplayed = true;
                isRockFragmentDisplayed = isClassicFragmentDisplayed = false;

                displayFragment();
            }
        });
    }

    public void displayFragment(){
        if (isRockFragmentDisplayed) {
            //change icon and text color
            DrawableCompat.setTint(rockImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorBlue));
            rockText.setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
            DrawableCompat.setTint(classicImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorSilver));
            classicText.setTextColor(ContextCompat.getColor(this, R.color.colorSilver));
            DrawableCompat.setTint(popImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorSilver));
            popText.setTextColor(ContextCompat.getColor(this, R.color.colorSilver));

            // display Rock artists
            RockFragment rockFragment = RockFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container, rockFragment).addToBackStack(null).commit();
        }else if(isClassicFragmentDisplayed){
            //change icon and text color
            DrawableCompat.setTint(classicImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorBlue));
            classicText.setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
            DrawableCompat.setTint(rockImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorSilver));
            rockText.setTextColor(ContextCompat.getColor(this, R.color.colorSilver));
            DrawableCompat.setTint(popImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorSilver));
            popText.setTextColor(ContextCompat.getColor(this, R.color.colorSilver));

            ClassicFragment classicFragment = ClassicFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container, classicFragment).addToBackStack(null).commit();
        }else{
            //change icon and text color
            DrawableCompat.setTint(popImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorBlue));
            popText.setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
            DrawableCompat.setTint(classicImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorSilver));
            classicText.setTextColor(ContextCompat.getColor(this, R.color.colorSilver));
            DrawableCompat.setTint(rockImage.getDrawable(),
                    ContextCompat.getColor(this, R.color.colorSilver));
            rockText.setTextColor(ContextCompat.getColor(this, R.color.colorSilver));

            PopFragment popFragment = PopFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container, popFragment).addToBackStack(null).commit();

        }
    }
}
