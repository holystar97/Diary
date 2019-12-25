package org.techtown.diary_20191225;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView login_join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       login_join=(TextView) findViewById(R.id.login_join);

        login_join.setOnClickListener(
                new TextView.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),JoinActivity.class);
                        startActivity(intent);
                    }
                }
        );




    }
}
