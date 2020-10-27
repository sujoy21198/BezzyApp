package com.bezzy.Ui.View.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bezzy.Ui.View.Utils.APIs;
import com.bezzy.Ui.View.Utils.Utility;
import com.example.easy_application.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Editprofile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextInputEditText ed_name, ed_username, ed_email,ed_dob,ed_bio;
    Spinner spinner;
    private String str_gender;
    Button btn_update;
    ImageView imageView;
    DatePickerDialog datePickerDialog;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        btn_update=findViewById(R.id.update);
        ed_dob = findViewById(R.id.dob);
        spinner = findViewById(R.id.spinner);
        ed_email = findViewById(R.id.email);
        ed_name = findViewById(R.id.fullname);
        ed_username = findViewById(R.id.username);
        ed_bio = findViewById(R.id.bio);

        ed_username.setText(Utility.getUserName(Editprofile.this));
        ed_name.setText(Utility.getName(Editprofile.this));
        ed_email.setText(Utility.getEmail(Editprofile.this));
        ed_dob.setText(Utility.getdob(Editprofile.this));
        //str_gender.equals(Utility.getGender(Editprofile.this));


        progressDialog = new ProgressDialog(Editprofile.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Please Wait..");


        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);

        imageView=findViewById(R.id.back_image);


        ed_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(Editprofile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = month + " - " + dayOfMonth + " - " + year;
                        ed_dob.setText(date);

                    }
                },month,day,year);
                datePickerDialog.show();

            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinnerr);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Updateprofile();
            }
        });

    }
    private void callApiUpdateProfile(String url) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if(object.getString("resp").equals("success")){
                        progressDialog.dismiss();
                        Toast.makeText(Editprofile.this,object.getString("message"),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Editprofile.this,Profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                    }else{
                        progressDialog.dismiss();
                        //Toast.makeText(Editprofile.this,object.getString("reg_msg"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Log.e("Exception",e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("userID",Utility.getUserId(Editprofile.this));
                map.put("username",ed_username.getText().toString());
                map.put("fullname",ed_name.getText().toString());
                map.put("email",ed_email.getText().toString());
                map.put("dob",ed_dob.getText().toString());
                map.put("gender",str_gender);
                map.put("user_bio",ed_bio.getText().toString());

                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(Editprofile.this);
        queue.add(request);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        str_gender = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void Updateprofile(){
        if(Utility.internet_check(Editprofile.this)) {
            progressDialog.show();
            callApiUpdateProfile(APIs.BASE_URL+APIs.UPDATEPROFILE);
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(Editprofile.this,"No Network!",Toast.LENGTH_SHORT).show();
        }


    }
}