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

public class Patient_BN_Activity extends AppCompatActivity {

    ListView listpbn;
    String[] pBNName,pBNBloodGroup,pBNMobNo,pBNemail,pBNPincode,pBNState,pBNDistrict,pBNCity,pBNAddress,pBNLandmark;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__b_n_activity);

        listpbn = findViewById(R.id.listpbn);
        rq = Volley.newRequestQueue(Patient_BN_Activity.this);
        pd = new ProgressDialog(Patient_BN_Activity.this);
        accessbnpatientdata();
    }

    public  void  accessbnpatientdata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/BN_OwnerAccess.php";
        JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    pBNName = new String[jsarr1.length()];
                    pBNBloodGroup = new String[jsarr1.length()];
                    pBNMobNo = new String[jsarr1.length()];
                    pBNemail = new String[jsarr1.length()];
                    pBNPincode = new String[jsarr1.length()];
                    pBNState = new String[jsarr1.length()];
                    pBNDistrict = new String[jsarr1.length()];
                    pBNCity = new String[jsarr1.length()];
                    pBNAddress = new String[jsarr1.length()];
                    pBNLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        pBNName[i] = jsobj.getString("Name");
                        pBNBloodGroup[i] = jsobj.getString("BloodGroup");
                        pBNMobNo[i] = jsobj.getString("MobileNo");
                        pBNemail[i] = jsobj.getString("Email");
                        pBNPincode[i] = jsobj.getString("Pincode");
                        pBNState[i] = jsobj.getString("State");
                        pBNDistrict[i] = jsobj.getString("District");
                        pBNCity[i] = jsobj.getString("City");
                        pBNAddress[i] = jsobj.getString("Address");
                        pBNLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Patient_BN_Activity.this, "RollNo" + pBNName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listpbn.setAdapter(ma);

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
                Toast.makeText(Patient_BN_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return pBNName.length;
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

            TextView pBN_Name = convertView.findViewById(R.id.txtpopname);
            TextView pBN_Blood = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView pBN_Mobno = convertView.findViewById(R.id.txtpopmobno);
            TextView pBN_email = convertView.findViewById(R.id.txtpopemail);
            TextView pBN_Pincode = convertView.findViewById(R.id.txtpopstate);
            TextView pBN_State = convertView.findViewById(R.id.txtpopcity);
            TextView pBN_District = convertView.findViewById(R.id.txtpophos);
            TextView pBN_City = convertView.findViewById(R.id.txtpopdate);
            TextView pBN_Address = convertView.findViewById(R.id.txtpopmessage);
            TextView pBN_Landmark = convertView.findViewById(R.id.txtpoplandmark);




            pBN_Name.setText(pBNName[position]);
            pBN_Blood.setText(pBNBloodGroup[position]);
            pBN_Mobno.setText(pBNMobNo[position]);
            pBN_email.setText(pBNemail[position]);
            pBN_Pincode.setText(pBNPincode[position]);
            pBN_State.setText(pBNState[position]);
            pBN_District.setText(pBNDistrict[position]);
            pBN_City.setText(pBNCity[position]);
            pBN_Address.setText(pBNAddress[position]);
            pBN_Landmark.setText(pBNLandmark[position]);
            return convertView;

        }
    }

}