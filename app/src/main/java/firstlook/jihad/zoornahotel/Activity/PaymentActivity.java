package firstlook.jihad.zoornahotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import firstlook.jihad.zoornahotel.R;
import firstlook.jihad.zoornahotel.models.Room;

public class PaymentActivity extends AppCompatActivity {
    private Button payNowButton;

    private Room selectedRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Retrieve the selected room from the intent
        selectedRoom = (Room) getIntent().getSerializableExtra("room");

        // Initialize views
        payNowButton = findViewById(R.id.payNowButton);

        // Handle pay now button click
        payNowButton.setOnClickListener(v -> processPayment());
    }

    private void processPayment() {
        // Perform the payment logic here
        // You can customize this method according to your application's requirements
        Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show();

        // Optionally, you can navigate to a confirmation activity or perform any additional steps
        Intent intent = new Intent(PaymentActivity.this, ConfirmationActivity.class);
        intent.putExtra("room", selectedRoom);
        startActivity(intent);

        finish();
    }
}
