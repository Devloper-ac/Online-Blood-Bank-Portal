package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class OwnerInfo_Activity extends AppCompatActivity {


    ProgressDialog pd;
    RequestQueue rq;
    TextInputEditText ownername,ownernumber,owneremail,owneraddress,shopname,shopmobnum,pincode,state,district,city,shopaddress,landmark,shopid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_info_activity);

        rq = Volley.newRequestQueue(OwnerInfo_Activity.this);
        pd = new ProgressDialog(OwnerInfo_Activity.this);

        ownername = findViewById(R.id.ownername);
        ownernumber = findViewById(R.id.ownernumber);
        owneremail = findViewById(R.id.owneremail);
        owneraddress = findViewById(R.id.owneraddress);
        shopname = findViewById(R.id.shopname);
        shopmobnum = findViewById(R.id.shopmobnum);
        pincode = findViewById(R.id.pincode);
        state = findViewById(R.id.state);
        district = findViewById(R.id.district);
        city =  findViewById(R.id.city);
        shopaddress = findViewById(R.id.shopaddress);
        landmark = findViewById(R.id.landmark);
        shopid = findViewById(R.id.shopid);

    }

    public void submit(View view) {

        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();

        String url ="https://lifeshareofficial.000webhostapp.com/Owner_Information.php";
        StringRequest strq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(OwnerInfo_Activity.this, "Data Store succefully..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(OwnerInfo_Activity.this, "Error In App", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> P = new HashMap<>();
                P.put("ownername",ownername.getText().toString().trim());
                P.put("ownernumber",ownernumber.getText().toString().trim());
                P.put("owneremail",owneremail.getText().toString().trim());
                P.put("owneraddress",owneraddress.getText().toString().trim());
                P.put("shopname",shopname.getText().toString().trim());
                P.put("shopmobnum",shopmobnum.getText().toString().trim());
                P.put("pincode",pincode.getText().toString().trim());
                P.put("state",state.getText().toString().trim());
                P.put("district",district.getText().toString().trim());
                P.put("city",city.getText().toString().trim());
                P.put("shopaddress",shopaddress.getText().toString().trim());
                P.put("landmark",landmark.getText().toString().trim());
                P.put("shopid",shopid.getText().toString().trim());
               SharedPreferences sp = getSharedPreferences("shopid",MODE_PRIVATE);
                SharedPreferences.Editor se = sp.edit();
                se.putString("shopid",shopid.getText().toString().trim());
                se.commit();

                return P;
            }


        };
        rq.add(strq);

    }
}