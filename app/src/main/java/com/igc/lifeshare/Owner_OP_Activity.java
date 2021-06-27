package com.igc.lifeshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Owner_OP_Activity extends AppCompatActivity {

    ListView OPListOwner;
    String[] OOPName,OOPBloodGroup,OOPMobNo,OOPemail,OOPPincode,OOPState,OOPDistrict,OOPCity,OOPAddress,OOPLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__o_p_activity);
        OPListOwner = findViewById(R.id.OPListOwner);
        rq = Volley.newRequestQueue(Owner_OP_Activity.this);
        pd = new ProgressDialog(Owner_OP_Activity.this);
        refOP = findViewById(R.id.refOP);

        refOP.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessDataOOP();
                refOP.setRefreshing(false);
            }
        });

        AccessDataOOP();
    }


    public void AccessDataOOP() {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/OP_OwnerAccess.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OOPName = new String[jsarr1.length()];
                    OOPBloodGroup = new String[jsarr1.length()];
                    OOPMobNo = new String[jsarr1.length()];
                    OOPemail = new String[jsarr1.length()];
                    OOPPincode = new String[jsarr1.length()];
                    OOPState = new String[jsarr1.length()];
                    OOPDistrict = new String[jsarr1.length()];
                    OOPCity = new String[jsarr1.length()];
                    OOPAddress = new String[jsarr1.length()];
                    OOPLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OOPName[i] = jsobj.getString("Name");
                        OOPBloodGroup[i] = jsobj.getString("BloodGroup");
                        OOPMobNo[i] = jsobj.getString("MobileNo");
                        OOPemail[i] = jsobj.getString("Email");
                        OOPPincode[i] = jsobj.getString("Pincode");
                        OOPState[i] = jsobj.getString("State");
                        OOPDistrict[i] = jsobj.getString("District");
                        OOPCity[i] = jsobj.getString("City");
                        OOPAddress[i] = jsobj.getString("Address");
                        OOPLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_OP_Activity.this, "RollNo" + OOPName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OPListOwner.setAdapter(ma);

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
                Toast.makeText(Owner_OP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OOPName.length;
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

            TextView OOP_Name = convertView.findViewById(R.id.txtpopname);
            TextView OOP_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView OOP_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView OOP_email = convertView.findViewById(R.id.txtpopemail);
            TextView OOP_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView OOP_State = convertView.findViewById(R.id.txtpopcity);
            TextView OOP_District = convertView.findViewById(R.id.txtpophos);
            TextView OOP_City = convertView.findViewById(R.id.txtpopdate);
            TextView OOP_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView OOP_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            OOP_Name.setText(OOPName[position]);
            OOP_Blood.setText(OOPBloodGroup[position]);
            OOP_Mobno.setText(OOPMobNo[position]);
            OOP_email.setText(OOPemail[position]);
            OOP_Pincode.setText(OOPPincode[position]);
            OOP_State.setText(OOPState[position]);
            OOP_District.setText(OOPDistrict[position]);
            OOP_City.setText(OOPCity[position]);
            OOP_Address.setText(OOPAddress[position]);
            OOP_Landmark.setText(OOPLandmark[position]);
            return convertView;
        }
    }



}