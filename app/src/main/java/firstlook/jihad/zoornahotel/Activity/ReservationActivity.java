package firstlook.jihad.zoornahotel.Activity;//package firstlook.jihad.zoornahotel.Activity;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import firstlook.jihad.zoornahotel.R;
//import firstlook.jihad.zoornahotel.models.Room;
//
//public class ReservationActivity extends AppCompatActivity {
//    private TextView roomNumberTextView;
//    private TextView roomTypeTextView;
//    private TextView priceTextView;
//    private Button bookNowButton;
//
//    private Room selectedRoom;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reservation);
//
//        // Retrieve the selected room from the intent
//        selectedRoom = (Room) getIntent().getSerializableExtra("room");
//
//        // Initialize views
//        roomNumberTextView = findViewById(R.id.roomNumberTextView);
//        roomTypeTextView = findViewById(R.id.roomTypeTextView);
//        priceTextView = findViewById(R.id.priceTextView);
//        bookNowButton = findViewById(R.id.bookNowButton);
//
//        // Set the room details
//        roomNumberTextView.setText(selectedRoom.getRoomNumber());
//        roomTypeTextView.setText(selectedRoom.getRoomType());
//        priceTextView.setText(String.format("$%.2f", selectedRoom.getPrice()));
//
//        // Handle book now button click
//        bookNowButton.setOnClickListener(v -> bookRoom());
//    }
//
//    private void bookRoom() {
//        // Perform the booking logic here
//        // You can customize this method according to your application's requirements
//        Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show();
//        finish();
//    }
//}
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import firstlook.jihad.zoornahotel.Activity.PaymentActivity;
import firstlook.jihad.zoornahotel.R;
import firstlook.jihad.zoornahotel.models.Room;

public class ReservationActivity extends AppCompatActivity {
    private TextView roomNumberTextView;
    private TextView roomTypeTextView;
    private TextView priceTextView;
    private Button bookNowButton;

    private Room selectedRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Retrieve the selected room from the intent
        selectedRoom = (Room) getIntent().getSerializableExtra("room");

        // Initialize views
        roomNumberTextView = findViewById(R.id.roomNumberTextView);
        roomTypeTextView = findViewById(R.id.roomTypeTextView);
        priceTextView = findViewById(R.id.priceTextView);
        bookNowButton = findViewById(R.id.bookNowButton);

        // Set the room details
        roomNumberTextView.setText(selectedRoom.getRoomNumber());
        roomTypeTextView.setText(selectedRoom.getRoomType());
        priceTextView.setText(String.format("$%.2f", selectedRoom.getPrice()));

        // Handle book now button click
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open PaymentActivity to continue the progress
                Intent intent = new Intent(ReservationActivity.this, PaymentActivity.class);
                intent.putExtra("room", selectedRoom);
                startActivity(intent);
            }
        });
    }
}
