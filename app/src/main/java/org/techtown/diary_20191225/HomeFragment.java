package org.techtown.diary_20191225;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static String IP_ADDRESS = "52.78.91.73/diary";
    private int mYear=0;
    private int mMonth=0;
    private int mDay=0;
    private String content;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_home,container,false);
        final CalendarView calendar=(CalendarView) rootView.findViewById(R.id.calendarView);

        FloatingActionButton fab= rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new FABClickListener());

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Toast.makeText(getActivity().getApplicationContext(),""+dayOfMonth+month+year,Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getActivity().getApplicationContext(),Long.toString(calendar.getDate()),Toast.LENGTH_SHORT).show();

                if(mYear==year && mMonth ==month + 1 && mDay ==dayOfMonth){
                    show();
                }else{
                    mYear = year;
                    mMonth = month + 1;
                    mDay = dayOfMonth;
                }



            }
        });

        return rootView;
    }


    class FABClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

                    Intent intent=new Intent(getActivity().getApplicationContext(),ChatActivity.class);
                    startActivity(intent);

        }
    }


    void show(){

        final EditText editText= new EditText(getActivity());
        editText.setHint("일정");

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("일정 입력 ");
        builder.setView(editText);

        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity().getApplicationContext(),editText.getText().toString(),Toast.LENGTH_SHORT).show();

                Calendar cal= Calendar.getInstance();
                int h =cal.get(Calendar.HOUR);
                String hour=String.valueOf(h);
                int m=cal.get(Calendar.MINUTE);
                String minute=String.valueOf(m);
                String year=String.valueOf(mYear);
                String month=String.valueOf(mMonth);
                String day=String.valueOf(mDay);
                String id=MainActivity.ID;
                content=editText.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insertCalendar.php",id,year,month,day,hour,minute,content);


            }
        });
        builder.setNegativeButton("저장안함", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity().getApplicationContext(),"저장 안함 ",Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();

    }


    class InsertData extends AsyncTask<String,Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog=ProgressDialog.show(getContext(),"기다려주세요",null,true,true);

        }


        @Override
        protected void onPostExecute(String result) {
            super.onPreExecute();
            progressDialog.dismiss();

        }



        @Override
        protected String doInBackground(String... strings) {
            String id=strings[1];
            String year=strings[2];
            String month=strings[3];
            String day=strings[4];
            String hour=strings[5];
            String minute=strings[6];
            String content=strings[7];
            String serverURL=strings[0];

            String postParameters="id="+id+"&year="+year+"&month="+month+"&day="+day+"&hour="+hour+"&minute="+minute+"&content="+content;

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
