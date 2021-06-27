package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class Information_Activity extends AppCompatActivity {

    ProgressDialog pd;
    RequestQueue rp;
    ImageView ownerimage,txtshopimage;
    TextInputEditText txtownername,txtownermobnumber,txtowneremail,txtowneraddress,txtshopname,
            txtcleaniknum,txtshoppincode,txtstate,txtdistrict,txtcity,txtshopaddress,txtownerid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);

        ownerimage = findViewById(R.id.ownerimage);
        txtshopimage = findViewById(R.id.txtshopimage);

        txtownername = findViewById(R.id.txtownername);
        txtownermobnumber = findViewById(R.id.txtownermobnumber);
        txtowneremail = findViewById(R.id.txtowneremail);
        txtowneraddress = findViewById(R.id.txtowneraddress);
        txtshopname = findViewById(R.id.txtshopname);
        txtcleaniknum = findViewById(R.id.txtcleaniknum);
        txtshoppincode = findViewById(R.id.txtshoppincode);
        txtstate = findViewById(R.id.txtstate);
        txtdistrict = findViewById(R.id.txtdistrict);
        txtcity = findViewById(R.id.txtcity);
        txtshopaddress = findViewById(R.id.txtshopaddress);
        txtownerid = findViewById(R.id.txtownerid);

        rp = Volley.newRequestQueue(Information_Activity.this);
        pd = new ProgressDialog(Information_Activity.this);
    }


    public void AddData(View view) {


        String url="https://lifeshareofficial.000webhostapp.com/Insert.php";
        if(txtownername.getText().toString().isEmpty() || txtownermobnumber.getText().toString().isEmpty() ||txtowneremail.getText().toString().isEmpty()||txtowneraddress.getText().toString().isEmpty()|| txtshopname.getText().toString().isEmpty()||txtcleaniknum.getText().toString().isEmpty() ||txtshoppincode.getText().toString().isEmpty()||txtstate.getText().toString().isEmpty()||txtdistrict.getText().toString().isEmpty()|| txtcity.getText().toString().isEmpty() || txtshopaddress.getText().toString().isEmpty()|| txtownerid.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }else if(txtownermobnumber.getText().toString().isEmpty())
        {

        }else if(txtowneremail.getText().toString().isEmpty())
        {
            txtowneremail.setError("Please Enter Email");txtowneremail.requestFocus();
        }else if(txtowneraddress.getText().toString().isEmpty())
        {
            txtowneraddress.setError("Please Enter Owner Address");txtowneraddress.requestFocus();
        }else if(txtshopname.getText().toString().isEmpty())
        {
            txtshopname.setError("Please Enter Cleanik Name");txtshopname.requestFocus();
        }else if(txtcleaniknum.getText().toString().isEmpty())
        {
            txtcleaniknum.setError("Please Enter Cleanik Mob No");txtcleaniknum.requestFocus();
        }else if(txtshoppincode.getText().toString().isEmpty())
        {
            txtshoppincode.setError("Please Enter Pincode");txtshoppincode.requestFocus();
        }else if(txtstate.getText().toString().isEmpty())
        {
            txtstate.setError("Please Enter State");txtstate.requestFocus();
        }else if(txtdistrict.getText().toString().isEmpty())
        {
            txtdistrict.setError("Please Enter District");txtdistrict.requestFocus();
        }else if(txtcity.getText().toString().isEmpty())
        {
            txtcity.setError("Please Enter City");txtcity.requestFocus();
        }else if(txtshopaddress.getText().toString().isEmpty())
        {
            txtshopaddress.setError("Please Enter Cleanik Address");txtshopaddress.requestFocus();
        }else if(txtownerid.getText().toString().isEmpty())
        {
            txtownerid.setError("Please Enter OwnerID");txtownerid.requestFocus();
        }else if(txtownername.getText().toString().isEmpty())
        {
            txtownermobnumber.setError("Please Enter Mobile No");txtownermobnumber.requestFocus();
        }
        else
        {
            pd.setTitle("Sending..");
            pd.setMessage("Sending Data Please Wait...");
            pd.show();

            StringRequest strp = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    pd.dismiss();
                    Toast.makeText(Information_Activity.this, "Data store...", Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    pd.dismiss();
                    Toast.makeText(Information_Activity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> M = new HashMap<>();
                    M.put("txtownername",txtownername.getText().toString());
                    M.put("txtownermobnumber",txtownermobnumber.getText().toString());
                    M.put("txtowneremail",txtowneremail.getText().toString());
                    M.put("txtowneraddress",txtowneraddress.getText().toString());
                    M.put("txtshopname",txtshopname.getText().toString());
                    M.put("txtcleaniknum",txtcleaniknum.getText().toString());
                    M.put("txtshoppincode",txtshoppincode.getText().toString());
                    M.put("txtstate",txtstate.getText().toString());
                    M.put("txtdistrict",txtdistrict.getText().toString());
                    M.put("txtcity",txtcity.getText().toString());
                    M.put("txtshopaddress",txtshopaddress.getText().toString());
                    M.put("txtownerid",txtownerid.getText().toString());

                    return M;
                }
            };
            rp.add(strp);
            startActivity(new Intent(Information_Activity.this,OwnerDashboard_Activity.class));
        }


    }
}