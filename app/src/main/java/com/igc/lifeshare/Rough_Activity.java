package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Rough_Activity extends AppCompatActivity {

    EditText name;
    RequestQueue rq;
    ProgressDialog pd;
    Calendar c;
    int dd,mm,yy,hr,mn,mi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rough_activity);

        name = findViewById(R.id.name);
        rq = Volley.newRequestQueue(Rough_Activity.this);
        pd = new ProgressDialog(Rough_Activity.this);

        c = Calendar.getInstance();
        dd = c.get(Calendar.DAY_OF_MONTH);
        mm = c.get(Calendar.MONTH);
        yy = c.get(Calendar.YEAR);
        hr = c.get(Calendar.HOUR_OF_DAY);
        mn = c.get(Calendar.MINUTE);
    }

    public void sub(View view) {
        pd.setTitle("Sending..");
        pd.setMessage("Sending data to server Please Wait..");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/demo.php";
        StringRequest strq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Rough_Activity.this, "Data Submitted..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Rough_Activity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> P = new HashMap<>();
                P.put("name",name.getText().toString().trim());
                return P;
            }
        };
        rq.add(strq);

    }
}
/*

 Button btnCalender,btnTime;
    Calendar c;
    GridView gridv;
    int dd,mm,yy,hr,mn,mi;
    String[] Name = {"chaitanya","Mahesh","Murataja"};
    int[] img = {R.drawable.v,R.drawable.a,R.drawable.b};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalender = findViewById(R.id.btnCalender);
        btnTime = findViewById(R.id.btnTime);
        gridv = findViewById(R.id.gridv);

        MyAdapter ma = new MyAdapter();
        gridv.setAdapter(ma);

        c = Calendar.getInstance();
        dd = c.get(Calendar.DAY_OF_MONTH);
        mm = c.get(Calendar.MONTH);
        yy = c.get(Calendar.YEAR);
        hr = c.get(Calendar.HOUR_OF_DAY);
        mn = c.get(Calendar.MINUTE);


    }

    public void opencal(View view) {

        DatePickerDialog dp = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                btnCalender.setText(dayOfMonth+"/"+month+"/"+year);
            }
        }, yy, mm, dd);
        dp.show();

    }

    public void opentime(View view) {

        TimePickerDialog tp = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                btnTime.setText(hourOfDay+":"+minute);
            }
        }, hr, mn, false);
        tp.show();
    }

 */