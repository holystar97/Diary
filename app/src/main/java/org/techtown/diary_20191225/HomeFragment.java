package org.techtown.diary_20191225;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_home,container,false);
        CalendarView calendar=(CalendarView) rootView.findViewById(R.id.calendarView);

        FloatingActionButton fab= rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new FABClickListener());



        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Toast.makeText(getActivity().getApplicationContext(),""+dayOfMonth+month+year,Toast.LENGTH_SHORT).show();
                show();
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





}
