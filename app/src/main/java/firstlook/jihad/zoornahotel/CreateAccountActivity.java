package firstlook.jihad.zoornahotel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView editTextDateOfBirth;

    private Spinner spinnerGender;
    private EditText editTextPhone;
    private Button buttonCreateAccount;

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

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String dateOfBirthStr = editTextDateOfBirth.getText().toString();
                String gender = spinnerGender.getSelectedItem().toString();
                String phone = editTextPhone.getText().toString();


                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(dateOfBirthStr) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date dateOfBirth;
                try {
                    dateOfBirth = dateFormat.parse(dateOfBirthStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Invalid date format", Toast.LENGTH_SHORT).show();
                    return;
                }


                User user = new User(name, email, password, dateOfBirth, gender, phone);


                Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
