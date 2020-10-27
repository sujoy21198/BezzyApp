package com.bezzy.Ui.View.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Forgot_pas extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Button button;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_screen);
        imageView=findViewById(R.id.back_image);
        editText=findViewById(R.id.email_send);
        button=findViewById(R.id.btn_logedin);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emailsend();

            }
        });
        progressDialog = new ProgressDialog(Forgot_pas.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Please Wait..");
    }
    public void Emailsend(){
        if(Utility.internet_check(Forgot_pas.this)) {
            progressDialog.show();
            callAPIEmailSend(APIs.BASE_URL+APIs.FORGETPASSWORDSEND);
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(Forgot_pas.this,"No Network!",Toast.LENGTH_SHORT).show();
        }


    }
    private void callAPIEmailSend(String url){
        final StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("resp").equals("true")){
                        progressDialog.dismiss();
                        Toast.makeText(Forgot_pas.this,jsonObject.getString("reg_msg"),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Forgot_pas.this,COTPActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Utility.setOtpScreen(Forgot_pas.this,"1");
                        Utility.setUserId(Forgot_pas.this,jsonObject.getString("log_userID"));

                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(Forgot_pas.this,jsonObject.getString("reg_msg"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Error",error.toString());

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("email",editText.getText().toString());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Forgot_pas.this);
        queue.add(stringRequest);

    }
}