package firstlook.jihad.zoornahotel.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import firstlook.jihad.zoornahotel.R;
import firstlook.jihad.zoornahotel.models.User;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextDateOfBirth;
    private Spinner spinnerGender;
    private EditText editTextPhone;
    private Button buttonCreateAccount;
    private String URL = "http://localhost/android/register.php";
    String name,email,password,dateOfBirth,gender,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        spinnerGender = findViewById(R.id.spinnerGender);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genders_array, android.R.layout.simple_spinner_item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerGender.setAdapter(adapter);

        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
//
//        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = editTextName.getText().toString();
//                String email = editTextEmail.getText().toString();
//                String password = editTextPassword.getText().toString();
//                String dateOfBirthStr = editTextDateOfBirth.getText().toString();
//                String gender = spinnerGender.getSelectedItem().toString();
//                String phone = editTextPhone.getText().toString();
//
//                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
//                        TextUtils.isEmpty(dateOfBirthStr) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(phone)) {
//                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                Date dateOfBirth;
//                try {
//                    dateOfBirth = dateFormat.parse(dateOfBirthStr);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), "Invalid date format", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                User user = new User(name, email, password, dateOfBirth, gender, phone);
//
//                Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });
    }

    public void save(View view){
        name = editTextName.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        dateOfBirth = editTextDateOfBirth.getText().toString().trim();
        gender = spinnerGender.getSelectedItem().toString().trim();
        phone = editTextPhone.getText().toString().trim();
        if(!name.equals("")&& !email.equals("")&&!password.equals("")&&!dateOfBirth.equals("")&&!gender.equals("")&&!phone.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(CreateAccountActivity.this, RoomActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(CreateAccountActivity.this, "Invalid Login email or password", Toast.LENGTH_SHORT);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CreateAccountActivity.this,error.toString().trim(),Toast.LENGTH_SHORT);
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> data = new HashMap<>();
                    data.put("email",name);
                    data.put("email",email);
                    data.put("password",password);
                    data.put("email",dateOfBirth);
                    data.put("email",gender);
                    data.put("email",phone);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
    public void login(){
        Intent intent =  new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String formattedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, dayOfMonth);
                        editTextDateOfBirth.setText(formattedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}
