package org.techtown.diary_20191225;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JoinActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "52.78.91.73/diary";
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

                    }
        }
        );


        join_join=(Button) findViewById(R.id.join_join);
        join_join.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String id=editText_id.getText().toString();
                        String password=editText_pw.getText().toString();
                        String name=editText_name.getText().toString();
                        String birth=editText_birth.getText().toString();
                        String email=editText_email.getText().toString();

                        InsertData tast= new InsertData();
                        tast.execute("http://"+ IP_ADDRESS+ "/insert.php",id,password, name, birth,email);

                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                });
    }


        class InsertData extends AsyncTask<String,Void, String>{

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog=ProgressDialog.show(JoinActivity.this,"기다려주세요",null,true,true);

            }


            @Override
            protected void onPostExecute(String result) {
                super.onPreExecute();
                progressDialog.dismiss();

            }



            @Override
            protected String doInBackground(String... strings) {
                String id=strings[1];
                String password=strings[2];
                String name=strings[3];
                String birth=strings[4];
                String email=strings[5];

                String serverURL=strings[0];
                String postParameters="id="+id+"&password="+password+"&name="+name+"&birth="+birth+"&email="+email;

                try{
                    URL uri=new URL(serverURL);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)uri.openConnection();
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.connect();

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    outputStream.write(postParameters.getBytes("UTF-8"));
                    outputStream.flush();
                    outputStream.close();

                    int responseStatusCode= httpURLConnection.getResponseCode();

                    InputStream inputStream;
                    if(responseStatusCode == HttpURLConnection.HTTP_OK){
                        inputStream=httpURLConnection.getInputStream();
                    }else{
                        inputStream=httpURLConnection.getErrorStream();
                    }

                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                    StringBuilder sb=new StringBuilder();
                    String line=null;

                    while((line = bufferedReader.readLine()) !=null){
                        sb.append(this);
                    }

                    bufferedReader.close();
                    return sb.toString();

                }catch (Exception e) {
                    return new String("Error: " + e.getMessage());
                }
            }

        }

}
