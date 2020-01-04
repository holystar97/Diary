package org.techtown.diary_20191225;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase database;
    Button join_join;
    Button checkBtn;
    private String tableName;
    private User user;
    private EditText editText_id;
    private EditText editText_pw;
    private EditText editText_name;
    private EditText editText_birth;
    private EditText editText_email;
    private String alertNull;
    private SQLiteDatabase db;
    private boolean isCheck=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        user=new User();
        dbHelper=new DBHelper(this);
        db=dbHelper.getWritableDatabase();

        editText_id=findViewById(R.id.join_Id);
        editText_pw=findViewById(R.id.join_pw);
        editText_name=findViewById(R.id.join_name);
        editText_birth=findViewById(R.id.join_birth);
        editText_email=findViewById(R.id.join_email);
        checkBtn= findViewById(R.id.join_check);

        editText_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    alertNull="중복확인을 해주세요 ";
                    isCheck=false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        checkBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                       if(checkId()) {
                           AlertDialog.Builder builder=new AlertDialog.Builder(JoinActivity.this);
                           builder.setMessage("사용 가능한 아이디 입니다.")
                                   .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialogInterface, int i) {
                                       }
                                   }).show();
                            isCheck=true;
                       }
                       else{

                           AlertDialog.Builder builder=new AlertDialog.Builder(JoinActivity.this);
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


        join_join=(Button) findViewById(R.id.join_join);
        join_join.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isCheck) {
                            if (isNull()) {
                                dbHelper.insertUser(user);

                                AlertDialog.Builder builder=new AlertDialog.Builder(JoinActivity.this);
                                builder.setMessage("가입이 완료되었습니다. 로그인 해주세요")
                                        .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);
                                            }
                                        }).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                builder.setMessage(alertNull)
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {


                                            }
                                        }).show();
                            }
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                            builder.setMessage(alertNull)
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
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
        String pw=editText_pw.getText().toString().trim();
        String name=editText_name.getText().toString().trim();
        String birth=editText_birth.getText().toString().trim();
        String email=editText_email.getText().toString().trim();

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


        private Boolean checkId(){
            String id=editText_id.getText().toString().trim();
            if(id== null ||id.equals("")){
                alertNull="아이디를 입력하세요";
                return false;
            }
            else{
                user.setId(id);
            }

            String sql="SELECT id FROM "+DBHelper.USER_TABLE_NAME;
            Cursor cursor =db.rawQuery(sql,null);
            if(cursor.moveToFirst()){
                do{
                    String userid=cursor.getString(0);
                    if(userid.equals(id)){
                        alertNull="중복되는 아이디입니다.";
                        return false;
                    }
                }while(cursor.moveToNext());
            }

            return  true;
        }

}
