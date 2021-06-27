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

public class Owner_BloodRequest_Activity extends AppCompatActivity {

    ListView OBRList;
    String[] BRName,BRBloodGroup,BRMobNo,BRunit,BRState,BRCity, BRHosName,BRDate,BRMessage;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refOP1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__blood_request_activity);

        OBRList = findViewById(R.id.OBRList);
        rq = Volley.newRequestQueue(Owner_BloodRequest_Activity.this);
        pd = new ProgressDialog(Owner_BloodRequest_Activity.this);
        refOP1 = findViewById(R.id.refOP1);


        refOP1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessBloodRequestData();
                refOP1.setRefreshing(false);
            }
        });

        AccessBloodRequestData();
    }

    public void AccessBloodRequestData()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/Access_BloodRequest.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    BRName = new String[jsarr1.length()];
                    BRBloodGroup = new String[jsarr1.length()];
                    BRMobNo = new String[jsarr1.length()];
                    BRunit = new String[jsarr1.length()];
                    BRState = new String[jsarr1.length()];
                    BRCity = new String[jsarr1.length()];
                    BRHosName = new String[jsarr1.length()];
                    BRDate = new String[jsarr1.length()];
                    BRMessage = new String[jsarr1.length()];

                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        BRName[i] = jsobj.getString("BloodRname");
                        BRBloodGroup[i] = jsobj.getString("BloodR");
                        BRMobNo[i] = jsobj.getString("BloodRnumber");
                        BRunit[i] = jsobj.getString("BloodRUnit");
                        BRState[i] = jsobj.getString("BloodRState");
                        BRCity[i] = jsobj.getString("BloodRCity");
                        BRHosName[i] = jsobj.getString("BloodRHosName");
                        BRDate[i] = jsobj.getString("Datee");
                        BRMessage[i] = jsobj.getString("BloodRMessage");
                        Toast.makeText(Owner_BloodRequest_Activity.this, "RollNo" + BRName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OBRList.setAdapter(ma);

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
                Toast.makeText(Owner_BloodRequest_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return BRName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.viewbloodreqowner,null);

            TextView BR_BRName = convertView.findViewById(R.id.txtpopname);
            TextView BR_BRBloodGroup = convertView.findViewById(R.id.txtpopbloodgroup);
            TextView BR_BRMobNo = convertView.findViewById(R.id.txtpopmobno);
            TextView BR_BRunit = convertView.findViewById(R.id.txtpopemail);
            TextView BR_BRState = convertView.findViewById(R.id.txtpopstate);
            TextView BR_BRCity = convertView.findViewById(R.id.txtpopcity);
            TextView BR_BRHosName = convertView.findViewById(R.id.txtpophos);
            TextView BR_BRDate = convertView.findViewById(R.id.txtpopdate);
            TextView BR_BRMessage = convertView.findViewById(R.id.txtpopmessage);





            BR_BRName.setText(BRName[position]);
            BR_BRBloodGroup.setText(BRBloodGroup[position]);
            BR_BRMobNo.setText(BRMobNo[position]);
            BR_BRunit.setText(BRunit[position]);
            BR_BRState.setText(BRState[position]);
            BR_BRCity.setText(BRCity[position]);
            BR_BRHosName.setText(BRHosName[position]);
            BR_BRDate.setText(BRDate[position]);
            BR_BRMessage.setText(BRMessage[position]);

            return convertView;
        }
    }
}