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

public class Patient_ON_Activity extends AppCompatActivity {

    ListView listonpatient;
    String[] ponName,ponBloodGroup,ponMobNo,ponemail,ponPincode,ponState,ponDistrict,ponCity,ponAddress,ponLandmark;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__o_n_activity);

        listonpatient = findViewById(R.id.listonpatient);
        rq = Volley.newRequestQueue(Patient_ON_Activity.this);
        pd = new ProgressDialog(Patient_ON_Activity.this);
        accespatientondata();
    }

    public void accespatientondata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/ON_OwnerAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    ponName = new String[jsarr1.length()];
                    ponBloodGroup = new String[jsarr1.length()];
                    ponMobNo = new String[jsarr1.length()];
                    ponemail = new String[jsarr1.length()];
                    ponPincode = new String[jsarr1.length()];
                    ponState = new String[jsarr1.length()];
                    ponDistrict = new String[jsarr1.length()];
                    ponCity = new String[jsarr1.length()];
                    ponAddress = new String[jsarr1.length()];
                    ponLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        ponName[i] = jsobj.getString("Name");
                        ponBloodGroup[i] = jsobj.getString("BloodGroup");
                        ponMobNo[i] = jsobj.getString("MobileNo");
                        ponemail[i] = jsobj.getString("Email");
                        ponPincode[i] = jsobj.getString("Pincode");
                        ponState[i] = jsobj.getString("State");
                        ponDistrict[i] = jsobj.getString("District");
                        ponCity[i] = jsobj.getString("City");
                        ponAddress[i] = jsobj.getString("Address");
                        ponLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_ON_Activity.this, "RollNo" + ponName[i], Toast.LENGTH_SHORT).show();
                    }
                 MyAdapter ma = new MyAdapter();
                    listonpatient.setAdapter(ma);

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
                Toast.makeText(Patient_ON_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return ponName.length;
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

            TextView pon_Name = convertView.findViewById(R.id.txtpopname);
            TextView pon_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView pon_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView pon_email = convertView.findViewById(R.id.txtpopemail);
            TextView pon_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView pon_State = convertView.findViewById(R.id.txtpopcity);
            TextView pon_District = convertView.findViewById(R.id.txtpophos);
            TextView pon_City = convertView.findViewById(R.id.txtpopdate);
            TextView pon_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView pon_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            pon_Name.setText(ponName[position]);
            pon_Blood.setText(ponBloodGroup[position]);
            pon_Mobno.setText(ponMobNo[position]);
            pon_email.setText(ponemail[position]);
            pon_Pincode.setText(ponPincode[position]);
            pon_State.setText(ponState[position]);
            pon_District.setText(ponDistrict[position]);
            pon_City.setText(ponCity[position]);
            pon_Address.setText(ponAddress[position]);
            pon_Landmark.setText(ponLandmark[position]);
            return convertView;

        }
    }
}