package org.techtown.diary_20191225;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class FindActivity extends AppCompatActivity {

    Button Codebutton;
    LinearLayout showlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        Codebutton=(Button) findViewById(R.id.Codebutton);


        Codebutton.setOnClickListener(

            new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    showlayout =(LinearLayout) findViewById(R.id.showlayout);
                    if(showlayout.getVisibility() == view.INVISIBLE) {
                        showlayout.setVisibility(view.VISIBLE);
                    }
                }
            }
        );


    }
}
