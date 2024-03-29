package com.example.samsdebug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

    private TextView name,dcID,model,phone,status,cost,delivery,payment,textView,offer;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dcID = findViewById(R.id.dcID);
        name = findViewById(R.id.name);
        model = findViewById(R.id.model);
        phone = findViewById(R.id.phone);
        status = findViewById(R.id.status);
        cost = findViewById(R.id.cost);
        delivery = findViewById(R.id.delivery);
        payment = findViewById(R.id.payment);
        textView = findViewById(R.id.textView);
        offer = findViewById(R.id.offer);

        offer.setSelected(true);

        Toast toast = Toast.makeText(this,"LOADING", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();



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
                        String namee = student.getString("Name");
                        String laptop = student.getString("Laptop/Model");
                        String phonee = student.getString("Phone_no");
                        String statuss = student.getString("Status");
                        String idd = student.getString("DC_NO");
     //                        int idd = student.getInt("DC_NO");
                        String costt = student.getString("Aprox_cost");


                        String deliveryStatus = student.getString("Delivery_status");
                        String paymentStatus = student.getString("Payment_status");


                        String offers = student.getString("Offer");
                        offer.setText(offers);


                        //textView.append(id+idd+"\n");




                        if (idd.equals(id)) {

//                            textView.append("DC ID : "+idd+"\n"+"Name : "+name+ "\n" +"Laptop/Model : "+ laptop + "\n"+"Phone : "+phone + "\n" +"Status : "+ status + "\n"+"Aprox cost : "+cost+"\n"+"Delivery status : "+deliveryStatus+"\n"+"Payment status : "+paymentStatus);

                            offer.append(offers);

                            dcID.append(idd+"  ");
                            name.append(namee+"  ");
                            model.append(laptop+"  ");
                            phone.append(phonee+"  ");
                            status.append(statuss+"  ");
                            cost.append(costt+"  ");
                            delivery.append(deliveryStatus+"  ");
                            payment.append(paymentStatus+"  ");
                            break;
                            }else if(i+1 ==jsonArray.length()){
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
    public void contact(View view){
        Intent intent = new Intent(Main2Activity.this,contact.class);
        startActivity(intent);
    }
}
