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

public class Patient_AP_Activity extends AppCompatActivity {

    ListView listpap;
    String[] papName,papBloodGroup,papMobNo,papemail,papPincode,papState,papDistrict,papCity,papAddress,papLandmark;
    RequestQueue rq;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__a_p_activity);

        listpap = findViewById(R.id.listpap);
        rq = Volley.newRequestQueue(Patient_AP_Activity.this);
        pd = new ProgressDialog(Patient_AP_Activity.this);
        accesspatientapdata();

    }

    public void accesspatientapdata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/AP_Owner_Access.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    papName = new String[jsarr1.length()];
                    papBloodGroup = new String[jsarr1.length()];
                    papMobNo = new String[jsarr1.length()];
                    papemail = new String[jsarr1.length()];
                    papPincode = new String[jsarr1.length()];
                    papState = new String[jsarr1.length()];
                    papDistrict = new String[jsarr1.length()];
                    papCity = new String[jsarr1.length()];
                    papAddress = new String[jsarr1.length()];
                    papLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        papName[i] = jsobj.getString("Name");
                        papBloodGroup[i] = jsobj.getString("BloodGroup");
                        papMobNo[i] = jsobj.getString("MobileNo");
                        papemail[i] = jsobj.getString("Email");
                        papPincode[i] = jsobj.getString("Pincode");
                        papState[i] = jsobj.getString("State");
                        papDistrict[i] = jsobj.getString("District");
                        papCity[i] = jsobj.getString("City");
                        papAddress[i] = jsobj.getString("Address");
                        papLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_AP_Activity.this, "RollNo" + papName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listpap.setAdapter(ma);

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
                Toast.makeText(Patient_AP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return papName.length;
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

            TextView pap_Name = convertView.findViewById(R.id.txtpopname);
            TextView pap_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView pap_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView pap_email = convertView.findViewById(R.id.txtpopemail);
            TextView pap_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView pap_State = convertView.findViewById(R.id.txtpopcity);
            TextView pap_District = convertView.findViewById(R.id.txtpophos);
            TextView pap_City = convertView.findViewById(R.id.txtpopdate);
            TextView pap_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView pap_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            pap_Name.setText(papName[position]);
            pap_Blood.setText(papBloodGroup[position]);
            pap_Mobno.setText(papMobNo[position]);
            pap_email.setText(papemail[position]);
            pap_Pincode.setText(papPincode[position]);
            pap_State.setText(papState[position]);
            pap_District.setText(papDistrict[position]);
            pap_City.setText(papCity[position]);
            pap_Address.setText(papAddress[position]);
            pap_Landmark.setText(papLandmark[position]);
            return convertView;

        }
    }
}