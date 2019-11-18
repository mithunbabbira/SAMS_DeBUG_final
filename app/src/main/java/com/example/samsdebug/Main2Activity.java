package com.example.samsdebug;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    private TextView textView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.result);

       final String id = getIntent().getStringExtra("laptopId");// collecting from the previous screen (name should be exactly the same)
//      final int id = Integer.parseInt(getIntent().getStringExtra("laptopId"));// collecting from the previous screen (name should be exactly the same)


        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();




        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=mtiRBNZPjH8LlBhIAaXcFF-tbGkl_Tke6KDH4s2gtlq3_8yGTeCMsqmW_crt2IWVA5HfiAT4RwySp1awpvxJm6CZ--bpMFrHOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUXbwfl4vKUFagc_f4QNM6dR4Lj4t8CH39sCVO2ZGMyEH3aAJF6OGQ8ogXUTGdbFrG5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray jsonArray = response.getJSONArray("Sheet1");
                    for (int i=0; i < jsonArray.length(); i++){
                        JSONObject student = jsonArray.getJSONObject(i);
                        String name = student.getString("Name");
                        String laptop = student.getString("Laptop/Model");
                        String phone = student.getString("Phone_no");
                        String status = student.getString("Status");
                        String idd = student.getString("DC_NO");
//                        int idd = student.getInt("DC_NO");
                        int cost = student.getInt("Aprox_cost");
                        String deliveryStatus = student.getString("Delivery_status");
                        String paymentStatus = student.getString("Payment_status");
                        //textView.append(id+idd+"\n");


                        if (idd.equals(id)) {

                            textView.append("DC ID : "+idd+"\n"+"Name : "+name + "\n" +"Laptop/Model : "+ laptop + "\n"+"Phone : "+phone + "\n" +"Status : "+ status + "\n"+"Aprox cost : "+cost+"\n"+"Delivery status : "+deliveryStatus+"\n"+"Payment status : "+paymentStatus);
//
                                 break;

                            }else{
                            textView.setText("Please enter valid ID");
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }
}
