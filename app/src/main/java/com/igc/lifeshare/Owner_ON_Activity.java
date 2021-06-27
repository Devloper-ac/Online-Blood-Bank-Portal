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

public class Owner_ON_Activity extends AppCompatActivity {

    ListView OONLisst;
    String[] OONName,OONBloodGroup,OONMobNo,OONemail,OONPincode,OONState,OONDistrict,OONCity,OONAddress,OONLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__o_n_activity);

        OONLisst = findViewById(R.id.OONLisst);
        rq = Volley.newRequestQueue(Owner_ON_Activity.this);
        pd = new ProgressDialog(Owner_ON_Activity.this);

        refON = findViewById(R.id.refON);
        refON.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessDataOON();
                refON.setRefreshing(false);
            }
        });
        AccessDataOON();
    }

    public void AccessDataOON()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/ON_OwnerAccess.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OONName = new String[jsarr1.length()];
                    OONBloodGroup = new String[jsarr1.length()];
                    OONMobNo = new String[jsarr1.length()];
                    OONemail = new String[jsarr1.length()];
                    OONPincode = new String[jsarr1.length()];
                    OONState = new String[jsarr1.length()];
                    OONDistrict = new String[jsarr1.length()];
                    OONCity = new String[jsarr1.length()];
                    OONAddress = new String[jsarr1.length()];
                    OONLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OONName[i] = jsobj.getString("Name");
                        OONBloodGroup[i] = jsobj.getString("BloodGroup");
                        OONMobNo[i] = jsobj.getString("MobileNo");
                        OONemail[i] = jsobj.getString("Email");
                        OONPincode[i] = jsobj.getString("Pincode");
                        OONState[i] = jsobj.getString("State");
                        OONDistrict[i] = jsobj.getString("District");
                        OONCity[i] = jsobj.getString("City");
                        OONAddress[i] = jsobj.getString("Address");
                        OONLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_ON_Activity.this, "RollNo" + OONName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OONLisst.setAdapter(ma);

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
                Toast.makeText(Owner_ON_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);

    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OONName.length;
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

            TextView OON_Name = convertView.findViewById(R.id.txtpopname);
            TextView OON_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView OON_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView OON_email = convertView.findViewById(R.id.txtpopemail);
            TextView OON_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView OON_State = convertView.findViewById(R.id.txtpopcity);
            TextView OON_District = convertView.findViewById(R.id.txtpophos);
            TextView OON_City = convertView.findViewById(R.id.txtpopdate);
            TextView OON_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView OON_Landmark = convertView.findViewById(R.id.txtpoplandmark);





            OON_Name.setText(OONName[position]);
            OON_Blood.setText(OONBloodGroup[position]);
            OON_Mobno.setText(OONMobNo[position]);
            OON_email.setText(OONemail[position]);
            OON_Pincode.setText(OONPincode[position]);
            OON_State.setText(OONState[position]);
            OON_District.setText(OONDistrict[position]);
            OON_City.setText(OONCity[position]);
            OON_Address.setText(OONAddress[position]);
            OON_Landmark.setText(OONLandmark[position]);
            return convertView;
        }
    }

}