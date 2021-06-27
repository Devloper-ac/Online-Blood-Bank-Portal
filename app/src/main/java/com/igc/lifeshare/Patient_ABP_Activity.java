package com.igc.lifeshare;

import androidx.appcompat.app.AppCompatActivity;

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

public class Patient_ABP_Activity extends AppCompatActivity {

    ListView listpabp;
    String[] abpName,abpBloodGroup,abpMobNo,abpemail,abpPincode,abpState,abpDistrict,abpCity,abpAddress,abpLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__a_b_p_activity);

        listpabp = findViewById(R.id.listpabp);
        rq = Volley.newRequestQueue(Patient_ABP_Activity.this);
        pd = new ProgressDialog(Patient_ABP_Activity.this);
        accessdataabp();
    }

    public void accessdataabp()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/ABP_OwnerAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    abpName = new String[jsarr1.length()];
                    abpBloodGroup = new String[jsarr1.length()];
                    abpMobNo = new String[jsarr1.length()];
                    abpemail = new String[jsarr1.length()];
                    abpPincode = new String[jsarr1.length()];
                    abpState = new String[jsarr1.length()];
                    abpDistrict = new String[jsarr1.length()];
                    abpCity = new String[jsarr1.length()];
                    abpAddress = new String[jsarr1.length()];
                    abpLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        abpName[i] = jsobj.getString("Name");
                        abpBloodGroup[i] = jsobj.getString("BloodGroup");
                        abpMobNo[i] = jsobj.getString("MobileNo");
                        abpemail[i] = jsobj.getString("Email");
                        abpPincode[i] = jsobj.getString("Pincode");
                        abpState[i] = jsobj.getString("State");
                        abpDistrict[i] = jsobj.getString("District");
                        abpCity[i] = jsobj.getString("City");
                        abpAddress[i] = jsobj.getString("Address");
                        abpLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_ABP_Activity.this, "RollNo" + abpName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listpabp.setAdapter(ma);

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
                Toast.makeText(Patient_ABP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return abpName.length;
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

            TextView abp_Name = convertView.findViewById(R.id.txtpopname);
            TextView abp_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView abp_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView abp_email = convertView.findViewById(R.id.txtpopemail);
            TextView abp_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView abp_State = convertView.findViewById(R.id.txtpopcity);
            TextView abp_District = convertView.findViewById(R.id.txtpophos);
            TextView abp_City = convertView.findViewById(R.id.txtpopdate);
            TextView abp_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView abp_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            abp_Name.setText(abpName[position]);
            abp_Blood.setText(abpBloodGroup[position]);
            abp_Mobno.setText(abpMobNo[position]);
            abp_email.setText(abpemail[position]);
            abp_Pincode.setText(abpPincode[position]);
            abp_State.setText(abpState[position]);
            abp_District.setText(abpDistrict[position]);
            abp_City.setText(abpCity[position]);
            abp_Address.setText(abpAddress[position]);
            abp_Landmark.setText(abpLandmark[position]);
            return convertView;

        }
    }

}