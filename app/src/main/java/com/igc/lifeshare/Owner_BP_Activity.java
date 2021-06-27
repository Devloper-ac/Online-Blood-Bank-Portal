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

public class Owner_BP_Activity extends AppCompatActivity {

    ListView OBPAListview;
    String[] OBPAName,OBPABloodGroup,OBPAMobNo,OBPAemail,OBPAPincode,OBPAState,OBPADistrict,OBPACity,OBPAAddress,OBPALandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__b_p_activity);

        OBPAListview = findViewById(R.id.OBPAListview);
        rq = Volley.newRequestQueue(Owner_BP_Activity.this);
        pd = new ProgressDialog(Owner_BP_Activity.this);

        refBP = findViewById(R.id.refBP);
        refBP.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AccessDataOBPA();
                refBP.setRefreshing(false);
            }
        });
        AccessDataOBPA();
    }
    public void AccessDataOBPA()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/BP_OwnerAccess.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OBPAName = new String[jsarr1.length()];
                    OBPABloodGroup = new String[jsarr1.length()];
                    OBPAMobNo = new String[jsarr1.length()];
                    OBPAemail = new String[jsarr1.length()];
                    OBPAPincode = new String[jsarr1.length()];
                    OBPAState = new String[jsarr1.length()];
                    OBPADistrict = new String[jsarr1.length()];
                    OBPACity = new String[jsarr1.length()];
                    OBPAAddress = new String[jsarr1.length()];
                    OBPALandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OBPAName[i] = jsobj.getString("Name");
                        OBPABloodGroup[i] = jsobj.getString("BloodGroup");
                        OBPAMobNo[i] = jsobj.getString("MobileNo");
                        OBPAemail[i] = jsobj.getString("Email");
                        OBPAPincode[i] = jsobj.getString("Pincode");
                        OBPAState[i] = jsobj.getString("State");
                        OBPADistrict[i] = jsobj.getString("District");
                        OBPACity[i] = jsobj.getString("City");
                        OBPAAddress[i] = jsobj.getString("Address");
                        OBPALandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_BP_Activity.this, "RollNo" + OBPAName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OBPAListview.setAdapter(ma);

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
                Toast.makeText(Owner_BP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OBPAName.length;
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

            TextView OBP_Name = convertView.findViewById(R.id.txtpopname);
            TextView OBP_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView OBP_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView OBP_email = convertView.findViewById(R.id.txtpopemail);
            TextView OBP_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView OBP_State = convertView.findViewById(R.id.txtpopcity);
            TextView OBP_District = convertView.findViewById(R.id.txtpophos);
            TextView OBP_City = convertView.findViewById(R.id.txtpopdate);
            TextView OBP_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView OBP_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            OBP_Name.setText(OBPAName[position]);
            OBP_Blood.setText(OBPABloodGroup[position]);
            OBP_Mobno.setText(OBPAMobNo[position]);
            OBP_email.setText(OBPAemail[position]);
            OBP_Pincode.setText(OBPAPincode[position]);
            OBP_State.setText(OBPAState[position]);
            OBP_District.setText(OBPADistrict[position]);
            OBP_City.setText(OBPACity[position]);
            OBP_Address.setText(OBPAAddress[position]);
            OBP_Landmark.setText(OBPALandmark[position]);
            return convertView;
        }
    }
}