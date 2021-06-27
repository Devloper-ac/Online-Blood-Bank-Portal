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

public class Owner_BN_Activity extends AppCompatActivity {

    ListView OBNAListview;
    String[] OBNAName,OBNABloodGroup,OBNAMobNo,OBNAemail,OBNAPincode,OBNAState,OBNADistrict,OBNACity,OBNAAddress,OBNALandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refBN;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__b_n_activity);

        OBNAListview = findViewById(R.id.OBNAListview);
        rq = Volley.newRequestQueue(Owner_BN_Activity.this);
        pd = new ProgressDialog(Owner_BN_Activity.this);

        refBN = findViewById(R.id.refBN);
        refBN.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessDataOBNA();
                refBN.setRefreshing(false);
            }
        });
        AccessDataOBNA();
    }

    public void AccessDataOBNA()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/BN_OwnerAccess.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OBNAName = new String[jsarr1.length()];
                    OBNABloodGroup = new String[jsarr1.length()];
                    OBNAMobNo = new String[jsarr1.length()];
                    OBNAemail = new String[jsarr1.length()];
                    OBNAPincode = new String[jsarr1.length()];
                    OBNAState = new String[jsarr1.length()];
                    OBNADistrict = new String[jsarr1.length()];
                    OBNACity = new String[jsarr1.length()];
                    OBNAAddress = new String[jsarr1.length()];
                    OBNALandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OBNAName[i] = jsobj.getString("Name");
                        OBNABloodGroup[i] = jsobj.getString("BloodGroup");
                        OBNAMobNo[i] = jsobj.getString("MobileNo");
                        OBNAemail[i] = jsobj.getString("Email");
                        OBNAPincode[i] = jsobj.getString("Pincode");
                        OBNAState[i] = jsobj.getString("State");
                        OBNADistrict[i] = jsobj.getString("District");
                        OBNACity[i] = jsobj.getString("City");
                        OBNAAddress[i] = jsobj.getString("Address");
                        OBNALandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_BN_Activity.this, "RollNo" + OBNAName[i], Toast.LENGTH_SHORT).show();
                    }
                   MyAdapter ma = new MyAdapter();
                    OBNAListview.setAdapter(ma);

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
                Toast.makeText(Owner_BN_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OBNAName.length;
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

            TextView OBN_Name = convertView.findViewById(R.id.txtpopname);
            TextView OBN_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView OBN_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView OBN_email = convertView.findViewById(R.id.txtpopemail);
            TextView OBN_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView OBN_State = convertView.findViewById(R.id.txtpopcity);
            TextView OBN_District = convertView.findViewById(R.id.txtpophos);
            TextView OBN_City = convertView.findViewById(R.id.txtpopdate);
            TextView OBN_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView OBN_Landmark = convertView.findViewById(R.id.txtpoplandmark);





            OBN_Name.setText(OBNAName[position]);
            OBN_Blood.setText(OBNABloodGroup[position]);
            OBN_Mobno.setText(OBNAMobNo[position]);
            OBN_email.setText(OBNAemail[position]);
            OBN_Pincode.setText(OBNAPincode[position]);
            OBN_State.setText(OBNAState[position]);
            OBN_District.setText(OBNADistrict[position]);
            OBN_City.setText(OBNACity[position]);
            OBN_Address.setText(OBNAAddress[position]);
            OBN_Landmark.setText(OBNALandmark[position]);
            return convertView;
        }
    }
}