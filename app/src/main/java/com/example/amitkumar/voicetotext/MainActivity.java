package com.example.amitkumar.voicetotext;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult,length,breadth,height,width,address,contact;
    private EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = (TextView) findViewById(R.id.txtResult);
        length = (TextView) findViewById(R.id.length);
        breadth = (TextView) findViewById(R.id.breadth);
        height = (TextView) findViewById(R.id.height);
        width = (TextView) findViewById(R.id.width);
        address = (TextView) findViewById(R.id.address);
        contact = (TextView) findViewById(R.id.contact);
        edittext = (EditText) findViewById(R.id.edittext);
    }

    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if (resultCode ==RESULT_OK && data !=null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtResult.setText(result.get(0));
                    switch (edittext.getText().toString().trim().toLowerCase()) {
                        case "length":
                            length.setText(result.get(0));
                            break;
                        case "breadth":
                            breadth.setText(result.get(0));
                            break;
                        case "height":
                            height.setText(result.get(0));
                            break;
                        case "width":
                            width.setText(result.get(0));
                            break;
                        case "address":
                            address.setText(result.get(0));
                            break;
                        case "contact":
                            contact.setText(result.get(0));
                            break;
                    }
                }
                break;
        }

    }

}
