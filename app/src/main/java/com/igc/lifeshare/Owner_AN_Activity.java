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

public class Owner_AN_Activity extends AppCompatActivity {

    ListView OANListview;
    String[] OANName,OANBloodGroup,OANMobNo,OANemail,OANPincode,OANState,OANDistrict,OANCity,OANAddress,OANLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__a_n_activity);

        OANListview = findViewById(R.id.OANListview);
        rq = Volley.newRequestQueue(Owner_AN_Activity.this);
        pd = new ProgressDialog(Owner_AN_Activity.this);

        refAN = findViewById(R.id.refAN);
        refAN.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessDataOAN();
                refAN.setRefreshing(false);
            }
        });

        AccessDataOAN();
    }

    public void AccessDataOAN()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/AN_OwnerAccess.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OANName = new String[jsarr1.length()];
                    OANBloodGroup = new String[jsarr1.length()];
                    OANMobNo = new String[jsarr1.length()];
                    OANemail = new String[jsarr1.length()];
                    OANPincode = new String[jsarr1.length()];
                    OANState = new String[jsarr1.length()];
                    OANDistrict = new String[jsarr1.length()];
                    OANCity = new String[jsarr1.length()];
                    OANAddress = new String[jsarr1.length()];
                    OANLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OANName[i] = jsobj.getString("Name");
                        OANBloodGroup[i] = jsobj.getString("BloodGroup");
                        OANMobNo[i] = jsobj.getString("MobileNo");
                        OANemail[i] = jsobj.getString("Email");
                        OANPincode[i] = jsobj.getString("Pincode");
                        OANState[i] = jsobj.getString("State");
                        OANDistrict[i] = jsobj.getString("District");
                        OANCity[i] = jsobj.getString("City");
                        OANAddress[i] = jsobj.getString("Address");
                        OANLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_AN_Activity.this, "RollNo" + OANName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OANListview.setAdapter(ma);

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
                Toast.makeText(Owner_AN_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OANName.length;
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

            TextView  OAN_Name = convertView.findViewById(R.id.txtpopname);
            TextView  OAN_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView  OAN_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView  OAN_email = convertView.findViewById(R.id.txtpopemail);
            TextView  OAN_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView  OAN_State = convertView.findViewById(R.id.txtpopcity);
            TextView  OAN_District = convertView.findViewById(R.id.txtpophos);
            TextView  OAN_City = convertView.findViewById(R.id.txtpopdate);
            TextView  OAN_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView  OAN_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            OAN_Name.setText(OANName[position]);
            OAN_Blood.setText(OANBloodGroup[position]);
            OAN_Mobno.setText(OANMobNo[position]);
            OAN_email.setText(OANemail[position]);
            OAN_Pincode.setText(OANPincode[position]);
            OAN_State.setText(OANState[position]);
            OAN_District.setText(OANDistrict[position]);
            OAN_City.setText(OANCity[position]);
            OAN_Address.setText(OANAddress[position]);
            OAN_Landmark.setText(OANLandmark[position]);
            return convertView;
        }
    }
}