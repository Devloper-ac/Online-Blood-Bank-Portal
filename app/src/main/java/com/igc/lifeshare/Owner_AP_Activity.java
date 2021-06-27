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

public class Owner_AP_Activity extends AppCompatActivity {

    ListView OAPListview;
    String[] OAPName,OAPBloodGroup,OAPMobNo,OAPemail,OAPPincode,OAPState,OAPDistrict,OAPCity,OAPAddress,OAPLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__a_p_activity);
        OAPListview = findViewById(R.id.OAPListview);
        rq = Volley.newRequestQueue(Owner_AP_Activity.this);
        pd = new ProgressDialog(Owner_AP_Activity.this);
        refAP = findViewById(R.id.refAP);
        refAP.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessDataOAP();
                refAP.setRefreshing(false);
            }
        });
        AccessDataOAP();
    }

    public void AccessDataOAP()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/AP_Owner_Access.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OAPName = new String[jsarr1.length()];
                    OAPBloodGroup = new String[jsarr1.length()];
                    OAPMobNo = new String[jsarr1.length()];
                    OAPemail = new String[jsarr1.length()];
                    OAPPincode = new String[jsarr1.length()];
                    OAPState = new String[jsarr1.length()];
                    OAPDistrict = new String[jsarr1.length()];
                    OAPCity = new String[jsarr1.length()];
                    OAPAddress = new String[jsarr1.length()];
                    OAPLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OAPName[i] = jsobj.getString("Name");
                        OAPBloodGroup[i] = jsobj.getString("BloodGroup");
                        OAPMobNo[i] = jsobj.getString("MobileNo");
                        OAPemail[i] = jsobj.getString("Email");
                        OAPPincode[i] = jsobj.getString("Pincode");
                        OAPState[i] = jsobj.getString("State");
                        OAPDistrict[i] = jsobj.getString("District");
                        OAPCity[i] = jsobj.getString("City");
                        OAPAddress[i] = jsobj.getString("Address");
                        OAPLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_AP_Activity.this, "RollNo" + OAPName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OAPListview.setAdapter(ma);

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
                Toast.makeText(Owner_AP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);

    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OAPName.length;
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

            TextView  OAP_Name = convertView.findViewById(R.id.txtpopname);
            TextView  OAP_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView  OAP_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView  OAP_email = convertView.findViewById(R.id.txtpopemail);
            TextView  OAP_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView  OAP_State = convertView.findViewById(R.id.txtpopcity);
            TextView  OAP_District = convertView.findViewById(R.id.txtpophos);
            TextView  OAP_City = convertView.findViewById(R.id.txtpopdate);
            TextView  OAP_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView  OAP_Landmark = convertView.findViewById(R.id.txtpoplandmark);





            OAP_Name.setText(OAPName[position]);
            OAP_Blood.setText(OAPBloodGroup[position]);
            OAP_Mobno.setText(OAPMobNo[position]);
            OAP_email.setText(OAPemail[position]);
            OAP_Pincode.setText(OAPPincode[position]);
            OAP_State.setText(OAPState[position]);
            OAP_District.setText(OAPDistrict[position]);
            OAP_City.setText(OAPCity[position]);
            OAP_Address.setText(OAPAddress[position]);
            OAP_Landmark.setText(OAPLandmark[position]);
            return convertView;
        }
    }
}