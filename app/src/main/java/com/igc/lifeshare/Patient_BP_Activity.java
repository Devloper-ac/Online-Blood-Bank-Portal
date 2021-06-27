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

public class Patient_BP_Activity extends AppCompatActivity {

    ListView listpbp;
    String[] pBPName,pBPBloodGroup,pBPMobNo,pBPemail,pBPPincode,pBPState,pBPDistrict,pBPCity,pBPAddress,pBPLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__b_p_activity);

        listpbp = findViewById(R.id.listpbp);
        rq = Volley.newRequestQueue(Patient_BP_Activity.this);
        pd = new ProgressDialog(Patient_BP_Activity.this);
        aceesspatientbpdata();
    }

    public void aceesspatientbpdata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/BP_OwnerAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    pBPName = new String[jsarr1.length()];
                    pBPBloodGroup = new String[jsarr1.length()];
                    pBPMobNo = new String[jsarr1.length()];
                    pBPemail = new String[jsarr1.length()];
                    pBPPincode = new String[jsarr1.length()];
                    pBPState = new String[jsarr1.length()];
                    pBPDistrict = new String[jsarr1.length()];
                    pBPCity = new String[jsarr1.length()];
                    pBPAddress = new String[jsarr1.length()];
                    pBPLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        pBPName[i] = jsobj.getString("Name");
                        pBPBloodGroup[i] = jsobj.getString("BloodGroup");
                        pBPMobNo[i] = jsobj.getString("MobileNo");
                        pBPemail[i] = jsobj.getString("Email");
                        pBPPincode[i] = jsobj.getString("Pincode");
                        pBPState[i] = jsobj.getString("State");
                        pBPDistrict[i] = jsobj.getString("District");
                        pBPCity[i] = jsobj.getString("City");
                        pBPAddress[i] = jsobj.getString("Address");
                        pBPLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_BP_Activity.this, "RollNo" + pBPName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listpbp.setAdapter(ma);

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
                Toast.makeText(Patient_BP_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return pBPName.length;
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

            TextView pBP_Name = convertView.findViewById(R.id.txtpopname);
            TextView pBP_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView pBP_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView pBP_email = convertView.findViewById(R.id.txtpopemail);
            TextView pBP_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView pBP_State = convertView.findViewById(R.id.txtpopcity);
            TextView pBP_District = convertView.findViewById(R.id.txtpophos);
            TextView pBP_City = convertView.findViewById(R.id.txtpopdate);
            TextView pBP_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView pBP_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            pBP_Name.setText(pBPName[position]);
            pBP_Blood.setText(pBPBloodGroup[position]);
            pBP_Mobno.setText(pBPMobNo[position]);
            pBP_email.setText(pBPemail[position]);
            pBP_Pincode.setText(pBPPincode[position]);
            pBP_State.setText(pBPState[position]);
            pBP_District.setText(pBPDistrict[position]);
            pBP_City.setText(pBPCity[position]);
            pBP_Address.setText(pBPAddress[position]);
            pBP_Landmark.setText(pBPLandmark[position]);
            return convertView;

        }
    }
}