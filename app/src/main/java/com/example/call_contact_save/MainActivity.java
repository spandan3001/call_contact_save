package com.example.call_contact_save;

import static android.Manifest.permission.CALL_PHONE;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button call,save,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.edittext);
        call=findViewById(R.id.btn_call);
        save=findViewById(R.id.btn_save);
        clear=findViewById(R.id.btn_clear);

        call.setOnClickListener(v -> MakeCall());
        save.setOnClickListener(v -> saveContact());
        clear.setOnClickListener(v -> e1.setText(""));
    }
    public void numberClick(View view){
        String value = e1.getText().toString();
        String id= String.valueOf(view.getId());

        if(id.equals(String.valueOf(R.id.btn_zero))){
            value += "0";
        }
        else if(id.equals(String.valueOf(R.id.btn_One))) {
            value += "1";
        }
        else if(id.equals(String.valueOf(R.id.btn_Two))) {
            value += "2";
        }
        else if(id.equals(String.valueOf(R.id.btn_Three))) {
            value += "3";
        }
        else if(id.equals(String.valueOf(R.id.btn_four))) {
            value += "4";
        }
        else if(id.equals(String.valueOf(R.id.btn_five))) {
            value += "5";
        }
        else if(id.equals(String.valueOf(R.id.btn_six))) {
            value += "6";
        }
        else if(id.equals(String.valueOf(R.id.btn_seven))) {
            value += "7";
        }
        else if(id.equals(String.valueOf(R.id.btn_eight))) {
            value += "8";
        }
        else if(id.equals(String.valueOf(R.id.btn_nine))) {
            value += "9";
        }
        else if(id.equals(String.valueOf(R.id.btn_zero))) {
            value += "0";
        }
        else if(id.equals(String.valueOf(R.id.btn_star))) {
            value += "*";
        }
        else if(id.equals(String.valueOf(R.id.btn_hash))) {
            value += "#";
        }

        e1.setText(value);
    }
    private  void MakeCall(){
        try{
            if(e1.length()==10){
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+e1.getText().toString()));
                if(ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                    startActivity(intent);
                }
                else{
                    requestPermissions(new String[]{CALL_PHONE},1);
                }
            }else{
                Toast.makeText(MainActivity.this,"enter 10 numbers",Toast.LENGTH_LONG).show();

            }
        }catch (Exception ex){
            Toast.makeText(this, ""+ ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void saveContact(){
        try{
            if(e1.length() == 10){
                Intent intent=new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE,e1.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this,"enter 10 numbers",Toast.LENGTH_LONG).show();
            }

        }catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}