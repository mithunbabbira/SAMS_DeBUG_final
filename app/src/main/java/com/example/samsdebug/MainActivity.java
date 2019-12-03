package com.example.samsdebug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

    }

    public void contact(View view){
        Intent intent = new Intent(MainActivity.this,contact.class);
        startActivity(intent);
    }

    public void status(View view){
        String value = editText.getText().toString();// make use every format is collect ,so we convert to string


        if(value.length()==0){
            editText.setError("Please enter");

        }else{
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);// calling the other main java class(other screen
            // above is to open the class

            intent.putExtra("laptopId",value);// sending the value to the  Main2Activity from Mainactivity


            // the below is optional \|/ ,when tranferring mutliple data to other java file but the above is also appicable

//            Bundle bundle = new Bundle();
//            bundle.putString("namebdm",value);
//            //if u have more put bundle.putString("----",value);
//            intent.putExtras(bundle);

            //startActivity(intent); should be after the data transfer
            startActivity(intent);//start activity



        }

    }
}
