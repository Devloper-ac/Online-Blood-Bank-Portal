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

public class Patient_ABN_Activity extends AppCompatActivity {

    ListView listpabn;
    String[] abnName,abnBloodGroup,abnMobNo,abnemail,abnPincode,abnState,abnDistrict,abnCity,abnAddress,abnLandmark;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__a_b_n_activity);

        listpabn = findViewById(R.id.listpabn);
        rq = Volley.newRequestQueue(Patient_ABN_Activity.this);
        pd = new ProgressDialog(Patient_ABN_Activity.this);
        accessabndata();
    }

    public void accessabndata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/ABN_OwnerAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    abnName = new String[jsarr1.length()];
                    abnBloodGroup = new String[jsarr1.length()];
                    abnMobNo = new String[jsarr1.length()];
                    abnemail = new String[jsarr1.length()];
                    abnPincode = new String[jsarr1.length()];
                    abnState = new String[jsarr1.length()];
                    abnDistrict = new String[jsarr1.length()];
                    abnCity = new String[jsarr1.length()];
                    abnAddress = new String[jsarr1.length()];
                    abnLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        abnName[i] = jsobj.getString("Name");
                        abnBloodGroup[i] = jsobj.getString("BloodGroup");
                        abnMobNo[i] = jsobj.getString("MobileNo");
                        abnemail[i] = jsobj.getString("Email");
                        abnPincode[i] = jsobj.getString("Pincode");
                        abnState[i] = jsobj.getString("State");
                        abnDistrict[i] = jsobj.getString("District");
                        abnCity[i] = jsobj.getString("City");
                        abnAddress[i] = jsobj.getString("Address");
                        abnLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_ABN_Activity.this, "RollNo" + abnName[i], Toast.LENGTH_SHORT).show();
                    }

                    MyAdapter ma = new MyAdapter();
                    listpabn.setAdapter(ma);

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
                Toast.makeText(Patient_ABN_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return abnName.length;
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

            TextView abn_Name = convertView.findViewById(R.id.txtpopname);
            TextView abn_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView abn_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView abn_email = convertView.findViewById(R.id.txtpopemail);
            TextView abn_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView abn_State = convertView.findViewById(R.id.txtpopcity);
            TextView abn_District = convertView.findViewById(R.id.txtpophos);
            TextView abn_City = convertView.findViewById(R.id.txtpopdate);
            TextView abn_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView abn_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            abn_Name.setText(abnName[position]);
            abn_Blood.setText(abnBloodGroup[position]);
            abn_Mobno.setText(abnMobNo[position]);
            abn_email.setText(abnemail[position]);
            abn_Pincode.setText(abnPincode[position]);
            abn_State.setText(abnState[position]);
            abn_District.setText(abnDistrict[position]);
            abn_City.setText(abnCity[position]);
            abn_Address.setText(abnAddress[position]);
            abn_Landmark.setText(abnLandmark[position]);
            return convertView;

        }
    }
}