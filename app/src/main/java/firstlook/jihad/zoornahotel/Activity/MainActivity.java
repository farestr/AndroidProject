package firstlook.jihad.zoornahotel.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import firstlook.jihad.zoornahotel.R;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private String email,password;
    private Button buttonLogin;
    private Button buttonCreateAccount;
    private String URL = "http://localhost/android/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

      buttonLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              login(v);
          }
      });
      buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              register(v);
          }
      });
    }
public void login(View view){
        email = editTextEmail.getText().toString().trim();
    password = editTextPassword.getText().toString().trim();
    if(!email.equals("")&& !password.equals("")){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Intent intent = new Intent(MainActivity.this, RoomActivity.class);
                    startActivity(intent);
                    finish();
                } else if (response.equals("failure")) {
                    Toast.makeText(MainActivity.this, "Invalid Login email or password", Toast.LENGTH_SHORT);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString().trim(),Toast.LENGTH_SHORT);
            }
        }){
//            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("email",email);
                data.put("password",password);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }else{
        Toast.makeText(this,"fileds cannot be empty",Toast.LENGTH_SHORT);
    }
}
    public void register(View view){
        Intent intent = new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }
    // Method to perform login authentication (dummy implementation)
    private boolean performLogin(String email, String password) {
        // Replace this with your actual login authentication logic
        // For demonstration purposes, return true if email is "admin" and password is "admin"
        return email.equals("admin") && password.equals("admin");
    }
}
