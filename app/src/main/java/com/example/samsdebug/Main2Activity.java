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
//        final String id = getIntent().getStringExtra("laptopId");// collecting from the previous screen (name should be exactly the same)
       final int id = Integer.parseInt(getIntent().getStringExtra("laptopId"));// collecting from the previous screen (name should be exactly the same)


        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();



        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=pBJdLO_2Y07q9d1P_xsvzb8lKwaA66u79YXcbeg6_oEa7SLEAUhUrVVvw7X2EIdjfvcvRAwt-g3BME-Yo2ea5rCUGdER9Mi5OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUYgU2M9v1oTwPFSc4eD-yLcD4N-rKgbIqUtiDktDvWx08QMdrsBNRJEHoB79eRjp75y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray jsonArray = response.getJSONArray("Sheet1");
                    for (int i=0; i < jsonArray.length(); i++){
                        JSONObject student = jsonArray.getJSONObject(i);
                        String name = student.getString("Name");
                        String laptop = student.getString("Laptop");
                        int phone = student.getInt("phone_no");
                        String status = student.getString("Status");
                        int idd = student.getInt("ID");


                        if (idd == id) {
                            textView.append("Name : "+name + "\n " +"Model : "+ laptop + "\n"+"Phone : "+phone + "\n " +"Status : "+ status + "\n"+"ID :"+idd);
//                            String.valueOf(phone)
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
