package org.techtown.diary_20191225;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {


    DBHelper dbHelper;
    SQLiteDatabase database;
    Button join_join;
    private String tableName;
    private User user;
    private EditText editText_id;
    private EditText editText_pw;
    private EditText editText_name;
    private EditText editText_birth;
    private EditText editText_email;
    private String alertNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        user=new User();
        dbHelper=new DBHelper(this);

        editText_id=findViewById(R.id.join_Id);
        editText_pw=findViewById(R.id.join_pw);
        editText_name=findViewById(R.id.join_name);
        editText_birth=findViewById(R.id.join_birth);
        editText_email=findViewById(R.id.join_email);


        join_join=(Button) findViewById(R.id.join_join);
        join_join.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        if(isNull()){
                            dbHelper.insertUser(user);
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }

                        else{
                            AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
                            builder.setMessage(alertNull)
                                    .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                        }
                                    }).show();
                        }
                    }
                }
        );

    }

    private void createDatabase() {
        Toast.makeText(this, "호출됨", Toast.LENGTH_SHORT).show();

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();


        Toast.makeText(this, "생성함", Toast.LENGTH_SHORT).show();
    }

        private Boolean isNull(){
        String id=editText_id.getText().toString();
        String pw=editText_pw.getText().toString();
        String name=editText_name.getText().toString();
        String birth=editText_birth.getText().toString();
        String email=editText_email.getText().toString();

        if(id== null ||id.equals("")){
            alertNull="아이디를 입력하세요";
            return false;
        }
        else{

         user.setId(id);
        }

        if(pw== null || pw.equals("")){
            alertNull="비밀번호를 입력하세요";

            return false;
        }
        else{
            user.setPw(pw);
        }
        if(name== null || name.equals("")){
            alertNull="이름을 입력하세요";

            return false;
        }
        else{
            user.setName(name);
        }
        if(birth== null || birth.equals("")){
            alertNull="생년월일을 입력하세요";

            return false;
        }  else{
            user.setBirth(birth);
        }
        if(email== null || email.equals("")){
            alertNull="이메일을 입력하세요";

            return false;
        }  else{
            user.setEmail(email);
        }

            return true;
        }

}
