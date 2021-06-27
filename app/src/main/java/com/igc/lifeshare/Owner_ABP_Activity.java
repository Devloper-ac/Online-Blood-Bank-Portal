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

public class Owner_ABP_Activity extends AppCompatActivity {

    ListView listOwnerABP;
    String[] OABPName,OABPBloodGroup,OABPMobNo,OABPemail,OABPPincode,OABPState,OABPDistrict,OABPCity,OABPAddress,OABPLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refABP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__a_b_p_activity);
        listOwnerABP = findViewById(R.id.listOwnerABP);
        rq = Volley.newRequestQueue(Owner_ABP_Activity.this);
        pd = new ProgressDialog(Owner_ABP_Activity.this);

        refABP = findViewById(R.id.refABP);
        refABP.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AccessData();
                refABP.setRefreshing(false);
            }
        });
        AccessData();
    }

    public void AccessData() {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/ABP_OwnerAccess.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OABPName = new String[jsarr1.length()];
                    OABPBloodGroup = new String[jsarr1.length()];
                    OABPMobNo = new String[jsarr1.length()];
                    OABPemail = new String[jsarr1.length()];
                    OABPPincode = new String[jsarr1.length()];
                    OABPState = new String[jsarr1.length()];
                    OABPDistrict = new String[jsarr1.length()];
                    OABPCity = new String[jsarr1.length()];
                    OABPAddress = new String[jsarr1.length()];
                    OABPLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OABPName[i] = jsobj.getString("Name");
                        OABPBloodGroup[i] = jsobj.getString("BloodGroup");
                        OABPMobNo[i] = jsobj.getString("MobileNo");
                        OABPemail[i] = jsobj.getString("Email");
                        OABPPincode[i] = jsobj.getString("Pincode");
                        OABPState[i] = jsobj.getString("State");
                        OABPDistrict[i] = jsobj.getString("District");
                        OABPCity[i] = jsobj.getString("City");
                        OABPAddress[i] = jsobj.getString("Address");
                        OABPLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_ABP_Activity.this, "RollNo" + OABPName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listOwnerABP.setAdapter(ma);

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
                Toast.makeText(Owner_ABP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OABPName.length;
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

            TextView OABP_Name = convertView.findViewById(R.id.txtpopname);
            TextView OABP_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView OABP_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView OABP_email = convertView.findViewById(R.id.txtpopemail);
            TextView OABP_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView OABP_State = convertView.findViewById(R.id.txtpopcity);
            TextView OABP_District = convertView.findViewById(R.id.txtpophos);
            TextView OABP_City = convertView.findViewById(R.id.txtpopdate);
            TextView OABP_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView OABP_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            OABP_Name.setText(OABPName[position]);
            OABP_Blood.setText(OABPBloodGroup[position]);
            OABP_Mobno.setText(OABPMobNo[position]);
            OABP_email.setText(OABPemail[position]);
            OABP_Pincode.setText(OABPPincode[position]);
            OABP_State.setText(OABPState[position]);
            OABP_District.setText(OABPDistrict[position]);
            OABP_City.setText(OABPCity[position]);
            OABP_Address.setText(OABPAddress[position]);
            OABP_Landmark.setText(OABPLandmark[position]);
            return convertView;
        }
    }




}
