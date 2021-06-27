package com.igc.lifeshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.ListIterator;
import java.util.Map;

public class OwnerProfileUpdate_Activity extends AppCompatActivity {


    ProgressDialog pd;
    RequestQueue rq;
    ListView listallowner;
    String[] allName,allBloodGroup,allMobNo,allemail,allPincode,allState,allDistrict,allCity,allAddress,allLandmark;
    TextInputEditText ownernameupdate,ownernumberupdate,owneremailupdate,owneraddressupdate,
            shopnameupdate,shopmobnumupdate,pincodeupdate,stateupdate,districtupdate,
            cityupdate,shopaddressupdate,landmarkupdate,shopidupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_profile_update_activity);

        ownernameupdate = findViewById(R.id.patientname);
        ownernumberupdate = findViewById(R.id.ownernumber);
        owneremailupdate = findViewById(R.id.patientREmail);
        owneraddressupdate = findViewById(R.id.owneraddress);

        listallowner = findViewById(R.id.listallowner);

        rq = Volley.newRequestQueue(OwnerProfileUpdate_Activity.this);
        pd = new ProgressDialog(OwnerProfileUpdate_Activity.this);

        shopnameupdate = findViewById(R.id.shopname);
        shopmobnumupdate = findViewById(R.id.shopmobnum);
        pincodeupdate = findViewById(R.id.pincode);
        stateupdate= findViewById(R.id.state);
        districtupdate= findViewById(R.id.district);
        cityupdate =  findViewById(R.id.city);
        shopaddressupdate = findViewById(R.id.shopaddress);
        landmarkupdate = findViewById(R.id.landmark);
        shopidupdate = findViewById(R.id.shopid);


        AccessOwnerProfile();
    }

    public void AccessOwnerProfile()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/accessownerD.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");


                    allName = new String[jsarr1.length()];
                    allBloodGroup = new String[jsarr1.length()];
                    allMobNo = new String[jsarr1.length()];
                    allemail = new String[jsarr1.length()];
                    allPincode = new String[jsarr1.length()];
                    allState = new String[jsarr1.length()];
                    allDistrict = new String[jsarr1.length()];
                    allCity = new String[jsarr1.length()];
                    allAddress = new String[jsarr1.length()];
                    allLandmark = new String[jsarr1.length()];

                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);



                        allName[i] = jsobj.getString("Name");
                        allBloodGroup[i] = jsobj.getString("BloodGroup");
                        allMobNo[i] = jsobj.getString("MobileNo");
                        allemail[i] = jsobj.getString("Email");
                        allPincode[i] = jsobj.getString("Pincode");
                        allState[i] = jsobj.getString("State");
                        allDistrict[i] = jsobj.getString("District");
                        allCity[i] = jsobj.getString("City");
                        allAddress[i] = jsobj.getString("Address");
                        allLandmark[i] = jsobj.getString("Landmark");

                        //Toast.makeText(OwnerProfileUpdate_Activity.this, "RollNo" +allName, Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listallowner.setAdapter(ma);

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(OwnerProfileUpdate_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    /*public void updateownerprofile(View view) {

        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/OwnerProfileUpdate.php";
        StringRequest strq3 = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(OwnerProfileUpdate_Activity.this, "Data Store Succefully..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(OwnerProfileUpdate_Activity.this, "Error in App", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();

                P.put("ownername",ownernameupdate.getText().toString().trim());
                P.put("ownernumber",ownernumberupdate.getText().toString().trim());
                P.put("owneremail",owneremailupdate.getText().toString().trim());
                P.put("owneraddress",owneraddressupdate.getText().toString().trim());
                P.put("shopname",shopnameupdate.getText().toString().trim());
                P.put("shopmobnum",shopmobnumupdate.getText().toString().trim());
                P.put("pincode",pincodeupdate.getText().toString().trim());
                P.put("state",stateupdate.getText().toString().trim());
                P.put("district",districtupdate.getText().toString().trim());
                P.put("city",cityupdate.getText().toString().trim());
                P.put("shopaddress",shopaddressupdate.getText().toString().trim());
                P.put("landmark",landmarkupdate.getText().toString().trim());
                return P;
            }
        };
        rq.add(strq3);
    }*/


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return  allName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.listoppatient,null);

           TextView ow_Name = convertView.findViewById(R.id.txtpopname);
           TextView ow_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
           TextView ow_Mobno = convertView.findViewById(R.id.txtpopmobno);
           TextView ow_email = convertView.findViewById(R.id.txtpopemail);
           TextView ow_Pincode = convertView.findViewById(R.id.txtpopstate);
           TextView ow_State = convertView.findViewById(R.id.txtpopcity);
           TextView ow_District = convertView.findViewById(R.id.txtpophos);
           TextView ow_City = convertView.findViewById(R.id.txtpopdate);
           TextView ow_Address = convertView.findViewById(R.id.txtpopmessage);
           TextView ow_Landmark = convertView.findViewById(R.id.txtpoplandmark);



            ow_Name.setText(allName[position]);
            ow_Blood.setText(allBloodGroup[position]);
            ow_Mobno.setText(allMobNo[position]);
            ow_email.setText(allemail[position]);
            ow_Pincode.setText(allPincode[position]);
            ow_State.setText(allState[position]);
            ow_District.setText(allDistrict[position]);
            ow_City.setText(allCity[position]);
            ow_Address.setText(allAddress[position]);
            ow_Landmark.setText(allLandmark[position]);
            return convertView;



        }
    }

}