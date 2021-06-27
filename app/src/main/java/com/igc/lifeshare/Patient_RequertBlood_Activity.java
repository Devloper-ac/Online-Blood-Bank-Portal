package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Patient_RequertBlood_Activity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    ProgressDialog pd;
    RequestQueue rq;
    Spinner spinner3;
    Calendar c;
    String BloodR;
    String Datee;
    int dd,mm,yy,hr,mn,mi;
    Button btncal;
    String[] bloodGrougPatient ={"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    TextInputEditText BloodRname,BloodRnumber,BloodRUnit,BloodRState,BloodRCity,BloodRHosName,BloodRMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__requert_blood_activity);

        rq = Volley.newRequestQueue(Patient_RequertBlood_Activity.this);
        pd = new ProgressDialog(Patient_RequertBlood_Activity.this);

        c = Calendar.getInstance();
        dd = c.get(Calendar.DAY_OF_MONTH);
        mm = c.get(Calendar.MONTH);
        yy = c.get(Calendar.YEAR);
        hr = c.get(Calendar.HOUR_OF_DAY);
        mn = c.get(Calendar.MINUTE);
        btncal = findViewById(R.id.btncal);
        spinner3 = findViewById(R.id.spinner3);

        BloodRname = findViewById(R.id.BloodRname);
        BloodRnumber = findViewById(R.id.BloodRnumber);
        BloodRUnit = findViewById(R.id.BloodRUnit);
        BloodRState = findViewById(R.id.BloodRState);
        BloodRCity = findViewById(R.id.BloodRCity);
        BloodRHosName = findViewById(R.id.BloodRHosName);
        BloodRMessage = findViewById(R.id.BloodRMessage);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGrougPatient);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(aa);
        spinner3.setOnItemSelectedListener(this);

        BloodR = spinner3.getSelectedItem().toString();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void opencal(View view) {

        DatePickerDialog dp = new DatePickerDialog(Patient_RequertBlood_Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                btncal.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                Datee = btncal.getText().toString().trim();
            }
        }, yy, mm - 1, dd);
        dp.show();

    }

    public void postRequest(View view) {
        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/Patient_BloodRequest.php";
        StringRequest strBR = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                pd.dismiss();
                Toast.makeText(Patient_RequertBlood_Activity.this, "Request Send Succefully..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Patient_RequertBlood_Activity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();


                P.put("BloodRname",BloodRname.getText().toString().trim());
                P.put(" BloodRnumber", BloodRnumber.getText().toString().trim());
                P.put("BloodR",spinner3.getSelectedItem().toString());
                P.put(" BloodRUnit", BloodRUnit.getText().toString().trim());
                P.put(" BloodRState", BloodRState.getText().toString().trim());
                P.put("BloodRCity",BloodRCity.getText().toString().trim());
                P.put("BloodRHosName",BloodRHosName.getText().toString().trim());
                P.put("Datee",btncal.getText().toString());
                P.put("BloodRMessage",BloodRMessage.getText().toString().trim());

                return P;
            }
        };
        rq.add(strBR);
    }
}