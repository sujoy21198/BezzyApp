package com.bezzy.Ui.View.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.bezzy.Ui.View.Utils.APIs;
import com.bezzy.Ui.View.Utils.Utility;
import com.example.easy_application.R;
import com.bezzy.Ui.View.Controller.SessionManager;
import com.bezzy.Ui.View.Model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextInputEditText ed_name, ed_username, ed_email, ed_password, ed_cnfpasswd,ed_dob;
    Spinner spinner;
    private String str_gender;
    Button btn_register;
    ImageView imageView;
    DatePickerDialog datePickerDialog;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);
        ed_email = findViewById(R.id.email);
        ed_password = findViewById(R.id.password);
        ed_name = findViewById(R.id.fullname);
        ed_username = findViewById(R.id.username);
        ed_cnfpasswd = findViewById(R.id.cnf_password);
        ed_dob = findViewById(R.id.dob);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);
        spinner = findViewById(R.id.spinner);
        imageView=findViewById(R.id.back_image);
        progressDialog = new ProgressDialog(Registration.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Please Wait..");

        //ed_gender = findViewById(R.id.gender);
        btn_register = findViewById(R.id.register);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }
        });

        ed_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {
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

    }
    public void Register() {
        if(ed_username.getText().toString().isEmpty()){
            ed_username.setError("enter name");
        }else if(ed_email.getText().toString().isEmpty()){
            ed_email.setError("enter email");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches()){
            ed_email.setError("enter valid email");
        }else if(ed_password.getText().toString().isEmpty()){
            ed_password.setError("enter password");
        }else if(ed_password.getText().toString().length() < 8){
            ed_password.setError("password must of at least 8 characters");
        }else if(ed_cnfpasswd.getText().toString().isEmpty()){
            ed_cnfpasswd.setError("enter confirm password");
        }else if(!ed_cnfpasswd.getText().toString().equals(ed_password.getText().toString())){
            ed_cnfpasswd.setError("password not matched");
        }else if(ed_dob.getText().toString().isEmpty()){
            ed_dob.setError("Enter Date of Birth");
        }else if(str_gender.equals("Select Your Gender")){
            Toast.makeText(Registration.this, "Please select your gender", Toast.LENGTH_SHORT).show();
        }else{
            if(Utility.internet_check(Registration.this)) {
                progressDialog.show();
                callApiRegistration(APIs.BASE_URL+APIs.REGISTRATION);
            }
            else {
                progressDialog.dismiss();
                Toast.makeText(Registration.this,"No Network!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void callApiRegistration(String url) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if(object.getString("resp").equals("true")){
                        progressDialog.dismiss();
                        Toast.makeText(Registration.this,object.getString("reg_msg"),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration.this,OTPActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Utility.setOtpScreen(Registration.this,"1");
                        Utility.setUserId(Registration.this,object.getString("log_userID"));
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Registration.this,object.getString("reg_msg"),Toast.LENGTH_SHORT).show();
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
                map.put("username",ed_username.getText().toString());
                map.put("fullname",ed_name.getText().toString());
                map.put("email",ed_email.getText().toString());
                map.put("password",ed_password.getText().toString());
                map.put("dob",ed_dob.getText().toString());
                map.put("gender",str_gender);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(Registration.this);
        queue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        str_gender = parent.getItemAtPosition(position).toString();
        Log.e("Gender",str_gender);
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}