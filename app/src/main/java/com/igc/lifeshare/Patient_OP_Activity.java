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

public class Patient_OP_Activity extends AppCompatActivity {

    ListView listoppatient;
    String[] popName,popBloodGroup,popMobNo,popemail,popPincode,popState,popDistrict,popCity,popAddress,popLandmark;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__o_p_activity);

        listoppatient = findViewById(R.id.listoppatient);
        rq = Volley.newRequestQueue(Patient_OP_Activity.this);
        pd = new ProgressDialog(Patient_OP_Activity.this);
        accesspopdata();
    }

    public void accesspopdata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/OP_OwnerAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    popName = new String[jsarr1.length()];
                    popBloodGroup = new String[jsarr1.length()];
                    popMobNo = new String[jsarr1.length()];
                    popemail = new String[jsarr1.length()];
                    popPincode = new String[jsarr1.length()];
                    popState = new String[jsarr1.length()];
                    popDistrict = new String[jsarr1.length()];
                    popCity = new String[jsarr1.length()];
                    popAddress = new String[jsarr1.length()];
                    popLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        popName[i] = jsobj.getString("Name");
                        popBloodGroup[i] = jsobj.getString("BloodGroup");
                        popMobNo[i] = jsobj.getString("MobileNo");
                        popemail[i] = jsobj.getString("Email");
                        popPincode[i] = jsobj.getString("Pincode");
                        popState[i] = jsobj.getString("State");
                        popDistrict[i] = jsobj.getString("District");
                        popCity[i] = jsobj.getString("City");
                        popAddress[i] = jsobj.getString("Address");
                        popLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_OP_Activity.this, "RollNo" + popName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listoppatient.setAdapter(ma);

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
                Toast.makeText(Patient_OP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return popName.length;
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

            TextView pop_Name = convertView.findViewById(R.id.txtpopname);
            TextView pop_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView pop_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView pop_email = convertView.findViewById(R.id.txtpopemail);
            TextView pop_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView pop_State = convertView.findViewById(R.id.txtpopcity);
            TextView pop_District = convertView.findViewById(R.id.txtpophos);
            TextView pop_City = convertView.findViewById(R.id.txtpopdate);
            TextView pop_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView pop_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            pop_Name.setText(popName[position]);
            pop_Blood.setText(popBloodGroup[position]);
            pop_Mobno.setText(popMobNo[position]);
            pop_email.setText(popemail[position]);
            pop_Pincode.setText(popPincode[position]);
            pop_State.setText(popState[position]);
            pop_District.setText(popDistrict[position]);
            pop_City.setText(popCity[position]);
            pop_Address.setText(popAddress[position]);
            pop_Landmark.setText(popLandmark[position]);
            return convertView;

        }
    }
}