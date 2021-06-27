package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class Patient_Information_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ProgressDialog pd;
    RequestQueue rq;

    Spinner spinner2;
    String demo;
    String[] bloodGroug ={"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    TextInputEditText patientname,patientnumber,patientemail,ppincode,pstate,pdistrict,pcity,patientaddress,patientlandmark,patientID;

    TextInputEditText patientnameupdate1,patientnumberupdate1,patientemailupdate1,ppincodeupdate1, pstateupdate1,
            pdistrictupdate1,pcityupdate1,patientaddressupdate1,patientlandmarkupdate1,patientIDupdate1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__information_activity);

        rq = Volley.newRequestQueue(Patient_Information_Activity.this);
        pd = new ProgressDialog(Patient_Information_Activity.this);


        spinner2 = findViewById(R.id.spinner2);

        patientname = findViewById(R.id.patientname);
        patientnumber = findViewById(R.id.patientnumber);
        patientemail = findViewById(R.id.patientemail);
        ppincode = findViewById(R.id.ppincode);
        pstate = findViewById(R.id.pstate);
        pdistrict = findViewById(R.id.pdistrict);
        pcity = findViewById(R.id.pcity);
        patientaddress = findViewById(R.id.patientaddress);
        patientlandmark = findViewById(R.id.patientlandmark);
        patientID = findViewById(R.id.patientID);

        patientnameupdate1 = findViewById(R.id.patientnameupdate);
        patientnumberupdate1 = findViewById(R.id.patientnumberupdate);
        patientemailupdate1 = findViewById(R.id.patientemailupdate);
        ppincodeupdate1 = findViewById(R.id.ppincodeupdate);
        pstateupdate1 = findViewById(R.id.pstateupdate);
        pdistrictupdate1 = findViewById(R.id.pdistrictupdate);
        pcityupdate1 = findViewById(R.id.pcityupdate);
        patientaddressupdate1 = findViewById(R.id.patientaddressupdate);
        patientlandmarkupdate1 = findViewById(R.id.patientlandmarkupdate);
        patientIDupdate1 = findViewById(R.id.patientIDupdate);

        String data1 = patientID.getText().toString();

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGroug);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(aa);
        spinner2.setOnItemSelectedListener(this);

        demo = spinner2.getSelectedItem().toString();
        setdataon();
        acc();


    }

    public void setdataon()
    {
        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/searchupdatepatient.php?patid="+patientID.getText().toString();

        StringRequest strqu = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Patient_Information_Activity.this, "Data Store Succefully..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Patient_Information_Activity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();
                P.put("patid",patientID.getText().toString());
                return P;
            }
        };
        rq.add(strqu);

    }

    public void acc()
    {
        pd.setTitle("Searching Data...");
        pd.setMessage("Searching Data Please Be waited..");
        pd.show();

        String urll ="https://lifeshareofficial.000webhostapp.com/searchupdatepatient.php";
        String url ="https://lifeshareofficial.000webhostapp.com/searchupdatepatient.php?txtkey="+patientID.getText().toString();
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
                        Toast.makeText(Patient_Information_Activity.this, "Data Found", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(Patient_Information_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();

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

                        Toast.makeText(Patient_Information_Activity.this, ""+n, Toast.LENGTH_SHORT).show();




                        patientnameupdate1.setText(n);
                        patientnumberupdate1.setText(num);
                        patientemailupdate1.setText(email);
                        ppincodeupdate1.setText(pin);
                        pstateupdate1.setText(state);
                        pdistrictupdate1.setText(dist);
                        pcityupdate1.setText(cityy);
                        patientaddressupdate1.setText(addre);
                        patientlandmarkupdate1.setText(land);
                        patientIDupdate1.setText(eeid);




                        Toast.makeText(Patient_Information_Activity.this, "Name"+n, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(Patient_Information_Activity.this, "Error:- "+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }


    public void submit(View view) {

        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/Patient_Information.php";
        StringRequest strq = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Patient_Information_Activity.this, "Data Store Succefully..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Patient_Information_Activity.this,Patient_Dashborad_Activity.class));
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Patient_Information_Activity.this, "Error in App", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();


                P.put("demo",spinner2.getSelectedItem().toString());
                P.put("patientname",patientname.getText().toString().trim());
                P.put("patientnumber",patientnumber.getText().toString().trim());
                P.put("patientemail", patientemail.getText().toString().trim());
                P.put("ppincode",ppincode.getText().toString().trim());
                P.put("pstate",pstate.getText().toString().trim());
                P.put("pdistrict",pdistrict.getText().toString().trim());
                P.put("pcity",pcity.getText().toString().trim());
                P.put("patientaddress",patientaddress.getText().toString().trim());
                P.put("patientlandmark",patientlandmark.getText().toString().trim());
                P.put("patientID",patientID.getText().toString().trim());


                return P;
            }
        };
        rq.add(strq);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}