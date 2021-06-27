package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.HashMap;
import java.util.Map;

public class Owner_PatientAdd_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner OPatientspinner;
    ProgressDialog pd;
    RequestQueue rq;
    String demo;
    String[] bloodGrougA ={"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    TextInputEditText OPatientname,OPatientnumber,OPatientEmail,OPatientpincode,OPatientstate,OPatientdistrict,OPatientCity,OPatientAddress,OPatientLandmark,OPatientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__patient_add_activity);

        rq = Volley.newRequestQueue(Owner_PatientAdd_Activity.this);
        pd = new ProgressDialog(Owner_PatientAdd_Activity.this);

        OPatientspinner = findViewById(R.id.OPatientspinner);
        OPatientname = findViewById(R.id.OPatientname);
        OPatientnumber = findViewById(R.id.OPatientnumber);
        OPatientEmail = findViewById(R.id.OPatientEmail);
        OPatientpincode = findViewById(R.id.OPatientpincode);
        OPatientstate = findViewById(R.id.OPatientstate);
        OPatientdistrict = findViewById(R.id.OPatientdistrict);
        OPatientCity = findViewById(R.id.OPatientCity);
        OPatientAddress = findViewById(R.id.OPatientAddress);
        OPatientLandmark = findViewById(R.id.OPatientLandmark);
        OPatientID = findViewById(R.id.OPatientID);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGrougA);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OPatientspinner.setAdapter(aa);
        OPatientspinner.setOnItemSelectedListener(this);

        demo = OPatientspinner.getSelectedItem().toString();


    }

    public void OPatientSubmit(View view) {
        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/Patient_Information.php";
        StringRequest strq3 = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Owner_PatientAdd_Activity.this, "Data Store Succefully..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Owner_PatientAdd_Activity.this, "Error in App", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();

                P.put("demo",OPatientspinner.getSelectedItem().toString());
                P.put("patientname", OPatientname.getText().toString().trim());
                P.put("patientnumber",OPatientnumber.getText().toString().trim());
                P.put("patientemail", OPatientEmail.getText().toString().trim());
                P.put("ppincode",OPatientpincode.getText().toString().trim());
                P.put("pstate",OPatientstate .getText().toString().trim());
                P.put("pdistrict",OPatientdistrict.getText().toString().trim());
                P.put("pcity",OPatientCity.getText().toString().trim());
                P.put("patientaddress",OPatientAddress.getText().toString().trim());
                P.put("patientlandmark",OPatientLandmark.getText().toString().trim());
                P.put("patientID",OPatientID.getText().toString().trim());
                return P;
            }
        };
        rq.add(strq3);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}