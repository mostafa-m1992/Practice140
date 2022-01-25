package com.example.practice140;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText_email, editText_password;
    Button registerBTN;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText_email = findViewById(R.id.email);
        editText_password = findViewById(R.id.password);
        registerBTN = findViewById(R.id.registerBTN);

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*requestQueue = Volley.newRequestQueue(getApplicationContext());

                String email = editText_email.getText().toString().trim() ;
                String password = editText_password.getText().toString().trim() ;

                String BASE_URL = "http://mostafa.pcshared.com/store/";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "insert.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), "error listener ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);*/

                register(editText_email.getText().toString().trim(), editText_password.getText().toString().trim());

                /*final TextView textView = findViewById(R.id.textView);

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="https://www.google.com";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                textView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                    }
                });

                queue.add(stringRequest);*/
            }
        });
    }

    private void register(final String email, final String pass) {
        String BASE_URL = "http://mostafa.pcshared.com/store/insert.php";
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        editText_email.setText("");
        editText_password.setText("");
        progressDialog.show();

        Response.Listener<String> listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        StringRequest request=new StringRequest(Request.Method.POST, BASE_URL, listener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("email",email);
                map.put("password",pass);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}