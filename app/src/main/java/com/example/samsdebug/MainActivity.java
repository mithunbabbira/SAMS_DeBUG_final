package com.example.samsdebug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.textInput);

        editText.setOnEditorActionListener(editorActionListener);


    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

            String ii = String.valueOf(i);
            String ll1 = String.valueOf(EditorInfo.IME_ACTION_GO);
            if (ii.equals(ll1)) {
                Toast.makeText(MainActivity.this, "Please wait", Toast.LENGTH_SHORT).show();
                StatusProcess();
            }
            return false;
        }
    };


    public void status(View view) {
        StatusProcess();
    }

    private void StatusProcess() {

        String value = editText.getText().toString();// make use every format is collect ,so we convert to string


        if (value.length() == 0) {
            editText.setError("Please enter");

        } else {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);// calling the other main java class(other screen
            // above is to open the class

            intent.putExtra("laptopId", value);// sending the value to the  Main2Activity from Mainactivity


            // the below is optional \|/ ,when tranferring mutliple data to other java file but the above is also appicable

//            Bundle bundle = new Bundle();
//            bundle.putString("namebdm",value);
//            //if u have more put bundle.putString("----",value);
//            intent.putExtras(bundle);

            //startActivity(intent); should be after the data transfer
            startActivity(intent);//start activity


        }

    }


    public void contact(View view) {
        Intent intent = new Intent(MainActivity.this, contact.class);
        startActivity(intent);
    }


}
