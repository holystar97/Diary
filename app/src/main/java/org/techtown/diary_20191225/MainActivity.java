package org.techtown.diary_20191225;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView login_join;
    TextView login_find;
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


        login_find=(TextView) findViewById(R.id.login_find);

        login_find.setOnClickListener(
                new TextView.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),FindActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder Dialog= new AlertDialog.Builder(this);
        Dialog.setMessage("앱을 종료하시겠습니까?")
                .setTitle("Dialog")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }




}
