package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Patient_ProfileUpdate_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    ProgressDialog pd;
    RequestQueue rq;
    String demoupdate;
    Spinner spinner2update;
    String[] bloodGrougupdate ={"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    TextInputEditText patientnameupdate,patientnumberupdate,patientemailupdate,ppincodeupdate,pstateupdate,
            pdistrictupdate,pcityupdate,patientaddressupdate,patientlandmarkupdate,patientIDupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__profile_update_activity);

        rq = Volley.newRequestQueue(Patient_ProfileUpdate_Activity.this);
        pd = new ProgressDialog(Patient_ProfileUpdate_Activity.this);

        spinner2update = findViewById(R.id.spinner2update);

        patientnameupdate = findViewById(R.id.patientnameupdate);
        patientnumberupdate = findViewById(R.id.patientnumberupdate);
        patientemailupdate = findViewById(R.id.patientemailupdate);
        ppincodeupdate = findViewById(R.id.ppincodeupdate);
        pstateupdate = findViewById(R.id.pstateupdate);
        pdistrictupdate = findViewById(R.id.pdistrictupdate);
        pcityupdate = findViewById(R.id.pcityupdate);
        patientaddressupdate = findViewById(R.id.patientaddressupdate);
        patientlandmarkupdate = findViewById(R.id.patientlandmarkupdate);
        patientIDupdate = findViewById(R.id.patientIDupdate);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGrougupdate);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2update.setAdapter(aa);
        spinner2update.setOnItemSelectedListener(this);


        demoupdate = spinner2update.getSelectedItem().toString();
        //getdatafrom();
    }

    public void getdatafrom()
    {


            pd.setTitle("Searching Data...");
            pd.setMessage("Searching Data Please Be waited..");
            pd.show();

            String url ="https://lifeshareofficial.000webhostapp.com/searchupdatepatient.php";
            String urll ="https://lifeshareofficial.000webhostapp.com/accessempdata.php?txtkey="+patientIDupdate.getText().toString();
            JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {


                    pd.dismiss();
                    try {
                        JSONArray jsarr1 = response.getJSONArray("Result");
                        //Toast.makeText(Update_Delete_Employees.this, ""+jsarr1.length(), Toast.LENGTH_SHORT).show();

                        if(jsarr1.length() != 0)
                        {
                            Toast.makeText(Patient_ProfileUpdate_Activity.this, "Data Found", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(Patient_ProfileUpdate_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();

                        }


                        for (int i = 0; i < jsarr1.length(); i++)
                        {

                            JSONObject jsobj = jsarr1.getJSONObject(i);

                            String n = (jsobj.getString("Name"));
                            String num = (jsobj.getString("Number"));
                            String email = (jsobj.getString("Email"));
                            String datee = (jsobj.getString("Datee"));
                            String spin = (jsobj.getString("Spinner"));
                            String pin = (jsobj.getString("Pincode"));
                            String state = (jsobj.getString("State"));
                            String dist = (jsobj.getString("District"));
                            String cityy = (jsobj.getString("City"));
                            String addre = (jsobj.getString("Address"));
                            String land = (jsobj.getString("Landmark"));
                            String eeid = (jsobj.getString("Eid"));

                            Toast.makeText(Patient_ProfileUpdate_Activity.this, ""+n, Toast.LENGTH_SHORT).show();


                            /*patientnameupdate = findViewById(R.id.patientnameupdate);
                            patientnumberupdate = findViewById(R.id.patientnumberupdate);
                            patientemailupdate = findViewById(R.id.patientemailupdate);
                            ppincodeupdate = findViewById(R.id.ppincodeupdate);
                            pstateupdate = findViewById(R.id.pstateupdate);
                            pdistrictupdate = findViewById(R.id.pdistrictupdate);
                            pcityupdate = findViewById(R.id.pcityupdate);
                            patientaddressupdate = findViewById(R.id.patientaddressupdate);
                            patientlandmarkupdate = findViewById(R.id.patientlandmarkupdate);
                            patientIDupdate = findViewById(R.id.patientIDupdate);*/


                            patientnameupdate.setText(n);
                            patientnumberupdate.setText(num);
                            patientemailupdate.setText(email);
                            ppincodeupdate.setText(pin);
                            pstateupdate.setText(state);
                            pdistrictupdate.setText(dist);
                            pcityupdate.setText(cityy);
                            patientaddressupdate.setText(addre);
                            patientlandmarkupdate.setText(land);
                            patientIDupdate.setText(eeid);


                            Toast.makeText(Patient_ProfileUpdate_Activity.this, "Name"+n, Toast.LENGTH_SHORT).show();

                        }

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    pd.dismiss();
                    Toast.makeText(Patient_ProfileUpdate_Activity.this, "Error:- "+error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            rq.add(jsrq);



    }



    public void submitupdate(View view) {
        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/Update_PatientInfo.php";

        StringRequest strqu = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Patient_ProfileUpdate_Activity.this, "Data Store Succefully..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Patient_ProfileUpdate_Activity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();

                P.put("demoupdate",spinner2update.getSelectedItem().toString());
                P.put("patientnameupdate",patientnameupdate.getText().toString().trim());
                P.put("patientnumberupdate",patientnumberupdate.getText().toString().trim());
                P.put("patientemailupdate",patientemailupdate.getText().toString().trim());
                P.put("ppincodeupdate",ppincodeupdate.getText().toString().trim());
                P.put("pstateupdate",pstateupdate.getText().toString().trim());
                P.put("pdistrictupdate",pdistrictupdate.getText().toString().trim());
                P.put("pcityupdate",pcityupdate.getText().toString().trim());
                P.put(" patientaddressupdate", patientaddressupdate.getText().toString().trim());
                P.put("patientlandmarkupdate",patientlandmarkupdate.getText().toString().trim());
                P.put("patientIDupdate",patientIDupdate.getText().toString().trim());
                return P;
            }
        };
        rq.add(strqu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}