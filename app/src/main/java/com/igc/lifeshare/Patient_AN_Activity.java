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

public class Patient_AN_Activity extends AppCompatActivity {

    ListView listpan;
    String[] panName,panBloodGroup,panMobNo,panemail,panPincode,panState,panDistrict,panCity,panAddress,panLandmark;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__a_n_activity);

        listpan = findViewById(R.id.listpan);
        rq = Volley.newRequestQueue(Patient_AN_Activity.this);
        pd = new ProgressDialog(Patient_AN_Activity.this);
        accessdataanpatient();
    }

    public void accessdataanpatient()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/AN_OwnerAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    panName = new String[jsarr1.length()];
                    panBloodGroup = new String[jsarr1.length()];
                    panMobNo = new String[jsarr1.length()];
                    panemail = new String[jsarr1.length()];
                    panPincode = new String[jsarr1.length()];
                    panState = new String[jsarr1.length()];
                    panDistrict = new String[jsarr1.length()];
                    panCity = new String[jsarr1.length()];
                    panAddress = new String[jsarr1.length()];
                    panLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        panName[i] = jsobj.getString("Name");
                        panBloodGroup[i] = jsobj.getString("BloodGroup");
                        panMobNo[i] = jsobj.getString("MobileNo");
                        panemail[i] = jsobj.getString("Email");
                        panPincode[i] = jsobj.getString("Pincode");
                        panState[i] = jsobj.getString("State");
                        panDistrict[i] = jsobj.getString("District");
                        panCity[i] = jsobj.getString("City");
                        panAddress[i] = jsobj.getString("Address");
                        panLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_AN_Activity.this, "RollNo" + panName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listpan.setAdapter(ma);

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
                Toast.makeText(Patient_AN_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return panName.length;
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

            TextView pan_Name = convertView.findViewById(R.id.txtpopname);
            TextView pan_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView pan_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView pan_email = convertView.findViewById(R.id.txtpopemail);
            TextView pan_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView pan_State = convertView.findViewById(R.id.txtpopcity);
            TextView pan_District = convertView.findViewById(R.id.txtpophos);
            TextView pan_City = convertView.findViewById(R.id.txtpopdate);
            TextView pan_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView pan_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            pan_Name.setText(panName[position]);
            pan_Blood.setText(panBloodGroup[position]);
            pan_Mobno.setText(panMobNo[position]);
            pan_email.setText(panemail[position]);
            pan_Pincode.setText(panPincode[position]);
            pan_State.setText(panState[position]);
            pan_District.setText(panDistrict[position]);
            pan_City.setText(panCity[position]);
            pan_Address.setText(panAddress[position]);
            pan_Landmark.setText(panLandmark[position]);
            return convertView;

        }
    }
}