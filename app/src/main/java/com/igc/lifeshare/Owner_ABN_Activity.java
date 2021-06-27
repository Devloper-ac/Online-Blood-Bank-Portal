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

public class Owner_ABN_Activity extends AppCompatActivity {

    ListView OwnerlytABN;
    String[] OName,OBloodGroup,OMobNo,Oemail,OPincode,OState,ODistrict,OCity,OAddress,OLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refABN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__a_b_n_activity);
        OwnerlytABN = findViewById(R.id.OwnerlytABN);
        rq = Volley.newRequestQueue(Owner_ABN_Activity.this);
        pd = new ProgressDialog(Owner_ABN_Activity.this);
        refABN = findViewById(R.id.refABN);
        refABN.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessData1();
                refABN.setRefreshing(false);
            }
        });

        AccessData1();

    }

    public void AccessData1() {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/Owner_AccessData.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                        JSONArray jsarr1 = response.getJSONArray("Result");

                        OName = new String[jsarr1.length()];
                        OBloodGroup = new String[jsarr1.length()];
                        OMobNo = new String[jsarr1.length()];
                        Oemail = new String[jsarr1.length()];
                        OPincode = new String[jsarr1.length()];
                        OState = new String[jsarr1.length()];
                        ODistrict = new String[jsarr1.length()];
                        OCity = new String[jsarr1.length()];
                        OAddress = new String[jsarr1.length()];
                        OLandmark = new String[jsarr1.length()];
                        JSONObject jsobj;
                        for (int i = 0; i < jsarr1.length(); i++) {
                            jsobj = jsarr1.getJSONObject(i);

                            OName[i] = jsobj.getString("Name");
                            OBloodGroup[i] = jsobj.getString("BloodGroup");
                            OMobNo[i] = jsobj.getString("MobileNo");
                            Oemail[i] = jsobj.getString("Email");
                            OPincode[i] = jsobj.getString("Pincode");
                            OState[i] = jsobj.getString("State");
                            ODistrict[i] = jsobj.getString("District");
                            OCity[i] = jsobj.getString("City");
                            OAddress[i] = jsobj.getString("Address");
                            OLandmark[i] = jsobj.getString("Landmark");
                            Toast.makeText(Owner_ABN_Activity.this, "RollNo" + OName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OwnerlytABN.setAdapter(ma);

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
                Toast.makeText(Owner_ABN_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OName.length;
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

            TextView tName = convertView.findViewById(R.id.txtpopname);
            TextView tBlood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView tMobno = convertView.findViewById(R.id.txtpopmobno);
            TextView temail = convertView.findViewById(R.id.txtpopemail);
            TextView tPincode = convertView.findViewById(R.id.txtpopstate);
            TextView tState = convertView.findViewById(R.id.txtpopcity);
            TextView tDistrict = convertView.findViewById(R.id.txtpophos);
            TextView tCity = convertView.findViewById(R.id.txtpopdate);
            TextView tAddress = convertView.findViewById(R.id.txtpopmessage);
            TextView tLandmark = convertView.findViewById(R.id.txtpoplandmark);




            tName.setText(OName[position]);
            tBlood.setText(OBloodGroup[position]);
            tMobno.setText(OMobNo[position]);
            temail.setText(Oemail[position]);
            tPincode.setText(OPincode[position]);
            tState.setText(OState[position]);
            tDistrict.setText(ODistrict[position]);
            tCity.setText(OCity[position]);
            tAddress.setText(OAddress[position]);
            tLandmark.setText(OLandmark[position]);
            return convertView;
        }
    }

}

/*

 ListView lyst;
    String[] RollNo,Name,Fees;
    RequestQueue rq;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lyst = findViewById(R.id.lyst);
        rq = Volley.newRequestQueue(MainActivity.this);
        pd = new ProgressDialog(MainActivity.this);
    }

    public void AccessData(View view) {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://myonlinesite91.000webhostapp.com/StudAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr = response.getJSONArray("Result");
                    RollNo = new String[jsarr.length()];
                    Name = new String[jsarr.length()];
                    Fees = new String[jsarr.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr.length(); i++) {
                        jsobj = jsarr.getJSONObject(i);
                        RollNo[i] = jsobj.getString("RollNo");
                        Name[i] = jsobj.getString("Name");
                        Fees[i] = jsobj.getString("Fees");
                        Toast.makeText(MainActivity.this, "RollNo" + RollNo[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    lyst.setAdapter(ma);

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
                Toast.makeText(MainActivity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return Name.length;
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
            convertView = getLayoutInflater().inflate(R.layout.access_list_activity,null);
            TextView txtRollNo = convertView.findViewById(R.id.txtRollNo);
            TextView txtName = convertView.findViewById(R.id.txtName);
            TextView txtFees = convertView.findViewById(R.id.txtFees);

            txtRollNo.setText(RollNo[position]);
            txtName.setText(Name[position]);
            txtFees.setText(Fees[position]);
            return convertView;
        }
    }

}
 */